package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class newCustomer extends JFrame implements ActionListener {

    JLabel heading, customerName, meterNum, address, city, state, email, phone;
    JButton next, cancel;
    JTextField nameText, addressText, cityText, stateText, emailText, phoneText;
    JLabel meterNumberLabel;

    newCustomer() {
        super("New Customer");
        setSize(700, 500);
        setLocationRelativeTo(null); // Center the frame on screen

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(252, 182, 3));
        add(panel);

        heading = new JLabel("New Customer");
        heading.setBounds(180, 10, 200, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);

        customerName = new JLabel("Customer Name:");
        customerName.setBounds(50, 80, 100, 20);
        panel.add(customerName);

        nameText = new JTextField();
        nameText.setBounds(180, 80, 150, 20);
        panel.add(nameText);

        meterNum = new JLabel("Meter Number:");
        meterNum.setBounds(50, 120, 100, 20);
        panel.add(meterNum);

        meterNumberLabel = new JLabel(generateRandomMeterNumber());
        meterNumberLabel.setBounds(180, 120, 150, 20);
        panel.add(meterNumberLabel);

        address = new JLabel("Address:");
        address.setBounds(50, 160, 100, 20);
        panel.add(address);

        addressText = new JTextField();
        addressText.setBounds(180, 160, 150, 20);
        panel.add(addressText);

        city = new JLabel("City:");
        city.setBounds(50, 200, 100, 20);
        panel.add(city);

        cityText = new JTextField();
        cityText.setBounds(180, 200, 150, 20);
        panel.add(cityText);

        state = new JLabel("State:");
        state.setBounds(50, 240, 100, 20);
        panel.add(state);

        stateText = new JTextField();
        stateText.setBounds(180, 240, 150, 20);
        panel.add(stateText);

        email = new JLabel("Email:");
        email.setBounds(50, 280, 100, 20);
        panel.add(email);

        emailText = new JTextField();
        emailText.setBounds(180, 280, 150, 20);
        panel.add(emailText);

        phone = new JLabel("Phone:");
        phone.setBounds(50, 320, 100, 20);
        panel.add(phone);

        phoneText = new JTextField();
        phoneText.setBounds(180, 320, 150, 20);
        panel.add(phoneText);

        next = new JButton("Next");
        next.setBounds(120, 390, 100, 25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        panel.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(230, 390, 100, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/boyy.png"));
        Image image = imageIcon.getImage().getScaledInstance(230, 200, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(scaledIcon);
        add(imageLabel, BorderLayout.WEST);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            String sname = nameText.getText();
            String smeter = meterNumberLabel.getText();
            String saddress = addressText.getText();
            String scity = cityText.getText();
            String sstate = stateText.getText();
            String eemail = emailText.getText();
            String sphon = phoneText.getText();

            String queryCustomer = "INSERT INTO new_customer VALUES('" + sname + "','" + smeter + "','" + saddress + "','" + scity + "','" + sstate + "','" + eemail + "','" + sphon + "')";
            String querySignup = "INSERT INTO Signup VALUES('" + smeter + "','','" + sname + "','','')";

            try {
                database c = new database();
                c.getStatement().executeUpdate(queryCustomer);
                c.getStatement().executeUpdate(querySignup);

                JOptionPane.showMessageDialog(null, "Customer details added successfully");
                setVisible(false);
                new meterInfo(smeter); // Open meter information entry after customer details

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Customer details addition failed");
            }
        } else if (e.getSource() == cancel) {
            setVisible(false); // Close the current window on cancel
        }
    }

    private String generateRandomMeterNumber() {
        Random random = new Random();
        long number = Math.abs(random.nextLong() % 1000000);
        return String.valueOf(number);
    }

    public static void main(String[] args) {
        new newCustomer();
    }
}