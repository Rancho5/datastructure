package com.test;

/*
学习树（Tree）
树的定义，结点的度，层次、深度、高度（按照边算），有序树、无序树，
树的存储结构：顺序+链式

二叉树：斜树，满二叉树、完全二叉树（未完成的满二叉树，编号与满二叉树相同）
二叉树的性质：第i层上最多几个结点？深度为k的二叉树最多几个结点？有n个节点的二叉树最多几层？完全二叉树的父节点、子节点序号有什么关系？度为0的结点和度为2的结点有什么关系？（n0=n2+1）
二叉树的存储：顺序或者链式都可以
二叉树的遍历：前序遍历（根-左-右）、中序遍历（左-根-右）、后序遍历（左-右-根）、层序遍历
 */

public class BinaryTree {
    private TreeNode root = null;

    public BinaryTree(){
        root = new TreeNode<String>(1, "A");
    }

    /**
     * 构建二叉树
     *         A
     *     B       C
     * D      E        F
     */
    public void createBinaryTree(){
        TreeNode<String> nodeB = new TreeNode<>(2, "B");
        TreeNode<String> nodeC = new TreeNode<>(3, "C");
        TreeNode<String> nodeD = new TreeNode<>(4, "D");
        TreeNode<String> nodeE = new TreeNode<>(5, "E");
        TreeNode<String> nodeF = new TreeNode<>(6, "F");
        root.leftChild = nodeB;
        root.rightChild = nodeC;
        nodeB.leftChild = nodeD;
        nodeB.rightChild = nodeE;
        nodeC.rightChild = nodeF;
    }

    /**
     * 求二叉树高度
     *
     */
    public int getHeight(){
        return getHeight(root);
    }

    private int getHeight(TreeNode node){
        if(node == null)
            return 0;
        int i = getHeight(node.leftChild);
        int j = getHeight(node.rightChild);
        return (i>j)?i+1:j+1;
    }

    /**
     * 获取二叉树结点数
     *
     */
    public int getSize(){
        return getSize(root);
    }

    private int getSize(TreeNode node){
        if(node == null)
            return 0;
        return getSize(node.rightChild) + getSize(node.leftChild)+1;
    }

    /**
     * 前序遍历（迭代法）
     *
     */
    public void preOrder(TreeNode node){
        if(node == null)
            return;
        System.out.println(node.data);
        preOrder(node.leftChild);
        preOrder(node.rightChild);
    }

    /**
     * 前序遍历（非迭代）
     *
     */
    public void nonRecOrder(TreeNode node){
        if(node == null){
            return;
        }
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(node);
        while(!s.isEmpty()){
            TreeNode n = s.pop();
            if(n.rightChild != null)
                s.push(n.rightChild);
            if(n.leftChild != null)
                s.push(n.leftChild);
            System.out.println(n.data);
        }


    }


    public class TreeNode<T>{
        private int index;
        private T data;
        private TreeNode leftChild;
        private TreeNode rightChild;

        public TreeNode(int index, T data){
            this.index = index;
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

    }

    public static void main(String[] args){
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.createBinaryTree();
        int height = binaryTree.getHeight();
        System.out.println("treeHeihgt:"+height);
        int size = binaryTree.getSize();
        System.out.println("treeSize:"+size);
		binaryTree.preOrder(binaryTree.root);
//      binaryTree.nonRecOrder(binaryTree.root);
    }

}

