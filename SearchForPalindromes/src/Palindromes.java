public class Palindromes {

	public static void returnPalyndroms(String str) {
		StringBuilder palindrome = new StringBuilder();
		String lastPalindrome = "";
		int startIndex;
		int lastIndex;
		char ch;

		for (int i = 0; i < str.length(); i++) {
			startIndex = i;
			ch = Character.toLowerCase(str.charAt(i));

			lastIndex = str.lastIndexOf(ch, str.length() - 1);

			while (startIndex < lastIndex) {
				String currentString = str.substring(startIndex, lastIndex + 1);

				for (int j = currentString.length() - 1; j >= 0; j--) {
					palindrome.append(currentString.charAt(j));
				}

				if ((currentString.toLowerCase().equals(palindrome.toString()
						.toLowerCase()))
						&& (!lastPalindrome.toLowerCase().contains(
								currentString.toLowerCase()))) {

					lastPalindrome = currentString;
					System.out.println(currentString);
				}
				palindrome.setLength(0);
				lastIndex = str.lastIndexOf(ch, lastIndex - 1);

			}

		}

	}

	public static void main(String[] args) {
		returnPalyndroms("ThisisAlabalanicaneven");

	}
}
