/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reiseplugin.gui;

import java.util.Arrays;
import java.util.Collections;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import reiseplugin.data.Held;
import reiseplugin.data.Parameter;
import reiseplugin.data.ReiseCalculator;
import reiseplugin.data.IService;
import reiseplugin.data.Rast;

/**
 *
 * @author Luca Corbatto
 */
public class Controller {
    private IService service;
    private JDialog dialog;
    private ReisePanel reisePanel;
    private JFrame parent;
    
    public Controller(IService service) {
        this.service = service;
        this.dialog = new JDialog();
        this.dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setupPanel();
        this.setupDialog();
    }
    
    public Controller(IService service, JFrame parent) {
        this.parent = parent;
        this.service = service;
        this.setupPanel();
    }
    
    private void setupPanel() {
        this.reisePanel = new ReisePanel();
        Rast[] defaultRasten = {
            new Rast(22, 6, 2, 1),
            new Rast(12, 13, 1, 0)
        };
        this.reisePanel.getModel().setParameter(new Parameter(this.service.getAllHelden(), 1, Arrays.asList(defaultRasten)));
    }
    
    private void setupDialog() {
        this.dialog.setSize(800, 600);
        this.dialog.getContentPane().add(this.reisePanel);
        this.dialog.setVisible(true);
    }
    
    public void showWindow() {
        this.dialog = new JDialog(this.parent, "Reise", false);
        this.setupDialog();
    }
    
    public void setText(String s) {
        this.reisePanel.getjTextArea1().setText(s);
    }
    
    public JPanel getPanel() {
        return this.reisePanel;
    }
}
