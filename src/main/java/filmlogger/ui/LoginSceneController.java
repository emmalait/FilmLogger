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
 * FXML Controller class for LoginScene
 *
 * @author emmalait
 */

public class LoginSceneController implements Initializable {
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
    private Text loginText;
    
    @FXML
    private TextField usernameLoginInput;
    
    @FXML
    private Button loginButton;
    
    @FXML
    private Button registerButton;
    
    @FXML
    private Text errorMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = usernameLoginInput.getText();
        
        boolean login = logger.login(username);
        
        if (login) {
            usernameLoginInput.setText("");
            application.setLoggerScene();
        } else {
            errorMessage.setText("Username not found!");
        }
        
    }
    
    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {
        errorMessage.setText("");
        application.setRegisterScene();
    }
}
