package com.sort;

import java.util.Arrays;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年8月21日 下午10:28:30	
*/
public class RadixSort {
	
	public static void main(String[] args) {
		
		int arr[] = {53,3,542,748,14,214};
		//radixSort(arr);
		radisSortOptimize(arr);
		
	}
	
	// 优化基数排序 - 更改为动态的(不是死值)
	public static void radisSortOptimize(int[] arr){
		// 第1轮(针对每个元素的各位进行排序处理)
		
		// 定义一个二维数组，表示10个桶，每个桶就是一个一维数组
		// 说明
		// 1.二维数组包含10个一维数组
		// 2.为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为arr.length
		// 3.明确，基数排序是使用空间换时间的经典算法
		int[][] bucket = new int[10][arr.length];
		
		// 为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入的数据个数
		// 可以这样理解
		// 比如：bucketElementCount[0]，记录的就是 bucket[0]桶的放入数据个数
		int[] bucketElementCount = new int[10];
		
		
		// 找到数组中的最大值
		// 先假定一个最大值
		int max = arr[0];
		for(int i=1;i<arr.length;i++){
			if(max < arr[i]){
				max = arr[i];
			}
		}
		// 查找最大长度
		int maxLength = (max+"").length();
		
		for(int i=0,n=1;i<maxLength;i++,n *= 10){
			// 第一轮(针对每个元素的各位进行排序处理)
			for(int j=0;j<arr.length;j++){
				// 取出每个元素的个位值
				int digitOfElement = arr[j]/n%10;
				// 放入到对应的桶中
				bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];
				bucketElementCount[digitOfElement]++;
			}
			
			// 按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
			int index = 0;
			// 遍历每一桶，并将桶中数据，放入到原数组中
			for(int k=0;k<bucketElementCount.length;k++){
				// 如果桶中，有数据，我们才能放到原数组中
				if(bucketElementCount[k] != 0){
					//循环该桶即第k个桶(即第k个一维数组)，放入
					for(int l=0;l<bucketElementCount[k];l++){
						// 取出元素放入到arr
						arr[index++] = bucket[k][l];
					}
				}
				
				// 第1轮处理后，需要将每个bucketElementCounts[k] = 0 !!!
				bucketElementCount[k] = 0;
				
			}
			System.out.println("第"+(i+1)+"轮，对个位的排序处理 arr="+Arrays.toString(arr));
		}
		
	}
	
	
	// 基数排序方法
	/*
	public static void radixSort(int[] arr){
		// 第1轮(针对每个元素的各位进行排序处理)
		
		// 定义一个二维数组，表示10个桶，每个桶就是一个一维数组
		// 说明
		// 1.二维数组包含10个一维数组
		// 2.为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为arr.length
		// 3.明确，基数排序是使用空间换时间的经典算法
		int[][] bucket = new int[10][arr.length];
		
		// 为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入的数据个数
		// 可以这样理解
		// 比如：bucketElementCount[0]，记录的就是 bucket[0]桶的放入数据个数
		int[] bucketElementCount = new int[10];
		
		// 第一轮(针对每个元素的各位进行排序处理)
		for(int j=0;j<arr.length;j++){
			// 取出每个元素的个位值
			int digitOfElement = arr[j]%10;
			// 放入到对应的桶中
			bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];
			bucketElementCount[digitOfElement]++;
		}
		
		// 按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
		int index = 0;
		// 遍历每一桶，并将桶中数据，放入到原数组中
		for(int k=0;k<bucketElementCount.length;k++){
			// 如果桶中，有数据，我们才能放到原数组中
			if(bucketElementCount[k] != 0){
				//循环该桶即第k个桶(即第k个一维数组)，放入
				for(int l=0;l<bucketElementCount[k];l++){
					// 取出元素放入到arr
					arr[index++] = bucket[k][l];
				}
			}
			
			// 第1轮处理后，需要将每个bucketElementCounts[k] = 0 !!!
			bucketElementCount[k] = 0;
			
		}
		System.out.println("第1轮，对个位的排序处理 arr="+Arrays.toString(arr));
		
		// ================================================
		// 第2轮(针对每个元素的十位进行排序)
		for(int j=0;j<arr.length;j++){
			// 取出每个元素的个位值
			int digitOfElement = arr[j]/10%10;  // 例子：748/10=>74%10=>4
			// 放入到对应的桶中
			bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];
			bucketElementCount[digitOfElement]++;
		}
		
		// 按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
		index = 0;
		// 遍历每一桶，并将桶中数据，放入到原数组中
		for(int k=0;k<bucketElementCount.length;k++){
			// 如果桶中，有数据，我们才能放到原数组中
			if(bucketElementCount[k] != 0){
				//循环该桶即第k个桶(即第k个一维数组)，放入
				for(int l=0;l<bucketElementCount[k];l++){
					// 取出元素放入到arr
					arr[index++] = bucket[k][l];
				}
			}
			
			// 第2轮处理后，需要将每个bucketElementCounts[k] = 0 !!!
			bucketElementCount[k] = 0;
			
		}
		System.out.println("第2轮，对十位的排序处理 arr="+Arrays.toString(arr));
		
		// ================================================
		// 第3轮(针对每个元素的百位进行排序)
		for(int j=0;j<arr.length;j++){
			// 取出每个元素的个位值
			int digitOfElement = arr[j]/100%10;  // 例子：748/10=>74%10=>4
			// 放入到对应的桶中
			bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];
			bucketElementCount[digitOfElement]++;
		}
		
		// 按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
		index = 0;
		// 遍历每一桶，并将桶中数据，放入到原数组中
		for(int k=0;k<bucketElementCount.length;k++){
			// 如果桶中，有数据，我们才能放到原数组中
			if(bucketElementCount[k] != 0){
				//循环该桶即第k个桶(即第k个一维数组)，放入
				for(int l=0;l<bucketElementCount[k];l++){
					// 取出元素放入到arr
					arr[index++] = bucket[k][l];
				}
			}
			
			// 第3轮处理后，需要将每个bucketElementCounts[k] = 0 !!!
			bucketElementCount[k] = 0;
			
		}
		System.out.println("第3轮，对百位的排序处理 arr="+Arrays.toString(arr));
		
		
	}
	*/
	
}
	