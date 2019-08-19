package com.sort;

import java.util.Arrays;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年8月19日 下午10:44:38	
*/

/*
 * 
 * 		归并排序
 * 
 * 
 * */
public class MergeSort {
	
	public static void main(String[] args) {
		
		int[] arr = {8,4,5,7,1,3,6,2};
		int[] temp = new int[arr.length];  // 归并排序需要额外一个空间
		
		mergeSort(arr, 0, arr.length-1, temp);
		
		System.out.println("归并排序后="+Arrays.toString(arr));
		
	}
	
	// 分+合方法
	public static void mergeSort(int[] arr,int left,int right,int[] temp){
		if(left < right){
			int mid = (left + right)/2; // 中间索引
			//向左递归进行分解
			mergeSort(arr, left, mid, temp);
			// 向右递归进行分解
			mergeSort(arr, mid+1, right, temp);
			
			// 核心-分解完成之后便进行合并
			merge(arr, left, mid, right, temp);
			
		}
	}
	
	
	/*		合并的方法		
	 * 
	 * 参数说明：
	 * 		arr 排序的原始数组
	 * 		left 左边有序序列的初始索引
	 * 		mid 中间索引
	 * 		right 右边索引
	 * 		temp 做中转的数组
	 * 
	 * */
	public static void merge(int[] arr,int left,int mid,int right,int[] temp){
		
		int i = left; // 初始化i，左边有序序列的初始索引
		int j = mid+1; //初始化j，右边有序序列的初始索引
		int t = 0; // 指向temp数组的当前索引
		
		//(一)
		// 先把左右两边(有序)的数据按照规则填充到temp数组中
		// 直到左右两边的有序序列，有一边处理完毕为止
		while(i<=mid && j<=right){ // 继续
			// 如果左边的有序序列的当前元素，小于等于右边的有序序列的当前元素
			// 即将左边的当前元素，填充到temp数组中
			// 然后 t++，i++
			if(arr[i]<arr[j]){
				temp[t] = arr[i];
				t += 1;
				i += 1;
			}else{ // 反之，将右边有序序列的当前元素，填充到temp数组中
				temp[t] = arr[j];
				t += 1;
				j += 1;
			}
		}
		
		//(二)
		//把有剩余数据的一边一次全部填充到temp中
		while(i<=mid){  // 左边的有序序列还有剩余的元素，就全部填充到temp
			temp[t] = arr[i];
			t += 1;
			i += 1;
		}
		
		while(j<=right){
			temp[t] = arr[j];
			t +=1;
			j +=1;
		}
		
		//(三)
		//将temp数组的元素拷贝到arr
		//注意，并不是每次都拷贝所有
		t = 0;
		int tempLeft = left;
		
		// 打印合并的顺序
		System.out.println("tempLeft="+tempLeft + "right=" + right);
		
		while(tempLeft <= right){
			arr[tempLeft] = temp[t];
			t += 1;
			tempLeft += 1;
		}
		
		
		
	}
	
	
	
}
	