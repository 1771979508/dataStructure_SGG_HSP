package com.recursion;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年7月23日 下午7:40:37	
*/
public class RecursionTest {
	
	public static void main(String[] args) {
		
		// 通过打印问题，回顾递归调用机制
//		test(4);
		
		int res = factorial(3);
		System.out.println("res=" + res);
		
		
	}
	
	// 打印问题
		//1.不添加else代码，输出的是 n=2,3,4
		//2.添加else，输出的是 n=2
	public static void test(int n){
		if(n > 2){
			test(n - 1);
		}
//2.		else{
			System.out.println("n=" + n);
//2.		}
	}
	
	
	// 阶乘问题
	public static int factorial(int n){
		if(n==1){
			return 1;
		}else{
			return factorial(n - 1)* n;  // 1 * 2 * 3
		}
	}
	
	
	
	
	
	
	
}
	