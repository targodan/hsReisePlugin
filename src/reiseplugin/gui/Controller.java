/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reiseplugin.gui;

import helden.plugin.datenxmlplugin.DatenAustauschImpl;
import helden.plugin.werteplugin2.PluginHeld2;
import helden.plugin.werteplugin3.PluginHeldenWerteWerkzeug3;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Luca Corbatto
 */
public class Controller {
    private JDialog dialog;
    private ReisePanel reisePanel;
    
    public Controller() {
        this.dialog = new JDialog();
        this.setupDialog();
    }
    
    public Controller(JFrame parent) {
        this.dialog = new JDialog(parent, "Reise", false);
        this.setupDialog();
        this.reisePanel.getjTextArea1().setText("plane");
    }
    
    public Controller(JFrame parent, Integer intgr, DatenAustauschImpl dai) {
        this(parent);
        this.reisePanel.getjTextArea1().setText("int,dai");
    }
    
    public Controller(JFrame parent, PluginHeld2[] phs, PluginHeldenWerteWerkzeug3 phww) {
        this(parent);
        phww.setAktivenHeld(phs[0]);
        this.reisePanel.getjTextArea1().setText("phs,phww\n"+phww.getEigenschaftswert("Körperkraft"));
    }
    
    private void setupDialog() {
        this.dialog.setSize(800, 600);
        this.reisePanel = new ReisePanel();
        this.dialog.getContentPane().add(this.reisePanel);
        this.dialog.setVisible(true);
    }
}
