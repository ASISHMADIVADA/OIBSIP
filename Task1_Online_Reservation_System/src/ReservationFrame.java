import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReservationFrame extends JFrame implements ActionListener {

    JLabel titleLabel;
    JLabel passengerLabel;
    JLabel trainNoLabel;
    JLabel trainNameLabel;
    JLabel classLabel;
    JLabel dateLabel;
    JLabel sourceLabel;
    JLabel destinationLabel;

    JTextField passengerField;
    JTextField trainNoField;
    JTextField trainNameField;
    JTextField dateField;
    JTextField sourceField;
    JTextField destinationField;

    JComboBox<String> classCombo;

    JButton fetchButton;
    JButton bookButton;
    JButton cancelButton;
    JButton clearButton;

    ReservationDAO dao;

    public ReservationFrame() {

        dao = new ReservationDAO();

        setTitle("Reservation Form");
        setSize(600,550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        titleLabel = new JLabel("TRAIN RESERVATION");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(180,20,300,30);

        passengerLabel = new JLabel("Passenger Name");
        passengerLabel.setBounds(40,80,120,25);

        passengerField = new JTextField();
        passengerField.setBounds(180,80,220,25);

        trainNoLabel = new JLabel("Train Number");
        trainNoLabel.setBounds(40,120,120,25);

        trainNoField = new JTextField();
        trainNoField.setBounds(180,120,120,25);

        fetchButton = new JButton("Fetch");
        fetchButton.setBounds(320,120,80,25);

        trainNameLabel = new JLabel("Train Name");
        trainNameLabel.setBounds(40,160,120,25);

        trainNameField = new JTextField();
        trainNameField.setBounds(180,160,220,25);
        trainNameField.setEditable(false);

        classLabel = new JLabel("Class");
        classLabel.setBounds(40,200,120,25);

        classCombo = new JComboBox<>();
        classCombo.addItem("Sleeper");
        classCombo.addItem("3A");
        classCombo.addItem("2A");
        classCombo.addItem("1A");

        classCombo.setBounds(180,200,150,25);

        dateLabel = new JLabel("Journey Date");
        dateLabel.setBounds(40,240,120,25);

        dateField = new JTextField();
        dateField.setBounds(180,240,220,25);

        sourceLabel = new JLabel("Source");
        sourceLabel.setBounds(40,280,120,25);

        sourceField = new JTextField();
        sourceField.setBounds(180,280,220,25);

        destinationLabel = new JLabel("Destination");
        destinationLabel.setBounds(40,320,120,25);

        destinationField = new JTextField();
        destinationField.setBounds(180,320,220,25);

        bookButton = new JButton("Book Ticket");
        bookButton.setBounds(40,390,140,35);

        cancelButton = new JButton("Cancel Ticket");
        cancelButton.setBounds(210,390,140,35);

        clearButton = new JButton("Clear");
        clearButton.setBounds(380,390,100,35);

        fetchButton.addActionListener(this);
        bookButton.addActionListener(this);
        cancelButton.addActionListener(this);
        clearButton.addActionListener(this);

        add(titleLabel);
        add(passengerLabel);
        add(passengerField);
        add(trainNoLabel);
        add(trainNoField);
        add(fetchButton);
        add(trainNameLabel);
        add(trainNameField);
        add(classLabel);
        add(classCombo);
        add(dateLabel);
        add(dateField);
        add(sourceLabel);
        add(sourceField);
        add(destinationLabel);
        add(destinationField);
        add(bookButton);
        add(cancelButton);
        add(clearButton);

        setVisible(true);
    }
        @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fetchButton) {

            String trainNumber = trainNoField.getText().trim();

            if (trainNumber.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Train Number");
                return;
            }

            if (!trainNumber.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Train Number must be numeric");
                return;
            }

            String trainName = TrainData.getTrainName(trainNumber);

            if (trainName.equals("Train Not Found")) {
                JOptionPane.showMessageDialog(this, "Invalid Train Number");
                trainNameField.setText("");
            } else {
                trainNameField.setText(trainName);
            }
        }

        if (e.getSource() == bookButton) {

            String passenger = passengerField.getText().trim();
            String trainNo = trainNoField.getText().trim();
            String trainName = trainNameField.getText().trim();
            String classType = classCombo.getSelectedItem().toString();
            String date = dateField.getText().trim();
            String source = sourceField.getText().trim();
            String destination = destinationField.getText().trim();

            if (passenger.isEmpty() ||
                    trainNo.isEmpty() ||
                    trainName.isEmpty() ||
                    date.isEmpty() ||
                    source.isEmpty() ||
                    destination.isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Please fill all fields");

                return;
            }

            if (!trainNo.matches("\\d+")) {

                JOptionPane.showMessageDialog(this,
                        "Train Number must be numeric");

                return;
            }

            try {

                java.sql.Date.valueOf(date);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this,
                        "Date must be yyyy-MM-dd");

                return;
            }

            String pnr = PNRGenerator.generatePNR();

            Reservation reservation = new Reservation(
                    pnr,
                    passenger,
                    trainNo,
                    trainName,
                    classType,
                    date,
                    source,
                    destination
            );

            boolean success = dao.bookTicket(reservation);

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Booking Successful\n\n" +
                                "PNR : " + pnr +
                                "\nPassenger : " + passenger +
                                "\nTrain : " + trainName +
                                "\nClass : " + classType +
                                "\nDate : " + date +
                                "\nSource : " + source +
                                "\nDestination : " + destination
                );

            } else {

                JOptionPane.showMessageDialog(this,
                        "Booking Failed");

            }

        }

        if (e.getSource() == cancelButton) {

            dispose();
            new CancellationFrame();

        }

        if (e.getSource() == clearButton) {

            passengerField.setText("");
            trainNoField.setText("");
            trainNameField.setText("");
            dateField.setText("");
            sourceField.setText("");
            destinationField.setText("");
            classCombo.setSelectedIndex(0);

        }

    }

}