package com.tictactoe;

import java.util.ArrayList;
import java.util.Random;

public class AI extends Player {

    private static final int DEFAULT_LEVEL = 3;

    private final int expLevel;

    private char enemySymbol = 'X';

    public AI() {
        this(DEFAULT_LEVEL);
    }

    public AI(int expLevel) {
        super();
        this.expLevel = expLevel;
    }

    public Cell getMove(Field field) {
        Cell cRoot = bypass(field, playerSymbol, expLevel * 2 - 1);
		return cRoot;
	}

    private Cell bypass(Field field, char symbol, int deep) {
        Cell cell = null;
        ArrayList<Cell> availableArray = new ArrayList<Cell>();
        if (deep == 0) {
            for (int i = 0; i < field.fieldSize; i++) {
                for (int j = 0; j < field.fieldSize; j++) {
                    if (field.getCell(i, j)==field.fieldFiller) {
                        cell = new Cell();
                        cell.x = i;
                        cell.y = j;
                        availableArray.add(cell);
                    }
                }
            }
            if (availableArray.size() > 0) {
                Random random = new Random(System.currentTimeMillis());
                cell = availableArray.get(random.nextInt(availableArray.size()));
            }
            return cell;
        }
        availableArray.clear();
        for (int i = 0; i < field.fieldSize; i++) {
            for (int j = 0; j < field.fieldSize; j++) {
                if (field.getCell(i, j) == field.fieldFiller) {
                    Field newField = new Field(field);
                    newField.setCell(i, j, symbol);
                    char winner = VictoryChecker.victoryCode(newField, playerSymbol, enemySymbol);
                    if (winner == enemySymbol /* || winner == VictoryChecker.DRAW_SYMBOL */ ) {
                        return null;
                    }
                    if (winner == playerSymbol) {
                        cell = new Cell();
                        cell.x = i;
                        cell.y = j;
                        return cell;
                    }
                    cell = bypass(newField, (symbol == playerSymbol) ? enemySymbol : playerSymbol, deep - 1);
                    if (cell != null) {
                        cell = new Cell();
                        cell.x = i;
                        cell.y = j;
                        availableArray.add(cell);
                    }
                }
            }
        }
        if (availableArray.size() > 0) {
            Random random = new Random(System.currentTimeMillis());
            cell = availableArray.get(random.nextInt(availableArray.size()));
        } else {
            for (int i = 0; i < field.fieldSize; i++) {
                for (int j = 0; j < field.fieldSize; j++) {
                    if (field.getCell(i, j)==field.fieldFiller) {
                        cell = new Cell();
                        cell.x = i;
                        cell.y = j;
                        availableArray.add(cell);
                    }
                }
            }
            if (availableArray.size() > 0) {
                Random random = new Random(System.currentTimeMillis());
                cell = availableArray.get(random.nextInt(availableArray.size()));
            }
        }
        return cell;
    }

    public void setEnemySymbol(char enemySymbol) {
        this.enemySymbol = enemySymbol;
    }
}
