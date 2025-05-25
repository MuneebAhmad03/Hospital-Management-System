package hospital.management.system;

import javax.swing.*;
import java.awt.*;

public class Admin extends JFrame {

    Admin() {

        getContentPane().setBackground(new Color(221, 230, 237));
        setTitle("HMS");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/logo3.jpg")));
        setSize(750, 300);
        setLocation(400, 270);
        setLayout(null);
        setResizable(false);
        setVisible(true);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
