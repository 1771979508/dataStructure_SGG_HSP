package com.search;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年8月22日 下午10:49:21	
*/
public class SeqSearch {
	
	public static void main(String[] args) {
		
		int arr[] = {1,9,11,-1,34,89}; // 没有顺序的数组
		int index = seqSearch(arr, -11);
		if(index == -1){
			System.out.println("没有找到对应的值");
		}else{
			System.out.println(index);
		}
		
	}
	
	public static int seqSearch(int[] arr,int value){
		// 线性查找是逐一比对，发现有相同值，就返回下标
		for(int i=0;i<arr.length;i++){
			if(arr[i] == value){
				return i;
			}
		}
		return -1;
	}
	
}
	