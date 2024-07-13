package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_class extends JFrame implements ActionListener {
    String accountType;
    String meterNumber;

    main_class(String accountType, String meterNumber) {
        this.meterNumber = meterNumber;
        this.accountType = accountType;
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/ebs.png"));
        Image image = imageIcon.getImage().getScaledInstance(1530, 830, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon2);
        add(imageLabel);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Menu");
        menu.setFont(new Font("Serif", Font.PLAIN, 15));

        JMenuItem newCustomer = createMenuItem("New Customer", "icon/newcustomer.png");
        menu.add(newCustomer);

        JMenuItem customerDetails = createMenuItem("Customer Details", "icon/customerDetails.png");
        menu.add(customerDetails);

        JMenuItem depositDetails = createMenuItem("Deposit Details", "icon/depositdetails.png");
        menu.add(depositDetails);

        JMenuItem calculateBill = createMenuItem("Calculate Bill", "icon/calculatorbills.png");
        menu.add(calculateBill);

        JMenu info = new JMenu("Information");
        info.setFont(new Font("Serif", Font.PLAIN, 15));

        JMenuItem updateInfo = createMenuItem("Update Information", "icon/refresh.png");
        info.add(updateInfo);

        JMenuItem viewInfo = createMenuItem("View Information", "icon/information.png");
        info.add(viewInfo);

        JMenu user = new JMenu("User");
        user.setFont(new Font("Serif", Font.PLAIN, 15));

        JMenuItem payBill = createMenuItem("Pay Bill", "icon/pay.png");
        user.add(payBill);

        JMenuItem billDetails = createMenuItem("Bill Details", "icon/detail.png");
        user.add(billDetails);

        JMenu bill = new JMenu("Bill");
        bill.setFont(new Font("Serif", Font.PLAIN, 15));

        JMenuItem generateBill = createMenuItem("Generate Bill", "icon/bill.png");
        bill.add(generateBill);

        JMenu utility = new JMenu("Utility");
        utility.setFont(new Font("Serif", Font.PLAIN, 15));

        JMenuItem notepad = createMenuItem("Notepad", "icon/notepad.png");
        utility.add(notepad);

        JMenuItem calculator = createMenuItem("Calculator", "icon/calculator.png");
        utility.add(calculator);

        JMenu exit = new JMenu("Exit");
        exit.setFont(new Font("Serif", Font.PLAIN, 15));

        JMenuItem exitMenuItem = createMenuItem("Exit", "icon/exit.png");
        exit.add(exitMenuItem);

        if (accountType.equals("Admin")) {
            menuBar.add(menu);
        } else {
            menuBar.add(bill);
            menuBar.add(user);
            menuBar.add(info);
        }

        menuBar.add(utility);
        menuBar.add(exit);

        setLayout(new FlowLayout());
        setVisible(true);
    }

    private JMenuItem createMenuItem(String title, String iconPath) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.setFont(new Font("Monospaced", Font.PLAIN, 14));
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(iconPath));
        Image image = icon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        menuItem.setIcon(new ImageIcon(image));
        menuItem.addActionListener(this);
        return menuItem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "New Customer":
                new newCustomer();
                break;
            case "Customer Details":
                new customer_details();
                break;
            case "Deposit Details":
                new deposit_details();
                break;
            case "Calculate Bill":
                new calculate_bill();
                break;
            case "View Information":
                new view_information(meterNumber);
                break;
            case "Update Information":
                new update_information(meterNumber);
                break;
            case "Bill Details":
                new bill_details(meterNumber);
                break;
            case "Pay Bill":
                new pay_bill(meterNumber);
                break;
            case "Generate Bill":
                new generate_bill(meterNumber);
                break;
            case "Calculator":
                openUtility("calc.exe");
                break;
            case "Notepad":
                openUtility("notepad.exe");
                break;
            case "Exit":
                setVisible(false);
                new Login();
                break;
        }
    }

    private void openUtility(String utility) {
        try {
            Runtime.getRuntime().exec(utility);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new main_class("", "");
    }
}
