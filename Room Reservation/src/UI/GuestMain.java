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
    private JButton searchButton;
    private JPanel infoPanel;
    private JLabel rlTitle;
    private JComboBox typeComboBox;
    private JCheckBox searchRoomWithPriceCheckBox;
    private JCheckBox searchRoomByTypeCheckBox;
    private JRadioButton searchByUsingFilterRadioButton;
    private JRadioButton directSearchByRoomRadioButton;
    private JTextField rmNumField;
    private JButton resetButton;
    private JTextField priceTxtField;
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
        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuestReserveWindow.run();
            }
        });
        ResButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuestReservationView.run();
            }
        });
        InfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuestInfoWindow.run();
            }
        });
        searchByUsingFilterRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchRoomByTypeCheckBox.setEnabled(true);
                searchRoomWithPriceCheckBox.setEnabled(true);
                priceTxtField.setEnabled(true);
                typeComboBox.setEnabled(true);
                rmNumField.setEnabled(false);

            }
        });
        directSearchByRoomRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchRoomByTypeCheckBox.setEnabled(false);
                searchRoomWithPriceCheckBox.setEnabled(false);
                priceTxtField.setEnabled(false);
                typeComboBox.setEnabled(false);
                rmNumField.setEnabled(true);
            }
        });
    }

    public static void run(long id,int type) {
        frame.setContentPane(new GuestMain(id,type).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
