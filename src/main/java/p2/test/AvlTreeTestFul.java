package p2.test;


import org.junit.jupiter.api.Test;
import p2.prog.AVLTree;

public class AvlTreeTestFul {

    @Test
    public void testPrettyPrintAfterOperations() {
        AVLTree tree = new AVLTree();

        for (int i = 1; i <= 7; i++) {
            tree.insert(i);
        }

        tree.delete(4);
        tree.insert(4);


        System.out.println(tree.toPrettyString());
    }

    @Test
    public void testPrettyPrintAfterOperations2() {
        AVLTree tree = new AVLTree();

        for (int i = 7; i >= 1; i--) {
            tree.insert(i);
        }

        tree.delete(4);
        tree.insert(4);


        System.out.println(tree.toPrettyString());
    }
}
