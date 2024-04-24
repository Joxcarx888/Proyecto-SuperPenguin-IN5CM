package org.josemorejon.controller;


import org.josemorejon.system.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MenuPrincipalController implements Initializable{
    private Main stage;
    
    @FXML
    MenuItem btnClientes;
    
    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnClientes) {
            stage.menuClientesView();
        }
    }

    
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        
    
    
    }
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
}
