package calculator.gui;

import calculator.AbstractValueParser;
import calculator.Calculator;
import calculator.datatypes.complex.ComplexValueParser;
import calculator.datatypes.integer.IntegerValueParser;
import calculator.datatypes.real.RealValueParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SimpleCalculator {
    private static final AbstractValueParser[] valueParsers = new AbstractValueParser[] { new IntegerValueParser(),
            new RealValueParser(), new ComplexValueParser() };

    public static void main(String[] args) {

        JPanel windowContent = new JPanel();
        GridLayout gl = new GridLayout(9, 1);
        windowContent.setLayout(gl);

        String[] datatype = {"Integer", "Real", "Complex"};
        JComboBox<String> comboBox = new JComboBox<>(datatype);

        JLabel label1 = new JLabel("Number 1st:");
        JTextField field1 = new JTextField(30);

        String[] op = {"+", "-", "*", "/"};
        JComboBox<String> operations = new JComboBox<>(op);

        JLabel label2 = new JLabel("Number 2nd:");
        JTextField field2 = new JTextField(30);

        JTextField result = new JTextField(30);

        ActionListener actionListener = e -> {
            try {
                int choice = comboBox.getSelectedIndex();
                AbstractValueParser parser = valueParsers[choice];
                Calculator calc = new Calculator(parser);
                String arg1 = field1.getText();
                String operation = String.valueOf(operations.getSelectedItem());
                String arg2 = field2.getText();
                String res = calc.calculate(arg1, operation, arg2);
                result.setText(res);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.exit(1);
            }
        };

        JButton resButton = new JButton("Result:");
        resButton.addActionListener(actionListener);

        JButton fileButton = new JButton("Save to file");
        fileButton.addActionListener(e -> {
            ExMenu fileMenu = new ExMenu(field1.getText() + " " + operations.getSelectedItem()
                    + " " + field2.getText() + " = " + result.getText());
            fileMenu.go();
        });

        windowContent.add(comboBox);
        windowContent.add(label1);
        windowContent.add(field1);
        windowContent.add(operations);
        windowContent.add(label2);
        windowContent.add(field2);
        windowContent.add(resButton);
        windowContent.add(result);
        windowContent.add(fileButton);

        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(windowContent);
        frame.setSize(600,300);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
