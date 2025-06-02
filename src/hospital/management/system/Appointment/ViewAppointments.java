package hospital.management.system.Appointment;

import hospital.management.system.Utilities.conn;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ViewAppointments extends JFrame {

    JTable table;
    DefaultTableModel model;
    JButton cancelBtn, backBtn;

    public ViewAppointments() {


        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(232, 245, 253));
        panel.setBounds(5, 5, 990, 540);
        add(panel);

        JLabel heading = new JLabel("Appointments List");
        heading.setFont(new Font("Poppins", Font.BOLD, 24));
        heading.setBounds(400, 10, 300, 30);
        panel.add(heading);

        // Table Setup
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "Patient ID", "Patient Name", "Doctor Name", "Date", "Time"
        });

        table = new JTable(model);
        table.setFont(new Font("Montserrat", Font.PLAIN, 14));
        table.setRowHeight(22);
        table.setShowVerticalLines(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Poppins", Font.BOLD, 14));
        header.setBackground(new Color(39, 55, 77));
        header.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 50, 970, 400);
        panel.add(scrollPane);

        cancelBtn = new JButton("Cancel Appointment");
        cancelBtn.setBounds(350, 460, 180, 35);
        cancelBtn.setBackground(new Color(39, 55, 77));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFont(new Font("Poppins", Font.PLAIN, 14));
        cancelBtn.setEnabled(false);
        panel.add(cancelBtn);


        backBtn = new JButton("Back");
        backBtn.setBounds(560, 460, 120, 35);
        backBtn.setBackground(new Color(39, 55, 77));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Poppins", Font.PLAIN, 14));
        panel.add(backBtn);

        // Load appointment data
        loadAppointments();

        // Enable cancel button only when a row is selected
        table.getSelectionModel().addListSelectionListener(e -> {
            cancelBtn.setEnabled(table.getSelectedRow() != -1);
        });

        // Cancel selected appointment
        cancelBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                String patientId = model.getValueAt(row, 0).toString();
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to cancel Appointment for Patient ID: " + patientId + "?",
                        "Confirm Cancel", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    cancelAppointment(patientId);
                    model.removeRow(row);
                    JOptionPane.showMessageDialog(null, "Appointment canceled successfully.");
                }
            }
        });

        backBtn.addActionListener(e -> setVisible(false));

        setSize(1000, 550);
        setLocation(300,250);
        setUndecorated(true);
        setLayout(null);
        setVisible(true);
    }

    private void loadAppointments() {
        try {
            conn c = new conn();
            String query = "SELECT * FROM Appointments";
            ResultSet rs = c.statement.executeQuery(query);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("patient_id"),
                        rs.getString("patient_name"),
                        rs.getString("doctor_name"),
                        rs.getString("appointment_date"),
                        rs.getString("appointment_time")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cancelAppointment(String patientID) {
        try {
            conn c = new conn();
            String query = "DELETE FROM Appointments WHERE patient_id = '" + patientID + "'";
            c.statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ViewAppointments();
    }
}
