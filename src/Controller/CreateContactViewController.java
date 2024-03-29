package Controller;

import Models.Contact;
import Models.DBConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreateContactViewController implements Initializable {

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private DatePicker birthdayDatePicker;

    @FXML
    private ImageView imageView;

    @FXML
    private Label warningMessage;


    /**
     * This method will provide initial values for the view
     * and configure any listeners
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Create new contact
     */
    @FXML
    public void createContactButtonPushed(ActionEvent actionEvent) throws IOException, InterruptedException {
        //set default text and color of border
        warningMessage.setText("");
        firstNameTextField.styleProperty().set("-fx-border-color: none;");
        lastNameTextField.styleProperty().set("-fx-border-color: none;");
        birthdayDatePicker.styleProperty().set("-fx-border-color: none;");
        addressTextField.styleProperty().set("-fx-border-color: none;");
        phoneTextField.styleProperty().set("-fx-border-color: none;");
        if(testFieldsForInputs()) {
            try {
                Contact newContact = new Contact(firstNameTextField.getText(),
                        lastNameTextField.getText(),
                        birthdayDatePicker.getValue(),
                        addressTextField.getText(),
                        phoneTextField.getText());
                System.out.printf("The new contact is %s%n", newContact);
                //insert newContact to database
                DBConnect.insertContactIntoDB(newContact);
                //change scene to table
                SceneChanger.changeScenes(actionEvent, "../Views/TableOfContacts.fxml", "Table");
            } catch (IllegalArgumentException | SQLException e) {
                warningMessage.setText(e.getMessage());
            }
        }
    }

    /**
     * This method will check each of the fields in the GUI to ensure they have valid inputs
     */
    public boolean testFieldsForInputs()
    {
        warningMessage.setText("");
        if (firstNameTextField.getText().isEmpty())
        {
            firstNameTextField.styleProperty().set("-fx-border-color: red;");
            String msg = warningMessage.getText();
            warningMessage.setText(msg + "First name cannot be empty\n");
        }
        if (lastNameTextField.getText().isEmpty())
        {
            lastNameTextField.styleProperty().set("-fx-border-color: red;");
            String msg = warningMessage.getText();
            warningMessage.setText(msg + "Last name cannot be empty\n");
        }
        try{
            LocalDate date =birthdayDatePicker.getValue();
            System.out.println(date.toString());

        }
        catch (Exception e)
        {
            birthdayDatePicker.styleProperty().set("-fx-border-color: red;");
            String msg = warningMessage.getText();
            warningMessage.setText(msg+"Please enter date using calendar\n");
        }
        if (addressTextField.getText().isEmpty())
        {
            addressTextField.styleProperty().set("-fx-border-color: red;");
            String msg = warningMessage.getText();
            warningMessage.setText(msg + "Address cannot be empty\n");
        }
        if (phoneTextField.getText().isEmpty())
        {
            phoneTextField.styleProperty().set("-fx-border-color: red;");
            String msg = warningMessage.getText();
            warningMessage.setText(msg + "Phone cannot be empty\n");
        }

        return warningMessage.getText().isEmpty();
    }

    /**
     * choose image from your computer
     * @param event
     */
    public void chooseImageButtonPushed(ActionEvent event)
    {
        //get the Stage to open a new window (or Stage in JavaFX)
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        //Instantiate a FileChooser object
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");

        //filter for .jpg and .png
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("Image File (*.jpg, *.png)", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(jpgFilter);

        //Set to the user's picture directory or user directory if not available
        String userDirectoryString = System.getProperty("user.home")+"\\Pictures";
        File userDirectory = new File(userDirectoryString);

        //if you cannot navigate to the pictures directory, go to the user home
        if (!userDirectory.canRead())
            userDirectory = new File(System.getProperty("user.home"));

        fileChooser.setInitialDirectory(userDirectory);

        //open the file dialog window
        File imageFile = fileChooser.showOpenDialog(stage);

        if (imageFile != null)
        {
            //update the ImageView with the new image
            if (imageFile.isFile())
            {
                try
                {
                    Image image = new Image(imageFile.toURI().toString());
                    imageView.setImage(image);
                }
                catch (Exception e)
                {
                    System.err.println(e.getMessage());
                }
            }
        }

    }
}
