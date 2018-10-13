package Controller;

import Models.Contact;
import Views.ControllerClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class SceneChanger {

    /**
     * This method will accept the title of the new scene, the .fxml file name for
     * the view and the ActionEvent that triggered the change
     */
    public static void changeScenes(ActionEvent event, String viewName, String title) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        System.out.println("Viewname: "+ viewName);
        loader.setLocation(new Object(){}.getClass().getResource(viewName));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);

        //get the stage from the event that was passed in
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }



    /**
     * This method will change scenes and preload the next scene with a Contact object
     */
    public static void changeScenes(ActionEvent event, String viewName, String title, Contact contact, ControllerClass controllerClass) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new Object(){}.getClass().getResource(viewName));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);

        //access the controller class and preloaded the contact data
        controllerClass = loader.getController();
        controllerClass.preloadData(contact);

        //get the stage from the event that was passed in
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
