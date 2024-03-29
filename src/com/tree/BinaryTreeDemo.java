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

public class BinaryTreeDemo {
	
	public static void main(String[] args) {
		
		BinaryTree binaryTree = new BinaryTree();
		
		HeroNode root = new HeroNode(1,"宋江");
		HeroNode node2 = new HeroNode(2,"吴用");
		HeroNode node3 = new HeroNode(3,"卢俊义");
		HeroNode node4 = new HeroNode(4,"林冲");
		
		// 练习题目
		HeroNode node5 = new HeroNode(5,"关胜");
		
		
		/* -- 基本测试
		// 说明，我们先手动创建该二叉树，后面我们学递归的方式创建二叉树
		root.setLeft(node2);
		root.setRight(node3);
		node3.setRight(node4);
		binaryTree.setRoot(root);
		
		// 测试
		System.out.println("前序遍历");  // 1，2，3，4
		binaryTree.preOrder();
		
		System.out.println("中序遍历");
		binaryTree.fixOrder();
		
		System.out.println("后续遍历");
		binaryTree.postOrder();
		*/
		
		
		// 练习题解答
		root.setLeft(node2);
		root.setRight(node3);
		node3.setRight(node4);
		node3.setLeft(node5);
		binaryTree.setRoot(root);
		
		System.out.println("前序遍历");  // 1，2，3，5，4
		binaryTree.preOrder();
		
		System.out.println("中序遍历"); // 2，1，5，3，4
		binaryTree.fixOrder();
		
		System.out.println("后续遍历"); // 2，5，4，3，1
		binaryTree.postOrder();
		
		
		
	}
	 
}


// 定义一颗二叉树
class BinaryTree{
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




	