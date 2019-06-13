package com.sparseArr;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年6月13日 下午10:01:56	
*/
/*
 * 	首先有一个二维数组，二维数组中只有很少有用的数据，
 * 		为了压缩存储空间所以将有用的数据提炼出来重新组合成一个新的稀疏二位数组
 * 	
 * 		二维数组转稀疏数组：
 * 			1.遍历整个二维数组，得到有效的数据sum
 * 			2.根据sum创建新的稀疏数组，对应的行、列分别为：sparseArr[sum+1],3  即sparseArr int[sum+1][3]
 * 			3.将二维数组的有效数据存入到稀疏数组中
 * 
 * 
 * 		稀疏数组转二维数组：
 * 			1.先读取稀疏数组的第一行，根据第一行的数据得到二维数组的行列大小  chessArr2 = int[11][11]
 * 			2.在读取稀疏数组后几行的数据，并赋给原始的二维数组即可
 * 
 * */
public class SparseArray {
	
//	public static int count = 0;
	
	public static void main(String[] args) {
		
		// 首先来定义一个二维数组,行和列均为11
		int[][] chessArr1 = new int[11][11];
		
		// 人为往二维数组中赛入一些测试数据 - 1代表白子  2代表黑子
		chessArr1[0][3] = 1;
		chessArr1[1][5] = 2;
		chessArr1[4][7] = 2;
		chessArr1[8][4] = 1;
		
		//定义总共有多少个有效数据
		int count = 0;
		
		System.out.println("原始的二维数组为：");
		
		// 遍历二维数组里面的有效数据，并且获得有效数据的总数
		for(int[] a : chessArr1){  // 首先遍历行
			for(int b : a){  // 再遍历列
				if(b != 0){
					 //有效数据的自增
					count++;
				}
			// 格式化输出
				System.out.printf("%d\t",b);
			}
			// 每次打完一行的数据则进行换行
			System.out.println();
		}
//		System.out.println(count);
		
//		System.out.println(chessArr1.length);
		
		// 创建二维稀疏对象
		int[][] sparseArr = new int[count+1][3];
		// 初始化稀疏数组
		sparseArr[0][0] = 11;
		sparseArr[0][1] = 11;
		sparseArr[0][2] = count;
		// 定义一个稀疏数组的行自增变量
		int countSparse = 0;
		// 循环原始的二维数组  =>  向稀疏数组中赛入数据
		for(int i=0;i<chessArr1.length;i++){  // 遍历行
			for(int j=0;j<chessArr1[0].length;j++){ // 遍历列
				// 判断原始数组中元素不为0的就往稀疏数组中添加数据
				if(chessArr1[i][j] != 0){
					// 每遍历一次则自增一次
					countSparse++;
					// 总共就三列，分别为每列赋值，每列就相当于存放的数据在原始的二维数组中的位置即i，j的值
					sparseArr[countSparse][0] = i;
					sparseArr[countSparse][1] = j;
					sparseArr[countSparse][2] = chessArr1[i][j];
				}
			}
		}
		
		System.out.println();
		System.out.println("得到的稀疏数组为：");
		// 对稀疏数组进行遍历输出
		for(int[] a : sparseArr){  // 首先遍历行
			for(int b : a){  // 再遍历列
				if(b != 0){
					 //有效数据的自增
					count++;
				}
			// 格式化输出
				System.out.printf("%d\t",b);
			}
			// 每次打完一行的数据则进行换行
			System.out.println();
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
	