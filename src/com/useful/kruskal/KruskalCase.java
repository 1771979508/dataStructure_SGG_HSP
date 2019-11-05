package com.useful.kruskal;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年11月5日 下午10:01:19	
*/


/*
 * 
 * 		克鲁斯卡尔算法 - 解决公交站问题
 * 
 * 
 * */
public class KruskalCase {
	
	private int edgeNum;  // 边的个数
	private char[] vertexs;  // 顶点数组
	private int[][] matrix;  // 邻接矩阵
	
	// 使用 INF 表示两个顶点不能连通
	private static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		
		char[] vertexs = new char[]{'A','B','C','D','E','F','G'};
		
		// 克鲁斯卡尔算法的邻接矩阵
		int[][] matrix = new int[][]{
			/*A*//*B*//*C*//*D*//*E*//*F*//*G*/
		/*A*/{ 0,  12, INF, INF, INF,  16,  14},
		/*B*/{ 12, 0,  10,  INF, INF,  7,  INF},
		/*C*/{INF, 10, 0,	3,	 5,	   6,  INF},
		/*D*/{INF, INF,3,	0,	 4,	  INF, INF},
		/*E*/{INF, INF,5,   4,   0,    2,    8},
		/*F*/{ 16, 7,  6,   INF, 2,	   0,    9},
		/*G*/{ 14, INF,INF, INF, 8,    9,    0},
		};
		
		// 创建克鲁斯对象实例
		KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
		kruskalCase.print();
		
	}
	
	// 构造器
	public KruskalCase(char[] vertexs,int[][] matrix){
		// 初始化顶点数和边的个数
		int vlen = vertexs.length;
		
		// 初始化顶点,拷贝的方式
		this.vertexs = new char[vlen];
		for(int i=0;i<vertexs.length;i++){
			this.vertexs[i] = vertexs[i];
		}
		
		// 初始化边，使用的是复制拷贝的方式
		this.matrix = new int[vlen][vlen];
		for(int i=0;i<vlen;i++){
			for(int j=0;j<vlen;j++){
				this.matrix[i][j] = matrix[i][j];
			}
		}
		
		// 统计边
		for(int i=0;i<vlen;i++){
			for(int j=0;j<vlen;j++){
				if(this.matrix[i][j] != INF){
					edgeNum++;
				}
			}
		}
		
		
	}
	
	// 打印邻接矩阵
	public void print(){
		System.out.println("邻接矩阵：");
		for(int i=0;i<vertexs.length;i++){
			for(int j=0;j<vertexs.length;j++){
				System.out.printf("%12d",matrix[i][j]);
			}
			System.out.println();
		}
	}
	
}
	