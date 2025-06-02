package hospital.management.system.Staff;

import hospital.management.system.Core_Modules.Login;
import hospital.management.system.Appointment.Appointments;
import hospital.management.system.Appointment.ViewAppointments;
import hospital.management.system.Deaprtment_Resources.Ambulance;
import hospital.management.system.Deaprtment_Resources.Department;
import hospital.management.system.Deaprtment_Resources.Search_Room;
import hospital.management.system.Patient_Management.New_Patient;
import hospital.management.system.Patient_Management.Patient_Discharge;
import hospital.management.system.Patient_Management.Patient_History;
import hospital.management.system.Patient_Management.Update_Patient;
import hospital.management.system.Utilities.conn;
import net.proteanit.sql.DbUtils;


import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Reception extends JFrame {

    public Reception(){

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5,160,1525,670);
        panel.setBackground(new Color(82, 109, 130));
        add(panel);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(5,5,1525,150);
        panel1.setBackground(new Color(221, 230, 237));
        add(panel1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/Hospital.png"));
        Image image = i1.getImage().getScaledInstance(300,180,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(image);
        JLabel label = new JLabel(i2);
        label.setBounds(1100,0,300,180);
        panel1.add(label);

        JButton btn1 = new JButton("Add New Patient");
        btn1.setBounds(30,15,150,30);
        btn1.setBackground(new Color(39 ,55 ,77));
        btn1.setForeground(Color.white);
        btn1.setFont(new Font("Poppins",Font.BOLD,12));
        panel1.add(btn1);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new New_Patient();

                }
            });


        JButton btn2 = new JButton("Update Patient");
        btn2.setBounds(30,58,150,30);
        btn2.setBackground(new Color(39 ,55 ,77));
        btn2.setForeground(Color.white);
        btn2.setFont(new Font("Poppins",Font.BOLD,12));
        panel1.add(btn2);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Update_Patient();
            }
        });


        JButton btn3 = new JButton("Patient Discharge");
        btn3.setBounds(30,100,150,30);
        btn3.setBackground(new Color(39 ,55 ,77));
        btn3.setForeground(Color.white);
        btn3.setFont(new Font("Poppins",Font.BOLD,12));
        panel1.add(btn3);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new Patient_Discharge();
            }
        });


        JButton btn4 = new JButton("Check Room");
        btn4.setBounds(270,15,150,30);
        btn4.setBackground(new Color(39 ,55 ,77));
        btn4.setForeground(Color.white);
        btn4.setFont(new Font("Poppins",Font.BOLD,12));
        panel1.add(btn4);
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Search_Room();

            }
        });


        JButton btn5 = new JButton("Department");
        btn5.setBounds(270,58,150,30);
        btn5.setBackground(new Color(39 ,55 ,77));
        btn5.setForeground(Color.white);
        btn5.setFont(new Font("Poppins",Font.BOLD,12));
        panel1.add(btn5);
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Department();

            }
        });



        JButton btn6 = new JButton("Ambulance");
        btn6.setBounds(270,100,150,30);
        btn6.setBackground(new Color(39 ,55 ,77));
        btn6.setForeground(Color.white);
        btn6.setFont(new Font("Poppins",Font.BOLD,12));
        panel1.add(btn6);
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Ambulance();
            }
        });

        JButton btn7 = new JButton("Employee Info");
        btn7.setBounds(510,15,150,30);
        btn7.setBackground(new Color(39 ,55 ,77));
        btn7.setForeground(Color.white);
        btn7.setFont(new Font("Poppins",Font.BOLD,12));
        panel1.add(btn7);
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Employee_info();

            }
        });

        JButton btn8 = new JButton("Appointments");
        btn8.setBounds(510,58,150,30);
        btn8.setBackground(new Color(39 ,55 ,77));
        btn8.setForeground(Color.white);
        btn8.setFont(new Font("Poppins",Font.BOLD,12));
        panel1.add(btn8);
        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Appointments();
            }
        });



        JButton btn9 = new JButton("Patient History");
        btn9.setBounds(510,100,150,30);
        btn9.setBackground(new Color(39 ,55 ,77));
        btn9.setForeground(Color.white);
        btn9.setFont(new Font("Poppins",Font.BOLD,12));
        panel1.add(btn9);
        btn9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Patient_History();
            }
        });


        JButton btn10 = new JButton("View Appointments");
        btn10.setBounds(750,15,150,30);
        btn10.setBackground(new Color(39 ,55 ,77));
        btn10.setForeground(Color.white);
        btn10.setFont(new Font("Poppins",Font.BOLD,12));
        panel1.add(btn10);
        btn10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new ViewAppointments();

            }
        });

        JButton btn11 = new JButton("Log Out");
        btn11.setBounds(750,58,150,30);
        btn11.setBackground(new Color(39 ,55 ,77));
        btn11.setForeground(Color.white);
        btn11.setFont(new Font("Poppins",Font.BOLD,12));
        panel1.add(btn11);
        btn11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login();

            }
        });


        JLabel labelPD = new JLabel("Admitted Patient");
        labelPD.setBounds(25,20,200,25);
        labelPD.setFont(new Font("Montserrat",Font.BOLD,24));
        labelPD.setForeground(Color.WHITE);
        panel.add(labelPD);

        JTable table = new JTable();
        table.setFont(new Font("Montserrat", Font.PLAIN, 14));
        table.setRowHeight(22);
        table.setBackground(new Color(221, 230, 237));
        table.setShowVerticalLines(false);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Poppins", Font.BOLD, 14));
        header.setBackground(new Color(39, 55, 77));
        header.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 90, 1500, 400);
        panel.add(scrollPane);

        try {

            conn c = new conn();
            String q = "select* from Patient_Info";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch (Exception e){
            e.printStackTrace();
        }






        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/logo3.jpg")));
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE );
    }

    public static void main (String [] args){
        new Reception();

    }
}
