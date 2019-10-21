package com.binarysorttree;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年10月17日 下午7:19:46	
*/


/*
 * 		二叉树删除节点可能遇到的集中情况：
 * 			一、第一种情况 - 删除叶子节点
 * 				(1)需要去找到要删除的节点，targetNode
 * 				(2)找到targetNode的 父节点 parent
 * 				(3)确定targetNode是parent的左子节点还是右子节点
 * 				(4)根据前面的情况来应对删除
 * 				左子节点：parent.left = null
 * 				右子节点：parent.right = null
 * 			
 * 			二、第二种情况 - 删除只有y一颗子树的节点，比如1
 * 				(1)需要去找到要删除的节点，targetNode
 * 				(2)找到targetNode的 父节点 parent
 * 				(3)确定targetNode的子节点是左子节点还是右子节点
 * 				(4)targetNode是parent的左子节点还是右子节点
 * 				(5)如果targetNode是左子节点
 * 					5.1如果targetNode是parent的左子节点
 * 						parent.left = targetNode.left
 * 					5.2如果targetNode是parent的右子节点
 * 						parent.right = targetNode.right
 * 				(6)如果targetNode有有子节点
 * 					6.1如果targetNode是parent的左子节点
 * 						parent.left = targetNode.right
 * 					6.2如果targetNode是parent的右子节点
 * 						parent.right = targetNode.right
 * 			
 * 			三、删除有两颗子树的节点(比如7，3，10)
 * 				(1)需要去找到要删除的节点 targetNode
 * 				(2)找到targetNode的父节点 parent
 * 				(3)从targetNode的右子树找到最小的节点
 * 				(4)用一个临时变量，将最小节点的值保存 temp = 11
 * 				(5)删除该最小节点
 * 				(6)targetNode.value = temp
 * 	
 * 
 * 
 * */


public class BinarySortTreeDemo {
	
	public static void main(String[] args) {
		
		int[] arr = {7,3,10,12,5,1,9,2};
		BinarySortTree binarySortTree = new BinarySortTree();
		// 循环添加节点到二叉排序树
		for(int i=0;i<arr.length;i++){
			binarySortTree.add(new Node(arr[i]));
		}
		// 中序遍历
		System.out.println("中序遍历二叉排序树");
		binarySortTree.infixOrder();  // 1 3 5 7 9 10 12
		
		
		// 测试删除叶子节点2
		binarySortTree.delNode(2);
		System.out.println("删除节点后");
		binarySortTree.infixOrder();
		
		
	}
	
}


// 创建二叉排序
class BinarySortTree{
	private Node root;
	
	// 查找要删除的节点
	public Node search(int value){
		if(root == null){
			return null;
		}else{
			return root.search(value);
		}
	}
	
	// 查找父节点
	public Node searchParent(int value){
		if(root == null){
			return null;
		}else{
			return root.searchParent(value);
		}
	}
	
	// 编写节点	
	// 1.返回以node为根节点的二叉排序树的最小节点的值
	// 2.删除node为根节点的二叉排序树的最小节点
	/**
	 * 
	 * @param node		传入的节点(当作二叉排序树的根节点)
	 * @return			返回以node为根节点的二叉排序树的最小节点的值
	 */
	public int delRightTreeMin(Node node){
		Node target = node;
		// 循环的查找左子节点，就会找到最小值
		while(target.left != null){
			target = target.left;
		}
		// 这时 target 就指向了最小节点
		// 删除最小节点
		delNode(target.value);
		return target.value;
	}
	
	// 删除节点
	public void delNode(int value){
		if(root == null){
			return;
		}else{
			// 1.需求先去找到要删除的节点 targetNode
			Node targetNode = search(value);
			// 如果没有找到要删除的节点
			if(targetNode == null){
				return;
			}
			// 如果我们发现当前这颗二叉排序树只有一个节点
			if(root.left == null && root.right == null){
				root = null;
				return;
			}
			// 去找到targetNode的父节点
			Node parent = searchParent(value);
			// 第一种情况：如果要删除的节点是叶子节点
			if(targetNode.left == null && targetNode.right == null){
				// 判断targetNode 是父节点的左子节点，还是右子节点
				if(parent.left != null && parent.left.value == value){	// 是左子节点
					parent.left = null;
				}else if(parent.right != null && parent.right.value == value){
					parent.right = null;
				}
			}else if(targetNode.left != null && targetNode.right != null){  // 第三种情况：删除有两颗子树的节点，那么就剩下最后一种情况了
				
				int minVal = delRightTreeMin(targetNode.right);
				targetNode.value = minVal;
				
			}else{  // 删除只有一颗子树的节点
				// 如果要删除的节点有左子节点
				if(targetNode.left != null){
					// 如果targetNode 是 parent 的左子节点
					if(parent.left.value == value){
						parent.left = targetNode.left;
					}else{ // targetNode是parent的右子节点
						parent.right = targetNode.left;
					}
				}else{ // 如果要删除的节点有右子节点
					// 如果targetNode 是 parent 的左子节点
					if(parent.left.value == value){
						parent.left = targetNode.right;
					}else{  // 如果targetNode 是 parent 的右子节点
						parent.right = targetNode.right;
					}
				}
			}
		}
	}
	
	// 准备完善删除节点的后两种情况
	
	// 添加节点的方法
	public void add(Node node){
		if(root == null){
			root = node;  // 如果root为空则直接让root指向node
		}else{
			root.add(node);
		}
	}
	// 中序遍历
	public void infixOrder(){
		if(root != null){
			root.infixOrder();
		}else{
			System.out.println("二叉排序树为空");
		}
	} 
}


// 创建node节点
class Node{
	
	int value;
	Node left;
	Node right;
	
	public Node(int value){
		this.value = value;
	}
	
	// 查找要删除的节点
	/**
	 * 
	 * @param value		希望删除的节点的值
	 * @return			如果找到返回该节点，否则返回null
	 */
	public Node search(int value){
		if(value == this.value){  // 找到就是该节点
			return this;
		}else if(value < this.value){	// 如果查找的值小于当前节点，向左子树递归查找
			// 如果左子节点为空
			if(this.left == null){
				return null;
			}
			return this.left.search(value);
		}else{	// 如果查找的值不小于当前节点，向右子树递归查找
			if(this.right == null){
				return null;
			}
			return this.right.search(value);
		}
	}
	
	
	// 查找要删除节点的父节点
	/**
	 * 
	 * @param value		要找到的节点的值
	 * @return			返回的是要删除的节点的父节点，如果没有就返回null
	 */
	public Node searchParent(int value){
		// 如果当前节点就是要删除的节点的父节点，就返回 
		if((this.left != null && this.left.value == value )  || (this.right != null && this.right.value == value)){
			return this;
		}else{
			// 如果要查找的值小于当前节点的值，并且当前节点的左子节点不为空
			if(value < this.value && this.left != null){
				return this.left.searchParent(value);  // 向左子树递归查找
			}else if(value >= this.value && this.right != null){  
				return this.right.searchParent(value);
			}else{
				return null;  // 没有找到父节点
			}
		}
	}
	
	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	// 添加节点的方法
	// 递归的形式添加节点，注意满足二叉排序树的要求
	public void add(Node node){
		if(node == null){
			return;
		}
		// 判断传入的节点的值，和当前子树的根节点的值的关系
		if(node.value < this.value){
			// 如果当前子节点为null
			if(this.left == null){
				this.left = node;
			}else{
				// 递归向左子树添加
				this.left.add(node);
			}
		}else{  // 添加的节点的值大于当前节点的值
			if(this.right == null){
				this.right = node;
			}else{
				// 递归向右子树添加
				this.right.add(node);
			}
		}
	}
	
	// 中序遍历
	public void infixOrder(){
		if(this.left != null){
			this.left.infixOrder();
		}
		System.out.println(this);
		if(this.right != null){
			this.right.infixOrder();
		}
	}
	
	
}