package com.tree.threadbinarytree;


/**
*	@author 作者 Joker青
*	@version 创建时间：2019年9月19日 下午10:13:26	
*/
public class ThreadBinaryTreeDemo {
	
	public static void main(String[] args) {
		
		// 测试中序线索二叉树的功能
		HeroNode root = new HeroNode(1,"tom");
		HeroNode node2 = new HeroNode(3,"jack");
		HeroNode node3 = new HeroNode(6,"smith");
		HeroNode node4 = new HeroNode(8,"mary");
		HeroNode node5 = new HeroNode(10,"king");
		HeroNode node6 = new HeroNode(14,"dim");
		
		// 二叉树，后面我们要递归创建，现在简单手动创建
		root.setLeft(node2);
		root.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node3.setLeft(node6);
		
		// 测试中序线索化
		ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
		threadedBinaryTree.setRoot(root);
		// 调用重载后的方法
		threadedBinaryTree.threadedNodes();
		
		// 测试：以10号节点测试
		HeroNode leftNode = node5.getLeft();
		System.out.println("10号节点的前驱节点为："+leftNode);
		HeroNode rightNode = node5.getRight();
		System.out.println("10号节点的后继节点为："+rightNode);
		
		// 当线索化二叉树后，使用以前遍历二叉树的方法遍历 - 会无限地循环
//		threadedBinaryTree.fixOrder();
		
		System.out.println();
		
		// 使用线索化遍历的方法遍历
		System.out.println("使用线索化的方式遍历，线索化二叉树");
		threadedBinaryTree.threadedList();  // 8 3 10 1 14 6
		
	}
	
}


//ThreadedBinaryTree 实现了线索化功能的二叉树
class ThreadedBinaryTree{
	private HeroNode root;
	
	// 为了实现线索化，需要创建给指向节点当前节点的前驱节点的指针
	// 在递归进行线索化时，pre总是保留前一个节点
	private HeroNode pre = null;
	
	public void setRoot(HeroNode root){
		this.root = root;
	}
	
	// 重载一下threadedNodes方法
	public void threadedNodes(){
		this.threadedNodes(root);
	}
	
	// 遍历线索化二叉树的方法
	public void threadedList(){
		// 定义一个变量，存储当前遍历的节点，从root开始
		HeroNode node = root;
		while(node != null){
			// 循环的找到leftType == 1的节点，第一个找到就是8节点
			// 后面随着遍历而变化，因为当leftType==1时，说明该节点是按照线索化
			// 处理后的有效节点
			while(node.getLeftType() == 0){
				node = node.getLeft();
			}
			// 打印当前这个节点
			System.out.println(node);
			// 如果当前节点的右指针指向的是后继节点，就一直输出
			while(node.getRightType() == 1){
				// 获取到当前节点的后继节点
				node = node.getRight();
				System.out.println(node);
			}
			// 当走到这一步的时候，表明node.getRightType已经不为1（后继节点）了
			node = node.getRight();
		}
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

