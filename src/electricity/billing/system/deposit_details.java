package electricity.billing.system;

import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class deposit_details extends JFrame implements ActionListener {
    Choice searchMeterCho, searchMonthCho;
    JTable table;
    JButton search, print, close;

    deposit_details() {
        super("Deposit Details");
        getContentPane().setBackground(new Color(192, 186, 245));
        setSize(700, 500);
        setLocation(400, 200);
        setLayout(null);

        JLabel searchMeter = new JLabel("Search By Meter Number");
        searchMeter.setBounds(20, 20, 150, 20);
        add(searchMeter);

        searchMeterCho = new Choice();
        searchMeterCho.setBounds(180, 20, 150, 20);
        add(searchMeterCho);

        populateMeterNumbers();

        JLabel searchMonth = new JLabel("Search By Month");
        searchMonth.setBounds(400, 20, 100, 20);
        add(searchMonth);

        searchMonthCho = new Choice();
        addMonthsToChoice(searchMonthCho);
        searchMonthCho.setBounds(520, 20, 150, 20);
        add(searchMonthCho);

        table = new JTable();
        loadTableData("select * from bill");

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 100, 700, 350);
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane);

        search = new JButton("Search");
        search.setBackground(Color.WHITE);
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBackground(Color.WHITE);
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        close = new JButton("Close");
        close.setBackground(Color.WHITE);
        close.setBounds(600, 70, 80, 20);
        close.addActionListener(this);
        add(close);

        setVisible(true);
    }

    private void populateMeterNumbers() {
        try {
            database db = database.getInstance();
            ResultSet resultSet = db.getStatement().executeQuery("select * from new_customer");
            while (resultSet.next()) {
                searchMeterCho.add(resultSet.getString("meter_no"));
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMonthsToChoice(Choice choice) {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        for (String month : months) {
            choice.add(month);
        }
    }

    private void loadTableData(String query) {
        try {
            database db = database.getInstance();
            ResultSet resultSet = db.getStatement().executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String query_search = "select * from bill where meter_no = '" + searchMeterCho.getSelectedItem() + "' and month = '" + searchMonthCho.getSelectedItem() + "'";
            loadTableData(query_search);
        } else if (e.getSource() == print) {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == close) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new deposit_details();
    }
}
