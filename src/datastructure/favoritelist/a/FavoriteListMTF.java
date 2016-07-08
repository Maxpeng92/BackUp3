package datastructure.favoritelist.a;

import java.util.Iterator;

import datastructure.positionallist.a.LinkedPositionalList;
import datastructure.positionallist.a.Position;
import datastructure.positionallist.a.PositionalList;

public class FavoriteListMTF<E> extends FavoriteList<E> {
	
	protected void moveUp(Position<Item<E>> p)
	{
		if (p!=list.first())
			list.addFirst(list.remove(p));
	}
	
	@SuppressWarnings("unchecked")
	public Iterable<E> getFavorites(int k) throws IllegalArgumentException
	{
		if (k < 0 || k > this.size())
			throw new IllegalArgumentException("Invalid k");
		PositionalList<Item<E>> temp = new LinkedPositionalList<>();
		Iterator<Item<E>> iter = ((LinkedPositionalList<Item<E>>) list).iterator();			
		while (iter.hasNext())
		{
			temp.addLast((datastructure.favoritelist.a.FavoriteList.Item<E>) ((datastructure.favoritelist.a.FavoriteList.Item<E>) iter).getValue());
			iter.next();
		}
		PositionalList<E> result = new LinkedPositionalList<>();
		for (int j=0; j < k; j++) 
		{
			Position<Item<E>> highPos = temp.first( ); 
			Position<Item<E>> walk = temp.after(highPos);
			while (walk!=null)
			{
				if (count(walk) > count(highPos))
					highPos = walk;
				walk = temp.after(walk);
			}
			result.addLast(value(highPos));
			temp.remove(highPos);
		}
		return (Iterable<E>) result;
	}
}
