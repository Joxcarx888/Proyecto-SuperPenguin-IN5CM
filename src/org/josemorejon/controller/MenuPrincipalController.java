package org.josemorejon.controller;


import org.josemorejon.system.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import org.josemorejon.report.GenerarReporte;
import org.josemorejon.utils.SuperPenguinAlertas;

public class MenuPrincipalController implements Initializable{
    private Main stage;
    
    @FXML
    Button btnCerrarSesion;
    
    @FXML
    MenuItem btnClientes, btnTicketSoporte,btnCargos,btnDistribuidores,btnCategoriaP,btnEmpleados,btnProductos,btnPromociones,btnFacturas,btnCompras,btnSueldoEmpleados,btnClientes3,btnProductos3;
    
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
        }else if(event.getSource() == btnCerrarSesion){
            if(SuperPenguinAlertas.getInstance().mostrarAlertaConfirmacion(606).get() == ButtonType.OK){
                stage.menuInicioSesionView();
            }
        }else if(event.getSource() == btnSueldoEmpleados){
            stage.graficaSueldos();
        }else if(event.getSource() == btnClientes3){
            GenerarReporte.getInstance().generarClientes();
        }else if(event.getSource() == btnProductos3){
            GenerarReporte.getInstance().generarProductos();
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
