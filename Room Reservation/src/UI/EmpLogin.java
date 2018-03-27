package UI;

import Oracle.OraEmployee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmpLogin{
    private JPanel topTItle;
    private JPanel buttonField;
    private JButton loginButton;
    private JTextField employeeIDTextField;
    private JPanel mainPanel;
    private static JFrame frame = new JFrame("EmpLogin");
    private OraEmployee em = new OraEmployee();
    public EmpLogin() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int id = Integer.parseInt(employeeIDTextField.getText());
                    if(em.isValidEID(id)){
                        EmpMain.run(id);
                        frame.dispose();
                    }else{
                        JOptionPane.showMessageDialog(frame, "Employee ID does not exist!");
                    }
                }catch(Exception exp){JOptionPane.showMessageDialog(frame, "Input is not VALID!");}

            }
        });
    }

    public static void run() {
        frame.setContentPane(new EmpLogin().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
