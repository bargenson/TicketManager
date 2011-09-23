package fr.bargenson.util.test.dbunit;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.xml.sax.InputSource;

public class DbUnitUtils {
	
	public static void loadDataset(Connection connection, String datasetFile) throws SQLException {
		connection.prepareStatement("SET REFERENTIAL_INTEGRITY FALSE;").execute();
		IDatabaseConnection dbUnitconnection = null;
		try {
			dbUnitconnection = new DatabaseConnection(connection);
			InputStream datasetStream = new FileInputStream(datasetFile);
			FlatXmlProducer producer = new FlatXmlProducer(new InputSource(datasetStream));
			FlatXmlDataSet dataset = new FlatXmlDataSet(producer);
			DatabaseOperation.CLEAN_INSERT.execute(dbUnitconnection, dataset);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Impossible to init DB Unit.");
		} finally {
			connection.prepareStatement("SET REFERENTIAL_INTEGRITY TRUE;").execute();
			if(dbUnitconnection != null)
				dbUnitconnection.close();
		}
	}

}
