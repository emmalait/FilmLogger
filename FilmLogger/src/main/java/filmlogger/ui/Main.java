
package filmlogger.ui;

import filmlogger.database.*;
import filmlogger.dao.*;
import filmlogger.domain.*;
import java.sql.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        // Logger setup + functionality and UI are only temporarily in the same class.
        // They will be separated down the road and the UI is temporary.
        
        // Initialise database.
        Database db = new Database();
        
        // Initialise DAOs.
        UserDAO users = new UserDAO(db);
        FilmDAO films = new FilmDAO(db);
        ReviewDAO reviews = new ReviewDAO(db);
        TagDAO tags = new TagDAO(db);
        
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
            String cmnd = lukija.nextLine();

            if (cmnd.equals("1")) {
                System.out.println("");
                System.out.println("Login by entering username:");
                
                System.out.print("> ");
                String username = lukija.nextLine();
                
                User user = users.find(username);

                if (user == null) {
                    System.out.println("Username not found!");
                } else {                    
                    System.out.println("");
                    System.out.println("Welcome " + user.getName() +  "!");
                        
                    while (true) {
                        System.out.println("");
                        System.out.println("Options:");
                        System.out.println("1 - Log a new film");
                        System.out.println("2 - See all logged films");
                        System.out.println("3 - Logout");
                        System.out.print("> ");
                        cmnd = lukija.nextLine();

                        if (cmnd.equals("1")) {
                            System.out.println("Film: ");
                            System.out.print("> ");
                            String filmName = lukija.nextLine();

                            System.out.println("Year: ");
                            System.out.print("> ");
                            String filmYear = lukija.nextLine();
                            
                            if (films.find(filmName) == null) {
                                Film film = new Film(null, filmName, filmYear);
                                films.create(film);
                            }
                            
                            Film film = films.find(filmName);

                            System.out.println("Rating (1-5):");
                            System.out.print("> ");
                            Integer rating = Integer.parseInt(lukija.nextLine());
                            
                            Review review = new Review(null, user.getId(), film.getId(), rating);
                            reviews.create(review);
                        } else if (cmnd.equals("2")) {
                            List<Review> allReviews = reviews.findAllByUser(user.getId());
                            
                            for (Review review : allReviews) {
                                System.out.println(films.find(review.getFilmID()) + " - Rated: " + review.getRating());
                            }

                        } else if (cmnd.equals("3")) {
                            break;
                        } else {
                            System.out.println("Oops, that was not an option, try again!");
                        }
                    }
                }

            } else if (cmnd.equals("2")) {
                while (true) {
                    System.out.println("Register by entering username (5-15 characters):");
                    
                    System.out.print("> ");
                    String username = lukija.nextLine();

                    if (username.length() < 5) {
                        System.out.println("Username is too short!");
                    } else if (username.length() > 15) {
                        System.out.println("Username is too long!");
                    } else {
                        users.create(new User(null, username));
                        break;
                    }
                }
            } else if (cmnd.equals("3")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Oops, that was not an option, try again!");
            }
            
        } 
    }  
}
