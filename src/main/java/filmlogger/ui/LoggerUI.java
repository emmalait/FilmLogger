package filmlogger.ui;

import filmlogger.dao.*;
import filmlogger.domain.*;
import java.util.*;

public class LoggerUI {
    
    public static void main(String[] args) throws Exception {        
        // Initialise database.
        Database db = new Database();
        
        // Initialise DAOs.
        DbUserDAO users = new DbUserDAO(db);
        DbFilmDAO films = new DbFilmDAO(db);
        DbTagDAO tags = new DbTagDAO(db);
        DbReviewDAO reviews = new DbReviewDAO(db, users, films, tags);
        
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
                        System.out.println("1 - Watchlist");
                        System.out.println("2 - Seen");
                        System.out.println("3 - Logout");
                        System.out.print("> ");
                        command = lukija.nextLine();
                        
                        if(command.equals("1")) {
                            while (true) {
                                System.out.println("");
                                System.out.println("WATCHLIST");
                                logger.printWatchlist();
                                System.out.println("- - -");
                                System.out.println("1 - Add a new film to watchlist");
                                System.out.println("2 - Return");
                                System.out.print("> ");
                                command = lukija.nextLine();

                                if(command.equals("1")) {
                                    System.out.println("Film: ");
                                    System.out.print("> ");
                                    String filmName = lukija.nextLine();

                                    System.out.println("Year: ");
                                    System.out.print("> ");
                                    String filmYear = lukija.nextLine();
                                    
                                    logger.addToWatchlist(filmName, filmYear);
                                } else if (command.equals("2")) {
                                    break;
                                } else {
                                    System.out.println("Oops, that was not an option, try again!");
                                }
                            }
                        } else if (command.equals("2")) {                            
                            while (true) {
                                System.out.println("");
                                System.out.println("SEEN FILMS");
                                logger.printSeen();
                                System.out.println("- - -");
                                System.out.println("Options: ");
                                System.out.println("1 - Review a new film");
                                System.out.println("2 - Return");
                                System.out.print("> ");
                                command = lukija.nextLine();
                            
                                if (command.equals("1")) {
                                    System.out.println("Film: ");
                                    System.out.print("> ");
                                    String filmName = lukija.nextLine();

                                    if (logger.findFilm(filmName) == null) {
                                        System.out.println("Year: ");
                                        System.out.print("> ");
                                        String filmYear = lukija.nextLine();

                                        logger.addFilm(filmName, filmYear);
                                    }

                                    Film reviewedFilm = logger.findFilm(filmName);

                                    System.out.println("Date watched (dd/mm/yyyy): ");
                                    System.out.print("> ");
                                    String watchDate = lukija.nextLine();

                                    System.out.println("Rating (1-5):");
                                    System.out.print("> ");
                                    Integer filmRating = Integer.parseInt(lukija.nextLine());

                                    System.out.println("Review (max. 1000 characters): ");
                                    System.out.print("> ");
                                    String filmReview = lukija.nextLine();

                                    logger.addToSeen(reviewedFilm, watchDate, filmRating, filmReview);
                                } else if (command.equals("2")) {
                                    break;
                                } else {
                                    System.out.println("Oops, that was not an option, try again!");
                                }
                            }
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