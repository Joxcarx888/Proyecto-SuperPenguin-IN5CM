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
    MenuItem btnClientes, btnTicketSoporte,btnCargos,btnDistribuidores,btnCategoriaP,btnEmpleados,btnProductos,btnPromociones,btnFacturas,btnCompras;
    
    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnClientes) {
            stage.menuClientesView();
        } else if(event.getSource() == btnTicketSoporte){
            stage.menuTicketSoporteView();
        } else if(event.getSource() == btnCargos){
            stage.menuCargosView();
        } else if(event.getSource() == btnDistribuidores){
            stage.menuDistribuidoresView();
        } else if(event.getSource() == btnCategoriaP){
            stage.menuCategoriaPView();
        } else if(event.getSource() == btnEmpleados){
            stage.menuEmpleadosView();
        }else if(event.getSource() == btnProductos){
            stage.menuProductosView();
        }else if(event.getSource() == btnPromociones){
            stage.menuPromocionesView();
        }else if(event.getSource() == btnFacturas){
            stage.menuFacturasView();
        }else if(event.getSource() == btnCompras){
            stage.menuComprasView();
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
