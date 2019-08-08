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
		
		// 交换位置之后的排序
		shellSortMoveOptimize(arr);
		
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
	
	// 希尔排序优化版
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
	
	// 对交换式的希尔排序进行优化-> 移位法
	public static void shellSortMoveOptimize(int[] arr){
		// 增量gap,并逐步的缩小增量
		for(int gap = arr.length/2;gap > 0;gap /= 2){
			// 从gap个元素，逐个对其所在的组进行直接插入排序
			for(int i=gap;i<arr.length;i++){
				int j = i;
				// 先将gap这个索引值存起来
				int temp = arr[j];
				// 这里就相当于是 把第一位和总长度/2位置的值进行比较
				if(arr[j] < arr[j - gap]){
					// 小于的话则进行交换
					while(j - gap >=0 && temp < arr[j - gap]){  // 这里不是很懂呐
						// 进行移动
						arr[j] = arr[j - gap];
						j -= gap;
					}
					// 当退出while后，就给temp找到插入的位置
					arr[j] = temp;
				}
			}
		}
	}
	
	
	
}
	