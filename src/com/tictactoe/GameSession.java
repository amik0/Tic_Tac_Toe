package com.tictactoe;

import java.util.HashMap;
import java.util.Map;

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
            Map<Character, Integer> counter = new HashMap<Character, Integer>();
            for (int j = 0; j < field.fieldSize; j++) {
                char c = field.getCell(i, j);
                if (counter.containsValue(c)) {
                    counter.put(c, counter.get(c) + 1);
                } else {
                    counter.put(c, 1);
                }
            }
            if (counter.get(p1Symbol) != null &&
                counter.get(p1Symbol) == field.fieldSize) {
                return p1Symbol;
            }
            if (counter.get(p2Symbol) != null &&
                    counter.get(p2Symbol) == field.fieldSize) {
                return p2Symbol;
            }
        }
        for (int j = 0; j < field.fieldSize; j++) {
            Map<Character, Integer> counter = new HashMap<Character, Integer>();
            for (int i = 0; i < field.fieldSize; i++) {
                char c = field.getCell(i, j);
                if (counter.containsValue(c)) {
                    counter.put(c, counter.get(c) + 1);
                } else {
                    counter.put(c, 1);
                }
            }
            if (counter.get(p1Symbol) != null &&
                    counter.get(p1Symbol) == field.fieldSize) {
                return p1Symbol;
            }
            if (counter.get(p2Symbol) != null &&
                    counter.get(p2Symbol) == field.fieldSize) {
                return p2Symbol;
            }
        }
        Map<Character, Integer> counter = new HashMap<Character, Integer>();
        for (int i = 0; i < field.fieldSize; i++) {
            char c = field.getCell(i, i);
            if (counter.containsValue(c)) {
                counter.put(c, counter.get(c) + 1);
            } else {
                counter.put(c, 1);
            }
        }
        if (counter.get(p1Symbol) != null &&
                counter.get(p1Symbol) == field.fieldSize) {
            return p1Symbol;
        }
        if (counter.get(p2Symbol) != null &&
                counter.get(p2Symbol) == field.fieldSize) {
            return p2Symbol;
        }
        counter = new HashMap<Character, Integer>();
        for (int i = 0; i < field.fieldSize; i++) {
            char c = field.getCell(i, field.fieldSize - i - 1);
            if (counter.containsValue(c)) {
                counter.put(c, counter.get(c) + 1);
            } else {
                counter.put(c, 1);
            }
        }
        if (counter.get(p1Symbol) != null &&
                counter.get(p1Symbol) == field.fieldSize) {
            return p1Symbol;
        }
        if (counter.get(p2Symbol) != null &&
                counter.get(p2Symbol) == field.fieldSize) {
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
