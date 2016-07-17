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

/**
 *
 * @author Luca Corbatto
 */
public class RastTableModel extends AbstractTableModel {
    private static final String[] COLUMNS = {
        "Von", "Bis", "-Ersch.", "-Überanst.", "Löschen"
    };
    
    private static final Pattern timePattern = Pattern.compile("^(\\d{1,2})(:00)?$");
    
    private Parameter data = null;
    
    public RastTableModel() {
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
        return this.data.getErholung().size();
    }

    @Override
    public int getColumnCount() {
        return RastTableModel.COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(this.data == null) {
            return null;
        }
        Parameter.Rast rast = this.data.getErholung().get(rowIndex);
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
        Parameter.Rast rast = this.data.getErholung().get(rowIndex);
        String text = (String)aValue;
        Matcher m;
        switch(columnIndex) {
            case 0:
                m = RastTableModel.timePattern.matcher(text);
                if(m.matches()) {
                    rast.setStart(Integer.parseInt(m.group(1)));
                } else {
                    // check for this
                }
                break;
                
            case 1:
                m = RastTableModel.timePattern.matcher(text);
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
        return columnIndex != 4;
    }

    @Override
    public String getColumnName(int column) {
        return RastTableModel.COLUMNS[column];
    }
}
