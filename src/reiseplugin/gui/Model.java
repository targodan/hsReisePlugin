package reiseplugin.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractSpinnerModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import reiseplugin.calculator.ErgebnisTag;
import reiseplugin.calculator.Parameter;
import reiseplugin.calculator.ReiseCalculator;

/**
 *
 * @author Luca Corbatto
 */
public class Model implements Observer {
    private ReiseCalculator calculator = null;
    private Parameter parameter = null;
    private SpinnerModel tagSpinnerModel = null;
    private SpinnerModel erschSpinnerModel = null;
    private RastTableModel rastTableModel = null;
    
    private ReiseTableConfig reiseTableConfig = null;

    public Model() {
        this.reiseTableConfig = new ReiseTableConfig();
        this.tagSpinnerModel = new SpinnerModel();
        this.erschSpinnerModel = new SpinnerModel();
        this.rastTableModel = new RastTableModel();
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
        this.calculator = new ReiseCalculator(this.parameter);
        this.calculator.addObserver(this);
        this.rastTableModel.setParameter(this.parameter);
        this.reiseTableConfig.setParameter(this.parameter);
        this.update(null, null);
    }

    public RastTableModel getRastTableModel() {
        return rastTableModel;
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
    
    @Override
    public void update(Observable o, Object arg) {
        this.parameter.setErschöpfungProStunde(this.erschSpinnerModel.getInt());
        this.reiseTableConfig.setData(this.calculator.getTag(this.tagSpinnerModel.getInt()));
        this.getReiseTableConfig().getModel().fireTableDataChanged();
    }
    
    public class SpinnerModel extends SpinnerNumberModel {
        private int value = 0;
        
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
