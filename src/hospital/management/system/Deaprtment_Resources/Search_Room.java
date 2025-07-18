package hospital.management.system.Deaprtment_Resources;

import hospital.management.system.Utilities.conn;
import net.proteanit.sql.DbUtils;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Search_Room extends JFrame {

    Choice choice;
    JTable table;
    public Search_Room(){
        JPanel panel = new JPanel();
        panel.setBounds(5,5,690,490);
        panel.setBackground(new Color(221, 230, 237));
        panel.setLayout(null);
        add(panel);

        JLabel For = new JLabel("Search For Room");
        For.setBounds(250,11,186,31);
        For.setFont(new Font("Montserrat",Font.BOLD,20));
        panel.add(For);

        JLabel status = new JLabel("Status");
        status.setBounds(70,70,80,20);
        status.setFont(new Font("Montserrat",Font.BOLD,16));
        panel.add(status);

        choice =new Choice();
        choice.setBounds(170,70,120,20);
        choice.add("Available");
        choice.add("Occupied");
        panel.add(choice);

        table = new JTable();
        table.setBounds(10,187,700,210);
        table.setBackground(new Color(221, 230, 237));
        panel.add(table);

        try {
            conn c = new conn();
            String q = "select * from room";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel Roomno = new JLabel("Room Number");
        Roomno.setBounds(23,162,150,20);
        Roomno.setFont(new Font("Montserrat",Font.BOLD,16));
        panel.add(Roomno);

        JLabel available = new JLabel("Availability");
        available.setBounds(195,162,150,20);
        available.setFont(new Font("Montserrat",Font.BOLD,16));
        panel.add(available);

        JLabel price = new JLabel("Price");
        price.setBounds(430,162,150,20);
        price.setFont(new Font("Montserrat",Font.BOLD,16));
        panel.add(price);

        JLabel Bed = new JLabel("Bed Type");
        Bed.setBounds(580,162,150,20);
        Bed.setFont(new Font("Montserrat",Font.BOLD,16));
        panel.add(Bed);

        JButton Search = new JButton("Search");
        Search.setBounds(200,420,120,25);
        Search.setBackground(new Color(39, 55, 77));
        Search.setForeground(Color.white);
        panel.add(Search);
        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String q = "select * from Room where Availability = '"+choice.getSelectedItem()+"'";
                try {
                    conn c = new conn();
                    ResultSet resultSet = c.statement.executeQuery(q);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JButton Back = new JButton("Back");
        Back.setBounds(380,420,120,25);
        Back.setBackground(new Color(39, 55, 77));
        Back.setForeground(Color.white);
        panel.add(Back);
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });


        setUndecorated(true);
        setSize(700,500);
        setLayout(null);
        setLocation(450,250);
        setVisible(true);
    }
    public static void main(String[] args) {

        new Search_Room();
    }
}
