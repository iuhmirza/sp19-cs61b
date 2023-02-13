import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MyTrieSet implements TrieSet61B {
    private Node root;

    private class Node {
        public boolean terminator;
        public TreeMap<Character, Node> children;
        public Node() {
            terminator = false;
            children = new TreeMap<>();
        }
    }

    public MyTrieSet() {
        root = new Node();
    }

    public void clear() {
        root = new Node();
    }

    public boolean contains(String key) {
        char[] characterArray = key.toCharArray();
        Node currentNode = root;
        int i = 0;
        while (i < characterArray.length) {
            if (!currentNode.children.containsKey(characterArray[i])) {
                return false;
            }
            currentNode = currentNode.children.get(characterArray[i]);
            i += 1;
        }
        return currentNode.terminator;
    }

    public void add(String key) {
        char[] characterArray = key.toCharArray();
        Node currentNode = root;
        int i = 0;
        while (i < characterArray.length) {
            TreeMap<Character, Node> currentCharMap = currentNode.children;
            char currentChar = characterArray[i];

            if (!currentCharMap.containsKey(currentChar)) {
                currentCharMap.put(currentChar, new Node());
            }
            currentNode = currentCharMap.get(currentChar);
            i += 1;
        }
        currentNode.terminator = true;
    }

    public List<String> keysWithPrefix (String prefix) {
        char[] characterArray = prefix.toCharArray();
        Node currentNode = root;
        int i = 0;
        while (i < characterArray.length) {
            if (!currentNode.children.containsKey(characterArray[i])) {
                return new ArrayList<>();
            }
            currentNode = currentNode.children.get(characterArray[i]);
            i += 1;
        }

        List<String> lst = new ArrayList<>();
        keysWithPrefixOfHelper(prefix, lst, currentNode);
        return lst;

    }

    private static void keysWithPrefixOfHelper(String s, List<String> x, Node N) {
        if (N.terminator) {
            x.add(s);
        }
        for (char c : N.children.keySet()) {
            keysWithPrefixOfHelper(s + c, x, N.children.get(c));
        }
    }

    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

}
