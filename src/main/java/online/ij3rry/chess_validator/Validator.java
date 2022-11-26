package online.ij3rry.chess_validator;

import online.ij3rry.chess_validator.enums.CHESS_PIECES;
import online.ij3rry.chess_validator.enums.PIECE_COLOR;

public class Validator {

    private int[] fromLocation;
    private int[] toLocation;
    private CHESS_PIECES[][] board;

    public Validator(int[] fromLocation,int[] toLocation,CHESS_PIECES[][] board){
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.board = board;
    }
    public Boolean validateMovement(){
        if(!checkFromLocationIsNotEmpty()){
            System.out.println("From Location is Empty :: Invalid Move");
            return Boolean.FALSE;
        }
        if(!checkToLocationIsNotSameColor()){
            System.out.println("Same Color :: Invalid Move");
            return Boolean.FALSE;
        }
        if(!checkToLocationIsNotKing()){
            System.out.println("To Location is king :: Invalid Move");
            return Boolean.FALSE;
        }
        if(!isAValidMovement()){
            System.out.println("Illegal move");
            return Boolean.FALSE;
        }

        System.out.println("<<VALIDATION COMPLETED :: Valid Move>>");
        return Boolean.TRUE;
    }
    private boolean checkFromLocationIsNotEmpty(){
        return board[fromLocation[0]][fromLocation[1]]!=CHESS_PIECES.e;
    }
    private boolean checkToLocationIsNotSameColor(){
        PIECE_COLOR fromColor = getColor(fromLocation,board);
        PIECE_COLOR toColor = getColor(toLocation,board);
        return !fromColor.equals(toColor);
    }

    private boolean checkToLocationIsNotKing(){
        return board[toLocation[0]][toLocation[1]] != CHESS_PIECES.K || board[toLocation[0]][toLocation[1]] != CHESS_PIECES.k;
    }
    private PIECE_COLOR getColor(int[] location, CHESS_PIECES[][] board){
        CHESS_PIECES piece = board[location[0]][location[1]];
        if(piece.ordinal()==0) return PIECE_COLOR.space;
        if(piece.ordinal()<7) return PIECE_COLOR.white;
        if(piece.ordinal()<13) return PIECE_COLOR.black;
        else return PIECE_COLOR.none;
    }

    private boolean isAValidMovement(){
        CHESS_PIECES piece = board[fromLocation[0]][fromLocation[1]];
        switch (piece) {
            case P:
                System.out.println("White Pawn");
                return isPossibleWhitePawnMove(fromLocation,toLocation,board);
            case p:
                System.out.println("Black Pawn");
                return isPossibleBlackPawnMove(fromLocation,toLocation,board);
            case R:
            case r:
                System.out.println("Rook");
                return isPossibleRookMove(fromLocation,toLocation,board);
            case N:
            case n:
                System.out.println("Knight");
                return isPossibleKnightMove(fromLocation,toLocation,board);
            case B:
            case b:
                System.out.println("Bishop");
                return isPossibleBishopMove(fromLocation,toLocation,board);
            case Q:
            case q:
                System.out.println("Queen");
                return isPossibleQueenMove(fromLocation,toLocation,board);
            case K:
            case k:
                System.out.println("King");
                return isPossibleKingMove(fromLocation,toLocation,board);
        }
        return false;
    }

    private boolean isPossibleWhitePawnMove(int[] fromLocation,int[] toLocation,CHESS_PIECES[][] board){
        int moveLen = toLocation[0] - fromLocation[0];
        System.out.println("MOvement len :: "+moveLen);
        //single foreword move
        if(moveLen == 1 && fromLocation[0] != 7 && fromLocation[1] == toLocation[1] && board[toLocation[0]][toLocation[1]] == CHESS_PIECES.e) return true;
        // double foreword move
        if(moveLen == 2 && fromLocation[0] == 1 && fromLocation[1] == toLocation[1] && board[toLocation[0]-1][toLocation[1]] == CHESS_PIECES.e && board[toLocation[0]][toLocation[1]] == CHESS_PIECES.e) return true;
        // capture
        boolean isPossibleCross = fromLocation[1] - toLocation[1] == -1 || fromLocation[1] - toLocation[1] == 1;
        if (moveLen==1 && isPossibleCross && getColor(toLocation,board) == PIECE_COLOR.black) return true;
        return false;
    }

    private boolean isPossibleBlackPawnMove(int[] fromLocation,int[] toLocation,CHESS_PIECES[][] board){
        int moveLen = fromLocation[0] - toLocation[0];
        //single foreword move
        if(moveLen == 1 && fromLocation[0] != 0 && board[toLocation[0]][toLocation[1]] == CHESS_PIECES.e) return true;
        // double foreword move
        if(moveLen == 2 && fromLocation[0] == 6 && board[toLocation[0]+1][toLocation[1]] == CHESS_PIECES.e && board[toLocation[0]][toLocation[1]] == CHESS_PIECES.e) return true;
        // capture
        boolean isPossibleCross = fromLocation[1] - toLocation[1] == -1 || fromLocation[1] - toLocation[1] == 1;
        if (moveLen==1 && isPossibleCross && getColor(toLocation,board) == PIECE_COLOR.white) return true;
        return false;
    }

    private boolean isPossibleRookMove(int[] fromLocation,int[] toLocation,CHESS_PIECES[][] board){
        if(fromLocation[0] == toLocation[0]){
            System.out.println("SAME ROW");
            int startPos,endPos;
            if(fromLocation[1] < toLocation[1]) {
                startPos = fromLocation[1];
                endPos = toLocation[1];
            }else {
                startPos = toLocation[1];
                endPos = fromLocation[1];
            }
            for(int i = startPos+1; i<endPos; i++){
                if(board[fromLocation[0]][i] != CHESS_PIECES.e) return false;
            }
        }else if(fromLocation[1] == toLocation[1]){
            System.out.println("SAME COLUMN");
            int startPos,endPos;
            if(fromLocation[0] < toLocation[0]) {
                startPos = fromLocation[0];
                endPos = toLocation[0];
            }else {
                startPos = toLocation[0];
                endPos = fromLocation[0];
            }
            for(int i = startPos+1; i<endPos; i++){
                System.out.println("[] :: ["+i+","+fromLocation[1]+"]");
                if(board[i][fromLocation[1]] != CHESS_PIECES.e) return false;
            }
        }
        return true;
    }

    private boolean isPossibleKnightMove(int[] fromLocation,int[] toLocation,CHESS_PIECES[][] board){
        if(fromLocation[0] + 3 == toLocation[0] && fromLocation[1] == toLocation[1] + 1) return true;
        if(fromLocation[0] - 3 == toLocation[0] && fromLocation[1] == toLocation[1] + 1) return true;
        if(fromLocation[0] + 3 == toLocation[0] && fromLocation[1] == toLocation[1] - 1) return true;
        if(fromLocation[0] - 3 == toLocation[0] && fromLocation[1] == toLocation[1] - 1) return true;
        if(fromLocation[1] + 3 == toLocation[1] && fromLocation[0] == toLocation[0] + 1) return true;
        if(fromLocation[1] - 3 == toLocation[1] && fromLocation[0] == toLocation[0] + 1) return true;
        if(fromLocation[1] + 3 == toLocation[1] && fromLocation[0] == toLocation[0] - 1) return true;
        if(fromLocation[1] - 3 == toLocation[1] && fromLocation[0] == toLocation[0] - 1) return true;
        return false;
    }

    private boolean isPossibleBishopMove(int[] fromLocation,int[] toLocation,CHESS_PIECES[][] board){
        int rowLen = toLocation[0] - fromLocation[0];
        int colLen = toLocation[1] - fromLocation[1];

        int unsignedRowLen = rowLen < 0 ? rowLen * -1 : rowLen;
        int unsignedColLen = colLen < 0 ? colLen * -1 : colLen;

        if(unsignedColLen != unsignedRowLen) return false;

        int rowIncrementer = rowLen/unsignedRowLen;
        int columnIncrementer = colLen/unsignedColLen;
        int i = fromLocation[0];
        int j = fromLocation[1];
        do{
            i += rowIncrementer;
            j += columnIncrementer;
            if(board[i][j] != CHESS_PIECES.e) return false;
        }while ((i!=toLocation[0]-rowIncrementer) && (j!=toLocation[1]-columnIncrementer));
        return true;
    }

    private boolean isPossibleQueenMove(int[] fromLocation,int[] toLocation,CHESS_PIECES[][] board){
        return isPossibleBishopMove(fromLocation,toLocation,board) && isPossibleRookMove(fromLocation,toLocation,board);
    }

    private boolean isPossibleKingMove(int[] fromLocation,int[] toLocation,CHESS_PIECES[][] board){

        CHESS_PIECES enemyKing = getColor(fromLocation,board) == PIECE_COLOR.black ? CHESS_PIECES.K : CHESS_PIECES.k;
        if(toLocation[0]+1 <= 7 && board[toLocation[0]+1][toLocation[1]] == enemyKing) return false;
        if(toLocation[0]+1 <= 7 && toLocation[1]+1 <= 7 && board[toLocation[0]+1][toLocation[1]+1] == enemyKing) return false;
        if(toLocation[0]+1 <= 7 && toLocation[1]-1 >= 0 && board[toLocation[0]+1][toLocation[1]-1] == enemyKing) return false;
        if(toLocation[0]-1 >= 0 && toLocation[1]+1 <= 7 && board[toLocation[0]-1][toLocation[1]+1] == enemyKing) return false;
        if(toLocation[0]-1 >= 0 && toLocation[1]-1 >= 0 && board[toLocation[0]-1][toLocation[1]-1] == enemyKing) return false;
        if(toLocation[0]-1 >= 0 && board[toLocation[0]-1][toLocation[1]] == enemyKing) return false;
        if(toLocation[1]+1 <= 7 && board[toLocation[0]][toLocation[1]+1] == enemyKing) return false;
        if(toLocation[1]-1 >= 0 && board[toLocation[0]][toLocation[1]-1] == enemyKing) return false;

        int rowMoveLen = fromLocation[0] - toLocation[0];
        int colMoveLen = fromLocation[1] - toLocation[1];

        return (rowMoveLen == 1 || rowMoveLen == -1 || rowMoveLen == 0) && (colMoveLen == 1 || colMoveLen == -1 || colMoveLen == 0);
    }
}