package datastructure.map.a;

import java.util.ArrayList;
import java.util.Map.Entry;

public class ProbeHashMap<K, V> extends AbstractHashMap<K, V> {

	private MapEntry<K,V>[] table;
	private MapEntry<K,V> DEFUNCT = new MapEntry<>(null,null);
	public ProbeHashMap() { super(); }
	public ProbeHashMap(int cap) { super(cap); }
	public ProbeHashMap(int cap, int p) { super(cap, p); }
	
	private boolean isAvailable(int j)
	{
		return (table[j] == null || table[j] == DEFUNCT);
	}
	
	private int findSlot (int h, K k)
	{
		int avail = -1;
		int i = h;
		do
		{
			if (isAvailable(i))
			{
				if (avail == -1) avail = i;
				if (table[i] == null) break;	
			}
			else if(table[i].getKey().equals(k))
				return i;
			i = (i+1) % capacity;
		}while(i != h);
		return -(avail + 1);
	}
	
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K,V>> buffer =  new ArrayList<>();
		for (int h=0;h<capacity;h++)
			if (!isAvailable(h)) buffer.add(table[h]);
		return buffer;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void creatTable() {
		table = (MapEntry<K,V>[]) new MapEntry[capacity];
	}

	@Override
	protected V bucketGet(int h, K k) {
		int i = findSlot(h,k);
		if (i<0) return null;
		return table[i].getValue();
	}

	@Override
	protected V bucketPut(int h, K k, V v) {
		int i = findSlot(h,k);
		if (i>=0)
			return table[i].setValue(v);
		table[-(i+1)] = new MapEntry<>(k,v);
		n++;
		return null;
	}

	@Override
	protected V bucketRemove(int h, K k) {
		int i = findSlot(h,k);
		if (i < 0)  
			return null;
		V answer = table[i].getValue();
		table[i] = DEFUNCT;
		n -- ;
		return answer;
	}

}
