package reiseplugin.gui;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import reiseplugin.WTFException;
import reiseplugin.calculator.Parameter;

/**
 *
 * @author Luca Corbatto
 */
public class RastTableConfig implements TableButtonListener {
    private static final String[] COLUMNS = {
        "Von", "Bis", "-Ersch.", "-Überanst.", "Löschen"
    };
    
    private static final Pattern timePattern = Pattern.compile("^(\\d{1,2})(:00)?$");
    
    private Parameter data = null;
    
    private Model model = null;
    private Renderer renderer = null;
    private Editor editor = null;
    
    public RastTableConfig() {
        this.model = new Model();
        this.renderer = new Renderer();
        this.editor = new Editor();
        this.editor.addTableButtonListener(this);
    }
    
    public Parameter getParameter() {
        return this.data;
    }

    public void setParameter(Parameter data) {
        this.data = data;
        this.model.fireTableStructureChanged();
        this.model.fireTableDataChanged();
    }
    
    public Model getModel() {
        return this.model;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public Editor getEditor() {
        return editor;
    }

    @Override
    public void tableButtonClicked(int row, int col) {
        this.data.removeRast(this.data.getRast(row));
    }
    
    public class Model extends AbstractTableModel {
        @Override
        public int getRowCount() {
            if(RastTableConfig.this.data == null) {
                return 0;
            }
            return RastTableConfig.this.data.getErholung().size();
        }

        @Override
        public int getColumnCount() {
            return RastTableConfig.COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if(RastTableConfig.this.data == null) {
                return null;
            }
            Parameter.Rast rast = RastTableConfig.this.data.getErholung().get(rowIndex);
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

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            if(columnIndex == 4) {
                return;
            }
            Parameter.Rast rast = RastTableConfig.this.data.getErholung().get(rowIndex);
            String text = (String)aValue;
            Matcher m;
            switch(columnIndex) {
                case 0:
                    m = RastTableConfig.timePattern.matcher(text);
                    if(m.matches()) {
                        rast.setStart(Integer.parseInt(m.group(1)));
                    } else {
                        // check for this
                    }
                    break;

                case 1:
                    m = RastTableConfig.timePattern.matcher(text);
                    if(m.matches()) {
                        rast.setEnde(Integer.parseInt(m.group(1)));
                    } else {
                        // check for this
                    }
                    break;

                case 2:
                    rast.setErschöpfungProStunde(Integer.parseInt(text));
                    break;

                case 3:
                    rast.setÜberanstrengungProStunde(Integer.parseInt(text));
                    break;

                default:
                    throw new WTFException();
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
//            return columnIndex != 4;
        }

        @Override
        public String getColumnName(int column) {
            return RastTableConfig.COLUMNS[column];
        }
    }
    
    public class Renderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if(column != 4) {
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
            
            JButton btn = new JButton((String)value);
            return btn;
        }
    }
    
    public class Editor extends DefaultCellEditor {
        
        private List<TableButtonListener> listeners;
        
        public Editor() {
            super(new JTextField());
            this.listeners = new ArrayList<>();
        }

        public boolean addTableButtonListener(TableButtonListener e) {
            return listeners.add(e);
        }

        public void removeTableButtonListener(TableButtonListener e) {
            listeners.remove(e);
        }
        
        private void fireButtonClicked(int row, int column) {
            this.listeners.stream().forEach(l -> {l.tableButtonClicked(row, row);});
        }
        
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
