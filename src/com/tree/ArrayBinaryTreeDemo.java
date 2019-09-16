package com.tree;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年9月16日 下午10:30:01	
*/

/*
 * 		顺序存储二叉树的特点：
 * 			1.顺序二叉树通常只考虑完全二叉树
 * 			2.第n个元素的左子节点为2*n+1
 * 			3.第n个元素的右子节点为2*n+2
 * 			4.第n个元素的父节点为(n-1)/2
 * 
 * 		n:表示二叉树中的第几个元素(按0开始编号)
 * 
 * 					1(n:0)
 * 				2(n:1)	3(n:2)
 * 			  4   5   6   7
 * 
 * */


public class ArrayBinaryTreeDemo {
	
	public static void main(String[] args) {
		
		int[] arr = {1,2,3,4,5,6,7};
		// 创建一个ArrBinaryTree
		ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
		arrBinaryTree.preOrder();
		
	}
	
}
	
// 编写一个ArrayBinaryTree，实现顺序存储二叉树遍历
class ArrBinaryTree{
	private int[] arr; // 存储数据节点的数组
	
	public ArrBinaryTree(int[] arr){
		this.arr = arr;
	}
	
	// 重载preOrder
	public void preOrder(){
		this.preOrder(0);
	}
	
	// 编写一个方法，完成顺序存储二叉树的前序遍历
	/**
	 * 
	 * @param index   数组的下标
	 */
	public void preOrder(int index){
		// 如果数组为空，或者arr.length=0
		if(arr == null || arr.length == 0){
			System.out.println("数组为空，不能按照二叉树的前序遍历");
		}
		//输出当前这个元素
		System.out.print(arr[index]);
		// 向左递归
		if((2*index+1)<arr.length){
			preOrder(2*index+1);
		}
		//向右递归
		if((2*index+2)<arr.length){
			preOrder(2*index+2);
		}
	}
	
}
