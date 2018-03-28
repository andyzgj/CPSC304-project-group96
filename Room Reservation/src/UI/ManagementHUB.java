package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagementHUB {
    private JButton button1;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JLabel nameTxt;
    private JPanel infoButtonPanel;
    private JButton InfoButton;
    private JPanel viewPanel;
    private JTabbedPane tabbedPane1;
    private JLabel rlTitle;
    private JList roomList;
    private JRadioButton reservationNumberRadioButton;
    private JRadioButton guestPhoneNumberRadioButton;
    private JTextField textField1;
    private JPanel filterPanel;
    private JRadioButton reservationRadioButton;
    private JRadioButton guestPhoneRadioButton;
    private JRadioButton guestIDRadioButton;
    private JButton searchButton;
    private static JFrame frame = new JFrame("ManagementHUB");
    public ManagementHUB(int id) {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void run(int id) {
        frame.setContentPane(new ManagementHUB(id).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
