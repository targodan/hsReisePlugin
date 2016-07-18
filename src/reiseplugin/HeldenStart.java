package reiseplugin;

import helden.plugin.HeldenXMLDatenPlugin3;
import helden.plugin.datenxmlplugin.DatenAustausch3Interface;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import reiseplugin.data.IService;
import reiseplugin.data.Service;
import reiseplugin.gui.Controller;

/**
 *
 * @author Luca Corbatto
 */
public class HeldenStart implements helden.plugin.HeldenXMLDatenPlugin3 {
    private IService service;
    private Controller controller;
    
    public HeldenStart() {
        super();
    }

    /**
     * Gibt den Namen des Plugins an.
     * Wird verwendet um das Menu auf zu bauen.
     * @return name
     */
    @Override
    public String getMenuName() {
        return "Reisen";
    }

    /**
     * Wird angezeigt, wenn die maus über den Menu schwebt.
     * @return tooltip
     */
    @Override
    public String getToolTipText() {
        return "Berechnet Erschöpfung und Überanstrengung der Helden";
    }

    /**
     * Liefert das ImageIcon für das Menü
     * @return ImageIcon oder null 
     */
    @Override
    public ImageIcon getIcon() {
        /*
        ProtectionDomain currentProtectionDomain = getClass().getProtectionDomain();
        CodeSource codeSource = currentProtectionDomain.getCodeSource();
        URL icon = new URLClassLoader(new URL[]{codeSource.getLocation()})
            .getResource("mondkalender/madamal.gif");
        return new ImageIcon(icon);
        */
        return null;
    }

    @Override
    public String getType() {
        return HeldenXMLDatenPlugin3.DATEN;
    }

    @Override
    public void doWork(JFrame jframe) {
        // unused
    }

    @Override
    public void click() {
        this.controller.showWindow();
    }

    @Override
    public JComponent getPanel() {
        return this.controller.getPanel();
    }

    @Override
    public boolean hatMenu() {
        return true;
    }

    @Override
    public boolean hatTab() {
        return false;
    }

    @Override
    public void init(DatenAustausch3Interface dai, JFrame jframe) {
        // called on opening the tool
        this.service = new Service(dai);
        this.controller = new Controller(this.service, jframe);
    }

    @Override
    public ArrayList<JComponent> getUntermenus() {
        ArrayList<JComponent> ret = new ArrayList<>();
        return ret;
    }
}
