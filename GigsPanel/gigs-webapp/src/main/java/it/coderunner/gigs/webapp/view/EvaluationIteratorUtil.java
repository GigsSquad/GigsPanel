package it.coderunner.gigs.webapp.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.thymeleaf.util.EvaluationUtil;

public class EvaluationIteratorUtil {

	public static List<Object> evaluateAsIterable(final Object value) {
        if (value == null) {
            return Collections.emptyList();
        }
        if(value instanceof Iterator<?>){
        	return collectionFromIterator(value);
        } else {
        	return EvaluationUtil.evaluateAsIterable(value);
        }

    }

	private static List<Object> collectionFromIterator(Object value) {
		List<Object> objects = new ArrayList<Object>();
		Iterator<?> iterator = (Iterator<?>) value;
		while (iterator.hasNext()){
			Object object = iterator.next();
			objects.add(object);
		}
		return Collections.unmodifiableList(objects);
	}
}
