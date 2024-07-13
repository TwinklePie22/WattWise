package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame {
    public Splash() {
        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("icon/Splash.jpg"));
        Image scaledImage = image.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        add(imageLabel);

        setSize(600, 400);
        setLocationRelativeTo(null); // Center the splash screen
        setUndecorated(true); // Remove window decorations (title bar, border)
        setVisible(true);

        // Use a Timer instead of Thread.sleep to delay action
        Timer timer = new Timer(3000, e -> {
            setVisible(false);
            new Login(); // Launch your main application after splash screen
        });
        timer.setRepeats(false); // Only run the timer once
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Splash::new);
    }
}
