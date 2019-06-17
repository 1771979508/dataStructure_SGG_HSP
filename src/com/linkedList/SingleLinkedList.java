package com.linkedList;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年6月17日 下午11:00:28	
*/


public class SingleLinkedList {
	
	public static void main(String[] args) {
		
		
		
	}
	
}

//2.创建单链表的类


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
	