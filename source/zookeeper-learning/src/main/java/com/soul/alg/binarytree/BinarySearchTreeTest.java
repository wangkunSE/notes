package com.soul.alg.binarytree;

/***
 * @author wangkun1
 * @version 2018/1/5 
 */
public class BinarySearchTreeTest {

    private static class BinnarySearchTree {

        private class Node {
            private Node left = null;
            private Node right = null;
            private Node parent = null;
            private Integer value = null;

            public Node getLeft() {
                return left;
            }

            public void setLeft(Node left) {
                this.left = left;
            }

            public Node getRight() {
                return right;
            }

            public void setRight(Node right) {
                this.right = right;
            }

            public Integer getValue() {
                return value;
            }

            public void setValue(Integer value) {
                this.value = value;
            }

            public Node getParent() {
                return parent;
            }

            public void setParent(Node parent) {
                this.parent = parent;
            }

            public Node(Node left, Node right, Node parent, Integer value) {
                super();
                this.left = left;
                this.right = right;
                this.parent = parent;
                this.value = value;
            }

        }

        private Node root = null;

        public void insert(Integer value) throws Exception {
            insertNode(new Node(null, null, null, value));
        }

        private void insertNode(Node node) throws Exception {
            Node pre = null;
            Node x = this.root;
            while (x != null) {
                pre = x;
                if (x.getValue() > node.getValue()) {
                    x = x.getLeft();
                } else if (x.getValue() < node.getValue()) {
                    x = x.getRight();
                } else {
                    throw new Exception("value is existed");
                }
            }
            if (pre != null) {
                if (node.getValue() < pre.getValue()) {
                    pre.setLeft(node);
                } else {
                    pre.setRight(node);
                }
                node.setParent(pre);
            } else {
                // 根节点
                this.root = node;
            }
        }

        public Node find(Integer value) {
            Node x = root;
            while (x != null && x.getValue() != value) {
                if (x.getValue() > value) {
                    x = x.getLeft();
                } else if (x.getValue() < value) {
                    x = x.getRight();
                }
            }
            return x;
        }

        public void delete(Integer value) throws Exception {
            Node x = find(value);
            if (x != null) {
                if (x.getLeft() == null && x.getRight() == null) {
                    // 叶子节点
                    x.setLeft(null);
                    x.setRight(null);
                } else if (x.getLeft() != null) {
                    // 左子树不为空
                    Node y = x.getLeft();
                    while (y.getRight() != null) {
                        y = y.getRight();
                    }
                    x.setValue(y.getValue());
                    System.out.println(y.getParent());
                    y.getParent().setRight(null);
                } else {
                    // 右子树不为空
                    Node y = x.getRight();
                    if (x.getParent() != null) {
                        y.setLeft(x.getLeft());
                        if (x.getParent().getValue() > x.getValue()) {
                            x.getParent().setLeft(y);
                        } else {
                            x.getParent().setRight(y);
                        }
                    } else {
                        // 根节点
                        this.root = y;
                    }
                }
            } else {
                throw new Exception("value is not exists");
            }
        }

        public void inOrder(Node node) {
            if (node != null && node.getValue() != null) {
                inOrder(node.getLeft());
                System.out.println(node.getValue());
                inOrder(node.getRight());
            }
        }

        public Node getRoot() {
            return root;
        }

        public void setRoot(Node root) {
            this.root = root;
        }

    }

    public static void main(String[] args) throws Exception {
        BinnarySearchTree bt = new BinnarySearchTree();
        bt.insert(7);
        bt.insert(11);
        bt.insert(8);
        bt.insert(12);
        bt.insert(9);
        bt.insert(13);
        bt.insert(10);
        bt.inOrder(bt.getRoot());
//        System.out.println("----------------------");
//        bt.delete(11);
//        bt.inOrder(bt.getRoot());
        bt.delete(11);
    }
}
