import java.awt.Point;
import java.util.ArrayList;
/**
 * <p>
 *  Implements a Two Dimensional Binary Search Tree for efficient spatial data
 *  storage and retrieval. This structure can find and store points in a
 *  two dimensional space. It supports operations like insertion of points, searching
 *  for a specific point, and finding all points within a specified range.
 *  The tree organizes points by alternating between x and y coordinates at each level.
 * </p>
 * author Alfredo Sandoval-Luis and Charles Ray
 * edu.uwp.cs.340.course CSCI 340 - Data Structures/Algotrithm Design
 * edu.uwp.cs.340.section 001
 * edu.uwp.cs.340.assignment 3
 * bugs: none
 */
public class TwoDTree {
    /**
     * Static inner class representing a node in the 2D tree.
     */
    private static class TwoDTreeNode {
        Point point;
        TwoDTreeNode left, right;
        /**
         * Constructs a node with the given point.
         * @param point The point to be stored in this node.
         */
        TwoDTreeNode(Point point) {
            this.point = point;
        }
    }

    /**
     * root of tree
     */
    private TwoDTreeNode root;

    /**
     * Constructs an empty TwoDTree.
     */
    public TwoDTree() {
        this.root = null;
    }

    /**
     * Constructs a TwoDTree from a list of points.
     * @param points The list of points to build the tree from.
     */
    public TwoDTree(ArrayList<Point> points) {
        for (Point p : points) {
            insert(p); // insert each point into the tree
        }
    }

    /**
     * Inserts a new point into the 2D tree.
     * @param p The point to be inserted.
     */
    public void insert(Point p) {
        root = insert(root, p);
    }

    /**
     * Helper method to recursively insert a new point into the tree.
     * It ensures the tree maintains its binary search property based on x coordinates.
     * @param node The current node in the tree during recursion.
     * @param p The point to be inserted.
     * @return The node after insertion.
     */
    private TwoDTreeNode insert(TwoDTreeNode node, Point p) {
        if (node == null) {
            // base case, if the current node is null, insert the new point here
            return new TwoDTreeNode(p);
        }
        // if the point already exists in the tree, return the node to avoid duplicates
        if (p.equals(node.point)) {
            return node;
        }
        // decides what side of the tree to insert into
        if (p.x < node.point.x) {
            node.left = insert(node.left, p); // insert to the left if the point is less than the node
        } else {
            node.right = insert(node.right, p); // else insert right
        }
        return node;
    }

    /**
     * Searches for a point in the 2D tree.
     * @param p The point to search for.
     * @return true if the point is found, false otherwise.
     */
    public boolean search(Point p) {
        return search(root, p); // start searching from root
    }

    /**
     * Helper method to recursively search for a point in the tree.
     * @param node The current node during the search.
     * @param p The point to search for.
     * @return true if the point is found, false otherwise.
     */
    private boolean search(TwoDTreeNode node, Point p) {
        if (node == null) {
            // base case, if the node is null then it was not found
            return false;
        }
        if (p.equals(node.point)) {
            // point is found
            return true;
        }
        // recursive search in either the left or right subtree
        if (p.x < node.point.x) {
            return search(node.left, p); // search left if the point is less than the node
        } else {
            return search(node.right, p); // else search right
        }
    }

    /**
     * Finds all points within a specified rectangular range.
     * @param p1 The lower-left point of the rectangular range.
     * @param p2 The upper-right point of the rectangular range.
     * @return A list of points within the specified range.
     */
    public ArrayList<Point> searchRange(Point p1, Point p2) {
        ArrayList<Point> result = new ArrayList<>();
        searchRange(root, p1, p2, result); // start the search from the root
        return result;
    }

    /**
     * Helper method to recursively find and collect points within a given range.
     * @param node The current node during the search.
     * @param p1 The lower-left point of the rectangular range.
     * @param p2 The upper-right point of the rectangular range.
     * @param result The list where found points are collected.
     */
    private void searchRange(TwoDTreeNode node, Point p1, Point p2, ArrayList<Point> result) {
        if (node == null) {
            // base case, if null there is nothing to consider
            return;
        }
        // check if the current node point is within range
        boolean inRange = node.point.x >= p1.x && node.point.x <= p2.x && node.point.y >= p1.y && node.point.y <= p2.y;
        if (inRange) {
            // if so then add to the result
            result.add(node.point);
        }
        // recursively search the left and/or right subtree if there's a possibility of finding more points in the range
        if (p1.x < node.point.x) {
            searchRange(node.left, p1, p2, result); // search left if the range minimum is less than the node
        }
        if (p2.x >= node.point.x) {
            searchRange(node.right, p1, p2, result); // search right if the range max is greater than or equal to node
        }
    }
}
