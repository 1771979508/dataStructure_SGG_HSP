package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年10月24日 下午10:37:20	
*/

/*
 * 		广度优先遍历算法步骤：
 * 			1.访问初始节点v并标记节点v为已访问
 * 			2.节点v入队列
 * 			3.当队列非空时，继续执行，否则算法结束
 * 			4.出队列，取得队头节点u
 * 			5.查找节点u的第一个邻接节点w
 * 			6.若节点u的邻接节点w不存在，则转移到步骤3；否则循环执行一下三个步骤：
 * 				6.1 若节点w尚未被访问，则访问节点w并标记为已访问
 * 				6.2 节点w入队列
 * 				6.3 查找节点u的继w邻节点后的下一个邻接节点w，转到步骤6
 * 
 * */

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
		
//		System.out.println(vertexList);
		
		System.out.println("图的深度优先遍历：");
		graphDemo.dfs();
		System.out.println();
		System.out.println("图的广度优先遍历：");
		graphDemo.bfs();
		
		
		
	}
	
	// 构造器
	public GraphDemo(int n){
		// 初始化矩阵和vertexList
		edges = new int[n][n];
		vertexList = new ArrayList<>(n);
		numOfEdges = 0;
		
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
	
	// 深度优先遍历算法
	// i 第一次 就是 0
	private void dfs(boolean[] isVisted,int i){
		// 首先我们访问该节点并输出
		System.out.print(getValueByIndex(i) + "->");
		// 将节点设置为已经访问
		isVisted[i] = true;
		// 查找节点i的第一个邻接节点w
		int w = getFirstNeighbor(i);
		// 通过while循环找到已经访问过的邻接节点w  即 w!=-1
		while(w != -1){  // 说明有邻接节点
			if(!isVisted[w]){
				dfs(isVisted,w);
			}
			// 如果w节点已经被访问过
			w = getNextNeighbor(i, w);  // 当前节点i的下一个邻接节点w
		}
	}
	
	// 如果访问过该节点，则需要进行一个重载，遍历我们所有的节点，并进行dfs
	// 上面的方法默认是从第一个节点开始的，那么如果要从该节点的下一个节点继续进行重新开始，则需要多写一个重载的方法
	public void dfs(){
		isVisited = new boolean[vertexList.size()];
		// 遍历所有的节点，进行dfs[回溯]
		for(int i=0;i<getNumOfVertex();i++){
			if(!isVisited[i]){
				dfs(isVisited,i);
			}
		}
	}
	
	// 广度优先
	// 对一个节点进行广度优先遍历的方法
	private void bfs(boolean[] isVisted,int i){
		int u;  // 表示队列的头节点对应的下标
		int w;	// 邻接节点w
		// 队列，记录节点访问的顺序
		LinkedList queue = new LinkedList();
		// 访问节点，输出节点信息
		System.out.print(getValueByIndex(i));
		// 标记为已访问过
		isVisted[i] = true;
		// 将节点加入队列
		queue.addLast(i);
		
		while(!queue.isEmpty()){
			// 取出队列的头节点下标
			u = (Integer)queue.removeFirst();
			// 得到第一个邻接节点的下标w
			w = getFirstNeighbor(u);
			while(w != -1){  // 说明找到了
				// 是否访问过
				if(!isVisted[w]){
					System.out.print(getValueByIndex(w) + "=>");
					// 标记已经访问
					isVisted[w] = true;
					// 入队
					queue.addLast(w);
				}
				// 以u为前驱点，找w后面的下一个邻节点
				w = getNextNeighbor(u,w);  // 体现出我们的广度优先
			}
		}
		
	}
	
	// 重载遍历所有的节点，都进行广度优先搜索
	public void bfs(){
		isVisited = new boolean[vertexList.size()];
		for(int i=0;i<getNumOfVertex();i++){
			if(!isVisited[i]){
				bfs(isVisited,i);
			}
		}
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
	