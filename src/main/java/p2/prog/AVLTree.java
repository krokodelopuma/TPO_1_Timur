package p2.prog;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {

    public static class Node {
        public int key;
        public Node left;
        public Node right;
        public Node parent;
        public int height;

        public Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    private Node root;

    // лог
    private final List<String> trace = new ArrayList<>();

    private void log(String s) {
        trace.add(s);
    }

    public List<String> getTrace() {
        return new ArrayList<>(trace);
    }

    public void clearTrace() {
        trace.clear();
    }

    public Node getRoot() {
        return root;
    }

    // вспомогательные

    private int height(Node n) {
        return n == null ? 0 : n.height;
    }

    private int getBalance(Node n) {
        if (n == null) return 0;
        return height(n.left) - height(n.right);
    }

    private void updateHeight(Node n) {
        if (n != null) {
            n.height = Math.max(height(n.left), height(n.right)) + 1;
            log("UPDATE_HEIGHT(" + n.key + ")");
        }
    }

    private Node rightRotate(Node y) {
        log("RIGHT_ROTATE(" + y.key + ")");
        Node x = y.left;
        Node T2 = x.right;

        // Поворот
        x.right = y;
        y.left = T2;

        if (T2 != null) {
            T2.parent = y;
        }

        x.parent = y.parent;
        y.parent = x;

        // обновление высот
        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Node leftRotate(Node x) {
        log("LEFT_ROTATE(" + x.key + ")");
        Node y = x.right;
        Node T2 = y.left;

        // поворот
        y.left = x;
        x.right = T2;

        if (T2 != null) {
            T2.parent = x;
        }

        y.parent = x.parent;
        x.parent = y;

        // обновление высот
        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // вставка

    public void insert(int key) {
        clearTrace();
        log("START_INSERT(" + key + ")");
        root = insertRec(root, null, key);
        log("END_INSERT(" + key + ")");
    }

    private Node insertRec(Node node, Node parent, int key) {
        if (node == null) {
            log("CREATE_NODE(" + key + ")");
            Node newNode = new Node(key);
            newNode.parent = parent;
            return newNode;
        }

        if (key < node.key) {
            log("GO_LEFT(" + node.key + ")");
            node.left = insertRec(node.left, node, key);
        } else if (key > node.key) {
            log("GO_RIGHT(" + node.key + ")");
            node.right = insertRec(node.right, node, key);
        } else {
            // дубликаты не вставляем
            return node;
        }

        updateHeight(node);

        int balance = getBalance(node);
        log("CHECK_BALANCE(" + node.key + ")=" + balance);

        // A
        if (balance > 1 && key < node.left.key) {
            log("A(" + node.key + ")");
            return rightRotate(node);
        }

        // B
        if (balance < -1 && key > node.right.key) {
            log("B(" + node.key + ")");
            return leftRotate(node);
        }

        // C
        if (balance > 1 && key > node.left.key) {
            log("C(" + node.key + ")");
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // D
        if (balance < -1 && key < node.right.key) {
            log("D(" + node.key + ")");
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // поиск

    public Node search(int key) {
        return searchRec(root, key);
    }

    private Node searchRec(Node node, int key) {
        if (node == null || node.key == key) {
            return node;
        }
        if (key < node.key) {
            return searchRec(node.left, key);
        } else {
            return searchRec(node.right, key);
        }
    }

    // обход IN-ORDER

    public List<Node> inOrder() {
        List<Node> res = new ArrayList<>();
        inOrderRec(root, res);
        return res;
    }

    private void inOrderRec(Node node, List<Node> res) {
        if (node == null) return;
        inOrderRec(node.left, res);
        res.add(node);
        inOrderRec(node.right, res);
    }

    // min
/*
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }*/
    private Node maxValueNode(Node node) {
        Node current = node;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }


    // удаление

    public void delete(int key) {
        clearTrace();
        log("START_DELETE(" + key + ")");
        root = deleteRec(root, key);
        log("END_DELETE(" + key + ")");
    }

    private Node deleteRec(Node node, int key) {
        if (node == null) {
            throw new IllegalArgumentException("ненашел");
        }

        if (key < node.key) {
            log("GO_LEFT(" + node.key + ")");
            node.left = deleteRec(node.left, key);
        } else if (key > node.key) {
            log("GO_RIGHT(" + node.key + ")");
            node.right = deleteRec(node.right, key);
        } else {
            // узел найден
            log("DELETE_NODE(" + node.key + ")");
            if (node.left == null || node.right == null) {
                Node temp = (node.left != null) ? node.left : node.right;
                if (temp == null) {
                    node = null;
                } else {
                    temp.parent = node.parent;
                    node = temp;
                }
            } else {
                Node temp = maxValueNode(node.left);
                node.key = temp.key;
                node.left = deleteRec(node.left, temp.key);

                /*
                Node temp = minValueNode(node.right);
                node.key = temp.key;
                node.right = deleteRec(node.right, temp.key);
                */
            }
        }

        if (node == null) {
            return null;
        }

        updateHeight(node);
        int balance = getBalance(node);
        log("CHECK_BALANCE(" + node.key + ")=" + balance);

        // A
        if (balance > 1 && getBalance(node.left) >= 0) {
            log("A(" + node.key + ")");
            return rightRotate(node);
        }

        // C
        if (balance > 1 && getBalance(node.left) < 0) {
            log("C(" + node.key + ")");
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // B
        if (balance < -1 && getBalance(node.right) <= 0) {
            log("B(" + node.key + ")");
            return leftRotate(node);
        }

        // D
        if (balance < -1 && getBalance(node.right) > 0) {
            log("D(" + node.key + ")");
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // подсчет узлов

    public int countNodes() {
        return countNodesRec(root);
    }

    private int countNodesRec(Node node) {
        if (node == null) return 0;
        return 1 + countNodesRec(node.left) + countNodesRec(node.right);
    }

    // проверка правильности АВЛ

    public boolean isValidAVL() {
        return isBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE) && isBalanced(root);
    }

    private boolean isBst(Node node, int min, int max) {
        if (node == null) return true;
        if (node.key < min || node.key > max) return false;
        return isBst(node.left, min, node.key - 1)
                && isBst(node.right, node.key + 1, max);
    }

    private boolean isBalanced(Node node) {
        if (node == null) return true;
        int balance = getBalance(node);
        if (balance < -1 || balance > 1) return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

// печать

    public String toPrettyString() {
        if (root == null) return "<empty>";
        StringBuilder sb = new StringBuilder();
        buildPrettyString(root, "", true, sb);
        return sb.toString();
    }

    private void buildPrettyString(Node node, String prefix, boolean isTail, StringBuilder sb) {
        if (node == null) return;

        sb.append(prefix)
                .append(isTail ? "└── " : "├── ")
                .append(node.key)
                .append("\n");

        List<Node> children = new ArrayList<>();
        if (node.left != null) children.add(node.left);
        if (node.right != null) children.add(node.right);

        for (int i = 0; i < children.size() - 1; i++) {
            buildPrettyString(children.get(i), prefix + (isTail ? "    " : "│   "), false, sb);
        }
        if (children.size() > 0) {
            buildPrettyString(children.get(children.size() - 1), prefix + (isTail ? "    " : "│   "), true, sb);
        }
    }

}
