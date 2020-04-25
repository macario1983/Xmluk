package com.nfehost.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadUtil {

	private static final int THREADQUANTITY = 4;
	
	public static int quantityOfThreads() {
		return THREADQUANTITY;
	}

	public static List<Thread> creatPoolThread() {
		return new ArrayList<Thread>(THREADQUANTITY);
	}

	public static int[] generateThreadsIndex(Object[] array) {
		return MathUtil.generateIndex(array, THREADQUANTITY);
	}
	
	public static Object[] sliceArray(Object[] array, int start, int end) {
		 return Arrays.copyOfRange(array, start, end);
	}
	
	public static void startThreads(List<Thread> listThread) {
		for (Thread thread : listThread) {
			thread.start();
		}
		joinThreads(listThread);
	}

	private static void joinThreads(List<Thread> listThread) {
		for (Thread thread : listThread) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}

}
