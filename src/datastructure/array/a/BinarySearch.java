package datastructure.array.a;

import java.io.File;
public class BinarySearch {
	public BinarySearch(){}
	
	public static boolean binarySearch(int[] data, int target, int low, int high)
	{
		if (low > high)
			return false;
		else
		{
			int mid = (low+high)/2;
			if (target == data[mid])
				return true;
			else if (target < data[mid])
				return binarySearch(data,target,low,mid-1);
			else
				return binarySearch(data,target,mid+1,high);
		}
	}
	
	public long diskUsage(File root)
	{
		long total =  root.length();
		if (root.isDirectory())
		{
			for (String childname : root.list())
			{
				File child = new File(root, childname);
				total += diskUsage(child);
			}
		}
		System.out.println(total+"\t"+root);
		return total;
	}
}
