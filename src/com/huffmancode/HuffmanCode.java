package com.huffmancode;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年10月11日 下午11:06:01	
*/

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*赫夫曼编码*/

/*		功能：根据赫夫曼编码压缩数据的原理，需要创建 "i like like like java do you like a java"对应的赫夫曼树
 * 	 思路：	
 * 		一、Node{data(存放数据),weight(权值),left 和 right}
 * 		二、得到"i like like like java do you like a java"对应的byte[] 数组
 * 		三、编写一个方法，将准备构建赫夫曼树的Node节点放到List，形式{Node[data=97,weight=5],Node[data=8,weight=5]....}
 * 		四、可以通过List创建对应的赫夫曼树
 * 
 *  
 * 	
 * 
 * 
 * 
 * */

public class HuffmanCode {
	
	public static void main(String[] args){
		
		String content = "i like like like java do you like a java";
		//二、得到"i like like like java do you like a java"对应的byte[] 数组
		byte[] bytes = content.getBytes();  // 得到对饮的字符数组和字符对应的ASCII编码值
//		System.out.println(bytes.length);  // 40
		
		ArrayList<Node> nodes = getNodes(bytes);
		System.out.println("nodes="+nodes);
		
		// 测试
		System.err.println("赫夫曼树");
		Node huffmanTreeRoot = createHuffmanTree(nodes);
		System.out.println("前序遍历");
		
		// 前序遍历赫夫曼树
		preOrder(huffmanTreeRoot);
		
		
	}
	
	// 前序遍历赫夫曼树
	public static void preOrder(Node root){
		if(root != null){
			root.preOrder();
		}else{
			System.out.println("赫夫曼树为空");
		}
	}
	
	
	// 三、编写一个方法，将准备构建赫夫曼树的Node节点放到List
	private static ArrayList<Node> getNodes(byte[] bytes){
		// 1.创建一个ArrayList
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		// 遍历bytes，统计m每一个byte出现的次数->map[key,value]
		Map<Byte,Integer> counts = new HashMap<Byte, Integer>();
		for(byte b : bytes){
			Integer count = counts.get("b");
			if(counts.get("b") == null){
				counts.put(b,1);
			}else{
				counts.put(b,count+1);
			}
		}
		// 把每个键值对转成一个Node对象，并加入到nodes集合
		for(Map.Entry<Byte,Integer> entry : counts.entrySet()){
			nodes.add(new Node(entry.getKey(), entry.getValue()));
		}
		
		return nodes;
		
	}
	
	//四、通过List创建对应的赫夫曼数
	private static Node createHuffmanTree(ArrayList<Node> nodes){
		while(nodes.size()>1){
			// 排序，从小到大
			Collections.sort(nodes);
			// 取出第一颗最小的二叉树
			Node leftNode = nodes.get(0);
			// 取出第二颗最小的二叉树
			Node rightNode = nodes.get(1);
			// 创建一颗新的二叉树，它的根节点 没有data,只有权值
			Node parent = new Node(null, leftNode.weight+rightNode.weight);
			parent.left = leftNode;
			parent.right = rightNode;
			
			// 将已经处理的两颗二叉树从nodes中删除
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			
			// 往nodes中添加新的节点(parent)
			nodes.add(parent);
			
		}
		// 最后返回nodes中第一个元素(有且只有一个元素) - 赫夫曼的根节点
		return nodes.get(0);
	}
	
	
	
}


// 一、创建Node，带数据和权值   Node{data(存放数据),weight(权值),left 和 right}
class Node implements Comparable<Node>{
	
	Byte data;	// 存放数据(字符)本身，比如 'a'=>97   ' '=>2
	int weight; // 权值，表示字符出现的次数
	Node left;
	Node right;
	
	public Node(Byte data,int weight) {
		// TODO 自动生成的构造函数存根
		this.data = data;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Node o) {
		// 从小到大进行排序
		return this.weight - o.weight;
	}
	
	public String toString(){
		return "Node [data = "+data+" weight=" + weight + "]"; 
	}
	
	// 前序遍历
	public void preOrder(){
		System.out.println(this);
		if(this.left != null){
			this.left.preOrder();
		}
		if(this.right != null){
			this.right.preOrder();
		}
	}
	
}