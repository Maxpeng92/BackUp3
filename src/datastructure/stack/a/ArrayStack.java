package datastructure.stack.a;

public class ArrayStack<E> implements Stack<E> {
	public static final int CAPACITY = 1000;
	private E[] data;
	private int t = -1;
	
	@SuppressWarnings("unchecked")
	public ArrayStack(int capacity)
	{
		data = (E[]) new Object[capacity];
	}
	public ArrayStack()
	{
		this(CAPACITY);
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return t+1;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (t == -1);
	}

	@Override
	public void push(E e) throws IllegalStateException{
		// TODO Auto-generated method stub
		if (this.size() == data.length)
			throw new IllegalStateException("Stack is full!");
		data[++t] = e;
	}

	@Override
	public E top() {
		// TODO Auto-generated method stub
		if (this.isEmpty())
			return null;
		return data[t];
	}

	@Override
	public E pop() {
		// TODO Auto-generated method stub
		if (this.isEmpty())
			return null;
		E answer = data[t];
		data[t] = null;
		t--;
		return answer;
	}

}
