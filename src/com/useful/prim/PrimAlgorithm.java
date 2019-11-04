package com.useful.prim;

import java.util.Arrays;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年11月3日 下午9:56:29	
*/

/*
 * 
 * 		普里姆算法
 * 
 * 
 * */

public class PrimAlgorithm {
	
	public static void main(String[] args) {
		
		// 测试看看图是否创建成功
		char[] data = new char[]{'A','B','C','D','E','F','G'};
		int verxs = data.length;
		// 邻接矩阵的关系使用二维数组表示,10000大数表示两个点不连通
		int[][] weight = new int[][]{
			{10000,5,7,10000,10000,10000,2},
			{5,10000,10000,9,10000,10000,3},
			{7,10000,10000,10000,8,10000,10000},
			{10000,9,10000,10000,10000,4,10000},
			{10000,10000,8,10000,10000,5,4},
			{10000,10000,10000,4,5,10000,6},
			{2,3,10000,10000,4,6,10000},
		};
		// 创建graph对象
		MGraph graph = new MGraph(verxs);
		// 创建一个MiniTree
		MiniTree miniTree = new MiniTree();
		miniTree.createGraph(graph, verxs, data, weight);
		// 输出
		miniTree.showGraph(graph);
		
	}
	
}

// 创建最小生成树 -> 村庄
class MiniTree{
	// 创建图的邻接矩阵
	/**
	 * 
	 * @param graph		图对象
	 * @param verxs		图对应的顶点个数
	 * @param data		图的各个顶点的值
	 * @param weight	图的邻接矩阵
	 */
	public void createGraph(MGraph graph,int verxs,char data[],int[][] weight){
		int i,j;
		for(i=0;i<verxs;i++){	// 操作顶点
			graph.data[i] = data[i];
			for(j=0;j<verxs;j++){
				// 构造图
				graph.weight[i][j] = weight[i][j];
			}
		}
	}
	
	// 显示图的邻接矩阵
	public void showGraph(MGraph graph){
		for(int[] link : graph.weight){
			System.out.println(Arrays.toString(link));
		}
	}
	
	
	
}
 

// 先写一个图的构造器
class MGraph{
	int verxs;	// 表示图的节点个数
	char[] data;	// 存放节点数据
	int[][] weight;	// 存放边，就是我们的邻接矩阵
	public MGraph(int verxs){
		this.verxs = verxs;
		data = new char[verxs];
		weight = new int[verxs][verxs];
	}
}