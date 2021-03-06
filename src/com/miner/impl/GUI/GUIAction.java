package com.miner.impl.GUI;

import com.miner.*;
import com.miner.impl.base.BaseAction;
import com.miner.interfaces.GeneratorBoard;
import com.miner.interfaces.MinerLogic;

import java.awt.event.*;

import static com.miner.Constants.CELL_PADDING;

public class GUIAction extends BaseAction implements ActionListener, MouseListener, HotKeyListener {
    private GUIBoard board;

    public GUIAction(GeneratorBoard generatorBoard, GUIBoard board, MinerLogic minerLogic) {
        super(generatorBoard, board, minerLogic);
        this.board = board;
        this.board.addMouseListener(this);
        this.board.setEventListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {    }

    @Override
    public void mousePressed(MouseEvent e) {    }

    @Override
    public void mouseEntered(MouseEvent e) {    }

    @Override
    public void mouseExited(MouseEvent e) {    }

    @Override
    public void mouseReleased(MouseEvent e) {

        int x = e.getX()/CELL_PADDING;
        int y = e.getY()/CELL_PADDING;

        if (!this.ifClickInsideBoard(x, y)) return;

        if (e.getButton()==MouseEvent.BUTTON1) {
            this.select(x,y, Constants.SUGGESTION.EMPTY);
        }
        else if (e.getButton()==MouseEvent.BUTTON3) {
            if (this.board.cells[x][y].isSuggestBomb()) this.select(x, y, Constants.SUGGESTION.UNKNOWN);
            else this.select(x,y, Constants.SUGGESTION.BOMB);
        }

        board.repaint();
    }

    private boolean ifClickInsideBoard(int x, int y) {
        if (this.board.cells!=null && (x<this.board.cells.length && y<this.board.cells[0].length))
            return true;
        else return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.initGame();
    }

    @Override
    public void onKeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_N:
                this.initGame();
                break;
        }
    }
}
