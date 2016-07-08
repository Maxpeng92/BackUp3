package datastructure.positionallist.a;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedPositionalList<E> implements PositionalList<E> {

	private Node<E> header; 
	private Node<E> trailer; 
	private int size = 0;
	
	public LinkedPositionalList( ) 
	{
		header = new Node<>(null, null, null); 
		trailer = new Node<>(null, header, null); 
		header.setNext(trailer);
	}
	
	private static class Node<E> implements Position<E>
	{
		private E element;
		private Node<E> prev;
		private Node<E> next;
		public Node (E e, Node<E> prev, Node<E> next)
		{
			this.element = e; 
			this.prev = prev; 
			this.next = next;
		}
		@Override
		public E getElement() throws IllegalStateException {
			// TODO Auto-generated method stub
			if (next == null)
				return null;
			return element;
		}
		public Node<E> getPrev() 
		{ 
			return prev;
		}
		public Node<E> getNext() 
		{
			return next; 
		}
		public void setElement(E e) { 
			element = e;
		}
		public void setPrev(Node<E> p) {
			prev = p;
		}
		public void setNext(Node<E> n) {
			next = n;
		}
	}
	
	private class PositionIterator implements Iterator<Position<E>>
	{
		private Position<E> cursor = first();
		private Position<E> recent = null;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (cursor!=null);
		}

		@Override
		public Position<E> next() throws NoSuchElementException {
			// TODO Auto-generated method stub
			if (cursor == null) 
				throw new NoSuchElementException("nothing left");
			recent = cursor;
			cursor = after(cursor);
			return recent;
		}
		
		public void remove( ) throws IllegalStateException {
			if (recent == null) 
				throw new IllegalStateException("nothing to remove"); 
			LinkedPositionalList.this.remove(recent);
			recent = null;
		}
	}
	
	private class PositionIterable implements Iterable<Position<E>> 
	{
		public Iterator<Position<E>> iterator() 
		{ 
			return new PositionIterator(); 
		}
	} 
	
	public Iterable<Position<E>> positions( ) 
	{
		return new PositionIterable();  
	}
	
	private class ElementIterator implements Iterator<E> 
	{
		Iterator<Position<E>> posIterator = new PositionIterator( );
		public boolean hasNext( ) 
		{ 
			return posIterator.hasNext( ); 
		}
		public E next( )
		{ 
			return posIterator.next( ).getElement( ); 
		}  
		public void remove( ) 
		{ 
			posIterator.remove( ); 
		}
	}
	
	public Iterator<E> iterator( ) 
	{ 
		return new ElementIterator( ); 
	}

	private Node<E> validate(Position<E> p) throws IllegalArgumentException 
	{
		if (!(p instanceof Node)) 
			throw new IllegalArgumentException("Invalid p");
		Node<E> node = (Node<E>) p; 
		if (node.getNext() == null)
			throw new IllegalArgumentException("p is no longer in the list"); 
		return node;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public Position<E> first() {
		// TODO Auto-generated method stub
		return header.getNext();
	}

	@Override
	public Position<E> last() {
		// TODO Auto-generated method stub
		return trailer.getPrev();
	}

	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		return node.getPrev();
	}

	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		return node.getNext();
	}

	private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
		Node<E> newest = new Node<>(e, pred, succ); 
		pred.setNext(newest);
		succ.setPrev(newest);
		size++;
		return newest; 
	}
	
	@Override
	public Position<E> addFirst(E e) {
		// TODO Auto-generated method stub
		return addBetween(e, header, header.getNext());
	}

	@Override
	public Position<E> addLast(E e) {
		// TODO Auto-generated method stub
		return addBetween(e, trailer.getPrev(), trailer);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		return addBetween(e, node.getPrev(), node);
	}

	@Override
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		return addBetween(e, node, node.getNext());
	}

	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node = validate(p); 
		E answer = node.getElement(); 
		node.setElement(e);
		return answer;
	}

	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		Node<E> prevNode = node.getPrev();
		Node<E> succNode = node.getNext();
		prevNode.setNext(succNode);
		succNode.setPrev(prevNode);
		E answer = node.getElement();
		node.setPrev(null);
		node.setNext(null);
		node.setElement(null);
		size --;
		return answer;
	}
	
	public static void insertionSort(PositionalList<Integer> list) 
	{
		Position<Integer> marker = list.first();
		while (marker != list.last())
		{
			Position<Integer> pivot = list.after(marker);
			int value = pivot.getElement();
			if (value > marker.getElement())
				marker = pivot;
			else 
			{
				Position<Integer> walk = marker;
				while (walk != list.first() && list.before(walk).getElement() > value)
					walk = list.before(walk);
				list.remove(pivot);
				list.addBefore(walk, value);
			}
		}
	}

}
