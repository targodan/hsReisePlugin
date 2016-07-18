/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reiseplugin.gui;

import java.util.EventListener;

/**
 * Interface for listening to table button clicks.
 * @author Luca Corbatto<luca@corbatto.de>
 */
public interface TableButtonListener extends EventListener {
    /**
     * Invoked when a button in a Table was clicked.
     * @param row The row of the clicked button.
     * @param col The column of the clicked button.
     */
    public void tableButtonClicked(int row, int col);
}
