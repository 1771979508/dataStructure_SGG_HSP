package com.heap;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年10月8日 下午10:43:11	
*/
public class HeapSort {
	
	public static void main(String[] args) {
		
		// 要求将数组进行升序排序
//		int arr[] = {4,6,8,5,9};
		
		// 测试大数据量下的排序情况
		int[] arr = new int[8000000];
		for(int i=0;i<arr.length;i++){
			arr[i] = (int) (Math.random()*8000000);  // 生成一个[0,8000000]的数据元素
		}
		System.out.println("堆排序前");
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("堆排序的时间是："+date1Str);
		heapSort(arr);
		System.out.println("堆排序后");
		Date date2 = new Date();
		String dateStr2 = simpleDateFormat.format(date2);
		System.out.println("堆排序后的时间为：" + dateStr2);
	}
	
	// 编写一个堆排序的方法
	public static void heapSort(int arr[]){
		int temp = 0;
//		System.out.println("堆排序！！");
		
		// 先进行分步实现
//		adjustHeap(arr,1,arr.length);
//		System.out.println("第一次排序完的结果" + Arrays.toString(arr));
//		adjustHeap(arr,0,arr.length);
//		System.out.println("第二次排序完的结果" + Arrays.toString(arr));
		
		
		// 完成最终版代码
		// 将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
		for(int i=arr.length/2-1;i>=0;i--){
			adjustHeap(arr, i, arr.length);   // 得到的是已经将最大的元素放置到堆顶，即根节点
		}
//		System.out.println(Arrays.toString(arr));
		
		// 2.将堆顶元素与末尾元素交换，将最大元素 “沉” 到数组末端
		// 3.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，【反复执行调整和交换】步骤，直到整个序列有序
		for(int j=arr.length-1;j>0;j--){  // 这里只需要对数组-1个数进行交换即可
			// 把末尾元素存到临时变量中
			temp = arr[j];
			// 把最大元素(根节点)交换到末尾元素
			arr[j] = arr[0];
			// 把temp元素还给第一个元素
			arr[0] = temp;
			
			// 交换完成之后，即剔除了数组末尾的最大元素，继续调整大顶堆、交换
			adjustHeap(arr, 0, j);  // 从上往下走，所以从0开始
			
		}
//		System.out.println(Arrays.toString(arr));
		
	}
	
	// 将一个数组（完全二叉树），调整成一个大顶堆
	/**
	 * 功能：完成将以i对应的非叶子节点的树调整成大顶堆(以i为顶点的子树)   =  其中 i=arr.length/2-1
	 * 举例 int arr[] = {4,6,8,5,9} => i=1=> adjustHeap => 得到{4，9，8，5，6}
	 * 如果我们再次调用 adjust 传入的是 i=0=> 得到{4,9,8,5,6}=>{9,6,8,5,4}
	 * @param arr	待调整的数组
	 * @param i	表示非叶子节点在数组中的索引
	 * @param length	表示对多少个元素进行调整， length 是逐步减少的
	 */
	public static void adjustHeap(int arr[],int i,int length){
		int temp = arr[i]; // 先取出当前元素的值，保存在临时变量中
		// 开始调整
		// 说明
		// 1.k=i*2+1 其中，k为i节点的左子节点
		for(int k=i*2+1;k<length;k=k*2+1){   // k=k*2+1 为啥？
			if(k+1<length && arr[k] < arr[k+1]){
				k++;  // k 指向右子节点
			}
			if(arr[k] > temp){  // 如果子节点大于父节点
				arr[i] = arr[k];  // 把较大的值赋给当前节点
				i = k;  // ！！！important 将i指向k，继续循环比较
			}else{
				break;  // 由于树是从左向右，从上往下依次生成的
			}
		}
		// 当for 循环结束后，我们已经将以i为父节点的树的最大值，放在了最顶部(局部)
		arr[i] = temp;  // 将temp值放到调整后的位置
	}
	
}
	