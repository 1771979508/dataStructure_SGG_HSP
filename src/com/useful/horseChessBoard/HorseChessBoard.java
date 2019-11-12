package com.useful.horseChessBoard;
/**
*	@author 作者 Joker青
*	@version 创建时间：2019年11月11日 下午10:22:44	
*/

import java.awt.Point;
import java.util.ArrayList;

/**
 * 
 * @author ABC	马踏棋盘 - 根据交点来求得
 *
 */

public class HorseChessBoard {
	
	private static int X; // 棋盘的列数
	private static int Y; // 棋盘的行数
	
	public static void main(String[] args) {
		
		
		
	}
	
	/**
	 * 功能：根据当前位置(Point对象)，计算马儿还能走那些位置(Point)，并放入到一个集合中(ArrayList)，最多有8个位置
	 * @param curPoint
	 * @return
	 */
	public static ArrayList<Point> next(Point curPoint){
		// 创建一个ArrayLisst
		ArrayList<Point> ps = new ArrayList<>();
		// 创建一个Point
		Point p1 = new Point();
		// 表示马儿可以走5这个位置
		if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0){
			ps.add(new Point(p1));
		}
		// 判断马儿可以走6这个位置
		if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0){
			ps.add(new Point(p1));
		}
		// 判断马儿可以走7这个位置
		if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0){
			ps.add(new Point(p1));
		}
		// 判断马儿可以走0这个位置
		if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0){
			ps.add(new Point(p1));
		}
		// 判断马儿可以走1这个位置
		if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y){
			ps.add(new Point(p1));
		}
		// 判断马儿可以走2这个位置
		if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y){
			ps.add(new Point(p1));
		}
		// 判断马儿可以走3这个位置
		if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y){
			ps.add(new Point(p1));
		}
		// 判断马儿可以走4这个位置
		if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y){
			ps.add(new Point(p1));
		}
		return ps;
	}
	
}
	