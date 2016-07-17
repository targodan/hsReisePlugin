/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reiseplugin.gui;

import java.util.Arrays;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import reiseplugin.calculator.Held;
import reiseplugin.calculator.Parameter;
import reiseplugin.calculator.ReiseCalculator;
import reiseplugin.data.IService;

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
        this.reisePanel = new ReisePanel();
        this.setupDialog();
    }
    
    public Controller(IService service, JFrame parent) {
        this.parent = parent;
        this.service = service;
        this.reisePanel = new ReisePanel();
    }
    
    private void setupDialog() {
        this.dialog.setSize(800, 600);
        this.reisePanel.getModel().setParameter(new Parameter(this.service.getAllHelden(), 1, null));
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
    
    public void test() {
        Parameter.Rast r = new Parameter.Rast(22, 5, 2, 1);
        Parameter.Rast r2 = new Parameter.Rast(12, 14, 1, 0);
        this.reisePanel.getModel().getParameter().addRast(r);
        this.reisePanel.getModel().getParameter().addRast(r2);
    }
}
