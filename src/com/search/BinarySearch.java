package com.search;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年8月22日 下午11:12:47	
*/

/*
 * 	二分查找
 * 
 * */
public class BinarySearch {
	
	public static void main(String[] args) {
		
		int arr[] = {1,8,10,89,1000,1234};
		int resIndex = binarySearch(arr, 0, arr.length-1, -1);
		System.out.println("找到的resIndex值为："+resIndex);
		
	}
	
	//二分算法
	/**
	 * 
	 * @param arr  数组
	 * @param left 左边的索引
	 * @param right 右边的索引
	 * @param findVal 要查找的值
	 * @return  如果找到就返回下标，如果没有找到，就返回-1
	 */
	public static int binarySearch(int[] arr,int left,int right,int findVal){
		
		// 判断出口 - 如果位置成为left在right的右边即
		if(left > right){
			return -1;
		}
		
		int mid = (left+right)/2;
		int midVal = arr[mid];
		
		if(findVal > midVal){  // 向右递归
			return binarySearch(arr, mid+1, right, findVal);
		}else if(findVal < midVal){  // 向左递归
			return binarySearch(arr, left, mid-1, findVal);
		}else {
			return mid;
		}
		
	}
	
}
	