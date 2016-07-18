package reiseplugin.gui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.SpinnerNumberModel;
import reiseplugin.data.Parameter;
import reiseplugin.data.Rast;
import reiseplugin.data.ReiseCalculator;

/**
 * The big, all-containing Model for the gui.
 * @author Luca Corbatto<luca@corbatto.de>
 */
public class Model implements Observer {
    private ReiseCalculator calculator = null;
    private Parameter parameter = null;
    private SpinnerModel tagSpinnerModel = null;
    private SpinnerModel erschSpinnerModel = null;
    private RastTableConfig rastTableConfig = null;
    private HeldenTableModel heldenTableModel = null;
    
    private ReiseTableConfig reiseTableConfig = null;

    /**
     * Creates a new Model with all default values.
     */
    public Model() {
        this.reiseTableConfig = new ReiseTableConfig();
        this.tagSpinnerModel = new SpinnerModel(0);
        this.erschSpinnerModel = new SpinnerModel(1);
        this.rastTableConfig = new RastTableConfig();
        this.heldenTableModel = new HeldenTableModel();
    }

    /**
     * Returns the Parameter.
     * @return The Parameter.
     */
    public Parameter getParameter() {
        return parameter;
    }

    /**
     * Sets the Parameter and triggers a redraw where necessary.
     * @param parameter The new Parameter.
     */
    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
        this.calculator = new ReiseCalculator(this.parameter);
        this.calculator.addObserver(this);
        this.rastTableConfig.setParameter(this.parameter);
        this.reiseTableConfig.setParameter(this.parameter);
        this.heldenTableModel.setParameter(this.parameter);
        this.update(null, null);
    }

    /**
     * Returns the RastTableConfig containing the model for the JTable representing the Rasten.
     * @return The RastTableConfig.
     */
    public RastTableConfig getRastTableConfig() {
        return rastTableConfig;
    }

    /**
     * Returns the ReiseTableConfig containing the model for the JTable representing the results.
     * @return The ReiseTableConfig.
     */
    public ReiseTableConfig getReiseTableConfig() {
        return reiseTableConfig;
    }

    /**
     * Returns the currently selected day.
     * @return The currently selected day.
     */
    public int getTag() {
        return this.tagSpinnerModel.getInt();
    }

    /**
     * Returns the current Erschöpfung per hour.
     * @return The current Erschöpfung per hour.
     */
    public int getErschöpfung() {
        return this.erschSpinnerModel.getInt();
    }

    /**
     * Returns the SpinnerModel, the model for the JSpinner representing the currently selected day.
     * @return The SpinnerModel.
     */
    public SpinnerModel getTagSpinnerModel() {
        return tagSpinnerModel;
    }

    /**
     * Returns the SpinnerModel, the model for the JSpinner representing the Erschöpfung per hour.
     * @return The SpinnerModel.
     */
    public SpinnerModel getErschöpfungSpinnerModel() {
        return erschSpinnerModel;
    }

    /**
     * Returns the HeldenTableModel, the model for the JTable representing the Helden.
     * @return The HeldenTableModel.
     */
    public HeldenTableModel getHeldenTableModel() {
        return heldenTableModel;
    }
    
    /**
     * Adds a new Rast with default values and triggers a redraw.
     */
    public void addRast() {
        this.parameter.addRast(new Rast(0, 0, 0, 0));
        this.getRastTableConfig().getModel().fireTableDataChanged();
    }
    
    /**
     * Called when any of the Ovservables are updated.
     * Delegates the relevant calls to the sub models and triggers a redraw.
     * @param o The Observable that was updated.
     * @param arg An argument.
     */
    @Override
    public void update(Observable o, Object arg) {
        this.parameter.setErschöpfungProStunde(this.erschSpinnerModel.getInt());
        this.reiseTableConfig.setData(this.calculator.getTag(this.tagSpinnerModel.getInt()));
        this.getReiseTableConfig().getModel().fireTableDataChanged();
    }
    
    /**
     * The SpinnerModel used for the JSpinners in the gui.
     */
    public class SpinnerModel extends SpinnerNumberModel {
        private int value = 0;

        /**
         * Creates a new SpinnerModel with the given inital value.
         * @param value The initial value.
         */
        public SpinnerModel(int value) {
            super();
            this.value = value;
        }
        
        /**
         * Returns the currently selected value.
         * @return The currently selected value.
         */
        public int getInt() {
            return this.value;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public Object getValue() {
            return this.value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setValue(Object value) {
            this.value = (int)value;
            Model.this.update(null, this);
            this.fireStateChanged();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object getNextValue() {
            return this.value+1;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object getPreviousValue() {
            return Math.max(0, this.value-1);
        }
    }
    
}
