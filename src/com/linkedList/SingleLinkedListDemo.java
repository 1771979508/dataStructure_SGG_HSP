package com.linkedList;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年6月17日 下午11:00:28	
*/


public class SingleLinkedListDemo {
	
	public static void main(String[] args) {
		
		// 创建节点
		HeroNode heroNode1 = new HeroNode(1, "1", "1");
		HeroNode heroNode2 = new HeroNode(2, "2", "2");
		HeroNode heroNode3 = new HeroNode(3, "3", "3");
		HeroNode heroNode4 = new HeroNode(4, "4", "4");
		
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		singleLinkedList.add(heroNode1);
		singleLinkedList.add(heroNode2);
		singleLinkedList.add(heroNode3);
		singleLinkedList.add(heroNode4);
		
		singleLinkedList.list();
		
	}
	
}

//2.创建单链表的类SingleLinkedList 管理我们的英雄
class SingleLinkedList{
	// 先初始化一个头节点，头节点不动，不存放具体的数据
	private HeroNode headNode = new HeroNode(0, "", "");
	
	// 添加节点到单项链表->
	// 思路，当不考虑编号顺序的时候
		// 1.找到当前链表的最后节点
		// 2.将最后这个节点的next 指向 新的节点
	public void add(HeroNode heroNode){
		// 由于head头节点不能动，因此我们需要一个辅助遍历 temp
		HeroNode temp = headNode;
		while(true){
			// 如果找到最后一个节点为空，则为链表的最后
			if(temp.next == null){  // 这里为什么又是temp.next了呢？因为这里需要找到下一条是空的节点，这样为了往【当前节点的下一条节点是空的节点添加新的节点】
				break;
			}
			// 如果没有到最后，则将节点指针往后移动
			temp = temp.next;
		}
		// 当退出while循环的时候，temp就指向了链表的最后,此时将新添加进来的节点加入到链表的最后即可
		temp.next = heroNode;
	}
	
	// 遍历链表
	public void list(){
		// 判断链表是否为空
		if(headNode.next == null){
			System.out.println("链表为空");
			return;
		}
		// 因为头节点，不能动，因此我们需要一个辅助变量来遍历
		HeroNode temp = headNode.next;
		while(true){
			// 判断链表是否到最后
			if(temp == null){   //  这里为什么是temp而不是temp.next?因为如果这里是temp.next的话，则会导致一条数据不会被遍历出来，他的下一条数据为空，直接跳出循环
				break;
			}
			// 输出节点信息
			System.out.println(temp);
			// 将temp向后移动，否则永远遍历当前节点，陷入死循环
			temp = temp.next;
		}
	}
	
}


//1. 先定义一个节点类,每个HeroNode对象就是一个节点
class HeroNode{
	
	public int no;
	public String name;
	public String nickName;  // 昵称
	public HeroNode next;
	
	public HeroNode(int no,String name,String nickName){
		this.no = no;
		this.name = name;
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + ", nickName=" + nickName + ", next=" + next + "]";
	}
	
}
	