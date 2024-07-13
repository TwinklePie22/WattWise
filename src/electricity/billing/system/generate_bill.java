package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class generate_bill extends JFrame implements ActionListener {
    Choice searchMonthChoice;
    String meter;
    JTextArea billArea;
    JButton generateBillButton;

    generate_bill(String meter) {
        this.meter = meter;
        setSize(500, 700);
        setLocation(500, 30);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();

        JLabel heading = new JLabel("Generate Bill");
        JLabel meterLabel = new JLabel(meter);

        searchMonthChoice = new Choice();
        addMonthsToChoice(searchMonthChoice);

        billArea = new JTextArea(50, 15);
        billArea.setText("\n \n \t ------------------- Click on the ---------------\n \t ------------------- Generate Bill ---------------");
        billArea.setFont(new Font("Senserif", Font.ITALIC, 15));
        JScrollPane pane = new JScrollPane(billArea);

        generateBillButton = new JButton("Generate Bill");
        generateBillButton.addActionListener(this);

        add(pane);

        panel.add(heading);
        panel.add(meterLabel);
        panel.add(searchMonthChoice);
        add(panel, "North");
        add(generateBillButton, "South");

        setVisible(true);
    }

    private void addMonthsToChoice(Choice choice) {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        for (String month : months) {
            choice.add(month);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            database db = database.getInstance();
            String selectedMonth = searchMonthChoice.getSelectedItem();
            billArea.setText("\n Power Limited \n Electricity Bill For Month of " + selectedMonth + ", 2023\n\n\n");

            // Fetch customer details
            ResultSet resultSet = db.getStatement().executeQuery("select * from new_customer where meter_no ='" + meter + "'");
            if (resultSet.next()) {
                billArea.append("\n    Customer Name        : " + resultSet.getString("name"));
                billArea.append("\n    Customer Meter Number: " + resultSet.getString("meter_no"));
                billArea.append("\n    Customer Address     : " + resultSet.getString("address"));
                billArea.append("\n    Customer City        : " + resultSet.getString("city"));
                billArea.append("\n    Customer State       : " + resultSet.getString("state"));
                billArea.append("\n    Customer Email       : " + resultSet.getString("email"));
                billArea.append("\n    Customer Phone Number: " + resultSet.getString("phone_no"));
            }
            resultSet.close();

            // Fetch meter information
            resultSet = db.getStatement().executeQuery("select * from meter_info where meter_number ='" + meter + "'");
            if (resultSet.next()) {
                billArea.append("\n    Customer Meter Location: " + resultSet.getString("meter_location"));
                billArea.append("\n    Customer Meter Type    : " + resultSet.getString("meter_type"));
                billArea.append("\n    Customer Phase Code    : " + resultSet.getString("phase_code"));
                billArea.append("\n    Customer Bill Type     : " + resultSet.getString("bill_type"));
                billArea.append("\n    Customer Days          : " + resultSet.getString("days"));
            }
            resultSet.close();

            // Fetch tax details
            resultSet = db.getStatement().executeQuery("select * from tax");
            if (resultSet.next()) {
                billArea.append("\n   Cost Per Unit         : " + resultSet.getString("cost_per_unit"));
                billArea.append("\n   Meter Rent            : " + resultSet.getString("meter_rent"));
                billArea.append("\n   Service Charge        : " + resultSet.getString("service_charge"));
                billArea.append("\n   Service Tax           : " + resultSet.getString("service_tax"));
                billArea.append("\n   Swacch Bharat         : " + resultSet.getString("swacch_bharat"));
                billArea.append("\n   Fixed Tax             : " + resultSet.getString("fixed_tax"));
            }
            resultSet.close();

            // Fetch bill details
            resultSet = db.getStatement().executeQuery("select * from bill where meter_no = '" + meter + "' and month = '" + selectedMonth + "'");
            if (resultSet.next()) {
                billArea.append("\n Current Month           : " + resultSet.getString("month"));
                billArea.append("\n Units Consumed          : " + resultSet.getString("unit"));
                billArea.append("\n Total Charges           : " + resultSet.getString("total_bill"));
                billArea.append("\n Total Payable           : " + resultSet.getString("total_bill"));
            }
            resultSet.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new generate_bill("");
    }
}
