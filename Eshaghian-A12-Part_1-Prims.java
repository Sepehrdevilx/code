
import java.util.*;

public class WeightedGraph<V> extends AbstractGraph<V> {
	public List<PriorityQueue<WeightedEdge>> queues = new ArrayList<PriorityQueue<WeightedEdge>>();

	public WeightedGraph() {
	  }

	  /** Construct a WeightedGraph from edges and vertices in arrays */
	  public WeightedGraph(int[][] edges, V[] vertices) {
	   super(edges, vertices);
	   createQueues(edges, vertices.length);
	  }

	  /** Construct a WeightedGraph from edges and vertices in List */
	  public WeightedGraph(int[][] edges, int numberOfVertices) {
	   super(edges, numberOfVertices);
	   createQueues(edges, numberOfVertices);
	  }

	  /** Construct a WeightedGraph for vertices 0, 1, 2 and edge list */
	  @SuppressWarnings({ "rawtypes", "unchecked" })
	  public WeightedGraph(List<WeightedEdge> edges, List<V> vertices) {
	   super((List) edges, vertices);
	   createQueues(edges, vertices.size());
	  }

	  /** Construct a WeightedGraph from vertices 0, 1, and edge array */
	  @SuppressWarnings({ "rawtypes", "unchecked" })
	  public WeightedGraph(List<WeightedEdge> edges, int numberOfVertices) {
	   super((List) edges, numberOfVertices);
	   createQueues(edges, numberOfVertices);
	  }

	  /** Create priority adjacency lists from edge arrays */
	  private void createQueues(int[][] edges, int numberOfVertices) {
	   for (int i = 0; i < numberOfVertices; i++) {
	    queues.add(new PriorityQueue<WeightedEdge>()); // Create a queue
	   }

	   for (int i = 0; i < edges.length; i++) {
	    int u = edges[i][0];
	    int v = edges[i][1];
	    int weight = edges[i][2];
	    // Insert an edge into the queue
	    queues.get(u).offer(new WeightedEdge(u, v, weight));
	   }
	  }

	  /** Create priority adjacency lists from edge lists */
	  private void createQueues(List<WeightedEdge> edges, int numberOfVertices) {
	   for (int i = 0; i < numberOfVertices; i++) {
	    queues.add(new PriorityQueue<WeightedEdge>()); // Create a queue
	   }

	   for (WeightedEdge edge : edges) {
	    queues.get(edge.u).offer(edge); // Insert an edge into the queue
	   }
	  }

	  /** Display edges with weights */
	  public void printWeightedEdges() {
	   for (int i = 0; i < queues.size(); i++) {
	    System.out.print(getVertex(i) + " (" + i + "): ");
	    for (WeightedEdge edge : queues.get(i)) {
	     System.out.print("(" + edge.u + ", " + edge.v + ", "
	       + edge.weight + ") ");
	    }
	    System.out.println();
	   }
	  }
 
  /** Add edges to the weighted graph */  
  public boolean addEdge(int u, int v, double weight) {
    return addEdge(new WeightedEdge(u, v, weight));
  }
  /** Get the edges from the weighted graph */
  public List<PriorityQueue<WeightedEdge>> getWeightedEdges() {
   return queues;
  }

  /** Clears the weighted graph */
  public void clear() {
   vertices.clear();
   neighbors.clear();
   queues.clear();
  }



  /** Get a minimum spanning tree rooted at vertex 0 */

  public MST getMinimumPrimSpanningTree() {
	   return getMinimumPrimSpanningTree(0);
	  }
  
  public MST getMinimumPrimSpanningTree(int startingVertex) {
	   Double[][] adjacencyMatrix = new Double[getSize()][getSize()];
	   for (int i = 0; i < queues.size(); i++) {
	    PriorityQueue<WeightedEdge> tmpQueue = queues.get(i);
	    for (WeightedEdge weightedEdge : tmpQueue) {
	     adjacencyMatrix[weightedEdge.u][weightedEdge.v] = weightedEdge.weight;
	    }
	   }
	   
	   List<V> newVertices = getVertices();
	   List<WeightedEdge> newEdges = new LinkedList<>();
	   List<Integer> visited = new LinkedList<>();
	   WeightedEdge min = null;
	   for (int i = 0; i < adjacencyMatrix.length; i++) {
	    for (int j = 0; j < adjacencyMatrix[i].length; j++) {
	     if(adjacencyMatrix[i][j] != null) {
	      if((min == null)||(adjacencyMatrix[i][j] < min.weight)) {
	       min = new WeightedEdge(i, j, adjacencyMatrix[i][j]);
	      }
	     }
	    }
	   }
	   adjacencyMatrix[min.u][min.v] = null;
	   adjacencyMatrix[min.v][min.u] = null;
	   visited.add(min.v);
	   visited.add(min.u);
	   newEdges.add(min);
	   newEdges.add(new WeightedEdge(min.v, min.u, min.weight));
	   
	   while(true) {
	    min = null;
	    for (WeightedEdge weightedEdge : newEdges) {
	     for (int i = 0; i < adjacencyMatrix.length; i++) {
	      if(!(visited.contains(i) && visited.contains(weightedEdge.v)))
	      if(adjacencyMatrix[i][weightedEdge.v] != null) {
	       if((min == null)||(adjacencyMatrix[i][weightedEdge.v] < min.weight)) {
	        min = new WeightedEdge(i, weightedEdge.v, adjacencyMatrix[i][weightedEdge.v]);
	       }
	      }
	     }
	     for (int i = 0; i < adjacencyMatrix.length; i++) {
	      if(!(visited.contains(weightedEdge.u) && visited.contains(i)))
	      if(adjacencyMatrix[weightedEdge.u][i] != null) {
	       if((min == null)||(adjacencyMatrix[weightedEdge.u][i] < min.weight)) {
	        min = new WeightedEdge(weightedEdge.u, i, adjacencyMatrix[weightedEdge.u][i]);
	       }
	      }
	     }
	    }
	    
	    adjacencyMatrix[min.u][min.v] = null;
	    adjacencyMatrix[min.v][min.u] = null;
	    visited.add(min.v);
	    visited.add(min.u);
	    newEdges.add(min);
	    newEdges.add(new WeightedEdge(min.v, min.u, min.weight));
	    
	    WeightedGraph<V> tmpGraph =  new WeightedGraph<V>(newEdges, newVertices);

	    Tree tree = tmpGraph.dfs(startingVertex);
	    if(tree.getNumberOfVerticesFound() == newVertices.size()) { 

	     double totalWeight = 0;
	     for (int i = 0; i < newEdges.size(); i++) {
	      totalWeight += newEdges.get(i).weight;
	     }
	     totalWeight = totalWeight / 2;     
	     return new MST(startingVertex, tree.parent, tree.searchOrder, totalWeight);
	    }
	    
	   }

	  }
	  

  /** MST is an inner class in WeightedGraph */
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
    // cost[v] stores the cost of the path from v to the source
    double[] cost = new double[getSize()];
    for (int i = 0; i < cost.length; i++) {
      cost[i] = Double.POSITIVE_INFINITY; // Initial cost set to infinity
    }
    cost[sourceVertex] = 0; // Cost of source is 0

    // parent[v] stores the previous vertex of v in the path
    int[] parent = new int[getSize()];
    parent[sourceVertex] = -1; // The parent of source is set to -1
    
    // T stores the vertices whose path found so far
    List<Integer> T = new ArrayList<>();
    
    // Expand T
    while (T.size() < getSize()) {
      // Find smallest cost v in V - T 
      int u = -1; // Vertex to be determined
      double currentMinCost = Double.POSITIVE_INFINITY;
      for (int i = 0; i < getSize(); i++) {
        if (!T.contains(i) && cost[i] < currentMinCost) {
          currentMinCost = cost[i];
          u = i;
        }
      }
      
      T.add(u); // Add a new vertex to T
      
      // Adjust cost[v] for v that is adjacent to u and v in V - T
      for (Edge e : neighbors.get(u)) {
        if (!T.contains(e.v) 
            && cost[e.v] > cost[u] + ((WeightedEdge)e).weight) {
          cost[e.v] = cost[u] + ((WeightedEdge)e).weight;
          parent[e.v] = u; 
        }
      }
    } // End of while

    // Create a ShortestPathTree
    return new ShortestPathTree(sourceVertex, parent, T, cost);
  }

  public class ShortestPathTree extends Tree {
	   private double[] cost; // cost[v] is the cost from v to source

	   /** Construct a path */
	   public ShortestPathTree(int source, int[] parent,
	     List<Integer> searchOrder, double[] cost) {
	    super(source, parent, searchOrder);
	    this.cost = cost;
	   }

	   /** Return the cost for a path from the root to vertex v */
	   public double getCost(int v) {
	    return cost[v];
	   }

	   /** Print paths from all vertices to the source */
	   public void printAllPaths() {
	    System.out.println("All shortest paths from "
	      + vertices.get(getRoot()) + " are:");
	    for (int i = 0; i < cost.length; i++) {
	     printPath(i); // Print a path from i to the source
	     System.out.println("(cost: " + cost[i] + ")"); // Path cost
	    }
	   }
	  }
	 }
