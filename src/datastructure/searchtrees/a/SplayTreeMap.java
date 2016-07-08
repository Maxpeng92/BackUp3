package datastructure.searchtrees.a;

import java.util.Comparator;

import datastructure.positionallist.a.Position;

public class SplayTreeMap<K, V> extends TreeMap<K, V> {
	public SplayTreeMap() { super(); }
	public SplayTreeMap(Comparator<K> comp) { super(comp); }
	
	private void splay (Position<Entry<K,V>> p)
	{
		while (!tree.isRoot(p))
		{
			Position<Entry<K,V>> parent = tree.parent(p);
			Position<Entry<K,V>> grand = tree.parent(parent);
			if (grand == null)
				tree.rotate(p);
			else if((p == tree.left(parent)) == (parent == tree.left(grand)))
			{
				tree.rotate (parent);
				tree.rotate (p);
			}
			else
			{
				tree.rotate(p);
				tree.rotate(p);
			}
		}
	}
	protected void rebalanceAccess(Position<Entry<K,V>> p)
	{
		if (tree.isExternal(p))
			p = tree.parent(p);
		if (p!=null)
			splay(p);
	}
	protected void rebalanceInsert(Position<Entry<K,V>> p)
	{
		splay(p);
	}
	protected void rebalanceDelete(Position<Entry<K,V>> p) 
	{
		if (!tree.isRoot(p)) 
			splay(tree.parent(p)); 
	}
}
