package UI;

import Oracle.*;
import Object.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.List;


public class ReservationHistory extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList ResList;
    private JLabel rlTitle;
    private JPanel infoPanel;
    private JLabel checkInLabel;
    private JLabel checkOutLabel;
    private JLabel guestNumberLabel;
    private JLabel roomTypeLabel;
    private JLabel discountLabel;
    private JLabel mealLabel;
    private JLabel roomNumberLabel;
    private JLabel parkingLabel;
    private JPanel mainPanel;
    private static ReservationHistory dialog;
    GuestInfo guest;

    OraGuest gm = new OraGuest();
    OraMakeReservation resm=new OraMakeReservation();
    OraApprove apm = new OraApprove();
    OraRoom rm = new OraRoom();
    OraIncludes_Meal mm = new OraIncludes_Meal();
    OraProvides pm = new OraProvides();
    OraBooked_At bm = new OraBooked_At();

    private List<Integer> resNumList;

    public ReservationHistory(int a ) {
        guest = gm.getGuestById(a);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        //TODO:set reservation list
        




        buttonOK.addActionListener(new ActionListener() {
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
        ResList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //todo show detail on right side correspond to the selection in the list

/*
                checkInLabel.setText();
                checkOutLabel.setText();
                guestNumberLabel.setText();
                roomTypeLabel.setText();

                discountLabel.setText();

                if(){
                    mealLabel.setText();
                }else{
                    mealLabel.setText();
                }

                if(){
                    parkingLabel.setText();
                }else{
                    parkingLabel.setText();
                }
                */
            }
        });
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void run(int id) {
        dialog = new ReservationHistory(id);
        dialog.pack();
        dialog.setVisible(true);

    }

}
