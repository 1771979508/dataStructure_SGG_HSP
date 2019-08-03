package com.sort;

import java.util.Arrays;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年8月3日 下午4:13:44	
*/
public class SelectSort {
	
	public static void main(String[] args) {
		
		int[] arr = {101,34,119,1};
		selectSort(arr);
		
	}
	
	// 选择排序
	public static void selectSort(int[] arr){
		// 使用逐步推导的方式来讲解选择排序
		// 第1论
		// 原始的数组：	101，34，119，1
		// 第一轮排序：	1，34，119，101
		// 算法 先简单--> 做复杂，就是可以把一个复杂的算法，拆分成简单的问题 --> 逐步解决
		
		// 第1轮
		System.out.println("第一轮在交换前的数组为：" + Arrays.toString(arr));
		int minIndex = 0;
		int min = arr[0];
		for(int i=0+1;i<arr.length;i++){
			// 找到最小值和最小值的索引
			if(min > arr[i]){
				min = arr[i];
				minIndex = i;
			}
		}
		
		// 将最小值，放在arr[0]，即交换
		if(minIndex != 0){  // 如果最小值的索引不在第一位则开始交换位置和值
			arr[minIndex] = arr[0];  // 现在 minIndex 是最小值的【索引】,将它放到数组的第一位
			arr[0] = min;
		}
		
		System.out.println("第一轮交换得到的数组为：" + Arrays.toString(arr));
		
		System.out.println("===========我是分割线============");
		
		// 第2轮
		System.out.println("第二轮在交换前的数组为：" + Arrays.toString(arr));
		minIndex = 1;
		min = arr[1];
		for(int i=1+1;i<arr.length;i++){
			// 找到最小值和最小值的索引
			if(min > arr[i]){
				min = arr[i];
				minIndex = i;
			}
		}
		
		// 将最小值，放在arr[0]，即交换
		if(minIndex != 1){
			arr[minIndex] = arr[1];  // 现在 minIndex 是最小值的【索引】,将它放到数组的第一位
			arr[1] = min;
		}
		
		System.out.println("第二轮交换得到的数组为：" + Arrays.toString(arr));
				
		System.out.println("===========我是分割线============");
		
		// 第2轮
		System.out.println("第三轮在交换前的数组为：" + Arrays.toString(arr));
		minIndex = 2;
		min = arr[2];
		for(int i=1+2;i<arr.length;i++){
			// 找到最小值和最小值的索引
			if(min > arr[i]){
				min = arr[i];
				minIndex = i;
			}
		}
		
		// 将最小值，放在arr[0]，即交换
		if(minIndex != 2){
			arr[minIndex] = arr[2];  // 现在 minIndex 是最小值的【索引】,将它放到数组的第一位
			arr[2] = min;
		}
		
		System.out.println("第三轮交换得到的数组为：" + Arrays.toString(arr));
		
		
	}
	
}
	