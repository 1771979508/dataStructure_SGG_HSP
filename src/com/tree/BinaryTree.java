package com.tree;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年8月31日 下午8:34:50	
*/

/*
 * 
 * 			二叉树的前序、中序、后序遍历	
 * 
 * 
 * */

public class BinaryTree {
	
	public static void main(String[] args) {
		
		
		
	}
	
}


// 定义一颗二叉树
class BTree{
	private HeroNode root;
	
	public void setRoot(HeroNode root){
		this.root = root;
	}
	
	// 前序遍历
	public  void preOrder(){
		if(this.root != null){
			this.root.preOrder();
		}else{
			System.out.println("二叉树为空，无法遍历");
		}
	}
	
	// 中序遍历
	public  void fixOrder(){
		if(this.root != null){
			this.root.infixOrder();
		}else{
			System.out.println("二叉树为空，无法遍历");
		}
	}
	
	// 后续遍历
	public  void postOrder(){
		if(this.root != null){
			this.root.postOrder();
		}else{
			System.out.println("二叉树为空，无法遍历");
		}
	}
	
}



// 先创建HeroNode节点
class HeroNode{
	private int no;
	private String name;
	private HeroNode left; // 默认为null
	private HeroNode right; // 默认为null
	public HeroNode(int no,String name){
		this.name = name;
		this.no = no;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HeroNode getLeft() {
		return left;
	}
	public void setLeft(HeroNode left) {
		this.left = left;
	}
	public HeroNode getRight() {
		return right;
	}
	public void setRight(HeroNode right) {
		this.right = right;
	}
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + "]";
	}
	
	// 编写前序遍历的方法
	public void preOrder(){
		System.out.println(this);  //先输出父节点
		// 递归向左子树前序遍历
		if(this.left != null){
			this.left.preOrder();
		}
		// 递归向右子树前序遍历
		if(this.right != null){
			this.right.preOrder();
		}
	}
	
	// 中序遍历
	public void infixOrder(){
		// 递归向左子树中序遍历
		if(this.left != null){
			this.left.infixOrder();
		}
		// 输出父节点
		System.out.println(this);
		// 递归向右子树中序遍历
		if(this.right != null){
			this.right.infixOrder();
		}
	}
	
	// 后续遍历
	public void postOrder(){
		// 向左子树开始遍历
		if(this.left != null){
			this.left.postOrder();
		}
		// 向右子树开始遍历
		if(this.right != null){
			this.right.postOrder();
		}
		// 输出父节点
		System.out.println(this);
	}
	
	
}




	