package hospital.management.system.Deaprtment_Resources;

import hospital.management.system.Utilities.conn;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Department extends JFrame {

    JPanel panel = new JPanel();

    public Department(){

        JPanel panel = new JPanel();
        panel.setBounds(5,5,840,540);
        panel.setLayout(null);
        panel.setBackground(new Color(221, 230, 237));
        add(panel);

        JTable table = new JTable();
        table.setFont(new Font("Montserrat", Font.PLAIN, 14));
        table.setRowHeight(22);
        table.setShowVerticalLines(false);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Poppins", Font.BOLD, 16));
        header.setBackground(new Color(39, 55, 77));
        header.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 50, 820, 330);
        panel.add(scrollPane);

        try{
            conn c = new conn();
            String q = "select * from department";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch (Exception e){
            e.printStackTrace();
        }


        JButton back = new JButton("Back");
        back.setBounds(400,410,120,30);
        back.setFont(new Font("Poppins",Font.BOLD,15));
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setSize(850,550);
        setLayout(null);
        setLocation(300,250);
        setUndecorated(true);
        setVisible(true);

    }

    public static void main(String[] args) {

        new Department();

    }
}
