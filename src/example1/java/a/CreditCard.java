package example1.java.a;

public class CreditCard {
	private String customer;
	private String bank;
	private String account;
	private int limit;
	protected double balance;
	
	public CreditCard(String customer, String bank, String account, int limit, double initialBal)
	{
		this.customer = customer;
		this.bank = bank;
		this.account = account;
		this.limit = limit;
		this.balance = initialBal;
	}
	
	public CreditCard(String customer, String bank, String account, int limit)
	{
		this(customer, bank, account, limit, 0.0);
	}
	
	public String getCustomer()
	{ return customer;}
	public String getBank()
	{ return bank;}
	public String getAccount()
	{ return account;}
	public int getLimit()
	{ return limit;}
	public Double getBalance()
	{ return balance;}
	
	public boolean charge(double price)
	{
		boolean cflag = false;
		if (price+balance > limit)
		{
			return cflag;
		}
		else
		{
			cflag = true;
			balance += price;
			return cflag;
		}
	}
	
	public void makePayment(double amount)
	{
		balance -= amount;
	}
	
	public static void printSummary(CreditCard card)
	{
		System.out.println("Customer = "+card.customer);
		System.out.println("Bank = "+card.bank);
		System.out.println("Account = "+card.account);
		System.out.println("Balance = "+card.balance);
		System.out.println("Limit = "+card.limit);
	}

}
