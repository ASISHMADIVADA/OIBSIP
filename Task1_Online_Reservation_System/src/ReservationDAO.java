import java.sql.*;

public class ReservationDAO {

    // LOGIN
    public boolean validateLogin(String username, String password) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

    // BOOK TICKET
    public boolean bookTicket(Reservation reservation) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO reservations VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, reservation.getPnr());
            ps.setString(2, reservation.getPassengerName());
            ps.setString(3, reservation.getTrainNumber());
            ps.setString(4, reservation.getTrainName());
            ps.setString(5, reservation.getClassType());
            ps.setDate(6, Date.valueOf(reservation.getJourneyDate()));
            ps.setString(7, reservation.getSource());
            ps.setString(8, reservation.getDestination());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

    // FETCH BOOKING BY PNR
    public Reservation getReservation(String pnr) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM reservations WHERE pnr=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, pnr);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Reservation reservation = new Reservation();

                reservation.setPnr(rs.getString("pnr"));
                reservation.setPassengerName(rs.getString("passenger_name"));
                reservation.setTrainNumber(rs.getString("train_number"));
                reservation.setTrainName(rs.getString("train_name"));
                reservation.setClassType(rs.getString("class_type"));
                reservation.setJourneyDate(rs.getDate("journey_date").toString());
                reservation.setSource(rs.getString("source_station"));
                reservation.setDestination(rs.getString("destination_station"));

                return reservation;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

    // CANCEL BOOKING
    public boolean cancelReservation(String pnr) {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM reservations WHERE pnr=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, pnr);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

}
