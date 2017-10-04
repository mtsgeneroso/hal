package net.unesc.hal.views;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import net.unesc.hal.controllers.FiniteAutomaton;
import net.unesc.hal.data.Source;
import net.unesc.hal.listeners.EditorListener;
import net.unesc.hal.utils.TextLineNumber;
import net.unesc.hal.utils.TextPanelHighLight;

/**
 *
 * @author Mateus Generoso
 */
public class Editor extends javax.swing.JFrame {
    
    public static final String SAVE = "Salvar";
    public static final String OPEN = "Abrir";
    public static final String CLOSE = "Fechar";
    public static final String NEW = "Novo";
    public static final String LEXICON = "Analisador léxico";
    
    private FiniteAutomaton fa;
    
    public Editor() {
        initComponents();
        initEditor();
        initListener();
        
        runTest();
    }
    
    public FiniteAutomaton getFiniteAutomaton(){
        return fa;
    }
    
    public Source getSource() {
        return new Source(fieldEditor.getText());
    }
    
    public void setTokens(ArrayList<ArrayList<String>> tokens) {
        // TODO: Poupulate Tokens table
    }
    
    public void setErrors(ArrayList<ArrayList<String>> tokens) {
        // TODO: Poupulate Errors table
    }
    
    private void runTest(){
        fieldEditor.setText("void main {\n" +
                            "    #var1: int;\n" +
                            "    #var2, #var3, #var4: float;\n" +
                            "    \n" +
                            "    int #var5 ( int ; float ) {\n" +
                            "        #var6: int;\n" +
                            "        float #var7 {\n" +
                            "            inicio\n" +
                            "                cout << #var7;\n" +
                            "            fim\n" +
                            "            return (#var7)\n" +
                            "        };\n" +
                            "        inicio\n" +
                            "            cout << #var6 ;\n" +
                            "        fim\n" +
                            "        return (#var6)\n" +
                            "    };\n" +
                            "    inicio\n" +
                            "        cin  >> #var1;\n" +
                            "    fim\n" +
                            "}");
        smRun.doClick();
    }
    
    private void initListener(){
        EditorListener el = new EditorListener(this);
        smRun.addActionListener(el);
        smNew.addActionListener(el);
        smOpen.addActionListener(el);
        smSave.addActionListener(el);
        smClose.addActionListener(el);
                
    }
    
    private void initEditor(){       
        fa = new FiniteAutomaton("HAL");
        
        setIconImage(new ImageIcon(getClass().getResource("../resources/favicon.png")).getImage());
        
        fieldEditor = new TextPanelHighLight();
        spEditor = new javax.swing.JScrollPane(fieldEditor);
        txtLineNumber = new TextLineNumber(fieldEditor);
        
        spEditor.setBorder(null);
        
        fieldEditor.setBackground(new java.awt.Color(7, 54, 66));
        fieldEditor.setBorder(null);
        fieldEditor.setFont(new java.awt.Font("Monospaced", 0, 14));
        fieldEditor.setForeground(new java.awt.Color(252, 252, 250));
        fieldEditor.setName("");
                
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
        mnFile = new javax.swing.JMenu();
        smNew = new javax.swing.JMenuItem();
        smOpen = new javax.swing.JMenuItem();
        smSave = new javax.swing.JMenuItem();
        smDivider = new javax.swing.JPopupMenu.Separator();
        smClose = new javax.swing.JMenuItem();
        mnRunner = new javax.swing.JMenu();
        smRun = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("H.A.L. - Analisador Léxico");
        setPreferredSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        MainPanel.setBackground(new java.awt.Color(253, 246, 227));
        MainPanel.setLayout(new java.awt.GridLayout(2, 0));

        pnEditor.setOpaque(false);
        pnEditor.setLayout(new java.awt.GridLayout(1, 0));
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

        mnFile.setForeground(new java.awt.Color(255, 255, 255));
        mnFile.setText("Arquivo");
        mnFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        smNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        smNew.setText("Novo");
        smNew.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        smNew.setIconTextGap(8);
        smNew.setPreferredSize(new java.awt.Dimension(120, 30));
        mnFile.add(smNew);

        smOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        smOpen.setText("Abrir");
        smOpen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        smOpen.setIconTextGap(8);
        smOpen.setPreferredSize(new java.awt.Dimension(120, 30));
        mnFile.add(smOpen);

        smSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        smSave.setText("Salvar");
        smSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        smSave.setIconTextGap(8);
        smSave.setPreferredSize(new java.awt.Dimension(120, 30));
        mnFile.add(smSave);
        mnFile.add(smDivider);

        smClose.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        smClose.setText("Fechar");
        smClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        smClose.setIconTextGap(8);
        smClose.setPreferredSize(new java.awt.Dimension(120, 30));
        mnFile.add(smClose);

        mnBar.add(mnFile);

        mnRunner.setForeground(new java.awt.Color(255, 255, 255));
        mnRunner.setText("Executar");
        mnRunner.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        smRun.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, java.awt.event.InputEvent.CTRL_MASK));
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
        mnRunner.add(smRun);

        mnBar.add(mnRunner);

        setJMenuBar(mnBar);

        getAccessibleContext().setAccessibleDescription("Analisador Léxico da Hybrid Access Language");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void smRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smRunActionPerformed
        
        String[] code = fieldEditor.getText().split("\n");
        
    }//GEN-LAST:event_smRunActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    private javax.swing.JSeparator divider;
    private javax.swing.JMenuBar mnBar;
    private javax.swing.JMenu mnFile;
    private javax.swing.JMenu mnRunner;
    private javax.swing.JPanel pnDebug;
    private javax.swing.JPanel pnEditor;
    private javax.swing.JMenuItem smClose;
    private javax.swing.JPopupMenu.Separator smDivider;
    private javax.swing.JMenuItem smNew;
    private javax.swing.JMenuItem smOpen;
    private javax.swing.JMenuItem smRun;
    private javax.swing.JMenuItem smSave;
    private javax.swing.JScrollPane spAnaliseLexicaDebug;
    private javax.swing.JScrollPane spErros;
    private javax.swing.JTable tbAnaliseLexica;
    private javax.swing.JTable tbErros;
    private javax.swing.JTabbedPane tpContainer;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JEditorPane fieldEditor;
    private javax.swing.JScrollPane spEditor;
    private net.unesc.hal.utils.TextLineNumber txtLineNumber;

}
