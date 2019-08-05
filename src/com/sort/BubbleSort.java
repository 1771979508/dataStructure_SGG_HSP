package com.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年7月29日 下午10:42:12	
*/

/*
 * 		冒泡排序
 * 
 * */
public class BubbleSort {
	
	public static void main(String[] args) {
		
//		int[] arr = {3,9,-1,10,-2,20,30};
//		int[] arr = {1,2,3,4};
		
//		System.out.println("排序前的数组为："+Arrays.toString(arr));
		
		// 测试一下冒泡排序的速度，给80000个数据进行排序，看花费的时间
		// 先创建随机数
		int[] arr = new int[80000];
		for(int i=0;i<80000;i++){
			arr[i] = (int)(Math.random()*80000);  // 生成80000个0-80000之间的随机数
		}
		// 开始计算时间
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date);
		System.out.println("排序前的时间为："+date1Str);
		
		bubble(arr);
		
//		System.out.println("排序后的数组为："+Arrays.toString(arr));
		
		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序前的时间为："+date2Str);
		
	}
	
	
	// 冒泡排序优化代码
	public static void bubble(int[] arr){
		// 第一堂趟排序，就是将最大的数排在最后
		int temp = 0;  // 临时变量
		
		boolean flag = false;  // 标识变量，表示是否j进行过交换
		
		for(int i=0;i<arr.length-1;i++){
			for(int j=0;j<arr.length-1-i;j++){
				// 如果前面的数比后面的数大，则交换
				if(arr[j] > arr[j+1]){
					
					// 如果进行交换则将flag置为true
					flag = true;
					
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
//			System.out.println("第"+(i+1)+"趟排序后的数组为：");
//			System.out.println(Arrays.toString(arr));
			
			if(!flag){  //在一趟排序中，一次都没有交换过，则直接跳过本次的内循环
				break;
			}else {
				flag = false;  // 重置flag!!!,进行下一次判断
			}
			
		} 
	}
	
}
	