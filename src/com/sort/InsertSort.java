package com.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年8月5日 下午9:59:55	
*/

/*
 * 
 * 		插入排序：先将第一个数当作有序数组，然后为后面的无序数组找到合适的位置插入到有序数组中去即可
 * 
 * 
 * */

public class InsertSort {
	
	public static void main(String[] args) {
		
		int[] arr = {101,34,119,1};
//		insertSort(arr);
		
		
		// 测试一下冒泡排序的速度，给80000个数据进行排序，看花费的时间
		// 先创建随机数
//		int[] arr = new int[80000];
//		for(int i=0;i<80000;i++){
//			arr[i] = (int)(Math.random()*80000);  // 生成80000个0-80000之间的随机数
//		}
//		// 开始计算时间
//		Date date = new Date();
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String date1Str = simpleDateFormat.format(date);
//		System.out.println("排序前的时间为："+date1Str);
		
		
		insertSortOptimize(arr);
		
		
//		Date date2 = new Date();
//		String date2Str = simpleDateFormat.format(date2);
//		System.out.println("排序前的时间为："+date2Str);
		
	}
	
	// 插入排序
//	public static void insertSort(int[] arr){
//		
//		// 第一轮
//		// {101,34,119,1} =》 {101,101,119,1}
//		// 定义待插入的数
//		int insertVal = arr[1];
//		int insertIndex = 1 - 1;// 即 arr[1] 的前面这个数的下标
//		
//		// 给insertVal 找到插入的位置
//		// 说明
//		// 1.insertIndex >= 0 保证在给insertVal 找插入位置，不越界
//		// 2.insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置 - 相当于找出来一个大于第一个数的值就好了
//		// 3.就需要将arr[insertIndex] 后移
//		while(insertIndex >= 0 && insertVal < arr[insertIndex] ){
//			arr[insertIndex + 1] = arr[insertIndex];
//			insertIndex--;
//		}
//		// 当退出while循环时，说明插入的位置找到，insertIndex+1
//		arr[insertIndex+1] = insertVal;
//		
//		System.out.println("第1轮插入排序之后的结果为："+Arrays.toString(arr));
//		
//		
//		insertVal = arr[2];
//		insertIndex = 2 - 1;// 即 arr[1] 的前面这个数的下标
//		
//		// 给insertVal 找到插入的位置
//		// 说明
//		// 1.insertIndex >= 0 保证在给insertVal 找插入位置，不越界
//		// 2.insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置 - 相当于找出来一个大于第一个数的值就好了
//		// 3.就需要将arr[insertIndex] 后移
//		while(insertIndex >= 0 && insertVal < arr[insertIndex] ){
//			arr[insertIndex + 1] = arr[insertIndex];
//			insertIndex--;
//		}
//		// 当退出while循环时，说明插入的位置找到，insertIndex+1
//		arr[insertIndex+1] = insertVal;
//		
//		System.out.println("第2轮插入排序之后的结果为："+Arrays.toString(arr));
//		
//		
//		insertVal = arr[3];
//		insertIndex = 3 - 1;// 即 arr[1] 的前面这个数的下标
//		
//		// 给insertVal 找到插入的位置
//		// 说明
//		// 1.insertIndex >= 0 保证在给insertVal 找插入位置，不越界
//		// 2.insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置 - 相当于找出来一个大于第一个数的值就好了
//		// 3.就需要将arr[insertIndex] 后移
//		while(insertIndex >= 0 && insertVal < arr[insertIndex] ){
//			arr[insertIndex + 1] = arr[insertIndex];
//			insertIndex--;
//		}
//		// 当退出while循环时，说明插入的位置找到，insertIndex+1
//		arr[insertIndex+1] = insertVal;
//		
//		System.out.println("第3轮插入排序之后的结果为："+Arrays.toString(arr));
//	}
	
	
	public static void insertSortOptimize(int[] arr){
		for(int i=1;i<arr.length;i++){
			// {101,34,119,1} =》 {101,101,119,1}
			// 定义待插入的数
			int insertVal = arr[i];
			int insertIndex = i - 1;// 即 arr[1] 的前面这个数的下标
			
			// 给insertVal 找到插入的位置
			// 说明
			// 1.insertIndex >= 0 保证在给insertVal 找插入位置，不越界
			// 2.insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置 
			// 3.就需要将arr[insertIndex] 后移
			while(insertIndex >= 0 && insertVal < arr[insertIndex]){
				arr[insertIndex + 1] = arr[insertIndex];
				insertIndex--;
			}
			// 当退出while循环时，说明插入的位置找到，insertIndex+1
			
			//优化-如果当前索引在i的位置的话，则代表刚好是顺序的不需要再进行重新赋值
			if(insertIndex+1 != i){
				arr[insertIndex+1] = insertVal;
			}
			
//			System.out.println("第"+i+"轮插入排序之后的结果为："+Arrays.toString(arr));
		}
	}
	
}
	