package com.hashTable;

import java.util.Scanner;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年8月27日 下午10:46:40	
*/
public class HashTableDemo {
	
	public static void main(String[] args) {
		
		// 实例化哈希表
		HashTable hashTable = new HashTable(7);
		
		// 写一个简单的菜但
		String key = "";
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.println("add: 添加雇员");
			System.out.println("find: 查找雇员");
			System.out.println("list: 显示雇员");
			System.out.println("exit: 退出系统");
			
			key = scanner.next();
			switch (key) {
			case "add":
				System.out.println("输入id");
				int id = scanner.nextInt();
				System.out.println("输入名字");
				String name = scanner.next();
				// 创建雇员
				Emp emp = new Emp(id,name);
				hashTable.add(emp);
				break;
			case "find":
				System.out.println("请输入要查找的id");
				id = scanner.nextInt();
				hashTable.findEmpById(id);
				break;
			case "list":
				hashTable.list();
				break;
			case "exit":
				scanner.close();
				System.exit(0);
			default:
				break;
			}
			
		}
		
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
		// 这里如果不写的话，添加雇员的方法时 会抛异常,因为在创建的数组中，初始化为null，将员工类Emp链在其后面显然不合理
		// 需要初始化链表
		for(int i=0;i<size;i++){
			empLinkedListArray[i] = new EmpLinkedList();  // 初始化的时候将其引用为链表类型
		}
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
			empLinkedListArray[i].list(i);
		}
	}
	
	//虽然在链表中写的查找雇员的方法，但是调用还是在散列表中
	public void findEmpById(int id){
		// 使用散列函数确定到哪条链表中查找
		int empLinkedListNO = hashFun(id);
		Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
		if(emp != null){
			System.out.printf("在第%d条链表中找到雇员id = %d\n",empLinkedListNO,id);
		}else{
			System.out.println("在哈希表中，没有找到雇员");
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
	public void list(int no){
		if(head == null){ // 说明链表为空
			System.out.println("第"+no+"条链表为空");
			return;
		}
		System.out.print("第"+no+"链表的信息为");
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
	
	
	// 根据id查找雇员
	// 如果查找到，就返回Emp，如果没有找到，就返回null
	public Emp findEmpById(int id){
		// 判断链表是否为空
		if(head == null){
			System.out.println("链表为空");
			return null;
		}
		// 辅助指针
		Emp curEmp = head;
		while(true){
			if(curEmp.id == id){  // 找到
				break;  // 这时curEmp就指向要查找的雇员
			}
			// 退出
			if(curEmp.next == null){  // 说明遍历当前链表没有找到该雇员
				curEmp = null;
				break;
			}
			curEmp = curEmp.next;  //  向后移动
		}
		
		return curEmp;
		
	}
	
	
}





	