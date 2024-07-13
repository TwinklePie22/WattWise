package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Signup extends JFrame implements ActionListener {
    Choice loginASCho;
    TextField meterText, EmployerText, userNameText, nameText;
    JPasswordField passwordText;
    JButton create, back;

    Signup() {
        super("Signup Page");
        getContentPane().setBackground(new Color(168, 203, 255));
        setLayout(null);

        JLabel createAs = new JLabel("Create Account As");
        createAs.setBounds(30, 50, 125, 20);
        add(createAs);

        loginASCho = new Choice();
        loginASCho.add("Admin");
        loginASCho.add("Customer");
        loginASCho.setBounds(170, 50, 120, 20);
        add(loginASCho);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(30, 100, 125, 20);
        meterNo.setVisible(false);
        add(meterNo);

        meterText = new TextField();
        meterText.setBounds(170, 100, 125, 20);
        meterText.setVisible(false);
        add(meterText);

        JLabel employer = new JLabel("Employer ID");
        employer.setBounds(30, 100, 125, 20);
        employer.setVisible(true);
        add(employer);

        EmployerText = new TextField();
        EmployerText.setBounds(170, 100, 125, 20);
        EmployerText.setVisible(true);
        add(EmployerText);

        JLabel userName = new JLabel("Username");
        userName.setBounds(30, 140, 125, 20);
        add(userName);

        userNameText = new TextField();
        userNameText.setBounds(170, 140, 125, 20);
        add(userNameText);

        JLabel name = new JLabel("Name");
        name.setBounds(30, 180, 125, 20);
        add(name);

        nameText = new TextField("");
        nameText.setBounds(170, 180, 125, 20);
        add(nameText);

        meterText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    database c = new database();
                    ResultSet resultSet = c.getStatement().executeQuery("select * from Signup where meter_no = '" + meterText.getText() + "'");
                    if (resultSet.next()) {
                        nameText.setText(resultSet.getString("name"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JLabel password = new JLabel("Password");
        password.setBounds(30, 220, 125, 20);
        add(password);

        passwordText = new JPasswordField();
        passwordText.setBounds(170, 220, 125, 20);
        add(passwordText);

        loginASCho.addItemListener(e -> {
            String user = loginASCho.getSelectedItem();
            if (user.equals("Customer")) {
                employer.setVisible(false);
                nameText.setEditable(false);
                EmployerText.setVisible(false);
                meterNo.setVisible(true);
                meterText.setVisible(true);
            } else {
                employer.setVisible(true);
                EmployerText.setVisible(true);
                meterNo.setVisible(false);
                meterText.setVisible(false);
            }
        });

        create = new JButton("Create");
        create.setBackground(new Color(66, 127, 219));
        create.setForeground(Color.BLACK);
        create.setBounds(50, 285, 100, 25);
        create.addActionListener(this);
        add(create);

        back = new JButton("Back");
        back.setBackground(new Color(66, 127, 219));
        back.setForeground(Color.BLACK);
        back.setBounds(180, 285, 100, 25);
        back.addActionListener(this);
        add(back);

        ImageIcon boyIcon = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image boyImg = boyIcon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon boyIcon2 = new ImageIcon(boyImg);
        JLabel boyLabel = new JLabel(boyIcon2);
        boyLabel.setBounds(330, 30, 250, 250);
        add(boyLabel);

        setSize(600, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            String sLoginAs = loginASCho.getSelectedItem();
            String sUsername = userNameText.getText();
            String sName = nameText.getText();
            String sPassword = new String(passwordText.getPassword());
            String sMeter = meterText.getText();

            try {
                database c = new database();
                String query;
                if (sLoginAs.equals("Admin")) {
                    query = "INSERT INTO Signup VALUES('" + sMeter + "','" + sUsername + "','" + sName + "','" + sPassword + "','" + sLoginAs + "')";
                } else {
                    query = "UPDATE Signup SET username = '" + sUsername + "', password = '" + sPassword + "', usertype = '" + sLoginAs + "' WHERE meter_no = '" + sMeter + "'";
                }

                c.getStatement().executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                setVisible(false);
                new Login();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Signup::new);
    }
}
