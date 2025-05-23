package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Patient_Discharge extends JFrame {
    Patient_Discharge(){


        JPanel panel = new JPanel();
        panel.setBounds(5,5,790,390);
        panel.setBackground(new Color(221, 230, 237));
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("Check-Out");
        label.setBounds(150,20,100,20);
        label.setFont(new Font("Poppins",Font.BOLD,20));
        panel.add(label);

        JLabel label2 = new JLabel("Customer Id");
        label2.setBounds(30,80,100,20);
        label2.setFont(new Font("Montserrat",Font.BOLD,14));
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(200,80,150,25);
        panel.add(choice);

        try {

            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from Patient_INFO");
            while (resultSet.next()){
                choice.add(resultSet.getString("number"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        JLabel label3 = new JLabel("Room No");
        label3.setBounds(30,130,100,20);
        label3.setFont(new Font("Montserrat",Font.BOLD,14));
        panel.add(label3);


        JLabel RoomNo = new JLabel();
        RoomNo.setBounds(200,130,100,20);
        RoomNo.setFont(new Font("Montserrat",Font.BOLD,14));
        panel.add(RoomNo);

        JLabel label4 = new JLabel("Check In");
        label4.setBounds(30,180,100,20);
        label4.setFont(new Font("Montserrat",Font.BOLD,14));
        panel.add(label4);

        JLabel checkIN = new JLabel();
        checkIN.setBounds(200,180,200,20);
        checkIN.setFont(new Font("Montserrat",Font.BOLD,14));
        panel.add(checkIN);

        JLabel label5 = new JLabel("Check Out");
        label5.setBounds(30,230,100,20);
        label5.setFont(new Font("Montserrat",Font.BOLD,14));
        panel.add(label5);

        Date date = new Date();

        JLabel checkOut = new JLabel(""+date);
        checkOut.setBounds(200,230,250,20);
        checkOut.setFont(new Font("Montserrat",Font.BOLD,14));
        panel.add(checkOut);

        JButton discharge = new JButton("Discharge");
        discharge.setBounds(30,300,120,30);
        discharge.setFont(new Font("Poppins",Font.BOLD,15));
        discharge.setBackground(new Color(39, 55, 77));
        discharge.setForeground(Color.WHITE);
        panel.add(discharge);
        discharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn c = new conn();
                try {
                    // check-out date
                    Date date = new Date();
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = sdf.format(date);

                    ResultSet rs = c.statement.executeQuery("SELECT * FROM Patient_info WHERE number = '"+choice.getSelectedItem()+"'");
                    if (rs.next()) {
                        // Insert into Patient_History with properly formatted date
                        String query = "INSERT INTO Patient_History VALUES('" +
                                rs.getString("ID") + "','" +
                                rs.getString("Number") + "','" +
                                rs.getString("Name") + "','" +
                                rs.getString("Gender") + "','" +
                                rs.getString("Patient_Disease") + "','" +
                                rs.getString("Room_Number") + "','" +
                                rs.getString("Time") + "','" +
                                rs.getString("Deposite") + "','" +
                                formattedDate + "')";

                        c.statement.executeUpdate(query);
                    }

                    c.statement.executeUpdate("DELETE FROM Patient_info WHERE number =  '"+choice.getSelectedItem()+"'");
                    c.statement.executeUpdate("UPDATE room SET Availability = 'Available' WHERE Room_Number= '"+RoomNo.getText()+"'");

                    JOptionPane.showMessageDialog(null,"Patient Discharged & Moved to History");
                    setVisible(false);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });


        JButton check = new JButton("Check");
        check.setBounds(170,300,120,30);
        check.setFont(new Font("Poppins",Font.BOLD,15));
        check.setBackground(new Color(39, 55, 77));
        check.setForeground(Color.WHITE);
        panel.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn c = new conn();

                try {
                    ResultSet resultSet = c.statement.executeQuery("select * from Patient_info where number = '"+choice.getSelectedItem()+"'");
                    while (resultSet.next()){
                        RoomNo.setText(resultSet.getString("Room_Number"));
                        checkIN.setText(resultSet.getString("Time"));
                    }

                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(310,300,120,30);
        back.setFont(new Font("Poppins",Font.BOLD,15));
        back.setBackground(new Color(39, 55, 77));
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setSize(800,400);
        setUndecorated(true);
        setLayout(null);
        setLocation(400,250);
        setVisible(true);

    }
    public static void main(String[] args) {
        new Patient_Discharge();
    }
}
