package racingManager.util;

import java.util.*;

public class Util {

	public static <K, V> String[][] HashtableToStringArray(Hashtable<K, V> table) {
		String[][] a = new String[table.size()][];
		Enumeration<K> e = table.keys();

		int i = 0;
		while (e.hasMoreElements()) {
			K key = e.nextElement();
			a[i] = new String[2];
			a[i][0] = key.toString();
			a[i][1] = table.get(key).toString();
			i++;
		}
		return a;
	}
}
