package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class update_information extends JFrame implements ActionListener {
    JLabel nameLabel;
    JTextField addressText, cityText, stateText, emailText, phoneText;
    String meter;
    JButton updateButton, cancelButton;

    update_information(String meter) {
        this.meter = meter;
        setTitle("Update Customer Information");
        getContentPane().setBackground(new Color(229, 255, 227));
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 30, 100, 20);
        formPanel.add(nameLabel);

        JLabel meterLabel = new JLabel("Meter Number:");
        meterLabel.setBounds(30, 70, 100, 20);
        formPanel.add(meterLabel);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(30, 110, 100, 20);
        formPanel.add(addressLabel);

        JLabel cityLabel = new JLabel("City:");
        cityLabel.setBounds(30, 150, 100, 20);
        formPanel.add(cityLabel);

        JLabel stateLabel = new JLabel("State:");
        stateLabel.setBounds(30, 190, 100, 20);
        formPanel.add(stateLabel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(30, 230, 100, 20);
        formPanel.add(emailLabel);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(30, 270, 100, 20);
        formPanel.add(phoneLabel);

        JLabel nametext = new JLabel();
        nametext.setBounds(150, 30, 200, 20);
        formPanel.add(nametext);

        JLabel meterText = new JLabel(meter);
        meterText.setBounds(150, 70, 200, 20);
        formPanel.add(meterText);

        addressText = new JTextField();
        addressText.setBounds(150, 110, 200, 20);
        formPanel.add(addressText);

        cityText = new JTextField();
        cityText.setBounds(150, 150, 200, 20);
        formPanel.add(cityText);

        stateText = new JTextField();
        stateText.setBounds(150, 190, 200, 20);
        formPanel.add(stateText);

        emailText = new JTextField();
        emailText.setBounds(150, 230, 200, 20);
        formPanel.add(emailText);

        phoneText = new JTextField();
        phoneText.setBounds(150, 270, 200, 20);
        formPanel.add(phoneText);

        try {
            database c = new database();
            ResultSet resultSet = c.getStatement().executeQuery("select * from new_customer where meter_no = '" + meter + "'");
            if (resultSet.next()) {
                nametext.setText(resultSet.getString("name"));
                addressText.setText(resultSet.getString("address"));
                cityText.setText(resultSet.getString("city"));
                stateText.setText(resultSet.getString("state"));
                emailText.setText(resultSet.getString("email"));
                phoneText.setText(resultSet.getString("phone_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        updateButton = new JButton("Update");
        updateButton.setBackground(new Color(33, 106, 145));
        updateButton.setForeground(Color.white);
        updateButton.setBounds(50, 320, 120, 25);
        updateButton.addActionListener(this);
        formPanel.add(updateButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(33, 106, 145));
        cancelButton.setForeground(Color.white);
        cancelButton.setBounds(200, 320, 120, 25);
        cancelButton.addActionListener(this);
        formPanel.add(cancelButton);

        add(formPanel, BorderLayout.CENTER);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/update.png"));
        Image image = imageIcon.getImage().getScaledInstance(400, 410, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(image);
        JLabel imgLabel = new JLabel(scaledIcon);
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imgLabel, BorderLayout.EAST);

        setSize(777, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String saddress = addressText.getText();
            String scity = cityText.getText();
            String sstate = stateText.getText();
            String semail = emailText.getText();
            String sphone = phoneText.getText();

            try {
                database c = new database();
                String query = "update new_customer set address ='" + saddress + "', city = '" + scity + "', state = '" + sstate + "', email = '" + semail + "', phone_no ='" + sphone + "' where meter_no = '" + meter + "'";
                c.getStatement().executeUpdate(query);

                JOptionPane.showMessageDialog(null, "User Information Updated Successfully");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating user information: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == cancelButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new update_information("");
    }
}
