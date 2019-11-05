package com.useful.dynamic;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年10月29日 下午11:04:36	
*/

/*
 * 		动态规划 - 背包问题
 * 
 * */

/*
 * 	动态规划算法：
 * 		1.每次遍历到的第i个物品，根据w[i]和v[i]来确定是否需要将该物品放入背包中。
 * 			即对于给定的n个物品，设v[i]、w[i]分别为第i个物品的价值和重量，C为背包的容量。
 * 				再令v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
 * 		我们有下面的公司：
 * 			(1) v[i][0] = v[0][j]=0		// 表示 填入表 第一行和第一列为0
 * 			(2) 当w[i] > j 时： v[i][j] = v[i-1][j]	// 当准备加入新增的商品的容量大于当前背包的容量时，就直接使用上一个单元格的装入策略
 * 			(3)	当j>=w[i]时：v[i][j]=max{v[i-1][j],v[i-1][j-w[i]]+v[i]}
 * 				当准备加入的新增的商品的容量小于等于当前背包的容量
 * 				装入的方式：
 * 					v[i-1][j]:就是上一个单元格的装入的最大值
 * 					v[i]:表示当前商品的价值
 * 					v[i-1][j-w[i]]：装入i-1商品，到剩余空间j-w[i]的最大值
 * 					当j>=w[i]时：v[i][j]=max{v[i-1][j],v[i-1][j-w[i]]}
 * 
 * 
 * */


/*
 * 
 * 		物品 		重量		 价格				物品(磅)		0		1		2		3		4
 * 										
 * 		吉他		1		1500			吉他			0	   1500	   1500    1500    1500
 * 		音响		4		3000			音响			0	   1500	   1500    1500    3000
 * 		电脑		3		2000			电脑			0	   1500    1500    2000    2000+1500
 * 
 * 
 * 
 * */


public class KnapsackProblem {

	public static void main(String[] args) {
		
		int[] w = {1,4,3};  // 物品的重量
		int[] val = {1500,3000,2000};	// 物品的价值 这里val[i] 就是前面讲的v[i]
		int m = 4;	// 背包的容量
		int n = val.length;  // 物品的个数
		
		// 创建二维数组
		// v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
		int[][] v = new int[n+1][m+1];
		
		// 为了记录放入商品的情况，我们创建一个二维数组
		int[][] path = new int[n+1][m+1];
		
		// 初始化第一行和第一列，这里本程序中，可以不去处理，因为默认就是0
		for(int i=0;i<v.length;i++){
			v[i][0] = 0;	// 将第一列设置为0
		}
		for(int i=0;i<v[0].length;i++){
			v[0][i] = 0;		// 将第一行设置为0
		}
		
		
		// 根据前面得到公式来动态规划处理
		for(int i=1;i<v.length;i++){	// 不处理第一行 i是从1开始的
			for(int j=1;j<v[0].length;j++){	// 不处理第一列，j是从 1开始的
				// 公式
				if(w[i-1] > j){
					v[i][j] = v[i-1][j];
				}else{
					// 说明：
					// 因为我们的i 从1开始的，因此公式需要调整成
					// v[i][j]=Math.max(v[i-1][j],v[i-1][j-w[i]]+v[i])
//					v[i][j] = Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
					
					// 为了记录商品存放到背包的情况，我们不能直接使用上面的公式，需要使用if-else来体现公式
					if(v[i-1][j]<val[i-1]+v[i-1][j-w[i-1]]){
						v[i][j] = val[i-1]+v[i-1][j-w[i-1]];
						// 把当前的情况记录到path
						path[i][j] = 1;
					}else{
						v[i][j] = v[i-1][j];
					}
					
				}
			}
		}
		
		
		// 输出一下v 看看目前的情况
		for(int i=0;i<v.length;i++){
			for(int j=0;j<v[i].length;j++){
				System.out.print(v[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("=========================");
		
		// 倒叙输出我们放入的物品
		int i = path.length - 1; // 行的最大下标
		int j = path[0].length - 1;  // 列的最大下标
		while(i>0 && j>0){	// 从 path的最后开始找
			if(path[i][j] == 1){
				System.out.printf("第%d个商品放入到背包\n",i);  // i表示商品
				j -= w[i-1];  // 表示去掉已经放过的重量
			}
			i--;
		}
		
		
	}

}
	