package com.useful.floyd;

import java.util.Arrays;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年11月9日 下午2:44:19	
*/

/**
 * 
 * @author ABC 	
 * 
 * 弗洛伊德算法：
 * 		每一个顶点都是出发访问点，所以需要将每一个顶点看作被访问顶点，求出从每一个顶点到其它顶点的最短路径
 *
 */


public class FloydAlgorithm {
	
	public static void main(String[] args) {
		
		char[] vertex = {'A','B','C','D','E','F','G'};
		// 邻接矩阵
		int[][] matrix = new int[vertex.length][vertex.length];
		final int N = 65535;	// 表示不可连接
		matrix[0] = new int[]{0,5,7,N,N,N,2};
		matrix[1] = new int[]{5,0,N,9,N,N,3};
		matrix[2] = new int[]{7,N,0,N,8,N,N};
		matrix[3] = new int[]{N,9,N,0,N,4,N};
		matrix[4] = new int[]{N,N,8,N,0,5,4};
		matrix[5] = new int[]{N,N,N,4,5,0,6};
		matrix[6] = new int[]{2,3,N,N,4,6,0};
		// 创建图 Graph
		Graph graph = new Graph(vertex.length, matrix,vertex);
		// 测试邻接矩阵是否正常
		graph.show();
		
	}
	
}

// 创建图
class Graph{
	private char[] vertex; // 存放顶点的数组
	private int[][] dis;	// 保存，从各个顶点出发到其它顶点的距离，最后的结果，也是保留在该数组
	private int[][] pre;	// 保存到达目标顶点的前驱节点
	
	/**
	 * 构造器
	 * @param length	大小
	 * @param matrix	邻接矩阵
	 * @param vertex	顶点数组
	 */
	public Graph(int length,int[][] matrix,char[] vertex){
		this.vertex = vertex;
		this.dis = matrix;
		this.pre = new int[length][length];
		// 对pre数组初始化，注意存放的是前驱节点的下标
		for(int i=0;i<length;i++){
			Arrays.fill(pre[i],i);
		}
	}
	
	public Graph(char[] vertex2, int[][] matrix) {
		// TODO 自动生成的构造函数存根
	}

	// 显示pre数组和dis数组
	public void show(){
		// 为了显示便于阅读，我们优化一下输出
		char[] vertex = {'A','B','C','D','E','F','G'};
		for(int k=0;k<dis.length;k++){
			// 先将pre数组输出的一行
			for(int i=0;i<dis.length;i++){
				System.out.print(vertex[pre[k][i]] + " ");
			}
			System.out.println();
			// 输出dis数组的一行数据
			for(int i=0;i<dis.length;i++){
				System.out.print("("+dis[k][i]+ "到" + vertex[i] + "的最短路径是" + dis[k][i] + ")");
			}
			System.out.println();
			System.out.println();
		}
	}
	
	
}
	