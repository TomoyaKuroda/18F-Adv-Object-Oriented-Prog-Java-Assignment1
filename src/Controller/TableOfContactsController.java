package Controller;

import Models.Contact;
import Models.DBConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TableOfContactsController implements Initializable {
    @FXML
    private TableView<Contact> tableView;

    @FXML
    private TableColumn<Contact, Integer> idColumn;

    @FXML
    private TableColumn<Contact, String> firstNameColumn;

    @FXML
    private TableColumn<Contact, String> lastNameColumn;

    @FXML
    private TableColumn<Contact, String> addressColumn;

    @FXML
    private TableColumn<Contact, String> phoneColumn;

    @FXML
    private Label messageLabel;


    /**
     *  Get all contacts from database and display in the table
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // confgure the table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<Contact, Integer>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("lastName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("address"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("phone"));
        try {
            tableView.getItems().addAll(DBConnect.getAllContacts());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Change the scene to register one
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void changeToRegisterButtonPushed(ActionEvent actionEvent) throws IOException {
        //SceneChanger sc = new SceneChanger();
        SceneChanger.changeScenes(actionEvent, "../Views/CreateContactView.fxml","Register");
    }
}
