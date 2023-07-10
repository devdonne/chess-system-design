package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getRows()];

        Position p = new Position(0, 0);

        if (getColor() == Color.WHITE) {
            parseTheValue(p, mat, 1);
        } else {
            parseTheValue(p, mat, -1);
        }
        return mat;
    }
    
    private int getResult (int value1, int value2){
        return value1 * value2;
    }

    private void parseTheValue(Position p, boolean[][] mat, int value) {
        colorPossibleMoves(p, mat, getResult(-1, value), 0, true);
        Position p2 = new Position(position.getRow() - getResult(1, value), position.getColumn());

        if (getMoveCount() == 0 && getBoard().positionExists(p2) && !getBoard().thereisAPiece(p2)) {
            colorPossibleMoves(p, mat, getResult(-2, value), 0, true);
        }

        colorPossibleMoves(p, mat, getResult(-1, value), -1, false);
        colorPossibleMoves(p, mat, getResult(-1, value), 1, false);
    }

    private void colorPossibleMoves(Position p, boolean[][] mat, int row, int column, boolean control) {
        p.setValues(position.getRow() + row, position.getColumn() + column);
        if (control) {
            if (getBoard().positionExists(p) && !getBoard().thereisAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
        } else {
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
        }
    }
}
