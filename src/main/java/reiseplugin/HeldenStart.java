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
package reiseplugin;

import helden.plugin.HeldenXMLDatenPlugin3;
import helden.plugin.datenxmlplugin.DatenAustausch3Interface;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import reiseplugin.data.IService;
import reiseplugin.data.Service;
import reiseplugin.data.helden.entities.HeldenService;
import reiseplugin.gui.Controller;

/**
 * Loader-class that gets instantiated by the HeldenSoftware.
 * @author Luca Corbatto {@literal <luca@corbatto.de>}
 */
public class HeldenStart implements helden.plugin.HeldenXMLDatenPlugin3 {
    private IService service;
    private Controller controller;
    
    /**
     * Constructor.
     */
    public HeldenStart() {
        super();
    }

    /**
     * Returns the name of the plugin.
     * Used to build up the menu.
     * @return Name of the plugin
     */
    @Override
    public String getMenuName() {
        return "Reisen";
    }

    /**
     * Returns the ToolTip text showed when hovering over the menu entry.
     * @return ToolTip text
     */
    @Override
    public String getToolTipText() {
        return "Berechnet Erschöpfung und Überanstrengung der Helden";
    }

    /**
     * Returns the icon used in the menu.
     * @return ImageIcon|null
     */
    @Override
    public ImageIcon getIcon() {
        ProtectionDomain currentProtectionDomain = getClass().getProtectionDomain();
        CodeSource codeSource = currentProtectionDomain.getCodeSource();
        URL icon = new URLClassLoader(new URL[]{codeSource.getLocation()})
            .getResource("icon.png");
        return new ImageIcon(icon);
    }

    /**
     * Returns the type of the plugin.
     * @return The plugin type.
     */
    @Override
    public String getType() {
        return HeldenXMLDatenPlugin3.DATEN;
    }

    /**
     * Unused in this plugin
     * @param jframe 
     */
    @Override
    public void doWork(JFrame jframe) {
        // unused
    }

    /**
     * Invoked when the user clicks on the menu entry.
     */
    @Override
    public void click() {
        this.controller.showWindow();
    }

    /**
     * Returns the JPanel that is shown in the tab, if enabled.
     * @return The JPanel representing the gui of this plugin.
     */
    @Override
    public JComponent getPanel() {
        return this.controller.getPanel();
    }

    /**
     * Returns true if the menu entry should be shown.
     * @return true
     */
    @Override
    public boolean hatMenu() {
        return true;
    }

    /**
     * Returns true if the tab should be shown.
     * @return false
     */
    @Override
    public boolean hatTab() {
        return false;
    }

    /**
     * Initializes the plugin.
     * Is invoked on startup of the HeldenSoftware and provides the interfaces.
     * @param dai The data interface.
     * @param jframe The JFrame that provides the look and feel settings.
     */
    @Override
    public void init(DatenAustausch3Interface dai, JFrame jframe) {
        // called on opening the tool
        this.service = new Service(new HeldenService(dai));
        this.controller = new Controller(this.service, jframe, true);
    }

    /**
     * Returns the names of the submenu entries.
     * @return The menu entries.
     */
    @Override
    public ArrayList<JComponent> getUntermenus() {
        ArrayList<JComponent> ret = new ArrayList<>();
        return ret;
    }
}
