package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class view_information extends JFrame implements ActionListener {
    JButton cancelButton;

    view_information(String view) {
        setBounds(350, 150, 850, 650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("View Customer Information");
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setFont(new Font("serif", Font.BOLD, 20));
        add(heading, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(70, 80, 100, 20);
        infoPanel.add(nameLabel);

        JLabel nameText = new JLabel();
        nameText.setBounds(200, 80, 150, 20);
        infoPanel.add(nameText);

        JLabel meterNoLabel = new JLabel("Meter Number:");
        meterNoLabel.setBounds(70, 140, 100, 20);
        infoPanel.add(meterNoLabel);

        JLabel meterNoText = new JLabel();
        meterNoText.setBounds(200, 140, 150, 20);
        infoPanel.add(meterNoText);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(70, 200, 100, 20);
        infoPanel.add(addressLabel);

        JLabel addressText = new JLabel();
        addressText.setBounds(200, 200, 150, 20);
        infoPanel.add(addressText);

        JLabel cityLabel = new JLabel("City:");
        cityLabel.setBounds(70, 260, 100, 20);
        infoPanel.add(cityLabel);

        JLabel cityText = new JLabel();
        cityText.setBounds(200, 260, 150, 20);
        infoPanel.add(cityText);

        JLabel stateLabel = new JLabel("State:");
        stateLabel.setBounds(500, 80, 100, 20);
        infoPanel.add(stateLabel);

        JLabel stateText = new JLabel();
        stateText.setBounds(600, 80, 150, 20);
        infoPanel.add(stateText);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(500, 140, 100, 20);
        infoPanel.add(emailLabel);

        JLabel emailText = new JLabel();
        emailText.setBounds(600, 140, 150, 20);
        infoPanel.add(emailText);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(500, 200, 100, 20);
        infoPanel.add(phoneLabel);

        JLabel phoneText = new JLabel();
        phoneText.setBounds(600, 200, 150, 20);
        infoPanel.add(phoneText);

        try {
            database c = new database();
            ResultSet resultSet = c.getStatement().executeQuery("select * from new_customer where meter_no = '" + view + "'");
            if (resultSet.next()) {
                nameText.setText(resultSet.getString("name"));
                meterNoText.setText(resultSet.getString("meter_no"));
                addressText.setText(resultSet.getString("address"));
                cityText.setText(resultSet.getString("city"));
                stateText.setText(resultSet.getString("state"));
                emailText.setText(resultSet.getString("email"));
                phoneText.setText(resultSet.getString("phone_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        add(infoPanel, BorderLayout.CENTER);

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(24, 118, 242));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBounds(350, 520, 150, 30);
        cancelButton.addActionListener(this);
        add(cancelButton, BorderLayout.SOUTH);

        ImageIcon infoIcon = new ImageIcon(ClassLoader.getSystemResource("icon/viewInfo.png"));
        Image infoImage = infoIcon.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(infoImage);
        JLabel imgLabel = new JLabel(scaledIcon);
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imgLabel, BorderLayout.NORTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new view_information("");
    }
}
