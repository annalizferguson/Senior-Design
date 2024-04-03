package main.backend;
import java.sql.*;

public class User{
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String id;  // customer/employee ID, this is optional and can be deleted later if it never gets used


    // Constructor
    // Sets the fields when a new user class is made.
    public user(String u, String p, String f, String l, String i){
        username = u;
        password = p;
        firstName = f;
        lastName = l;
        id = i;
    }

    // Get Methods
    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getId(){
        return id;
    }

    // Default Constructor
    public user(){

    }

    // setUsername
    // Changes the user's username to the given parameter.
    // Ensures the username is unique and (optionally) check for inappropriate names.
    public int setUsername(String user, String db){
        // Still needs to check if it is unique

        if (user.length() < 4 || user.length() > 12){  // checks if username is in the length bounds [4, 12]
            return 1;
        }

        if(updateDatabase(this.getId(), "username", user, db)){  //updates the database
            username = user;  // sets the username  //sets the current object
        }
        else{  //if there was an error updating the database
            System.out.println("SSN error");
        }


        return 0;  // successful change
    }

    // setPassword
    // Changes the user's password to the given parameter.
    // Ensures the password is strong by making it 8 characters long, has a number, a special character,
    // and one uppercase and lowercase letter.
    public int setPassword(String pass, String db){
        if (pass.length() < 8){  // checks for proper password strength
            return 1;
        }

        boolean number = false;  // if the string has a number
        boolean specialChar = false;  // if the string has a special character
        boolean upper = false;  // if there is an uppercase letter
        boolean lower = false;  //if there is a lowercase letter

        for (int i = 0; i < pass.length(); i++){  // iterates through each letter
            char c = pass.charAt(i);
            System.out.println(i);

            if (Character.isDigit(c)){  // checks if the digits is a number
                number = true;
                System.out.println("number");
                continue;  // continues because the rest of the checks are guaranteed false
            }

            if (Character.toString(c).matches("[^a-z A-Z0-9]")){  // checks for a special character
                specialChar = true;
                System.out.println("spec");
                continue;
            }

            if (Character.isUpperCase(c)){  // checks for uppercase letters
                upper = true;
                System.out.println("up");
                continue;
            }

            if (Character.isLowerCase(c)){  // checks for lowercase letters
                System.out.println("low");
                lower = true;
            }
        }

        if (number && specialChar && upper && lower){  // if all conditions are met
            if(updateDatabase(this.getId(), "password", pass, db)){  //updates the database
                password = pass;  //sets the current object
            }
            else{  //if there was an error updating the database
                System.out.println("Password error");
                return 1; //error with password
            }
            return 0;
        }

        return 1;  // if not all conditions are met
    }

    // setFirstName
    // Changes the user's first name to the given parameter.
    public void setFirstName(String first, String db){
        if(updateDatabase(this.getId(), "firstname", first, db)){  //updates the database
            firstName = first;  //sets the current object
        }
        else{  //if there was an error updating the database
            System.out.println("First name error");
        }
    }

    // setLastName
    // Changes the user's last name to the given parameter.
    public void setLastName(String last, String db){
        if(updateDatabase(this.getId(), "lastname", last, db)){  //updates the database
            lastName = last;  //sets the current object
        }
        else{  //if there was an error updating the database
            System.out.println("Last name error");
        }
    }

    public void setId(String num, String db){
        if(updateDatabase(this.getId(), "customer_id", num, db)){  //updates the database
            id = num;  //sets the current object
        }
        else{  //if there was an error updating the database
            System.out.println("Last name error");
        }
    }

    // login
    // Logs the user into the system. ASSUMES CUSTOMER AT THE MOMENT
    public String login(String user, String pass){
        String url = "jdbc:mysql://localhost:3306/laravel_api";  //database url
        String u = "root";  //database username
        String p = "kd(S(MavJCoLV1";  //database password

        String sql = "select count(*) from customers where username = '" + user + "'";  // sql statement
        boolean userExists = false;  // if the username was found
        boolean passExists = false;  // if the password matches for the username

        try(
                Connection connection = DriverManager.getConnection(url, u, p);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);)
        {
            if(resultSet.next()) {
                int temp = resultSet.getInt(1);  //checks if the username was found
                userExists = temp > 0;  // if the username was found, the boolean gets changed
            }
        }
        catch(SQLException e){
            return "Username DB error.";
        }

        if(userExists){
            sql = "select password from customers where username = '" + user + "'";
            try(Connection connection = DriverManager.getConnection(url, u, p);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);)
            {
                if(resultSet.next()){
                    String temp = resultSet.getString("password");
                    if(temp.equals(pass)){
                        passExists = true;
                    }
                }
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
                return "Password DB error.";
            }
        }

        if(passExists){
            return "Login successful.";
        }

        return "Username or password not found.";
    }

    // logout
    // Logs the user out of the system.
    public void logout(){

    }

    // updateDatabase
    // If the parent class needs to update the database for a subclass.
    // Used when the subclass doesn't have the set methods for the parent field.
    private boolean updateDatabase(String row, String col, String field, String db){
        String url = "jdbc:mysql://localhost:3306/laravel_api";  //database url
        String user = "root";  //database username
        String pass = "kd(S(MavJCoLV1";  //database password

        //the sql statement to send to the database
        String sql = "update " + db + " set " + col + " = '" + field + "' where customer_id = " + row;

        //attempts to connect the driver to the database
        try (
                Connection connection = DriverManager.getConnection(url, user, pass);
                Statement statement = connection.createStatement();
        )
        {
            statement.executeUpdate(sql); //executes the sql statement if the driver connected
            return true;  //returns success
        }

        catch (SQLException e) {  //if the driver could not connect
            return false;  //returns not successful with the connection
        }
    }
}