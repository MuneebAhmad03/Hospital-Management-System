package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Update_Patient extends JFrame {

    // Helper method to safely parse integers
    public int safeParseInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    Update_Patient() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 940, 490);
        panel.setBackground(new Color(221, 230, 237));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
        Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(image));
        label.setBounds(500, 60, 300, 300);
        panel.add(label);

        JLabel titleLabel = new JLabel("Update Patient Details");
        titleLabel.setBounds(124, 11, 260, 25);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        panel.add(titleLabel);

        JLabel labelID = new JLabel("Patient ID:");
        labelID.setBounds(25, 50, 100, 20);
        labelID.setFont(new Font("Montserrat", Font.BOLD, 16));
        panel.add(labelID);

        Choice choiceID = new Choice();
        choiceID.setBounds(248, 50, 150, 20);
        panel.add(choiceID);

        // Load patient IDs
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("select ID from Patient_Info");
            while (rs.next()) {
                choiceID.add(rs.getString("ID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel labelName = new JLabel("Name :");
        labelName.setBounds(25, 85, 100, 20);
        labelName.setFont(new Font("Montserrat", Font.BOLD, 16));
        panel.add(labelName);

        JTextField textFieldName = new JTextField();
        textFieldName.setBounds(248, 85, 150, 20);
        textFieldName.setFont(new Font("Montserrat", Font.PLAIN, 14));
        textFieldName.setEditable(false);
        panel.add(textFieldName);

        JLabel labelAge = new JLabel("Age:");
        labelAge.setBounds(25, 120, 100, 20);
        labelAge.setFont(new Font("Montserrat", Font.BOLD, 16));
        panel.add(labelAge);

        JTextField textFieldAge = new JTextField();
        textFieldAge.setBounds(248, 120, 150, 20);
        textFieldAge.setFont(new Font("Montserrat", Font.PLAIN, 14));
        textFieldAge.setEditable(false);
        panel.add(textFieldAge);

        JLabel labelRoom = new JLabel("Room Number :");
        labelRoom.setBounds(25, 155, 130, 20);
        labelRoom.setFont(new Font("Montserrat", Font.BOLD, 16));
        panel.add(labelRoom);

        JTextField textFieldR = new JTextField();
        textFieldR.setBounds(248, 155, 150, 20);
        textFieldR.setFont(new Font("Montserrat", Font.PLAIN, 14));
        textFieldR.setEditable(false);
        panel.add(textFieldR);

        JLabel labelInTime = new JLabel("Check-In Time:");
        labelInTime.setBounds(25, 190, 140, 20);
        labelInTime.setFont(new Font("Montserrat", Font.BOLD, 16));
        panel.add(labelInTime);

        JTextField textFieldInTime = new JTextField();
        textFieldInTime.setBounds(248, 190, 150, 20);
        textFieldInTime.setEditable(false);
        panel.add(textFieldInTime);

        JLabel labelAmount = new JLabel("Amount Paid (Rs) :");
        labelAmount.setBounds(25, 225, 150, 20);
        labelAmount.setFont(new Font("Montserrat", Font.BOLD, 16));
        panel.add(labelAmount);

        JTextField textFieldAmount = new JTextField();
        textFieldAmount.setBounds(248, 225, 150, 20);
        textFieldAmount.setEditable(false);
        panel.add(textFieldAmount);

        JLabel labelPending = new JLabel("Pending Amount (Rs) :");
        labelPending.setBounds(25, 260, 190, 20);
        labelPending.setFont(new Font("Montserrat", Font.BOLD, 16));
        panel.add(labelPending);

        JTextField textFieldPending = new JTextField();
        textFieldPending.setBounds(248, 260, 150, 20);
        textFieldPending.setFont(new Font("Montserrat", Font.PLAIN, 14));
        panel.add(textFieldPending);

        JLabel labelTotal = new JLabel("Total Amount (Rs) :");
        labelTotal.setBounds(25, 295, 150, 20);
        labelTotal.setFont(new Font("Montserrat", Font.BOLD, 16));
        panel.add(labelTotal);

        JTextField textFieldTotal = new JTextField();
        textFieldTotal.setBounds(248, 295, 150, 20);
        textFieldTotal.setFont(new Font("Montserrat", Font.PLAIN, 14));
        textFieldTotal.setEditable(false);
        panel.add(textFieldTotal);

        JButton check = new JButton("CHECK");
        check.setBounds(168, 378, 89, 23);
        check.setBackground(new Color(39, 55, 77));
        check.setForeground(Color.white);
        panel.add(check);

        check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String patientID = choiceID.getSelectedItem();
                try {
                    conn c = new conn();
                    ResultSet rs = c.statement.executeQuery("select * from Patient_Info where ID = '" + patientID + "'");
                    if (rs.next()) {
                        textFieldName.setText(rs.getString("Name"));
                        textFieldAge.setText(rs.getString("Age"));
                        textFieldR.setText(rs.getString("Room_Number"));
                        textFieldInTime.setText(rs.getString("Time"));
                        textFieldAmount.setText(rs.getString("Deposite"));
                        textFieldPending.setText("");
                        textFieldTotal.setText("");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton update = new JButton("UPDATE");
        update.setBounds(56, 378, 89, 23);
        update.setBackground(new Color(39, 55, 77));
        update.setForeground(Color.white);
        panel.add(update);

        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String patientID = choiceID.getSelectedItem();
                    String room = textFieldR.getText();
                    String time = textFieldInTime.getText();
                    int amountInt = safeParseInt(textFieldAmount.getText());
                    int pendingInt = safeParseInt(textFieldPending.getText());
                    int total = amountInt + pendingInt;
                    textFieldTotal.setText(String.valueOf(total));

                    conn c = new conn();
                    String updateQuery = "update Patient_Info set Room_Number = '" + room + "', Time = '" + time + "', Deposite = '" + amountInt + "', Pending_Amount = '" + pendingInt + "', Total_Amount = '" + total + "' where ID = '" + patientID + "'";
                    c.statement.executeUpdate(updateQuery);

                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                    setVisible(false);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error updating data. Please check inputs.");
                    ex.printStackTrace();
                }
            }
        });

        JButton back = new JButton("BACK");
        back.setBounds(281, 378, 89, 23);
        back.setBackground(new Color(39, 55, 77));
        back.setForeground(Color.white);
        panel.add(back);
        back.addActionListener(e -> setVisible(false));

        setUndecorated(true);
        setSize(950, 500);
        setLayout(null);
        setLocation(400, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Update_Patient();
    }
}


