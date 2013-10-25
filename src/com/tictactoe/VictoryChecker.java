package com.tictactoe;

public class VictoryChecker {

    public static final char DRAW_SYMBOL = '\u0000';

    public static final char CONTINUE_SYMBOL = '\u0001';

    static public char victoryCode(Field field, char p1Symbol, char p2Symbol) {
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
