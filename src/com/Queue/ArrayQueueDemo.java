package com.Queue;

import java.util.Scanner;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年6月15日 下午8:30:54	
*/

/*
 * 	用数组实现队列
 * 
 * */

public class ArrayQueueDemo {
	
	public static void main(String[] args) {
		
		// 测试
		Queue queue = new Queue(3);
		char key = ' ';  // 接收用户输入
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		// 输出一个菜单
		while(loop){
			System.out.println("s(show)：显示队列");
			System.out.println("e(exit)：退出程序");
			System.out.println("a(add)：  添加一个数据到队列");
			System.out.println("p(pop)：  从队列取出数据");
			System.out.println("h(head)：查看队列头的数据");
			key = scanner.next().charAt(0);	
			switch (key) {
			case 's':
				queue.showQueue();
				break;
			case 'a':
				System.out.println("请输入一个数字");
				// 接收用户输入的数据
				int value = scanner.nextInt();
				try {
					queue.addData(value);
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
				break;
			case 'p': // 取数据，这里可能会抛异常所以用try catch 来处理
				try{
					int res = queue.popQueue();
					System.out.printf("您获取的数据为%d\n",res);
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
				break;
			case 'h':
				try{
					int head = queue.headQueue();
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

// 手写一个队列
class Queue{
	// 数组队列的最大容量
	private int maxSize;
	// 队首指针
	private int front;
	// 队尾指针
	private int rear;
	// 用来存放数据的数组
	private int[] arr;
	
	// 创建队列的构造器
	public Queue(int maxLen){
		maxSize = maxLen;
		// 实例化数组
		arr = new int[maxSize];
		rear = -1;  // 指向队列尾部(包含最后一个数据)
		front = -1; // 指向队列头部的前一个位置(不包含第一个数据)
	}
	
	// 判断队列是否满
	public boolean isFull(){
		// 如果队尾等于最大容量值等于数据已经塞满了则返回队列已满
		return rear==maxSize-1;
	}
	
	// 队列是否为空
	public boolean isEmpty(){
		return rear==front;
	}
	
	
	// 添加数据到队列
	public void addData(int data) throws Exception{
//		队列是否已满
		if(isFull()){
			throw new Exception("队列已满");
		}
		arr[++rear] = data;
	}
	

	// 获取队列的数据，出队列
	public int popQueue() throws Exception{  // 还应该判断下标的越界情况的
		// 判断队列是否为空
		if(isEmpty()){
			throw new Exception("队列为空无法获得数据");
		}
		front++;  // 队列的头指针出队列
		return arr[front];
	}
	
	// 显示队列的所有数据
	public void showQueue(){
		//判空
		if(isEmpty()) {
			System.out.println("队列为空");
			return;
		}
		//遍历获取数据
		for(int i=0;i<arr.length;i++){
			System.out.printf("arr[%d]=%d\n",i,arr[i]);
		}
		
	}
	
	// 显示队列的头数据，不是取出数据
	public int headQueue(){
		// 判断
		if(isEmpty()) {
			throw new RuntimeException("队列为空，无相关数据");
		}
		return arr[front + 1];
	}
	
	
}
	