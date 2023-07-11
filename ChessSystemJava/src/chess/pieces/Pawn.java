package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
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
            //# special move en passant

            if (position.getRow() == 3) {
                testEnPassant(mat, -1, -1);
                testEnPassant(mat, 1, -1);
            }
        } else {
            parseTheValue(p, mat, -1);

            //# special move en passant
            if (position.getRow() == 4) {
                testEnPassant(mat, -1, 1);
                testEnPassant(mat, 1, 1);
            }
        }
        return mat;
    }

    private void testEnPassant(boolean[][] mat, int value1, int value2) {
        Position p = new Position(position.getRow(), position.getColumn() + value1);

        if (getBoard().positionExists(p) && isThereOpponentPiece(p) && getBoard().piece(p) == chessMatch.getEnPassantVulnarable()) {
            mat[p.getRow() + value2][p.getColumn()] = true;
        }
    }

    private int getResult(int value1, int value2) {
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
