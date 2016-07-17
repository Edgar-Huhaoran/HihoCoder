package com.edgarhu.hihocoder.competition.camp.B;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int high = in.nextInt();
        int line = in.nextInt();

        Tree tree = getTree(high);
        for (int i = 0; i < line; i++) {
            int x1, y1, x2, y2;
            x1 = in.nextInt();
            y1 = in.nextInt();
            x2 = in.nextInt();
            y2 = in.nextInt();
            System.out.println(tree.nodeAmount(x1, x2, y1, y2));
        }
    }

    public static Tree getTree(int high) {
        if (high == 1) {
            Tree tree = new Tree();
            tree.high = 1;
            tree.nodes.add(new Tree.Node(0, 0));
            return tree;
        }

        Tree tree = new Tree();
        tree.high = 2;
        tree.nodes.add(new Tree.Node(0, 0));
        tree.nodes.add(new Tree.Node(2, -2));
        tree.nodes.add(new Tree.Node(2, 2));
        if (high == 2) {
            return tree;
        }

        for (long i = 3, factor = 3; i <= high; i++, factor <<= 1) {
            Tree leftTree = tree.copy().move(factor, factor * -1);
            Tree rightTree = tree.copy().move(factor, factor);

            tree = rightTree;
            tree.merge(leftTree);
            tree.addNode(new Tree.Node(0, 0));
            tree.high = i;
        }
        return tree;
    }

    public static class Tree {

        public List<Node> nodes;
        public long high;

        public Tree() {
            nodes = new ArrayList<Node>();
        }

        public int nodeAmount(long xMin, long xMax, long yMin, long yMax) {
            int count = 0;
            for (Node node : nodes) {
                if (xMin <= node.x && xMax >= node.x &&
                        yMin <= node.y && yMax >= node.y) {
                    count++;
                }
            }
            return count;
        }

        public Tree move(long xVector, long yVector) {
            for (Node node : nodes) {
                node.x += xVector;
                node.y += yVector;
            }
            return this;
        }

        public void merge(Tree tree) {
            nodes.addAll(tree.nodes);
        }

        public void addNode(Node node) {
            nodes.add(node);
        }

        public Tree copy() {
            Tree tree = new Tree();
            for (Node node : nodes) {
                tree.nodes.add(new Node(node.x, node.y));
            }
            return tree;
        }

        public static class Node {
            public Node(long x, long y) {
                this.x = x;
                this.y = y;
            }

            public long x;
            public long y;
        }
    }

}
