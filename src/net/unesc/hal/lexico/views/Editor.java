package net.unesc.hal.lexico.views;

import javax.swing.ImageIcon;

/**
 *
 * @author Mateus Generoso
 */
public class Editor extends javax.swing.JFrame {

    public Editor() {
        this.setIconImage(new ImageIcon(getClass().getResource("../resources/favicon.png")).getImage());
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new javax.swing.JPanel();
        spEditor = new javax.swing.JScrollPane();
        fieldEditor = new javax.swing.JEditorPane();
        tpContainer = new javax.swing.JTabbedPane();
        tpLexicoDebug = new javax.swing.JTabbedPane();
        tpLexicoBugs = new javax.swing.JTabbedPane();
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
        getContentPane().setLayout(new java.awt.GridLayout());

        MainPanel.setLayout(new java.awt.GridLayout(2, 0, 0, 10));

        fieldEditor.setMinimumSize(new java.awt.Dimension(10, 10));
        fieldEditor.setName(""); // NOI18N
        fieldEditor.setPreferredSize(new java.awt.Dimension(10, 10));
        spEditor.setViewportView(fieldEditor);

        MainPanel.add(spEditor);

        tpContainer.addTab("Análise léxica", tpLexicoDebug);
        tpContainer.addTab("Erros", tpLexicoBugs);

        MainPanel.add(tpContainer);

        getContentPane().add(MainPanel);

        mnArquivo.setText("Arquivo");

        smNovo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        smNovo.setText("Novo");
        mnArquivo.add(smNovo);

        smAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        smAbrir.setText("Abrir");
        mnArquivo.add(smAbrir);

        smSalvar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        smSalvar.setText("Salvar");
        mnArquivo.add(smSalvar);
        mnArquivo.add(smSeparador);

        smFechar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        smFechar.setText("Fechar");
        mnArquivo.add(smFechar);

        mnBar.add(mnArquivo);

        mnExecutar.setText("Executar");

        smRun.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, java.awt.event.InputEvent.CTRL_MASK));
        smRun.setText("Analisador léxico");
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
                new Editor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    private javax.swing.JEditorPane fieldEditor;
    private javax.swing.JMenu mnArquivo;
    private javax.swing.JMenuBar mnBar;
    private javax.swing.JMenu mnExecutar;
    private javax.swing.JMenuItem smAbrir;
    private javax.swing.JMenuItem smFechar;
    private javax.swing.JMenuItem smNovo;
    private javax.swing.JMenuItem smRun;
    private javax.swing.JMenuItem smSalvar;
    private javax.swing.JPopupMenu.Separator smSeparador;
    private javax.swing.JScrollPane spEditor;
    private javax.swing.JTabbedPane tpContainer;
    private javax.swing.JTabbedPane tpLexicoBugs;
    private javax.swing.JTabbedPane tpLexicoDebug;
    // End of variables declaration//GEN-END:variables
}
