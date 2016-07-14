package reiseplugin.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractSpinnerModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
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
    private TagSpinnerModel tagSpinnerModel = null;
    
    private ReiseTableConfig reiseTableConfig = null;

    public Model() {
        this.reiseTableConfig = new ReiseTableConfig();
        this.tagSpinnerModel = new TagSpinnerModel();
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
        this.calculator = new ReiseCalculator(this.parameter);
        this.parameter.addObserver(this);
        this.reiseTableConfig.setParameter(this.parameter);
        this.update(null, null);
    }

    public ReiseTableConfig getReiseTableConfig() {
        return reiseTableConfig;
    }

    public int getTag() {
        return this.tagSpinnerModel.getTag();
    }

    public TagSpinnerModel getTagSpinnerModel() {
        return tagSpinnerModel;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        this.reiseTableConfig.setData(this.calculator.getTag(this.tagSpinnerModel.getTag()));
    }
    
    public class TagSpinnerModel extends AbstractSpinnerModel {
        private List<ChangeListener> listener;
        private int tag = 0;

        public TagSpinnerModel() {
            this.listener = new ArrayList<>();
        }
        
        public int getTag() {
            return tag;
        }
        
        @Override
        public Object getValue() {
            return this.tag;
        }

        @Override
        public void setValue(Object value) {
            this.tag = (int)value;
            Model.this.update(null, null);
            this.fireStateChanged();
        }

        @Override
        public Object getNextValue() {
            return this.tag+1;
        }

        @Override
        public Object getPreviousValue() {
            return Math.max(0, this.tag-1);
        }
    }
}
