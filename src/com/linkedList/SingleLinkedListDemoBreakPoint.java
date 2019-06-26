package com.linkedList;

import javax.xml.transform.Templates;

public class SingleLinkedListDemoBreakPoint {
	
	public static void main(String[] args) {
		
		// 创建节点
		HeroNode heroNode1 = new HeroNode(1, "1", "1");
		HeroNode heroNode2 = new HeroNode(2, "2", "2");
		HeroNode heroNode3 = new HeroNode(3, "3", "3");
		HeroNode heroNode4 = new HeroNode(4, "4", "4");
		
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		singleLinkedList.add(heroNode1);
		System.out.println("========================================第一个节点添加完毕================================================");
		singleLinkedList.add(heroNode2);
		System.out.println("========================================第一个节点添加完毕================================================");
		singleLinkedList.add(heroNode3);
		System.out.println("========================================第一个节点添加完毕================================================");
		singleLinkedList.add(heroNode4);
		
		singleLinkedList.list();
		
	}
	
}

// 单链表的类
class SingleLinkedListBreakPoint{
	
	// 初始化一个头节点，头节点不动，不存放具体的数据
	private HeroNode head = new HeroNode(0, "", "");
	
	// 添加到节点的单向链表中去->
	// 思路，当不考虑编号顺序的时候
			// 1.找到当前链表的最后节点
			// 2.将最后这个节点next指向 新的节点
	public void add(HeroNode heroNode){
		// 由于head节点不能动，因此我们需要一个辅助遍历temp
		HeroNode temp = head;
		
		// 输出头节点的信息
		System.out.println("头节点的信息："+head);
		System.out.println("头节点的下一个节点："+head.next);
		System.out.println("temp节点："+temp);
		System.out.println("temp节点的下一个节点："+temp.next);
		// 通过遍历来找到链表的最后一个元素
		while(true){
			// 如果找到的最后一个元素为空
			if(temp.next == null){
				break;
			}
			// 完成添加链表的操作之后，链表继续往后移动
			temp = temp.next;
			// 这里输出添加完之后temp节点的详细信息
			System.out.println("当前的temp临时节点："+temp);
			System.out.println("当前的temp节点的下一个节点："+temp.next);
		}
		// 当退出while循环的时候，这里退出则代表已经到了链表的末尾，则可以开始添加操作了
		temp.next = heroNode;
		System.out.println("当前的temp临时节点："+temp);
		System.out.println("当前的temp节点的下一个节点："+temp.next);
	}
	
	// 单链表的遍历操作
	public void list(){
		HeroNode temp = head;
		// 先判空
		if (temp.next == null) {
			System.out.println("当前链表为空");
			return;
		}
		// 循环查找元素
		while(true){
			if(temp.next == null){
				break;
			}
			// 输出节点的信息
			System.out.println(temp.next);
			temp = temp.next;
		}
	}

	@Override
	public String toString() {
		return "SingleLinkedList [head=" + head + "]";
	}
	
	
	
	
}



// 节点类
class HeroNodeBreakPoint{
	public int no;
	public String name;
	public String nickName;
	public HeroNode next;
	// 申明构造方法
	public HeroNodeBreakPoint(int no,String name,String nickname){
		this.no = no;
		this.name = name;
		this.nickName = nickname;
	}
	// 重写toString方法
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickName=" + nickName + ", next=" + next + "]";
	}
	
}