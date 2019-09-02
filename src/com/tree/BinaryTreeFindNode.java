package com.tree;

import java.security.interfaces.RSAKey;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年9月2日 下午10:31:57	
*/

/*
 * 		使用前序、中序、后序方式来查询指定的节点
 * 
 * 			前序查找思路：
 * 				1.先判断当前节点的no是否等于要查找的
 * 				2.如果是相等，则返回当前节点
 * 				3.如果不等，则判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
 * 				4.如果左递归前序查，找到节点，则返回；否则，继续判断当前节点的右子节点是否为空；如果不为空，则继续向右递归前序查找
 * 
 * 			中序查找思路：
 * 				1.判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
 * 				2.如果找到，则返回，如果没有找到，就和当前节点比较，如果是则返回当前节点，否则继续进行右递归的中序查找
 * 				3.如果右递归中序查找，找到就返回，否则返回null
 * 
 * 			后序查找思路：
 * 				1.判断当前节点的左子节点是否为空，如果不为空，则递归后序查找
 * 				2.如果找到，就返回；如果没有找到，就判断当前节点的右子节点是否为空，如果不为空，则右递归进行后序查找；如果找到，则返回
 * 				3.如果上面都没有找到，则和当前节点进行比较，如果是，则返回，否则返回null
 * 
 * 
 * 
 * */


public class BinaryTreeFindNode {
	
	public static void main(String[] args) {
		
		
		
	}
	
}

//定义一颗二叉树
class BinaryTreeForFind{
	private BinaryTreeForFind root;
	
	public void setRoot(BinaryTreeForFind root){
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
			this.root.fixOrder();
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
	
	// 前序遍历查找节点
	public HeroNodeForFind preOrderSearch(int no){
		if(root != null){
			return root.preOrderSearch(no);
		}else{
			return null;
		}
	}
	
	// 中序遍历查找节点
	public HeroNodeForFind infixOrderSearch(int no){
		if(root != null){
			return root.infixOrderSearch(no);
		}else{
			return null;
		}
	}
	
	// 后序遍历查找节点
	public HeroNodeForFind postOrderSearch(int no){
		if(root != null){
			return root.postOrderSearch(no);
		}else{
			return null;
		}
	}
	
	
}


//先创建HeroNode节点
class HeroNodeForFind{
	private int no;
	private String name;
	private HeroNodeForFind left; // 默认为null
	private HeroNodeForFind right; // 默认为null
	public HeroNodeForFind(int no,String name){
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
	public HeroNodeForFind getLeft() {
		return left;
	}
	public void setLeft(HeroNodeForFind left) {
		this.left = left;
	}
	public HeroNodeForFind getRight() {
		return right;
	}
	public void setRight(HeroNodeForFind right) {
		this.right = right;
	}
	@Override
	public String toString() {
		return "HeroNodeForFind [no=" + no + ", name=" + name + "]";
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
	public HeroNodeForFind preOrderSearch(int no){
		// 比较当前节点是不是
		if(this.no == no){
			return this;
		}
		// 1.判断当前节点的左子节点是否为空，如果不为空，则进行递归前序查找
		// 2.如果左递归前序查找，找到节点，则返回
		HeroNodeForFind resNode = null;
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
	public HeroNodeForFind infixOrderSearch(int no){
		// 判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
		HeroNodeForFind resNode = null;
		if(this.left != null){
			resNode = this.left.infixOrderSearch(no);
		}
		if(resNode != null){
			return resNode;
		}
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
	public HeroNodeForFind postOrderSearch(int no){
		// 判断当前节点的子节点是否为空，如果不为空，则递归后序查找
		HeroNodeForFind resNode = null;
		if(this.left != null){
			resNode = this.left.postOrderSearch(no);
		}
		if(this.left != null){
			return resNode;
		}
		if(this.right != null){
			resNode = this.right.postOrderSearch(no);
		}
		// 如果左子树没有找到，则向右子树递归进行后序遍历查找
		if(resNode != null){
			return resNode;
		}
		// 如果左子树都没有找到，就比较当前节点是不是
		if(this.no == no){
			return this;
		}
		return resNode;
	}
	
	
}



	