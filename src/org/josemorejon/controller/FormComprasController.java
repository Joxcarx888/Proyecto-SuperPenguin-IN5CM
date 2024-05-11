/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorejon.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.josemorejon.dao.Conexion;
import org.josemorejon.dto.CompraDTO;
import org.josemorejon.model.Compra;
import org.josemorejon.system.Main;
import org.josemorejon.utils.SuperPenguinAlertas;


public class FormComprasController implements Initializable {
    private Main stage;
    private int op;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    
   @FXML
    Button btnRegresarFMA,btnGuardar;
   
   @FXML
   TextField tfCompraId,tfFecha;
   
   @FXML
private void handleButtonAction(ActionEvent event) {
    if (event.getSource() == btnRegresarFMA) {
        CompraDTO.getCompraDTO().setCompra(null);
        stage.menuComprasView();
    } else if (event.getSource() == btnGuardar) {
        
            stage.menuComprasView();
        if (op == 2) {
            if (SuperPenguinAlertas.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK) {
                editarCompra();
                CompraDTO.getCompraDTO().setCompra(null);
                SuperPenguinAlertas.getInstance().mostrarAlertasInformacion(500);
                stage.menuComprasView();
            } else {
                stage.menuComprasView();
            }
        }
    }
}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(CompraDTO.getCompraDTO().getCompra() != null){
            cargarDatos(CompraDTO.getCompraDTO().getCompra());
        }
        
    }    
    
    public void cargarDatos(Compra compra) {
    tfCompraId.setText(Integer.toString(compra.getCompraId()));

    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
    tfFecha.setText(formatoFecha.format(compra.getFechaCompra()));
}

    
    public void editarCompra(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_EditarCompra(?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfCompraId.getText()));
            statement.setString(2,tfFecha.getText());
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement != null){
                    statement.close();
                }
                
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    public void setOp(int op) {
        this.op = op;
    }
    
}
