package filmlogger.ui;

import filmlogger.dao.*;
import filmlogger.domain.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

/**
 * Class responsible for initialising the application and starting the UI.
 * 
 * @author emmalait
 */

public class LoggerAppMain extends Application {
    private Stage stage;
    private Logger logger;
    private Scene loginScene;
    private Scene registerScene;
    private Scene loggerScene;
    private Scene reviewScene;
    private LoggerSceneController loggerSceneController;
    private ReviewSceneController reviewSceneController;

    /**
     * Initialises the database, DAOs and the different scenes.
     * 
     * @throws Exception 
     */
    
    @Override
    public void init() throws Exception {
        Database db = new Database("jdbc:sqlite:filmlogger.db");
        
        DbUserDAO users = new DbUserDAO(db);
        DbFilmDAO films = new DbFilmDAO(db);
        DbTagDAO tags = new DbTagDAO(db);
        DbReviewDAO reviews = new DbReviewDAO(db, users, films, tags);

        logger = new Logger(users, films, reviews, tags);
        
        FXMLLoader loginSceneLoader = new FXMLLoader(getClass().getResource("/fxml/LoginScene.fxml"));
        Parent loginPane = loginSceneLoader.load();
        LoginSceneController loginSceneController = loginSceneLoader.getController();
        loginSceneController.setLogger(logger);
        loginSceneController.setApplication(this);
        loginScene = new Scene(loginPane);
        
        FXMLLoader registerSceneLoader = new FXMLLoader(getClass().getResource("/fxml/RegisterScene.fxml")); 
        Parent registerPane = registerSceneLoader.load();
        RegisterSceneController registerSceneController = registerSceneLoader.getController();
        registerSceneController.setLogger(logger);
        registerSceneController.setApplication(this);
        registerScene = new Scene(registerPane);
        
        FXMLLoader loggerSceneLoader = new FXMLLoader(getClass().getResource("/fxml/LoggerScene.fxml")); 
        Parent loggerPane = loggerSceneLoader.load();
        loggerSceneController = loggerSceneLoader.getController();
        loggerSceneController.setLogger(logger);
        loggerSceneController.setApplication(this);
        loggerScene = new Scene(loggerPane);
        
        FXMLLoader reviewSceneLoader = new FXMLLoader(getClass().getResource("/fxml/ReviewScene.fxml")); 
        Parent reviewPane = reviewSceneLoader.load();
        reviewSceneController = reviewSceneLoader.getController();
        reviewSceneController.setLogger(logger);
        reviewSceneController.setApplication(this);
        reviewScene = new Scene(reviewPane);
    }
    
    /**
     * Sets up the first stage.
     * 
     * @param stage
     * @throws Exception 
     */
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        
        stage.setTitle("Film Logger");
        setLoginScene();
        stage.show(); 
    }
    
    /**
     * Sets up the LoginScene.
     */
    
    public void setLoginScene() {
        stage.setScene(loginScene);
    }
    
    /**
     * Sets up the RegisterScene.
     */
    
    public void setRegisterScene() {
        stage.setScene(registerScene);
    }
    
    /**
     * Sets up the LoggerScene.
     */
    
    public void setLoggerScene() {
        loggerSceneController.setLoggedInUserText();
        loggerSceneController.setWatchlist();
        loggerSceneController.setSeen();
        
        stage.setScene(loggerScene);
    }
    
    /**
     * Sets up the ReviewScene.
     *
     * @param review 
     */
    
    public void setReviewScene(Review review) {
        reviewSceneController.setReview(review);
        stage.setScene(reviewScene);
    }

    public static void main(String[] args) throws Exception {    
        launch(args);
    }
}
