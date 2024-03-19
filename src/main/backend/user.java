package main.backend;
public class user {
    String username;
    String password;
    String firstName;
    String lastName;
    String id;  // customer ID, this is optional and can be deleted later if it never gets used

    // Constructor
    // Sets the fields when a new user class is made.
    public user(String u, String p, String f, String l, String i){
        username = u;
        password = p;
        firstName = f;
        lastName = l;
        id = i;
    }

    // Default Constructor
    public user(){

    }

    // setUsername
    // Changes the user's username to the given parameter.
    // Ensures the username is unique and (optionally) check for inappropriate names.
    public int setUsername(String user){
        return 0;
    }

    // setPassword
    // Changes the user's password to the given parameter.
    // Ensures the password is strong by making it 8 characters long, has a number, a special character,
    // and one uppercase and lowercase letter.
    public int setPassword(String pass){
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
            password = pass;  // changes the password
            return 0;  // returns success code
        }

        return 1;  // if not all conditions are met
    }

    // setFirstName
    // Changes the user's first name to the given parameter.
    public void setFirstName(String first){
        firstName = first;
    }

    // setLastName
    // Changes the user's last name to the given parameter.
    public void setLastName(String last){
        lastName = last;
    }

    // login
    // Logs the user into the system.
    public void login(){

    }

    // logout
    // Logs the user out of the system.
    public void logout(){

    }
}
