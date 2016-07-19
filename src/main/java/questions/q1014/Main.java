package questions.q1014;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TrieTree trieTree = new TrieTree();
        Scanner in = new Scanner(System.in);

        int amount = in.nextInt();
        for (int i = 0; i < amount; i++) {
            trieTree.add(in.next());
        }

        int count = in.nextInt();
        for (int i = 0; i < count; i++) {
            System.out.println(trieTree.countPrefix(in.next()));
        }
    }

    public static class TrieTree {

        public TrieNode root = new TrieNode();

        public static class TrieNode {
            public char nodeChar;
            public int freq;
            public TrieNode[] children;
            public TrieNode() {
                freq = 0;
                children = new TrieNode[26];
            }
        }

        public void add(String str) {
            add(root, str);
        }

        public void add(TrieNode trieNode, String str) {
            if (str.length() == 0) {
                return;
            }

            int k = str.charAt(0) - 'a';
            if (trieNode.children[k] == null) {
                trieNode.children[k] = new TrieNode();
                trieNode.children[k].nodeChar = str.charAt(0);
            }
            trieNode.children[k].freq++;

            String subStr = str.substring(1);
            if (subStr.length() != 0) {
                add(trieNode.children[k], subStr);
            }
        }

        public TrieNode searchNode(String str) {
            return searchNode(root, str);
        }

        public TrieNode searchNode(TrieNode trieNode, String str) {
            if (str.length() == 0) {
                return null;
            }

            int k = str.charAt(0) - 'a';
            if (trieNode.children[k] == null) {
                return null;
            }

            String subStr = str.substring(1);
            if (subStr.length() == 0) {
                return trieNode.children[k];
            } else {
                return searchNode(trieNode.children[k], subStr);
            }
        }

        public int countPrefix(String str) {
            TrieNode trieNode = searchNode(str);
            if (trieNode == null) {
                return 0;
            }
            return trieNode.freq;
        }
    }

}
