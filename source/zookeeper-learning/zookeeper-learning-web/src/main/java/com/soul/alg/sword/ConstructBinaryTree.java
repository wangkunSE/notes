package com.soul.alg.sword;

/**
 * @author wangkun1
 * @version 2018/4/11
 */
public class ConstructBinaryTree {

    private class BinaryTree {

        private int value;
        private BinaryTree leftChild;
        private BinaryTree rightChild;

        public BinaryTree() {
        }

        public BinaryTree(int value, BinaryTree leftChild, BinaryTree rightChild) {
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public BinaryTree getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(BinaryTree leftChild) {
            this.leftChild = leftChild;
        }

        public BinaryTree getRightChild() {
            return rightChild;
        }

        public void setRightChild(BinaryTree rightChild) {
            this.rightChild = rightChild;
        }
    }

    public static void main(String[] args) {
        int[] preOrder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inOrder = {4, 7, 2, 1, 5, 3, 8, 6};
        ConstructBinaryTree constructBinaryTree = new ConstructBinaryTree();
        BinaryTree binaryTree = constructBinaryTree.constructBinaryTreeWithPreAndInOrder(preOrder, inOrder, preOrder.length);
        constructBinaryTree.preOrderSearch(binaryTree);
        System.out.println("=========================");
        constructBinaryTree.inOrderSearch(binaryTree);
    }

    private void preOrderSearch(BinaryTree binaryTree) {
        if (binaryTree != null) {
            System.out.println(binaryTree.getValue());
            preOrderSearch(binaryTree.getLeftChild());
            preOrderSearch(binaryTree.getRightChild());
        }
    }

    private void inOrderSearch(BinaryTree binaryTree) {
        if (binaryTree != null) {
            inOrderSearch(binaryTree.getLeftChild());
            System.out.println(binaryTree.getValue());
            inOrderSearch(binaryTree.getRightChild());
        }
    }

    private BinaryTree constructBinaryTreeWithPreAndInOrder(int[] preOrder, int[] inOrder, int length) {
        if (preOrder == null || inOrder == null || length <= 0) {
            return null;
        }
        return coreConstructBinaryTree(preOrder, 0, length - 1, inOrder, 0, length - 1);
    }

    private BinaryTree coreConstructBinaryTree(int[] preOrder, int preOrderStart, int preOrderEnd, int[] inOrder, int inOrderStart, int inOrderEnd) {
        if (preOrderStart == preOrderEnd) {
            return new BinaryTree(preOrder[preOrderEnd], null, null);
        }
        int leftSize = 0;
        for (int i = inOrderStart; i < inOrderEnd && (preOrder[preOrderStart] != inOrder[i]); i++) {
            if (preOrder[preOrderStart] != inOrder[i]) {
                leftSize++;
                inOrderStart++;
            }
        }
        BinaryTree root = new BinaryTree(preOrder[preOrderStart], null, null);
        if (((preOrderStart + 1) <= (preOrderStart + leftSize)) && ((inOrderStart - leftSize) <= (inOrderStart - 1))) {
            root.setLeftChild(coreConstructBinaryTree(preOrder, preOrderStart + 1, preOrderStart + leftSize, inOrder, inOrderStart - leftSize, inOrderStart - 1));
        }
        if (((preOrderStart + leftSize + 1) <= (preOrderEnd)) && ((inOrderStart + 1) <= (inOrderEnd))) {
            root.setRightChild(coreConstructBinaryTree(preOrder, preOrderStart + leftSize + 1, preOrderEnd, inOrder, inOrderStart + 1, inOrderEnd));
        }
        return root;
    }
}
