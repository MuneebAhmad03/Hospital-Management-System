package hospital.management.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame {

    Splash() {

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/splashlogin.png"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH); // stretch to fit
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setBounds(0, 0, 750, 300);
        add(imageLabel);

        setUndecorated(true);
        setSize(750, 300);
        setLocation(400, 270);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/logo3.jpg")));
        getContentPane().setBackground(new Color(221, 230, 237));
        setLayout(null);
        setVisible(true);

        try {
            Thread.sleep(1500);
            dispose();
            new Login(); // Open login screen
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}
