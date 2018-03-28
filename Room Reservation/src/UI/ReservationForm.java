package UI;

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
    private JLabel rmtptxt;
    private JLabel roomNumberLabel;
    private JComboBox inYear;
    private JComboBox inMonth;
    private JComboBox inDate;
    private JCheckBox mealCheckBox;
    private JComboBox outYear;
    private JComboBox outMonth;
    private JComboBox outDate;
    private JCheckBox parkingCheckBox;
    private JTextField textField1;
    private JLabel priceLable;
    private JLabel roomTypeLabel;
    private JComboBox comboBox1;
    private JSlider slider1;
    private JLabel guestNumLabel;
    private JCheckBox showOnlyPopularMealCheckBox;
    private static ReservationForm dialog = new ReservationForm();
    private static int roomNum;

    public ReservationForm() {
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
        roomNum = rmNum;
        dialog.pack();
        dialog.setVisible(true);
    }
}
