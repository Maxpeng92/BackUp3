package example1.java.a;

public class TestCreditCard {

	public static void main(String[] args) 
	{
		CreditCard[] wallet = new CreditCard[3];
		wallet[0] = new CreditCard("Chen Tan", "Chemical Bank", "1888 8888 8888 8888", 5000);
		wallet[1] = new CreditCard("Max Peng", "Chemical Bank", "8888 8888 8888 8888", 3500);
		
		for (int val=1;val<16;val++)
		{
			wallet[0].charge(3*val);
			wallet[1].charge(2*val);
		}
		
		for (CreditCard id : wallet)
		{
			CreditCard.printSummary(id);
			while (id.getBalance()>=200.0)
			{
				id.makePayment(200);
				System.out.println("New balance: "+id.getBalance());
			}
		}
	}

}
