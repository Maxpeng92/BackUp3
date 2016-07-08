package datastructure.priorityqueues.a;

import java.util.*;
public class StringLengthComparator implements Comparator<String>{

	@Override
	public int compare(String a, String b) {
		if (a.length() < b.length())
			return -1;
		else if (a.length() == b.length())
			return 0;
		else
		return 1;
	}
}
