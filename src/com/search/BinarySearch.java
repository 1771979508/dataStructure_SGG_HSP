package com.search;

import java.util.ArrayList;
import java.util.List;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年8月22日 下午11:12:47	
*/

/*
 * 	二分查找
 * 
 * */
public class BinarySearch {
	
	public static void main(String[] args) {
		
		int arr[] = {1,8,10,89,1000,1000,1000,1000,1234};
//		int resIndex = binarySearch(arr, 0, arr.length-1, -1);
//		System.out.println("找到的resIndex值为："+resIndex);
		
		
		// 优化后的统计多少个值
		List<Integer> resIndexList = binarySearchOptimize(arr, 0, arr.length-1, 1000);
		System.out.println("找到的resIndexList值为："+resIndexList);
	}
	
	//二分算法
	/**
	 * 
	 * @param arr  数组
	 * @param left 左边的索引
	 * @param right 右边的索引
	 * @param findVal 要查找的值
	 * @return  如果找到就返回下标，如果没有找到，就返回-1
	 */
	public static int binarySearch(int[] arr,int left,int right,int findVal){
		
		// 判断出口 - 如果位置成为left在right的右边即
		if(left > right){
			return -1;
		}
		
		int mid = (left+right)/2;
		int midVal = arr[mid];
		
		if(findVal > midVal){  // 向右递归
			return binarySearch(arr, mid+1, right, findVal);
		}else if(findVal < midVal){  // 向左递归
			return binarySearch(arr, left, mid-1, findVal);
		}else {
			return mid;
		}
		
	}
	
	
	/*
	 * 课后思考题：在一个有序数组中{1,8,10,89,1000,1000,1234}
	 * 		有多个相同的数值时，如何将所有的数值都查找到，比如这里的1000	
	 * 
	 * 思路分析：
	 * 		1.在找到mid索引值时，不要马上返回
	 * 		2.向mid 索引值的左边扫描，将所有满足1000的元素下标，加入到ArrayList集合中
	 * 		3.向mid 索引的的右边扫描，将所有满足1000的元素下标，加入到ArrayList集合中
	 * 		4.将ArrayList集合返回		
	 * 
	 * */
	
	/**
	 * 
	 * @param arr
	 * @param left
	 * @param right
	 * @param findVal
	 * @return
	 */
	public static ArrayList<Integer> binarySearchOptimize(int[] arr,int left,int right,int findVal){
		
		// 判断出口 - 如果位置成为left在right的右边即
		if(left > right){
			return new ArrayList<Integer>();
		}
		
		int mid = (left+right)/2;
		int midVal = arr[mid];
		
		if(findVal > midVal){  // 向右递归
			return binarySearchOptimize(arr, mid+1, right, findVal);
		}else if(findVal < midVal){  // 向左递归
			return binarySearchOptimize(arr, left, mid-1, findVal);
		}else {
			// 找到中间值的时候进行统计重复数值的操作
			ArrayList<Integer> resIndexList = new ArrayList<Integer>();
			// 向mid索引值的左边扫描，将所有满足1000，的下标元素，加入到集合ArrayList中
			int temp = mid - 1;
			while(true){
				if(temp < 0 || arr[temp] != findVal){  // 退出
					break;
				}
				// 否则，就temp 放入到resIndexList
				resIndexList.add(temp);
				temp -= 1;  // temp左移
			}
			
			resIndexList.add(mid);  // 这个代码是为鸡毛 => 这里mid刚好是找到的findVal的值，也添加进集合中再添加右边的值
			
			// 向mid索引值的右边扫描，将所有满足1000，的元素下标，加入到集合ArrayList中
			temp = mid + 1;
			while(true){
				if(temp > arr.length-1 || arr[temp] != findVal){
					break;
				}
				// 否则就将temp放入到resIndexList中
				resIndexList.add(temp);
				temp += 1;
			}
			
			return resIndexList;
			
		}
		
		
	}
	
	
	
	
	
	
}
	