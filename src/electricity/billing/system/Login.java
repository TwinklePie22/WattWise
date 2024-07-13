package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JTextField userText;
    JPasswordField passwordText;
    Choice loginChoice;
    JButton loginButton, cancelButton, signupButton;

    Login() {
        super("Login");
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLayout(null);

        JLabel username = new JLabel("Username");
        username.setBounds(300, 60, 100, 20);
        add(username);

        userText = new JTextField();
        userText.setBounds(400, 60, 150, 20);
        add(userText);

        JLabel password = new JLabel("Password");
        password.setBounds(300, 100, 100, 20);
        add(password);

        passwordText = new JPasswordField();
        passwordText.setBounds(400, 100, 150, 20);
        add(passwordText);

        JLabel loggingInAs = new JLabel("Logging In As");
        loggingInAs.setBounds(300, 140, 100, 20);
        add(loggingInAs);

        loginChoice = new Choice();
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        loginChoice.setBounds(400, 140, 150, 20);
        add(loginChoice);

        loginButton = new JButton("Login");
        loginButton.setBounds(300, 190, 100, 20);
        loginButton.addActionListener(this);
        add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(450, 190, 100, 20);
        cancelButton.addActionListener(this);
        add(cancelButton);

        signupButton = new JButton("Signup");
        signupButton.setBounds(370, 230, 100, 20);
        signupButton.addActionListener(this);
        add(signupButton);

        ImageIcon profileIcon = new ImageIcon(ClassLoader.getSystemResource("icon/profile.png"));
        Image profileImage = profileIcon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon scaledProfileIcon = new ImageIcon(profileImage);
        JLabel profileLabel = new JLabel(scaledProfileIcon);
        profileLabel.setBounds(5, 0, 250, 250);
        add(profileLabel);

        setSize(640, 300);
        setLocation(400, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());
            String userType = loginChoice.getSelectedItem();

            try {
                database db = database.getInstance();
                String query = "SELECT * FROM Signup WHERE username = '" + username + "' AND password = '" + password + "' AND usertype = '" + userType + "'";
                ResultSet resultSet = db.getStatement().executeQuery(query);

                if (resultSet.next()) {
                    String meter = resultSet.getString("meter_no");
                    setVisible(false);
                    new main_class(userType, meter);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                }

                resultSet.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == cancelButton) {
            setVisible(false);
        } else if (e.getSource() == signupButton) {
            setVisible(false);
            new Signup();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
