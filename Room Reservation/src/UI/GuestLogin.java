package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestLogin{

    public GuestLogin(){

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //go to register window
                // TODO
                GuestRegister.run();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //log in with phone#
                try{
                    int a = Integer.parseInt(selectLogInTypeTextField.getText());
                    if(true/*valid*/){
                        if(selectID = false){
                            GuestMain.run(a,1);
                            frame.dispose();
                        }
                        //log in with ID
                        else{
                            GuestMain.run(a,0);
                            frame.dispose();
                        }
                    }else{
                        JOptionPane.showMessageDialog(frame, "User does not exist!");
                    }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(frame, "Please provide a valid number!");
                }
            }
        });
        phoneNumberRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectID = false;
            }
        });
        guestIDRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectID = true;
            }
        });
    }
    private boolean selectID = false;
    private JPanel mainPanel;
    private JRadioButton phoneNumberRadioButton;
    private JButton registerButton;
    private JPanel topTItle;
    private JPanel buttonField;
    private JButton loginButton;
    private JPanel buttonPanel;
    private JPanel inputPanel;
    private JPanel choicePanel;
    private JPanel textPanel;
    private JRadioButton guestIDRadioButton;
    private JTextField selectLogInTypeTextField;
    private JPanel buttom;
    public static JFrame frame = new JFrame("GuestLogin");
    public static void run() {

        frame.setContentPane(new GuestLogin().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

