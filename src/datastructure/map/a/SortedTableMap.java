package datastructure.map.a;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedMap;

public class SortedTableMap<K, V> extends AbstractSortedMap<K, V> {
	private ArrayList<MapEntry<K,V>> table = new ArrayList<>( ); 
	public SortedTableMap() { super(); }
	public SortedTableMap(Comparator<K> comp) { super(comp);}
	
	private int findIndex(K key, int low, int high)
	{
		if (high < low) return high + 1;
		int mid = (low+high) / 2;
		int comp = compare(key,table.get(mid));
		if (comp == 0)
			return mid;
		else if (comp < 0)
			return findIndex(key, low, mid - 1); 
		else
			return findIndex(key, mid + 1, high);
	}
	private int findIndex(K key) { return findIndex(key, 0, table.size() - 1); }
	public int size( ) { return table.size( ); }
	@SuppressWarnings("unchecked")
	public V get(Object k)
	{
		K key = (K) k;
		int j = findIndex(key);
		if (j == size() || compare(key, table.get(j)) != 0) return null;
		return table.get(j).getValue();
	}
	public V put(K key, V value) {
		int j = findIndex(key);
		if (j < size() && compare(key, table.get(j)) == 0)
			return table.get(j).setValue(value); 
		table.add(j, new MapEntry<K,V>(key,value)); 
		return null;
	}
	public V remove(Object k)
	{
		@SuppressWarnings("unchecked")
		K key = (K) k;
		int i = findIndex(key);
		if (i == size() && compare(key, table.get(i)) != 0)
			return null;
		return table.remove(i).getValue();
	}
	private Entry<K,V> safeEntry(int j)
	{
		if (j<0 || j>=size())
			return null;
		return table.get(j);
	}
	public Entry<K,V> firstEntry() { return safeEntry(0); }
	public Entry<K,V> lastEntry() { return safeEntry(table.size()-1);}
	public Entry<K,V> ceilingEntry(K key)
	{return safeEntry(findIndex(key));}
	public Entry<K,V> floorEntry(K key)
	{
		int j = findIndex(key);
		if (j == size() || ! key.equals(table.get(j).getKey()))
			j --;
		return safeEntry(j);
	}
	private Iterable<Entry<K,V>> snapshot(int startIndex, K stop)
	{
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		int j = startIndex;
		while (j<table.size() && (stop==null||compare(stop,table.get(j))>0))
			buffer.add(table.get(j++));
		return buffer;
	}
	public Set<java.util.Map.Entry<K, V>> entrySet() { return (Set<java.util.Map.Entry<K, V>>) snapshot(0, null); }
	@SuppressWarnings("unchecked")
	public SortedMap<K, V> subMap(K fromKey, K toKey) 
	{ return (SortedMap<K, V>) snapshot(findIndex(fromKey), toKey);}
}
