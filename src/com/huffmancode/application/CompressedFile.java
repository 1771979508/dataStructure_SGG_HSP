package com.huffmancode.application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.huffmancode.HuffmanCode;

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
		
		/*
		// 压缩完成之后需要自己写解压的方法
		// 需要压缩文件的路径
		String srFile = "d://a.txt";
		// 压缩完成存放文件的路径
		String dstFile = "d://zipFile.zip";
		// 调用压缩文件的方法
		zipFile(srFile,dstFile);
		*/
		
		
		// 测试解压文件
		String zipFile = "d://dst.zip";
		String dstFile = "d://src2.bmp";
		unZipFile(zipFile, dstFile);
		System.out.println("解压成功");
		
		
	}
	
	// 赫夫曼编码
	static Map<Byte,String> huffmanCodes = new HashMap<Byte,String>();
	
	// 编写犯法，将一个文件进行压缩
	/**
	 * 
	 * @param srcFile		你传入的希望压缩的文件的路径
	 * @param dstFile		我们压缩后将压缩文件那个目录
	 * 
	 */
	public static void zipFile(String srcFile,String dstFile){
		//创建文件的输入流
		FileInputStream is = null;
		// 创建文件输出流
		OutputStream os = null;
		// 以对象的形式传输数据
		ObjectOutputStream oos = null;
		try{
			is = new FileInputStream(srcFile);
			// 创建一个和源文件大小一样的byte[] 
			byte[] b = new byte[is.available()];
			// 读取文件
			is.read();
			// 直接对源文件压缩
			byte[] huffmanBytes = huffmanZip(b);
			// 创建文件的输出流,存放压缩文件
			os = new FileOutputStream(dstFile);	
			// 创建一个和文件输出流关联的ObjectOutStream
			oos = new ObjectOutputStream(os);
			// 把赫夫曼编码后的字节数组写入压缩文件
			oos.writeObject(huffmanBytes);
			
			// 这里我们以对象流的方式写入 赫夫曼编码，是为了我们以后恢复源文件时使用
			// 注意一定要把赫夫曼编码写入压缩文件
			oos.writeObject(huffmanCodes);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally {
			try {
				oos.close();
				os.close();
				is.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		}
	}
	
	// 解压缩的方法
	public static void unZipFile(String zipFile,String dstFile){
		// 定义文件输入流
		InputStream is = null;
		// 定义一个对象输入流
		ObjectInputStream ois = null;
		// 定义文件的输出流
		OutputStream os = null;
		try{
			// 创建文件输入流
			is = new FileInputStream(zipFile);
			// 创建一个 和 is关联的对象输入流
			ois = new ObjectInputStream(is);
			// 读取到byte数组 huffmanBytes
			byte[] huffmanBytes = (byte[])ois.readObject();
			// 读取赫夫曼编码表
			Map<Byte,String> huffmanCodes = (Map<Byte, String>)ois.readObject();
			
			// 解码
			byte[] bytes = decode(huffmanCodes,huffmanBytes);
			// 将bytes数组写入到目标文件
			os = new FileOutputStream(dstFile);
			// 写数据到dstFile文件
			os.write(bytes);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally {
			try {
				os.close();
				ois.close();
				is.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	
	// 解码的方法
	private static byte[] decode(Map<Byte, String> huffmanCodes2, byte[] huffmanBytes) {
		// TODO 自动生成的方法存根
		return null;
	}
	
	// 压缩赫夫曼编码
	private static byte[] huffmanZip(byte[] b) {
		// TODO 自动生成的方法存根
		return null;
	}
	
	
}
	