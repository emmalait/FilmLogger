package filmlogger.ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import filmlogger.domain.Logger;
import filmlogger.domain.Review;
import filmlogger.domain.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author emmalait
 */

public class LoggerSceneController implements Initializable {
    private Logger logger;
    private LoggerAppMain application;
    
    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    
    public void setApplication(LoggerAppMain application) {
        this.application = application;
    }
    
    @FXML
    private Text loggedInUserText;
    
    @FXML
    private Button logoutButton;
    
    @FXML
    private Text noOfToWatchText;
    
    @FXML
    private VBox watchlistBox;
    
    @FXML
    private TextField filmNameField;
    
    @FXML
    private TextField filmYearField;
    
    @FXML
    private Button addToWatchlistButton;
    
    @FXML
    private Text errorMessageWatchlist;
    
    @FXML
    private Text noOfSeenText;
    
    @FXML
    private VBox seenBox;
    
    public void setLoggedInUserText() {
        User user = logger.getCurrentUser();
        loggedInUserText.setText(user.getName() + " (" + user.getUsername() + ")");
    }
    
    public void setWatchlist() {
        watchlistBox.getChildren().clear();
        List<Review> filmsToWatch = logger.getWatchlist();
        
        filmsToWatch.forEach(review -> {
            watchlistBox.getChildren().add(createToWatchNode(review));
        });
        
        noOfToWatchText.setText("To watch: " + String.valueOf(filmsToWatch.size()));
    }
    
    private Node createToWatchNode(Review review) {
        HBox container = new HBox();
        container.setAlignment(Pos.CENTER_LEFT);
        container.setPrefHeight(47.0);
        container.setPrefWidth(413.0);
        container.setSpacing(10.0);
        
        Text film = new Text(review.getFilm().toString());
        Region region = new Region();
        Button markAsSeenButton = new Button("Mark as seen");
        
        markAsSeenButton.setOnAction(e -> {
            logger.markAsSeen(review);
            setWatchlist();
            setSeen();
        });
        
        HBox.setHgrow(region, Priority.ALWAYS);
        container.setPadding(new Insets(5, 5, 5, 5));
        container.getChildren().addAll(film, region, markAsSeenButton);
        return container;
    } 
    
    public void setSeen() {
        seenBox.getChildren().clear();
        
        List<Review> filmsSeen = logger.getSeen();
        
        filmsSeen.forEach(review -> {
            seenBox.getChildren().add(createSeenNode(review));
        });
        
        noOfSeenText.setText("Seen: " + String.valueOf(filmsSeen.size()));
    }
        
    private Node createSeenNode(Review review) {
        HBox container = new HBox();
        container.setAlignment(Pos.CENTER_LEFT);
        container.setPrefHeight(47.0);
        container.setPrefWidth(413.0);
        container.setSpacing(10.0);
        
        Text film = new Text(review.getFilm().toString());
        Region region = new Region();
        Button reviewButton = new Button();
        
        if (review.getRating() == -1) {
            reviewButton.setText("Add review");
        } else {
            reviewButton.setText("See review");   
        }
        
        reviewButton.setOnAction(e -> {
            application.setReviewScene(review);
        });

        HBox.setHgrow(region, Priority.ALWAYS);
        container.setPadding(new Insets(5, 5, 5, 5));
        container.getChildren().addAll(film, region, reviewButton);
        return container;
    } 

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
    }
    
    @FXML
    private void handleLogoutButton() {
        logger.logout();
        application.setLoginScene();
    }
    
    @FXML
    private void handleToWatchlistButton(ActionEvent event) throws SQLException {
        errorMessageWatchlist.setText(logger.addToWatchlist(filmNameField.getText(), filmYearField.getText()));
        filmNameField.setText("");
        filmYearField.setText("");
        setWatchlist();
    }

}
