package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Stack;

public class New_Patient extends JFrame implements ActionListener {

    JComboBox comboBox;
    JTextField textFieldNumber,textName,textFieldDisease,textFieldDeposit;
    JRadioButton r1,r2;
    Choice c1;
    JLabel date;
    JButton b1,b2;

    New_Patient(){

        JPanel panel = new JPanel();
        panel.setBounds(5,5,840,540);
        panel.setBackground(new Color(221, 230, 237));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/patient.png"));
        Image image = imageIcon.getImage().getScaledInstance(300,200,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(530,150,300,300);
        panel.add(label);

        JLabel labelName = new JLabel(" NEW PATIENT FORM");
        labelName.setBounds(118,11,260,53);
        labelName.setFont(new Font("Montserrat",Font.BOLD,20));
        panel.add(labelName);

        JLabel labelID = new JLabel("Patient ID :");
        labelID.setBounds(35,76,200,14);
        labelID.setFont(new Font("Montserrat",Font.BOLD,18));
        panel.add(labelID);

        comboBox = new JComboBox(new String[]{"CNIC #","Phone #"});
        comboBox.setBounds(271,73,150,20);
        comboBox.setBackground(Color.white);
        comboBox.setFont(new Font("Montserrat",Font.BOLD,14));
        panel.add(comboBox);

        JLabel labelNumber = new JLabel("Number :");
        labelNumber.setBounds(35,111,200,14);
        labelNumber.setFont(new Font("Montserrat",Font.BOLD,18));
        panel.add(labelNumber);

        textFieldNumber = new JTextField();
        textFieldNumber.setBounds(271,111,150,20);
        panel.add(textFieldNumber);

        JLabel labelName1 = new JLabel("Name :");
        labelName1.setBounds(35,151,200,14);
        labelName1.setFont(new Font("Montserrat",Font.BOLD,18));
        panel.add(labelName1);

        textName = new JTextField();
        textName.setBounds(271,151,150,20);
        panel.add(textName);

        JLabel labelGender = new JLabel("Gender :");
        labelGender.setBounds(35,191,200,14);
        labelGender.setFont(new Font("Montserrat",Font.BOLD,18));
        panel.add(labelGender);

        r1 =new JRadioButton("Male");
        r1.setFont(new Font("Montserrat",Font.BOLD,14));
        r1.setBackground(new Color(221, 230, 237));
        r1.setBounds(271,191,80,18);
        panel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Montserrat",Font.BOLD,14));
        r2.setBackground(new Color(221, 230, 237));
        r2.setBounds(350,191,80,18);
        panel.add(r2);

        JLabel labelDisease = new JLabel("Disease :");
        labelDisease.setBounds(35,231,200,14);
        labelDisease.setFont(new Font("Montserrat",Font.BOLD,18));
        panel.add(labelDisease);

        textFieldDisease = new JTextField();
        textFieldDisease.setBounds(271,231,150,20);
        panel.add(textFieldDisease);

        JLabel labelRoom = new JLabel("Room :");
        labelRoom.setBounds(35,274,200,14);
        labelRoom.setFont(new Font("Montserrat",Font.BOLD,18));
        panel.add(labelRoom);

        c1 = new Choice();

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from Room");
            while (resultSet.next()){
                c1.add(resultSet.getString("Room_Number"));
            }
        }catch ( Exception e){
            e.printStackTrace();
        }

        c1.setBounds(271,274,150,28);
        c1.setFont(new Font("Montserrat",Font.BOLD,14));
        c1.setBackground(Color.WHITE);
        panel.add(c1);

        JLabel labelDeposit = new JLabel("Deposit :");
        labelDeposit.setBounds(35,359,200,17);
        labelDeposit.setFont(new Font("Montserrat",Font.BOLD,18));
        panel.add(labelDeposit);

        textFieldDeposit = new JTextField();
        textFieldDeposit.setBounds(271,359,150,20);
        panel.add(textFieldDeposit);

        JLabel labelDate = new JLabel("Date and Time :");
        labelDate.setBounds(35,316,200,14);
        labelDate.setFont(new Font("Montserrat",Font.BOLD,18));
        panel.add(labelDate);

        Date date1 = new Date();

        date = new JLabel(""+date1);
        date.setBounds(271,316,250,16);
        date.setFont(new Font("Montserrat",Font.BOLD,14));
        panel.add(date);

        b1 = new JButton("Add");
        b1.setBounds(100,430,120,30);
        b1.setFont(new Font("Poppins",Font.BOLD,15));
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(39, 55, 77));
        b1.addActionListener(this);
        panel.add(b1);

        b2 = new JButton("Back");
        b2.setBounds(260,430,120,30);
        b1.setFont(new Font("Poppins",Font.BOLD,15));
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(39, 55, 77));
        b2.addActionListener(this);
        panel.add(b2);

        setSize(850,550);
        setUndecorated(true);
        setLayout(null);
        setLocation(300,250);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1){
            conn c = new conn();
            String radioBTN = null;
            if (r1.isSelected()){

                radioBTN = "Male";
            } else if (r2.isSelected()){
                radioBTN = "Female";
            }

            String s1 = (String) comboBox.getSelectedItem();
            String s2 = textFieldNumber.getText();
            String s3 = textName.getText();
            String s4 = radioBTN;
            String s5 = textFieldDisease.getText();
            String s6 = c1.getSelectedItem();
            String s7 = date.getText();
            String s8 = textFieldDeposit.getText();

            try {

                String q = "insert into Patient_Info values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"')";
                String q1 = "update room set Availability = 'Occupided' where Room_Number = " +s6;
                c.statement.executeUpdate(q);
                c.statement.executeUpdate(q1);

                JOptionPane.showMessageDialog(null,"Added Successfully");
                setVisible(false);

            }catch (Exception E){
                E.printStackTrace();
            }

        }else {
            setVisible(false);
        }
    }

    public static void main (String[]args){
        new New_Patient();
    }

}
