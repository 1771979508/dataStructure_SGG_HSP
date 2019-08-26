package com.search;

import java.util.Arrays;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年8月26日 下午10:56:48	
*/
public class FiBonacciSearch {
	
	static int maxSize = 20;
	public static void main(String[] args) {
		
		int[] arr = {1,8,10,89,1000,1234};
		
		System.out.println("index=" + fibSearch(arr, 89));
		
	}
	
	// 因为后面我们mid=low+F(k-1)-1，需要使用到斐波那契数列，因此我们需要先获取到一个斐波那契数列
	// 非递归方法得到一个斐波那契数列
	public static int[] fib(){
		int[] f = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for(int i=2;i<maxSize;i++){
			f[i] = f[i-1] + f[i-2];
		}
		return f;
	}
	
	
	// 编写斐波那契查找算法 
	/**
	 * 
	 * @param a		数组
	 * @param key	我们需要查找的关键码(值)
	 * @return		返回对应的下标，如果没有-1
	 */
	public static int  fibSearch(int[] a,int key){
		int low = 0;
		int high = a.length - 1;
		int k=0; // 表示斐波那契分割数值的下标
		int mid = 0; // 存放mid的值
		int f[] = fib(); // 获得斐波那契数列
		// 获取到斐波那契分割数值的下标
		while(high > f[k] - 1){
			k++;
		}
		// 因为f[k]值可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
		// 不足的部门会使用0填充
		int[] temp = Arrays.copyOf(a,f[k]);
		// 实际上需要使用a数组最后的数值来填充 temp
		// 举例
		// temp = {1,8,10,89,1000,1234,0,0,0} => {1,8,10,89,1000,1234,1234,1234,1234}
		for(int i=high+1;i<temp.length;i++){
			temp[i] = a[high];
		}
		
		
		/*
		 *   【！！！ 这里while循环的时候，向左和向右查找k的取值很懵懂  ！！！】
		 * 
		 * */
		
		// 使用while来循环处理，找到我们的数key
		while(low <= high){  // 只要这个条件满足，就可以找
			mid = low + f[k - 1] - 1;
			if(key < temp[mid]){ // 我们应该继续向数组的前面查找(即左边)
				high = mid - 1;
				// 为什么是k--
				// 说明
				/*
				 * 1.全部元素 = 前面的元素 + 后边元素
				 * 2.f[k] = f[k-1]+f[k-2]
				 * 因为前面有f[k-1]个元素，所以可以继续拆分f[k-1] = f[k-2] + f[k-3]
				 * 即在f[k-1]的前面继续查找k
				 * 即下次循环mid = f[k-1]-1
				 * 
				 * */
				//k-=1;
				k--; // 上面的写法也能走通
			}else if(key > temp[mid]){  // 我们应该继续向数组的后面查找(右边)
				low = mid + 1;
				// 为什么是 k -= 2
				// 说明
				/*
				 * 1.全部元素 = 前面的元素 + 后边元素
				 * 2.f[k] = f[k-1] + f[k-2]
				 * 3.因为后面我们有f[k-2] 所以我们继续拆分 f[k-1] = f[k-3] + f[k-4]
				 * 4.即在f[k-2]的前面进行查找 k-=2
				 * 5.即下次循环mid = f[k-1-2] - 1
				 * 
				 * */
				k -= 2;
			}else{  // 找到了
				// 需要确定返回的是哪个下标
				if(mid <= high){
					return mid;
				}else{
					return high;
				}
			}
		}
		
		
		// 如果经过整个while循环仍然没有找到，则返回-1
		return -1;
		
		
	}
	
	
}
	