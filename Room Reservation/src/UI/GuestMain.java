package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestMain {
    private JButton button1;
    private JPanel mainPanel;
    private int guestID;
    private int phoneNum;

    public GuestMain(int id,int type) {
        if(type == 0){guestID = id;}
        else{phoneNum = id;}
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
