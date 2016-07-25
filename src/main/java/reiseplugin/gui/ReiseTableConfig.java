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

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import reiseplugin.data.ErgebnisTag;
import reiseplugin.data.Parameter;
import reiseplugin.data.Rast;

/**
 * This class contains the
 * {@link reiseplugin.gui.ReiseTableConfig.Model ReiseTableConfig.Model} and
 * the {@link reiseplugin.gui.ReiseTableConfig.Renderer ReiseTableConfig.Renderer}
 * for the {@link javax.swing.JTable JTable} representing the calculated results.
 * @author Luca Corbatto {@literal <luca@corbatto.de>}
 */
public class ReiseTableConfig {
    private ErgebnisTag data = null;
    private Parameter parameter = null;
    
    private final Model model;
    private final Renderer renderer;

    /**
     * Creates a new {@link reiseplugin.gui.ReiseTableConfig ReiseTableConfig}.
     */
    public ReiseTableConfig() {
        this.model = new Model();
        this.renderer = new Renderer();
    }
    
    /**
     * Returns the currently shown calculation result.
     * @return 
     */
    public ErgebnisTag getData() {
        return data;
    }

    /**
     * Sets the new calculation result and triggers a redraw.
     * @param data 
     */
    public void setData(ErgebnisTag data) {
        if(this.data == null || !this.data.equals(data)) {
            this.data = data;
            this.model.fireTableStructureChanged();
            this.model.fireTableDataChanged();
        }
    }

    /**
     * Returns the {@link reiseplugin.data.Parameter Parameter}.
     * @return The {@link reiseplugin.data.Parameter Parameter}.
     */
    public Parameter getParameter() {
        return parameter;
    }

    /**
     * Sets the {@link reiseplugin.data.Parameter Parameter} and triggers a redraw.
     * @param parameter The new {@link reiseplugin.data.Parameter Parameter}.
     */
    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
        this.model.fireTableDataChanged();
    }

    /**
     * Returns the {@link reiseplugin.gui.ReiseTableConfig.Model ReiseTableConfig.Model}.
     * @return The {@link reiseplugin.gui.ReiseTableConfig.Model ReiseTableConfig.Model}.
     */
    public Model getModel() {
        return model;
    }

    /**
     * Returns the {@link reiseplugin.gui.ReiseTableConfig.Renderer ReiseTableConfig.Renderer}.
     * @return The {@link reiseplugin.gui.ReiseTableConfig.Renderer ReiseTableConfig.Renderer}.
     */
    public Renderer getRenderer() {
        return renderer;
    }
    
    /**
     * The Model for the calculation-result-{@link javax.swing.JTable JTable}.
     * Implements the access to the table data and some methods used by the
     * {@link reiseplugin.gui.ReiseTableConfig.Renderer ReiseTableConfig.Renderer}.
     */
    public class Model extends AbstractTableModel {
        /**
         * {@inheritDoc}
         */
        @Override
        public String getColumnName(int column) {
            if (column == 0) {
                return "Uhrzeit";
            } else if (ReiseTableConfig.this.data == null) {
                return "";
            } else {
                return ReiseTableConfig.this.data.getHelden().get(column - 1).getName();
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getRowCount() {
            return ReiseTableConfig.this.data.STUNDEN;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getColumnCount() {
            if (ReiseTableConfig.this.data == null) {
                return 1;
            }
            return ReiseTableConfig.this.data.getHelden().size() + 1;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                return String.format("%02d:00 - %02d:00", rowIndex, rowIndex+1);
            } else {
                ErgebnisTag.Zustand z = ReiseTableConfig.this.data.getZustand(ReiseTableConfig.this.data.getHelden().get(columnIndex - 1), rowIndex);
                return z.getErschöpfung() + " / " + z.getÜberanstregnung();
            }
        }
        
        /**
         * Returns whether or not a {@link reiseplugin.data.Held Held} is
         * überanstrengt in a certain hour.
         * This is used by the
         * {@link reiseplugin.gui.ReiseTableConfig.Renderer ReiseTableConfig.Renderer}
         * to determine if a cell should be red or not.
         * @param hour The hour. This directly corresponds to the table row.
         * @param heldIndex The index of the {@link reiseplugin.data.Held Held},
         * starting with 1. This directly corresponds to the table column.
         * @return true if the {@link reiseplugin.data.Held Held} is überanstrngt.
         */
        public boolean isÜberanstrengt(int hour, int heldIndex) {
            if (heldIndex == 0) {
                return false;
            } else {
                ErgebnisTag.Zustand z = ReiseTableConfig.this.data.getZustand(ReiseTableConfig.this.data.getHelden().get(heldIndex - 1), hour);
                return z.getÜberanstregnung() > 0;
            }
        }
        
        /**
         * Returns how much Erholung a {@link reiseplugin.data.Held Held} gets
         * in a certain hour.
         * @param hour The hour. This directly corresponds to the table row.
         * @return The average Erholung in the given hour.
         */
        public double getErholung(int hour) {
            if(ReiseTableConfig.this.parameter == null) {
                return 0;
            }
            
            Rast rast = ReiseTableConfig.this.parameter.getErholung().stream()
                    .filter(r -> r.matchStunde(hour))
                    .findFirst().orElse(null);
            
            if(rast == null) {
                return 0;
            } else {
                return rast.getErschöpfungProStunde() + rast.getÜberanstrengungProStunde() / 2.;
            }
        }
    }
    
    /**
     * The Renderer of the calculation-result-{@link javax.swing.JTable JTable}.
     * This uses the {@link reiseplugin.data.Held Held} to determine the colour
     * of a cell.
     */
    public class Renderer extends DefaultTableCellRenderer {
        /**
         * Calculates the alpha depending on the Erholung.
         * @param erholung The amount of Erholung.
         * @return The alpha value.
         */
        private int calcAlpha(double erholung) {
            int ret = (int)(erholung * 64);
            return Math.min(255, ret);
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel l = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            if(column == 0) {
                double erholung = ReiseTableConfig.this.model.getErholung(row);
                if(erholung > 0.1) {
                    l.setBackground(new Color(0, 255, 0, this.calcAlpha(erholung)));
                } else {
                    l.setBackground(Color.WHITE);
                }
            } else {
                if(ReiseTableConfig.this.model.isÜberanstrengt(row, column)) {
                    l.setBackground(Color.RED);
                } else {
                    l.setBackground(Color.WHITE);
                }
            }
            
            return l;
        }
    }
}
