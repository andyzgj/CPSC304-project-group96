package UI;

import Oracle.OraGuest;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;

public class GuestRegister extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel mainField;
    private JPanel buttonBox;
    private JPanel buttomBox;
    private JTextField credit;
    private JTextField phone;
    private JComboBox month;
    private JComboBox date;
    private JComboBox year;
    private JCheckBox registerAsVIPCheckBox;
    private JLabel bday;
    private JLabel cardtxt;
    private JTextField name;
    private JLabel nametxt;
    private static int id = 60000000;
    private static JFrame frame = new JFrame("GuestRegister");
    OraGuest gm = new OraGuest();

    public static void run() {

        frame.setContentPane(new GuestRegister().contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public GuestRegister() {
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
        try{
            int y = 1900 + year.getSelectedIndex();
            int m = 1 + month.getSelectedIndex();
            int d = 1 + date.getSelectedIndex();
            long ph = Long.parseLong(phone.getText());
            long cr = Long.parseLong(credit.getText());
            gm.InsertGuest(id,name.getText(),new Date(y,m,d),ph,cr);  //TODO: wait for PH an CR type in InsertGuest to change to LONG!!!!
            JOptionPane.showMessageDialog(frame, "guest info check - birthday: "+y+" "+m+" "+d+" Name: "+name.getText()+" id: "+id+" phone: "+ph+" Card: "+cr);
            id++;
            frame.dispose();
        }catch(Exception e){
            JOptionPane.showMessageDialog(frame, "INVALID Phone Number or Credit Card Number!");
        }

    }

    private void onCancel() {
        // add your code here if necessary
        frame.dispose();
    }
}
