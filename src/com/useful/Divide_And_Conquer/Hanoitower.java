package com.useful.Divide_And_Conquer;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年10月29日 下午9:42:48	
*/



/*
 * 		汉诺塔实践背景：
 * 			现存在三根柱子，在一根柱子上从下往上按照大小顺序摞着64片黄金圆盘。
 * 			现需要从下面k开始按照大小顺序重新摆放在另一根柱子上。
 * 			并且规定，在小圆盘上不能方法圆盘，在三根柱子之间一次只能移动一个圆盘
 * 
 * */


/*
 * 
 * 		分治算法 - 汉诺塔游戏：
 * 		
 * 			1.如果有一个盘，A->C
 * 		
 * 		如果我们有 n>=2的情况，我们总是k可以看作是两个盘：(1)最下边的盘 (2)上面的盘
 * 			1.先把最上面的盘A->B
 * 			2.把最下边的盘A->C
 * 			3.把B塔的所有盘从B->C	
 * 
 * 
 * */

public class Hanoitower {

	public static void main(String[] args) {
		
		hanoiTower(2,'A','B','C');
		
	}
	
	// 汉诺塔的移动方法
	// 使用分治算法
	public static void hanoiTower(int num,char a,char b,char c){
		// 如果只有一个盘
		if(num == 1){
			System.out.println("第1个盘从 " + a + "->" + c);
		}else{
			// 如果我们有 n>=2 的情况，我们总是可以看做是两个盘，最下边的一个盘和最上边的一个盘
			// 1.先把 最上面 的所有盘 A->B ，移动过程会使用到C
			hanoiTower(num-1, a, c,b);
			// 2.把最下边的的盘 A->C
			System.out.println("第" + num + "个盘从 " + a + "->" + c);
			// 3.把B塔的所有盘 从 B->C ，移动过程中使用到A
			hanoiTower(num-1, b, a, c);
		}
	}
	 
	
	
	

}
	