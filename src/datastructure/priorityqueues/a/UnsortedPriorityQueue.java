package datastructure.priorityqueues.a;

import java.util.Comparator;
import datastructure.positionallist.a.LinkedPositionalList;
import datastructure.positionallist.a.Position;

public class UnsortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

	private LinkedPositionalList<Entry<K,V>> list = new LinkedPositionalList<>();
	public UnsortedPriorityQueue() {super();}
	public UnsortedPriorityQueue(Comparator<K> comp) { super(comp); }
	
	private Position<Entry<K,V>> findMin()
	{
		Position<Entry<K,V>> small = list.first();
		for (Position<Entry<K,V>> walk : list.positions())
			if (compare(walk.getElement(),small.getElement()) < 0)
				small = walk;
		return small;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Entry<K,V> newest = new PQEntry(key, value);
		list.addLast(newest);
		return null;
	}

	@Override
	public Entry<K, V> min() {
		if (list.isEmpty()) return null;
		return findMin().getElement();
	}

	@Override
	public Entry<K, V> removeMin() {
		if (list.isEmpty()) return null;
		return list.remove(findMin());
	}
}
