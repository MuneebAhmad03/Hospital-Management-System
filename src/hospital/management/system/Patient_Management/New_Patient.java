package hospital.management.system.Patient_Management;

import hospital.management.system.Utilities.conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class New_Patient extends JFrame implements ActionListener {

    JTextField textFieldCNIC, textFieldNumber, textName, textFieldDisease, textFieldDeposit, textAge;
    JRadioButton r1, r2;
    Choice c1, cPatientID, cAge;
    JLabel date;
    JButton b1, b2;
    JCheckBox indoorCheckBox, outdoorCheckBox, emergencyCheckBox;
    private List<Component> navigationOrder;

    public New_Patient() {
        // [All your existing initialization code remains exactly the same...]
        JPanel panel = new JPanel();
        panel.setBounds(5,5,990,540);
        panel.setBackground(new Color(221, 230, 237));
        panel.setLayout(null);
        add(panel);

        JLabel labelName = new JLabel(" NEW PATIENT FORM");
        labelName.setBounds(380,11,260,53);
        labelName.setFont(new Font("Montserrat",Font.BOLD,20));
        panel.add(labelName);

        JLabel labelPD = new JLabel("Personal Details");
        labelPD.setBounds(35,76,200,25);
        labelPD.setFont(new Font("Montserrat",Font.BOLD,20));
        panel.add(labelPD);

        JLabel labelID = new JLabel("Patient ID :");
        labelID.setBounds(35,130,100,20);
        labelID.setFont(new Font("Montserrat",Font.BOLD,16));
        panel.add(labelID);

        cPatientID = new Choice();
        cPatientID.setBounds(200,130,100,20);
        cPatientID.setFont(new Font("Montserrat",Font.PLAIN,14));
        panel.add(cPatientID);
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("SELECT ID FROM patient_Info");
            Set<String> usedIDs = new HashSet<>();
            while (rs.next()) {
                usedIDs.add(rs.getString("ID"));
            }
            for (int i = 1001; i <= 1050; i++) {
                if (!usedIDs.contains(String.valueOf(i))) {
                    cPatientID.add(String.valueOf(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel labelCNIC = new JLabel("CNIC :");
        labelCNIC.setBounds(35,165,50,20);
        labelCNIC.setFont(new Font("Montserrat",Font.BOLD,16));
        panel.add(labelCNIC);

        textFieldCNIC = new JTextField();
        textFieldCNIC.setBounds(200, 165, 150, 20);
        textFieldCNIC.setFont(new Font("Montserrat", Font.PLAIN, 14));
        panel.add(textFieldCNIC);

        JLabel labelNumber = new JLabel("Phone Number :");
        labelNumber.setBounds(35,200,150,20);
        labelNumber.setFont(new Font("Montserrat",Font.BOLD,16));
        panel.add(labelNumber);

        textFieldNumber = new JTextField();
        textFieldNumber.setBounds(200,200,150,20);
        textFieldNumber.setFont(new Font("Montserrat",Font.PLAIN,14));
        panel.add(textFieldNumber);

        JLabel labelName1 = new JLabel("Name :");
        labelName1.setBounds(35,235,150,20);
        labelName1.setFont(new Font("Montserrat",Font.BOLD,16));
        panel.add(labelName1);

        textName = new JTextField();
        textName.setBounds(200,235,150,20);
        textName.setFont(new Font("Montserrat",Font.PLAIN,14));
        panel.add(textName);

        JLabel labelAge = new JLabel("Age :");
        labelAge.setBounds(35,270,150,20);
        labelAge.setFont(new Font("Montserrat",Font.BOLD,16));
        panel.add(labelAge);

        textAge = new JTextField();
        textAge.setBounds(200,270,150,20);
        textAge.setFont(new Font("Montserrat",Font.PLAIN,14));
        panel.add(textAge);

        JLabel labelGender = new JLabel("Gender :");
        labelGender.setBounds(35,305,200,18);
        labelGender.setFont(new Font("Montserrat",Font.BOLD,16));
        panel.add(labelGender);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Montserrat",Font.BOLD,14));
        r1.setBackground(new Color(221, 230, 237));
        r1.setBounds(200,305,80,20);
        panel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Montserrat",Font.BOLD,14));
        r2.setBackground(new Color(221, 230, 237));
        r2.setBounds(280,305,80,20);
        panel.add(r2);

        JLabel labelPT = new JLabel("Patient Type");
        labelPT.setBounds(500,76,200,25);
        labelPT.setFont(new Font("Montserrat",Font.BOLD,20));
        panel.add(labelPT);

        indoorCheckBox = new JCheckBox("Indoor");
        indoorCheckBox.setFont(new Font("Montserrat", Font.BOLD, 14));
        indoorCheckBox.setBackground(new Color(221, 230, 237));
        indoorCheckBox.setBounds(500, 130, 80, 20);
        panel.add(indoorCheckBox);

        outdoorCheckBox = new JCheckBox("Outdoor");
        outdoorCheckBox.setFont(new Font("Montserrat", Font.BOLD, 14));
        outdoorCheckBox.setBackground(new Color(221, 230, 237));
        outdoorCheckBox.setBounds(600, 130, 90, 20);
        panel.add(outdoorCheckBox);

        emergencyCheckBox = new JCheckBox("Emergency");
        emergencyCheckBox.setFont(new Font("Montserrat", Font.BOLD, 14));
        emergencyCheckBox.setBackground(new Color(221, 230, 237));
        emergencyCheckBox.setBounds(700, 130, 120, 20);
        panel.add(emergencyCheckBox);

        JLabel labelDisease = new JLabel("Disease :");
        labelDisease.setBounds(500,165,200,20);
        labelDisease.setFont(new Font("Montserrat",Font.BOLD,16));
        panel.add(labelDisease);

        textFieldDisease = new JTextField();
        textFieldDisease.setBounds(675,165,150,20);
        textFieldDisease.setFont(new Font("Montserrat",Font.PLAIN,14));
        panel.add(textFieldDisease);

        JLabel labelRoom = new JLabel("Room :");
        labelRoom.setBounds(500,200,100,20);
        labelRoom.setFont(new Font("Montserrat",Font.BOLD,16));
        panel.add(labelRoom);

        c1 = new Choice();
        c1.add("None");
        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT Room_Number FROM Room WHERE Availability = 'Available'");
            while (resultSet.next()){
                c1.add(resultSet.getString("Room_Number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(675,200,150,20);
        c1.setFont(new Font("Montserrat",Font.PLAIN,14));
        c1.setBackground(Color.WHITE);
        panel.add(c1);

        JLabel labelDeposit = new JLabel("Deposit Amount :");
        labelDeposit.setBounds(500,235,200,20);
        labelDeposit.setFont(new Font("Montserrat",Font.BOLD,16));
        panel.add(labelDeposit);

        textFieldDeposit = new JTextField();
        textFieldDeposit.setBounds(675,235,150,20);
        textFieldDeposit.setFont(new Font("Montserrat",Font.PLAIN,14));
        panel.add(textFieldDeposit);

        JLabel labelDate = new JLabel("Date and Time:");
        labelDate.setBounds(500, 270, 200, 20);
        labelDate.setFont(new Font("Montserrat", Font.BOLD, 16));
        panel.add(labelDate);

        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
        String formattedDate = sdf.format(currentDate);

        date = new JLabel(formattedDate);
        date.setBounds(675, 270, 200, 20);
        date.setFont(new Font("Montserrat", Font.PLAIN, 14));
        panel.add(date);

        b1 = new JButton("Add");
        b1.setBounds(100,430,120,30);
        b1.setFont(new Font("Poppins",Font.BOLD,15));
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(39, 55, 77));
        b1.addActionListener(this);
        panel.add(b1);

        b2 = new JButton("Back");
        b2.setBounds(260,430,120,30);
        b1.setFont(new Font("Poppins",Font.BOLD,15));
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(39, 55, 77));
        b2.addActionListener(this);
        panel.add(b2);

        // Set up keyboard navigation (NEW CODE STARTS HERE)
        navigationOrder = new ArrayList<>();
        navigationOrder.add(cPatientID);
        navigationOrder.add(textFieldCNIC);
        navigationOrder.add(textFieldNumber);
        navigationOrder.add(textName);
        navigationOrder.add(textAge);
        navigationOrder.add(r1);
        navigationOrder.add(r2);
        navigationOrder.add(indoorCheckBox);
        navigationOrder.add(outdoorCheckBox);
        navigationOrder.add(emergencyCheckBox);
        navigationOrder.add(textFieldDisease);
        navigationOrder.add(c1);
        navigationOrder.add(textFieldDeposit);
        navigationOrder.add(b1);
        navigationOrder.add(b2);

        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) {
                    Component current = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
                    int currentIndex = navigationOrder.indexOf(current);

                    if (currentIndex != -1) {
                        int nextIndex;
                        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                            nextIndex = (currentIndex + 1) % navigationOrder.size();
                        } else {
                            nextIndex = (currentIndex - 1 + navigationOrder.size()) % navigationOrder.size();
                        }

                        Component nextComponent = navigationOrder.get(nextIndex);
                        nextComponent.requestFocus();

                        if (nextComponent instanceof JRadioButton) {
                            ((JRadioButton) nextComponent).setSelected(true);
                        }
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Component current = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
                    if (current == b1) {
                        actionPerformed(new ActionEvent(b1, ActionEvent.ACTION_PERFORMED, null));
                    } else if (current == b2) {
                        actionPerformed(new ActionEvent(b2, ActionEvent.ACTION_PERFORMED, null));
                    } else {
                        int currentIndex = navigationOrder.indexOf(current);
                        if (currentIndex != -1 && currentIndex < navigationOrder.size() - 1) {
                            navigationOrder.get(currentIndex + 1).requestFocus();
                        }
                    }
                }
            }
        };

        for (Component component : navigationOrder) {
            component.addKeyListener(keyAdapter);
        }

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(r1);
        genderGroup.add(r2);

        Set<AWTKeyStroke> forwardKeys = new HashSet<>(r1.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
        r1.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardKeys);
        r2.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardKeys);
        // (NEW CODE ENDS HERE)

        setSize(1000,550);
        setUndecorated(true);
        setLayout(null);
        setLocation(300,250);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // [Your existing actionPerformed method remains exactly the same]
        if (e.getSource() == b1) {
            conn c = new conn();
            String radioBTN = null;
            if (r1.isSelected()) {
                radioBTN = "Male";
            } else if (r2.isSelected()) {
                radioBTN = "Female";
            }
            if (radioBTN == null) {
                JOptionPane.showMessageDialog(null, "Please select gender.");
                return;
            }

            String s1 = cPatientID.getSelectedItem();
            String s1a = textFieldCNIC.getText();
            String s2 = textFieldNumber.getText();
            String s3 = textName.getText();
            String s4 = radioBTN;
            String s4a = textAge.getText();
            String s5 = textFieldDisease.getText();
            String s6 = c1.getSelectedItem();
            String s7 = date.getText();
            String s8 = textFieldDeposit.getText();
            StringBuilder patientTypeBuilder = new StringBuilder();
            String s9 = "";

            try {
                int age = Integer.parseInt(s4a);
                if (age <= 0 || age > 120) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid age (1-120)");
                    return;
                }
            } catch (NumberFormatException E1) {
                JOptionPane.showMessageDialog(null, "Age must be a number");
                return;
            }

            try {
                double deposit = Double.parseDouble(s8);
            } catch (NumberFormatException E2) {
                JOptionPane.showMessageDialog(null, "Deposit must be a number");
                return;
            }

            if (indoorCheckBox.isSelected()) {
                patientTypeBuilder.append("Indoor,");
            }
            if (outdoorCheckBox.isSelected()) {
                patientTypeBuilder.append("Outdoor,");
            }
            if (emergencyCheckBox.isSelected()) {
                patientTypeBuilder.append("Emergency,");
            }

            String patientType = "";
            if (patientTypeBuilder.length() > 0) {
                patientType = patientTypeBuilder.toString();
                patientType = patientType.substring(0, patientType.length() - 1);
            }

            try {
                String q = "insert into Patient_Info values('" + s1 + "','" + s1a + "','" + s2 + "','" + s3 + "','" + s4a + "','" + s4 + "','" + patientType + "','" + s5 + "','" + s6 + "','" + s7 + "','" + s8 + "','" + s9 + "')";
                c.statement.executeUpdate(q);

                if (!s6.equals("None")) {
                    String q1 = "update room set Availability = 'Occupied' where Room_Number = '" + s6 + "'";
                    c.statement.executeUpdate(q1);
                }

                JOptionPane.showMessageDialog(null,"Added Successfully");
                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new New_Patient();
    }
}




