package net.unesc.hal.views;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.unesc.hal.controllers.FiniteAutomaton;
import net.unesc.hal.analysis.lexicon.Source;
import net.unesc.hal.languages.HAL;
import net.unesc.hal.listeners.ButtonListener;
import net.unesc.hal.listeners.EditorListener;
import net.unesc.hal.utils.TextLineNumber;
import net.unesc.hal.utils.TextPanelHighLight;

/**
 *
 * @author Mateus Generoso
 */
public class Editor extends javax.swing.JFrame {

    public static final String SAVE = "SALVAR";
    public static final String OPEN = "ABRIR";
    public static final String CLOSE = "FECHAR";
    public static final String NEW = "NOVO";
    public static final String RUN = "EXECUTAR";
    public static final String ABOUT = "SOBRE";

    private FiniteAutomaton fa;
    private String latestVersionCode = "";
    private boolean unsaved = false;
    private String path = "";

    public Editor() {
        initComponents();
        initEditor();
        initListener();
    }

    public FiniteAutomaton getFiniteAutomaton() {
        return fa;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
        updateLatestVersion(fieldEditor.getText());
    }

    public Source getSource() {
        return new Source(fieldEditor.getText());
    }

    public void setSource(String value) {
        fieldEditor.setText(value);
        updateLatestVersion(fieldEditor.getText());
    }

    public void updateLatestVersion(String code) {
        this.latestVersionCode = code;
        checkCode();
    }

    public void setTokens(ArrayList<String[]> tokens) {
        // TODO: Poupulate Tokens table

        DefaultTableModel tbModel = new DefaultTableModel();

        tbModel.addColumn("Linha");
        tbModel.addColumn("Código");
        tbModel.addColumn("Token");

        for (int i = 0; i < tokens.size(); i++) {
            tbModel.addRow(tokens.get(i));
        }

        tbLexicon.setModel(tbModel);
    }

    public void clearTokens() {
        ArrayList<String[]> tokens = new ArrayList<>();
        setTokens(tokens);
    }

    public void setStacks(ArrayList<String> stacks) {
        // TODO: Poupulate Tokens table

        DefaultTableModel tbModel = new DefaultTableModel();

        tbModel.addColumn("Pilha");

        for (int i = 0; i < stacks.size(); i++) {
            tbModel.addRow(new String[]{stacks.get(i)});
        }

        tbSyntatic.setModel(tbModel);
    }

    public void clearStacks() {
        ArrayList<String> stacks = new ArrayList<>();
        setStacks(stacks);
    }

    public void setIdentifiers(ArrayList<String[]> identifiers) {

        DefaultTableModel tbModel = new DefaultTableModel();

        tbModel.addColumn("Nome");
        tbModel.addColumn("Categoria");
        tbModel.addColumn("Tipo");
        tbModel.addColumn("Nível");

        for (int i = 0; i < identifiers.size(); i++) {
            tbModel.addRow(identifiers.get(i));
        }

        tbSemantic.setModel(tbModel);
    }

    public void clearIdentifiers() {
        ArrayList<String[]> identifiers = new ArrayList<>();
        setIdentifiers(identifiers);
    }

    public void clearErrors() {
        txaErrors.setText("");
        pnDebug.setVisible(false);
    }

    public void setErrors(ArrayList<String[]> tokens) {

        if (tokens.isEmpty()) {
            txaErrors.setText("✓ Compilado com sucesso!");
        }

        for (int i = 0; i < tokens.size(); i++) {
            if(tokens.get(i).length > 2)
                txaErrors.setText("Linha: " + tokens.get(i)[0] + " -> " + tokens.get(i)[1]);
            else
                txaErrors.setText("Semântico: " + tokens.get(i)[0]);

        }

        spErrors.setVisible(true);
        pnDebug.setVisible(true);
        splitDebug.setDividerLocation(410);
    }

    private void initListener() {
        EditorListener el = new EditorListener(this);
        ButtonListener bl = new ButtonListener();

        btnRun.addActionListener(el);
        btnRun.addMouseListener(bl);
        btnRun.setEnabled(false);

        btnNew.addActionListener(el);
        btnNew.addMouseListener(bl);

        btnOpen.addActionListener(el);
        btnOpen.addMouseListener(bl);

        btnSave.addActionListener(el);
        btnSave.addMouseListener(bl);

        btnAbout.addActionListener(el);
        btnAbout.addMouseListener(bl);

    }

    private void initEditor() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (unsaved) {
                    if (JOptionPane.showConfirmDialog(null, "Deseja salvar as alteraçãos antes de sair?", "Salvar alterações", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                        btnSave.doClick();
                    };
                }
                e.getWindow().dispose();
            }
        }
        );
        fa = new FiniteAutomaton(new HAL());

        setIconImage(new ImageIcon(getClass().getResource("../resources/favicon.png")).getImage());

        fieldEditor = new TextPanelHighLight();
        fieldEditor.setCaretColor(Color.WHITE);
        spEditor = new javax.swing.JScrollPane(fieldEditor);
        txtLineNumber = new TextLineNumber(fieldEditor);

        spEditor.setBorder(null);

        fieldEditor.setBackground(new java.awt.Color(7, 54, 66));
        fieldEditor.setBorder(null);
        fieldEditor.setFont(new java.awt.Font("Monospaced", 0, 14));
        fieldEditor.setForeground(new java.awt.Color(252, 252, 250));
        fieldEditor.setName("");
        fieldEditor.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                checkCode();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                checkCode();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                checkCode();
            }
        });

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

    private void checkCode() {
        String currentCode = fieldEditor.getText();
        btnRun.setEnabled(!currentCode.isEmpty());
        if (!latestVersionCode.equals(currentCode)) {
            this.setTitle("HAL - Analisador *");
            unsaved = true;
        } else {
            this.setTitle("HAL - Analisador");
            unsaved = false;
        }
    };
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolbar = new javax.swing.JPanel();
        toolbarLeft = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDIvider = new javax.swing.JSeparator();
        btnRun = new javax.swing.JButton();
        toolbarRight = new javax.swing.JPanel();
        btnAbout = new javax.swing.JButton();
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
        spSemanticDebug = new javax.swing.JScrollPane();
        tbSemantic = new javax.swing.JTable();
        pnErrors = new javax.swing.JPanel();
        spErrors = new javax.swing.JScrollPane();
        txaErrors = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("H.A.L. - Analisador");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(1024, 600));
        setSize(new java.awt.Dimension(1024, 600));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        toolbar.setBackground(new java.awt.Color(0, 43, 54));
        toolbar.setMaximumSize(new java.awt.Dimension(32767, 45));
        toolbar.setMinimumSize(new java.awt.Dimension(10, 45));
        toolbar.setPreferredSize(new java.awt.Dimension(10, 35));
        toolbar.setLayout(new javax.swing.BoxLayout(toolbar, javax.swing.BoxLayout.LINE_AXIS));

        toolbarLeft.setBackground(new java.awt.Color(0, 43, 54));
        toolbarLeft.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 5));

        btnNew.setBackground(new java.awt.Color(7, 54, 66));
        btnNew.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/unesc/hal/resources/new.png"))); // NOI18N
        btnNew.setText("NOVO");
        btnNew.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnNew.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNew.setFocusable(false);
        btnNew.setMaximumSize(new java.awt.Dimension(59, 25));
        btnNew.setMinimumSize(new java.awt.Dimension(59, 25));
        btnNew.setPreferredSize(new java.awt.Dimension(75, 25));
        toolbarLeft.add(btnNew);

        btnOpen.setBackground(new java.awt.Color(7, 54, 66));
        btnOpen.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        btnOpen.setForeground(new java.awt.Color(255, 255, 255));
        btnOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/unesc/hal/resources/open.png"))); // NOI18N
        btnOpen.setText("ABRIR");
        btnOpen.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnOpen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpen.setFocusable(false);
        btnOpen.setMaximumSize(new java.awt.Dimension(75, 25));
        btnOpen.setMinimumSize(new java.awt.Dimension(75, 25));
        btnOpen.setPreferredSize(new java.awt.Dimension(75, 25));
        toolbarLeft.add(btnOpen);

        btnSave.setBackground(new java.awt.Color(7, 54, 66));
        btnSave.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/unesc/hal/resources/save.png"))); // NOI18N
        btnSave.setText("SALVAR");
        btnSave.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSave.setFocusable(false);
        btnSave.setMaximumSize(new java.awt.Dimension(80, 25));
        btnSave.setMinimumSize(new java.awt.Dimension(80, 25));
        btnSave.setPreferredSize(new java.awt.Dimension(80, 25));
        toolbarLeft.add(btnSave);
        toolbarLeft.add(btnDIvider);

        btnRun.setBackground(new java.awt.Color(7, 54, 66));
        btnRun.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        btnRun.setForeground(new java.awt.Color(255, 255, 255));
        btnRun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/unesc/hal/resources/run.png"))); // NOI18N
        btnRun.setText("EXECUTAR");
        btnRun.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnRun.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRun.setFocusable(false);
        btnRun.setMaximumSize(new java.awt.Dimension(100, 25));
        btnRun.setMinimumSize(new java.awt.Dimension(100, 25));
        btnRun.setPreferredSize(new java.awt.Dimension(100, 25));
        toolbarLeft.add(btnRun);

        toolbar.add(toolbarLeft);

        toolbarRight.setBackground(new java.awt.Color(0, 43, 54));
        toolbarRight.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 5));

        btnAbout.setBackground(new java.awt.Color(7, 54, 66));
        btnAbout.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        btnAbout.setForeground(new java.awt.Color(255, 255, 255));
        btnAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/unesc/hal/resources/info.png"))); // NOI18N
        btnAbout.setText("SOBRE");
        btnAbout.setBorder(null);
        btnAbout.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAbout.setFocusable(false);
        btnAbout.setMaximumSize(new java.awt.Dimension(75, 25));
        btnAbout.setMinimumSize(new java.awt.Dimension(75, 25));
        btnAbout.setPreferredSize(new java.awt.Dimension(75, 25));
        toolbarRight.add(btnAbout);

        toolbar.add(toolbarRight);

        getContentPane().add(toolbar);

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

        tabsResults.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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

        tbSemantic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome", "Categoria", "Tipo", "Nível"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spSemanticDebug.setViewportView(tbSemantic);
        if (tbSemantic.getColumnModel().getColumnCount() > 0) {
            tbSemantic.getColumnModel().getColumn(0).setResizable(false);
            tbSemantic.getColumnModel().getColumn(1).setResizable(false);
            tbSemantic.getColumnModel().getColumn(2).setResizable(false);
            tbSemantic.getColumnModel().getColumn(3).setResizable(false);
        }

        tabsResults.addTab("Semântico", spSemanticDebug);

        pnAnalysis.add(tabsResults);
        tabsResults.getAccessibleContext().setAccessibleName("Results");

        splitDebug.setLeftComponent(pnAnalysis);

        pnErrors.setBackground(new java.awt.Color(0, 43, 54));
        pnErrors.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2), "Saída", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("SansSerif", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        pnErrors.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnErrors.setMinimumSize(new java.awt.Dimension(35, 50));
        pnErrors.setPreferredSize(new java.awt.Dimension(100, 150));
        pnErrors.setRequestFocusEnabled(false);
        pnErrors.setLayout(new javax.swing.BoxLayout(pnErrors, javax.swing.BoxLayout.Y_AXIS));

        spErrors.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        spErrors.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        spErrors.setMinimumSize(new java.awt.Dimension(0, 15));
        spErrors.setPreferredSize(new java.awt.Dimension(164, 100));
        spErrors.setRequestFocusEnabled(false);

        txaErrors.setColumns(20);
        txaErrors.setLineWrap(true);
        txaErrors.setRows(5);
        txaErrors.setWrapStyleWord(true);
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

        getAccessibleContext().setAccessibleName("HAL - Analisador");
        getAccessibleContext().setAccessibleDescription("Analisador Léxico da Hybrid Access Language");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbout;
    private javax.swing.JSeparator btnDIvider;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnRun;
    private javax.swing.JButton btnSave;
    private javax.swing.JPanel pnAnalysis;
    private javax.swing.JPanel pnDebug;
    private javax.swing.JPanel pnEditor;
    private javax.swing.JPanel pnErrors;
    private javax.swing.JPanel pnMain;
    private javax.swing.JScrollPane spErrors;
    private javax.swing.JScrollPane spLexiconDebug;
    private javax.swing.JScrollPane spSemanticDebug;
    private javax.swing.JScrollPane spSyntaticDebug;
    private javax.swing.JSplitPane splitDebug;
    private javax.swing.JSplitPane splitMain;
    private javax.swing.JTabbedPane tabsResults;
    private javax.swing.JTable tbLexicon;
    private javax.swing.JTable tbSemantic;
    private javax.swing.JTable tbSyntatic;
    private javax.swing.JPanel toolbar;
    private javax.swing.JPanel toolbarLeft;
    private javax.swing.JPanel toolbarRight;
    private javax.swing.JTextArea txaErrors;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JEditorPane fieldEditor;
    private javax.swing.JScrollPane spEditor;
    private net.unesc.hal.utils.TextLineNumber txtLineNumber;

}
