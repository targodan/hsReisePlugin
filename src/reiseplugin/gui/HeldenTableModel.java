/*
 * Copyright (C) 2016 Luca Corbatto
 *
 * This file is part of the hsReisePlugin.
 *
 * The hsReisePlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The hsReisePlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package reiseplugin.gui;

import java.util.regex.Matcher;
import javax.swing.table.AbstractTableModel;
import reiseplugin.WTFException;
import reiseplugin.data.Parameter;
import reiseplugin.data.Held;

/**
 * GUI Model for the JTable containing the Helden.
 * @author Luca Corbatto {@literal <luca@corbatto.de>}
 */
public class HeldenTableModel extends AbstractTableModel {
    private static final String[] COLUMNS = {
        "Name", "KO", "Ersch.Mod"
    };
    
    private Parameter data = null;

    /**
     * Returns the Parameter.
     * @return The Parameter.
     */
    public Parameter getParameter() {
        return this.data;
    }

    /**
     * Sets the Parameter and triggers a redraw.
     * @param data The Parameter.
     */
    public void setParameter(Parameter data) {
        this.data = data;
        this.fireTableStructureChanged();
        this.fireTableDataChanged();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getRowCount() {
        if(this.data == null) {
            return 0;
        }
        return this.data.getHeldenCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumnCount() {
        return HeldenTableModel.COLUMNS.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(this.data == null) {
            return null;
        }
        Held held = this.data.getHeld(rowIndex);
        switch(columnIndex) {
            case 0:
                return held.getName();
            case 1:
                return held.getKO();
            case 2:
                return held.getMod();
            default:
                throw new WTFException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Held held = this.data.getHeld(rowIndex);
        String text = (String)aValue;
        Matcher m;
        if(columnIndex == 2) {
            try {
                held.setMod(Integer.parseInt(text));
            } catch(Exception e) {}
        } else {
            throw new WTFException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getColumnName(int column) {
        return HeldenTableModel.COLUMNS[column];
    }
}
