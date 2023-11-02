package notepad;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import notepad.About;

public class Notepad extends JFrame implements ActionListener {
    JTextArea area;
    String text;

    Notepad() {
        setTitle("NotePad");
        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/NotepadIcon.png"));
        Image icon = notepadIcon.getImage();
        setIconImage(icon);

        JMenuBar mb = new JMenuBar();
        mb.setBackground(Color.PINK);
        JMenu file = new JMenu("File");
        file.setFont(new Font("AERIAL", Font.PLAIN, 12));

        JMenuItem newDoc = new JMenuItem("New");
        newDoc.addActionListener(this);
        newDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        JMenuItem print = new JMenuItem("Print");
        print.addActionListener(this);
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));

        file.add(newDoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);

        mb.add(file);

        JMenu edit = new JMenu("edit");
        edit.setFont(new Font("AERIAL", Font.PLAIN, 12));

        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));

        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));

        JMenuItem selectAll = new JMenuItem("Select_All");
        selectAll.addActionListener(this);
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(selectAll);

        mb.add(edit);

        // JMenu view = new JMenu("view");
        // view.setFont(new Font("AERIAL", Font.PLAIN, 12));

        // JMenuItem about = new JMenuItem("About");
        // about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));

        // view.add(about);

        // mb.add(view);

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setFont(new Font("AERIAL", Font.PLAIN, 12));

        JMenuItem help = new JMenuItem("Help");
        help.addActionListener(this);
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));

        helpMenu.add(help);

        mb.add(helpMenu);

        setJMenuBar(mb);

        area = new JTextArea();
        area.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        area.setLineWrap(true);
        area.setBackground(Color.WHITE);
        area.setForeground(Color.BLACK);
        area.setWrapStyleWord(true);

        JScrollPane pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    };

    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("New")) {
            area.setText("");
        } else if (ae.getActionCommand().equals("Open")) {
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");

            chooser.addChoosableFileFilter(restrict);
            chooser.showOpenDialog(this);

            int action = chooser.showOpenDialog(this);

            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File file = chooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getActionCommand().equals("Save")) {
            JFileChooser saveAs = new JFileChooser();
            saveAs.setApproveButtonText("Save");
            int act = saveAs.showOpenDialog(this);

            if (act != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File filename = new File(saveAs.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try {
                outFile = new BufferedWriter(new FileWriter(filename));
                area.write(outFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getActionCommand().equals("Print")) {
            try {
                area.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getActionCommand().equals("Exit")) {
            System.exit(0);
        } else if (ae.getActionCommand().equals("Copy")) {
            area.getSelectedText();
        } else if (ae.getActionCommand().equals("Paste")) {
            area.insert(text, area.getCaretPosition());;
        }else if(ae.getActionCommand().equals("Cut")){
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        }else if(ae.getActionCommand().equals("Select_All")){
            area.selectAll();
        }else if(ae.getActionCommand().equals("About")){
            new About().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Notepad();
    }

}
