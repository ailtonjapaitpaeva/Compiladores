package panda.Main;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Formatter;

import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import panda.Absyn.Absyn;
import panda.Absyn.Exp;
import panda.ErrorMsg.ErrorMsg;
import panda.ErrorMsg.Loc;
import panda.Parse.PandaCup;
import panda.Parse.PandaLex;
import panda.Util.BoxTree;

@SuppressWarnings("serial")
public class SemantGui extends JFrame implements ActionListener {
   private GridBagLayout layout = new GridBagLayout();
   private GridBagConstraints constraints = new GridBagConstraints();

   private JButton openButton;
   private JTextField fileField;
   private JFileChooser chooser;
   private JTextArea programArea;
   private JTextArea logArea;
   private JButton saveButton;
   private JButton compileButton;
   private JButton asyButton;
   private JButton quitButton;
   private JTree tree;
   private JDialog dialog;
   private JButton dialogClose;

   private Exp exp;

   public SemantGui() {
      super("Tiger Compiler");
      setLayout(layout);

      JLabel label = new JLabel("Tiger source:");
      addComponent(label, 0, 0, 1, 1, 0);

      fileField = new JTextField(25);
      fileField.addActionListener(this);
      addComponent(fileField, 0, 1, 1, 1, GridBagConstraints.HORIZONTAL);

      openButton = new JButton("Browse");
      openButton.addActionListener(this);
      addComponent(openButton, 0, 2, 1, 1, GridBagConstraints.NONE);

      saveButton = new JButton("Save");
      saveButton.addActionListener(this);
      addComponent(saveButton, 0, 3, 1, 1, GridBagConstraints.NONE);

      compileButton = new JButton("Compile");
      compileButton.addActionListener(this);
      addComponent(compileButton, 0, 4, 1, 1, GridBagConstraints.NONE);

      asyButton = new JButton("Tree");
      asyButton.addActionListener(this);
      addComponent(asyButton, 0, 5, 1, 1, GridBagConstraints.NONE);

      quitButton = new JButton("Quit");
      quitButton.addActionListener(this);
      addComponent(quitButton, 0, 6, 1, 1, GridBagConstraints.NONE);

      chooser = new JFileChooser();

      programArea = new JTextArea(12, 80);
      programArea.setFont(new Font("Courier", Font.BOLD, 18));
      JScrollPane scrollPane = new JScrollPane(programArea);
      addComponent(scrollPane, 1, 0, 7, 1, GridBagConstraints.BOTH);

      logArea = new JTextArea(5, 80);
      scrollPane = new JScrollPane(logArea);
      addComponent(scrollPane, 2, 0, 7, 1, GridBagConstraints.BOTH);

      JPanel panel = new JPanel(new GridLayout(1, 4));
      addComponent(panel, 3, 0, 7, 1, GridBagConstraints.BOTH);

      tree = new JTree(new DefaultTreeModel(null));
      scrollPane = new JScrollPane(tree);
      panel.add(scrollPane);

      pack();
   }

   private void addComponent(Component component, int row, int column, int width, int height, int fill) {
      addComponent(this, component, row, column, width, height, fill);
   }

   private void addComponent(Container container, Component component, int row, int column, int width, int height,
         int fill) {
      constraints.gridx = column;
      constraints.gridy = row;
      constraints.gridwidth = width;
      constraints.gridheight = height;
      constraints.fill = fill;
      constraints.weightx = 1;
      constraints.weighty = 1;
      constraints.insets = new Insets(2, 2, 2, 2);
      layout.setConstraints(component, constraints);
      container.add(component);
   }

   public static void main(String args[]) throws Exception {
      SemantGui application = new SemantGui();
      application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      application.setVisible(true);
   }

   private void readSource() {
      try {
         FileReader file = new FileReader(fileField.getText());
         StringBuilder str = new StringBuilder();
         while (true) {
            int ch = file.read();
            if (ch == -1)
               break;
            str.append((char) ch);
         }
         file.close();
         programArea.setText(str.toString());
      } catch (FileNotFoundException e1) {
         JOptionPane.showMessageDialog(SemantGui.this, "File not found:\n" + fileField.getText(),
               "Error reading file", JOptionPane.ERROR_MESSAGE);
      } catch (IOException e2) {
         JOptionPane.showMessageDialog(SemantGui.this, "Error reading file:\n" + fileField.getText(),
               "Error reading file", JOptionPane.ERROR_MESSAGE);
      }
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == openButton) {
         int returnVal = chooser.showOpenDialog(SemantGui.this);
         if (returnVal == JFileChooser.APPROVE_OPTION)
            fileField.setText(chooser.getSelectedFile().getPath());
         readSource();
      }
      if (e.getSource() == saveButton) {
         String filePath = fileField.getText().trim();
         if (!filePath.isEmpty())
            chooser.setSelectedFile(new File(filePath));
         int returnVal = chooser.showSaveDialog(SemantGui.this);
         if (returnVal == JFileChooser.APPROVE_OPTION) {
            filePath = chooser.getSelectedFile().getPath();
            try {
               FileWriter file = new FileWriter(filePath);
               file.write(programArea.getText());
               file.close();
               fileField.setText(filePath);
            } catch (IOException e1) {
               JOptionPane.showMessageDialog(SemantGui.this, "Error writing file:\n" + filePath,
                     "Error writing file", JOptionPane.ERROR_MESSAGE);
            }
         }
      } else if (e.getSource() == fileField)
         readSource();
      else if (e.getSource() == compileButton)
         compileProgram();
      else if (e.getSource() == asyButton)
         asyGraph();
      else if (e.getSource() == dialogClose)
         dialog.dispose();
      else if (e.getSource() == quitButton)
         dispose();
   }

   private void compileProgram() {
      ErrorMsg errorMsg = new GuiErrorMsg(fileField.getText(), logArea);
      Absyn.E = errorMsg;
      StringReader file = new StringReader(programArea.getText());
      
      ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();      
      PandaLex scanner = new PandaLex("-", file, symbolFactory, errorMsg);
      PandaCup parser = new PandaCup(scanner, symbolFactory, errorMsg);

      try {
         logArea.setText("");
         Symbol prog = parser.parse();
         exp = (Exp) prog.value;
         DefaultTreeModel treeModel = new DefaultTreeModel(BoxTree.toDefaultMutableTreeNode(exp.toTree()));
         tree.setModel(treeModel);
         for (int row = 0; row < tree.getRowCount(); row++)
            tree.expandRow(row);
         panda.Types.Type et = exp.semant();
         logArea.append("===> " + et.toString() + "\n");
         // logArea.append(et.exp.toString());
      } catch (Exception e) {
         // e.printStackTrace();
         JOptionPane.showMessageDialog(SemantGui.this, "Error compiling program\n", "Error compiling program",
               JOptionPane.ERROR_MESSAGE);
      }

   }

   private void asyGraph() {
      if (exp != null)
         try {
            String imageType = "png";
            File tempfile = File.createTempFile("parsetree-", ".asy");
            tempfile.deleteOnExit();
            BoxTree.dot(exp.toTree(), tempfile);
            File tempfile2 = File.createTempFile("parsetree-", "." + imageType);
            tempfile2.deleteOnExit();
            String cmd[] = { "dot", "-Tpng", "-o", tempfile2.getPath(), tempfile.getPath() };
            Process proc = Runtime.getRuntime().exec(cmd, null, tempfile.getParentFile());
            proc.waitFor();
            dialog = new JDialog(SemantGui.this, "Parse Tree");
            dialog.setLayout(layout);
            Icon icon = new ImageIcon(tempfile2.getPath());
            JLabel label = new JLabel(icon);
            JScrollPane scrollPane = new JScrollPane(label);
            addComponent(dialog, scrollPane, 0, 0, 1, 1, GridBagConstraints.BOTH);
            dialogClose = new JButton("Close");
            dialogClose.addActionListener(this);
            addComponent(dialog, dialogClose, 1, 0, 1, 1, GridBagConstraints.NONE);
            dialog.pack();
            dialog.setVisible(true);
         } catch (Exception e) {
            e.printStackTrace();
         }
   }
}

class GuiErrorMsg extends ErrorMsg {
   private JTextArea textArea;

   public GuiErrorMsg(String f, JTextArea textArea) {
      // super(f);
      this.textArea = textArea;
   }

   public void error(Loc loc, String msg) {
      Absyn.E.errorCounter++;
      Formatter fmt = new Formatter();
      fmt.format("%s %s\n", loc, msg);
      textArea.append(fmt.toString());
      fmt.close();
   }
}
