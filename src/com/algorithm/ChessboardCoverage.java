package com.algorithm;

//分治法解决棋盘覆盖问题
public class ChessboardCoverage {
    int type = 1;            //用来填补空格的L型物体标志符
    public void boardCoverage(int[][] board){
        int size = board.length;
        //得到异常点坐标
        int specialX = 0;
        int specialY = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(board[i][j]<0){
                    specialX = i;
                    specialY = j;
                }
            }
        }
        boardCoverage(board, size, 0, 0);
        board[specialX][specialY] = 0;
        //用L型物体填充临时异常点
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(board[i][j]<0){
                    board[i][j] = -board[i][j];
                }
            }
        }
        //打印
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.print(" "+board[i][j]);
            }
            System.out.println();
        }
    }

    private void boardCoverage(int[][] board, int size, int beginX, int beginY) {
        if(size==1)
            return;
        //得到异常点的坐标
        int specialX=0;
        int specialY=0;
        for(int i = beginX; i < beginX+size; i++){
            for(int j = beginY; j < beginY+size; j++){
                if(board[i][j]<0){
                    specialX = i;
                    specialY = j;
                }
            }
        }
        //分治,添加临时异常点
        int midX = size/2+beginX-1;
        int midY = size/2+beginY-1;
        if(specialX<=midX && specialY<=midY){
            //如果异常点在左上区域
            board[midX][midY + 1] = -type;
            board[midX + 1][midY] = -type;
            board[midX + 1][midY + 1] = -type;
            type = type%9+1;
        }else if(specialX<=midX && specialY>midY){
            //异常点在右上
            board[midX][midY] = -type;
            board[midX+1][midY] = -type;
            board[midX+1][midY+1] = -type;
            type = type%9+1;
        }else if(specialX>midX && specialY<=midY){
            //异常点在左下
            board[midX][midY] = -type;
            board[midX][midY+1] = -type;
            board[midX+1][midY+1] = -type;
            type = type%9+1;
        }else if(specialX>midX && specialY>midY){
            //异常点在右下
            board[midX][midY] = -type;
            board[midX+1][midY] = -type;
            board[midX][midY+1] = -type;
            type = type%9+1;
        }
        boardCoverage(board, size/2, beginX, beginY);
        boardCoverage(board, size/2, beginX, midY+1);
        boardCoverage(board, size/2, midX+1, beginY);
        boardCoverage(board, size/2, midX+1, midY+1);
    }

    public int[][] createBoard(int size, int specialX, int specialY){
        int[][] board = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                board[i][j] = 0;
            }
        }
        board[specialX][specialY] = -1;
        return board;
    }

    public static void main(String[] args) {
        ChessboardCoverage cbc = new ChessboardCoverage();
        cbc.boardCoverage(cbc.createBoard(16, 0, 1));
    }
}
