package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Oracle.OraGuest;
import Object.GuestInfo;


public class GuestMain {
    private JButton button1;
    private JPanel mainPanel;
    private OraGuest gm = new OraGuest();
    private GuestInfo guest;

    public GuestMain(int id,int type) {
        if(type == 0){
           guest = gm.getGuestById(id);
        }
        else{
            guest = gm.getGuestByPhoneNumber(id);
        }
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public static void run(int id,int type) {
        JFrame frame = new JFrame("GuestMain");
        frame.setContentPane(new GuestMain(id,type).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
