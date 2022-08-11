package SudokuSolver.src;

class SudokuSolver {
    
    public boolean isSafe(char[][] board, int row, int col, char num) {
        for(int i=0; i<9; i++) {
            if(board[i][col]==num || board[row][i]==num) return false;
        }
        
        int[] rBorder = findBorder(row);
        int[] cBorder = findBorder(col);
        
        for(int i=rBorder[0]; i<=rBorder[1]; i++) {
            for(int j=cBorder[0]; j<=cBorder[1]; j++) {
                if(board[i][j]==num) return false;
            }
        }
        return true;
    }
    
    public int[] findBorder(int r) {
        int[] res = new int[2];
        if(r<=2) {
            res[1] = 2;
        }
        else if(r<=5) {
            res[0] = 3;
            res[1] = 5;
        }
        else {
            res[0] = 6;
            res[1] = 8;
        }
        return res;
    }
    
    public boolean solver(char[][] board, int row, int col) {
        if(col==9) {
            row += 1;
            col = 0;
        }
        if(row==9) return true;
        if(board[row][col]!='.') return solver(board, row, col+1);
        for(char i='1'; i<='9'; i++) {
            if(isSafe(board, row, col, i)) {
                board[row][col] = i;
                if(solver(board, row, col+1)) return true;
                board[row][col] = '.';
            }
        }
        return false;
    }
    
    public char[][] solveSudoku(char[][] board) {
        solver(board, 0, 0);
        return board;
    }
}
