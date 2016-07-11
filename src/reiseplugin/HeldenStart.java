package reiseplugin;

import helden.plugin.HeldenPlugin;
import helden.plugin.HeldenWertePlugin3;
import helden.plugin.datenplugin.DatenPluginHeldenWerkzeug;
import helden.plugin.datenxmlplugin.DatenAustausch3Interface;
import helden.plugin.datenxmlplugin.DatenAustauschImpl;
import helden.plugin.werteplugin2.PluginHeld2;
import helden.plugin.werteplugin3.PluginHeldenWerteWerkzeug3;
import java.util.List;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author Luca Corbatto
 */
public class HeldenStart implements helden.plugin.HeldenXMLDatenPlugin3 {
    
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
        return HeldenWertePlugin3.DATEN3;
    }

    @Override
    public void doWork(JFrame jframe) {
        Object o = new reiseplugin.gui.Controller(jframe);
    }

    @Override
    public void click() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JComponent getPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hatMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hatTab() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init(DatenAustausch3Interface dai, JFrame jframe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<JComponent> getUntermenus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
