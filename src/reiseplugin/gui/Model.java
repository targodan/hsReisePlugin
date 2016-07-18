package reiseplugin.gui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.SpinnerNumberModel;
import reiseplugin.data.Parameter;
import reiseplugin.data.Rast;
import reiseplugin.data.ReiseCalculator;

/**
 *
 * @author Luca Corbatto
 */
public class Model implements Observer {
    private ReiseCalculator calculator = null;
    private Parameter parameter = null;
    private SpinnerModel tagSpinnerModel = null;
    private SpinnerModel erschSpinnerModel = null;
    private RastTableConfig rastTableConfig = null;
    private HeldenTableModel heldenTableModel = null;
    
    private ReiseTableConfig reiseTableConfig = null;

    public Model() {
        this.reiseTableConfig = new ReiseTableConfig();
        this.tagSpinnerModel = new SpinnerModel(0);
        this.erschSpinnerModel = new SpinnerModel(1);
        this.rastTableConfig = new RastTableConfig();
        this.heldenTableModel = new HeldenTableModel();
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
        this.calculator = new ReiseCalculator(this.parameter);
        this.calculator.addObserver(this);
        this.rastTableConfig.setParameter(this.parameter);
        this.reiseTableConfig.setParameter(this.parameter);
        this.heldenTableModel.setParameter(this.parameter);
        this.update(null, null);
    }

    public RastTableConfig getRastTableConfig() {
        return rastTableConfig;
    }

    public ReiseTableConfig getReiseTableConfig() {
        return reiseTableConfig;
    }

    public int getTag() {
        return this.tagSpinnerModel.getInt();
    }

    public int getErschöpfung() {
        return this.erschSpinnerModel.getInt();
    }

    public SpinnerModel getTagSpinnerModel() {
        return tagSpinnerModel;
    }

    public SpinnerModel getErschöpfungSpinnerModel() {
        return erschSpinnerModel;
    }

    public HeldenTableModel getHeldenTableModel() {
        return heldenTableModel;
    }
    
    public void addRast() {
        this.parameter.addRast(new Rast(0, 0, 0, 0));
        this.getRastTableConfig().getModel().fireTableDataChanged();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        this.parameter.setErschöpfungProStunde(this.erschSpinnerModel.getInt());
        this.reiseTableConfig.setData(this.calculator.getTag(this.tagSpinnerModel.getInt()));
        this.getReiseTableConfig().getModel().fireTableDataChanged();
    }
    
    public class SpinnerModel extends SpinnerNumberModel {
        private int value = 0;

        public SpinnerModel(int value) {
            super();
            this.value = value;
        }
        
        public int getInt() {
            return this.value;
        }
        
        @Override
        public Object getValue() {
            return this.value;
        }

        @Override
        public void setValue(Object value) {
            this.value = (int)value;
            Model.this.update(null, this);
            this.fireStateChanged();
        }

        @Override
        public Object getNextValue() {
            return this.value+1;
        }

        @Override
        public Object getPreviousValue() {
            return Math.max(0, this.value-1);
        }
    }
    
}
