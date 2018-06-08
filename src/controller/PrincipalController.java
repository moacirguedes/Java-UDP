package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import Main.Main;

/**
 * FXML Controller class
 *
 * @author moaci
 */
public class PrincipalController implements Initializable {

    BorderPane border;
    
    @FXML
    private MenuItem btnSender;
    @FXML
    private SeparatorMenuItem btnReceiverrOnAction;
    @FXML
    private MenuItem btnSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSenderOnAction(ActionEvent event) {
        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/sender.fxml"));
            border = Main.getRoot();
            border.setCenter(anchorPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnReceiverOnAction(ActionEvent event) {
        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/receiver.fxml"));
            border = Main.getRoot();
            border.setCenter(anchorPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void sairOnAction(ActionEvent event) {
    Platform.exit();
    }
    
}
