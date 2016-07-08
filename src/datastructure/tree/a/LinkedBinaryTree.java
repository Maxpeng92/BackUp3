package datastructure.tree.a;

import java.util.*;

import datastructure.positionallist.a.Position;
import datastructure.queue.a.LinkedQueue;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
	public static class Node<E> implements Position<E>
	{
		private E element;
		private Node<E> parent;
		private Node<E> left;
		private Node<E> right;
		public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild)
		{
			this.element = e;
			this.parent = above;
			this.left = leftChild;
			this.right = rightChild;
		}
		
		public E getElement() throws IllegalStateException {
			return element;
		}
		public Node<E> getParent() {return parent;}
		public Node<E> getLeft() { return left; }
		public Node<E> getRight() { return right; }
		
		public void setElement(E e) { element = e; }
		public void setParent(Node<E> parentNode) { parent = parentNode; } public void setLeft(Node<E> leftChild) { left = leftChild; }
		public void setRight(Node<E> rightChild) { right = rightChild; }
	}
	
	private class ElementIterator implements Iterator<E>
	{
		Iterator<Position<E>> posIterator = positions().iterator();
		public boolean hasNext()
		{
			return posIterator.hasNext();
		}
		public E next()
		{
			return posIterator.next().getElement();
		}
		public void remove()
		{
			posIterator.remove();
		}
	}
	
	public Iterator<E> iterator()
	{
		return new ElementIterator();
	}
	
	private void preorderSubtree(Position<E> p, List<Position<E>> snapshot)
	{
		snapshot.add(p);
		for (Position<E> c : children(p))
			preorderSubtree(c,snapshot);
	}
	
	public Iterable<Position<E>> preorder()
	{
		List<Position<E>> snapshot = new ArrayList<>();
		if (!isEmpty())
			preorderSubtree(root(),snapshot);
		return snapshot;
	}
	
	private void postorderSubtree(Position<E> p, List<Position<E>> snapshot)
	{
		for (Position<E> c : children(p))
			postorderSubtree(c, snapshot);
		snapshot.add(p);
	}
	
	public Iterable<Position<E>> postorder()
	{
		List<Position<E>> snapshot = new ArrayList<>();
		if (!isEmpty())
			postorderSubtree(root(), snapshot);
		return snapshot;
	}
	
	public Iterable<Position<E>> breadthfirst()
	{
		List<Position<E>> snapshot = new ArrayList<>();
		if (!isEmpty())
		{
			LinkedQueue<Position<E>> fringe = new LinkedQueue<>();
			fringe.enqueue(root());
			while(!fringe.isEmpty())
			{
				Position<E> p = fringe.dequeue();
				snapshot.add(p);
				for (Position<E> c : children(p))
					fringe.enqueue(c);
			}
		}
		return snapshot;
	}
	
	private void inorderSubtree (Position<E> p, List<Position<E>> snapshot)
	{
		if (left(p)!=null)
			inorderSubtree(left(p), snapshot);
		snapshot.add(p);
		if (right(p)!=null)
			inorderSubtree(right(p), snapshot);
	}
	
	public Iterable<Position<E>> inorder()
	{
		List<Position<E>> snapshot = new ArrayList<>();
		if (!isEmpty())
			inorderSubtree(root(), snapshot);
		return snapshot;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right)
	{
		return new Node(e, parent, left, right);
	}
	
	@Override
	public Iterable<Position<E>> positions() 
	{
		return inorder(); 
	}
	
	protected Node<E> root = null;
	private int size = 0;
	
	public LinkedBinaryTree(){}
	
	protected Node<E> validate(Position<E> p) throws IllegalArgumentException
	{
		if (!(p instanceof Node))
			throw new IllegalArgumentException("Not valid position type");
		Node<E> node = (Node<E>) p;
		if (node.getParent() == node)
			throw new IllegalArgumentException("p is no longer in the tree");
		return node;
	}
	
	public int size()
	{
		return size;
	}
	public Position<E> root()
	{
		return root;
	}
	public Position<E> parent(Position<E> p) throws IllegalArgumentException
	{
		Node<E> node = validate(p);
		return node.getParent();
	}
	public Position<E> left(Position<E> p) throws IllegalArgumentException 
	{
		Node<E> node = validate(p);
		return node.getLeft(); 
	}
	public Position<E> right(Position<E> p) throws IllegalArgumentException 
	{
		Node<E> node = validate(p);
		return node.getRight(); 
	}
	public boolean isExternal(Position<E> p) throws IllegalArgumentException
	{
		Node<E> node = validate(p);
		return (node.getLeft() == null && node.getRight() == null);
	}
	public boolean isInternal(Position<E> p) throws IllegalArgumentException
	{
		Node<E> node = validate(p);
		return !isExternal(node);
	}
	public Position<E> addRoot(E e) throws IllegalStateException 
	{
		if (!isEmpty()) 
			throw new IllegalStateException("Tree is not empty"); 
		root = createNode(e, null, null, null);
		size = 1;
		return root;
	}
	public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException
	{
		Node<E> parent = validate(p);
		if(parent.getLeft() != null)
			throw new IllegalArgumentException("p already has a left child");
		Node<E> child = createNode(e, parent, null, null);
		parent.setLeft(child);
		size++;
		return child;
	}
	public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException 
	{ 
		Node<E> parent = validate(p);
			if (parent.getRight() != null)
			throw new IllegalArgumentException("p already has a right child");
			Node<E> child = createNode(e, parent, null, null); parent.setRight(child);
			size++;
			return child;
	}
	public E set(Position<E> p, E e) throws IllegalArgumentException
	{
		Node<E> node = validate(p);
		E temp = node.getElement();
		node.setElement(e);
		return temp;
	}
	public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException
	{
		Node<E> node = validate(p);
		if(isInternal(p)) 
			throw new IllegalArgumentException("p must be a leaf");
		this.size += t1.size() + t2.size(); 
		if(!t1.isEmpty())
		{
			t1.root.setParent(node);
			node.setLeft(t1.root);
			t1.root = null;
			t1.size = 0;
		}
		if(!t2.isEmpty())
		{
			t2.root.setParent(node);
			node.setRight(t2.root);
			t2.root = null;
			t2.size = 0;
		}
	}
	public E remove(Position<E> p) throws IllegalArgumentException
	{
		Node<E> node = validate(p);
		if (numChildren(p) == 2)
			throw new IllegalArgumentException("p has two children");
		Node<E> child = (node.getLeft() != null? node.getLeft():node.getRight());
		if (child != null)
			child.setParent(node.getParent());
		if (node == root)
			root = child;
		else 
		{
			Node<E> parent = node.getParent();
			if (node == parent.getLeft())
				parent.setLeft(child);
			else
				parent.setRight(child);
		}
		size--;
		E temp = node.getElement();
		node.setElement(null);
		node.setLeft(null);
		node.setRight(null);
		node.setParent(node);
		return temp;
	}
	
}
