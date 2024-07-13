package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class pay_bill extends JFrame implements ActionListener {

    Choice searchMonthCho;
    String meter;
    JButton pay, back;
    JLabel meterNumberText, nameText, unitText, totalBillText, statusText;

    public pay_bill(String meter) {
        this.meter = meter;
        setSize(900, 600);
        setLocationRelativeTo(null); // Center the frame on screen
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(252, 182, 3));
        add(panel, BorderLayout.CENTER);

        JLabel heading = new JLabel("Pay Bill");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(120, 5, 400, 30);
        panel.add(heading);

        JLabel meterNumber = new JLabel("Meter Number:");
        meterNumber.setBounds(35, 80, 200, 20);
        panel.add(meterNumber);

        meterNumberText = new JLabel("");
        meterNumberText.setBounds(300, 80, 200, 20);
        panel.add(meterNumberText);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(35, 140, 200, 20);
        panel.add(nameLabel);

        nameText = new JLabel("");
        nameText.setBounds(300, 140, 200, 20);
        panel.add(nameText);

        JLabel monthLabel = new JLabel("Month:");
        monthLabel.setBounds(35, 200, 200, 20);
        panel.add(monthLabel);

        searchMonthCho = new Choice();
        searchMonthCho.add("January");
        searchMonthCho.add("February");
        searchMonthCho.add("March");
        searchMonthCho.add("April");
        searchMonthCho.add("May");
        searchMonthCho.add("June");
        searchMonthCho.add("July");
        searchMonthCho.add("August");
        searchMonthCho.add("September");
        searchMonthCho.add("October");
        searchMonthCho.add("November");
        searchMonthCho.add("December");
        searchMonthCho.setBounds(300, 200, 150, 20);
        panel.add(searchMonthCho);

        JLabel unitLabel = new JLabel("Unit:");
        unitLabel.setBounds(35, 260, 200, 20);
        panel.add(unitLabel);

        unitText = new JLabel("");
        unitText.setBounds(300, 260, 200, 20);
        panel.add(unitText);

        JLabel totalBillLabel = new JLabel("Total Bill:");
        totalBillLabel.setBounds(35, 320, 200, 20);
        panel.add(totalBillLabel);

        totalBillText = new JLabel("");
        totalBillText.setBounds(300, 320, 200, 20);
        panel.add(totalBillText);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(35, 380, 200, 20);
        panel.add(statusLabel);

        statusText = new JLabel("");
        statusText.setBounds(300, 380, 200, 20);
        statusText.setForeground(Color.RED);
        panel.add(statusText);

        try {
            database c = new database();
            ResultSet resultSet = c.getStatement().executeQuery("SELECT * FROM new_customer WHERE meter_no = '" + meter + "'");
            if (resultSet.next()) {
                meterNumberText.setText(meter);
                nameText.setText(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchMonthCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                database c = new database();
                try {
                    ResultSet resultSet = c.getStatement().executeQuery("SELECT * FROM bill WHERE meter_no = '" + meter + "' AND month = '" + searchMonthCho.getSelectedItem() + "'");
                    if (resultSet.next()) {
                        unitText.setText(resultSet.getString("unit"));
                        totalBillText.setText(resultSet.getString("total_bill"));
                        statusText.setText(resultSet.getString("status"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        pay = new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setBounds(100, 460, 100, 25);
        pay.addActionListener(this);
        panel.add(pay);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(230, 460, 100, 25);
        back.addActionListener(this);
        panel.add(back);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pay) {
            try {
                database c = new database();
                c.getStatement().executeUpdate("UPDATE bill SET status = 'Paid' WHERE meter_no = '" + meter + "' AND month = '" + searchMonthCho.getSelectedItem() + "'");
                JOptionPane.showMessageDialog(null, "Bill paid successfully");
                setVisible(false);
                new pay_bill(meter);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Failed to pay bill");
            }
        } else if (e.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new pay_bill("");
    }
}
