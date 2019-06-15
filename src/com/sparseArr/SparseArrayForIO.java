package com.sparseArr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

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
 * 
 * 		【在完成数组之间相互转换的基础之上再添加磁盘的写入写出功能(即将稀疏数组的存盘功能)】
 * 
 * */
public class SparseArrayForIO {
	
	//定义总共有多少个有效数据
	public static int count = 0;
	
	public static void main(String[] args) throws IOException {
		
		// 首先来定义一个二维数组,行和列均为11
		int[][] chessArr1 = new int[11][11];
		
		// 人为往二维数组中赛入一些测试数据 - 1代表白子  2代表黑子
		chessArr1[0][3] = 1;
		chessArr1[1][5] = 2;
		chessArr1[4][7] = 2;
		chessArr1[8][4] = 1;
		
		System.out.println("原始的二维数组为：");
		
		// 遍历二维数组里面的有效数据，并且获得有效数据的总数
		outPutArray(chessArr1);
		
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
		outPutArray(sparseArr);
		
		
		// 稀疏数组的存盘功能
		System.out.println();
		System.out.println("遍历稀疏数组，将稀疏数组的每一行存入sparseArr.txt文件中：");
		
//		OutputStreamWriter writer = null;
		File file = new File("src/com/sparseArr/sparseArr.txt");
		FileWriter out = new FileWriter(file);
		for(int[] a : sparseArr){  // 首先遍历行
			
			for(int b : a){  // 再遍历列，取得数据
				
				if(b != 0){
					 //有效数据的自增
					count++;
				}
			// 格式化输出
				System.out.printf("%d\t",b);
				
				
				// 在这里进行存盘功能的编写
				// 定义输出流对象
//				FileOutputStream fos = new FileOutputStream("src/com/sparseArr/sparseArr.txt",true);
//				writer = new OutputStreamWriter(fos, "UTF-8");
				
				// 要存入的数据
//				writer.write(b+",");
				
//				writer.flush();
//				System.out.println("写出完毕");
				
				
				out.write(b+",");
				System.out.println("写出完毕");
				
				
			}
			
			// 读取玩数据后写入一个换行
//			writer.write("\r\n");
			
			out.write("\r\n");
			
			// 每次打完一行的数据则进行换行
			System.out.println();
		
		}
//		writer.close();
		out.close();
		
		
		
		// 这里进行一些操作在上面用户由于各种原因退出存盘之后，接下来就是再此登录的时候我们读取txt里面的数据到稀疏数组
		// 读取文件的缓存文件
		int[][] sparseArr1 = new int[count+1][3];
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line; // 一行的数据
		int row = 0;
		// 逐行读取
		while((line = in.readLine())!= null){
			String[] temp = line.split(",");
			for(int j=0;j<temp.length;j++){
				sparseArr1[row][j] = (int) Double.parseDouble(temp[j]);
			}
			row++;
		}
		in.close();
		
		
		// 查看从sparseArr.txt文件中取出来的数据是否正确
		System.out.println();
		System.out.println("从txt文件中读取出来的数据：");
//		outPutArray(sparseArr1);
		for(int i=0;i<count+1;i++){
			for(int j=0;j<3;j++){
				System.out.printf("%d\t",sparseArr1[i][j]);
			}
			System.out.println();
		}
		
		
		
		
		
		
		
		// 接下来将稀疏数组转再变成原来的二维数组
		// 定义一个新的二维数组
		int[][] newChessArr = new int[sparseArr[0][0]][sparseArr[0][1]];
		int newCount = 0;
		// 首先获取二维数组的行列
		for(int i=1;i<sparseArr.length;i++){
			newCount++;
			newChessArr[sparseArr[newCount][0]][sparseArr[newCount][1]] = sparseArr[newCount][2];
		}
		
		System.out.println();
		System.out.println("得到的新的数组为：");
		
		// 输出新数组
		outPutArray(newChessArr);
		
		
	}
	
	// 格式化输出数组的函数
	public static void outPutArray(int[][] params){
		for(int[] a : params){  // 首先遍历行
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
	