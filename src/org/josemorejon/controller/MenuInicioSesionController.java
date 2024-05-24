/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorejon.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.josemorejon.system.Main;
import org.josemorejon.utils.SuperPenguinAlertas;


public class MenuInicioSesionController implements Initializable {
    private Main stage;
    
    @FXML
    PasswordField tfContra;
    
    @FXML
    TextField tfUsuario;
    
    @FXML
    Button btnIniciar, btnRegistrar;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(!tfUsuario.getText().equals("") && !tfContra.getText().equals("")){
            if(event.getSource() == btnIniciar){
                stage.menuPrincipalView();
            }else if(event.getSource() == btnRegistrar){
                stage.menuPrincipalView();
            }
        }else{
            SuperPenguinAlertas.getInstance().mostrarAlertasInformacion(33);
            if(tfUsuario.getText().equals("")){
                tfUsuario.requestFocus();
            }else if(tfContra.getText().equals("")){
                tfContra.requestFocus();
            }   
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }    
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }  
    
    
}
