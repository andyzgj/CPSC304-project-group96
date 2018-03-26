package UI;

import Oracle.OraGuest;

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

                try{
                    int a = Integer.parseInt(selectLogInTypeTextField.getText());
                    //log in with phone#
                    if(selectID = false){
                        if(gm.isValidPhoneNumber(a)){
                            GuestMain.run(a,1);
                            frame.dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(frame, "Phone Number does not exist!");
                        }
                    }
                        //log in with ID
                    else{
                        if(gm.isValidID(a)){
                            GuestMain.run(a,0);
                            frame.dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(frame, "User ID does not exist!");
                        }
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
    public static JFrame frame;
    private OraGuest gm = new OraGuest();


    public static void run() {
        frame = new JFrame("GuestLogin");
        frame.setContentPane(new GuestLogin().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

