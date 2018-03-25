package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmpLogin{
    private JPanel topTItle;
    private JPanel buttonField;
    private JButton loginButton;
    private JTextField employeeIDTextField;
    private JPanel mainPanel;

    public EmpLogin() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void run() {
        JFrame frame = new JFrame("EmpLogin");
        frame.setContentPane(new EmpLogin().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
