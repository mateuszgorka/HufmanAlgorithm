import java.util.*;

public class HuffmanCoding {
    private Map<Character, String> codes = new HashMap<>();

    public Map<Character, String> generateCodes(String text) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        List<HuffmanNode> nodes = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
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
