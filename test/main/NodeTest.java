package main;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class NodeTest {
    @Test
    public void constructAndGetString() {
        final String setString = "hello";
        String getString;

        Node<String> node = new Node<String>(setString, null, null, null);
        getString = node.getData();

        assertEquals(setString, getString);
    }

    @Test
    public void constructAndGetInt() {
        final int setInt = 17;
        int getInt;

        Node<Integer> node = new Node<Integer>(setInt, null, null, null);
        getInt = node.getData();

        assertEquals(setInt, getInt);
    }
}