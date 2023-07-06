    package application;

import chess.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Run {

    public static void main(String args[]) {
        ChessMatch chessMatch = new ChessMatch();

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces());

                System.out.println("");

                System.out.println("Source:");
                ChessPosition source = UI.readChessPosition(sc);
                
                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println("");
                System.out.println("Target: ");

                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
            } catch (ChessException | InputMismatchException e) {
                System.out.println(e.getMessage());
                System.out.println("");
                System.out.println("Write anything and press Enter to continue");
                sc.next();
            }
        }

    }
}
