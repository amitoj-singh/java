/* Program to encrypt and decrypt the text using Caesar Cipher*/
import java.util.*;
public class crypto {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int shift, group, key; // shift is the key for encryption while key is used in purpose for decryption
        String str, decrypt;
        System.out.println("Hello, encrypt your text using Caesar Cipher");
        System.out.print("Enter the text to be encrypted: ");
        str = input.nextLine();
        System.out.print("How many characters would you like to shift each character? ");
        shift = input.nextInt();
        System.out.print("In how many groups should the encrypted text be divided? ");
        group = input.nextInt();
        str = encryptString(str, shift, group); //call to function to encrypt the text
        System.out.println("Encrypted text: " + str);
        System.out.println("----------------------------\n");

        System.out.print("Do you want to decrypt? (Y/N) ");
        decrypt = input.next();
        if (decrypt.equals("Y") || decrypt.equals("y")) {
            System.out.print("Enter the encrypted text: ");
            input.nextLine(); // Consume newline left-over
            String encryptedString = input.nextLine();
            System.out.print("What is the encryption key? ");
            key = input.nextInt();
            encryptedString = ungroupify(encryptedString); // normalizing the the encrypted key before decrypting
            String OriginalString = ceaserify(encryptedString, -key); // using ceaserify for decryption be sending -ve key
            System.out.println("Original string is: " + OriginalString);
        }
    }
    // Method to Normalize the text entered by user by removing punctuations and white spaces. returning uppercase String
    public static String normalizeText(String plainText) {
        plainText = plainText.replace("!", "");
        plainText = plainText.replace("(", "");
        plainText = plainText.replace(")", "");
        plainText = plainText.replace(";", "");
        plainText = plainText.replace(".", "");
        plainText = plainText.replace(",", "");
        plainText = plainText.replace("?", "");
        plainText = plainText.replace("/", "");
        plainText = plainText.replace("\\", "");
        plainText = plainText.replace("'", "");
        plainText = plainText.replace("\"", "");
        plainText = plainText.replace(" ", "");
        plainText = plainText.toUpperCase();
        return plainText;
    }
    // Method to cipher the text by shifting each character as per given shift value
    public static String ceaserify(String str, int shift) {
        char newChar, currChar;
        String result = "";
        for (int i = 0; i < str.length(); i++) { // iterating over each character of the string
            currChar = str.charAt(i);
            newChar = (char) (currChar + shift);
            if (newChar > 90) // 90 is the ASCII value for 'Z'
                newChar -= 26;
            else if (newChar < 65) // 65 is the ASCII value for 'A'
                newChar += 26;
            result += newChar; // concatenating the new character in a resulting string
        }
        return result;
    }
    // Method divides the encrypted text to the said number of groups by separating through white spaces
    // if there are less characters in the last group then 'x'[s] will be added to fill the group
    public static String groupify(String str, int group) {
        int completeGroups = str.length() / group ;
        int charsLeft = str.length() - ( completeGroups * group );
        String result = "";
        int beginIndex = 0, endIndex = group;

        for (int i = 0; i < completeGroups; i++){
            result += str.substring(beginIndex, endIndex) + " ";
            beginIndex = endIndex;
            endIndex += group;
        }

        if ( charsLeft != 0 ) {
            int numberOfx = group - charsLeft;
            result += str.substring(beginIndex);
            for (int i = 0; i < numberOfx; i++) {
                result += 'x';
            }
        } else { // (charsLeft == 0) This will remove the truncating space character
            result = result.substring(0, str.length());
        }
        return result;
    }
    // Calls the methods in a proper serial to get return the encrypted text
    public static String encryptString(String str, int shift, int group){
        str = normalizeText(str);
        str = ceaserify(str, shift);
        str = groupify(str, group);
        return str; // returning encrypted string to main method
    }
    // Normalize the encrypted text by removing spaces and 'x'[s] in encryted text
    public static String ungroupify(String str) {
        str = str.replace(" ", "");
        str = str.replace("x", "");
        return str;
    }
}
