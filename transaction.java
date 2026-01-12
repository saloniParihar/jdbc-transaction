import java.sql.*;

public class transaction {

    public static void main(String args[]) {
        String url = "jdbc:mysql://localhost:3306/student";
        String username = "root";
        String password = "Saloni@123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, username, password);

            //Step 1: setAutocommit off

            con.setAutoCommit(false);
            int amount = 1000;
            int fromAcc = 101;
            int toAcc = 102;

            //Step 2 :Debit from Sender

            String sql = "UPDATE account SET balance = balance-? WHERE acc_no = ?";

            PreparedStatement ps1 = con.prepareStatement(sql);

            ps1.setInt(1,amount);
            ps1.setInt(2, fromAcc);
            ps1.executeUpdate();

            //Step 3 :Credit to receiver

            PreparedStatement ps2 = con.prepareStatement("UPDATE account SET balance = balance+? WHERE acc_no = ?");

            ps2.setInt(1,amount );
            ps2.setInt(2, toAcc);
            ps2.executeUpdate();

            //Step 4 : commit Transaction 
            con.commit();
            System.out.println("Balance Trnsfered Successfully");
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
