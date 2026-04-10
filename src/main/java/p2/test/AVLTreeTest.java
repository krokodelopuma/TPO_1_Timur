package p2.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import p2.prog.AVLTree;

import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTest {

    private AVLTree tree;

    @BeforeEach
    public void setUp() {
        tree = new AVLTree();
    }

    @Test
    @DisplayName("Root")
    void testGetRoot() {
        tree.insert(10);
        assertEquals(10, tree.getRoot().key);
    }

    @Test
    @DisplayName("дубликат")
    void testInsertDuplicate() {
        tree.insert(10);
        tree.insert(10);
        assertEquals(1, tree.countNodes());
    }

    @Test
    @DisplayName("T2.parent = y в B при удалении")
    void testRightRotateT2Parent() {
        tree.insert(30);
        tree.insert(10);
        tree.insert(40);
        tree.insert(20);
        tree.delete(40);

        assertTrue(tree.getTrace().stream().anyMatch(s -> s.startsWith("RIGHT_ROTATE")));
    }





    @Test
    @DisplayName("A удаление")
    void testDeleteACase() {
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(10);

        tree.delete(40);

        assertTrue(tree.getTrace().stream().anyMatch(s -> s.startsWith("A(")));
    }

    @Test
    @DisplayName("B удаление")
    void testDeleteBCase() {
        tree.insert(10);
        tree.insert(5);
        tree.insert(20);
        tree.insert(30);

        tree.delete(5);

        assertTrue(tree.getTrace().stream().anyMatch(s -> s.startsWith("B(")));
    }

    @Test
    @DisplayName("C удаление")
    void testDeleteCCase() {
        tree.insert(30);
        tree.insert(10);
        tree.insert(40);
        tree.insert(20);

        tree.delete(40);

        assertTrue(tree.getTrace().stream().anyMatch(s -> s.startsWith("C(")));
    }

    @Test
    @DisplayName("D удаление")
    void testDeleteDCase() {
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);
        tree.insert(20);

        tree.delete(5);

        assertTrue(tree.getTrace().stream().anyMatch(s -> s.startsWith("D(")));
    }







    @Test
    @DisplayName("вставка и обход in order")
    public void te2stInsertAndInOrder() {
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);


        List<AVLTree.Node> nodes = tree.inOrder();
        assertEquals(20, nodes.get(0).key);
        assertEquals(30, nodes.get(1).key);
        assertEquals(40, nodes.get(2).key);
    }


    @Test
    @DisplayName("вставка и обход in order")
    public void testInsertAndInOrder1() {
        tree.insert(30);
        tree.insert(40);

        List<AVLTree.Node> nodes = tree.inOrder();
        assertEquals(30, nodes.get(0).key);
        assertEquals(40, nodes.get(1).key);
    }

    @Test
    @DisplayName("вставка и обход in order")
    public void testInsertAndInOrder2() {
        tree.insert(30);
        tree.insert(20);

        List<AVLTree.Node> nodes = tree.inOrder();
        assertEquals(20, nodes.get(0).key);
        assertEquals(30, nodes.get(1).key);
    }


    @Test
    @DisplayName("поиск узла")
    public void testSearch() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);

        AVLTree.Node found = tree.search(20);
        assertNotNull(found);
        assertEquals(20, found.key);
    }

    @Test
    @DisplayName("поиск несуществующего узла")
    public void testNotSearch() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);

        AVLTree.Node notFound = tree.search(100);
        assertNull(notFound);
    }

    @Test
    @DisplayName("подсчет количества узлов")
    public void testCountNodes() {
        assertEquals(0, tree.countNodes());
        tree.insert(10);
        assertEquals(1, tree.countNodes());
    }

    @Test
    @DisplayName("удаление узла с 0 ребенком")
    public void testDeleteNodeWithNullChild() {
        tree.insert(10);
        tree.insert(5);

        tree.delete(10);
        assertNull(tree.search(10));
        assertTrue(tree.isValidAVL());
    }

    @Test
    @DisplayName("удаление узла с 1 ребенком")
    public void testDeleteNodeWithOneChild() {
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        tree.delete(5);
        assertNull(tree.search(5));
        assertTrue(tree.isValidAVL());
    }

    @Test
    @DisplayName("удаление узла с 2 детьми")
    public void testDeleteNodeWithTwoChildren() {
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);
        tree.insert(15);

        tree.delete(10);
        assertNull(tree.search(10));
        assertTrue(tree.isValidAVL());

        List<AVLTree.Node> nodes = tree.inOrder();
        assertEquals(List.of(5, 15, 20, 30),
                nodes.stream().map(n -> n.key).toList());
    }

    @Test
    @DisplayName("удаление несуществующего узла")
    public void testDeleteNotFound() {
        tree.insert(10);
        tree.insert(20);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> tree.delete(100));
        assertEquals("ненашел", ex.getMessage());
    }

    // точки ll rr  lr rl

    @Test
    @DisplayName("A: вставка 30, 20, 10")
    public void testACaseTrace() {
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);

        List<String> trace = tree.getTrace();
        assertTrue(trace.stream().anyMatch(s -> s.startsWith("A")));
    }

    @Test
    @DisplayName("B: вставка 10, 20, 30")
    public void testBCaseTrace() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);

        List<String> trace = tree.getTrace();
        assertTrue(trace.stream().anyMatch(s -> s.startsWith("B")));
    }

    @Test
    @DisplayName("C: вставка 30, 10, 20")
    public void testCCaseTrace() {
        tree.insert(30);
        tree.insert(10);
        tree.insert(20);

        List<String> trace = tree.getTrace();
        assertTrue(trace.stream().anyMatch(s -> s.startsWith("C")));
    }

    @Test
    @DisplayName("D: вставка 10, 30, 20")
    public void testDCaseTrace() {
        tree.insert(10);
        tree.insert(30);
        tree.insert(20);

        List<String> trace = tree.getTrace();
        assertTrue(trace.stream().anyMatch(s -> s.startsWith("D")));
    }
}

