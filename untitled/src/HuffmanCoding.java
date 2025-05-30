import java.util.*;

public class HuffmanCoding {
    private Map<Character, String> codesMap = new HashMap<>();

    public Map<Character, String> getCodes(String text) {



        // -> tu sprawdzamy ile razy dana litera wystapila w tekscie wazne bo
        // w huffmanie dziala to tak to algorytm kompresji
        // bezstratnej, który przypisuje krótsze kody binarne częściej występującym znakom,
        // a dłuższe - rzadszym


        Map<Character, Integer> frequenciesMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequenciesMap.put(c, frequenciesMap.getOrDefault(c, 0) + 1);
        }


        // ->>>>> lista węzłów (do kazdego z osobna przypisywane sa znaki)

        List<HuffmanNode> nodes = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : frequenciesMap.entrySet()) {
            nodes.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }



        // ->>>>>><<<><>>ulalal dopki w liscu jest wiecej niz jeden wezel
        // -> srotujemy, ciecie dwoch najmniejszych
        // -> nowy wezel rodzica -> usuniete jako lewe i prawe dziecko
        // -> dodajemy nowy wezel spowrotem

        while (nodes.size() != 1) {
            nodes.sort(Comparator.comparingInt(n -> n.frequencyOfNodes));
            HuffmanNode leftNodes = nodes.remove(0);
            HuffmanNode rightNodes = nodes.remove(0);
            HuffmanNode parent = new HuffmanNode('\0', leftNodes.frequencyOfNodes + rightNodes.frequencyOfNodes);
            parent.left = leftNodes;
            parent.right = rightNodes;
            nodes.add(parent);
        }

        buildCodesHuffman(nodes.get(0), "");
        return codesMap;
    }

    private void buildCodesHuffman(HuffmanNode node, String code) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            codesMap.put(node.character, code);
            return;
        }
        buildCodesHuffman(node.left, code + "0");
        buildCodesHuffman(node.right, code + "1");
    }
}
