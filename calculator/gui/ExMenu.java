package calculator.gui;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class ExMenu {
    private JFrame frame;
    private final String text;

    public ExMenu(String text) {
        this.text = text;
    }

    public void go() {
        frame = new JFrame("Menu");
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem saveMenuItem = new JMenuItem("Save");

        newMenuItem.addActionListener(new NewMenuListener());
        saveMenuItem.addActionListener(new SaveMenuListener());
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        frame.setSize(800, 1000);
        frame.setVisible(true);
    }

    public class NewMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            JFileChooser fileNew = new JFileChooser();
            fileNew.showOpenDialog(frame);
            saveFile(fileNew.getSelectedFile());
        }
    }
    public class SaveMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }

    private void saveFile(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(text);
            writer.close();
        } catch (IOException ex) {
            System.out.println("couldn't write out");
        }
    }
}

