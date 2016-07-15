/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reiseplugin.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.color.ColorSpace;
import java.util.Enumeration;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import reiseplugin.calculator.ErgebnisTag;
import reiseplugin.calculator.Parameter;

/**
 *
 * @author Luca Corbatto
 */
public class ReiseTableConfig {
    private ErgebnisTag data = null;
    private Parameter parameter = null;
    
    private Model model;
    private Renderer renderer;

    public ReiseTableConfig() {
        this.model = new Model();
        this.renderer = new Renderer();
    }
    
    public ErgebnisTag getData() {
        return data;
    }

    public void setData(ErgebnisTag data) {
        if(this.data == null || !this.data.equals(data)) {
            this.data = data;
            this.model.fireTableStructureChanged();
            this.model.fireTableDataChanged();
        }
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
        this.model.fireTableDataChanged();
    }

    public Model getModel() {
        return model;
    }

    public Renderer getRenderer() {
        return renderer;
    }
    

    public class Model extends AbstractTableModel {
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

        @Override
        public int getRowCount() {
            return ReiseTableConfig.this.data.STUNDEN;
        }

        @Override
        public int getColumnCount() {
            if (ReiseTableConfig.this.data == null) {
                return 1;
            }
            return ReiseTableConfig.this.data.getHelden().size() + 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                return rowIndex + ":00";
            } else {
                ErgebnisTag.Zustand z = ReiseTableConfig.this.data.getZustand(ReiseTableConfig.this.data.getHelden().get(columnIndex - 1), rowIndex);
                return z.getErschöpfung() + " / " + z.getÜberanstregnung();
            }
        }
        
        public boolean isÜberanstrengt(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                return false;
            } else {
                ErgebnisTag.Zustand z = ReiseTableConfig.this.data.getZustand(ReiseTableConfig.this.data.getHelden().get(columnIndex - 1), rowIndex);
                return z.getÜberanstregnung() > 0;
            }
        }
        
        public double getErholung(int rowIndex) {
            if(ReiseTableConfig.this.parameter == null) {
                return 0;
            }
            
            Parameter.Rast rast = ReiseTableConfig.this.parameter.getErholung().stream()
                    .filter(r -> r.matchStunde(rowIndex))
                    .findFirst().orElse(null);
            
            if(rast == null) {
                return 0;
            } else {
                return rast.getErschöpfungProStunde() + rast.getÜberanstrengungProStunde() / 2.;
            }
        }
    }
    
    public class Renderer extends DefaultTableCellRenderer {
        private int calcAlpha(double erholung) {
            int ret = (int)(erholung * 64);
            return Math.min(255, ret);
        }
        
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
