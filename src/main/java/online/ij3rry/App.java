package online.ij3rry;

import online.ij3rry.chess_validator.Validator;
import online.ij3rry.chess_validator.enums.CHESS_PIECES;

public class App {
    public static void main(String[] args) {
        int[] fromLocation = {7, 4};
        int[] toLocation = {6,4};
        CHESS_PIECES[][] board = {
                {CHESS_PIECES.R, CHESS_PIECES.N, CHESS_PIECES.B, CHESS_PIECES.e, CHESS_PIECES.K, CHESS_PIECES.e, CHESS_PIECES.N, CHESS_PIECES.R},
                {CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.P, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.P, CHESS_PIECES.e},
                {CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e},
                {CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e},
                {CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e},
                {CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e, CHESS_PIECES.e},
                {CHESS_PIECES.e, CHESS_PIECES.p, CHESS_PIECES.p, CHESS_PIECES.p, CHESS_PIECES.e, CHESS_PIECES.p, CHESS_PIECES.p, CHESS_PIECES.p},
                {CHESS_PIECES.r, CHESS_PIECES.n, CHESS_PIECES.b, CHESS_PIECES.q, CHESS_PIECES.k, CHESS_PIECES.b, CHESS_PIECES.n, CHESS_PIECES.r}
        };
        Validator validator = new Validator(fromLocation, toLocation, board);

        Boolean result = validator.validateMovement();
        System.out.println("Validation result :: " + result);
    }
}