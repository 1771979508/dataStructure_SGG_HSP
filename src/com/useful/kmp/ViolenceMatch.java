package com.useful.kmp;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年10月30日 下午11:17:34	
*/

/*
 * 
 * 		暴力匹配算法	 -   缺点在于会进行多次无用的回溯，效率不高
 * 
 * 
 * */
public class ViolenceMatch {
	
	public static void main(String[] args) {
		
		// 测试暴力匹配算法
		String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
		String str2 = "尚硅谷你尚硅你";
		int index = violenceMatch(str1, str2);
		System.out.println("index="+index);
		
	}
	
	// 暴力匹配算法实现
	public static int violenceMatch(String str1,String str2){
		char[] c1 = str1.toCharArray();
		char[] c2 = str2.toCharArray();
		
		int c1Len = c1.length;
		int c2Len = c2.length;
		
		int i=0;  // 索引指向 c1
		int j=0;  // 索引指向c2
		
		while(i<c1Len && j<c2Len){
			if(c1[i] == c2[j]){
				i++;
				j++;
			}else{
				i = i-(j-1);
				j=0;
			}
		}
		
		// 判断是否成功
		if(j == c2Len){
			return i-j;
		}else{
			return -1;
		}
		
	}
	
	
	
}
	