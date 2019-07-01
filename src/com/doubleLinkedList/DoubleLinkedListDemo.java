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
		
		System.out.println("双向链表的测试：");
		// 创建节点
		DoubleHeroNode doubleHeroNode1 = new DoubleHeroNode(1,"1","1");
		DoubleHeroNode doubleHeroNode2 = new DoubleHeroNode(2,"2","2");
		DoubleHeroNode doubleHeroNode3 = new DoubleHeroNode(3,"3","3");
		DoubleHeroNode doubleHeroNode4 = new DoubleHeroNode(4,"4","4");
		// 创建双向链表
		DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
		// 双向链表的添加方法
		doubleLinkedList.add(doubleHeroNode1);
		doubleLinkedList.add(doubleHeroNode2);
		doubleLinkedList.add(doubleHeroNode3);
		doubleLinkedList.add(doubleHeroNode4);
		// 输出列表
		doubleLinkedList.list();
		
	}
	
}

// 创建一个双向链表的类
class DoubleLinkedList{
	
	// 先初始化一个头节点，头节点不要动，不存放具体的数据
	private DoubleHeroNode head = new DoubleHeroNode(0, "", "");
	
	public DoubleHeroNode getHead(){
		return head;
	}
	
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
	
	// 修改双向链表节点的信息，根据no编号来改，即no编号不能改
		// 说明
		// 1.根据newHeroNode 的 no 来修改即可
	public void updateNode(DoubleHeroNode newHeroNode){
		// 判断是否为空
		if(head.next == null){
			System.out.println("链表为空~");
			return;
		}
		// 找到需要修改的节点，根据no编号
		// 定义一个辅助变量
		DoubleHeroNode temp = head.next;
		boolean flag = false; // 表示是否找到该节点
		while(true){
			if(temp == null){
				break;  // 已经遍历完链表
			}
			if(temp.no == newHeroNode.no){
				// 表明已经找到了
				flag = true;
				break;
			}
			// 继续往下走
			temp = temp.next;
		}
		// 根据flag 判断是否找到要修改的节点
		if(flag){
			temp.name = newHeroNode.name;
			temp.nickName = newHeroNode.nickName;
		}else{  // 没有找到
			System.out.printf("没有找到 编号 %d 的节点，不能修改\n",newHeroNode.no);
		}
	}
	
	// 双向链表的删除节点
	// 删除节点
		// 思路：
		// 	1.因为是双向链表，因此，我们可以实现自我删除某个节点
		//	2.直接找到要删除的这个节点，比如temp
		//  3.temp.pre.next=temp.next
		//  4.temp.next.pre=temp.pre
	public void del(int no){
		// 判断是否为空节点
		if(head.next == null){
			System.out.println("链表为空");
			return;
		}
		DoubleHeroNode temp = head.next;
		boolean flag = false;	// 标志是否找到待删除节点的标志
		// 这里进行while循环，将temp向后移动用来寻找合适的节点
		while(true){
			if(temp == null){
				System.out.println("已经到链表的最后了");
				break;
			}
			if(temp.no == no){ // 表明找到了该节点
				flag = true;
				break;
			}
			temp = temp.next;
		}
		// 这里进行一下判断
		if(flag){ //如果找到了，则进行删除操作 
			//temp.next = temp.next.next;
			temp.pre.next = temp.next;
			// 空节点是没有前后指针的，否则会报空指针异常
			// 所以这里执行的话需要判断是否是最后一个节点  temp.next.pre 会抛空指针异常
			if(temp.next != null){
				temp.next.pre = temp.pre;
			}
		}else{
			System.out.printf("要删除的%d 节点不存在 \n",no);
		}
	}
	
	
	/*
	 * Practice:双向链表的第二种添加方式，按照编号顺序 [示意图]按照单链表的顺序添加，稍作修改即可
	 * 
	 * */
	
	
	
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




	