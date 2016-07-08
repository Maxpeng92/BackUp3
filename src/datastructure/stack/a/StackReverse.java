package datastructure.stack.a;

import java.util.Arrays;

public class StackReverse {

	public static <E> void reverse(E[] data)
	{
		Stack<E> buffer = new ArrayStack<>(data.length);
		for (int i=0;i<data.length;i++)
			buffer.push(data[i]);
		for (int j=0;j<data.length;j++)
			data[j] = buffer.pop();
	}
	
	public static boolean isMatch(String expression)
	{
		final String opening = "([{";
		final String closing = ")]}";
		Stack<Character> buffer = new LinkedStack<>();
		for (char c : expression.toCharArray())
		{
			if (opening.indexOf(c) != -1)
				buffer.push(c);
			else if (closing.indexOf(c) != -1)
			{
				if (buffer.isEmpty())
					return false;
				if (closing.indexOf(c) != opening.indexOf(buffer.pop()))
					return false;
			}
		}
		return buffer.isEmpty();
	}
	
	public static boolean isHTMLMatched(String html)
	{
		Stack<String> buffer = new LinkedStack<>();
		int j = html.indexOf('<');
		while (j!=-1)
		{
			int k = html.indexOf('<', j+1);
			if (j == -1)
				return false;
			String tag = html.substring(j+1, k);
			if(!tag.startsWith("/"))
				buffer.push(tag);
			else
			{
				if (buffer.isEmpty())
					return false;
				if (!tag.substring(1).equals(buffer.pop()))
					return false;
			}
			j = html.indexOf('<', k+1);
		}
		return buffer.isEmpty();
	}
	
	public static void main(String[] args) {
		Integer[] a = {4, 8, 15, 16, 23, 24};
		String[] s = {"Jack", "Kate", "Hurley", "Jin", "Micheal"};
		System.out.println("a = " + Arrays.toString(a));
		System.out.println("s = " + Arrays.toString(s));
		System.out.println("Reversing ........... ");
		reverse(a);
		reverse(s);
		System.out.println("a = " + Arrays.toString(a));
		System.out.println("s = " + Arrays.toString(s));
	}

}
