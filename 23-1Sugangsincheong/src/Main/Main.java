package Main;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.util.Scanner;

import Presentation.PInitial;

public class Main {

	private PInitial initial;

	public Main(Scanner scanner) {
		this.initial = new PInitial(scanner);
	}
	
	
	public static String randomPassword() {
		int index = 0;
		char[] charset = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
				'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z' };

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			index = (int) (charset.length * Math.random());
			sb.append(charset[index]);
		}
		return sb.toString();
	}

	public static String SHA256(String password) { // one-way hash functions
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes("UTF-8"));
			String hexString = null;

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				hexString += hex;
			}
			return hexString;

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public void run() throws FileNotFoundException {
		this.initial.show();
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		Main main = new Main(scanner);
		main.run();
		scanner.close();
	}

}
