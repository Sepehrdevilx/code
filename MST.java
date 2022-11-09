 public class MST extends Tree {
   private double totalWeight; // Total weight of all edges in the tree

   public MST(int root, int[] parent, List<Integer> searchOrder,
     double totalWeight) {
    super(root, parent, searchOrder);
    this.totalWeight = totalWeight;
   }

   public double getTotalWeight() {
    return totalWeight;
   }
  }

  /** Find single source shortest paths */
  public ShortestPathTree getShortestPath(int sourceVertex) {
   // T stores the vertices whose path found so far
   List<Integer> T = new ArrayList<Integer>();
   // T initially contains the sourceVertex;
   T.add(sourceVertex);

   // vertices is defined in AbstractGraph
   int numberOfVertices = vertices.size();

   // parent[v] stores the previous vertex of v in the path
   int[] parent = new int[numberOfVertices];
   parent[sourceVertex] = -1; // The parent of source is set to -1

   // cost[v] stores the cost of the path from v to the source
   double[] cost = new double[numberOfVertices];
   for (int i = 0; i < cost.length; i++) {
    cost[i] = Double.MAX_VALUE; // Initial cost set to infinity
   }
   cost[sourceVertex] = 0; // Cost of source is 0

   // Get a copy of queues
   List<PriorityQueue<WeightedEdge>> queues = deepClone(this.queues);

   // Expand T
   while (T.size() < numberOfVertices) {
    int v = -1; // Vertex to be determined
    double smallestCost = Double.MAX_VALUE; // Set to infinity
    for (int u : T) {
     while (!queues.get(u).isEmpty()
       && T.contains(queues.get(u).peek().v)) {
      queues.get(u).remove(); // Remove the vertex in queue
            // for u
     }

     if (queues.get(u).isEmpty()) {
      // All vertices adjacent to u are in T
      continue;
     }

     WeightedEdge e = queues.get(u).peek();
     if (cost[u] + e.weight < smallestCost) {
      v = e.v;
      smallestCost = cost[u] + e.weight;
      // If v is added to the tree, u will be its parent
      parent[v] = u;
     }
    } // End of for

    T.add(v); // Add a new vertex to T
    cost[v] = smallestCost;
   } // End of while

   // Create a ShortestPathTree
   return new ShortestPathTree(sourceVertex, parent, T, cost);
  }
  }