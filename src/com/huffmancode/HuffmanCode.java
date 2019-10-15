package com.huffmancode;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年10月11日 下午11:06:01	
*/

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
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
 * 	在已经生成赫夫曼树的基础上:拿到赫夫曼编码就能还原出原来的数据
 * 		1.生成赫夫曼树对应的赫夫曼编码，如下表：
 * 			=01 a=100 d=11000 u=11001 e=1110 v=11011 i=101 y=11010 j=0010 k=1111 l=000 o=0011
 * 		2.使用赫夫曼编码来生成赫夫曼编码数据，即按照上面的赫夫曼编码，
 * 			将"i like like like java do you like a java" 字符串
 * 			生成对应的编码数据，形式如下
 * 		1010100010111.......共133位   与没有压缩之前(359)对比很明显节省了不少的空间
 * 
 * 
 * 
 * */

public class HuffmanCode {
	
	public static void main(String[] args){
		
		String content = "i like like like java do you like a java";
		//二、得到"i like like like java do you like a java"对应的byte[] 数组
		byte[] bytes = content.getBytes();  // 得到对应的字符数组和字符对应的ASCII编码值
//		System.out.println(bytes.length);  // 40
		
		byte[] huffmanCodesBytes = huffmanZip(bytes);
		System.out.println("压缩后的结果是："+Arrays.toString(huffmanCodesBytes) + "长度= "+huffmanCodesBytes.length);
		
		// 测试byteToBitString方法
//		System.out.println(byteToBitString((byte)1));
		byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);
		System.out.println("原来的字符串="+new String(sourceBytes));
		
		
		/*
		 * 
		 // 分步操作：
		 
		ArrayList<Node> nodes = getNodes(bytes);
		System.out.println("nodes="+nodes);
		
		// 测试
		System.err.println("赫夫曼树");
		Node huffmanTreeRoot = createHuffmanTree(nodes);
		System.out.println("前序遍历");
		
		// 前序遍历赫夫曼树
		huffmanTreeRoot.preOrder();
		
		// 测试是否生成了赫夫曼编码
		Map<Byte,String> huffmanCodes = getCodes(huffmanTreeRoot);
		System.out.println("生成的赫夫曼编码表"+huffmanCodes);
		
		
		// 测试压缩代码
		byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);  // 需要这样传参才能还原出来原来的数据 - 而且赫夫曼编码也是前缀编码
		System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));
		
		// 发送huffmanCodeBytes数组
		
		*/
		
	}
	
	// 使用一个方法，将前面的方法封装起来，便于我们的调用
	/**
	 * 
	 * @param bytes		原始的字符串对应的字节数组
	 * @return			是经过 赫夫曼编码处理后的字节数组(压缩后的数组)
	 */
	private static byte[] huffmanZip(byte[] bytes){
		ArrayList<Node> nodes = getNodes(bytes);
		//根据 nodes 创建的赫夫曼树
		Node huffmanTreeRoot = createHuffmanTree(nodes);
		// 对应的赫夫曼编码(根据 赫夫曼树)
		Map<Byte,String> huffmanCodes = getCodes(huffmanTreeRoot);
		// 根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
		byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
		return huffmanCodeBytes;
	}
	
	
	// 解码代码
	/**
	 * 
	 * @param huffmanCodes		赫夫曼编码表map
	 * @param huffmanBytes		赫夫曼编码得到的字节数组
	 * @return					原来的字符串对应的数组
	 */
	private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
		// 1.先得到huffmanBytes 对应的二进制的字符串，形式1010100010111...
		StringBuilder stringBuilder = new StringBuilder();
		// 将byte数组转成二进制的字符串
		for(int i=0;i<huffmanBytes.length;){
			byte b = huffmanBytes[i];
			// 判断是不是最后一个字节
			boolean flag = (i == huffmanBytes.length - 1);
			stringBuilder.append(byteToBitString(!flag, b));
		}
		
		// 把字符安装指定的赫夫曼编码进行解码
		//把赫夫曼编码表j进行调换，因为反响查询a->100 100->a
		Map<String, Byte> map = new HashMap<String,Byte>();
		for(Map.Entry<Byte,String> entry : huffmanCodes.entrySet()){
			map.put(entry.getValue(),entry.getKey());
		}
		// 输出map
		//System.out.println("map="+map);  // {000=108(i) 01=32(空格) 100=97(l) 101=105(k) ......}
		
		
		// 创建集合，存放byte
		ArrayList list = new ArrayList<>();
		// i可以理解成就是索引，扫描stringBuilder
		for(int i=0;i<stringBuilder.length();i++){
			int count = 1;  // 小的计数器
			boolean flag = true;
			Byte b = null;
			while(flag){
				// 1010100010111...
				// 递增的取出 key 1
				String key = stringBuilder.substring(i,i+count); // i不动，让count移动，直到匹配到mao中的字节
				b = map.get(key);
				if(b == null){  // 说明没有匹配到
					count++;
				}else{
					// 匹配到
					flag = false;
				}
			}
			list.add(b);
			i+=count;	// i直接移动到 count
		}
		
		// 当for循环结束后，我们list中就存放了所有的字符 "i like like like java do you like a java"
		// 把list 中的数据放入到byte[] 并返回
		byte b[] = new byte[list.size()];
		for(int i=0;i<b.length;i++){
			b[i] = (byte) list.get(i);
		}
		
		return b;
	}
	
	
	
	
	/*
	 * 		解码过程：
	 * 			1.将huffmanCodesBytes [-88,-65,-56,....]
	 * 			重新先转成 赫夫曼编码对应的二进制的字符串 "1010100010111...."
	 * 			2.赫夫曼编码对应的二进制的字符串 "1010100010111..."=>对照 赫夫曼编码 还原成原来的字符串 => "i like like like java do you like a java"
	 * 
	 * */
	/**
	 * 将byte转成一个二进制的字符串
	 * @param flag	是标志是否需要补高为 如果是true，表示需要补高位，如果是false表示不补
	 * @param b		传入的byte
	 * @return		是该b对应的二进制的字符串(注意是按补码返回)
	 */
	private static String byteToBitString(boolean flag,byte b){
		// 使用临时变量保存b
		int temp = b;
		// 如果是正数我们需要补高位
		if(flag){
			temp |= 256;  // 按位或256    1 000 0000 | 0000 0001  => 1 0000 0001
		}
		String str = Integer.toBinaryString(temp); // 返回的是temp对应的二进制的补码
		if(flag){
			return str.substring(str.length() - 8);
		}else{
			return str;
		}
	}
	
	
	
	// 编写一个方法，将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
	/**
	 * 
	 * @param bytes		这是原始的字符串对应的byte[]
	 * @param huffmanCodes	生成的赫夫曼编码map
	 * @return		返回赫夫曼编码处理后的 byte[]
	 * 	举例：String content = "i like like like java do you like a java"; => byte[] bytes = content.getBytes();
	 * 返回的是 字符串  "10101000....."
	 * => 对应的 byte[] huffmanCodeByte ，即8位对应一个byte，放入到huffmanCodeBytes
	 * huffmanCodeBytes[0] = 10101000(补码) => byte [推到 10101000=>10101000-1=>10100111(反码)=>11011000(原码)]
	 * huffmanCodes[1] = -88
	 * 
	 * 对应的程序是 String strBytes = "10101000";
	 * 			System.out.println((Byte)Integer.parseInt(strBytes,2))  // 将上面的字符串形式的补码，转为ASCII码，然后对应ASCII找到对应的字符
	 * 
	 */
	private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
		
		// 1.利用huffmanCodes 将 bytes 转成 赫夫曼编码对应的字符串
		StringBuilder sBuilder = new StringBuilder();
		// 遍历数组
		for(byte b : bytes){
			stringBuilder.append(b);
		}
		System.out.println("测试stringBuilder="+stringBuilder.toString());
		
		// 将"1010100010...." 转成byte[]
		
		// 统计返回 byte[] huffmanCodeBytes 长度
		// 一句话 int length = (stringBuilder.length()+7)/8;
		int len;
		if(stringBuilder.length()%8 == 0){
			len = stringBuilder.length() / 8;
		}else{
			len = sBuilder.length()/8 + 1;
		}
		// 创建 存储压缩后的byte数组
		byte[] huffmanCodeBytes = new byte[len];
		int index = 0;// 记录是第几个byte
		for(int i=0;i<stringBuilder.length();i=i+8){  // 因为是每8位对应一个byte，所以步长 +8
			String strByte;
			if(i+8 > stringBuilder.length()){  // 不够8位
				strByte = stringBuilder.substring(i);
			}else{
				strByte = stringBuilder.substring(i,i+8);
			}
			// 将strByte 转成一个byte，放入到huffmanCodeBytes
			huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte,2);
			index++;
		}
		return huffmanCodeBytes;
		
	}
	
	
	
	
	// 生成赫夫曼树对应的赫夫曼编码
	// 思路：
		//1.将赫夫曼编码表存放在Map<Byte,String>
			// 空格：32=>01 i:97=>100
	static Map<Byte,String> huffmanCodes = new HashMap<Byte,String>();
	// 2.在生成赫夫曼编码表时，需要存储拼接叶子节点的路径
	static StringBuilder stringBuilder = new StringBuilder();
	
	// 为了调用方便，我们重载getCodes
	private static Map<Byte,String> getCodes(Node root){
		if(root == null){
			return null;
		}
		// 处理root 的左子树
		getCodes(root.left,"0",stringBuilder);
		// 处理root的右子树
		getCodes(root.right,"1",stringBuilder);
		return huffmanCodes;
	}
	
	
	
	/**
	 * 功能：将传入的node节点的所有叶子节点的赫夫曼编码得到，并放入到huffmanCodes集合
	 * @param node	传入节点 - 根节点
	 * @param code	路径 - 左子节点为0，右子节点为1
	 * @param stringBuffer	叶子节点路径
	 */
	private static void getCodes(Node node,String code,StringBuilder stringBuilder){
		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
		// 将code加入到stringBuilder2
		stringBuilder2.append(code);
		if(node != null){  // 如果node==null不处理
			// 判断当前node是叶子节点还是非叶子节点
			if(node.data == null){  // 非叶子节点=>如何理解:因为它是新构造出来的节点(即parent节点)，parent节点不可能为叶子节点
				// 递归处理
				// 向左递归
				getCodes(node.left, "0", stringBuilder2);
				// 向右递归
				getCodes(node.right, "1", stringBuilder2);
			}else{  // 说明是y一个叶子节点
				// 就表示找到某个叶子节点的最后,存入赫夫曼编码表中
				huffmanCodes.put(node.data,stringBuilder2.toString());
			}
		}
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