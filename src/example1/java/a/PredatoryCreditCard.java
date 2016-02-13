package example1.java.a;

public class PredatoryCreditCard extends CreditCard{
	private double apr;
	
	public PredatoryCreditCard(String customer, String bank, String account, int limit, double initialBal, double rate)
	{
		super(customer, bank, account, limit, initialBal);
		this.apr = rate;
	}
	
	public boolean charge(double price)
	{
		boolean isSucess = super.charge(price);
		if (!isSucess)
		{
			this.balance += 5;
		}
		return isSucess;
	}
	
	public void processMonth()
	{
		if (this.balance>0)
		{
			double monthFactor = Math.pow(1+apr, 1.0/12);
			balance = monthFactor;
		}
	}
}
