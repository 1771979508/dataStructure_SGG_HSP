package com.application.Poland;

import java.util.*;
import java.util.ArrayList;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年7月16日 下午9:44:11	
*/


/*
 * 		用栈来实现逆波兰表达式
 * 
 * */

public class PolandNotation {
	
	public static void main(String[] args) throws Exception {
		
		/*一、逆波兰表达式的计算*/
		/*
		// 先定义给逆波兰表达式(后缀表达式) -> 逆波兰表达式转化的结果为什么是这个？
		//中缀表达式： (3+4)*5-6 => 后缀表达式 3 4 + 5 * 6 -
		// 说明，为了方便，逆波兰表达式 的数字和符号使用空格 隔开
		String suffixExpression = "3 4 + 5 * 6 -";
		
		// 思路
			//1.先将"3 4 + 5 * 6 - " => 放到ArrayList中
			//2.将ArrayList 传递给一个方法，遍历ArrayList 配合栈 完成计算
		List<String> rpnList = getListString(suffixExpression);
		System.out.println("rpnList" + rpnList);
		
		// 测试结果
		int res = calculate(rpnList);
		System.out.println("计算出来的结果为："+res);
		*/
		
		/*二、中缀表达式转后缀表达式*/ 
		// (30+4)*5-6 => 30 4 + 5 * 6 - => 164
		// 4 * 5 - 8 + 60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / +
		
		String expression = "1+((2+3)×4)-5";
		List<String> infixExpressionList = toInfixExpressionList(expression);
		System.out.println(infixExpressionList);
		
	}
	
	/*二、将中缀表达式转后缀表达式的方法 - 将中缀表达式转成对应的list*/
	// s="1+((2+3)*4)-5
	public static List<String> toInfixExpressionList(String s){
		// 定义一个List，存放中缀表达式 对应的内容
		List<String> ls = new ArrayList<String>();
		int i = 0;// 这是一个指针，用于遍历 中缀表达式字符串
		String str; // 对多位数的拼接
		char c; // 每遍历一个字符，就放入到c
		do{
			// 如果c是一个非数字，我们需要加入到ls中
			if((c=s.charAt(i))<48 || (c=s.charAt(i)) > 57){
				ls.add(""+c);
				i++; // i需要往后移动 
			}else{  // 如果是一个数，需要考虑多位数
				str = ""; // 先将str 置成""  '0'[48] -> '9'[57]
				while(i<s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57){
					str += c; // 拼接
					i++;
				}
				ls.add(str);
			}
		}while(i<s.length());
		return ls;
	}
	
	
	//将一个逆波兰表达式，依次将数据和运算符 放到ArrayList中
	public static List<String> getListString(String suffixExpression){
		// 将 suffixExpression 分割
		String[] split = suffixExpression.split(" ");
		List<String> list = new ArrayList<>();
		for(String ele : split){
			list.add(ele);
		}
		return list;
	}
	
	// 完成对逆波兰表达式的运算
		/*
		 * 1.从左至右扫描，将3和4压入栈
		 * 2.遇到+运算符，因此弹出4和3(4为栈顶元素，3为次顶元素)，计算3+4的值，再将7入栈
		 * 3.将5入栈
		 * 4.接下来是 * 运算符，因此弹出5和7，计算7*5=35，将35入栈
		 * 5.将6入栈
		 * 6.最后是-运算符，计算出35-6的值，即29，由此得出最终结果
		 * 
		 * */
	public static int calculate(List<String> ls) throws Exception{
		// 创建栈，只需要一个栈即可
		Stack<String> stack = new Stack<String>();
		// 遍历 ls
		for(String item : ls){
			// 这里使用正则表达式取数据
			if(item.matches("\\d+")){ // 匹配的是多位数
				// 入栈
				stack.push(item);
			}else{
				// pop出两个数，并运算，再入栈
				int num2 = Integer.parseInt(stack.pop());
				int num1 = Integer.parseInt(stack.pop());
				int res = 0;
				if(item.equals("+")){
					res = num1 + num2;
				}else if(item.equals("-")){
					res = num1 - num2;
				}else if(item.equals("*")){
					res = num1 * num2;
				}else if(item.equals("/")){
					res = num1 / num2;
				}else{
					throw new RuntimeException("运算符有误");
				}
				// 把res入栈
				stack.push(""+res);
			}
		}
		// 最后留在stack中的数据是运算结果
		return Integer.parseInt(stack.pop().trim());
	}
	
	
	
	
	
}
	