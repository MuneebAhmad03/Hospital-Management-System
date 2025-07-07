package hospital.management.system.Patient_Management;

import hospital.management.system.Utilities.conn;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Patient_History extends JFrame {

    private DefaultTableModel model;
    private JTable table;

    public Patient_History() {

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
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setFont(new Font("Montserrat", Font.PLAIN, 12));
        table.setRowHeight(22);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Poppins", Font.BOLD, 12));
        header.setBackground(new Color(39, 55, 77));
        header.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 100, 970, 240);
        panel.add(scrollPane);

        // Add time period filter buttons
        JPanel filterPanel = new JPanel();
        filterPanel.setBounds(10, 60, 970, 30);
        filterPanel.setBackground(new Color(221, 230, 237));
        filterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panel.add(filterPanel);

        JButton allTimeButton = createFilterButton("All Time");
        JButton last5DaysButton = createFilterButton("Last 5 Days");
        JButton last10DaysButton = createFilterButton("Last 10 Days");
        JButton last15DaysButton = createFilterButton("Last 15 Days");
        JButton last30DaysButton = createFilterButton("Last 30 Days");

        filterPanel.add(allTimeButton);
        filterPanel.add(last5DaysButton);
        filterPanel.add(last10DaysButton);
        filterPanel.add(last15DaysButton);
        filterPanel.add(last30DaysButton);

        // Add total amount label
        JLabel totalAmountLabel = new JLabel("Total Amount: $0.00");
        totalAmountLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        totalAmountLabel.setBounds(10, 350, 300, 30);
        panel.add(totalAmountLabel);

        // Load all data initially
        loadPatientData(null);
        updateTotalAmount();

        allTimeButton.addActionListener(e -> {
            loadPatientData(null);
            updateTotalAmount();
        });

        last5DaysButton.addActionListener(e -> {
            loadPatientData(5);
            updateTotalAmount();
        });

        last10DaysButton.addActionListener(e -> {
            loadPatientData(10);
            updateTotalAmount();
        });

        last15DaysButton.addActionListener(e -> {
            loadPatientData(15);
            updateTotalAmount();
        });

        last30DaysButton.addActionListener(e -> {
            loadPatientData(30);
            updateTotalAmount();
        });

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

    private JButton createFilterButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Poppins", Font.PLAIN, 12));
        button.setBackground(new Color(39, 55, 77));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private void loadPatientData(Integer days) {
        model.setRowCount(0); // Clear existing data

        try {
            conn c = new conn();
            String query = "SELECT * FROM Patient_History";

            if (days != null) {
                LocalDate dateThreshold = LocalDate.now().minusDays(days);
                String formattedDate = dateThreshold.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                query += " WHERE Check_Out >= '" + formattedDate + "'";
            }

            ResultSet rs = c.statement.executeQuery(query);

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
    }

    private void updateTotalAmount() {
        double total = 0.0;
        int amountColumnIndex = 7; // Assuming Total Amount is at index 7

        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                String value = model.getValueAt(i, amountColumnIndex).toString();
                total += Double.parseDouble(value);
            } catch (Exception e) {
                // Handle potential parsing errors
            }
        }

        // Update the total amount label
        Component[] components = this.getContentPane().getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                for (Component panelComp : panel.getComponents()) {
                    if (panelComp instanceof JLabel && ((JLabel) panelComp).getText().startsWith("Total Amount:")) {
                        ((JLabel) panelComp).setText(String.format("Total Amount: %.2f", total));
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Patient_History();
    }
}