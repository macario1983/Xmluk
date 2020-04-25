package com.nfehost.util;

public class MathUtil {

	public static int[] generateIndex(Object[] array, int numberThread) {

		int[] maxIndexOfThread = new int[numberThread];

		for (int i = 0; i < numberThread; i++) {
			maxIndexOfThread[i] = array.length / numberThread;
		}
		
		maxIndexOfThread[numberThread - 1] += array.length % numberThread;

		return maxIndexOfThread;
	}

}
