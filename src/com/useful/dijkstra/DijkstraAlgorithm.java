package com.useful.dijkstra;

import java.util.Arrays;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年11月7日 下午10:37:56	
*/

/*
 * 
 * 		迪杰斯特拉算法 - 解决最短路径问题		
 * 
 * 
 */
public class DijkstraAlgorithm {
	
	public static void main(String[] args) {
		
		char[] vertex = {'A','B','C','D','E','F','G'};
		// 邻接矩阵
		int[][] matrix = new int[vertex.length][vertex.length];
		final int N = 65535;	// 表示不可连接
		matrix[0] = new int[]{N,5,7,N,N,N,2};
		matrix[1] = new int[]{5,N,N,9,N,N,3};
		matrix[2] = new int[]{7,N,N,N,8,N,N};
		matrix[3] = new int[]{N,9,N,N,N,4,N};
		matrix[4] = new int[]{N,N,8,N,N,5,4};
		matrix[5] = new int[]{N,N,N,4,5,N,6};
		matrix[6] = new int[]{2,3,N,N,4,6,N};
		// 创建图 Graph
		Graph graph = new Graph(vertex, matrix);
		// 测试邻接矩阵是否正常
		graph.showGraph();
	}
	
}

// 构造一个图的类
class Graph{
	private char[] vertex; // 顶点数组
	private int[][] matrix;	// 邻接矩阵
	// 构造器
	public Graph(char[] vertex,int[][] matrix){
		this.vertex = vertex;
		this.matrix = matrix;
	}
	// 显示图
	public void showGraph(){
		for(int[] link : matrix){
			System.out.println(Arrays.toString(link));
		}
	}
}





	