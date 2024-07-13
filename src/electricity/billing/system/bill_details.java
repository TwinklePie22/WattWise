package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class bill_details extends JFrame {
    String meter;

    bill_details(String meter){
        this.meter = meter;
        setSize(700, 650);
        setLocation(400, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JTable table = new JTable();

        try {
            database c = new database();
            String queryBill = "select * from bill where meter_no = '"+meter+"'";
            ResultSet resultSet = c.getStatement().executeQuery(queryBill);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
            // Close resources
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving data.");
        }

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 0, 700, 650);
        add(sp);

        setVisible(true);
    }

    public static void main(String[] args) {
        new bill_details("");
    }
}
