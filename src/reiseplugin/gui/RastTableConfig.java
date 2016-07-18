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

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import reiseplugin.WTFException;
import reiseplugin.data.Parameter;
import reiseplugin.data.Rast;

/**
 * This class contains the Model, the Renderer and the Editor for the JTable representing the Rasten.
 * @author Luca Corbatto {@literal <luca@corbatto.de>}
 */
public class RastTableConfig implements TableButtonListener {
    private static final String[] COLUMNS = {
        "Von", "Bis", "-Ersch.", "-Überanst.", "Löschen"
    };
    
    private static final Pattern TIME_PATTERN = Pattern.compile("^(\\d{1,2})(:00)?$");
    
    private Parameter data = null;
    
    private Model model = null;
    private Renderer renderer = null;
    private Editor editor = null;
    
    /**
     * Creates a new RastTableConfig.
     */
    public RastTableConfig() {
        this.model = new Model();
        this.renderer = new Renderer();
        this.editor = new Editor();
        this.editor.addTableButtonListener(this);
    }
    
    /**
     * Returns the Parameter.
     * @return The Parameter.
     */
    public Parameter getParameter() {
        return this.data;
    }

    /**
     * Sets the Parameter and triggers a redraw.
     * @param data The new Parameter.
     */
    public void setParameter(Parameter data) {
        this.data = data;
        this.model.fireTableStructureChanged();
        this.model.fireTableDataChanged();
    }
    
    /**
     * Returns the Model.
     * @return The Model.
     */
    public Model getModel() {
        return this.model;
    }

    /**
     * Returns the Renderer.
     * @return The Renderer.
     */
    public Renderer getRenderer() {
        return renderer;
    }

    /**
     * Returns the Editor.
     * @return The Editor.
     */
    public Editor getEditor() {
        return editor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tableButtonClicked(int row, int col) {
        this.data.removeRast(this.data.getRast(row));
    }
    
    /**
     * The Model of the RastTable.
     * This class implements the access to the table data and the ability to change the data.
     */
    public class Model extends AbstractTableModel {
        /**
         * {@inheritDoc}
         */
        @Override
        public int getRowCount() {
            if(RastTableConfig.this.data == null) {
                return 0;
            }
            return RastTableConfig.this.data.getErholung().size();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getColumnCount() {
            return RastTableConfig.COLUMNS.length;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if(RastTableConfig.this.data == null) {
                return null;
            }
            Rast rast = RastTableConfig.this.data.getErholung().get(rowIndex);
            switch(columnIndex) {
                case 0:
                    return String.format("%02d:00", rast.getStart());
                case 1:
                    return String.format("%02d:00", rast.getEnde());
                case 2:
                    return rast.getErschöpfungProStunde();
                case 3:
                    return rast.getÜberanstrengungProStunde();
                case 4:
                    return "X";
                default:
                    throw new WTFException();
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            if(columnIndex == 4) {
                return;
            }
            Rast rast = RastTableConfig.this.data.getErholung().get(rowIndex);
            String text = (String)aValue;
            Matcher m;
            switch(columnIndex) {
                case 0:
                    m = RastTableConfig.TIME_PATTERN.matcher(text);
                    if(m.matches()) {
                        rast.setStart(Integer.parseInt(m.group(1)));
                    }
                    break;

                case 1:
                    m = RastTableConfig.TIME_PATTERN.matcher(text);
                    if(m.matches()) {
                        rast.setEnde(Integer.parseInt(m.group(1)));
                    }
                    break;

                case 2:
                    try {
                        rast.setErschöpfungProStunde(Integer.parseInt(text));
                    } catch(Exception e) {}
                    break;

                case 3:
                    try {
                        rast.setÜberanstrengungProStunde(Integer.parseInt(text));
                    } catch(Exception e) {}
                    break;

                default:
                    throw new WTFException();
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getColumnName(int column) {
            return RastTableConfig.COLUMNS[column];
        }
    }
    
    /**
     * The Renderer of the RastTable.
     * This makes the delete-buttons look like buttons.
     */
    public class Renderer extends DefaultTableCellRenderer {
        /**
         * {@inheritDoc}
         */
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if(column != 4) {
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
            
            JButton btn = new JButton((String)value);
            return btn;
        }
    }
    
    /**
     * The Editor of the RastTable.
     * This makes the buttons actually trigger and makes the other fields editable.
     */
    public class Editor extends DefaultCellEditor {
        
        private final List<TableButtonListener> listeners;
        
        /**
         * Creates a new editor.
         */
        public Editor() {
            super(new JTextField());
            this.listeners = new ArrayList<>();
        }
        
        /**
         * Registers a new TableButtonListener.
         * @param e A TableButtonListener.
         * @return true (as specified in {@link java.util.Collection.add})
         */
        public boolean addTableButtonListener(TableButtonListener e) {
            return listeners.add(e);
        }

        /**
         * Removed a TableButtonListener.
         * @param e The TableButtonListener to be removed.
         */
        public void removeTableButtonListener(TableButtonListener e) {
            listeners.remove(e);
        }
        
        /**
         * Notifies the TableButtonListeners.
         * @param row The row of the clicked button.
         * @param column The column of the clicked button.
         */
        private void fireButtonClicked(int row, int column) {
            this.listeners.stream().forEach(l -> {l.tableButtonClicked(row, row);});
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if(column == 4) {
                this.fireButtonClicked(row, column);
                return RastTableConfig.this.renderer.getTableCellRendererComponent(table, value, true, true, row, column);
            }
            return super.getTableCellEditorComponent(table, value, isSelected, row, column);
        }
    }
}
