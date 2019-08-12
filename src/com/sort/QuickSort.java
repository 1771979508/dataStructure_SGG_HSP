package com.sort;

import java.util.Arrays;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年8月12日 下午10:18:34	
*/
public class QuickSort {
	
	public static void main(String[] args) {
		
		int[] arr = {-9,78,0,23,-567,70};
		quickSort(arr,0,arr.length-1);
		System.out.println("Arrays："+Arrays.toString(arr));
		
	}
	
	public static void quickSort(int[] arr,int left,int right){
		int l = left;  // 左下标
		int r = right; // 右下标
		// pivot 中轴值
		int pivot = arr[(left+right)/2];
		
		int temp = 0; // 临时变量，作为交换时使用
		
		// while循环的目的是让比pivot值小放到左边
		// 比pivot值大的放到右边
		while(l < r){
			// 在pivot的左边一直找，找到大于等于pivot值，才退出
			while(arr[l] < pivot){
				l += 1;
			}
			// 在pivot的右边一直找，找到小于等于pivot值，才退出
			while(arr[r] > pivot){
				r -= 1;
			}
			
			// 如果l>=r说明pivot的左右两的值，已经按照左边全部是
			// 小于等于pivot值，右边全是大于等于pivot的值
			if(l >= r){
				break;
			}
			
			// 开始交换
			temp = arr[l];
			arr[l] = arr[r];
			arr[r] = temp;
			
			// 如果交换完成之后，发现arr[l]==pivot值相等， -- 就向前移动
			if(arr[l] == pivot){
				r -= 1;
			}
			if(arr[r] == pivot){
				l += 1;
			}
			
		}
		
		// 经过上面这个while循环之后，我们可以得到的是小于0的都在左边，大于0的都在右边
		
		
		// 如果 l==r，必须l++，r--，否则会出现栈溢出
		if(l==r){
			l+=1;
			r-=1;
		}
		
		// 开始写递归，将左边的顺序进行排序
		if(left < r){
			quickSort(arr, left, r);
		}
		// 在这将下面的注释，可以得到左边已经排序好的数组
		
		
		// 向右递归
		if(right > l){
			quickSort(arr,l, right);
		}
		
		
		
	}
	
	
}
	