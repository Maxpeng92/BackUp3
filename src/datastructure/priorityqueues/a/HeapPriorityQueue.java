package datastructure.priorityqueues.a;

import java.util.ArrayList;
import java.util.Comparator;

import datastructure.positionallist.a.PositionalList;

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

	protected ArrayList<Entry<K,V>> heap = new ArrayList<>();
	public HeapPriorityQueue() {super();}
	public HeapPriorityQueue(Comparator<K> comp) { super(comp);}
	public HeapPriorityQueue(K[] keys, V[] values)
	{
		super();
		for (int j=0;j<Math.min(keys.length, values.length);j++)
			heap.add(new PQEntry<>(keys[j],values[j]));
		heapify();
	}
	
	protected int parent(int j) {return (j-1)/2;}
	protected int left(int i) {return i*2 + 1;}
	protected int right(int k) {return k*2 + 2;}
	protected boolean hasLeft(int m) {return left(m) < heap.size();}
	protected boolean hasRight(int n) {return right(n) < heap.size();}
	
	protected void heapify()
	{
		int startIndex = parent(size()-1);
		for (int i=startIndex;i>=0;i--)
			downheap(i);
	}
	
	protected void swap (int i, int j)
	{
		Entry<K,V> temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}
	
	protected void upheap(int j)
	{
		while (j>0)
		{
			int p = parent(j);
			if (compare(heap.get(j),heap.get(p)) >= 0)
				break;
			swap(j,p);
			j = p;
		}
	}
	
	protected void downheap(int i)
	{
		while (hasLeft(i))
		{
			int leftIndex = left(i);
			int smallChildIndex = leftIndex;
			if (hasRight(i))
			{
				int rightIndex = right(i);
				if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
					smallChildIndex = rightIndex;
			}
			if (compare(heap.get(smallChildIndex), heap.get(i)) >= 0)
				break;
			swap (i,smallChildIndex);
			i = smallChildIndex;
		}
	}
 	@Override
	public int size() {
		// TODO Auto-generated method stub
		return heap.size();
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key);
		Entry<K,V> newest = new PQEntry<>(key, value);
		heap.add(newest);
		upheap(heap.size()-1);
		return null;
	}

	@Override
	public Entry<K, V> min() {
		if (heap.isEmpty()) return null;
		return heap.get(0);
	}

	@Override
	public Entry<K, V> removeMin() {
		if (heap.isEmpty()) return null;
		Entry<K,V> answer = heap.get(0);
		swap(0, heap.size()-1);
		heap.remove(heap.size()-1);
		downheap(0);
		return answer;
	}
	
	public static <E> void pqSort (PositionalList<E> S, PriorityQueue<E,?> P)
	{
		int n = S.size();
		for (int i=0;i<n;i++)
		{
			E element = S.remove(S.first());
			P.insert(element, null);
		}
		for (int j=0;j<n;j++)
		{
			E element = P.removeMin().getKey();
			S.addLast(element);
		}
	}
}
