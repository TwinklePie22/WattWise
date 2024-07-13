package electricity.billing.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.*;

public class customer_details extends JFrame implements ActionListener {
    Choice searchMeterCho, searchNameCho;
    JTable table;
    JButton search, print, close;

    customer_details() {
        super("Customer Details");
        getContentPane().setBackground(new Color(192, 186, 254));
        setSize(700, 500);
        setLocation(400, 200);
        setLayout(null);

        JLabel searchMeter = new JLabel("Search By Meter Number");
        searchMeter.setBounds(20, 20, 150, 20);
        add(searchMeter);

        searchMeterCho = new Choice();
        searchMeterCho.setBounds(180, 20, 150, 20);
        add(searchMeterCho);

        JLabel searchName = new JLabel("Search By Name");
        searchName.setBounds(400, 20, 100, 20);
        add(searchName);

        searchNameCho = new Choice();
        searchNameCho.setBounds(520, 20, 150, 20);
        add(searchNameCho);

        populateChoices();

        table = new JTable();
        loadTableData("select * from new_customer");

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 100, 700, 350);
        scrollPane.setBackground(Color.white);
        add(scrollPane);

        search = new JButton("Search");
        search.setBackground(Color.white);
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBackground(Color.white);
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        close = new JButton("Close");
        close.setBackground(Color.white);
        close.setBounds(600, 70, 80, 20);
        close.addActionListener(this);
        add(close);

        setVisible(true);
    }

    private void populateChoices() {
        try {
            database c = new database();
            ResultSet resultSet = c.getStatement().executeQuery("select * from new_customer");
            while (resultSet.next()) {
                searchMeterCho.add(resultSet.getString("meter_no"));
                searchNameCho.add(resultSet.getString("name"));
            }
            resultSet.close();
            c.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadTableData(String query) {
        try {
            database c = new database();
            ResultSet resultSet = c.getStatement().executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
            resultSet.close();
            c.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String query_search = "select * from new_customer where meter_no = ? and name = ?";
            try {
                database c = new database();
                PreparedStatement pstmt = c.getConnection().prepareStatement(query_search);
                pstmt.setString(1, searchMeterCho.getSelectedItem());
                pstmt.setString(2, searchNameCho.getSelectedItem());
                ResultSet resultSet = pstmt.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
                resultSet.close();
                pstmt.close();
                c.getConnection().close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == print) {
            try {
                table.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == close) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new calculate_bill();
    }
}
