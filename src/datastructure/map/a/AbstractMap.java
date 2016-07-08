package datastructure.map.a;

import java.util.Iterator;
import java.util.Map.Entry;

public abstract class AbstractMap<K, V> implements Map<K, V> {

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
	private class KeyIterator implements Iterator<K>
	{
		private Iterator<Entry<K,V>> entries = entrySet().iterator();
		@Override
		public boolean hasNext() {return entries.hasNext();}
		@Override
		public K next() {
			return entries.next().getKey();
		}
		public void remove() {throw new UnsupportedOperationException( );}
	}
	private class KeyIterable implements Iterable<K>
	{
		public Iterator<K> iterator() { return new KeyIterator(); }
	}
	
	public Iterable<K> keySet( ) { return new KeyIterable( ); }
	
	private class ValueIterator implements Iterator<V>
	{
		private Iterator<Entry<K,V>> entries = entrySet().iterator(); 
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return entries.hasNext();
		}
		@Override
		public V next() {
			// TODO Auto-generated method stub
			return entries.next().getValue();
		}
		public void remove( ) { throw new UnsupportedOperationException( ); }
	}
	private class ValueIterable implements Iterable<V> 
	{
		public Iterator<V> iterator() { return new ValueIterator(); }
	}
	public Iterable<V> values( ) { return new ValueIterable( ); }
}