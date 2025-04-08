/*
 * TC: O(n) where n is the number of nodes in the graph
 * SC: O(n) for the recursion stack and the visited map
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph {
    // Definition for a Node.
    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }
    }

    Map<Node, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        // if already visited, get clone from map
        if(visited.containsKey(node)) {
            return visited.get(node);
        }

        // create new clone
        Node clone = new Node(node.val);
        visited.put(node, clone);

        // for each neighbour, create their clone
        // and add to this clone's neighbours
        for(Node nnode: node.neighbors) {
            clone.neighbors.add(cloneGraph(nnode));
        }
        return clone;
    }
}
