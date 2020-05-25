package com.wordpuzzle;

import java.io.Serializable;

public class ServerToClientBoard implements Serializable {

    private static final long serialVersionUID = -4886374970227649037L;

    private char[][] gameCharacters;

    public ServerToClientBoard(char[][] gameCharacters) {
        this.gameCharacters = gameCharacters;
    }

    public char[][] getGameCharacters() {
        return gameCharacters;
    }

    public void setGameCharacters(char[][] gameCharacters) {
        this.gameCharacters = gameCharacters;
    }
}
