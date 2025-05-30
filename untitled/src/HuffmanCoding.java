import java.util.*;

public class HuffmanCoding {
    private Map<Character, String> codes = new HashMap<>();

    public Map<Character, String> generateCodes(String text) {



        // -> tu sprawdzamy ile razy dana litera wystapila w tekscie wazne bo
        // w huffmanie dziala to tak to algorytm kompresji
        // bezstratnej, który przypisuje krótsze kody binarne częściej występującym znakom,
        // a dłuższe – rzadszym


        Map<Character, Integer> frequencies = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }


        List<HuffmanNode> nodes = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            nodes.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (nodes.size() > 1) {
            nodes.sort(Comparator.comparingInt(n -> n.frequency));
            HuffmanNode left = nodes.remove(0);
            HuffmanNode right = nodes.remove(0);
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            nodes.add(parent);
        }

        buildCodes(nodes.get(0), "");
        return codes;
    }

    private void buildCodes(HuffmanNode node, String code) {
        if (node == null) return;
        if (node.isLeaf()) {
            codes.put(node.character, code);
            return;
        }
        buildCodes(node.left, code + "0");
        buildCodes(node.right, code + "1");
    }
}
