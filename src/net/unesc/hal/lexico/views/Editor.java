package net.unesc.hal.lexico.views;

import java.awt.Color;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import net.unesc.hal.lexico.utils.TextLineNumber;
import net.unesc.hal.lexico.utils.TextPanelHighLight;

/**
 *
 * @author Mateus Generoso
 */
public class Editor extends javax.swing.JFrame {

    public Editor() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        this.setIconImage(new ImageIcon(getClass().getResource("../resources/favicon.png")).getImage());
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        initComponents();
        initEditor();        
    }
    
    private void initEditor(){
        fieldEditor = new TextPanelHighLight();
        spEditor = new javax.swing.JScrollPane(fieldEditor);
        txtLineNumber = new TextLineNumber(fieldEditor);
        
        spEditor.setBorder(null);
        
        fieldEditor.setBackground(new java.awt.Color(7, 54, 66));
        fieldEditor.setBorder(null);
        fieldEditor.setFont(new java.awt.Font("Monospaced", 0, 14));
        fieldEditor.setForeground(new java.awt.Color(252, 252, 250));
        fieldEditor.setName("");
        fieldEditor.setText("function");
        
        
        txtLineNumber.setBorderGap(8);
        txtLineNumber.setUpdateFont(true);
        txtLineNumber.setBackground(new java.awt.Color(0, 43, 54));
        txtLineNumber.setFont(new java.awt.Font("Monospaced", 0, 14));
        txtLineNumber.setForeground(new java.awt.Color(252, 252, 250));
        txtLineNumber.setCurrentLineForeground(new java.awt.Color(252, 252, 250));
        
 
        spEditor.setRowHeaderView(txtLineNumber);
        
        pnEditor.add(spEditor);
        
        tpContainer.setBackgroundAt(0, Color.white);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        MainPanel = new javax.swing.JPanel();
        pnEditor = new javax.swing.JPanel();
        pnDebug = new javax.swing.JPanel();
        divider = new javax.swing.JSeparator();
        tpContainer = new javax.swing.JTabbedPane();
        spAnaliseLexicaDebug = new javax.swing.JScrollPane();
        tbAnaliseLexica = new javax.swing.JTable();
        spErros = new javax.swing.JScrollPane();
        tbErros = new javax.swing.JTable();
        mnBar = new javax.swing.JMenuBar();
        mnArquivo = new javax.swing.JMenu();
        smNovo = new javax.swing.JMenuItem();
        smAbrir = new javax.swing.JMenuItem();
        smSalvar = new javax.swing.JMenuItem();
        smSeparador = new javax.swing.JPopupMenu.Separator();
        smFechar = new javax.swing.JMenuItem();
        mnExecutar = new javax.swing.JMenu();
        smRun = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("H.A.L. - Analisador Léxico");
        setPreferredSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        MainPanel.setBackground(new java.awt.Color(253, 246, 227));
        MainPanel.setLayout(new java.awt.GridLayout(2, 0));

        pnEditor.setOpaque(false);
        pnEditor.setLayout(new java.awt.GridLayout());
        MainPanel.add(pnEditor);

        pnDebug.setLayout(new javax.swing.BoxLayout(pnDebug, javax.swing.BoxLayout.Y_AXIS));

        divider.setBackground(new java.awt.Color(0, 43, 54));
        divider.setForeground(new java.awt.Color(0, 43, 54));
        divider.setMaximumSize(new java.awt.Dimension(3000, 3000));
        divider.setName(""); // NOI18N
        divider.setPreferredSize(new java.awt.Dimension(3, 3));
        divider.setVerifyInputWhenFocusTarget(false);
        pnDebug.add(divider);

        spAnaliseLexicaDebug.setBorder(null);

        tbAnaliseLexica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        spAnaliseLexicaDebug.setViewportView(tbAnaliseLexica);

        tpContainer.addTab("Análise léxica", spAnaliseLexicaDebug);

        spErros.setBorder(null);

        tbErros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        spErros.setViewportView(tbErros);

        tpContainer.addTab("Erros", spErros);

        pnDebug.add(tpContainer);

        MainPanel.add(pnDebug);

        getContentPane().add(MainPanel);

        mnBar.setBackground(new java.awt.Color(0, 43, 54));
        mnBar.setBorder(null);
        mnBar.setToolTipText("");
        mnBar.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        mnBar.setPreferredSize(new java.awt.Dimension(104, 25));

        mnArquivo.setText("Arquivo");
        mnArquivo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        smNovo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        smNovo.setBackground(new java.awt.Color(7, 54, 66));
        smNovo.setText("Novo");
        smNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        smNovo.setIconTextGap(8);
        smNovo.setPreferredSize(new java.awt.Dimension(120, 30));
        mnArquivo.add(smNovo);

        smAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        smAbrir.setBackground(new java.awt.Color(7, 54, 66));
        smAbrir.setText("Abrir");
        smAbrir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        smAbrir.setIconTextGap(8);
        smAbrir.setPreferredSize(new java.awt.Dimension(120, 30));
        mnArquivo.add(smAbrir);

        smSalvar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        smSalvar.setBackground(new java.awt.Color(7, 54, 66));
        smSalvar.setText("Salvar");
        smSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        smSalvar.setIconTextGap(8);
        smSalvar.setPreferredSize(new java.awt.Dimension(120, 30));
        mnArquivo.add(smSalvar);
        mnArquivo.add(smSeparador);

        smFechar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        smFechar.setBackground(new java.awt.Color(7, 54, 66));
        smFechar.setText("Fechar");
        smFechar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        smFechar.setIconTextGap(8);
        smFechar.setPreferredSize(new java.awt.Dimension(120, 30));
        mnArquivo.add(smFechar);

        mnBar.add(mnArquivo);

        mnExecutar.setText("Executar");
        mnExecutar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        smRun.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, java.awt.event.InputEvent.CTRL_MASK));
        smRun.setBackground(new java.awt.Color(7, 54, 66));
        smRun.setText("Analisador léxico");
        smRun.setBorder(null);
        smRun.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        smRun.setDoubleBuffered(true);
        smRun.setIconTextGap(8);
        smRun.setPreferredSize(new java.awt.Dimension(177, 30));
        smRun.setRequestFocusEnabled(false);
        smRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smRunActionPerformed(evt);
            }
        });
        mnExecutar.add(smRun);

        mnBar.add(mnExecutar);

        setJMenuBar(mnBar);

        getAccessibleContext().setAccessibleDescription("Analisador Léxico da Hybrid Access Language");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void smRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smRunActionPerformed
        
        String[] code = fieldEditor.getText().split("\n");
        
    }//GEN-LAST:event_smRunActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Editor().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    private javax.swing.JSeparator divider;
    private javax.swing.JMenu mnArquivo;
    private javax.swing.JMenuBar mnBar;
    private javax.swing.JMenu mnExecutar;
    private javax.swing.JPanel pnDebug;
    private javax.swing.JPanel pnEditor;
    private javax.swing.JMenuItem smAbrir;
    private javax.swing.JMenuItem smFechar;
    private javax.swing.JMenuItem smNovo;
    private javax.swing.JMenuItem smRun;
    private javax.swing.JMenuItem smSalvar;
    private javax.swing.JPopupMenu.Separator smSeparador;
    private javax.swing.JScrollPane spAnaliseLexicaDebug;
    private javax.swing.JScrollPane spErros;
    private javax.swing.JTable tbAnaliseLexica;
    private javax.swing.JTable tbErros;
    private javax.swing.JTabbedPane tpContainer;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JEditorPane fieldEditor;
    private javax.swing.JScrollPane spEditor;
    private net.unesc.hal.lexico.utils.TextLineNumber txtLineNumber;
}
