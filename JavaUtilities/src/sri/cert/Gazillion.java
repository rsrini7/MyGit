package sri.cert;

import java.text.*;

public class Gazillion {
	public static void main(String[] args) throws Exception {
		Number s = 123.456f;
		NumberFormat nf = NumberFormat.getInstance();
		System.out.println(nf.parse(s.toString()));
		nf.setMaximumFractionDigits(2);
		System.out.println(nf.format(s));
	}
}
