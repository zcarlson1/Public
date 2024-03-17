/* 
Programmer: Zach Carlson
Class: CS &145
Date Due: 3/15/2024
Assignment: Lab 6 - Binary Search Tree Dictionary Lab
Purpose: A series of classes that will process input from a user,
storing that information in a binary search tree with menu options
for searching and printing that tree in specific traversals or
modify the data as the user desires. 

Binary Search Tree Class
*/

public class BinarySearchTree {
    // Root node of the BST
    private Node root;

    // Constructor
    public BinarySearchTree() {
        this.root = null; // BST is empty at the start
    } // end of BST constructor

    // Public method to start the insertion process
    public void insert(int memberID, Person person) {
        root = insertRec(root, memberID, person);
    } // end of insert method

    // Helper method to insert a new node in the BST
    private Node insertRec(Node root, int memberID, Person person) {
        // If the current spot is empty, place the new node here
        if (root == null) {
            return new Node(memberID, person);
        }
        // If the memberID is less than the root's, go left
        if (memberID < root.memberID) {
            root.left = insertRec(root.left, memberID, person);
        } 
        // Otherwise, go right
        else if (memberID > root.memberID) {
            root.right = insertRec(root.right, memberID, person);
        }
        
        return root;
    } // end of helper method insertRec

    // Public method to start the in-order traversal
    public void inorder() {
        inorderRec(root);
    } // end of inorder method

    // Helper method for in-order traversal
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.toString()); // Print the node's data
            inorderRec(root.right);
        }
    } // end of private inorderRec method

    // Public method for lookup
    public Node lookup(int memberID) {
        return lookupRec(root, memberID);
    } // end of lookup method

    // Helper method to recursively search for a node
    private Node lookupRec(Node root, int memberID) {
        // Base case: root is null or the ID is present at root
        if (root == null || root.memberID == memberID) {
            return root;
        }
        // Value is greater than root's ID
        if (memberID < root.memberID) {
            return lookupRec(root.left, memberID);
        } 
        // Value is less than root's ID
        else {
            return lookupRec(root.right, memberID);
        }
    } // end of helper lookupRec method

    // Public method for deletion
    public void delete(int memberID) {
        root = deleteRec(root, memberID);
    } // end of delete method

    // Helper method for deletion
    private Node deleteRec(Node root, int memberID) {
        // Base case: If the tree is empty
        if (root == null) return root;

        // Otherwise, progress recursively down the tree
        if (memberID < root.memberID) {
            root.left = deleteRec(root.left, memberID);
        }
        else if (memberID > root.memberID) {
            root.right = deleteRec(root.right, memberID);
        }
        // If the ID to be deleted is found
        else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            }
            else if (root.right == null) {
                return root.left;
            }
            // Node with two children: Get the inorder successor
            root.memberID = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.memberID);
        } 

        return root;
    } // end of helper deleteRec method
 
    // Method to find the inorder successor
    private int minValue(Node root) {
        int minv = root.memberID;
        while (root.left != null) {
            minv = root.left.memberID;
            root = root.left;
        }
        return minv;
    } // end of minValue method

    // Method to calculate the size of the BST (the total number of records)
    public int size() {
        return sizeRec(root);
    } // end of size method

    // Recursive helper method to calculate size
    private int sizeRec(Node root) {
        if (root == null) {
            return 0;
        } 
        else {
            return 1 + sizeRec(root.left) + sizeRec(root.right);
        }
    } // end of helpter sizeRec method. I like recursion. 

    // Method to initiate pre-order traversal
    public void preOrder() {
        preOrderRec(root);
    } // end of preOrder method

    // Recursive helper method for pre-order traversal
    private void preOrderRec(Node root) {
        if (root != null) {
            System.out.println(root); 
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    } // end of helper preOrderRec method

    // Method to initiate in-order traversal
    public void inOrder() {
        inOrderRec(root);
    } // end of inOrder method

    // Recursive helper method for in-order traversal
    private void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root); 
            inOrderRec(root.right);
        }
    } // end of helper inOrderRec method

    // Method to initiate post-order traversal
    public void postOrder() {
        postOrderRec(root);
    } // end of postOrder method

    // Recursive helper method for post-order traversal
    private void postOrderRec(Node root) {
        if (root != null) {
            postOrderRec(root.left);
            postOrderRec(root.right);
            System.out.println(root); 
        }
    } // end of helper postOrderRec method
    
    // Helper method for the modifyRecord method
    // Because deleteRecord method prompts the user for member ID
    // And the modifyRecord method already does that. 
    public void deleteMemberByID(int memberID) {
        root = deleteRec(root, memberID);
    } // end of helper deleteMemberByID method

    // Public method to lookup a node by its member ID
    public Node lookupNode(int memberID) {
        // Calls the recursive helper method to start the search from the root
        return lookupNodeRec(root, memberID);
    } // end of lookupNode method
    
    // Private recursive helper method to search for a node by member ID
    private Node lookupNodeRec(Node root, int memberID) {
        // Base case: if the current node is null (end of tree) or the member ID matches, return the node
        if (root == null || root.memberID == memberID) {
        return root;
        } 
        // If the member ID to search for is less than the current node's member ID, search the left subtree
        else if (memberID < root.memberID) {
            return lookupNodeRec(root.left, memberID);
        } 
        // If the member ID to search for is greater than the current node's member ID, search the right subtree
        else {
            return lookupNodeRec(root.right, memberID);
        }
    } // end of helper lookupNodeRec method

} // end of BinarySearchTree class