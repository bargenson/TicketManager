package fr.bargenson.util.test;

import java.lang.reflect.Field;

public class ReflectionHelper {
	
	public static void inject(Object target, String field, Object value) 
			throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException {
		
		Field ticketServiceField = target.getClass().getDeclaredField(field);
		ticketServiceField.setAccessible(true);
		ticketServiceField.set(target, value);
	}

}
