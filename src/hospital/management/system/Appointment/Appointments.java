package hospital.management.system.Appointment;

import hospital.management.system.Utilities.conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Appointments extends JFrame {

    public Appointments() {
        setTitle("Appointments Scheduler");

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(232, 245, 253));
        panel.setBounds(5,5,490,390);

        JLabel heading = new JLabel("Schedule Appointment");
        heading.setFont(new Font("Poppins", Font.BOLD, 20));
        heading.setBounds(170, 20, 300, 30);
        panel.add(heading);

        JLabel patientLabel = new JLabel("Select Patient ID:");
        patientLabel.setBounds(30, 80, 150, 20);
        patientLabel.setFont(new Font("Montserrat",Font.BOLD,16));
        panel.add(patientLabel);

        Choice patientChoice = new Choice();
        patientChoice.setBounds(180, 80, 150, 20);
        patientChoice.setFont(new Font("Montserrat",Font.PLAIN,14));
        panel.add(patientChoice);

        JLabel patientNameLabel = new JLabel("Patient Name:");
        patientNameLabel.setBounds(30, 120, 150, 20);
        patientNameLabel.setFont(new Font("Montserrat",Font.BOLD,16));
        panel.add(patientNameLabel);

        JTextField patientNameField = new JTextField();
        patientNameField.setBounds(180, 120, 150, 20);
        patientNameField.setFont(new Font("Montserrat",Font.PLAIN,14));
        patientNameField.setEditable(false);
        panel.add(patientNameField);

        JLabel doctorLabel = new JLabel("Doctor Name:");
        doctorLabel.setBounds(30, 160, 150, 20);
        doctorLabel.setFont(new Font("Montserrat",Font.BOLD,16));
        panel.add(doctorLabel);

        JTextField doctorField = new JTextField();
        doctorField.setBounds(180, 160, 150, 20);
        doctorField.setFont(new Font("Montserrat",Font.PLAIN,14));
        panel.add(doctorField);

        JLabel dateLabel = new JLabel("Appointment Date:");
        dateLabel.setBounds(30, 200, 150, 20);
        dateLabel.setFont(new Font("Montserrat",Font.BOLD,16));
        panel.add(dateLabel);

        JTextField dateField = new JTextField();
        dateField.setBounds(180, 200, 150, 20);
        dateField.setFont(new Font("Montserrat",Font.PLAIN,14));
        panel.add(dateField);

        JLabel timeLabel = new JLabel("Appointment Time:");
        timeLabel.setBounds(30, 240, 150, 20);
        timeLabel.setFont(new Font("Montserrat",Font.BOLD,16));
        panel.add(timeLabel);

        JTextField timeField = new JTextField();
        timeField.setBounds(180, 240, 150, 20);
        timeField.setFont(new Font("Montserrat",Font.PLAIN,14));
        panel.add(timeField);

        JButton saveBtn = new JButton("Save");
        saveBtn.setBounds(50, 300, 120, 30);
        saveBtn.setBackground(new Color(39, 55, 77));
        saveBtn.setForeground(Color.WHITE);
        panel.add(saveBtn);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(200, 300, 120, 30);
        cancelBtn.setBackground(new Color(39, 55, 77));
        cancelBtn.setForeground(Color.WHITE);
        panel.add(cancelBtn);

        add(panel);
        setSize(500, 400);
        setLocation(550,250);
        setUndecorated(true);
        setLayout(null);
        setVisible(true);

        // Populate patient dropdown
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("SELECT ID FROM Patient_info");
            while (rs.next()) {
                patientChoice.add(rs.getString("ID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set patient name on ID selection
        patientChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String id = patientChoice.getSelectedItem();
                try {
                    conn c = new conn();
                    ResultSet rs = c.statement.executeQuery("SELECT Name FROM Patient_info WHERE ID = '" + id + "'");
                    if (rs.next()) {
                        patientNameField.setText(rs.getString("Name"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Save appointment
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientId = patientChoice.getSelectedItem();
                String patientName = patientNameField.getText();
                String doctorName = doctorField.getText();
                String date = dateField.getText();
                String time = timeField.getText();

                try {
                    conn c = new conn();
                    String query = "INSERT INTO Appointments(patient_id, patient_name, doctor_name, appointment_date, appointment_time) " +
                            "VALUES('" + patientId + "','" + patientName + "','" + doctorName + "','" + date + "','" + time + "')";
                    c.statement.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Appointment Scheduled Successfully!");
                    setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        cancelBtn.addActionListener(e -> setVisible(false));
    }

    public static void main(String[] args) {
        new Appointments();
    }
}
