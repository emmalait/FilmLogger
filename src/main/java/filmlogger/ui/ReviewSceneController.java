/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmlogger.ui;

import filmlogger.domain.Logger;
import filmlogger.domain.Review;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author emmalait
 */
public class ReviewSceneController implements Initializable {
    private Logger logger;
    private LoggerAppMain application;
    private Review review;
    
    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    
    public void setApplication(LoggerAppMain application) {
        this.application = application;
    }
    
    @FXML
    private Text filmToReviewText;
    
    @FXML
    private DatePicker reviewDatePicker;
    
    @FXML
    private Slider ratingSlider;
    
    @FXML
    private TextArea reviewTextArea;
    
    @FXML
    private Button addReviewButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void setReview(Review review) {
        this.review = review;
        filmToReviewText.setText(review.getFilm().toString());
        reviewDatePicker.setValue(review.getDate());
        ratingSlider.setValue(review.getRating());
        reviewTextArea.setText(review.getReview());
        if (review.getRating() == -1) {
            addReviewButton.setText("Add");
        } else {
            addReviewButton.setText("Update");
        }
    }
      
    @FXML
    private void handleAddReviewButtonAction(ActionEvent event) {
        review.setDate(reviewDatePicker.getValue());
        review.setRating((int) ratingSlider.getValue());
        review.setReview(reviewTextArea.getText());
        logger.updateReview(review);
        reviewDatePicker.setValue(LocalDate.now());
        ratingSlider.setValue(0.00);
        reviewTextArea.setText("");
        
        application.setLoggerScene();
    }
    
    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        application.setLoggerScene();
    }
}
