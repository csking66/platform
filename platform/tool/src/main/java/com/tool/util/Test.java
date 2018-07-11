package com.tool.util;

public class Test {

	public static void main(String[] args) {
		
		Integer[] s = new Integer[] {1,5,2,4,8,9};
		int temp ;
		for(int i = 0; i < s.length; i++){
			for(int j = i; j < s.length; j++){
				if(s[j] > s[i]){
					temp = s[i];
					s[i] = s[j];
					s[j] = temp;
				}
			}
			System.out.println(s[i]);
		}
		

	}

}
