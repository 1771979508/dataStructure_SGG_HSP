package com.linkedList;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年6月17日 下午11:00:28	
*/


public class SingleLinkedListAddByOrderDemo {
	
	public static void main(String[] args) {
		
		// 创建节点
		HeroNodeAddByOrder heroNode1 = new HeroNodeAddByOrder(1, "1", "1");
		HeroNodeAddByOrder heroNode2 = new HeroNodeAddByOrder(2, "2", "2");
		HeroNodeAddByOrder heroNode3 = new HeroNodeAddByOrder(3, "3", "3");
		HeroNodeAddByOrder heroNode4 = new HeroNodeAddByOrder(4, "4", "4");
		
		SingleLinkedListAddByOrder singleLinkedList = new SingleLinkedListAddByOrder();
		singleLinkedList.addByOrder(heroNode1);
		singleLinkedList.addByOrder(heroNode2);
		singleLinkedList.addByOrder(heroNode3);
		singleLinkedList.addByOrder(heroNode4);
		
		singleLinkedList.list();
		
	}
	
}

//2.创建单链表的类SingleLinkedList 管理我们的英雄
class SingleLinkedListAddByOrder{
	// 先初始化一个头节点，头节点不动，不存放具体的数据
	private HeroNodeAddByOrder headNode = new HeroNodeAddByOrder(0, "", "");
	
	/*
	 * 		第二种方式添加节点：在添加英雄的时候，根据排名将英雄插入到指定位置(如果有这个排名，添加则失败，并给出提示)
	 * 
	 * 		方式：
	 * 			1.首先找到新节点的位置，是通过辅助变量(指针)，通过遍历来搞定
	 * 			2.将新的节点.next = temp.next
	 * 			3.将temp.next=新的节点
	 * 
	 * */
	public void addByOrder(HeroNodeAddByOrder heroNode){
		
		 // 因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
		
		// 【因为单链表，所以我们找的temp 是位于 添加位置的前一个节点，否则插入不了】
		HeroNodeAddByOrder temp = headNode;
		// 添加一个标志位，用来表示是否找到合适的位置
		boolean flag = false;
		// 开始遍历链表
		while(true){
			
			// 有一个问题，如果将标志的变量放到循环里面会怎样？
			
			
			// 判断temp的位置
			if(temp.next == null){ // 如果链表位置在最后面一个
				break;
			}
			if(temp.next.no > heroNode.no){  // 如果临时节点的下一个节点的位置 在 要插入的节点的   后面，则表明要插入的节点刚好处于 临时节点和临时节点的下一个节点的中间
				break;
			}else if(temp.next.no == heroNode.no){  // 添加编号已经存在
				flag = true;
				break;
			}
			// 节点向后移动
			temp = temp.next;
		}
		
		// 这里有一个细节的处理：刚开始的头节点的下一个节点是null，这里将添加节点的动作放到循环外面是为了让它刚好能够将头节点的后面一个节点添加出来
		if(flag){
			System.out.printf("准备插入的英雄的编号 %d 已经存在了，不能加入\n",heroNode.no);
		}else{
			// 插入到链表中，temp的后面
			heroNode.next = temp.next;
			temp.next = heroNode;
		}
		
	}
	
	// 遍历链表
	public void list(){
		// 判断链表是否为空
		if(headNode.next == null){
			System.out.println("链表为空");
			return;
		}
		// 因为头节点，不能动，因此我们需要一个辅助变量来遍历
		HeroNodeAddByOrder temp = headNode.next;
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
class HeroNodeAddByOrder{
	
	public int no;
	public String name;
	public String nickName;  // 昵称
	public HeroNodeAddByOrder next;
	
	public HeroNodeAddByOrder(int no,String name,String nickName){
		this.no = no;
		this.name = name;
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "HeroNodeAddByOrder [no=" + no + ", name=" + name + ", nickName=" + nickName + "]";
	}
	
}
	