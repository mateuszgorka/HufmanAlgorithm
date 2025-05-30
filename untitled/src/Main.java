import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wpisz tekst do kodowania Huffmana: ");
        String wartWejscia = scanner.nextLine();

        HuffmanCoding huffman = new HuffmanCoding();
        Map<Character, String> codes = huffman.getCodes(wartWejscia);

        System.out.println();
        System.out.println("==========================================");
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.println("'" + entry.getKey() + "' ---->>> " + entry.getValue());
        }
    }
}
