package com.sparseArr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年6月15日 下午4:09:15	
*/
public class NewTxt {
	 public static void main(String[] args) throws IOException {
	  int n = 5;  //N*N数组
	  double[][] arr = new double[n][n]; //插入的数组
	  double[][] arr2 = new double[n][n];;  //读取出的数组
	  
	  //数组初始化，随机生成的[0,100)之间的double数
	  for(int i=0;i<n;i++){
	   for(int j=0;j<n;j++){
	    arr[i][j] = Math.random()*100;
	    System.out.println(arr[i][j]);
	   }
	  }
	  
	  File file = new File("d:\\array.txt");  //存放数组数据的文件
	  
	  FileWriter out = new FileWriter(file);  //文件写入流
	  
	  //将数组中的数据写入到文件中。每行各数据之间TAB间隔
	  for(int i=0;i<n;i++){
	   for(int j=0;j<n;j++){
	    out.write(arr[i][j]+"\t");
	   }
	   out.write("\r\n");
	  }
	  out.close();
	  
	  BufferedReader in = new BufferedReader(new FileReader(file));  //
	  String line;  //一行数据
	  int row=0;
	  //逐行读取，并将每个数组放入到数组中
	  while((line = in.readLine()) != null){
	   String[] temp = line.split("\t"); 
	   for(int j=0;j<temp.length;j++){
	    arr2[row][j] = Double.parseDouble(temp[j]);
	   }
	   row++;
	  }
	  in.close();
	  
	  //显示读取出的数组
	  for(int i=0;i<n;i++){
	   for(int j=0;j<n;j++){
	    System.out.print(arr2[i][j]+"\t");
	   }
	   System.out.println();
	  }
	 }
	}
	