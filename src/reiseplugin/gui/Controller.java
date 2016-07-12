/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reiseplugin.gui;

import java.util.Arrays;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
    
    public Controller(IService service) {
        this.dialog = new JDialog();
        this.dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setupDialog();
    }
    
    public Controller(IService service, JFrame parent) {
        this.service = service;
        this.dialog = new JDialog(parent, "Reise", false);
        this.setupDialog();
        this.reisePanel.getjTextArea1().setText("plane");
    }
    
    private void setupDialog() {
        this.dialog.setSize(800, 600);
        this.reisePanel = new ReisePanel();
        this.dialog.getContentPane().add(this.reisePanel);
        this.dialog.setVisible(true);
    }
    
    public void setText(String s) {
        this.reisePanel.getjTextArea1().setText(s);
    }
    
    public void test() {
        Held h = new Held("Ich", 12, 1);
        Parameter.Rast r = new Parameter.Rast(22, 5, 2, 1);
        Parameter p = new Parameter(new Held[]{h}, 1, Arrays.asList(new Parameter.Rast[] {r}));
        this.reisePanel.getReiseTableConfig().setParameter(p);
        
        ReiseCalculator c = new ReiseCalculator(p);
        this.reisePanel.getReiseTableConfig().setData(c.getTag(0));
    }
}
