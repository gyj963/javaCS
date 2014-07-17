package ztb;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	public static boolean verifyEmail(String input) {
		boolean ck = false;
		String regex = "\\w+@\\w+(\\.\\w+)+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.matches()) {
			ck = true;
		}
		return ck;
	}
}
