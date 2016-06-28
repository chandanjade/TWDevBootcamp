package com.bootcamp.graph;

import org.junit.Assert;
import org.junit.Test;

public class NodeTest {


    @Test
    public void hasName() {
        Node a = new Node("A");
        Assert.assertEquals("A", a.getName());
    }

    @Test
    public void shouldBeReachableToItself() {
        Node a = new Node("A");
        Assert.assertEquals(true, a.canReach(a));
    }

    @Test
    public void shouldNotBeReachableToUnconnectedNode() {
        Node a = new Node("A");
        Node b = new Node("B");
        Assert.assertEquals(false, a.canReach(b));
    }

    @Test
    public void shouldBeReachableToNeighbor() {
        Node a = new Node("A");
        Node b = new Node("B");
        a.addNeighbor(b);
        Assert.assertEquals(true, a.canReach(b));
    }

    @Test
    public void shouldNotBeReachableFromNeighbor() {
        Node a = new Node("A");
        Node b = new Node("B");
        a.addNeighbor(b);
        Assert.assertEquals(false, b.canReach(a));
    }

    @Test
    public void shouldBeReachableToMultipleNeighbors() {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        a.addNeighbor(b);
        a.addNeighbor(c);
        Assert.assertEquals(true, a.canReach(b) && a.canReach(c));
    }

    @Test
    public void shouldBeReachableToDistantNeighbor() {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        a.addNeighbor(b);
        b.addNeighbor(c);
        Assert.assertEquals(true, a.canReach(c));
    }

    @Test
    public void shouldBeReachableToDistantMultipleNeighbors() {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        a.addNeighbor(b);
        b.addNeighbor(c);
        b.addNeighbor(d);
        Assert.assertEquals(true, a.canReach(d) && a.canReach(c));

    }

    @Test
    public void shouldNotBeReachableToSiblings() {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        a.addNeighbor(b);
        b.addNeighbor(c);
        b.addNeighbor(d);
        Assert.assertEquals(false, c.canReach(d));
    }
}