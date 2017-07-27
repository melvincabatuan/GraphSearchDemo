/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.graphsearchdemo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author cobalt mkc
 */
public class UndirectedGraph {

    private int numOfVertices;
    private ArrayList<String> vertices;
    private ArrayList<ArrayList<String>> adjacency;

    private ArrayList<String> visited;

    // Constructor
    public UndirectedGraph(int numOfVertices) {
        this.numOfVertices = numOfVertices;
        vertices = new ArrayList<>();
        adjacency = new ArrayList<>(numOfVertices);
        for (int i = 0; i < numOfVertices; i++) {
            adjacency.add(new ArrayList<>());
        }
        visited = new ArrayList<>();
    }

    public void initializeNodeNames() {
        char alphabet = 'A'; // Default Character nodes
        for (int i = 0; i < numOfVertices; ++i) {
            vertices.add(Character.toString(alphabet++));
        }
        System.out.println("Nodes are: " + vertices);
    }

    public void addEdge(String u, String v) {
        if (vertices.contains(u) && vertices.contains(v)) {
            if (!(adjacency.get(vertices.indexOf(u)).contains(v))) {
                adjacency.get(vertices.indexOf(u)).add(v);
            }
            if (!(adjacency.get(vertices.indexOf(v)).contains(u))) {
                adjacency.get(vertices.indexOf(v)).add(u);
            }
        } else {
            System.out.println("Element not found!");
        }
    }

    public void removeEdge(String u, String v) {
        if (vertices.contains(u) && vertices.contains(v)) {
            adjacency.get(vertices.indexOf(u)).remove(v);
            adjacency.get(vertices.indexOf(v)).remove(u);
        } else {
            System.out.println("Element not found!");
        }
    }

    public void iterativeDFS(String start) {
        visited.clear();
        if (!vertices.contains(start)) {
            System.out.println("Node " + start + " not found.");
            return;
        }

        Stack<String> stack = new Stack<>();
        String current;
        boolean hasAdjacentUnvisited;

        stack.push(start);
        while (!stack.empty()) {
            System.out.println("Stack: " + stack);
            hasAdjacentUnvisited = false;
            current = stack.peek();
            if (!visited.contains(current)) {
                visited.add(current);
            }
            System.out.println("Visited: " + visited);
            for (String element : adjacency.get(vertices.indexOf(current))) {
                if (!visited.contains(element)) {
                    stack.push(element);
                    hasAdjacentUnvisited = true;
                    break;
                }
            }
            if (!hasAdjacentUnvisited) {
                stack.pop();
            }
        }
        System.out.println("The stack is finally empty!\n");
    }

    private void recursiveDFS(String start) {
        visited.add(start);
        for (String element : adjacency.get(vertices.indexOf(start))) {
            if (!visited.contains(element)) {
                recursiveDFS(element);
            }
        }
    }

    public void iterativeBFS(String start) {
        visited.clear();
        if (!vertices.contains(start)) {
            System.out.println("Node " + start + " not found.");
            return;
        }

        Queue<String> queue = new ArrayDeque<>();
        String current;
        boolean hasAdjacentUnvisited;

        queue.add(start);
        while (!queue.isEmpty()) {
            System.out.println("Queue: " + queue);
            hasAdjacentUnvisited = false;
            current = queue.remove();
            if (!visited.contains(current)) {
                visited.add(current);
            }
            System.out.println("Visited: " + visited);
            for (String element : adjacency.get(vertices.indexOf(current))) {
                if (!visited.contains(element)) {
                    visited.add(element);
                    queue.add(element);
                }
            }
        }
        System.out.println("The queue is finally empty!\n");
    }

    public ArrayList<String> getVisitedNodes() {
        return visited;
    }

    public void displayAdjacency() {
        System.out.println("Adjacency list: ");
        for (int i = 0; i < adjacency.size(); ++i) {
            System.out.println(vertices.get(i) + ": " + adjacency.get(i));
        }
    }

    public static void main(String[] args) {

        UndirectedGraph graph = new UndirectedGraph(8);

        graph.initializeNodeNames();

        // Generate graph:
        graph.addEdge("A", "B");
        graph.addEdge("A", "D");
        graph.addEdge("A", "G");
        graph.addEdge("B", "E");
        graph.addEdge("B", "F");
        graph.addEdge("C", "F");
        graph.addEdge("C", "H");
        graph.addEdge("D", "F");
        graph.addEdge("C", "F");
        graph.addEdge("E", "G");
        graph.addEdge("C", "F");

        graph.displayAdjacency();

        // Testing Depth First Search
        graph.recursiveDFS("A");
        System.out.println("Recursive DFS Result: " + graph.getVisitedNodes());

        graph.iterativeDFS("A");
        System.out.println("Iterative DFS Result: " + graph.getVisitedNodes());

        // Testing Breadth First Search
        graph.iterativeBFS("A");
        System.out.println("Iterative BFS Result: " + graph.getVisitedNodes());
    }

}
