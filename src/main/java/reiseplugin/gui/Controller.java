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

import java.awt.Toolkit;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Arrays;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import reiseplugin.data.Parameter;
import reiseplugin.data.IService;
import reiseplugin.data.Rast;

/**
 * The Controller class for the gui.
 * @author Luca Corbatto {@literal <luca@corbatto.de>}
 */
public class Controller {
    private final IService service;
    private JDialog dialog;
    private ReisePanel reisePanel;
    private JFrame parent;
    
    /**
     * A proxy for the Controller class that limits the access to methods.
     * @author Luca Corbatto {@literal <luca@corbatto.de>}
     */
    public class ControllerProxy {
        private boolean debug = false;
        
        /**
         * Creates a new ControllerProxy.
         * @param debug Determines whether or not debug mode is on.
         */
        public ControllerProxy(boolean debug) {
            this.debug = debug;
        }
        
        /**
         * Sets the text of a debug TextField at the bottom of the window.
         * 
         * @param text The text to be set.
         */
        public void setDebugText(String text) {
            if(!this.debug) {
                return;
            }
            Controller.this.setDebugText(text);
        }
        
        /**
         * Appends text to the debug TextField at the bottom of the window.
         * 
         * @param text The text to be appended.
         */
        public void appendDebugText(String text) {
            if(!this.debug) {
                return;
            }
            Controller.this.appendDebugText(text);
        }
    }
    
    private ControllerProxy proxy;
    public static Controller INSTANCE = null;
    
    public static ControllerProxy getInstance() {
        return Controller.INSTANCE.proxy;
    }
    
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
        this.proxy = new ControllerProxy(true);
        this.reisePanel.setDebugVisibility(true);
        Controller.INSTANCE = this;
    }
    
    /**
     * Creates a new Controller containing a new ReisePanel.
     * @param service The IService to use for data requests.
     * @param parent The JFrame containing the look and feel settings.
     */
    public Controller(IService service, JFrame parent) {
        this(service, parent, false);
    }
    
    /**
     * Creates a new Controller containing a new ReisePanel.
     * @param service The IService to use for data requests.
     * @param parent The JFrame containing the look and feel settings.
     * @param debug Determines whether or not debug mode is on.
     */
    public Controller(IService service, JFrame parent, boolean debug) {
        this.parent = parent;
        this.service = service;
        this.setupPanel();
        this.proxy = new ControllerProxy(debug);
        this.reisePanel.setDebugVisibility(debug);
        Controller.INSTANCE = this;
    }
    
    /**
     * Creates the ReisePanel representing the gui of this plugin and assigns a default Model.
     */
    protected void setupPanel() {
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
    protected void setupDialog() {
        this.dialog.setSize(800, 600);
        this.dialog.getContentPane().add(this.reisePanel);
        this.dialog.setVisible(true);
        ProtectionDomain currentProtectionDomain = getClass().getProtectionDomain();
        CodeSource codeSource = currentProtectionDomain.getCodeSource();
        URL icon = new URLClassLoader(new URL[]{codeSource.getLocation()})
            .getResource("icon.png");
        this.dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(icon));
    }
    
    /**
     * Creates a new Window with the earlier created ReisePanel as its content.
     */
    public void showWindow() {
        this.dialog = new JDialog(this.parent, "Reise", false);
        this.setupDialog();
    }
    
    /**
     * Sets the text of the debug TextField at the bottom of the window.
     * 
     * @deprecated Renamed to {@link #setDebugText setDebugText}.
     * 
     * @param s 
     */
    public void setText(String s) {
        this.reisePanel.getDebugTextArea().setText(s);
    }
    
    /**
     * Sets the text of a debug TextField at the bottom of the window.
     * 
     * @param s The text to be set.
     */
    public void setDebugText(String s) {
        this.reisePanel.getDebugTextArea().setText(s);
    }
    
    /**
     * Appends text to the debug TextField at the bottom of the window.
     * 
     * @param s The text to be appended.
     */
    public void appendDebugText(String s) {
        this.reisePanel.getDebugTextArea().append(s);
    }
    
    /**
     * Returns the ReisePanel.
     * @return The ReisePanel.
     */
    public ReisePanel getPanel() {
        return this.reisePanel;
    }
}
