package datastructure.map.a;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import datastructure.priorityqueues.a.DefaultComparator;

public abstract class AbstractSortedMap<K, V> implements SortedMap<K,V> {
	protected static class MapEntry<K,V> implements Entry<K,V>
	{
		private K k;
		private V v;
		
		public MapEntry(K key, V value)
		{
			k = key;
			v = value;
		}
		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return k;
		}
		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return v;
		}
		public void setKey(K key)
		{
			k = key;
		}
		@Override
		public V setValue(V value) {
			// TODO Auto-generated method stub
			V old = v;
			v = value;
			return old;
		}
	}
	
	private Comparator<K> comp;
	protected AbstractSortedMap(Comparator<K> c) { comp = c; }
	protected AbstractSortedMap() {this(new DefaultComparator<K>());}
	
	protected int compare(K a, Entry<K,V> b) 
	{return comp.compare(a, b.getKey()); }
	
	public SortedMap<K, V> headMap(K toKey) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SortedMap<K, V> tailMap(K fromKey) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Comparator<? super K> comparator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public K firstKey() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public K lastKey() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public V get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
