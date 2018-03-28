package UI;

import Oracle.OraEmployee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginEmployee {
    private JPanel topTItle;
    private JPanel buttonField;
    private JButton loginButton;
    private JTextField employeeIDTextField;
    private JPanel mainPanel;
    private static JFrame frame = new JFrame("LoginEmployee");
    private OraEmployee em = new OraEmployee();
    public LoginEmployee() {

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    int id = Integer.parseInt(employeeIDTextField.getText());
                    //JOptionPane.showMessageDialog(frame, "Employee ID "+id);
                    if(em.isValidEID(id)){
                        ManagementHUB.run(id);
                        frame.dispose();
                    }else{
                        JOptionPane.showMessageDialog(frame, "Employee ID does not exist!");
                    }
                }catch(Exception exp){JOptionPane.showMessageDialog(frame, "Input is INVALID!");}

            }
        });
    }

    public static void run() {
        frame.setContentPane(new LoginEmployee().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}