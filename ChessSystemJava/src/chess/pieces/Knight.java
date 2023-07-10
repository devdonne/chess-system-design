package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "N";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getRows()];

        Position p = new Position(0, 0);
        
        colorPossibleMoves(p, mat, 2, 1);
    
        colorPossibleMoves(p, mat, 2, -1);
       
        colorPossibleMoves(p, mat, -2, 1);
     
        colorPossibleMoves(p, mat, -2, -1);
    
        colorPossibleMoves(p, mat,  -1, -2);
       
        colorPossibleMoves(p, mat, 1, -2);
       
        colorPossibleMoves(p, mat, -1, 2);
       
        colorPossibleMoves(p, mat, 1, 2);
        return mat;
    }

    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }

    private void colorPossibleMoves(Position p, boolean[][] mat, int row, int column) {
        p.setValues(position.getRow() + row, position.getColumn() + column);

        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
    }

}
