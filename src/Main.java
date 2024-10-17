import java.util.Scanner;

public class Main {

   
    public static String encryptEnglish(String message, int key) {
        final String alphabetEng = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        final int ALPHABET_LENGTH = alphabetEng.length();
        StringBuilder encryptedText = new StringBuilder();

        for (char c : message.toCharArray()) {
            int index = alphabetEng.indexOf(c);
            if (index != -1) {
                int newIndex = (index + key) % ALPHABET_LENGTH;
                if (newIndex < 0) {
                    newIndex += ALPHABET_LENGTH; 
                }
                encryptedText.append(alphabetEng.charAt(newIndex));
            } else {
                encryptedText.append(c);
            }
        }
        return encryptedText.toString();
    }

    
    public static String decryptEnglish(String message, int key) {
        final String alphabetEng = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        final int ALPHABET_LENGTH = alphabetEng.length();
        return encryptEnglish(message, ALPHABET_LENGTH - (key % ALPHABET_LENGTH));
    }

        public static StringBuilder encryptRussian(String message, int key) {
        String CapitalLetter = "АБВГДЕЁЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        String SmallLetter = "абвгдеёжзиклмнопрстуфхцчшщъыьэя";
        StringBuilder result = new StringBuilder();

        
        for (int i = 0; i < message.length(); i++) {
            char m = message.charAt(i);

        
            if (CapitalLetter.indexOf(m) != -1) {
                int index = (CapitalLetter.indexOf(m) + key) % 33;
                if (index < 0) {
                    index += 33; 
                }
                result.append(CapitalLetter.charAt(index));
            }
            else if (SmallLetter.indexOf(m) != -1) {
                int index = (SmallLetter.indexOf(m) + key) % 33;
                if (index < 0) {
                    index += 33; 
                }
                result.append(SmallLetter.charAt(index));
            }
            else {
                result.append(m);
            }
        }
        return result;
    }

    public static void hackingTheEnglishAlphabet(String encryptedMessage) {
        for (int key = 0; key < 26; key++) {
            String decryptedMessage = decryptEnglish(encryptedMessage, 26 - key);
            System.out.println("Ключ: " + key + " - Сообщение: " + decryptedMessage);
        }
    }

    public static void hackingTheRussianAlphabet(String encryptedMessage) {
        for (int key = 0; key < 33; key++) {
            StringBuilder decryptedMessage = encryptRussian(encryptedMessage, 33 - key);
            System.out.println("Ключ: " + key + " - Сообщение: " + decryptedMessage);
        }
    }

    public static void main(String[] args) {
        String CapitalLetter = "АБВГДЕЁЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        String SmallLetter = "абвгдеёжзиклмнопрстуфхцчшщъыьэя";

        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Введите строку для шифрования:");
        String inputString = inputScanner.nextLine();

        System.out.println("Введите ключ (сдвиг):");
        int cipherKey = inputScanner.nextInt();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < inputString.length(); i++) {
            char m = inputString.charAt(i);

            if (CapitalLetter.indexOf(m) != -1) {
                int index = (CapitalLetter.indexOf(m) + cipherKey) % 33;
                if (index < 0) {
                    index += 33; 
                }
                result.append(CapitalLetter.charAt(index));
            }
            else if (SmallLetter.indexOf(m) != -1) {
                int index = (SmallLetter.indexOf(m) + cipherKey) % 33;
                if (index < 0) {
                    index += 33; 
                }
                result.append(SmallLetter.charAt(index));
            }
            else if (Character.isLetter(m) && "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".indexOf(m) != -1) {
                result.append(encryptEnglish(Character.toString(m), cipherKey));
            }
            else {
                result.append(m);
            }
        }

        System.out.println("Зашифрованная строка: " + result.toString());

        inputScanner.nextLine(); 
        System.out.println("Хотите попробовать взломать зашифрованное сообщение? (да/нет):");
        String response = inputScanner.nextLine();

        if (response.equalsIgnoreCase("да")) {
            System.out.println("Какой алфавит был использован? (русский/английский):");
            String alphabetChoice = inputScanner.nextLine();

            if (alphabetChoice.equalsIgnoreCase("английский")) {
                hackingTheEnglishAlphabet(result.toString());
            } else if (alphabetChoice.equalsIgnoreCase("русский")) {
                hackingTheRussianAlphabet(result.toString());
            } else {
                System.out.println("Неправильный выбор алфавита.");
            }
        }
    }
}
