package pset2;


import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Test3 {
	public static void main(String args[]) {
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		set.add(2);
		set.add(3);
		SortedSet<Integer> sSet = new TreeSet<Integer>();
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("Hello", 5);
		map.put("World", 5);
		map.put("What", 4);
		
		sSet.add(3);
		sSet.add(1);
		sSet.add(9);
		Integer[] hi = new Integer[3];
		hi[0] = 1;
		hi[1] = 2;
		hi[2] = 3;
		if (!set.add(4)) {
			System.out.println("FALSE");
		}
		for (int i: map.values()) {
			
			System.out.println(i);
		}
	}
}
