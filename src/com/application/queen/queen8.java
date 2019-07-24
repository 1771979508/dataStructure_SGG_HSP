package com.application.queen;

import java.awt.print.Printable;
import java.sql.Array;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年7月24日 下午9:51:13	
*/
public class queen8 {
	
	int max = 8;
	// 定义数组array，保存皇后放置位置的结果，比如arr={0,4,7,5,2,6,1,3}
	int[] array = new int[max];
	
	// 统计有多少种解法
	static int count = 0;
	
	public static void main(String[] args) {
		
		queen8 queen8 = new queen8();
		queen8.check(0);
		
		System.out.printf("一共有%d种解法",count);
		
	}
	
	// 编写放置皇后的代码，放置第n个皇后 - n代表第几个皇后，设计的隐藏条件是 n 也代表了行的意思
	// 注意：check是每一次递归时，进入到check中都有 for(int i=0;i<max;i++),因此会有回溯
	private void check(int n){
		if(n == max){  // n = 8; 当n为8时，相当于在放置第9个元素，n从0开始取值
 			print();
 			return;
		}
		
		// 在同一行(n) 通过列(i)的变化来依次放入皇后
		for(int i=0;i<max;i++){
			// 先把当前这个皇后n，放到该行的第1列
			array[n] = i;
			// 判断当放置第n个皇后到i列时，是否冲突
			if(judge(n)){  // 如果不冲突，放下一个皇后，行向上移动，列正常从第1列往后移动
				// 接着放n+1个皇后，即开始递归
				check(n+1);
			}
			
			// 如果冲突，就继续执行 array[n] = i;即将第n个皇后，放置在本行得 后移的一个位置
			
		}
	}
	
	
	
	// 查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
	/*
	 * 
	 * 		n 表示第n个皇后
	 * 
	 * 
	 * */
	private boolean judge(int n){
		for(int i=0;i<n;i++){
			// 说明
				//1.array[i] == array[n] 表示判断 第n个皇后 是否和前面的n-1个皇后在同一列
				//2.Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否在同一斜线上 (y=x)
				//		n = 1 放置第2列1  n=1 array[1] = 1
				// Math.abs(1-0) == 1 Math.abs(array[n] - array[i]) = Math.abs(1-0) = 1
				//3.判断是否在同一行，没有必要，n每次都在递增
			if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){
				return false;
			}
		}
		return true;
	}
	
	// 写一个方法，可以将皇后摆放的位置输出
	private void print(){
		count++;
		for(int i=0;i<array.length;i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
		
	}
	
}
	