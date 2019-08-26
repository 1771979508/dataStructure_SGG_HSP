package com.search;

import java.util.Arrays;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年8月25日 下午11:07:33	
*/
public class InsertValueSearch {
	
	public static void main(String[] args) {
		
		int[] arr = new int[100];
		for(int i=0;i<100;i++){
			arr[i] = i;
		}
		System.out.println("原数组："+Arrays.toString(arr));
		
		int index = insertValueSearch(arr,0,arr.length-1,1);
		System.out.println("找到的索引为："+index);
		
	}
	
	// 编写插值算法
	/**
	 * 
	 * @param arr		数组
	 * @param left		左边索引
	 * @param right		右边索引
	 * @param findVal	查找值
	 * @return			如果找到，就返回对应的下标
	 */
	public static int insertValueSearch(int[] arr,int left,int right,int findVal){
		// 注意：findVal<arr[0] 和 findVal > arr[arr.length-1] 必须需要
		// 否则我们得到的mid可能越界
		if(left > right || findVal < arr[0] || findVal>arr[arr.length-1]){
			return -1;
		}
		
		// 与二分查找的区别就在于此：中间值mid的算法不同，就会有不同的结果，就像取模运算一样
		// 求出mid（查找的值也参与计算所以能够自适应的计算）
		int mid = left + (right-left)*(findVal-arr[left]) / (arr[right]-arr[left]);
		
		
		int midVal = arr[mid];
		if(findVal > midVal){  // 说明应该向右递归
			return insertValueSearch(arr, mid+1, arr.length-1, findVal);
		}else if(findVal < midVal){  // 说明应该向左递归
			return insertValueSearch(arr, left, mid-1, findVal);
		}else{
			return mid;
		}
		
	}
	
}
	