package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Boot{

    private JButton guestButton;
    private JButton employeeButton;
    private JPanel mainPanel;
    private JPanel button;
    private JLabel titlebox;
    private JPanel title;
    public static JFrame bFrame = new JFrame("xxx hotel");

    public Boot() {
        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bFrame.dispose();
                GuestLogin.run();
            }
        });
        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bFrame.dispose();
                EmpLogin.run();
            }
        });
    }
    public static void main(String[] args){
        bFrame.setContentPane(new Boot().mainPanel);
        bFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bFrame.pack();
        bFrame.setVisible(true);

    }
}
