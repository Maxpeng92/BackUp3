package datastructure.tree.a;

import java.util.Iterator;

import datastructure.list.a.ArrayList;
import datastructure.list.a.List;
import datastructure.positionallist.a.Position;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

	@Override
	public Position<E> root() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		List<Position<E>> snapshot = new ArrayList<>(2);
		if (left(p) != null)
			snapshot.add(0,left(p));
		if (right(p) != null)
			snapshot.add(1, right(p));
		return (Iterable<Position<E>>) snapshot;
	}

	@Override
	public int numChildren(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		int count = 0;
		if (left(p) != null)
			count ++;
		if (right(p) != null)
			count ++;
		return count;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Position<E> parent = parent(p);
		if (parent==null)
			return null;
		if (p==left(parent))
			return right(parent);
		else
			return left(parent);
	}

}
