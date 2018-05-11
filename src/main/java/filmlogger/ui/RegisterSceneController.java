/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmlogger.ui;

import filmlogger.domain.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class for RegisterScene
 *
 * @author emmalait
 */
public class RegisterSceneController implements Initializable {
    private Logger logger;
    private LoggerAppMain application;
    
    /**
     * Method sets up the logger.
     * 
     * @param logger 
     */
    
    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    
    /**
     * Method sets up the application.
     * 
     * @param application 
     */
    
    public void setApplication(LoggerAppMain application) {
        this.application = application;
    }

    @FXML
    private Text registerText;
    
    @FXML
    private TextField usernameRegisterInput;
    
    @FXML
    private TextField nameRegisterInput;
    
    @FXML
    private Button registerInputButton;
    
    @FXML
    private Button backToLoginButton;
    
    @FXML
    private Text errorMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void handleRegisterInputButtonAction(ActionEvent event) {
        errorMessage.setText(logger.createUser(nameRegisterInput.getText(), usernameRegisterInput.getText()));
        nameRegisterInput.setText("");
        usernameRegisterInput.setText("");
    }
    
    @FXML
    private void handleBackToLoginButtonAction(ActionEvent event) {
        errorMessage.setText("");
        application.setLoginScene();
    }
}
