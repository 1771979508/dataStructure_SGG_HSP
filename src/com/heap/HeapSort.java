package com.heap;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年10月8日 下午10:43:11	
*/
public class HeapSort {
	
	public static void main(String[] args) {
		
		// 要求将数组进行升序排序
		int arr[] = {4,6,8,5,9};
		
		
	}
	
	// 编写一个堆排序的方法
	public static void heapSort(int arr[]){
		System.out.println("堆排序！！");
	}
	
	// 将一个数组（完全二叉树），调整成一个大顶堆
	/**
	 * 功能：完成将以i对应的非叶子节点的树调整成大顶堆(以i为顶点的子树)   =  其中 i=arr.length/2-1
	 * 举例 int arr[] = {4,6,8,5,9} => i=1=> adjustHeap => 得到{4，9，8，5，6}
	 * 如果我们再次调用 adjust 传入的是 i=0=> 得到{4,9,8,5,6}=>{9,6,8,5,4}
	 * @param arr	待调整的数组
	 * @param i	表示非叶子节点在数组中的索引
	 * @param length	表示对多少个元素进行调整， length 是逐步减少的
	 */
	public static void adjustHeap(int arr[],int i,int length){
		
	}
	
}
	