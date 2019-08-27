package com.hashTable;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年8月27日 下午10:46:40	
*/
public class HashTableDemo {
	
	public static void main(String[] args) {
		
		
		
	}
	
}


//=======================第二步：写散列表，里面包含核心散列函数，用来进行数据的查找=========================
class HashTable{
	private EmpLinkedList[] empLinkedListArray;
	private int size; // 表示有多少条链表
	
	// 构造器
	public HashTable(int size){
		this.size = size;
		// 初始化empLinkedListArray
		empLinkedListArray = new EmpLinkedList[size];
		
		// 留下了一个坑？
		
	}
	
	// 添加雇员
	public void add(Emp emp){
		// 根据员工的id，得到该员工应当添加到哪条链表
		int empLinkedListNO = hashFun(emp.id);
		// 将emp 添加到对应的链表中
		empLinkedListArray[empLinkedListNO].add(emp);
	}
	
	// 遍历所有的链表，遍历hashTable
	public void list(){
		for(int i=0;i<size;i++){
			empLinkedListArray[i].list();
		}
	}
	
	// 编写散列函数，使用取模法
	public int hashFun(int id){
		return id%size;
	}
	
	
}



//=======================第一步：先写基本信息类和链表类==========================

// 雇员类
class Emp{
	int id;
	String name;
	Emp next;  // next 默认为null
	public Emp(int id,String name){
		super();
		this.id = id;
		this.name = name;
	}
}


/*
 * 
 * 		操作链表的时候，一个是添加的情况，另一个是遍历的情况
 * 			添加的情况：
 * 					while(true){
 * 						if(当前指针为空){
 * 							则代表已经到链表的最后，break退出当前循环
 *						}
 * 						否则不为空的情况下，链表继续后移
 * 
 * 			遍历的情况：
 * 					if(头指针为空){
 * 						则代表链表为空
 * 					}
 * 
 * */


// 创建EmpLinkedList，表示链表
class EmpLinkedList{
	// 头指针，指向第一个Emp，因此我们这个链表的head，是直接指向第一个Emp
	private Emp head;  // 默认null
	
	// 添加雇员到链表
	// 说明
	// 1.假定，当添加雇员时，id是自增长，即id的分配总是从小到大
	//   因此我们将该雇员直接加入到本链表的最后即可
	public void add(Emp emp){
		// 如果添加的是第一个雇员的话
		if(head == null){
			head = emp;
			return;
		}
		// 如果不是第一个雇员，则使用一个辅助指针，帮助定位到最后
		Emp currentEmp = head;
		while(true){
			if(currentEmp.next == null){  // 说明到链表的最后
				break;
			}
			currentEmp = currentEmp.next;  // 后移
		}
		// 退出时直接将emp 加入链表中
		currentEmp.next = emp;
	}
	
	// 遍历链表的雇员信息
	public void list(){
		if(head == null){ // 说明链表为空
			System.out.println("链表为空");
			return;
		}
		System.out.println("当前链表的信息为");
		Emp currentEmp = head;  // 辅助指针
		while(true){
			System.out.printf("=> id=%d name=%s\t",currentEmp.id,currentEmp.name);
			if(currentEmp.next == null){
				break;
			}
			currentEmp = currentEmp.next;  // 后移，遍历
		}
		System.out.println();
	}
	
	
}





	