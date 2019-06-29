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
		
		System.out.println("原本的链表是：");
		singleLinkedList.list();
		
		// 准备测试当前节点中的个数
		//System.out.println("有效节点的个数为："+getCount(singleLinkedList.getHeadNode()));
		
		// 测试一下看看是否得到了倒数第k个节点
		//HeroNode res = findLastIndexNode(singleLinkedList.getHeadNode(), 4);
		//System.out.println("倒数第k个节点是："+res);
		
		// 测试一下链表是否能够成功的反转
		System.out.println("反转后的链表是：");
		reverseList(singleLinkedList.getHeadNode());
		singleLinkedList.list();
		
	}
	
	// 使用头插法来达到链表的反转
	public static void reverseList(HeroNode head){  // 传入一个头节点
		// 如果当前链表为空，或者只有一个节点，无需反转，直接返回
		if(head.next == null || head.next.next == null){
			return; 
		}
		// 下面开始进行链表的反转
			// 定义一个辅助的指针(变量)，帮助我们遍历原来的链表
		HeroNode cur = head.next;
			// 定义cur的下一个节点next
		HeroNode next = null;
			// 定义一个新节点用来接收原来链表的节点
		HeroNode reverseNode = new HeroNode(0, "", "");
		// 开始遍历链表
		while(cur != null){
			// 先把下当前节点(cur)一个节点放给next，防止丢失
			next = cur.next;
			// 将原本在cur链表的节点放到新链表reverseNode的最前端
			cur.next = reverseNode.next;
			// 然后将当前节点放到新链表中去
			reverseNode.next = cur;
			// 将原来链表的当前节点cur向后移动
			cur = next;
		}
		// 最后一步将新的链表reverseList与head关联起来，达到链表的反转
		head.next = reverseNode.next;
	}
	
	
	// 获取有效的节点数(单链表) ->  如果是带头节点的链表，不统计头节点
	public static int getCount(HeroNode head){// 传一个头节点
		// 默认整型为0
		int length = 0;
		if(head.next == null){
			System.out.println("该链表为空链表");
			return 0;
		}
		// 定义一个辅助变量
		HeroNode cur = head.next;
		// 循环获取节点及相关数据
		while(cur != null){
			length++;
			cur = cur.next;
		}
		return length;  
	}
	
	// 查找单链表中的倒数第k个节点
	// 思路
		// 1.编写一个方法，接收head节点，同时接收一个index
		// 2.index表示是倒数第index个节点
		// 3.先把链表从头到尾遍历，得到链表的总长度 getLength
		// 4.得到size后，我们从链表的第一个开始遍历(size-index)个，就可以得到
		// 5.如果找到了，则返回该节点，否则返回null
	public static HeroNode findLastIndexNode(HeroNode heroNode,int index){
		// 判断如果链表为空，返回null
		if(heroNode.next == null){
			return null;  // 没有找到
		}
		// 第一个遍历得到链表的长度(节点个数)
		int size = getCount(heroNode);
		
		// 第二次遍历 size-index 位置，就是我们倒数的第k个节点
		// 先做一个index的校验
		if(index<=0 || index>size){
			return null;
		}
		// 开始遍历
		HeroNode cur = heroNode.next;
		for(int i=0;i<size-index;i++){
			cur = cur.next;
		}
		return cur;
	}
	
	

}

//2.创建单链表的类SingleLinkedList 管理我们的英雄
class SingleLinkedList{
	// 先初始化一个头节点，头节点不动，不存放具体的数据
	private HeroNode headNode = new HeroNode(0, "", "");
	

	public HeroNode getHeadNode() {
		return headNode;
	}

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
	