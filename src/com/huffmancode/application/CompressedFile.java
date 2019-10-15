package com.huffmancode.application;

import java.io.FileInputStream;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年10月15日 下午11:04:22	
*/

/*
 * 
 * 			赫夫曼编码实践 - 实现文件的压缩 
 * 
 * 
 * 
 * */
public class CompressedFile {
	
	
	public static void main(String[] args) {
		
		
		
	}
	
	
	// 编写犯法，将一个文件进行压缩
	/**
	 * 
	 * @param srcFile		你传入的希望压缩的文件的路径
	 * @param dstFile		我们压缩后将压缩文件那个目录
	 * 
	 */
	public static void zipFile(String srcFile,String dstFile){
		// 创建输入流
		try{
			//创建文件的输入流
			FileInputStream is = new FileInputStream(srcFile);
			// 创建一个和源文件大小一样的byte[] 
			byte[] b = new byte[is.available()];
			// 读取文件
			is.read();
			is.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally {
			
		}
	}
	
	
}
	