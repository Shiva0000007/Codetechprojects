package file;
import java.io.*;

public class FileEncryptionDecryption {
    public static void main(String[] args) {
        try {
            // Get user input for key
            int key = getKeyFromUser();

            // Input and output file paths
            String inputFile = "input.txt";
            String encryptedFile = "encrypted.txt";
            String decryptedFile = "decrypted.txt";

            // Encrypt the file
            encryptFile(inputFile, encryptedFile, key);
            System.out.println("File encrypted successfully. Encrypted content saved to: " + encryptedFile);

            // Decrypt the file
            decryptFile(encryptedFile, decryptedFile, key);
            System.out.println("File decrypted successfully. Decrypted content saved to: " + decryptedFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getKeyFromUser() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the key for encryption/decryption: ");
        return Integer.parseInt(reader.readLine());
    }

    private static void encryptFile(String inputFile, String outputFile, int key) throws IOException {
        processFile(inputFile, outputFile, key, true);
    }

    private static void decryptFile(String inputFile, String outputFile, int key) throws IOException {
        processFile(inputFile, outputFile, key, false);
    }

    private static void processFile(String inputFile, String outputFile, int key, boolean encrypt) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            int currentChar;
            while ((currentChar = reader.read()) != -1) {
                char processedChar = processChar((char) currentChar, key, encrypt);
                writer.write(processedChar);
            }
        }
    }

    private static char processChar(char ch, int key, boolean encrypt) {
        if (Character.isLetter(ch)) {
            char base = Character.isUpperCase(ch) ? 'A' : 'a';
            return (char) ((ch - base + (encrypt ? key : -key) + 26) % 26 + base);
        }
        return ch;
    }
}
