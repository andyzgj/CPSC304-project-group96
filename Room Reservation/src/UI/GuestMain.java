package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Oracle.OraGuest;
import Object.GuestInfo;


public class GuestMain {

    private JPanel mainPanel;
    private JButton ResButton;
    private JButton InfoButton;
    private JPanel topPanel;
    private JLabel nameTxt;
    private JPanel infoButtonPanel;
    private JPanel viewPanel;
    private JPanel filterPanel;
    private JList roomList;
    private JButton reserveButton;
    private JLabel typeTxt;
    private JLabel priceTxt;
    private JLabel priceField;
    private JLabel typeField;
    private JTextField textField1;
    private JButton applyButton;
    private JPanel infoPanel;
    private JLabel rlTitle;
    private OraGuest gm = new OraGuest();
    private GuestInfo guest;
    private static JFrame frame = new JFrame("GuestMain");

    private GuestMain(long id,int type) {
        if(type == 0){
            guest = gm.getGuestById((int)id);
        }
        else{
            guest = gm.getGuestByPhoneNumber(id);
        }
        nameTxt.setText("Welcome, "+guest.getName()+ " !");
    }

    public static void run(long id,int type) {
        frame.setContentPane(new GuestMain(id,type).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
