package com.seir.algos.structure;

import org.junit.jupiter.api.Test;

public class SparseGraphTest {

    @Test
    public void testAddEdge() {
        SparseGraph<String> graph = new SparseGraph<>();
        graph.addEdge(new SparseGraph.GraphVertex<>("A"), new SparseGraph.GraphVertex<>("B"));
        
    }
}
