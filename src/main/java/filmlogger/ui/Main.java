
package filmlogger.ui;

import filmlogger.database.*;
import filmlogger.dao.*;
import filmlogger.domain.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws Exception {        
        // Initialise database.
        Database db = new Database();
        
        // Initialise DAOs.
        UserDAO users = new UserDAO(db);
        FilmDAO films = new FilmDAO(db);
        ReviewDAO reviews = new ReviewDAO(db);
        TagDAO tags = new TagDAO(db);
        
        // Initialise logger.
        Logger logger = new Logger(users, films, reviews, tags);
        
        // Print temporary UI.
        Scanner lukija = new Scanner(System.in);
        
        while (true) {
            System.out.println("");
            System.out.println("* F I L M L O G G E R *");
            System.out.println("Options:");
            System.out.println("1 - Login");
            System.out.println("2 - Register");
            System.out.println("3 - Quit");
            
            System.out.print("> ");
            String command = lukija.nextLine();

            if (command.equals("1")) {
                System.out.println("");
                System.out.println("Login by entering username:");
                
                System.out.print("> ");
                String username = lukija.nextLine();

                if (logger.login(username)) {
                    System.out.println("");
                    System.out.println("Welcome " + logger.getCurrentUser().getName() +  "!");
                        
                    while (true) {
                        System.out.println("");
                        System.out.println("Options:");
                        System.out.println("1 - Log a new film");
                        System.out.println("2 - See all logged films");
                        System.out.println("3 - Logout");
                        System.out.print("> ");
                        command = lukija.nextLine();

                        if (command.equals("1")) {
                            System.out.println("Film: ");
                            System.out.print("> ");
                            String filmName = lukija.nextLine();

                            System.out.println("Year: ");
                            System.out.print("> ");
                            String filmYear = lukija.nextLine();
                            
                            System.out.println("Date watched (dd/mm/yyyy): ");
                            System.out.print("> ");
                            String watchDate = lukija.nextLine();
                            
                            System.out.println("Rating (1-5):");
                            System.out.print("> ");
                            Integer filmRating = Integer.parseInt(lukija.nextLine());
                            
                            System.out.println("Review (max. 1000 characters): ");
                            System.out.print("> ");
                            String filmReview = lukija.nextLine();
                            
                            logger.logFilm(filmName, filmYear, watchDate, filmRating, filmReview);
                        } else if (command.equals("2")) {
                            logger.printLoggedFilms();
                        } else if (command.equals("3")) {
                            break;
                        } else {
                            System.out.println("Oops, that was not an option, try again!");
                        }
                    }
                }

            } else if (command.equals("2")) {
                while (true) {
                    System.out.println("Register by entering username (5-15 characters):");
                    
                    System.out.print("> ");
                    String username = lukija.nextLine();

                    if (logger.createUser(username)) {
                        break;
                    }
                }
            } else if (command.equals("3")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Oops, that was not an option, try again!");
            }
            
        } 
    }  
}
