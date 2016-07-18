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

import javax.swing.JTextArea;

/**
 * The JPanel containing the gui of the plugin.
 * @author Luca Corbatto {@literal <luca@corbatto.de>}
 */
public class ReisePanel extends javax.swing.JPanel {
    
    private final Model model;

    /**
     * Creates new form ReisePanel.
     */
    public ReisePanel() {
        this.model = new Model();
        initComponents();
        this.jTable1.setDefaultRenderer(Object.class, this.model.getReiseTableConfig().getRenderer());
        this.jTable4.setDefaultRenderer(Object.class, this.model.getRastTableConfig().getRenderer());
        this.jTable4.setDefaultEditor(Object.class, this.model.getRastTableConfig().getEditor());
        this.setDebugVisibility(false);
    }

    /**
     * Return the Model.
     * @return The Model.
     */
    public Model getModel() {
        return model;
    }
    
    /**
     * Sets the visibility of the debug region at the bottom of the window.
     * @param visibility true if it should be visible.
     */
    public final void setDebugVisibility(boolean visibility) {
        this.jScrollPaneDebug.setVisible(visibility);
    }
    
    /**
     * Returns the debug JTextArea at the bottom of the window.
     * @return The debug JTextArea at the bottom of the window.
     */
    public JTextArea getjDebugTextArea() {
        return jTextAreaDebug;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneDebug = new javax.swing.JScrollPane();
        jTextAreaDebug = new javax.swing.JTextArea();
        jSplitPane2 = new javax.swing.JSplitPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinnerTag = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(800, 600));

        jTextAreaDebug.setColumns(20);
        jTextAreaDebug.setRows(5);
        jScrollPaneDebug.setViewportView(jTextAreaDebug);
        jTextAreaDebug.getAccessibleContext().setAccessibleName("ta");

        jSplitPane2.setDividerLocation(130);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jSplitPane1.setDividerLocation(500);
        jSplitPane1.setMinimumSize(new java.awt.Dimension(98, 100));
        jSplitPane1.setPreferredSize(new java.awt.Dimension(612, 120));

        jTabbedPane2.setMinimumSize(new java.awt.Dimension(200, 120));
        jTabbedPane2.setPreferredSize(new java.awt.Dimension(450, 120));

        jLabel1.setText("Erschöpfung/Stunde");

        jSpinner1.setModel(this.model.getErschöpfungSpinnerModel());

        jSpinnerTag.setModel(this.model.getTagSpinnerModel());
        jSpinnerTag.setEditor(new javax.swing.JSpinner.NumberEditor(jSpinnerTag, ""));

        jLabel2.setText("Tag");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jSpinnerTag))
                .addContainerGap(332, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinnerTag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Reise", jPanel1);

        jTable4.setModel(this.model.getRastTableConfig().getModel());
        jTable4.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(jTable4);

        jButton1.setText("Hinzufügen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
        );

        jTabbedPane2.addTab("Rast", jPanel2);

        jSplitPane1.setLeftComponent(jTabbedPane2);

        jTabbedPane1.setMinimumSize(new java.awt.Dimension(150, 100));

        jTable3.setModel(this.model.getHeldenTableModel());
        jTable3.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Helden", jPanel4);

        jSplitPane1.setRightComponent(jTabbedPane1);

        jSplitPane2.setLeftComponent(jSplitPane1);

        jTable1.setModel(this.model.getReiseTableConfig().getModel());
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);

        jSplitPane2.setBottomComponent(jScrollPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2)
            .addComponent(jScrollPaneDebug)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneDebug, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPaneDebug.getAccessibleContext().setAccessibleName("jsp");
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.model.addRast();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPaneDebug;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinnerTag;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextArea jTextAreaDebug;
    // End of variables declaration//GEN-END:variables
}
