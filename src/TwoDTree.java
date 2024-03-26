import java.awt.Point;
import java.util.ArrayList;

public class TwoDTree {
    private static class TwoDTreeNode {
        Point point;
        TwoDTreeNode left, right;
        TwoDTreeNode(Point point) {
            this.point = point;
        }
    }

    private TwoDTreeNode root;

    public TwoDTree() {
        this.root = null;
    }

    public TwoDTree(ArrayList<Point> points) {
        for (Point p : points) {
            insert(p);
        }
    }

    public void insert(Point p) {
        root = insert(root, p);
    }

    private TwoDTreeNode insert(TwoDTreeNode node, Point p) {
        if (node == null) {
            return new TwoDTreeNode(p);
        }
        if (p.equals(node.point)) {
            return node; // Avoid duplicates
        }
        if (p.x < node.point.x) {
            node.left = insert(node.left, p);
        } else {
            node.right = insert(node.right, p);
        }
        return node;
    }

    public boolean search(Point p) {
        return search(root, p);
    }

    private boolean search(TwoDTreeNode node, Point p) {
        if (node == null) {
            return false;
        }
        if (p.equals(node.point)) {
            return true;
        }
        if (p.x < node.point.x) {
            return search(node.left, p);
        } else {
            return search(node.right, p);
        }
    }

    public ArrayList<Point> searchRange(Point p1, Point p2) {
        ArrayList<Point> result = new ArrayList<>();
        searchRange(root, p1, p2, result);
        return result;
    }

    private void searchRange(TwoDTreeNode node, Point p1, Point p2, ArrayList<Point> result) {
        if (node == null) {
            return;
        }
        boolean inRange = node.point.x >= p1.x && node.point.x <= p2.x && node.point.y >= p1.y && node.point.y <= p2.y;
        if (inRange) {
            result.add(node.point);
        }
        if (p1.x < node.point.x) {
            searchRange(node.left, p1, p2, result);
        }
        if (p2.x >= node.point.x) {
            searchRange(node.right, p1, p2, result);
        }
    }
}
