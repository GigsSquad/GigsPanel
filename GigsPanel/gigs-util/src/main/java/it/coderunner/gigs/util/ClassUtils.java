package it.coderunner.gigs.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClassUtils {

	public static Set<String> getMapAndCollectionsFrom(Class<?> clazz){
		Set<String> properties = new HashSet<String>();
		for(Field field : getAllFields(new ArrayList<Field>(), clazz)){
			if (Collection.class.isAssignableFrom(field.getType()) || Map.class.isAssignableFrom(field.getType())) {
				properties.add(field.getName());
			}
		}
		return properties;
	}
	
	private static List<Field> getAllFields(List<Field> fields, Class<?> type) {
	    fields.addAll(Arrays.asList(type.getDeclaredFields()));

	    if (type.getSuperclass() != null) {
	        fields = getAllFields(fields, type.getSuperclass());
	    }

	    return fields;
	}
}
