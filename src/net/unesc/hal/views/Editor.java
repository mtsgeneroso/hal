package net.unesc.hal.views;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import net.unesc.hal.controllers.FiniteAutomaton;
import net.unesc.hal.data.Source;
import net.unesc.hal.languages.HAL;
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
    public static final String LEXICON = "Léxico";
    
    private FiniteAutomaton fa;
    
    public Editor() {
        initComponents();
        initEditor();
        initListener();
    }
    
    public FiniteAutomaton getFiniteAutomaton(){
        return fa;
    }
    
    public Source getSource() {
        return new Source(fieldEditor.getText());
    }
    
    public void setSource(String value){
        fieldEditor.setText(value);
    }
    
    public void setTokens(ArrayList<String[]> tokens) {
        // TODO: Poupulate Tokens table
        
        DefaultTableModel tbModel = new DefaultTableModel();
        
        tbModel.addColumn("Linha");
        tbModel.addColumn("Código");
        tbModel.addColumn("Token");
        
        for(int i = 0; i < tokens.size(); i++){
            tbModel.addRow(tokens.get(i));
        }
                
        tbLexicon.setModel(tbModel);   
    }
    
    public void setStacks(ArrayList<String> stacks) {
        // TODO: Poupulate Tokens table
        
        DefaultTableModel tbModel = new DefaultTableModel();
        
        tbModel.addColumn("Pilha");
        
        for(int i = 0; i < stacks.size(); i++){
            tbModel.addRow(new String[]{stacks.get(i)});
        }
                
        tbSyntatic.setModel(tbModel);   
    }
    
    public void clearStacks(){
        ArrayList<String> stacks = new ArrayList<>();
        setStacks(stacks);
    }
    
    public void clearTokens(){
        ArrayList<String[]> tokens = new ArrayList<>();
        setTokens(tokens);
    }
    
    public void clearErrors(){
        txaErrors.setText("");
        pnDebug.setVisible(false);
    }
    
    public void setErrors(ArrayList<String[]> tokens) {
        
        for(int i = 0; i < tokens.size(); i++){
            txaErrors.setText("Linha: " + tokens.get(i)[0] + " -> " + tokens.get(i)[1]);
        }
        
        pnDebug.setVisible(true);
        splitDebug.setDividerLocation(400);
    }
    
    private void initListener(){
        EditorListener el = new EditorListener(this);
        smRunLexicon.addActionListener(el);
        smNew.addActionListener(el);
        smOpen.addActionListener(el);
        smSave.addActionListener(el);
        smClose.addActionListener(el);
                
    }
    
    private void initEditor(){       
        fa = new FiniteAutomaton(new HAL());
        
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
        
        txaErrors.setDisabledTextColor(Color.BLACK);
        
        pnEditor.add(spEditor);
        pnDebug.setVisible(false);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnMain = new javax.swing.JPanel();
        splitMain = new javax.swing.JSplitPane();
        pnEditor = new javax.swing.JPanel();
        pnDebug = new javax.swing.JPanel();
        splitDebug = new javax.swing.JSplitPane();
        pnAnalysis = new javax.swing.JPanel();
        tabsResults = new javax.swing.JTabbedPane();
        spLexiconDebug = new javax.swing.JScrollPane();
        tbLexicon = new javax.swing.JTable();
        spSyntaticDebug = new javax.swing.JScrollPane();
        tbSyntatic = new javax.swing.JTable();
        pnErrors = new javax.swing.JPanel();
        spErrors = new javax.swing.JScrollPane();
        txaErrors = new javax.swing.JTextArea();
        mnBar = new javax.swing.JMenuBar();
        mnFile = new javax.swing.JMenu();
        smNew = new javax.swing.JMenuItem();
        smOpen = new javax.swing.JMenuItem();
        smSave = new javax.swing.JMenuItem();
        smDivider = new javax.swing.JPopupMenu.Separator();
        smClose = new javax.swing.JMenuItem();
        mnRunner = new javax.swing.JMenu();
        smRunLexicon = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("H.A.L. - Analisador");
        setPreferredSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        pnMain.setBackground(new java.awt.Color(0, 43, 54));
        pnMain.setLayout(new java.awt.GridLayout(1, 1));

        splitMain.setBackground(new java.awt.Color(0, 43, 54));
        splitMain.setBorder(null);
        splitMain.setDividerLocation((int) (this.getBounds().getWidth() / 2));
        splitMain.setDividerSize(9);
        splitMain.setToolTipText("Redimensione horizontalmente o painel de código e debug");
        splitMain.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pnEditor.setName(""); // NOI18N
        pnEditor.setOpaque(false);
        pnEditor.setLayout(new java.awt.GridLayout(1, 0));
        splitMain.setLeftComponent(pnEditor);

        pnDebug.setBackground(new java.awt.Color(0, 43, 54));
        pnDebug.setLayout(new javax.swing.BoxLayout(pnDebug, javax.swing.BoxLayout.Y_AXIS));

        splitDebug.setBackground(new java.awt.Color(0, 43, 54));
        splitDebug.setBorder(null);
        splitDebug.setDividerSize(9);
        splitDebug.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        splitDebug.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pnAnalysis.setBackground(new java.awt.Color(0, 43, 54));
        pnAnalysis.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Resultado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        pnAnalysis.setToolTipText("");
        pnAnalysis.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnAnalysis.setLayout(new javax.swing.BoxLayout(pnAnalysis, javax.swing.BoxLayout.LINE_AXIS));

        spLexiconDebug.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        spLexiconDebug.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tbLexicon.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tbLexicon.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbLexicon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Linha", "Código", "Token"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbLexicon.setGridColor(new java.awt.Color(204, 204, 204));
        tbLexicon.getTableHeader().setReorderingAllowed(false);
        spLexiconDebug.setViewportView(tbLexicon);
        if (tbLexicon.getColumnModel().getColumnCount() > 0) {
            tbLexicon.getColumnModel().getColumn(0).setResizable(false);
            tbLexicon.getColumnModel().getColumn(0).setPreferredWidth(5);
            tbLexicon.getColumnModel().getColumn(1).setResizable(false);
            tbLexicon.getColumnModel().getColumn(1).setPreferredWidth(5);
            tbLexicon.getColumnModel().getColumn(1).setHeaderValue("Código");
            tbLexicon.getColumnModel().getColumn(2).setResizable(false);
            tbLexicon.getColumnModel().getColumn(2).setHeaderValue("Token");
        }

        tabsResults.addTab("Léxico", spLexiconDebug);

        spSyntaticDebug.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        spSyntaticDebug.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tbSyntatic.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tbSyntatic.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tbSyntatic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Pilha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSyntatic.setGridColor(new java.awt.Color(204, 204, 204));
        tbSyntatic.getTableHeader().setReorderingAllowed(false);
        spSyntaticDebug.setViewportView(tbSyntatic);
        if (tbSyntatic.getColumnModel().getColumnCount() > 0) {
            tbSyntatic.getColumnModel().getColumn(0).setResizable(false);
            tbSyntatic.getColumnModel().getColumn(0).setPreferredWidth(5);
        }

        tabsResults.addTab("Sintático", spSyntaticDebug);

        pnAnalysis.add(tabsResults);
        tabsResults.getAccessibleContext().setAccessibleName("Results");

        splitDebug.setLeftComponent(pnAnalysis);

        pnErrors.setBackground(new java.awt.Color(0, 43, 54));
        pnErrors.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Erro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        pnErrors.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnErrors.setMinimumSize(new java.awt.Dimension(35, 50));
        pnErrors.setPreferredSize(new java.awt.Dimension(100, 150));
        pnErrors.setRequestFocusEnabled(false);
        pnErrors.setLayout(new javax.swing.BoxLayout(pnErrors, javax.swing.BoxLayout.PAGE_AXIS));

        spErrors.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        spErrors.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        spErrors.setPreferredSize(new java.awt.Dimension(168, 100));
        spErrors.setRequestFocusEnabled(false);

        txaErrors.setColumns(20);
        txaErrors.setRows(5);
        txaErrors.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txaErrors.setEnabled(false);
        txaErrors.setFocusable(false);
        txaErrors.setPreferredSize(new java.awt.Dimension(164, 100));
        txaErrors.setRequestFocusEnabled(false);
        spErrors.setViewportView(txaErrors);

        pnErrors.add(spErrors);

        splitDebug.setRightComponent(pnErrors);

        pnDebug.add(splitDebug);

        splitMain.setRightComponent(pnDebug);

        pnMain.add(splitMain);

        getContentPane().add(pnMain);

        mnBar.setBackground(new java.awt.Color(0, 43, 54));
        mnBar.setBorder(null);
        mnBar.setToolTipText("");
        mnBar.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        mnBar.setPreferredSize(new java.awt.Dimension(104, 25));

        mnFile.setForeground(new java.awt.Color(255, 255, 255));
        mnFile.setText("Arquivo");
        mnFile.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        smNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        smNew.setText("Novo");
        smNew.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        smNew.setIconTextGap(8);
        smNew.setPreferredSize(new java.awt.Dimension(120, 30));
        mnFile.add(smNew);

        smOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        smOpen.setText("Abrir");
        smOpen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        smOpen.setIconTextGap(8);
        smOpen.setPreferredSize(new java.awt.Dimension(120, 30));
        mnFile.add(smOpen);

        smSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        smSave.setText("Salvar");
        smSave.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        smSave.setIconTextGap(8);
        smSave.setPreferredSize(new java.awt.Dimension(120, 30));
        mnFile.add(smSave);
        mnFile.add(smDivider);

        smClose.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        smClose.setText("Fechar");
        smClose.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        smClose.setIconTextGap(8);
        smClose.setPreferredSize(new java.awt.Dimension(120, 30));
        mnFile.add(smClose);

        mnBar.add(mnFile);

        mnRunner.setForeground(new java.awt.Color(255, 255, 255));
        mnRunner.setText("Executar");
        mnRunner.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        smRunLexicon.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, java.awt.event.InputEvent.CTRL_MASK));
        smRunLexicon.setText("Léxico");
        smRunLexicon.setBorder(null);
        smRunLexicon.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        smRunLexicon.setDoubleBuffered(true);
        smRunLexicon.setIconTextGap(8);
        smRunLexicon.setPreferredSize(new java.awt.Dimension(177, 30));
        smRunLexicon.setRequestFocusEnabled(false);
        smRunLexicon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smRunLexiconActionPerformed(evt);
            }
        });
        mnRunner.add(smRunLexicon);

        mnBar.add(mnRunner);

        setJMenuBar(mnBar);

        getAccessibleContext().setAccessibleDescription("Analisador Léxico da Hybrid Access Language");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void smRunLexiconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smRunLexiconActionPerformed
    }//GEN-LAST:event_smRunLexiconActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar mnBar;
    private javax.swing.JMenu mnFile;
    private javax.swing.JMenu mnRunner;
    private javax.swing.JPanel pnAnalysis;
    private javax.swing.JPanel pnDebug;
    private javax.swing.JPanel pnEditor;
    private javax.swing.JPanel pnErrors;
    private javax.swing.JPanel pnMain;
    private javax.swing.JMenuItem smClose;
    private javax.swing.JPopupMenu.Separator smDivider;
    private javax.swing.JMenuItem smNew;
    private javax.swing.JMenuItem smOpen;
    private javax.swing.JMenuItem smRunLexicon;
    private javax.swing.JMenuItem smSave;
    private javax.swing.JScrollPane spErrors;
    private javax.swing.JScrollPane spLexiconDebug;
    private javax.swing.JScrollPane spSyntaticDebug;
    private javax.swing.JSplitPane splitDebug;
    private javax.swing.JSplitPane splitMain;
    private javax.swing.JTabbedPane tabsResults;
    private javax.swing.JTable tbLexicon;
    private javax.swing.JTable tbSyntatic;
    private javax.swing.JTextArea txaErrors;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JEditorPane fieldEditor;
    private javax.swing.JScrollPane spEditor;
    private net.unesc.hal.utils.TextLineNumber txtLineNumber;

}
