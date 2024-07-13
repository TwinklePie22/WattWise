package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class meterInfo extends JFrame implements ActionListener {

    Choice meterLocCho, meterTypeCho, phaseCodeCho, billTypeCho;
    JButton submit;
    String meterNumber;

    meterInfo(String meterNumber) {
        this.meterNumber = meterNumber;

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(252, 186, 3));
        add(panel);

        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180, 10, 200, 20);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);

        JLabel meterNumLabel = new JLabel("Meter Number:");
        meterNumLabel.setBounds(50, 80, 100, 20);
        panel.add(meterNumLabel);

        JLabel meterNumberText = new JLabel(meterNumber);
        meterNumberText.setBounds(180, 80, 150, 20);
        panel.add(meterNumberText);

        JLabel meterLoc = new JLabel("Meter Location:");
        meterLoc.setBounds(50, 120, 100, 20);
        panel.add(meterLoc);

        meterLocCho = new Choice();
        meterLocCho.add("Outside");
        meterLocCho.add("Inside");
        meterLocCho.setBounds(180, 120, 150, 20);
        panel.add(meterLocCho);

        JLabel meterType = new JLabel("Meter Type:");
        meterType.setBounds(50, 160, 100, 20);
        panel.add(meterType);

        meterTypeCho = new Choice();
        meterTypeCho.add("Electric Meter");
        meterTypeCho.add("Solar Meter");
        meterTypeCho.add("Smart Meter");
        meterTypeCho.setBounds(180, 160, 150, 20);
        panel.add(meterTypeCho);

        JLabel phaseCode = new JLabel("Phase Code:");
        phaseCode.setBounds(50, 200, 100, 20);
        panel.add(phaseCode);

        phaseCodeCho = new Choice();
        phaseCodeCho.add("011");
        phaseCodeCho.add("022");
        phaseCodeCho.add("033");
        phaseCodeCho.add("044");
        phaseCodeCho.add("055");
        phaseCodeCho.add("066");
        phaseCodeCho.add("077");
        phaseCodeCho.add("088");
        phaseCodeCho.add("099");
        phaseCodeCho.setBounds(180, 200, 150, 20);
        panel.add(phaseCodeCho);

        JLabel billType = new JLabel("Bill Type:");
        billType.setBounds(50, 240, 100, 20);
        panel.add(billType);

        billTypeCho = new Choice();
        billTypeCho.add("Normal");
        billTypeCho.add("Industrial");
        billTypeCho.setBounds(180, 240, 150, 20);
        panel.add(billTypeCho);

        JLabel billingDays = new JLabel("30 Days Billing Time...");
        billingDays.setBounds(50, 280, 150, 20);
        panel.add(billingDays);

        JLabel note = new JLabel("Note:");
        note.setBounds(50, 320, 100, 20);
        panel.add(note);

        JLabel noteDetail = new JLabel("By default, bill is calculated for 30 days only.");
        noteDetail.setBounds(50, 340, 300, 20);
        panel.add(noteDetail);

        submit = new JButton("Submit");
        submit.setBounds(220, 390, 100, 25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        panel.add(submit);

        setSize(700, 500);
        setLocationRelativeTo(null); // Center the frame on screen
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String smeterNum = meterNumber;
            String smeterLoc = meterLocCho.getSelectedItem();
            String smeterTyp = meterTypeCho.getSelectedItem();
            String sphaseCode = phaseCodeCho.getSelectedItem();
            String sbillTyp = billTypeCho.getSelectedItem();
            String sday = "30";

            String query_meterInfo = "INSERT INTO meter_info VALUES ('" + smeterNum + "','" + smeterLoc + "','" + smeterTyp + "','" + sphaseCode + "','" + sbillTyp + "','" + sday + "')";
            try {
                database c = new database(); // Assuming Database is your database class
                c.getStatement().executeUpdate(query_meterInfo);

                JOptionPane.showMessageDialog(null, "Meter Information Submitted Successfully");
                setVisible(false); // Hide the current frame

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Meter Information Submission Failed");
            }
        } else {
            setVisible(false); // Hide the current frame on cancel
        }
    }

    public static void main(String[] args) {
        new meterInfo("");
    }
}
