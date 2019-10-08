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
		int temp = arr[i]; // 先取出当前元素的值，保存在临时变量中
		// 开始调整
		// 说明
		// 1.k=i*2+1 其中，k为i节点的左子节点
		for(int k=i*2+1;k<length;k=k*2+1){   // k=k*2+1 为啥？
			if(k+1<length && arr[k] < arr[k+1]){
				k++;  // k 指向右子节点
			}
			if(arr[k] > temp){  // 如果子节点大于父节点
				arr[i] = arr[k];  // 把较大的值赋给当前节点
				i = k;  // ！！！important 将i指向k，继续循环比较
			}else{
				break;  // 由于树是从左向右，从上往下依次生成的
			}
		}
		// 当for 循环结束后，我们已经将以i为父节点的树的最大值，放在了最顶部(局部)
		arr[i] = temp;  // 将temp值放到调整后的位置
	}
	
}
	