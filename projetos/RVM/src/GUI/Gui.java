package GUI;

import rvm.*;
import rvm.VirtualMachine;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author  Ernestas Prisakaru OS GUI
 * @version 1.0
 */ 

public class Gui {
    private JFrame frame;
    RM rm = null;
    VirtualMachine vm = null;
    
    //- Main page
    private JButton loadFile; // load source code
    private JButton run; // run a program
    private JButton step; // make a step 
    
    private JList stackList; // stack
    private JTable realMemoryTable; // real memory
    private JTable virtualMemoryTable;
    
    private DefaultListModel stackModel; // stack values
    private MemoryTableModel realMemoryModel; // real memory words
    private MemoryTableModel virtualMemoryModel; // virtual memory words
    
    private MemoryTableCellRenderer realMemoryCell; 
    private MemoryTableCellRenderer virtualMemoryCell;
    
    private JScrollPane spStackList; 
    private JScrollPane spRealMemoryTable;
    private JScrollPane spVirtualMemoryTable;
    // registru reiksmes
    JTextField tfRegR;
    JTextField tfRegSF;
    JTextField tfRegIP;
    JTextField tfRegSP;
    JTextField tfRegDS;
    JTextField tfRegCS;
    JTextField tfRegSS;
    JTextField tfRegES;
    
    // registrai
    JLabel lbRegR;
    JLabel lbRegSF;    
    JLabel lbRegIP;
    JLabel lbRegSP;
    JLabel lbRegDS;
    JLabel lbRegCS;
    JLabel lbRegSS;
    JLabel lbRegES;
    
    //TODO grupe
    JPanel panelMemoryRepresentation;
    ButtonGroup rbGroup;
    JRadioButton rbChar;
    JRadioButton rbNum;
    
    JTextArea taOutput;
    JScrollPane spOutput;
    JTextField tfInput;
    
    JFileChooser fc;
    boolean isLoaded = false;
    
    private void initProject() throws FileNotFoundException {
        rm = new RM();
        vm = rm.startNewVM("src/program1");
    }

    public Gui() throws FileNotFoundException {
        frame = new JFrame();   
        initProject();
        initUI();
    }
    
    public final void initUI() {
        frame.setTitle("OS");
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.add(createMainPage());
        frame.setVisible(true);
    }

    public static void main(String[] arg) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException err) {
            System.out.println(err);
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Gui graphical = null;
                try {
                    graphical = new Gui();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }
                graphical.frame.setVisible(true);
            }
        });
    }
    
private void refreshAll() {
    virtualMemoryCell.setNextInstructionAddress(vm.getCS() + vm.getIP());
    updateRealMemoryModel();
    updateVirtualMemoryModel();
    updateStackModel();
    tfRegR.setText(String.valueOf(vm.getR()));
    tfRegSF.setText(String.valueOf(vm.getSF()));
    tfRegIP.setText(String.valueOf(vm.getIP()));
    tfRegSP.setText(String.valueOf(vm.getSP()));
    tfRegDS.setText(String.valueOf(vm.getDS()));
    tfRegCS.setText(String.valueOf(vm.getCS()));
    tfRegSS.setText(String.valueOf(vm.getSS()));
}
 
// main page panel
public JPanel createMainPage() {
        JPanel panel = new JPanel( new GridBagLayout() );
        GridBagConstraints c1 = new GridBagConstraints();

        prepareRealMemoryModel();
        prepareVirtualMemoryModel();
        prepareStackModel();


        //panel.add(stackList);
        stackList.setSelectedIndex(-1);

        loadFile = new JButton("Load...");
        //loadFile.setPreferredSize(new Dimension(20, 70));
        fc = new JFileChooser();
        fc.setFileFilter(new SourceFileFilter());
        fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
        loadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              int returnVal = fc.showOpenDialog(frame);
              if (returnVal == JFileChooser.APPROVE_OPTION) {
                  File file = fc.getSelectedFile();
              } 
           }
        });

        step = new JButton("Step>");
        //.setPreferredSize(new Dimension(20, 70));
        step.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.step();
                refreshAll();
            }
        });
        
        run = new JButton("Run>>");
        //run.setPreferredSize(new Dimension(20, 70));
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // runint
            }
        });

        java.awt.GridBagConstraints gridBagConstraints;
        // ====== deliojimas
        spRealMemoryTable = new JScrollPane();
        spRealMemoryTable.setViewportView(realMemoryTable);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 28;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        //gridBagConstraints.ipadx = 1058;
        gridBagConstraints.ipady = 193;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(18, 9, 11, 10);
        panel.add(spRealMemoryTable, gridBagConstraints);

        spStackList = new JScrollPane();
        spStackList.setViewportView(stackList);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 357;
        gridBagConstraints.ipady = 261;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 0, 10);
        panel.add(spStackList, gridBagConstraints);

        
        step.setText("Step>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.ipadx = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 2, 0, 10);
        panel.add(step, gridBagConstraints);

        loadFile.setText("Load ...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 27;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 2, 0, 10);
        panel.add(loadFile, gridBagConstraints);

        spVirtualMemoryTable = new JScrollPane();
        spVirtualMemoryTable.setViewportView(virtualMemoryTable);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 824;
        gridBagConstraints.ipady = 267;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 0, 0);
        panel.add(spVirtualMemoryTable, gridBagConstraints);
        
        run.setText("Run>>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 2, 0, 10);
        panel.add(run, gridBagConstraints);

        tfInput = new JTextField();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 27;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 825;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 9, 0, 0);
        panel.add(tfInput, gridBagConstraints);

        taOutput = new JTextArea();
        spOutput = new JScrollPane();
        //taOutput.setColumns(20);
        //taOutput.setRows(5);
        spOutput.setViewportView(taOutput);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 26;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 825;
        gridBagConstraints.ipady = 112;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(11, 9, 0, 0);
        panel.add(spOutput, gridBagConstraints);

        panelMemoryRepresentation = new JPanel();
        panelMemoryRepresentation.setBorder(javax.swing.BorderFactory.createTitledBorder("Representation"));
        panelMemoryRepresentation.setLayout(new java.awt.GridBagLayout());

        rbChar = new JRadioButton();
        rbChar.setText("Char");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 12, 0, 30);
        panelMemoryRepresentation.add(rbChar, gridBagConstraints);

        rbNum = new JRadioButton();
        rbNum.setText("Num");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 14, 0);
        panelMemoryRepresentation.add(rbNum, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.ipadx = 124;
        gridBagConstraints.ipady = -27;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 10);
        panel.add(panelMemoryRepresentation, gridBagConstraints);
        
        rbGroup = new ButtonGroup();
        rbGroup.add(rbChar);
        rbGroup.add(rbNum);
//        rbChar.add

        lbRegR = new JLabel();
        lbRegR.setText("R");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 10, 0, 0);
        panel.add(lbRegR, gridBagConstraints);

        lbRegSF = new JLabel();
        lbRegSF.setText("SF");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 10, 0, 0);
        panel.add(lbRegSF, gridBagConstraints);

        lbRegIP = new JLabel();
        lbRegIP.setText("IP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 10, 0, 0);
        panel.add(lbRegIP, gridBagConstraints);

        lbRegSP = new JLabel();
        lbRegSP.setText("SP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 10, 0, 0);
        panel.add(lbRegSP, gridBagConstraints);
        
        tfRegR = new JTextField();
        tfRegR.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 57;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 10, 0, 0);
        panel.add(tfRegR, gridBagConstraints);
        
        tfRegSF = new JTextField();
        tfRegSF.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 57;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        panel.add(tfRegSF, gridBagConstraints);
        
        tfRegIP = new JTextField();
        tfRegIP.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 57;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        panel.add(tfRegIP, gridBagConstraints);
        
        tfRegSP = new JTextField();
        tfRegSP.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 57;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        panel.add(tfRegSP, gridBagConstraints);

        lbRegDS = new JLabel();
        lbRegDS.setText("DS");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(14, 3, 0, 0);
        panel.add(lbRegDS, gridBagConstraints);

        lbRegCS = new JLabel();
        lbRegCS.setText("CS");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(4, 3, 0, 0);
        panel.add(lbRegCS, gridBagConstraints);

        lbRegSS = new JLabel();
        lbRegSS.setText("SS");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(4, 3, 0, 0);
        panel.add(lbRegSS, gridBagConstraints);

        lbRegES = new JLabel();
        lbRegES.setText("ES");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 0, 0);
        panel.add(lbRegES, gridBagConstraints);
        
        tfRegDS = new JTextField();
        tfRegDS.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 32;
        gridBagConstraints.ipadx = 57;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 10, 0, 10);
        panel.add(tfRegDS, gridBagConstraints);
        
        tfRegCS = new JTextField();
        tfRegCS.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 32;
        gridBagConstraints.ipadx = 57;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 10, 0, 10);
        panel.add(tfRegCS, gridBagConstraints);
        
        tfRegSS = new JTextField();
        tfRegSS.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridheight = 32;
        gridBagConstraints.ipadx = 57;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 10, 0, 10);
        panel.add(tfRegSS, gridBagConstraints);
        
        tfRegES = new JTextField();
        tfRegES.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridheight = 32;
        gridBagConstraints.ipadx = 57;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 10, 0, 10);
        panel.add(tfRegES, gridBagConstraints);

        //frame.pack();
        return panel;
}
 
    private void prepareRealMemoryModel () {
        int rows = Constants.MEMORY_SIZE / Constants.BLOCK_SIZE;
        realMemoryModel = new MemoryTableModel(0x11, rows); // eiluciu nulis poto iterpsim
        realMemoryTable = new JTable(realMemoryModel); 
        realMemoryTable.getTableHeader().setReorderingAllowed(false);
        realMemoryTable.getTableHeader().setResizingAllowed(false);
        realMemoryTable.setDragEnabled(false);
        realMemoryTable.setSelectionForeground(Color.black);
        realMemoryCell = new MemoryTableCellRenderer();
        try {
            realMemoryTable.setDefaultRenderer(Class.forName("java.lang.String"), realMemoryCell);
        } catch (ClassNotFoundException ex) {
            System.out.println("blet");
        }
        updateRealMemoryModel();
        realMemoryTable.setVisible(true);
    }
  
    private void updateRealMemoryModel() {
        Word[] mem = rm.getMemoryGui();
        for (int i=0; i < rvm.Constants.MEMORY_SIZE; i++) {
            int col = i % 0x10;
            int row = i / 0x10;
            if (mem[i] == null) {
                mem[i] = new Word(0x0);
            }
            realMemoryModel.setValueAt(mem[i].toString(), row, col+1);
        }
    }
    
    private void prepareVirtualMemoryModel () {
        int rows = vm.getMemory().getSize() / Constants.BLOCK_SIZE + 1 + 1;
        virtualMemoryModel = new MemoryTableModel(0x11, rows);
        virtualMemoryTable = new JTable(virtualMemoryModel);
        virtualMemoryTable.getTableHeader().setReorderingAllowed(false);
        virtualMemoryTable.getTableHeader().setResizingAllowed(false);
        virtualMemoryTable.setDragEnabled(false);
        virtualMemoryTable.setSelectionForeground(Color.black);
        virtualMemoryCell = new MemoryTableCellRenderer();
        try {
            virtualMemoryTable.setDefaultRenderer(Class.forName("java.lang.String"), virtualMemoryCell);
        } catch (ClassNotFoundException ex) {
            System.out.println("blet");
        }
        updateVirtualMemoryModel();
        virtualMemoryTable.setVisible(true);
    }      
    
    private void updateVirtualMemoryModel() {
        virtualMemoryTable.removeAll();
        virtualMemoryTable.repaint();
        Word[] vmem = vm.getMemory().getVirtualMemoryGui();
        for (int i=0; i < vm.getMemory().getSize(); i++) {
            int col = i % 0x10;
            int row = i / 0x10;
            if (vmem[i] == null) {
                vmem[i] = new Word(0x0);
            }
            virtualMemoryModel.setValueAt(vmem[i].toInt(), row, col+1);
        }
    }
    
    private void prepareStackModel () {
        stackModel = new DefaultListModel();
        stackList = new JList(stackModel);
        stackList.setVisible(true);

        updateStackModel();
    }
  
    private void updateStackModel() {
        if (vm != null) {
            stackModel.clear();
            int index = 0;
            System.out.println(vm.getSS() + " " + vm.getSP());
            for (int i=0; i< vm.getSP(); i++) {
               
                stackModel.add(index, vm.getMemory().getVirtualMemoryGui()[vm.getSS()+i].toInt());
                index++;
            }
            stackList.repaint();
        }
    }

}






