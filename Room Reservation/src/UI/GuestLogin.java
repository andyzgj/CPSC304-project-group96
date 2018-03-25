package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestLogin extends JFrame{

    public GuestLogin(){

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //go to register window
                // TODO
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //log in with phone#
                if(selectID = false){
                    //call login
                    //TODO
                }
                //log in with ID
                else{
                    //call login
                    //TODO
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
}

