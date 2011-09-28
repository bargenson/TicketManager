package fr.bargenson.util.test.dbunit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.xml.sax.InputSource;

public class DbUnitUtils {

	public static void loadDataset(Connection connection, String datasetFile) throws SQLException {
		try {
			cleanInsert(connection, datasetFile);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Impossible to init DB Unit.");
		}
	}

	private static void cleanInsert(Connection connection,
			String datasetFile) throws DatabaseUnitException,
			FileNotFoundException, DataSetException, SQLException {

		connection.prepareStatement("SET REFERENTIAL_INTEGRITY FALSE;").execute();
		IDatabaseConnection dbUnitconnection = null;
		try {
			dbUnitconnection = new DatabaseConnection(connection);
			dbUnitconnection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
			FlatXmlDataSet dataset = getAsFlatXmlDataset(datasetFile);
			DatabaseOperation.CLEAN_INSERT.execute(dbUnitconnection, dataset);
		} finally {
			connection.prepareStatement("SET REFERENTIAL_INTEGRITY TRUE;").execute();
			if(dbUnitconnection != null) 
				dbUnitconnection.close();
		}
	}

	private static FlatXmlDataSet getAsFlatXmlDataset(String datasetFile)
			throws FileNotFoundException, DataSetException {

		InputStream datasetStream = new FileInputStream(datasetFile);
		FlatXmlProducer producer = new FlatXmlProducer(new InputSource(datasetStream));
		FlatXmlDataSet dataset = new FlatXmlDataSet(producer);
		return dataset;
	}

}
