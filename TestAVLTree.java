
public class TestAVLTree {
  public static void main(String[] args) {
    // Create an AVL tree
    AVLTree<Integer> tree = new AVLTree<>(new Integer[]{35, 
      20, 3});
    System.out.print("After inserting 35, 20, 3:");
    printTree(tree);

    tree.insert(34);
    tree.insert(43);
    System.out.print("\nAfter inserting 34, 43:");
    printTree(tree);

    tree.insert(30);
    System.out.print("\nAfter inserting 30");
    printTree(tree);

    tree.insert(10);
    System.out.print("\nAfter inserting 10");
    printTree(tree);

    tree.delete(34);
    tree.delete(30);
    tree.delete(43);
    System.out.print("\nAfter removing 34, 30, 43:");
    printTree(tree);

    tree.delete(3);
    System.out.print("\nAfter removing 3:");
    printTree(tree);
    
    System.out.print("\nTraverse the elements in the tree: ");
    for (int e: tree) {
      System.out.print(e + " ");
    }
  }

  public static void printTree(BST tree) {
    // Traverse tree
    System.out.print("\nInorder (sorted): ");
    tree.inorder();
    System.out.print("\nPostorder: ");
    tree.postorder();
    System.out.print("\nPreorder: ");
    tree.preorder();
    System.out.print("\nThe number of nodes is " + tree.getSize());
    System.out.println();
  }
}
