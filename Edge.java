import java.util.*;
  
public class Edge {
   public int u; // Starting vertex of the edge
   public int v; // Ending vertex of the edge

   /** Construct an edge for (u, v) */
   public Edge(int u, int v) {
    this.u = u;
    this.v = v;
   }
  }

  @Override
  /** Obtain a DFS tree starting from vertex v */
  /** To be discussed in Section 27.6 */
  public Tree dfs(int v) {
   List<Integer> searchOrder = new ArrayList<Integer>();
   int[] parent = new int[vertices.size()];
   for (int i = 0; i < parent.length; i++)
    parent[i] = -1; // Initialize parent[i] to -1

   // Mark visited vertices
   boolean[] isVisited = new boolean[vertices.size()];

   // Recursively search
   dfs(v, parent, searchOrder, isVisited);

   // Return a search tree
   return new Tree(v, parent, searchOrder);
  }

  /** Recursive method for DFS search */
  private void dfs(int v, int[] parent, List<Integer> searchOrder,
    boolean[] isVisited) {
   // Store the visited vertex
   searchOrder.add(v);
   isVisited[v] = true; // Vertex v visited

   for (int i : neighbors.get(v)) {
    if (!isVisited[i]) {
     parent[i] = v; // The parent of vertex i is v
     dfs(i, parent, searchOrder, isVisited); // Recursive search
    }
   }
  }

  @Override
  /** Starting bfs search from vertex v */
  /** To be discussed in Section 27.7 */
  public Tree bfs(int v) {
   List<Integer> searchOrder = new ArrayList<Integer>();
   int[] parent = new int[vertices.size()];
   for (int i = 0; i < parent.length; i++)
    parent[i] = -1; // Initialize parent[i] to -1

   java.util.LinkedList<Integer> queue = new java.util.LinkedList<Integer>(); // list
                      // used
                      // as
                      // a
                      // queue
   boolean[] isVisited = new boolean[vertices.size()];
   queue.offer(v); // Enqueue v
   isVisited[v] = true; // Mark it visited

   while (!queue.isEmpty()) {
    int u = queue.poll(); // Dequeue to u
    searchOrder.add(u); // u searched
    for (int w : neighbors.get(u)) {
     if (!isVisited[w]) {
      queue.offer(w); // Enqueue w
      parent[w] = u; // The parent of w is u
      isVisited[w] = true; // Mark it visited
     }
    }
   }

   return new Tree(v, parent, searchOrder);
  }