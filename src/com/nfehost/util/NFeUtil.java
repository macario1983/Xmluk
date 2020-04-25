package com.nfehost.util;

public final class NFeUtil {

	public static String formatChave(String chaveNfe) {
		
		StringBuilder chave = new StringBuilder();

		if (!StringUtil.isStringNullOrEmpty(chaveNfe)) {
			chave.append(chaveNfe);
			chave.insert( 4, " ");
			chave.insert( 9, " ");
			chave.insert(14, " ");
			chave.insert(19, " ");
			chave.insert(24, " ");
			chave.insert(29, " ");
			chave.insert(34, " ");
			chave.insert(39, " ");
			chave.insert(44, " ");
			chave.insert(49, " ");
		}
		
		return chave.toString();
	}
}
