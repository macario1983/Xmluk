package com.nfehost.util;

import java.util.Collection;
import java.util.Map;


public class NullUtil {

	public static Boolean isNull(Object obj) {
		return obj == null;
	}

	public static Boolean isEmpty(final Collection<?> collection) {
		return isNull(collection) || collection.isEmpty();
	}

	public static Boolean isEmpty(final Object[] array) {
		return isNull(array) || (array.length == 0);
	}
	
	public static Integer integerNotNull(final Integer inteiro) {
		return isNull(inteiro) ? Integer.valueOf(0) : inteiro;
	}

	public static Boolean isEmptyMap(final Map<?, ?> map) {
		return isNull(map) || map.isEmpty();
	}

	public static Long longNotNull(final Long value) {
		return isNull(value) ? Long.valueOf(0L) : value;
	}

}

