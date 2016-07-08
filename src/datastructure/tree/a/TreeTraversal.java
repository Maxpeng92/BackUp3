package datastructure.tree.a;

import java.util.ArrayList;

import datastructure.positionallist.a.Position;

public class TreeTraversal {
	public static <E> void printPreorderIndent(Tree<E> T, Position<E> p, int d)
	{
		System.out.println(String.format("%" + 2*d + "s") + p.getElement());
		for (Position<E> c : T.children(p))
			printPreorderIndent(T, c, d+1);
	}
	
	public static <E> void printPreorderLabeled(Tree<E> T, Position<E> p, ArrayList<Integer> path) 
	{
		int d = path.size();
		System.out.println(String.format("%" + 2*d + "s"));
		for (int j=0; j < d; j++) 
			System.out.print(path.get(j) + (j == d-1 ? " " : "."));
		System.out.println(p.getElement( ));
		path.add(1);
		for (Position<E> c : T.children(p))
		{
			printPreorderLabeled(T, c, path);
			path.set(d, 1 + path.get(d));
		}
		path.remove(d);
	}
	
	public static int diskSpace(Tree<Integer> T, Position<Integer> p)
	{
		int subTotal = p.getElement();
		for (Position<Integer> c : T.children(p))
			subTotal += diskSpace(T,c);
		return subTotal;
	}
	
	public static <E> void parenthesize(Tree<E> T, Position<E> p) 
	{
		System.out.print(p.getElement());
		if (T.isInternal(p))
		{
			boolean firstTime = true;
			for (Position<E> c : T.children(p))
			{
				System.out.print((firstTime? " (" : ", "));
				firstTime = false;
				parenthesize(T,c);
			}
			System.out.print(")");
		}
	}
	
}