package hospital.management.system.Appointment;

import hospital.management.system.Utilities.conn;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ViewAppointments extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JButton cancelBtn, backBtn;
    private JPanel doctorsPanel;
    private Map<String, Integer> doctorAppointmentCounts = new HashMap<>();

    public ViewAppointments() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(232, 245, 253));
        add(mainPanel);

        // Top Panel for heading
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(232, 245, 253));
        topPanel.setPreferredSize(new Dimension(990, 50));
        JLabel heading = new JLabel("Appointments List");
        heading.setFont(new Font("Poppins", Font.BOLD, 24));
        topPanel.add(heading);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Center Panel with tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Poppins", Font.PLAIN, 14));

        // Tab 1: Doctor Cards View
        doctorsPanel = new JPanel();
        doctorsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        doctorsPanel.setBackground(new Color(232, 245, 253));
        JScrollPane doctorsScrollPane = new JScrollPane(doctorsPanel);
        doctorsScrollPane.setBorder(BorderFactory.createEmptyBorder());
        tabbedPane.addTab("Doctors View", doctorsScrollPane);

        // Tab 2: Table View
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(232, 245, 253));

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

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder());
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(232, 245, 253));
        buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        cancelBtn = new JButton("Cancel Appointment");
        cancelBtn.setPreferredSize(new Dimension(180, 35));
        cancelBtn.setBackground(new Color(39, 55, 77));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFont(new Font("Poppins", Font.PLAIN, 14));
        cancelBtn.setEnabled(false);
        buttonPanel.add(cancelBtn);

        backBtn = new JButton("Back");
        backBtn.setPreferredSize(new Dimension(120, 35));
        backBtn.setBackground(new Color(39, 55, 77));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Poppins", Font.PLAIN, 14));
        buttonPanel.add(backBtn);

        tablePanel.add(buttonPanel, BorderLayout.SOUTH);
        tabbedPane.addTab("List View", tablePanel);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Load appointment data
        loadAppointments();
        createDoctorCards();

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
                    updateDoctorCards();
                    JOptionPane.showMessageDialog(null, "Appointment canceled successfully.");
                }
            }
        });

        backBtn.addActionListener(e -> setVisible(false));

        setSize(1000, 600);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
    }

    private void loadAppointments() {
        try {
            conn c = new conn();
            String query = "SELECT * FROM Appointments";
            ResultSet rs = c.statement.executeQuery(query);

            // Clear existing data
            model.setRowCount(0);
            doctorAppointmentCounts.clear();

            while (rs.next()) {
                String doctorName = rs.getString("doctor_name");
                doctorAppointmentCounts.put(doctorName, doctorAppointmentCounts.getOrDefault(doctorName, 0) + 1);

                model.addRow(new Object[]{
                        rs.getString("patient_id"),
                        rs.getString("patient_name"),
                        doctorName,
                        rs.getString("appointment_date"),
                        rs.getString("appointment_time")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createDoctorCards() {
        doctorsPanel.removeAll();

        for (Map.Entry<String, Integer> entry : doctorAppointmentCounts.entrySet()) {
            String doctorName = entry.getKey();
            int appointmentCount = entry.getValue();

            JPanel card = new JPanel();
            card.setLayout(new BorderLayout());
            card.setPreferredSize(new Dimension(200, 150));
            card.setBackground(new Color(255, 255, 255));
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200)),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));

            // Doctor name label
            JLabel nameLabel = new JLabel(doctorName);
            nameLabel.setFont(new Font("Poppins", Font.BOLD, 16));
            nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            card.add(nameLabel, BorderLayout.NORTH);

            // Appointment count
            JLabel countLabel = new JLabel(appointmentCount + " Appointments");
            countLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
            countLabel.setHorizontalAlignment(SwingConstants.CENTER);
            card.add(countLabel, BorderLayout.CENTER);

            // View button
            JButton viewBtn = new JButton("View Details");
            viewBtn.setFont(new Font("Poppins", Font.PLAIN, 12));
            viewBtn.setBackground(new Color(39, 55, 77));
            viewBtn.setForeground(Color.WHITE);
            viewBtn.addActionListener(e -> showDoctorAppointments(doctorName));
            card.add(viewBtn, BorderLayout.SOUTH);

            doctorsPanel.add(card);
        }

        doctorsPanel.revalidate();
        doctorsPanel.repaint();
    }

    private void updateDoctorCards() {
        loadAppointments(); // Reload data to update counts
        createDoctorCards(); // Recreate cards with updated counts
    }

    private void showDoctorAppointments(String doctorName) {
        DefaultTableModel doctorModel = new DefaultTableModel();
        doctorModel.setColumnIdentifiers(new String[]{
                "Patient ID", "Patient Name", "Date", "Time"
        });

        try {
            conn c = new conn();
            String query = "SELECT * FROM Appointments WHERE doctor_name = '" + doctorName + "'";
            ResultSet rs = c.statement.executeQuery(query);

            while (rs.next()) {
                doctorModel.addRow(new Object[]{
                        rs.getString("patient_id"),
                        rs.getString("patient_name"),
                        rs.getString("appointment_date"),
                        rs.getString("appointment_time")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JTable doctorTable = new JTable(doctorModel);
        doctorTable.setFont(new Font("Montserrat", Font.PLAIN, 14));
        doctorTable.setRowHeight(22);

        JDialog dialog = new JDialog(this, "Appointments for Dr. " + doctorName, true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(this);

        JScrollPane scrollPane = new JScrollPane(doctorTable);
        dialog.add(scrollPane);
        dialog.setVisible(true);
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