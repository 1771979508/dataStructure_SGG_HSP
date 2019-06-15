package com.Queue;

import java.util.Scanner;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年6月15日 下午11:42:02	
*/

/*
 * 
 * 		环形的数组队列 - 通过取模运算来保证数组队列的不越界
 * 
 * */

public class CircleArrayCircle {
	
	public static void main(String[] args) {
		
		System.out.println("测试数组环形模拟队列");
		// 首先创建数组的环形队列
		CircleArray circleArray = new CircleArray(4);  // 由于数组空间预留一个空间所以该队列最大有效数据为3
		// 接收数据
		char key = ' ';
		// 创建Scanner板
		Scanner scanner = new Scanner(System.in);
		//创建标志位
		boolean loop = true;
		while(loop){
			System.out.println("s(show)：显示队列");
			System.out.println("e(exit)：退出程序");
			System.out.println("a(add)：  添加一个数据到队列");
			System.out.println("p(pop)：  从队列取出数据");
			System.out.println("h(head)：查看队列头的数据");
			// 将接收到的数据进行拆分
			key = scanner.next().charAt(0);
			switch (key) {
			case 's':
				try{
					circleArray.showQueue();
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'a':
				System.out.println("请输入一个数字");
				// 接收用户输入的数据
				int value = scanner.nextInt();
				try {
					circleArray.addData(value);
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
				break;
			case 'p': // 取数据，这里可能会抛异常所以用try catch 来处理
				try{
					int res = circleArray.popQueue();
					System.out.printf("您获取的数据为%d\n",res);
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
				break;
			case 'h':
				try{
					int head = circleArray.headQueue();
					System.out.printf("您获取的头数据为：%d\n",head);
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
				break;
			case 'e':
				//需要先关闭scanner，否则会报错
				scanner.close();
				loop = false;
				break;
			default:
				break;
			}
		}
		
	}
	
}

class CircleArray{
	
	private int maxSize;  // 表示数组的最大容量
	
	// front 变量的含义做一个调整：front就指向队列的第一个元素，也就是说arr[front]
	// front 的初始值 = 0
	private int front;
	
	// rear 变量的含义做一个调整：rear指向队列的最后一个元素的后一个位置。因为希望空出一个位置用来做调整
	// rear 的初始值 = 0
	private int rear; // 队列尾
	private int[] arr;  // 该数据用于存放数据，模拟队列
	
	public CircleArray(int arrMaxSize){
		maxSize = arrMaxSize;
		arr = new int[maxSize];
	}
	
	// 判断队列是否满 - 已经改造成环形队列了
	public boolean isFull(){
		return (rear+1)%maxSize == front;
	}
	
	// 判断队列是否为空
	public boolean isEmpty(){
		return rear == front;
	}
	
	// 添加数据到队列
	public void addData(int data){
		if(isFull()){
			throw new RuntimeException("队列已满");
		}
		// 直接将数据加入
		arr[rear] = data;
		// 将rear后移，这里必须考虑取模 - 貌似为了不使下标越界
		rear = (rear + 1)%maxSize;
	}
	
	// 取出数据
	public int popQueue(){
		if(isEmpty()){
			throw new RuntimeException("队列为空");
		}
		int res =  arr[front];
		// 后移的时候需要进行取模运算，否则会出现下标越界
		front = (front+1)%maxSize;
		return res;
	}
	
	// 显示队列的所有数据
	public void showQueue(){
		if(isEmpty()) {
			throw new RuntimeException("队列为空");
		}
		for(int i=front;i<front+size();i++){
			System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
		}
	}
	
	// 求得有效数据的方法
	public int size(){
		/*
		 * 举例：
		 * 		front=1
		 * 		rear=2
		 * 		maxSize=3
		 * 		最后有效的值为：1
		 * */
		return (rear-front+maxSize)%maxSize;
	}
	
	// 显示队列的头数据，不是取出数据
	public int headQueue(){
		// 判断
		if(isEmpty()) {
			throw new RuntimeException("队列为空，无相关数据");
		}
		return arr[front];
	}
	
	
	
	
	
	
	
}
	