package seniorproject.commercebank2.utils;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import java.sql.*;

public class Driver {

    public static void main(String[] args) {

        try{ //get connection to database
            Connection myConn = DriverManager.getConnection("jdbc:mysql://153.91.152.245:3306/seniorproject", "seniorproject", "thisisnotafakepassword");

            //create a statement
            Statement myStmt = myConn.createStatement();

            //execute sql query
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM ")
        }

        catch(Exception exc){
            exc.printStackTrace();
        }
    }

}
