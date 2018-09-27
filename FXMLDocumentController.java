/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package russa_animado;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author fcolu
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ImageView Cenario;

    @FXML
    private ImageView Wagon;

    @FXML
    private ImageView Passenger;

    @FXML
    private TextField cadeiras;

    @FXML
    private TextField passageiros;

    @FXML
    private TextField tempo_embarque;

    @FXML
    private TextField tempo_desembarque;

    @FXML
    private TextField tempo_viagem;

    @FXML
    private ToolBar toolBar;

    @FXML
    private Button bCreateWagon;

    @FXML
    private Button bExcludeWagon;

    @FXML
    private Button bCreatePassenger;

    @FXML
    private Button bExcludePassenger;
    
    Passenger p;
    Wagon w;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void create_wagon(ActionEvent event) {
        int chairs = Integer.parseInt(cadeiras.getText());
        int time_traveling = Integer.parseInt(tempo_viagem.getText());
        
        w = Roller_coaster.create_wagon(chairs, time_traveling);
    }

    @FXML
    private void exclude_wagon(ActionEvent event) {
        Roller_coaster.exclude_wagon(w);
    }

    @FXML
    private void create_passenger(ActionEvent event) {
        int time_boarding = Integer.parseInt(tempo_embarque.getText());
        int time_landing = Integer.parseInt(tempo_desembarque.getText());
        int passengers = Integer.parseInt(passageiros.getText());
        
        for(int i=0; i < passengers; i++){
            p = Roller_coaster.create_passenger(time_boarding, time_landing);
        }
    }

    @FXML
    private void exclude_passenger(ActionEvent event) {
        Roller_coaster.exclude_passenger(p);
    }
    
}
