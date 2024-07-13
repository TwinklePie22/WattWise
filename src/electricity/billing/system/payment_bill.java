package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class payment_bill extends JFrame implements ActionListener {
    JButton backButton;
    String meter;

    public payment_bill(String meter) {
        this.meter = meter;
        setTitle("Online Bill Payment");

        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);

        try {
            editorPane.setPage("https://paytm.com/online-payments");
        } catch (Exception e) {
            e.printStackTrace();
            editorPane.setContentType("text/html");
            editorPane.setText("<html>Error loading page. Please try again later.</html>");
        }

        JScrollPane scrollPane = new JScrollPane(editorPane);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            setVisible(false);
            new pay_bill(meter);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new payment_bill(""));
    }
}
