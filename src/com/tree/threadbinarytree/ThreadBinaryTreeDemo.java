package com.tree.threadbinarytree;


/**
*	@author 作者 Joker青
*	@version 创建时间：2019年9月19日 下午10:13:26	
*/
public class ThreadBinaryTreeDemo {
	
	
	
}


//ThreadedBinaryTree 实现了线索化功能的二叉树
class BinaryTree{
	private HeroNode root;
	
	// 为了实现线索化，需要创建给指向节点当前节点的前驱节点的指针
	// 在递归进行线索化时，pre总是保留前一个节点
	private HeroNode pre = null;
	
	public void setRoot(HeroNode root){
		this.root = root;
	}
	
	
	// 编写对二叉树进行中序遍历线索化的方法
	/**
	 * 
	 * @param node 就是当前需要线索化的节点
	 */
	public void threadedNodes(HeroNode node){
		// 如果node==null，不能线索化
		if(node == null){
			return;
		}
		
		//(一)先线索化左子树
		threadedNodes(node.getLeft());
		//(二)先线索化当前节点
		// 处理当前节点的前驱节点
		// 以8节点来理解
		// 8节点的.left = null,8节点的leftType = 1
		if(node.getLeft() == null){
			// 让当前节点的左指针指向前驱节点
			node.setLeft(pre);
			// 修改当前节点的左指针类型，类型为前驱节点
			node.setLeftType(1);
		}
		//处理后继节点
		if(pre != null && pre.getRight() == null){
			// 让前驱节点的右指针指向当前节点
			pre.setRight(node);
			// 修改前驱节点的右指针类型
			pre.setRightType(1);
		}
		// ！！！ important 每处理一个节点后，让当前节点是下一个节点的前驱节点
		// 为上面的 [处理后继节点铺路]
		pre = node;
		
		//(三)先线索化右子树
		threadedNodes(node.getRight());
		
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



//先创建HeroNode节点
class HeroNode{
	private int no;
	private String name;
	private HeroNode left; // 默认为null
	private HeroNode right; // 默认为null
	
	// 说明
	// 1.如果leftType == 0 表示指向的是左子树，如果是1则表示前驱节点
	// 2.如果rightType == 0 表示指向的是右子数，如果是1则表示后继节点
	private int leftType;
	private int rightType;
	
	public int getLeftType() {
		return leftType;
	}
	public void setLeftType(int leftType) {
		this.leftType = leftType;
	}
	public int getRightType() {
		return rightType;
	}
	public void setRightType(int rightType) {
		this.rightType = rightType;
	}
	
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

