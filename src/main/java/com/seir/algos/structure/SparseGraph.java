package com.seir.algos.structure;

import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

public class SparseGraph<T> {
    public record GraphVertex<T>(T data){}

    public class GraphEdge<T> {
        @Getter
        private final GraphVertex<T> from;
        @Getter
        private final GraphVertex<T> to;
        @Getter @Setter
        private int weight;
        GraphEdge(GraphVertex<T> from, GraphVertex<T> to) {
            this.from = from;
            this.to = to;
            this.weight = 1;
        }
        @Override
        public String toString() {
            return "GraphEdge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GraphEdge<?> graphEdge = (GraphEdge<?>) o;
            return from.equals(graphEdge.from) && to.equals(graphEdge.to) && weight == graphEdge.weight;
        }
        @Override
        public int hashCode() {
            return (from.hashCode() * 31 + to.hashCode())*31 + this.weight;
        }
    }

    private final Set<GraphVertex<T>> vertices;
    private final Set<GraphEdge<T>> edges;

    public SparseGraph() {
        this(new HashSet<>(), new HashSet<>());
    }
    public SparseGraph(Set<GraphVertex<T>> vertices, Set<GraphEdge<T>> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }
    public void addEdge(GraphVertex<T> from, GraphVertex<T> to) {
        if (from == null || to == null) {
            return;
        }
        vertices.add(from);
        vertices.add(to);
        GraphEdge<T> edge = new GraphEdge<>(from, to);
        if (!this.edges.contains(edge)) {
            this.edges.add(edge);
        } else {
            GraphEdge<T> currentEdge = this.edges.stream().filter(e -> e.equals(edge)).findFirst().orElse(null);
            if (currentEdge != null) {
                currentEdge.weight += edge.weight;
            }
        }
    }
}
