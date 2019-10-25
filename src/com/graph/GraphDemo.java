package com.graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年10月24日 下午10:37:20	
*/
public class GraphDemo {
	
	private static ArrayList<String> vertexList;  // 存储顶点集合
	private int[][] edges; // 储存图对应的邻结矩阵
	private int numOfEdges;  // 表示边的数目
	private boolean[] isVisited;  // 记录某个节点是否被访问过
	
	public static void main(String[] args) {
		// 测试创建是否成功
		int n = 5; // 节点的个数
		String Vertexs[] = {"A","B","C","D","E"};
		// 创建图对象
		GraphDemo graphDemo = new GraphDemo(n);
		// 循环添加顶点
		for(String vertex : Vertexs){
			graphDemo.insertVertexList(vertex);
		}
		// 添加边
		// A-B A-C B-C B-D B-E
		graphDemo.insertEdge(0, 1, 1); //A-B  -  表示A与B能相连
		graphDemo.insertEdge(0, 2, 1); //A-C  -  表示A与B能相连
		graphDemo.insertEdge(1, 2, 1); //B-C  -  表示A与B能相连
		graphDemo.insertEdge(1, 3, 1); //B-D  -  表示A与B能相连
		graphDemo.insertEdge(1, 4, 1); //B-E  -  表示A与B能相连
		
		// 显示邻阶矩阵
		graphDemo.showGraph();
		
		System.out.println(vertexList);
		
	}
	
	// 构造器
	public GraphDemo(int n){
		// 初始化矩阵和vertexList
		edges = new int[n][n];
		vertexList = new ArrayList<>(n);
		numOfEdges = 0;
		isVisited = new boolean[n];
	}
	
	// 显示图对应的矩阵
	public void showGraph(){
		for(int link[] : edges){
			System.out.println(Arrays.toString(link));
		}
		System.out.println();
	}
	
	// 得到第一个邻接节点的下标w
	/**
	 * 
	 * @param index		要查找节点的下标
	 * @return			如果存在就返回对应的下标，否则就返回-1
	 */
	public int getFirstNeighbor(int index){
		for(int j=0;j<vertexList.size();j++){
			if(edges[index][j] > 0){
				return j;
			}
		}
		return -1;
	}
	
	// 根据前一个邻接节点的下标来获取下一个邻接节点
	public int getNextNeighbor(int v1,int v2){
		for(int j=v2+1;j<vertexList.size();j++){
			if(edges[v1][j] > 0){
				return j;
			}
		}
		return -1;
	}
	
	// 图中常用的方法
	// 返回节点的个数
	public int getNumOfVertex(){
		return vertexList.size();
	}
	
	//得到边的数目
	public int getNumberOfEdges(){
		return numOfEdges;
	}
	
	// 返回结点i(下标)对应的数据 0->"A" 1->"B" 2->"C"
	public String getValueByIndex(int i){
		return vertexList.get(i);
	}
	
	//返回v1和v2的权值
	public int getWeight(int v1,int v2){
		return edges[v1][v2];
	}
	
	// 插入结点
	public void insertVertexList(String vertex){
		vertexList.add(vertex);
	}
	// 添加边
	/**
	 * 
	 * @param v1	表示点的下标即是第几个顶点 "A"-"B" | "A"->0 "B"->1
	 * @param v2	表示第二个顶点对应的下标
	 * @param weight	表示权值
	 */
	public void insertEdge(int v1,int v2,int weight){
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
		numOfEdges++;
	}
	
}
	