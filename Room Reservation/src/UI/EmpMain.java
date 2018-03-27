package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmpMain {
    private JButton button1;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JLabel nameTxt;
    private JPanel infoButtonPanel;
    private JButton ResButton;
    private JButton InfoButton;
    private JPanel viewPanel;
    private JPanel filterPanel;
    private JTextField textField1;
    private JButton applyButton;
    private JPanel infoPanel;
    private JLabel priceField;
    private JLabel priceTxt;
    private JLabel typeField;
    private JLabel typeTxt;
    private JButton reserveButton;
    private JList roomList;
    private JLabel rlTitle;
    private static JFrame frame = new JFrame("EmpMain");
    public EmpMain(int id) {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void run(int id) {
        frame.setContentPane(new EmpMain(id).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
