package com.tree;

import java.security.interfaces.RSAKey;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年9月2日 下午10:31:57	
*/

/*
 * 	删除子节点：
 * 		规定-递归删除节点
 * 			1.如果删除的节点是叶子节点，则删除该节点 
 * 			2.如果删除的节点是非叶子节点，则删除该子树
 * 		
 * 	  完成删除节点的操作：
 * 		思路：
 * 		1.因为我们的二叉树是单向的，所以我们是判断当前节点的子节点是否需要删除节点，而不能去判断当前这个节点是不是需要删除节点
 * 		2.如果当前节点的左子节点不为空，并且左子节点就是要删除节点，就将this.left=null;并且就返回(结束递归删除)
 * 		3.如果当前节点的子节点不为空，并且右子节点就是要删除节点，就将this.right=null;并且就返回(结束递归删除)
 * 		4.如果第2步和第3步没有删除节点，那么我们就需要向左子树进行递归删除
 * 		5.如果第4步也没有删除节点，则应当向右子树进行递归删除
 * 		6.考虑如果树是空树root，如果只有一个root节点，则等价将二叉树置空
 * 
 * */


public class BinaryTreeReceiveDelNodeSolution {
	
	public static void main(String[] args) {
		
		BinaryTreeForDelNode binaryTreeForDelNode = new BinaryTreeForDelNode();
		
		HeroNodeForDelNode root = new HeroNodeForDelNode(1,"宋江");
		HeroNodeForDelNode node2 = new HeroNodeForDelNode(2,"吴用");
		HeroNodeForDelNode node3 = new HeroNodeForDelNode(3,"卢俊义");
		HeroNodeForDelNode node4 = new HeroNodeForDelNode(4,"林冲");
		HeroNodeForDelNode node5 = new HeroNodeForDelNode(5,"关胜");
		
		binaryTreeForDelNode.setRoot(root);
		root.setLeft(node2);
		root.setRight(node3);
		node3.setRight(node4);
		node3.setLeft(node5);
		
		
		// 测试删除节点的代码
		System.out.println("删除前，前序遍历");
		binaryTreeForDelNode.preOrder(); // 1,2,3,5,4
		//进行删除操作
		binaryTreeForDelNode.delNode(5);
		System.out.println("删除后，前序遍历");
		binaryTreeForDelNode.preOrder();
		
		
	}
	
}

//定义一颗二叉树
class BinaryTreeForDelNode{
	private HeroNodeForDelNode root;
	
	public void setRoot(HeroNodeForDelNode root){
		this.root = root;
	}
	
	
	// 删除节点
	public void delNode(int no){
		if(root != null){
			// 如果只有一个root节点，这里立即判断root是不是就是要删除的节点
			if(root.getNo() == no){
				root = null;
			}else{
				// 递归删除
				root.delNode(no);
			}
		}else{
			System.out.println("空树，不能删除~");
		}
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
	
	// 后序遍历
	public  void postOrder(){
		if(this.root != null){
			this.root.postOrder();
		}else{
			System.out.println("二叉树为空，无法遍历");
		}
	}
	
	// 前序遍历查找节点
	public HeroNodeForDelNode preOrderSearch(int no){
		if(root != null){
			return root.preOrderSearch(no);
		}else{
			return null;
		}
	}
	
	// 中序遍历查找节点
	public HeroNodeForDelNode infixOrderSearch(int no){
		if(root != null){
			return root.infixOrderSearch(no);
		}else{
			return null;
		}
	}
	
	// 后序遍历查找节点
	public HeroNodeForDelNode postOrderSearch(int no){
		if(root != null){
			return root.postOrderSearch(no);
		}else{
			return null;
		}
	}
	
}


//先创建HeroNode节点
class HeroNodeForDelNode{
	private int no;
	private String name;
	private HeroNodeForDelNode left; // 默认为null
	private HeroNodeForDelNode right; // 默认为null
	public HeroNodeForDelNode(int no,String name){
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
	public HeroNodeForDelNode getLeft() {
		return left;
	}
	public void setLeft(HeroNodeForDelNode left) {
		this.left = left;
	}
	public HeroNodeForDelNode getRight() {
		return right;
	}
	public void setRight(HeroNodeForDelNode right) {
		this.right = right;
	}
	@Override
	public String toString() {
		return "HeroNodeForFind [no=" + no + ", name=" + name + "]";
	}
	
	// 递归删除节点
	// 1.如果删除的节点是叶子节点，则删除该节点
	// 2.如果删除的节点是非叶子节点，则删除该子树
	public void delNode(int no){
		 //2.如果当前节点的左子节点不为空，并且左子节点就是要删除节点，就将this.left=null;并且就返回(结束递归删除)
		if(this.left != null && this.left.no == no){
			this.left = null;
			return;
		}
		//3.如果当前节点的子节点不为空，并且右子节点就是要删除节点，就将this.right=null;并且就返回(结束递归删除)
		if(this.right != null && this.right.no == no){
			this.right = null;
			return;
		}
		//4.如果第2步和第3步没有删除节点，那么我们就需要向左子树进行递归删除
		// 这里需要注意的是：不应直接return进行返回，因为递归左子树可能有没有找到的情况
		if(this.left != null){
			this.left.delNode(no);
		}
		//5.如果第4步也没有删除节点，则应当向右子树进行递归删除
		if(this.right != null){
			this.right.delNode(no);
		}
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
	
	
	// 前序遍历查找节点
	/**
	 * 
	 * @param no	查找的no
	 * @return		如果找到就返回该Node，如果没有找到返回null
	 */
	public HeroNodeForDelNode preOrderSearch(int no){
		System.out.println("前序遍历查找统计");
		// 比较当前节点是不是
		if(this.no == no){
			return this;
		}
		// 1.判断当前节点的左子节点是否为空，如果不为空，则进行递归前序查找
		// 2.如果左递归前序查找，找到节点，则返回
		HeroNodeForDelNode resNode = null;
		if(this.left != null){
			resNode = this.left.preOrderSearch(no);
		}
		if(resNode != null){  //说明我们左子树找到了
			return resNode;
		}
		// 1.左递归前序查找，找到节点，则返回，否则继续判断
		// 2.当前节点的右子节点是否为空，如果不空，则继续向右递归前序查找
		if(this.right != null){
			resNode = this.right.preOrderSearch(no);
		}
		// 情况都试完了，不管是否找到均返回
		return resNode;
	}
	
	
	// 中序遍历查找
	public HeroNodeForDelNode infixOrderSearch(int no){
		// 判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
		HeroNodeForDelNode resNode = null;
		if(this.left != null){
			resNode = this.left.infixOrderSearch(no);
		}
		if(resNode != null){
			return resNode;
		}
		System.out.println("中序遍历查找统计");
		// 如果找到，则返回；如果没有找到，就和当前节点比较，如果是则返回当前节点
		if(this.no == no){
			return this;
		}
		// 否则继续进行右递归的中序查找
		if(this.right != null){
			resNode = this.right.infixOrderSearch(no);
		}
		return resNode;
	}
	
	// 后序遍历查找
	public HeroNodeForDelNode postOrderSearch(int no){
		// 判断当前节点的子节点是否为空，如果不为空，则递归后序查找
		HeroNodeForDelNode resNode = null;
		if(this.left != null){
			resNode = this.left.postOrderSearch(no);
		}
		if(resNode != null){
			return resNode;
		}
		if(this.right != null){
			resNode = this.right.postOrderSearch(no);
		}
		// 如果左子树没有找到，则向右子树递归进行后序遍历查找
		if(resNode != null){
			return resNode;
		}
		System.out.println("后序遍历查找统计");
		// 如果左子树都没有找到，就比较当前节点是不是
		if(this.no == no){
			return this;
		}
		return resNode;
	}
	
	
}



	