package datastructure.map.a;

import java.util.Map.Entry;
import java.util.SortedMap;

public class CostPerformanceDatabase {
	SortedMap<Integer,Integer> map = new SortedTableMap<>();
	public CostPerformanceDatabase(){};
	
	public Entry<Integer,Integer> best(int cost)
	{
		return ((SortedTableMap<Integer, Integer>) map).floorEntry(cost);
	}
	public void add(int c, int p)
	{
		Entry<Integer,Integer> other = ((SortedTableMap<Integer, Integer>) map).floorEntry(c);
		if (other!=null&&other.getValue()>=p)
			return;
		map.put(c, p);
		other = ((SortedTableMap<Integer, Integer>) map).ceilingEntry(c);
		while (other != null&&other.getValue() <= p)
		{
			map.remove(other.getKey());
			other = ((SortedTableMap<Integer, Integer>) map).ceilingEntry(c);
		}
	}
}
