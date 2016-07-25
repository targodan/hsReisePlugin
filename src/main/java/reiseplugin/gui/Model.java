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

import java.util.Observable;
import java.util.Observer;
import javax.swing.SpinnerNumberModel;
import reiseplugin.data.Parameter;
import reiseplugin.data.Rast;
import reiseplugin.data.ReiseCalculator;

/**
 * The big, all-containing {@link reiseplugin.gui.Model Model} for the gui.
 * @author Luca Corbatto {@literal <luca@corbatto.de>}
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
     * Creates a new {@link reiseplugin.gui.Model Model} with all default values.
     */
    public Model() {
        this.reiseTableConfig = new ReiseTableConfig();
        this.tagSpinnerModel = new SpinnerModel(0);
        this.erschSpinnerModel = new SpinnerModel(1);
        this.rastTableConfig = new RastTableConfig();
        this.heldenTableModel = new HeldenTableModel();
    }

    /**
     * Returns the {@link reiseplugin.data.Parameter Parameter}.
     * @return The {@link reiseplugin.data.Parameter Parameter}.
     */
    public Parameter getParameter() {
        return parameter;
    }

    /**
     * Sets the {@link reiseplugin.data.Parameter Parameter} and triggers a
     * redraw where necessary.
     * @param parameter The new {@link reiseplugin.data.Parameter Parameter}.
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
     * Returns the {@link reiseplugin.gui.RastTableConfig RastTableConfig}
     * containing the model for the {@link javax.swing.JTable JTable}
     * representing the {@link reiseplugin.data.Rast Rast}en.
     * @return The {@link reiseplugin.gui.RastTableConfig RastTableConfig}.
     */
    public RastTableConfig getRastTableConfig() {
        return rastTableConfig;
    }

    /**
     * Returns the {@link reiseplugin.gui.ReiseTableConfig ReiseTableConfig}
     * containing the model for the {@link javax.swing.JTable JTable}
     * representing the results.
     * @return The {@link reiseplugin.gui.ReiseTableConfig ReiseTableConfig}.
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
     * Returns the {@link reiseplugin.gui.Model.SpinnerModel SpinnerModel}, the
     * model for the {@link javax.swing.JSpinner JSpinner} representing the
     * currently selected day.
     * @return The {@link reiseplugin.gui.Model.SpinnerModel SpinnerModel}.
     */
    public SpinnerModel getTagSpinnerModel() {
        return tagSpinnerModel;
    }

    /**
     * Returns the {@link reiseplugin.gui.Model.SpinnerModel SpinnerModel}, the
     * model for the {@link javax.swing.JSpinner JSpinner} representing the
     * Erschöpfung per hour.
     * @return The {@link reiseplugin.gui.Model.SpinnerModel SpinnerModel}.
     */
    public SpinnerModel getErschöpfungSpinnerModel() {
        return erschSpinnerModel;
    }

    /**
     * Returns the {@link reiseplugin.gui.HeldenTableModel HeldenTableModel},
     * the model for the {@link javax.swing.JTable JTable} representing the
     * Helden.
     * @return The {@link reiseplugin.gui.HeldenTableModel HeldenTableModel}.
     */
    public HeldenTableModel getHeldenTableModel() {
        return heldenTableModel;
    }
    
    /**
     * Adds a new {@link reiseplugin.data.Rast Rast} with default values and
     * triggers a redraw.
     */
    public void addRast() {
        this.parameter.addRast(new Rast(0, 0, 0, 0));
        this.getRastTableConfig().getModel().fireTableDataChanged();
    }
    
    /**
     * Called when any of the {@link java.util.Observable Observable}s are
     * updated.
     * Delegates the relevant calls to the sub models and triggers a redraw.
     * @param o The {@link java.util.Observable Observable} that was updated.
     * @param arg An argument.
     */
    @Override
    public void update(Observable o, Object arg) {
        this.parameter.setErschöpfungProStunde(this.erschSpinnerModel.getInt());
        this.reiseTableConfig.setData(this.calculator.getTag(this.tagSpinnerModel.getInt()));
        this.getReiseTableConfig().getModel().fireTableDataChanged();
    }
    
    /**
     * The {@link reiseplugin.gui.Model.SpinnerModel SpinnerModel} used for the
     * {@link javax.swing.JSpinner JSpinner}s in the gui.
     */
    public class SpinnerModel extends SpinnerNumberModel {
        private int value = 0;

        /**
         * Creates a new {@link reiseplugin.gui.Model.SpinnerModel SpinnerModel}
         * with the given inital value.
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
