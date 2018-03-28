package UI;

import Oracle.*;
import Object.*;
import javax.swing.*;
import java.awt.event.*;

public class ReservationForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel infoPanel;
    private JLabel rmnumtxt;
    private JLabel guestnumtxt;
    private JLabel chkouttxt;
    private JLabel chkintxt;
    private JLabel roomNumberLabel;
    private JComboBox inYear;
    private JComboBox inMonth;
    private JComboBox inDate;
    private JCheckBox mealCheckBox;
    private JComboBox outYear;
    private JComboBox outMonth;
    private JComboBox outDate;
    private JCheckBox parkingCheckBox;
    private JTextField plateField;
    private JLabel priceLable;
    private JLabel roomTypeLabel;
    private JComboBox comboBox1;
    private JCheckBox showOnlyPopularMealCheckBox;
    private JSpinner guestSpinner;
    private static ReservationForm dialog;
    private OraRoom rm = new OraRoom();
    private RoomInfo room;


    public ReservationForm(int roomNum) {
        roomNumberLabel.setText(""+roomNum);
        room = rm.getRoomByRoomNum(roomNum);
        roomTypeLabel.setText(room.getType());
        priceLable.setText("$"+room.getPrice());
        plateField.setEnabled(false);
        comboBox1.setEnabled(false);
        mealCheckBox.setSelected(false);
        showOnlyPopularMealCheckBox.setSelected(false);
        parkingCheckBox.setSelected(false);
        showOnlyPopularMealCheckBox.setEnabled(false);
        comboBox1.setEnabled(true);
        plateField.setEnabled(false);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        showOnlyPopularMealCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        mealCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mealCheckBox.isSelected()){
                    comboBox1.setEnabled(true);
                    showOnlyPopularMealCheckBox.setEnabled(true);
                }else {
                    comboBox1.setEnabled(false);
                    showOnlyPopularMealCheckBox.setEnabled(false);
                }
            }
        });
        parkingCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(parkingCheckBox.isSelected()){
                    plateField.setEnabled(true);
                }else {
                    plateField.setEnabled(false);
                }
            }
        });
    }

    private void onOK() {
        // add your code here
        dialog.dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dialog.dispose();
    }

    public static void run(int rmNum) {
        dialog = new ReservationForm(rmNum);
        dialog.pack();
        dialog.setVisible(true);
    }
}
