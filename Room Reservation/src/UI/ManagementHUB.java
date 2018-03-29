package UI;

import Oracle.*;
import Object.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManagementHUB {
    OraGuest gm = new OraGuest();;
    OraEmployee em = new OraEmployee();
    OraMakeReservation resm = new OraMakeReservation();
    OraRoom rm = new OraRoom();;
    OraIncludes_Meal mm = new OraIncludes_Meal() ;
    OraParking_Space pm= new OraParking_Space();
    OraVIP vm = new OraVIP();
    OraApprove am = new OraApprove();
    private List<Integer> gList= gm.getAllGuestID();
    private List<Integer> rList= am.getUnApproveReserveNUm();
    private JList guestList;
    private JList ResList ;
    private JPanel tab1;
    private JPanel tab2;
    private JPanel resListPanel;
    private JPanel ReslistPanelInner;
    private JPanel resSearchPanel;
    private EmployeeInfo employee;
    private JButton guestSearchButton;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JLabel nameTxt;
    private JPanel infoButtonPanel;
    private JButton InfoButton;
    private JPanel viewPanel;
    private JTabbedPane tabbedPane1;
    private JLabel rlTitle;
    private JRadioButton reservationNumberRadioButton;
    private JRadioButton guestPhoneNumberRadioButton;
    private JTextField textField1;
    private JPanel filterPanel;
    private JRadioButton reservationRadioButton;
    private JRadioButton guestPhoneRadioButton;
    private JRadioButton guestIDRadioButton;
    private JPanel infoPanel;
    private JLabel checkInLabel;
    private JLabel checkOutLabel;
    private JLabel guestNumberLabel;
    private JLabel roomTypeLabel;
    private JLabel discountLabel;
    private JLabel mealLabel;
    private JLabel roomNumberLabel;
    private JLabel parkingLabel;
    private JLabel nameLabel;
    private JLabel bdayLabel;
    private JLabel phoneLabel;
    private JLabel idLabel;
    private JLabel pointLabel;
    private JLabel cardLabel;
    private JRadioButton approvedRadioButton;
    private JRadioButton notApprovedRadioButton;
    private JButton searchButton;
    private JButton editButton;
    private JButton approveButton;
    private static JFrame frame = new JFrame("ManagementHUB");
    public static void run(Integer id) {

        frame.setContentPane(new ManagementHUB(id).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public ManagementHUB(int a) {



        employee = em.getEmployeeById(a);
        guestList.setListData(gList.toArray());
        ResList.setListData(rList.toArray());

        guestSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        InfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoEmployee.run(employee.getID());
            }
        });
        ResList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Integer resNum = (int)ResList.getSelectedValue();
                RoomInfo room = rm.getRoomWithResrveNum(resNum);
                MakeReservationInfo reservation = resm.getReservationInfoWithReserveNum(resNum);
                roomNumberLabel.setText(room.getRoom_num()+"");
                checkInLabel.setText(reservation.getStart_date().toString());
                checkOutLabel.setText(reservation.getEnd_date().toString());
                guestNumberLabel.setText(reservation.getNumber_of_guest()+"");
                roomTypeLabel.setText(room.getType());
                discountLabel.setText("$"+reservation.getDiscount());
                if(/*resNum is approved*/){
                    approveButton.setEnabled(false);
                }else{
                    approveButton.setEnabled(true);
                }
                if(mm.getMealWithReserveNum(resNum).isEmpty()){
                    mealLabel.setText("Meal Not Included");
                }else{
                    mealLabel.setText(mm.getMealWithReserveNum(resNum).toString());
                }

                if(pm.getParkingInfoWithReserveNum(resNum) == null){
                    parkingLabel.setText("Parking Not Included");
                }else{
                    parkingLabel.setText(pm.getParkingInfoWithReserveNum(resNum).getPlate_num()+" (Stall#"+pm.getParkingInfoWithReserveNum(resNum).getStall_num()+") ");
                }
            }
        });
        guestList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                GuestInfo guest = gm.getGuestById((Integer)guestList.getSelectedValue());
                nameLabel.setText(guest.getName());
                bdayLabel.setText(guest.getBirthday().toString());
                phoneLabel.setText(guest.getPhoneNum()+"");
                cardLabel.setText(guest.getCredit_card_num()+"");
                idLabel.setText(guest.getID()+"");
                if(vm.getVipWithID(guest.getID()) == null){
                    pointLabel.setText("You are not a Member!");
                }else{
                    pointLabel.setText(""+vm.getVipWithID(guest.getID()).getPoints());
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoEditGuest.run((Integer)guestList.getSelectedValue());
                GuestInfo guest = gm.getGuestById((Integer)guestList.getSelectedValue());
                nameLabel.setText(guest.getName());
                bdayLabel.setText(guest.getBirthday().toString());
                phoneLabel.setText(guest.getPhoneNum()+"");
                cardLabel.setText(guest.getCredit_card_num()+"");
                idLabel.setText(guest.getID()+"");
                if(vm.getVipWithID(guest.getID()) == null){
                    pointLabel.setText("You are not a Member!");
                }else{
                    pointLabel.setText(""+vm.getVipWithID(guest.getID()).getPoints());
                }
            }
        });
        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRes = (Integer)ResList.getSelectedValue();
                am.InsertApprove(selectedRes,employee.getID());
                JOptionPane.showMessageDialog(frame, "Employee"+employee.getName()+"("+employee.getID()+") Approved Reservation "+ selectedRes);

                rList.clear();
                rList.addAll(am.getUnApproveReserveNUm());
                ResList.setListData(rList.toArray());
            }
        });
    }


}
