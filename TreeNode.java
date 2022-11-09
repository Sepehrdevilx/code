import java.lang.reflect.Array;


public class TreeNode {
    int data;
    int MAX_KIDS = 4;
    TreeNode[] children = new TreeNode[MAX_KIDS];
    TreeNode parent;
}