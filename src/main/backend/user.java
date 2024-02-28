public class User{
    String username;
    String password;
    String firstName;
    String lastName;
    String id;  // customer ID, this is optional and can be deleted later if it never gets used

    // Constructor
    // Sets the fields when a new user class is made.
    public User(String u, String p, String f, String l, String i){
        username = u;
        password = p;
        firstName = f;
        lastName = l;
        id = i;
    }

    // setUsername
    // Changes the user's username to the given parameter.
    // Ensures the username is unique and (optionally) check for inappropriate names.
    public int setUsername(String user){

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
            char c = pass.charAt(i)

            if (c.isDigit()){  // checks if the digits is a number
                number = true;
                continue;  // continues because the rest of the checks are guaranteed false
            }

            if (Character.toString(c).matches("!@#$%^&*(),.<>?/:;\"\'\\[]{}-_=+")){  // checks for a special character
                specialChar = true;
                continue
            }

            if (Character.isUpperCase(c)){  // checks for uppercase letters
                upper = true;
                continue;
            }

            if (Character.isLowerCase(c)){  // checks for lowercase letters
                lower = true;
            }
        }

        if (number && specialChar && upper && lower){  // if all conditions are met
            password = pass;  // changes the password
            return 0;  // returns success code
        }

        else{  // if a condition was not met
            return 1;  // returns invalid password code
        }
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