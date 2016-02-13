package datastructure.array.a;

public class CaesarCipher {
	protected char[] encoder = new char[26];
	protected char[] decoder = new char[26];
	
	public CaesarCipher(int rotation)
	{
		for (int i=0;i<26;i++)
		{
			encoder[i] = (char) ('A'+(i+rotation)%26);
			decoder[i] = (char) ('A'+(i-rotation+26)%26);
		}
	}
	
	public String encrypt(String message)
	{
		return transform(message, encoder);
	}
	public String decrypt(String secret)
	{
		return transform(secret, decoder);
	}
	
	private String transform(String orig, char[] code)
	{
		char[] msg = orig.toCharArray();
		for (int j=0;j<msg.length;j++)
		{
			if (Character.isUpperCase(msg[j]))
			{
				int d = msg[j]-'A';
				msg[j] = code[d];
			}
		}
		return new String(msg);
	}
	public static void main(String[] args) {
		CaesarCipher cipher = new CaesarCipher(3);
		System.out.println("Encrpytion code = "+new String(cipher.encoder));
		System.out.println("Decrpytion code = "+new String(cipher.decoder));
		String message = "THE EAGLE IS IN PLAY; MEET AT JOE'S";
		String coded = cipher.encrypt(message);
		System.out.println("Secret : "+coded);
		String answer = cipher.decrypt(coded);
		System.out.println("Message : "+answer);
	}

}
