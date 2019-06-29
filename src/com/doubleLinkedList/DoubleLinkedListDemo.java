package com.doubleLinkedList;


/**
*	@author 作者 Joker青
*	@version 创建时间：2019年6月29日 下午5:22:37	
*/

/*
 * 		双向链表
 * 
 * */
public class DoubleLinkedListDemo {
	
	public static void main(String[] args) {
		
		
		
	}
	
}

// 创建一个双向链表的类
class DoubleLinkedList{
	
	// 先初始化一个头节点，头节点不要动，不存放具体的数据
	private DoubleHeroNode head = new DoubleHeroNode(0, "", "");
	
	
	// 遍历链表
	public void list(){
		// 判断链表是否为空
		if(head.next == null){
			System.out.println("链表为空");
			return;
		}
		// 因为头节点，不能动，因此我们需要一个辅助变量来遍历
		DoubleHeroNode temp = head.next;
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
	
	// 双向链表节点的添加（先默认添加节点到链表的最后）:
		/*
		 	1.先找到双向链表的最后节点
			2.temp.next = newHeroNode
			3.newHeroNode.pre = temp
		*/
	public void add(DoubleHeroNode heroNode){
		// 由于head头节点不能动，因此我们需要一个辅助遍历 temp
		DoubleHeroNode temp = head;
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
		heroNode.pre = temp;
	}
	
	
	
}

// 双向链表的节点
class DoubleHeroNode{
	public int no;
	public String name;
	public String nickName;  // 昵称
	public DoubleHeroNode next; // 下一个节点
	public DoubleHeroNode pre;  // 前一个节点
	
	public DoubleHeroNode(int no,String name,String nickName){
		this.no = no;
		this.name = name;
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "DoubleHeroNode [no=" + no + ", name=" + name + ", nickName=" + nickName + ", next=" + next + ", pre="
				+ pre + "]";
	}

	
}




	