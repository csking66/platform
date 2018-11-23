package com.sos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
* @ClassName: Test
* @Description: 
* @date 2018年11月22日
*
*/

public class Test {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		List<List<String>> aa = new ArrayList<>();
		ExecutorService pool = Executors.newFixedThreadPool(3);
		Callable<List<List<String>>> callable1 = ()-> {
			System.out.println("w");
            return aa();
        };
        
        pool.execute(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("--sdasda");
				
			}
		});      

        Callable<List<List<String>>> callable2 = ()-> {
        	System.out.println("d");
            return bb();
        };
        
        Future<List<List<String>>> f1 =  pool.submit(callable1);
        
        Future<List<List<String>>> f2 =  pool.submit(callable2);
        aa.addAll(f1.get());
        aa.addAll(f2.get());
        pool.shutdown();
        
        System.out.println(aa.size());
        
        
	}
	
	static List<List<String>> aa(){
		List<List<String>> bb = new ArrayList<>();
		for(int i = 0; i < 50 ; i++) {
			List<String> aa = new ArrayList<>();
			for(int j = 0; j < 50 ; j++) {
				aa.add("w");
			}
			bb.add(aa);
		}		
		return bb;
	}
	
	static List<List<String>> bb(){
		System.out.println("kkkkkkkkkkkkkkkkkkk");
		List<List<String>> bb = new ArrayList<>();
		for(int i = 0; i < 10 ; i++) {
			List<String> aa = new ArrayList<>();
			for(int j = 0; j < 10 ; j++) {
				aa.add("v");
			}
			bb.add(aa);
		}		
		return bb;
	}


}
