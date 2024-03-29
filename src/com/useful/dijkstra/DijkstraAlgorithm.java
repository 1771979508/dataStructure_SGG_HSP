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


/*
 * 	弗洛伊德算法：
 * 		每一个顶点都是出发访问点，所以需要将每一个顶点看作被访问顶点，求出从每一个顶点到其它顶点的最短路径
 * 
 *  迪杰斯特拉算法：
 * 		通过选定的被访问顶点，求出从出发访问顶点到其它顶点的最短路径
 * 
 * */

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
		
		graph.djs(6);
		graph.showDijkstra();
		
	}
	
	
	
	
}

// 构造一个图的类
class Graph{
	private char[] vertex; // 顶点数组
	private int[][] matrix;	// 邻接矩阵
	private VisitedVertex vv; // 已经访问过顶点的集合
	// 构造器
	public Graph(char[] vertex,int[][] matrix){
		this.vertex = vertex;
		this.matrix = matrix;
	}
	
	// 显示最后的结果
	public void showDijkstra() {
		vv.show();
	}
	
	// 显示图
	public void showGraph(){
		for(int[] link : matrix){
			System.out.println(Arrays.toString(link));
		}
	}
	
	/**
	 * 
	 * @param index	表示出发顶点对应的下标
	 */
	public void djs(int index){
		vv = new VisitedVertex(vertex.length,index);
		// 更新index顶点到周围顶点的距离和前驱顶点
		update(index);
		for(int j=1;j<vertex.length;j++){
			index = vv.updateArr();	// 选择并返回新的访问顶点
			update(index);
		}
	}
	
	/**
	 * 功能：更新index下标顶点到周围顶点的距离和周围顶点的前驱节点
	 * @param index
	 */
	private void update(int index){
		int len = 0;
		// 根据遍历我们的邻接矩阵 matrix[index]行
		for(int j=0;j<matrix[index].length;j++){
			// len含义是： 出发顶点到index顶点的距离 + 从index顶点到j顶点的距离的和
			len = vv.getDis(index) + matrix[index][j];
			// 如果j顶点没有被访问过，并且len小于出发顶点到j顶点的距离，即获得最新的最短路径，就需要进行更新
			if(!vv.in(j) && len<vv.getDis(j)){
				// 更新j顶点的前驱节点为index顶点
				vv.updatePre(j, index);
				// 更新出发顶点到j顶点的距离
				vv.updateDis(j, len);
			}
		}
	}
	
}

// 已访问顶点的集合
class VisitedVertex{
	// 记录各个顶点是否访问过 1表示访问过 0表示未访问，会动态更新
	public int[] already_arr;
	// 每个下标对应的值为前一个顶点下标，会动态更新
	public int[] pre_visited;
	// 记录出发顶点到其他所有顶点的距离，比如G为出发点，就会记录G到其它顶点的距离，会动态更新，求的最短距离就会存放到dis
	public int[] dis;
	
	// 构造器
	/**
	 * 
	 * @param length	表示顶点的个数
	 * @param index		出发顶点对应的下标，比如G顶点，下标就是6
	 */
	public VisitedVertex(int length,int index){
		this.already_arr = new int[length];
		this.pre_visited = new int[length];
		this.dis = new int[length];
		// 初始化数组
		Arrays.fill(dis, 65535);
		// 设置出发顶点被访问过为1
		this.already_arr[index] = 1;
		// 设置出发顶点的访问距离为0
		this.dis[index] = 0;
	}
	
	/**
	 * 功能：判断index顶点是否被访问过
	 * @param index
	 * @return	如果访问过，就返回true，否则访问false
	 */
	public boolean in(int index){
		return already_arr[index] == 1;
	}
	
	/**
	 * 功能：更新出发顶点到index顶点的距离
	 * @param index
	 * @param len
	 */
	public void updateDis(int index,int len){
		dis[index] = len;
	}
	
	/**
	 * 功能：更新pre这个顶点的前驱顶点为index顶点
	 * @param pre
	 * @param index
	 */
	public void updatePre(int pre,int index){
		pre_visited[pre] = index;
	}
	
	/**
	 * 功能：返回出发顶点到index顶点的距离
	 * @param index
	 * @return
	 */
	public int getDis(int index){
		return dis[index];
	}
	
	/**
	 * 功能：继续选择并返回新的访问顶点，比如选择这里的G之后，就是A点作为新的访问顶点(注意不是出发顶点)
	 * @return
	 */
	public int updateArr(){
		int min = 65535,index = 0;
		for(int i=0;i<already_arr.length;i++){
			if(already_arr[i] == 0 && dis[i]<min){
				min = dis[i];
				index = i;
			}
		}
		// 更新index 顶点被访问过
		already_arr[index] = 1;
		return index;
	}
	
	/**
	 * 显示最后的结果，即将三个数组的情况输出
	 */
	public void show(){
		System.out.println("============================");
		// 输出already_arr
		for(int i : already_arr){
			System.out.print(i + " ");
		}
		System.out.println();
		// 输出pre_visited
		for(int i : pre_visited){
			System.out.print(i + " ");
		}
		System.out.println();
		// 输出dis
		for(int i : dis){
			System.out.print(i + " ");
		}
		System.out.println();
		// 为了好看最后的最短距离，我们进行如下处理
		char[] vertex = {'A','B','C','D','E','F','G'};
		int count = 0;
		for(int i : dis){
			if(i != 65535){
				System.out.print(vertex[count] + "(" + i + ")");
			}else{
				System.out.println("N ");
			}
			count++;
		}
		System.out.println();
	}
	
}




	