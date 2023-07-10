package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {

    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] possibleMoves() {

        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getRows()];

        Position p = new Position(0, 0);
        //nw
        colorPossibleMoves(p, mat, -1, -1);
        //ne
        colorPossibleMoves(p, mat, -1, 1);
        //sw
        colorPossibleMoves(p, mat, 1, -1);
        //se
        colorPossibleMoves(p, mat, 1, 1);
        
        return mat;
    }

    private void colorPossibleMoves(Position p, boolean[][] mat, int row, int column) {
        p.setValues(position.getRow() + row, position.getColumn() + column);

        while (getBoard().positionExists(p) && !getBoard().thereisAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() + column);
            p.setRow(p.getRow() + row);
        }

        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
    }

}
