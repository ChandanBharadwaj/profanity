package com.sai;

import java.util.*;

public class AhoCorasickSearch {
    class Node {
        Map<Character, Node> children = new HashMap<>();
        Node failure;
        Set<String> output = new HashSet<>();
    }

    private Node root = new Node();

    public AhoCorasickSearch(List<String> keywords) {
        buildTrie(root, keywords);
        buildAutomaton(root);
    }

    public void buildTrie(Node root, List<String> keywords) {
        for (String keyword : keywords) {
            Node current = root;
            for (char c : keyword.toCharArray()) {
                current = current.children.computeIfAbsent(c, k -> new Node());
            }
            current.output.add(keyword);
        }
    }

    public void buildAutomaton(Node root) {
        Queue<Node> queue = new LinkedList<>();
        for (Node node : root.children.values()) {
            queue.add(node);
            node.failure = root;
        }

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (Map.Entry<Character, Node> entry : current.children.entrySet()) {
                char key = entry.getKey();
                Node child = entry.getValue();
                queue.add(child);

                Node failure = current.failure;
                while (failure != null && !failure.children.containsKey(key)) {
                    failure = failure.failure;
                }
                child.failure = failure != null ? failure.children.get(key) : root;
                child.output.addAll(child.failure.output);
            }
        }
    }

    public List<String> searchPatterns(String text) {
        List<String> matchedTexts = new ArrayList<>();
        Node current = root;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            while (current != null && !current.children.containsKey(c)) {
                current = current.failure;
            }
            if (current == null) {
                current = root;
                continue;
            }
            current = current.children.get(c);
            for (String pattern : current.output) {
                matchedTexts.add(text.substring(i - pattern.length() + 1, i + 1));
            }
        }
        return matchedTexts;
    }
}
