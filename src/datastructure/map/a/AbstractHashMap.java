package datastructure.map.a;

import java.util.ArrayList;
import java.util.Map.*;
import java.util.Random;

public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {
	
	protected int n = 0;
	protected int capacity;
	private int prime;
	private long scale, shift;
	public AbstractHashMap(int cap, int p)
	{
		prime = p;
		capacity = cap;
		Random rand = new Random();
		scale = rand.nextInt(prime-1)+1;
		shift = rand.nextInt(prime);
		creatTable();
	}
	
	public AbstractHashMap(int cap) { this(cap, 109345121); }
	public AbstractHashMap() { this(17); }
	
	protected abstract void creatTable();
	protected abstract V bucketGet(int h, K k);
	protected abstract V bucketPut(int h, K k, V v);
	protected abstract V bucketRemove(int h, K k);
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return n;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return bucketGet(hashValue(key), key);
	}

	@Override
	public V put(K key, V value) {
		V answer = bucketPut(hashValue(key),key,value);
		if (n>capacity/2)
			resize(2*size()-1);
		return answer;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return bucketRemove(hashValue(key),key);
	}
	
	private int hashValue(K key)
	{
		return (int) ((Math.abs(key.hashCode()*scale + shift) % prime) % capacity);
	}
	
	public void resize(int newCap)
	{
		ArrayList<Entry<K,V>> buffer = new ArrayList<>(n);
		for (Entry<K,V> e : entrySet( ))
			buffer.add(e);
		capacity = newCap;
		creatTable( );
		n = 0;
		for (Entry<K,V> e : buffer)
			put(e.getKey(),e.getValue());
	}
	
}
