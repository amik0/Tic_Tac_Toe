package com.tictactoe;

//import java.util.HashMap;
//import java.util.Map;

public class GameSession {

    private static final char DRAW_SYMBOL = '\u0000';

    private static final char CONTINUE_SYMBOL = '\u0001';

	private Player player1;

	private Player player2;

	private Field field;

	private void refreshScreen() {
        System.out.println();
        for (int j = 0; j < field.fieldSize; j++) {
            for (int i = 0; i < field.fieldSize; i++) {
                System.out.print("[" + field.getCell(i, j) + "]");
            }
            System.out.println();
        }
        System.out.println();
    }

	public void play() {
        field = new Field();
        preparePlayers();
        refreshScreen();
        char winSymbol;
        boolean turnPlayer = true;
        do {
            Cell move;
            do {
                if (turnPlayer) {
                    move = player1.getMove(field);
                } else {
                    move = player2.getMove(field);
                }
            } while (move == null);
            field.setCell(move.x, move.y, turnPlayer ? player1.getPlayerSymbol() : player2.getPlayerSymbol());
            refreshScreen();
            turnPlayer = !turnPlayer;
            winSymbol = winner();
        } while (winSymbol == CONTINUE_SYMBOL);
        if (winSymbol == DRAW_SYMBOL) {
            System.out.println("!!!DRAW!!!");
        } else {
            System.out.println("Winner is " +
                    (winSymbol==player1.getPlayerSymbol() ? player1.getName() : player2.getName()) +"!!!");
        }
    }

	private void preparePlayers() {
        player1 = new Human();
        player1.setPlayerName("Player 1");
        player1.setPlayerSymbol('X');
        System.out.println("You are " + player1.getName() + ". Your symbol is \"" + player1.getPlayerSymbol() + "\"");
        player2 = new Human();
        player2.setPlayerName("Player 2");
        player2.setPlayerSymbol('0');
        System.out.println("You are " + player2.getName() + ". Your symbol is \"" + player2.getPlayerSymbol() + "\"");
        System.out.println("\"X\" moves first!");
    }

    private char winner() {
        char p1Symbol = player1.getPlayerSymbol();
        char p2Symbol = player2.getPlayerSymbol();
        for (int i = 0; i < field.fieldSize; i++) {
            int p1Counter = 0;
            int p2Counter = 0;
            for (int j = 0; j < field.fieldSize; j++) {
                char c = field.getCell(i, j);
                if (c == p1Symbol) {
                    p1Counter++;
                }
                if (c == p2Symbol) {
                    p2Counter++;
                }
            }
            if (p1Counter == field.fieldSize) {
                return p1Symbol;
            }
            if (p2Counter == field.fieldSize) {
                return p2Symbol;
            }
        }
        for (int j = 0; j < field.fieldSize; j++) {
            int p1Counter = 0;
            int p2Counter = 0;
            for (int i = 0; i < field.fieldSize; i++) {
                char c = field.getCell(i, j);
                if (c == p1Symbol) {
                    p1Counter++;
                }
                if (c == p2Symbol) {
                    p2Counter++;
                }
            }
            if (p1Counter == field.fieldSize) {
                return p1Symbol;
            }
            if (p2Counter == field.fieldSize) {
                return p2Symbol;
            }
        }
        int p1Counter = 0;
        int p2Counter = 0;
        for (int i = 0; i < field.fieldSize; i++) {
            char c = field.getCell(i, i);
            if (c == p1Symbol) {
                p1Counter++;
            }
            if (c == p2Symbol) {
                p2Counter++;
            }
        }
        if (p1Counter == field.fieldSize) {
            return p1Symbol;
        }
        if (p2Counter == field.fieldSize) {
            return p2Symbol;
        }
        p1Counter = 0;
        p2Counter = 0;
        for (int i = 0; i < field.fieldSize; i++) {
            char c = field.getCell(i, field.fieldSize - i - 1);
            if (c == p1Symbol) {
                p1Counter++;
            }
            if (c == p2Symbol) {
                p2Counter++;
            }
        }
        if (p1Counter == field.fieldSize) {
            return p1Symbol;
        }
        if (p2Counter == field.fieldSize) {
            return p2Symbol;
        }
        for (int j = 0; j < field.fieldSize; j++) {
            for (int i = 0; i < field.fieldSize; i++) {
                if (field.getCell(i, j) == field.fieldFiller) {
                    return CONTINUE_SYMBOL;
                }
            }
        }
        return DRAW_SYMBOL;
    }

}
