package com.test;

public class Graph {
    private int vertexNum;
    private int[] vertex;
    private int[][] matrix;
    private boolean[] isVisited;

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
     *
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
     *
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
     *
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
     *
     */
    public void depthFirstSearch(){
        isVisited = new boolean[vertexNum];
        for(int i = 0; i < vertexNum; i++){
            if(!isVisited[i]){
                depthFirstSearch(vertex[i]);
            }
        }
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

        g.depthFirstSearch();

    }



}
