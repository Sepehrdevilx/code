import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;




//Name: Sepehr Eshaghian 
//Class: CS 3305/W02
// Term: Fall 
//Instructor:  Prof Perry
//Assignment:  11-Part-1-DFS

class UnweightedGraphWithNonrecursiveDFS<V> extends UnweightedGraph<V>
 {
  /** Construct an empty graph */
  public UnweightedGraphWithNonrecursiveDFS() {
  }

  /** Construct a graph from edges and vertices stored in arrays */
  public UnweightedGraphWithNonrecursiveDFS(V[] vertices, int[][] edges) {
	  super(vertices, edges);
  }

  /** Construct a graph from edges and vertices stored in List */
  public UnweightedGraphWithNonrecursiveDFS(List<V> vertices,List<Edge> edges ) {
	  super(vertices, edges);
  }

  /** Construct a graph for integer vertices 0, 1, 2 and edge list */
  public UnweightedGraphWithNonrecursiveDFS(List<Edge> edges, int numberOfVertices) {
   super(edges, numberOfVertices);
  }

  /** Construct a graph from integer vertices 0, 1, and edge array */
  public UnweightedGraphWithNonrecursiveDFS(int[][] edges, int numberOfVertices) {
   super(edges, numberOfVertices);
  }
  @Override
  public Tree dfs(int v) {
   List<Integer> searchOrder = new ArrayList<Integer>();
   int[] parent = new int[vertices.size()];// array to keep track of parent
   for (int i = 0; i < parent.length; i++) { 
    parent[i] = -1;	// set all parent values to -1
   }

   boolean[] isVisited = new boolean[vertices.size()];

   LinkedList<Integer> stack = new LinkedList<>();
   stack.push(v); // pushing initial node (vector) 
   searchOrder.add(v);
   isVisited[v] = true;
   while(!stack.isEmpty()) { // while stack is not empty
    int newVertex = stack.peek();
    boolean check = true;
    for (Edge e : neighbors.get(newVertex)) {
    if(isVisited[e.v] == false) { //if newVertex has not been visited before
       isVisited[newVertex] = true; // set newVertex to true
       parent[e.v] = newVertex; // set newVertex as a parent of e.v
       stack.push(e.v); // push vertex into the stack
       searchOrder.add(e.v);
     check = false;
     break;

    	}
    }
      if (check) {
    	  stack.pop();
      }

    
   }
   return new Tree(v, parent, searchOrder);   // return a tree 
  }
  public static void main(String[] args) {
	    String[] vertices = {"Kennesaw", "Roswell", "Woodstock",
	    		"Johns Creek", "Chattanooga", "Marietta", "Huntsville", "Savannah"," Atlanta", "Jacksonvile", "Dallas", "Austin"};

	    int[][] edges = {
	      {0, 1}, {0, 3}, {0, 5},
	      {1, 0}, {1, 2}, {1, 3},
	      {2, 1}, {2, 3}, {2, 4}, {2, 10},
	      {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
	      {4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10},
	      {5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7},
	      {6, 5}, {6, 7},
	      {7, 4}, {7, 5}, {7, 6}, {7, 8},
	      {8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
	      {9, 8}, {9, 11},
	      {10, 2}, {10, 4}, {10, 8}, {10, 11},
	      {11, 8}, {11, 9}, {11, 10}
	    };

	    Graph<String> graph = new UnweightedGraph<>(vertices, edges);
	    AbstractGraph<String>.Tree dfs = graph.dfs(graph.getIndex("Marietta")); 

	    java.util.List<Integer> searchOrders = dfs.getSearchOrder();
	    System.out.println(dfs.getNumberOfVerticesFound() + " vertices are searched in this DFS order:");
	    for (int i = 0; i < searchOrders.size(); i++)
	      System.out.print(graph.getVertex(searchOrders.get(i)) + " ");
	    System.out.println();

	    for (int i = 0; i < searchOrders.size(); i++)
	      if (dfs.getParent(i) != -1)
	        System.out.println("parent of " + graph.getVertex(i) +
	          " is " + graph.getVertex(dfs.getParent(i)));
	  }
 }