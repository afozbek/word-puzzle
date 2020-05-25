package com.wordpuzzle;

import javax.swing.*;
import java.awt.*;

public class ClientGameBoard extends JFrame {

    private char[][] gameCharacters;

    public ClientGameBoard(String title) throws HeadlessException {
        super(title);
//        this.setSize(new Dimension(1000, 600));
    }

    public char[][] getGameCharacters() {
        return gameCharacters;
    }

    public void setGameCharacters(char[][] gameCharacters) {
        this.gameCharacters = gameCharacters;
    }
}
