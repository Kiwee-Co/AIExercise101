package exercise.coding.kiwee.ai;

import java.io.File;
import java.io.FileReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class TryCatchExample {

	public static void read() {
		var f = new File("doesnotexist");
		var reader = new FileReader(f);
	}

	public static void network() {
		URL url = new URI("http://example.com").toURL();
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
	}

	public static void parsingInput() {
		String userInput = "123";
		int number = Integer.parseInt(userInput);
		System.out.println(number);
	}
	
	public static void calculate() {
		int a = 5;
		int b = 0;
		int result = a / b; // Potential ArithmeticException
	}
	
	public static void nullPointer() {
		String str = null;
		int length = str.length(); // Potential NullPointerException
	}
	
	public static void numberFormat() {
		int num = Integer.parseInt("abc"); // Potential NumberFormatException
	}

	public static void cast() {
		int x = (int) 10.5; // Potential loss of precision, but not an exception; consider it for logic errors.
	}
	
}
