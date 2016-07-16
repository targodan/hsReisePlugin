package reiseplugin.gui;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.AbstractTableModel;
import reiseplugin.WTFException;
import reiseplugin.calculator.Parameter;
import reiseplugin.calculator.Held;

/**
 *
 * @author Luca Corbatto
 */
public class HeldenTableModel extends AbstractTableModel {
    private static final String[] COLUMNS = {
        "Name", "KO", "Ersch.Mod"
    };
    
    private Parameter data = null;
    
    public HeldenTableModel() {
    }

    public Parameter getParameter() {
        return this.data;
    }

    public void setParameter(Parameter data) {
        this.data = data;
        this.fireTableStructureChanged();
        this.fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        if(this.data == null) {
            return 0;
        }
        return this.data.getHeldenCount();
    }

    @Override
    public int getColumnCount() {
        return HeldenTableModel.COLUMNS.length;
    }

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

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Held held = this.data.getHeld(rowIndex);
        String text = (String)aValue;
        Matcher m;
        if(columnIndex == 2) {
            held.setMod(Integer.parseInt(text));
        } else {
            throw new WTFException();
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2;
    }

    @Override
    public String getColumnName(int column) {
        return HeldenTableModel.COLUMNS[column];
    }
}
