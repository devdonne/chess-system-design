package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getRows()];

        Position p = new Position(0, 0);

        //above
        colorPossibleMovesAboveAndBelow(p, mat, -1);
        
        //below
        colorPossibleMovesAboveAndBelow(p, mat, 1);
        
        //left
        colorPossibleMovesRightAndLeft(p, mat, -1);
        
        //right
        colorPossibleMovesRightAndLeft(p, mat, 1);

        return mat;
    }
    
    public void colorPossibleMovesRightAndLeft(Position p, boolean[][] mat, int number){
        p.setValues(position.getRow(), position.getColumn() + number);

        while (getBoard().positionExists(p) && !getBoard().thereisAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() + number);
        }

        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
    }
    
    public void colorPossibleMovesAboveAndBelow(Position p, boolean[][] mat, int number){
        p.setValues(position.getRow() + number, position.getColumn());

        while (getBoard().positionExists(p) && !getBoard().thereisAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() + number);
        }

        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
    }

}
