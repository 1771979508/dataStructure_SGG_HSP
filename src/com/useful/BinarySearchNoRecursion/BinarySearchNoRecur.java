package com.useful.BinarySearchNoRecursion;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年10月29日 下午9:15:07	
*/
public class BinarySearchNoRecur {

	public static void main(String[] args) {
		
		int[] arr = {1,3,8,10,11,67,100};
		int index = binarySearch(arr, 3);
		System.out.println("index=> " + index);
		
	}
	
	// 二分查找的非递归实现
	/**
	 * 
	 * @param arr		需要查找的数组,arr是升序排序
	 * @param target	target 需要查找的数
	 * @return			返回对应的下标，-1表示没有找到
	 */
	public static int binarySearch(int[] arr,int target){
		int left = 0;
		int right = arr.length - 1;
		while(left <= right){ // 说明继续查找
			int mid = (left+right)/2;
			if(arr[mid] == target){
				return mid;
			}else if(arr[mid] > target){
				// 向左边查找
				right = mid - 1;
			}else {
				// 向右边查找
				left = mid + 1;
			}
		}
		// while循环完之后还没有找到，则返回-1
		return -1;
	}
	
}
	