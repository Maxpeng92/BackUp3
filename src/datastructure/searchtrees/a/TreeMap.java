package datastructure.searchtrees.a;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedMap;
import datastructure.map.a.AbstractSortedMap;
import datastructure.positionallist.a.Position;
import datastructure.tree.a.*;

public class TreeMap<K, V> extends AbstractSortedMap<K, V> {

	protected BalanceableBinaryTree<K,V> tree = new BalanceableBinaryTree<>();
	public TreeMap() 
	{ 
		super();
		tree.addRoot(null);
	}
	public TreeMap(Comparator<K> comp)
	{
		super(comp);
		tree.addRoot(null);
	}
	public int size()
	{ return (tree.size()-1)/2;}
	@SuppressWarnings("unused")
	private void expandExternal(Position<Entry<K,V>> p, Entry<K,V> entry)
	{
		tree.set(p, entry);
		tree.addLeft(p, null);
		tree.addRight(p, null);
	}
	protected Position<Entry<K,V>> root( ) { return tree.root( ); }
	
	@SuppressWarnings("unused")
	private Position<Entry<K,V>> treeSearch (Position<Entry<K,V>> p, K key)
	{
		if (tree.isExternal(p))
			return p;
		int comp = compare(key, p.getElement());
		if (comp == 0)
			return p;
		else if (comp < 0)
			return treeSearch(tree.left(p), key);
		else
			return treeSearch(tree.right(p), key);
	}

	@Override
	public SortedMap<K, V> subMap(K fromKey, K toKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
