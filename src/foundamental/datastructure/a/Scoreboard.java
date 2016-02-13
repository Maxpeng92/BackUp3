package foundamental.datastructure.a;

public class Scoreboard {
	private int numEntries = 0;
	private GameEntry[] board;
	
	public Scoreboard(int capacity)
	{
		board = new GameEntry[capacity];
	}
	
	public void add(GameEntry entr)
	{
		int newScore = entr.getScore();
		if(numEntries<board.length||newScore>board[numEntries-1].getScore())
		{
			if (numEntries < board.length)
			{
				numEntries++;
			}
			int j = numEntries - 1;
			while (j>0&&newScore>board[j-1].getScore())
			{
				board[j] = board[j-1];
				j--;
			}
			board[j] = entr; 
		}
	}
	
	public GameEntry remove(int i) throws IndexOutOfBoundsException
	{
		if (i<0||i>=numEntries)
		{
			throw new IndexOutOfBoundsException("Invalid Index: "+i);
		}
		GameEntry temp = board[i];
		for (int j=i;j<numEntries-1;j++)
		{
			board[j]=board[j+1];
		}
		board[numEntries-1] = null;
		numEntries --;
		return temp;
	}
}
