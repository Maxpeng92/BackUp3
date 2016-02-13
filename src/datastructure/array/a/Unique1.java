package datastructure.array.a;

import java.util.Arrays;

public class Unique1 {
	public Unique1(){}
	
	public static boolean unique1(int[] data)
	{
		int n = data.length;
		for (int i=0;i<n;i++)
			for (int j=i+1;j<n;j++)
			{
				if (data[i] == data[j])
					return false;
			}
		return true;
	}
	
	public static boolean unique2(int[] data)
	{
		int n = data.length;
		int[]  temp = Arrays.copyOf(data,n);
		Arrays.sort(temp);
		for (int i=0;i<n-1;i++)
		{
			if (temp[i] == temp[i+1])
				return false;
		}
		return true;
	}
	
	public static long[] fibonacciGood(int n)
	{
		if (n <= 1)
		{
			long[] answer = {n,0};
			return answer;
		}
		else
		{
			long[] temp = fibonacciGood(n-1);
			long[] answer = {temp[0]+temp[1],temp[0]};
			return answer;
		}
	}
}
