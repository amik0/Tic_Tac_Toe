package com.tictactoe;

public class Field {

    private static final char DEFAULT_FILLER = ' ';

    private static final int DEFAULT_SIZE = 3;

	private final char[][] field;

	public final int fieldSize;

	public final char fieldFiller;

	public Field(int size) {
        fieldSize = size;
        field = new char[fieldSize][fieldSize];
        fieldFiller = DEFAULT_FILLER;
        clearField();
	}

    public Field() {
        this(DEFAULT_SIZE);
    }

    public Field(Field field) {
        this(field.fieldSize);
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                this.field[i][j] = field.getCell(i, j);
            }
        }
    }

    private void clearField() {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                field[i][j] = fieldFiller;
            }
        }
	}

	public char getCell(int x, int y) {
		return field[x][y];
	}

	public void setCell(int x, int y, char value) {
        field[x][y] = value;
	}

}
