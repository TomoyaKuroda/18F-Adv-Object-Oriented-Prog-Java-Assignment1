package Models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * main method
 */
public class Main extends Application {
    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * Start from Contact Table
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        //Parent root = FXMLLoader.load(getClass().getResource("../Views/CreateContactView.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("../Views/TableOfContacts.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Contact Table");
        stage.getIcons().add(new Image("Images/images.png"));
        stage.show();
    }
}
