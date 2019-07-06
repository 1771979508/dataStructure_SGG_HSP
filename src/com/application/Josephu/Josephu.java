package com.application.Josephu;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年7月4日 下午10:12:55	
*/
public class Josephu {
	
	public static void main(String[] args) {
		
		CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
		// 向环形链表中添加节点
		circleSingleLinkedList.add(5);
		
		// 显示节点
		circleSingleLinkedList.showBoy();
	}
	
}

// 创建一个环形的单向链表
class CircleSingleLinkedList{
	// 创建一个first节点，当前没有编号
	private Boy first = null;
	// 添加小孩节点，构建成一个环形的链表
	public void add(int nums){
		// nums 做一个数据校验
		if(nums < 1){
			System.out.println("nums的值不正确");
			return;
		}
		Boy curBoy = null;  // 辅助指针，帮助构建环形链表
		// 使用for循环来创建我们的环形链表
		for(int i=1;i<=nums;i++){
			// 根据编号，创建小孩节点
			Boy boy = new Boy(i);
			// 如果是第一个小孩
			if(i == 1){
				first = boy;
				first.setNext(first); // 形成环
				curBoy = first;  // 让curBoy指向第一个小孩
			}else{ 
				curBoy.setNext(boy);  // 将循环的boy节点放到当前链表的后面
				boy.setNext(first);  // 将链表设置成环形,指向头节点
				curBoy = boy;		// 将原来的当前节点往后移动，将新链接进来的节点设置成当前节点
			}
		}
	}
	
	// 遍历当前的环形链表
	public void showBoy(){
		// 判断链表是否为空
		if(first == null){
			System.out.println("没有任何小孩");
			return;
		}
		// 因为first不能动，因此我们仍然使用一个辅助指针完成遍历
		Boy curBoy = first;
		while(true){
			System.out.printf("小孩的编号 %d \n",curBoy.getNo());
			if(curBoy.getNext() == first){  // 说明已经遍历完毕
				break;
			}
			curBoy = curBoy.getNext();  // 将curBoy往后移动
		}
	}
	
	
}

// 创建一个Boy类，表示一个节点
class Boy{
	private int no;  // 编号
	private Boy next;  // 指向下一个节点
	public Boy(int no){
		this.no = no;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Boy getNext() {
		return next;
	}
	public void setNext(Boy next) {
		this.next = next;
	}
	
}