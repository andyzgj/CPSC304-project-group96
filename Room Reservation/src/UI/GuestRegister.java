package UI;

import javax.swing.*;
import java.awt.event.*;

public class GuestRegister extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel mainField;
    private JPanel buttonBox;
    private JPanel buttomBox;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField4;
    private JComboBox month;
    private JComboBox date;
    private JComboBox year;
    private JCheckBox registerAsVIPCheckBox;
    private static JFrame frame = new JFrame("GuestRegister");
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
        frame.dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        frame.dispose();
    }
}
