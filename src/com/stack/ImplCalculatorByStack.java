package com.stack;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年7月10日 下午11:16:52	
*/
public class ImplCalculatorByStack {
	
	public static void main(String[] args) {
		
		
		
	}
	
}
	



//定义一个ArrayStack biao表示栈
class ArrayStackForCalculator{
	private int maxSize;  // 栈的大小
	private int[] stack;  // 数组，数组模拟栈，数据就放在该数组
	private int top = -1; // top表示栈顶，初始化为-1
	
	// 构造器
	public ArrayStackForCalculator(int maxSize){
		this.maxSize = maxSize;
		stack = new int[this.maxSize];
	}
	
	// 栈满
	public boolean isFull(){
		return top == maxSize - 1;
	}
	
	// 栈空
	public boolean isEmpty(){
		return top == -1;
	}
	
	// 入栈
	public void push(int value){
		// 先判断栈是否满
		if(isFull()){
			System.out.println("栈满");
			return;
		}
		top++;
		stack[top] = value;
	}
	
	// 出栈
	public int pop(){
		// 先判断栈是否为空
		if(isEmpty()){
			// 抛出异常
			throw new RuntimeException("栈空，没有数据~");
		}
		// 取栈顶的元素
		int value = stack[top];
		top--;
		return value;
	}
	
	// 遍历栈中的数据 -> 遍历时，需要从栈顶开始显示数据
	public void list(){
		if(isEmpty()){
			System.out.println("栈空，没有数据~");
			return;
		}
		// 需要从栈顶开始显示数据
		for(int i=top;i>=0;i--){
			System.out.printf("stack[%d]=%d\n",i,stack[i]);
		}
	}
	
	// 返回运算符的优先级，优先级是程序员定，优先级使用数字表示
	// 数字越大，则优先级就越高   设定当前操作符只包含 + - * / 四类
	public int priority(int oper){
		if(oper == '*' || oper == '/'){
			return 1;
		}else if(oper == '+' || oper == '-'){
			return 0;
		}else{
			return -1;
		}
	}
	
	// 判断是不是y一个运算符
	public boolean isOper(char val){
		return val == '+' || val == '-' || val == '*' || val == '/';
	}
	
	// 计算方法
	public int cal(int num1,int num2,int oper){
		int res = 0;
		switch(oper){
			case '+':
				res = num1+num2;
				break;
			case '-':
				res = num1-num2;
				break;
			case '*':
				res = num1*num2;
				break;
			case '/':
				res = num1/num2;
				break;
			default:
				break;
		}
		return res;
	}
	
	
	
	
	
	
	
	
	
	
}