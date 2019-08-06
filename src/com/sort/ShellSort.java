package com.sort;

import java.util.Arrays;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年8月6日 下午10:19:19	
*/
public class ShellSort {
	
	public static void main(String[] args) {
		
		int[] arr = {8,9,1,7,2,3,5,4,6,0};
//		shellSort(arr);
		// 优化后的希尔排序
		shellSortOptimize(arr);
		
	}
	
	// 使用逐步推导的方式来编写希尔排序
	public static void shellSort(int[] arr){
		int temp = 0;
		// 希尔排序的第1轮
		// 因为第1轮排序，是将10个数据分成了5组
		for(int i=5;i<arr.length;i++){
			// 遍历各组中所有的元素(共5组，每组有2个元素) ，步长为5
			for(int j=i-5;j>=0;j-=5){
				// 如果当前元素大于加上步长后的那个元素，说明交换
				if(arr[j] > arr[j+5]){
					temp = arr[j];
					arr[j] = arr[j+5];
					arr[j+5] = temp;
				}
			}
		}
		System.out.println("希尔排序1轮后="+Arrays.toString(arr));
		
		
		// 希尔排序的第1轮
		// 因为第2轮排序，是将10个数据分成了 5/2 = 2 组
		for(int i=2;i<arr.length;i++){
			// 遍历各组中所有的元素(共5组，每组有2个元素) ，步长为5
			for(int j=i-2;j>=0;j-=2){
				// 如果当前元素大于加上步长后的那个元素，说明交换
				if(arr[j] > arr[j+2]){
					temp = arr[j];
					arr[j] = arr[j+2];
					arr[j+2] = temp;
				}
			}
		}
		System.out.println("希尔排序2轮后="+Arrays.toString(arr));
		
		// 希尔排序的第3轮
		// 因为第3轮排序，是将10个数据分成了 2/2 = 1 组
		for(int i=1;i<arr.length;i++){
			// 遍历各组中所有的元素(共5组，每组有2个元素) ，步长为5
			for(int j=i-1;j>=0;j-=1){
				// 如果当前元素大于加上步长后的那个元素，说明交换
				if(arr[j] > arr[j+1]){
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		System.out.println("希尔排序3轮后="+Arrays.toString(arr));
		
	}
	
	
	public static void shellSortOptimize(int[] arr){
		int temp = 0;
		int count = 0;
		for(int gap = arr.length/2;gap>0;gap /= 2){
			for(int i=gap;i<arr.length;i++){
				// 遍历各组中所有的元素(共5组，每组有2个元素) ，步长为5
				for(int j=i-gap;j>=0;j-=gap){
					// 如果当前元素大于加上步长后的那个元素，说明交换
					if(arr[j] > arr[j+gap]){
						temp = arr[j];
						arr[j] = arr[j+gap];
						arr[j+gap] = temp;
					}
				}
			}
			System.out.println("希尔排序"+(++count)+"轮后="+Arrays.toString(arr));
		}
	}
	
	
}
	