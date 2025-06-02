package hospital.management.system.Core_Modules ;

import hospital.management.system.Staff.Reception;
import hospital.management.system.Utilities.conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JTextField textField;

    JPasswordField jPasswordField;

    JButton b1,b2;



        public Login(){

            JLabel nameLabel = new JLabel("Username");
            nameLabel.setBounds(40,20,100,30);
            nameLabel.setFont(new Font("Montserrat",Font.BOLD,18));
            add(nameLabel);

            JLabel password = new JLabel("Password");
            password.setBounds(40,70,100, 30);
            password.setFont(new Font("Montserrat",Font.BOLD,18));
            add(password);

            textField = new JTextField();
            textField.setBounds(150,20,150,30);
            textField.setFont(new Font("Montserrat",Font.PLAIN,16));
            textField.setBackground(new Color(157, 178, 191));
            add(textField);

             jPasswordField = new JPasswordField();
             jPasswordField.setBounds(150,70,150,30);
             jPasswordField.setFont(new Font("Montserrat",Font.PLAIN,16));
             jPasswordField.setBackground(new Color(157, 178, 191));
             add(jPasswordField);

             ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
             Image i1 = imageIcon.getImage().getScaledInstance(500,500,Image.SCALE_DEFAULT);
             ImageIcon imageIcon1 = new ImageIcon(i1);
             JLabel label = new JLabel(imageIcon1);
             label.setBounds(300,0,400,300);
             add(label);

             b1 = new JButton("Login");
             b1.setBounds(40,140,120,30);
             b1.setFont(new Font("Poppins",Font.BOLD,15));
             b1.setBackground(new Color(39, 55, 77));
             b1.setForeground(Color.white);
             b1.addActionListener(this);
             add(b1);

             b2 = new JButton("Cancel");
             b2.setBounds(180,140,120,30);
             b2.setFont(new Font("Poppins" , Font.BOLD,15));
             b2.setBackground(new Color(39, 55, 77));
             b2.setForeground(Color.white);
             b2.addActionListener(this);
             add(b2);

            getContentPane().setBackground(new Color(221, 230, 237));
            setTitle("HMS");
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/logo3.jpg")));
            setSize(750,300);
            setLocation(400,270);
            setLayout(null);
            setResizable(false);
            setVisible(true);

            setDefaultCloseOperation(EXIT_ON_CLOSE);

        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                conn c = new conn();

                String user = textField.getText().trim();
                String Pass = new String(jPasswordField.getPassword()).trim();

                PreparedStatement ps = c.connection.prepareStatement("SELECT * FROM login WHERE ID = ? AND PW = ?");
                ps.setString(1, user);
                ps.setString(2, Pass);

                ResultSet resultSet = ps.executeQuery();

                if (resultSet.next()) {
                    new Reception();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.exit(10);
        }
    }


    public static void main (String[]args){
            new Login();

        }
}



