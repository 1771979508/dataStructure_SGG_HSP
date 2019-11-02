package com.useful.kmp;

import java.util.Arrays;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年10月31日 下午10:45:27	
*/
public class kmpNext {
	
	public static void main(String[] args) {
		
		String str1 = "BBC ABCDAB ABCDABCDABDE";
		String str2 = "ABCDABD";
		
		int[] next = kmpNext("ABCDABD");
		System.out.println("next= "+Arrays.toString(next));
		
		int index = kmpSearch(str1, str2, next);
		System.out.println("字符串匹配的索引为："+index);
		
	}
	
	// kmp的搜索算法
	public static int kmpSearch(String str1,String str2,int[] next){
		// 遍历
		for(int i=0,j=0;i<str1.length();i++){
			// 需要处理 str1.charAt(i) != str2.charAt(j),去调整j的大小
			// kmp算法核心点，【进行回溯】
			while(j>0 && str1.charAt(i) != str2.charAt(j)){
				j = next[j-1];
			}
			if(str1.charAt(i) == str2.charAt(j)){
				j++;
			}
			// 如果匹配完成，则返回搜索到的索引index值
			if(j == str2.length()){
				return i-j+1;
			}
		}
		// 如果循环完都没有找到的话，则返回-1
		return -1;
	}
	
	
	// 获取到一个字符串(字串)的部分匹配值表
	public static int[] kmpNext(String dest){
		// 创建一个next数组保存部门匹配值
		int[] next = new int[dest.length()];
		next[0] = 0; // 如果字符串是长度为1 部门匹配值j就是0
		for(int i=1,j=0;i<dest.length();i++){
			// 当dest.charAt(i) != dest.charAt(j),我们需要从  next[j-1]获取新的j
			// 直到我们发现有 dest.charAt(i) == dest.charAt(j)成立才退出
			while(j>0 && dest.charAt(i) != dest.charAt(j)){
				// 【KMP算法的核心 - 回溯：若匹配表当前值不满足，则使用匹配表中前面的数据进行匹配】
				j = next[j-1];
			}
			// 当dest.charAt(i) == dest.charAt(j)满足时，部分匹配值就是+1
			if(dest.charAt(i) == dest.charAt(j)){
				j++;
			}
			next[i] = j;
		}
		return next;
	}
	
}
	