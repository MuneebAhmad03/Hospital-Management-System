package hospital.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;

public class Patient_History extends JFrame {

    Patient_History() {

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 990, 390);
        panel.setBackground(new Color(221, 230, 237));
        panel.setLayout(null);
        add(panel);

        JLabel title = new JLabel("Patient History");
        title.setFont(new Font("Poppins", Font.BOLD, 22));
        title.setBounds(300, 10, 200, 30);
        panel.add(title);

        String[] columns = {"ID", "Number", "Name", "Gender", "Disease", "Room", "Check-In", "Total Amount", "Check-Out"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setFont(new Font("Montserrat", Font.PLAIN, 12));
        table.setRowHeight(22);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 60, 970, 280);
        panel.add(scrollPane);

        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM Patient_History");

            while (rs.next()) {
                String[] data = {
                        rs.getString("ID"),
                        rs.getString("Number"),
                        rs.getString("Name"),
                        rs.getString("Gender"),
                        rs.getString("Patient_Disease"),
                        rs.getString("Room_Number"),
                        rs.getString("Time"),
                        rs.getString("Total_Amount"),
                        rs.getString("Check_Out")
                };
                model.addRow(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton back = new JButton("Back");
        back.setBounds(330, 350, 120, 30);
        back.setFont(new Font("Poppins", Font.BOLD, 15));
        back.setBackground(new Color(39, 55, 77));
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(e -> setVisible(false));


        setSize(1000, 400);
        setUndecorated(true);
        setLayout(null);
        setLocation(400, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Patient_History();
    }
}
