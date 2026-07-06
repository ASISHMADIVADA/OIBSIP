import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CancellationFrame extends JFrame implements ActionListener {

    JLabel titleLabel;
    JLabel pnrLabel;

    JTextField pnrField;

    JTextArea detailsArea;

    JButton fetchButton;
    JButton cancelButton;
    JButton backButton;

    ReservationDAO dao;
    Reservation reservation;

    public CancellationFrame() {

        dao = new ReservationDAO();

        setTitle("Cancellation");
        setSize(600,500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("TICKET CANCELLATION");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(150,20,300,30);

        pnrLabel = new JLabel("PNR Number");
        pnrLabel.setBounds(40,80,100,25);

        pnrField = new JTextField();
        pnrField.setBounds(150,80,180,25);

        fetchButton = new JButton("Fetch");
        fetchButton.setBounds(360,80,100,25);

        detailsArea = new JTextArea();
        detailsArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setBounds(40,130,500,220);

        cancelButton = new JButton("Confirm Cancellation");
        cancelButton.setBounds(70,390,190,35);

        backButton = new JButton("Back");
        backButton.setBounds(330,390,120,35);

        fetchButton.addActionListener(this);
        cancelButton.addActionListener(this);
        backButton.addActionListener(this);

        add(titleLabel);
        add(pnrLabel);
        add(pnrField);
        add(fetchButton);
        add(scrollPane);
        add(cancelButton);
        add(backButton);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fetchButton) {

            String pnr = pnrField.getText().trim();

            if (pnr.isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Enter PNR Number");

                return;

            }

            reservation = dao.getReservation(pnr);

            if (reservation == null) {

                JOptionPane.showMessageDialog(this,
                        "No Booking Found");

                detailsArea.setText("");

            } else {

                detailsArea.setText(
                        "PNR : " + reservation.getPnr()
                                + "\nPassenger : " + reservation.getPassengerName()
                                + "\nTrain Number : " + reservation.getTrainNumber()
                                + "\nTrain Name : " + reservation.getTrainName()
                                + "\nClass : " + reservation.getClassType()
                                + "\nJourney Date : " + reservation.getJourneyDate()
                                + "\nSource : " + reservation.getSource()
                                + "\nDestination : " + reservation.getDestination()
                );

            }

        }

        if (e.getSource() == cancelButton) {

            if (reservation == null) {

                JOptionPane.showMessageDialog(this,
                        "Fetch Reservation First");

                return;

            }

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to cancel this booking?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
            );

            if (option == JOptionPane.YES_OPTION) {

                boolean success = dao.cancelReservation(reservation.getPnr());

                if (success) {

                    JOptionPane.showMessageDialog(this,
                            "Ticket Cancelled Successfully");

                    detailsArea.setText("");
                    pnrField.setText("");

                    reservation = null;

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Cancellation Failed");

                }

            }

        }

        if (e.getSource() == backButton) {

            dispose();

            new ReservationFrame();

        }

    }

}