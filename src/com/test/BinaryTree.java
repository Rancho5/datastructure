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

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import sun.reflect.generics.tree.Tree;
import java.util.LinkedList;
import java.util.ArrayList;

public class BinaryTree {
    private TreeNode root = null;

    public BinaryTree(){ }

    public class TreeNode<T>{
        private int index;
        private T data;
        private TreeNode leftChild;
        private TreeNode rightChild;
        private TreeNode parent;

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

        public TreeNode(int index, T data){
            this.index = index;
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
            this.parent = null;
        }

    }

    /**
     * 构建二叉树
     *         A
     *     B       C
     * D      E        F
     */
    public void createBinaryTree(){
        root = new TreeNode<>(1, "A");
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
        return getSize(node.rightChild) + getSize(node.leftChild) + 1;
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

    /**
     * 通过前序遍历的数据序列反向生成二叉树
     *              A
     *         B            C
     *      D     E      #      F
     *    #   # #   #         #    #
     *
     * 对应序列：ABD##E##C#F##
     *
     */
    public void createBinaryTreePre(String str){
        ArrayList<String> data = new ArrayList<>();
        for(int i = 0; i < str.length(); i++){
            data.add(str.substring(i, i+1));
        }
        createBinaryTreePre(data);
    }

    private int index = 0;
    private TreeNode createBinaryTreePre(ArrayList<String> data){
        if(data.size() == 0)
            return null;
        String d = data.get(index);
        if(d.equals("#")) {
            index++;
            return null;
        }
        TreeNode<String> node = new TreeNode<>(index, d);
        if(index == 0){
            root = node;
        }
        index++;
        node.leftChild = createBinaryTreePre(data);
        node.rightChild = createBinaryTreePre(data);
        return node;
    }


    /**
     * 层序遍历--利用队列
     *
     */
    public void leverOrder(TreeNode node){
        if(node == null)
            return;
        TreeNode n = root;
        LinkedList<TreeNode> ll = new LinkedList<>();
        ll.add(root);
        while (!ll.isEmpty()){
            n = ll.poll();
            System.out.println(n.data);
            if(n.leftChild!=null)
                ll.offer(n.leftChild);
            if(n.rightChild!=null)
                ll.offer(n.rightChild);
        }
    }

    /**
     * 通过层序遍历的数据序列反向生成二叉树
     *              A
     *         B            C
     *      D     E      #      F
     *    #   # #   #         #    #
     *
     * 对应层序序列：ABCDE#F######
     *
     */
    public void createBinaryTreeLevel(String str){
        ArrayList<String> data = new ArrayList<>();
        for(int i = 0; i < str.length(); i++)
            data.add(str.substring(i, i+1));
        createBinaryTreeLevel(data);
    }

    private int indexLevel = 0;
    private void createBinaryTreeLevel(ArrayList<String> data){
        LinkedList<TreeNode<String>> ll = new LinkedList<>();
        TreeNode<String> n = new TreeNode<>(indexLevel, data.get(indexLevel));
        ll.add(n);
        String d;
        while (!ll.isEmpty()){
            n = ll.pop();
            if(indexLevel == 0)
                root = n;
            indexLevel++;
            d = data.get(indexLevel);
            if(!d.equals("#")){
                n.leftChild = new TreeNode(indexLevel, d);
                ll.add(n.leftChild);
            }
            indexLevel++;
            d = data.get(indexLevel);
            if(!data.get(indexLevel).equals("#")) {
                n.rightChild = new TreeNode(indexLevel, d);
                ll.add(n.rightChild);
            }
        }
    }

    /**
     * 查找二叉树增加结点--递归实现
     *
     */
    public void putSearchTree(int data){
        putSearchTree(root, data);
    }
    public int indexSearch = 0;
    public TreeNode<Integer> putSearchTree(TreeNode<Integer> node, int data){
        if(root == null){
            root = new TreeNode(indexSearch, data);
            indexSearch++;
            return root;
        }
        if(node == null){
            node = new TreeNode(indexSearch, data);
            indexSearch++;
            return node;
        }
        if(data < node.data){
            node.leftChild = putSearchTree(node.leftChild, data);
        }
        else if(data > node.data){
            node.rightChild = putSearchTree(node.rightChild, data);
        }
        else ;
        return node;
    }


    /**
     * 查找二叉树增加结点--非递归实现
     *
     */
    public void nonRecPutSearchTree(int data){
        TreeNode<Integer> node = null;
        if(root == null){
            node = new TreeNode<>(indexSearch, data);
            root = node;
            indexSearch++;
            return;
        }
        node = root;
        TreeNode<Integer> parent = null;
        while (node != null){
            parent = node;
            if(data < node.data)
                node = node.leftChild;
            else if(data > node.data)
                node = node.rightChild;
            else
                return;
        }
        node = new TreeNode<>(indexSearch, data);
        if(data<parent.data)
            parent.leftChild = node;
        else
            parent.rightChild = node;
        indexSearch++;
    }

    /**
     * 查找二叉树删除结点
     *
     */
    public void deleteNode(int data) throws Exception {
        TreeNode<Integer> node = getNode(data);
        if(node == null)
            throw new Exception("不存在该结点");
        TreeNode<Integer> parent = getParent(data);
        //既没有左孩子，也没有右孩子
        if(node.leftChild==null && node.rightChild==null){
            if(parent.leftChild==node)
                parent.leftChild = null;
            if(parent.rightChild==node)
                parent.rightChild = null;
        }
        //只有左孩子，没有右孩子
        if(node.leftChild!=null && node.rightChild==null){
            if(parent.leftChild==node)
                parent.leftChild = node.leftChild;
            if(parent.rightChild==node)
                parent.rightChild = node.leftChild;
        }
        //只有右孩子，没有左孩子
        if(node.leftChild==null && node.rightChild!=null){
            if(parent.leftChild==node)
                parent.leftChild = node.rightChild;
            if(parent.rightChild==node)
                parent.rightChild = node.rightChild;
        }
        //左右孩子都有
        if(node.leftChild!=null && node.rightChild!=null){
            TreeNode<Integer> next = getNextNode(data);
            deleteNode(next.data);
            node.data = next.data;
        }
    }

    //查找结点
    private TreeNode<Integer> getNode(int data){
        TreeNode<Integer> node = root;
        while (node != null){
            if(node.data == data)
                return node;
            else if(node.data > data)
                node = node.leftChild;
            else
                node = node.rightChild;
        }
        return null;
    }

    //查找结点的父节点
    private TreeNode<Integer> getParent(int data) {
        TreeNode<Integer> node = root;
        TreeNode<Integer> parent = null;
        while (node!=null){
            if(node.data == data)
                return parent;
            parent = node;
            if(node.data < data)
                node = node.rightChild;
            else
                node = node.leftChild;
        }
        return null;
    }

    //查找后继节点
    private TreeNode<Integer> getNextNode(int data) {
        if(root == null)
            return null;
        TreeNode<Integer> node = getNode(data);
        TreeNode<Integer> next = null;
        //分情况：node有右节点，无右结点
        if(node.rightChild!=null){
            next = node.rightChild;
            while (next.leftChild!=null)
                next = next.leftChild;
            return next;
        }else{
            TreeNode<Integer> parent = getParent(data);
            while(getParent(parent.getData())!=null){
                parent = getParent(parent.getData());
            }
            return parent;
        }
    }

    /**
     * 中序遍历
     *
     */
    public void midOrder(TreeNode node){
        if(node == null)
            return;
        midOrder(node.leftChild);
        System.out.println(node.data);
        midOrder(node.rightChild);
    }


    public static void main(String[] args) throws Exception {
//        BinaryTree binaryTree = new BinaryTree();
//        binaryTree.createBinaryTree();
//        int height = binaryTree.getHeight();
//        System.out.println("treeHeihgt:"+height);
//        int size = binaryTree.getSize();
//        System.out.println("treeSize:"+size);
//		  binaryTree.preOrder(binaryTree.root);
//        binaryTree.nonRecOrder(binaryTree.root);
//        binaryTree.leverOrder(binaryTree.root);

//        BinaryTree bt = new BinaryTree();
//        bt.createBinaryTreePre("ABD##E##C#F##");
//        bt.preOrder(bt.root);
//        bt.createBinaryTreeLevel("ABCDE#F######");
//        bt.preOrder(bt.root);

//        测试查找二叉树
//        BinaryTree bt = new BinaryTree();
//        int[] intArray = new int[]{23, 34 , 43, 23, 34, 54, 23, 4, 6};
//        for(int i : intArray)
//            bt.putSearchTree(i);
//        bt.midOrder(bt.root);
//        System.out.println("index="+bt.indexSearch);
//        System.out.println("#########################");
//        BinaryTree bt2 = new BinaryTree();
//        int[] intArray2 = new int[]{2, 3, 4};
//        for(int i : intArray2)
//            bt2.putSearchTree(i);
//        bt2.midOrder(bt2.root);
//        System.out.println("index="+bt2.indexSearch);

//      测试查找二叉树的删除功能
        BinaryTree bt = new BinaryTree();
        int[] intArray = new int[]{23, 34 , 43, 23, 34, 54, 23, 4, 6, 35, 2, 1, 5, 10};
        for(int i : intArray)
            bt.putSearchTree(i);
        bt.midOrder(bt.root);
        System.out.println("#####################");
        bt.deleteNode(54);
        bt.midOrder(bt.root);
    }
}

