package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Employee_info extends JFrame {

    Employee_info(){

        JPanel panel = new JPanel();
        panel.setBounds(5,5,990,540);
        panel.setBackground(new Color(221, 230, 237));
        panel.setLayout(null);
        add(panel);

        JTable table = new JTable();
        table.setFont(new Font("Montserrat", Font.PLAIN, 13));
        table.setRowHeight(22);
        table.setShowVerticalLines(false);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Poppins", Font.BOLD, 14));
        header.setBackground(new Color(39, 55, 77));
        header.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 34, 970, 450);
        panel.add(scrollPane);

        try {
            conn c = new conn();
            String q = "select * from emp_info";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));


        }catch (Exception e){
            e.printStackTrace();
        }

        JButton back = new JButton("Back");
        back.setBounds(350,500,120,30);
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

        setSize(1000,550);
        setUndecorated(true);
        setLocation(300,250);
        setLayout(null);
        setVisible(true);

    }

    public static void main(String[] args) {

        new Employee_info();

    }
}
