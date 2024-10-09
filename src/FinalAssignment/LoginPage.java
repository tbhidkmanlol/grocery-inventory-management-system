package FinalAssignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static FinalAssignment.LinkToAll.PURPLE;
import static FinalAssignment.LinkToAll.BLUE;
import static FinalAssignment.LinkToAll.RESET;
import static FinalAssignment.LinkToAll.RED;
import static FinalAssignment.LinkToAll.GREEN;


public class LoginPage {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();
        loginPage.authenticateUser();
    }

    public void authenticateUser() {
        System.out.println(BLUE + "  _       ____    _____  _____  _   _    _____          _____  ______ " + RESET);
        System.out.println(BLUE +" | |     / __ \\  / ____||_   _|| \\ | |  |  __ \\  /\\    / ____||  ____|"+ RESET);
        System.out.println(BLUE +" | |    | |  | || |  __   | |  |  \\| |  | |__) |/  \\  | |  __ | |__   "+ RESET);
        System.out.println(BLUE +" | |    | |  | || | |_ |  | |  | . ` |  |  ___// /\\ \\ | | |_ ||  __|  "+ RESET);
        System.out.println(BLUE +" | |____| |__| || |__| | _| |_ | |\\  |  | |   / ____ \\| |__| || |____ "+ RESET);
        System.out.println(BLUE +" |______|\\____/  \\_____||_____||_| \\_|  |_|  /_/    \\_\\\\_____||______|"+ RESET);
        System.out.println("                                                                     ");
        System.out.println("                                                                     ");
    


        
        System.out.println(BLUE+"Please Enter Your User Id:"+ RESET);
        String userId = scanner.nextLine().trim();
        
        System.out.println(BLUE+"\nPlease Enter Your Password:"+RESET);
        String password = scanner.nextLine().trim();
        
        boolean isAuthenticated = checkCredentials(userId, password);
        
        if (isAuthenticated) {
            System.out.println(GREEN + "Login Successful!" + RESET);
            // Navigate to the main menu
            LinkToAll lta = new LinkToAll();
            lta.mainMenu();
        } else {
            System.out.println(RED+"Invalid User Id or Password. Please try again.\n"+RESET);
            authenticateUser();
        }
    }

    private boolean checkCredentials(String userId, String password) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("C:/Users/Lionel Seow/Downloads/sampleUsers.txt"));
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                
                // Check if the ID and password match
                if (data.length >= 3 && data[0].trim().equals(userId) && data[2].trim().equals(password)) {
                    return true; // Credentials match
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false; // No match found
    }
}
