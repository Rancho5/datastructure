package com.test;

import java.util.LinkedList;

public class Graph {
    private int vertexNum;
    private int[] vertex;
    private int[][] matrix;
    private boolean[] isVisited;
    private Edge[] edges;
    private int[] fatherNode;       //记录父节点，用来判断回环
    private int edgeNum;

    public Graph(int vertexNum){
        this.vertexNum = vertexNum;
        vertex = new int[vertexNum];
        matrix = new int[vertexNum][vertexNum];
        for(int i = 0; i < vertexNum; i++){
            vertex[i] = i;
        }
    }

    /**
     * 获取节点的入度
     *
     */
    public int getInDegree(int index){
        int degree = 0;
        for(int i = 0; i < vertexNum; i++){
            if(matrix[i][index]>0)
                degree++;
        }
        return degree;
    }

    /**
     * 获取节点的出度
     */
    public int getOutDegree(int index){
        int degree = 0;
        for(int i = 0; i < vertexNum; i++){
            if(matrix[index][i]>0)
                degree++;
        }
        return degree;
    }

    /**
     * 获取节点的权值
     */
    public int getWeight(int index1, int index2){
        int weight = matrix[index1][index2];
        return weight;
    }

    /**
     * 获取某个顶点的邻接点
     */
    public int getFirstNeighbor(int index){
        for(int i = 0; i < vertexNum; i++){
            if(matrix[index][i]>0)
                return i;
        }
        return -1;
    }

    /**
     * 获取当前顶点相对当前邻接点的下一个邻接点
     * @param index 当前顶点
     * @param first 当前邻接点
     * @return 下一个邻接点
     */
    public int getNextNeighbor(int index, int first){
        if(first == vertexNum)          //如果当前邻接点已经是最后一个，直接返回-1
            return -1;
        for(int i = first+1; i < vertexNum; i++){
            if(matrix[index][i]>0)
                return i;
        }
        return -1;
    }

    /**
     * 类内部的图的深度优先遍历
     */
    private void depthFirstSearch(int i){
        System.out.println("访问到了"+i);
        isVisited[i] = true;
        int n = getFirstNeighbor(i);
        while (n != -1){
            if(!isVisited[n])
                depthFirstSearch(n);
            n = getNextNeighbor(i, n);
        }
    }

    /**
     * 对外公开的图的深度优先遍历
     */
    public void depthFirstSearch(){
        isVisited = new boolean[vertexNum];
        for(int i = 0; i < vertexNum; i++){
            if(!isVisited[i]){
                depthFirstSearch(vertex[i]);
            }
        }
    }

    /**
     * 图的广度优先遍历
     */
    public void broadFirstSearch(){
        isVisited = new boolean[vertexNum];
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0; i < vertexNum; i++){
            if(!isVisited[i]){
                System.out.println("访问到了"+i);
                isVisited[i] = true;
                queue.add(i);
                while (!queue.isEmpty()){
                    int first = queue.removeFirst();        //移除队列中的第一个元素
                    int n = getFirstNeighbor(i);
                    while (n != -1){
                        if(!isVisited[n]){
                            System.out.println("访问到了"+n);
                            isVisited[n] = true;
                            queue.add(n);
                        }
                        n = getNextNeighbor(first, n);
                    }
                }
            }
        }
    }

    /**
     * 最小生成树之普利姆算法
     */
    public void prim(){
        int min, minId, sum = 0;
        int[] lowcost = new int[vertexNum];     //已经连通的部分与剩下节点的最小距离，0表示已连通
        int[] adjvex = new int[vertexNum];      //未连通节点的最邻近节点
        for(int i = 0; i < vertexNum; i++){     //初始化lowcost数组
            lowcost[i] = matrix[0][i];
        }
        for(int i = 1; i < vertexNum; i++){     //v0为起点，循环只需要从1开始
            min = 10000;                        //初始给min一个很大的值，方便后面找到小值替换
            minId = 0;
            for(int j = 0; j < vertexNum; j++){   //选出lowcost中最小的
                if(lowcost[j]>0 && lowcost[j]<min){
                    min = lowcost[j];
                    minId = j;
                }
            }
            System.out.println(minId+"连接到"+adjvex[minId]+",权值为："+min);
            sum += min;
            lowcost[minId] = 0;
            for(int j = 0; j < vertexNum; j++){
                if((lowcost[j]<0 && matrix[minId][j]>0) || (matrix[minId][j]>0 && matrix[minId][j]<lowcost[j])){
                    lowcost[j] = matrix[minId][j];
                    adjvex[j] = minId;
                }
            }
        }
        System.out.println("最小权值和是："+sum);
    }



    //接下来是 最小生成树之克鲁斯卡尔算法

    public class Edge{
        private int begin;
        private int end;
        private int weight;

        public Edge(int begin, int end, int weight) {
            this.begin = begin;
            this.end = end;
            this.weight = weight;
        }

        public int getBegin() {
            return begin;
        }

        public void setBegin(int begin) {
            this.begin = begin;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }

    /**
     * 根据顶点矩阵生成边集合
     *
     */
    private void setEdge(){
        for(int i = 0; i < vertexNum; i++){
            for(int j = i+1; j < vertexNum; j++){
                if(matrix[i][j]>0)
                    edgeNum++;
            }
        }
        edges = new Edge[edgeNum];
        int index = 0;
        for(int i = 0; i < vertexNum; i++){
            for(int j = i+1; j < vertexNum; j++){
                if(matrix[i][j]>0){
                    Edge e = new Edge(i, j, matrix[i][j]);
                    edges[index++] = e;
                }
            }
        }
    }

    /**
     * 并查集寻找节点的父节点，用来判断是否回环
     *
     */
    //非递归实现
    private int noRecFind(int i){
        int result = i;
        while (fatherNode[result] != result){
            result = fatherNode[result];
        }
        int temp;
        while (i != result){            //路径压缩
            temp = fatherNode[i];
            fatherNode[i] = result;
            i = temp;
        }
        return result;
    }
    //递归实现
    private int find(int i){
        if(fatherNode[i] == i)
            return i;
        return i = find(fatherNode[i]);
    }

    /**
     * kruskal算法
     *
     */
    public void kruskal(){
        this.setEdge();
        fatherNode = new int[vertexNum];        //初始化fatherNode数组
        for(int i = 0; i < vertexNum; i++){
            fatherNode[i] = i;
        }

        for(int i = 0; i < edgeNum; i++){     //冒泡排序将edge按照权重从小到大排序
            for(int j = edgeNum-1; j > i; j--){
                Edge temp;
                if(edges[j].weight<edges[j-1].weight){
                    temp = edges[j-1];
                    edges[j-1] = edges[j];
                    edges[j] = temp;
                }
            }
        }

        int sum = 0;
        for(int i = 0; i < edgeNum; i++){
            if(find(edges[i].begin)!=find(edges[i].end)){
                System.out.println(edges[i].begin+"连接到"+edges[i].end+",权值为："+edges[i].weight);
                sum += edges[i].weight;
                fatherNode[find(edges[i].begin)] = edges[i].end;
            }
        }
        System.out.println("最小权值和是："+sum);

    }


    public static void main(String[] args){
        Graph g = new Graph(9  );
        int[] i0 = new int[]{0,10,-1,-1,-1,11,-1,-1,-1};
        int[] i1 = new int[]{10,0,18,-1,-1,-1,16,-1,12};
        int[] i2 = new int[]{-1,-1,0,22,-1,-1,-1,-1,8};
        int[] i3 = new int[]{-1,-1,22,0,20,-1,-1,16,21};
        int[] i4 = new int[]{-1,-1,-1,20,0,26,-1,7,-1};
        int[] i5 = new int[]{11,-1,-1,-1,26,0,17,-1,-1};
        int[] i6 = new int[]{-1,16,-1,-1,-1,17,0,19,-1};
        int[] i7 = new int[]{-1,-1,-1,16,7,-1,19,0,-1};
        int[] i8 = new int[]{-1,12,8,21,-1,-1,-1,-1,0};

        g.matrix[0] = i0;
        g.matrix[1] = i1;
        g.matrix[2] = i2;
        g.matrix[3] = i3;
        g.matrix[4] = i4;
        g.matrix[5] = i5;
        g.matrix[6] = i6;
        g.matrix[7] = i7;
        g.matrix[8] = i8;

//        int degree = g.getOutDegree(2);
//        System.out.println(degree);

//        int weight = g.getWeight(1, 4);
//        System.out.println(weight);

//        g.depthFirstSearch();
//        g.broadFirstSearch();

//        g.prim();

        g.kruskal();
    }



}
