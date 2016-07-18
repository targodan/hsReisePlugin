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

import java.util.Arrays;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import reiseplugin.data.Parameter;
import reiseplugin.data.IService;
import reiseplugin.data.Rast;

/**
 * The Controller class for the gui.
 * @author Luca Corbatto<luca@corbatto.de>
 */
public class Controller {
    private IService service;
    private JDialog dialog;
    private ReisePanel reisePanel;
    private JFrame parent;
    
    /**
     * Creates a new test Controller that automatically opens a new Window.
     * Used only for testing purposes.
     * @param service 
     */
    public Controller(IService service) {
        this.service = service;
        this.dialog = new JDialog();
        this.dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setupPanel();
        this.setupDialog();
        this.reisePanel.setDebugVisibility(true);
    }
    
    /**
     * Creates a new Controller containing a new ReisePanel.
     * @param service The IService to use for data requests.
     * @param parent The JFrame containing the look and feel settings.
     */
    public Controller(IService service, JFrame parent) {
        this.parent = parent;
        this.service = service;
        this.setupPanel();
    }
    
    /**
     * Creates the ReisePanel representing the gui of this plugin and assigns a default Model.
     */
    private void setupPanel() {
        this.reisePanel = new ReisePanel();
        Rast[] defaultRasten = {
            new Rast(22, 6, 2, 1),
            new Rast(12, 13, 1, 0)
        };
        this.reisePanel.getModel().setParameter(new Parameter(this.service.getAllHelden(), 1, Arrays.asList(defaultRasten)));
    }
    
    /**
     * Creates a new Window using the earlier(!) created ReisePanel as its content.
     */
    private void setupDialog() {
        this.dialog.setSize(800, 600);
        this.dialog.getContentPane().add(this.reisePanel);
        this.dialog.setVisible(true);
    }
    
    /**
     * Creates a new Window with the earlier created ReisePanel as its content.
     */
    public void showWindow() {
        this.dialog = new JDialog(this.parent, "Reise", false);
        this.setupDialog();
    }
    
    /**
     * Sets the text of a debug TextField at the bottom of the window.
     * @param s 
     */
    public void setText(String s) {
        this.reisePanel.getjDebugTextArea().setText(s);
    }
    
    /**
     * Returns the ReisePanel.
     * @return The ReisePanel.
     */
    public ReisePanel getPanel() {
        return this.reisePanel;
    }
}
