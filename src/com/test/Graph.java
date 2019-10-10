package com.test;

public class Graph {
    private int vertexNum;
    private int[] vertex;
    private int[][] matrix;

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


    public static void main(String[] args){
        Graph g = new Graph(5);
        int[] i0 = new int[]{0, -1, -1, -1, 6};
        int[] i1 = new int[]{9, 0, 3, -1, -1};
        int[] i2 = new int[]{2, -1, 0, 5, -1};
        int[] i3 = new int[]{-1, -1, -1, 0, 1};
        int[] i4 = new int[]{-1, -1, -1, -1, 0};
        g.matrix[0] = i0;
        g.matrix[1] = i1;
        g.matrix[2] = i2;
        g.matrix[3] = i3;
        g.matrix[4] = i4;

//        int degree = g.getOutDegree(2);
//        System.out.println(degree);
        int weight = g.getWeight(1, 4);
        System.out.println(weight);

    }



}
