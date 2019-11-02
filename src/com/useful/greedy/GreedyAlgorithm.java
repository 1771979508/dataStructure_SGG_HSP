package com.useful.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
*	@author 作者 Joker青
*	@version 创建时间：2019年11月2日 下午4:50:19	
*/


/*
 * 
 * 		贪心算法:
 * 			
 * 			下列的广播电台：如何选择最少的广播台
 * 
 * 		广播台 		覆盖地区
 * 		 K1			"北京","上海","天津"
 *       K2			"广州","北京","深圳"
 *       K3			"成都","上海","杭州"
 *       K4			"上海","天津"
 *       K5			"杭州","大连"
 * 
 * */
public class GreedyAlgorithm {

	public static void main(String[] args) {
		
		// 创建广播电台，放入到Map
		HashMap<String,HashSet<String>> broadcasts = new HashMap<String,HashSet<String>>();
		// 将各个电台放入到broadcasts
		HashSet<String> hashSet1 = new HashSet<String>();
		hashSet1.add("北京");
		hashSet1.add("上海");
		hashSet1.add("天津");
		
		HashSet<String> hashSet2 = new HashSet<String>();
		hashSet2.add("广州");
		hashSet2.add("北京");
		hashSet2.add("深圳");
		
		HashSet<String> hashSet3 = new HashSet<String>();
		hashSet3.add("成都");
		hashSet3.add("上海");
		hashSet3.add("杭州");
		
		HashSet<String> hashSet4 = new HashSet<String>();
		hashSet4.add("上海");
		hashSet4.add("天津");
		
		HashSet<String> hashSet5 = new HashSet<String>();
		hashSet5.add("杭州");
		hashSet5.add("大连");
		
		// 然后加入到map中
		broadcasts.put("K1", hashSet1);
		broadcasts.put("K2", hashSet2);
		broadcasts.put("K3", hashSet3);
		broadcasts.put("K4", hashSet4);
		broadcasts.put("K5", hashSet5);
		
		// allAreas 存放所有的地区
		HashSet<String> allAreas = new HashSet<String>();
		allAreas.add("北京");
		allAreas.add("上海");
		allAreas.add("天津");
		allAreas.add("广州");
		allAreas.add("深圳");
		allAreas.add("成都");
		allAreas.add("杭州");
		allAreas.add("大连");
		
		// 创建ArrayList，存放选择的电台集合 - 即 K1、K2等
		ArrayList<String> selects = new ArrayList<String>();
		
		// 定义一个l临时的集合，在遍历过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
		HashSet<String> tempSet = new HashSet<String>();
		
		// 定义给maxKey，保存在一次遍历过程中，能够覆盖最大为覆盖的地区对应的电台的key
		// 如果maxKey 不为 null，则会加入到 selects
		String maxKey = null;
		while(allAreas.size() != 0){  // 如果allAddress 不为0，则表示还没有覆盖到所有的地区
			// 每进行一次while，需要
			maxKey = null;
			// 遍历 broadcases，取出对应的key
			for(String key : broadcasts.keySet()){
				// 每进行一次for
				tempSet.clear();
				// 当前这个key能够覆盖的地区
				HashSet<String> areas = broadcasts.get(key);
				tempSet.addAll(areas);
				// 求出tempSet 和 allAreas 集合的交集，交集会赋给 tempSet
				tempSet.retainAll(allAreas);
				// 如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合地区还多
				// 就需要重置maxKey
				// tempSet.size() > broadcasts.get(maxKey).size() 体现出贪心算法的特点，每次选择最优的
				if(tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())){
					maxKey = key;
				}
			}
			
			// maxKey != null ， 就应该将maxKey 加入 selects
			if(maxKey != null){
				selects.add(maxKey);
				// 将maxKey指向的广播电台覆盖的地区，从allAreas去掉
				allAreas.removeAll(broadcasts.get(maxKey));
			}
		}
		System.out.println(selects);
		
	}
	
}
	