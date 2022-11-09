public class TestWeightedGraph {
	public static void main(String[] args) {

 String[] vertices = {"A" , "B", "C" , "D" , "E" , "F", "G" , "H"};

 int[][] edges = {
 {0, 2, 4}, {0, 5, 7}, {1, 7, 3},
{1, 4, 9}, {2, 0, 4}, {2, 5, 2},
 {2, 6, 9}, {2, 3, 3}, {3, 2, 3}, {3, 4, 3},
 {3, 6, 7}, {4, 3, 3}, {4, 1, 9}, {4, 7, 7},
 {4, 6, 2},  {5, 0, 7}, {5, 2, 2}, {5, 6, 8},
{6, 5, 8}, {6, 2, 9},
 {6, 3, 7}, {6, 4, 2}, {6, 7, 3},
{7, 6, 3}, {7, 2, 3}, {7, 4, 7} };
 
  WeightedGraph<String> graph1 = new WeightedGraph<>(edges, vertices );
  WeightedGraph<String>.MST tree1 = graph1.getMinimumPrimSpanningTree();
  System.out.println("Total weight is " + tree1.getTotalWeight());
  tree1.printTree();
  System.out.println("Search Order" + tree1.getSearchOrder());
  
  }
}
