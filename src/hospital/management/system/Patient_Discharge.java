package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Patient_Discharge extends JFrame {

    private Choice patientChoice;
    private JLabel labelRoomNo, labelCheckIn, labelCheckOut, labelTotalAmount, labelPatientName;

    public Patient_Discharge() {
        setTitle("Patient Discharge");
        setSize(800, 450);
        setLocation(400, 250);
        setUndecorated(true);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 790, 440);
        panel.setBackground(new Color(221, 230, 237));
        panel.setLayout(null);
        add(panel);

        JLabel heading = new JLabel("Patient Check-Out");
        heading.setBounds(280, 10, 300, 30);
        heading.setFont(new Font("Poppins", Font.BOLD, 22));
        panel.add(heading);

        // Customer ID Choice
        panel.add(createLabel("Patient ID:", 30, 60));
        patientChoice = new Choice();
        patientChoice.setBounds(200, 60, 200, 25);
        panel.add(patientChoice);
        populatePatientIDs();

        // Patient Name
        panel.add(createLabel("Patient Name:", 30, 100));
        labelPatientName = createValueLabel(200, 100);
        panel.add(labelPatientName);

        // Room Number
        panel.add(createLabel("Room Number:", 30, 140));
        labelRoomNo = createValueLabel(200, 140);
        panel.add(labelRoomNo);

        // Check-In Time
        panel.add(createLabel("Check-In Time:", 30, 180));
        labelCheckIn = createValueLabel(200, 180);
        panel.add(labelCheckIn);

        // Total Amount
        panel.add(createLabel("Total Amount:", 30, 220));
        labelTotalAmount = createValueLabel(200, 220);
        panel.add(labelTotalAmount);

        // Check-Out Time
        panel.add(createLabel("Check-Out Time:", 30, 260));
        labelCheckOut = createValueLabel(200, 260);
        updateCheckoutTime();
        panel.add(labelCheckOut);

        // Buttons
        JButton checkButton = createButton("Check", 100, 320);
        panel.add(checkButton);
        checkButton.addActionListener(e -> loadPatientDetails());

        JButton dischargeButton = createButton("Discharge", 250, 320);
        panel.add(dischargeButton);
        dischargeButton.addActionListener(e -> dischargePatient());

        JButton backButton = createButton("Back", 400, 320);
        panel.add(backButton);
        backButton.addActionListener(e -> setVisible(false));

        setVisible(true);
    }

    // Method to create labels
    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Montserrat", Font.BOLD, 14));
        label.setBounds(x, y, 150, 25);
        return label;
    }

    // Method to create dynamic value labels
    private JLabel createValueLabel(int x, int y) {
        JLabel label = new JLabel();
        label.setFont(new Font("Montserrat", Font.PLAIN, 14));
        label.setBounds(x, y, 250, 25);
        return label;
    }

    // Method to create buttons
    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 120, 30);
        button.setBackground(new Color(39, 55, 77));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Poppins", Font.BOLD, 14));
        return button;
    }

    // Populate patient IDs in the choice box
    private void populatePatientIDs() {
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM Patient_Info");
            while (rs.next()) {
                patientChoice.add(rs.getString("ID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load patient details into labels
    private void loadPatientDetails() {
        String id = patientChoice.getSelectedItem();
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM Patient_Info WHERE ID = '" + id + "'");
            if (rs.next()) {
                labelRoomNo.setText(rs.getString("Room_Number"));
                labelCheckIn.setText(rs.getString("Time"));
                labelTotalAmount.setText(rs.getString("Total_Amount"));
                labelPatientName.setText(rs.getString("Name"));
                updateCheckoutTime();
            } else {
                JOptionPane.showMessageDialog(null, "Patient not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update check-out time label
    private void updateCheckoutTime() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
        labelCheckOut.setText(sdf.format(now));
    }

    // Discharge patient and update DB
    private void dischargePatient() {
        String id = patientChoice.getSelectedItem();
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM Patient_Info WHERE ID = '" + id + "'");
            if (rs.next()) {
                String name = rs.getString("Name");
                String gender = rs.getString("Gender");
                String disease = rs.getString("Patient_Disease");
                String room = rs.getString("Room_Number");
                String checkIn = rs.getString("Time");
                String total = rs.getString("Total_Amount");
                String number = rs.getString("Number");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String checkoutFormatted = sdf.format(new Date());

                // Insert into Patient_History
                String insertQuery = "INSERT INTO Patient_History (ID, Number, Name, Gender, Patient_Disease, Room_Number, Time, Total_Amount, check_out) " +
                        "VALUES('" + id + "','" + number + "','" + name + "','" + gender + "','" + disease + "','" + room + "','" + checkIn + "','" + total + "','" + checkoutFormatted + "')";
                c.statement.executeUpdate(insertQuery);

                // Delete from current patient info
                c.statement.executeUpdate("DELETE FROM Patient_Info WHERE ID = '" + id + "'");

                // Update room availability
                c.statement.executeUpdate("UPDATE room SET Availability = 'Available' WHERE Room_Number = '" + room + "'");

                JOptionPane.showMessageDialog(null, "Patient Discharged and moved to history.");
                setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Patient_Discharge();
    }
}

