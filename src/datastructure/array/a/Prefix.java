package datastructure.array.a;

public class Prefix {
	public Prefix(){}
	
	public static double[] prefixAverage1(double[] data)
	{
		int n = data.length;
		double[] a = new double[n];
		for (int i=0;i<n;i++)
		{
			double total = 0;
			for (int j=0;j<=i;j++)
			{
				total += data[j];
			}
			a[i] = total/(i+1);
		}
		return a;
	}
	
	public static double[] prefixAverage2(double[] data)
	{
		int n = data.length;
		double[] a = new double[n];
		double total = 0;
		for (int i=0;i<n;i++)
		{
			total += data[i];
			a[i] = total/(i+1);
		}
		return a;
	}
}
