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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.josemorejon.dao.Conexion;
import org.josemorejon.dto.ClienteDTO;
import org.josemorejon.model.Cliente;
import org.josemorejon.system.Main;
import org.josemorejon.utils.SuperPenguinAlertas;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class FormClienteController implements Initializable {
    private Main stage;
    private int op;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    
   @FXML
    Button btnRegresarFMA,btnGuardar;
   
   @FXML
   TextField tfClienteId,tfNombre,tfApellido,tfTelefono,tfDireccion,tfNit;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    
        if(event.getSource() == btnRegresarFMA){
            ClienteDTO.getClienteDTO().setCliente(null);
            stage.menuClientesView();
        }else if(event.getSource() == btnGuardar){
            if(op == 1){
                if(!tfNombre.getText().equals("") && !tfApellido.getText().equals("") && !tfDireccion.getText().equals("")){
                    agregarCliente();
                    SuperPenguinAlertas.getInstance().mostrarAlertasInformacion(400);
                    stage.menuClientesView();
                }else{
                    SuperPenguinAlertas.getInstance().mostrarAlertasInformacion(33);
                    if(tfNombre.getText().equals("")){
                        tfNombre.requestFocus();
                    }else if(tfApellido.getText().equals("")){
                        tfApellido.requestFocus();
                    }else{
                        tfDireccion.requestFocus();
                    }
                }
                
               
            }else if(op == 2){
                if(!tfNombre.getText().equals("") && !tfApellido.getText().equals("") && !tfDireccion.getText().equals("")){
                    if(SuperPenguinAlertas.getInstance().mostrarAlertaConfirmacion(505).get() == ButtonType.OK){
                        editarCliente();
                        ClienteDTO.getClienteDTO().setCliente(null);
                        SuperPenguinAlertas.getInstance().mostrarAlertasInformacion(500);
                        stage.menuClientesView();
                    }else{
                        stage.menuClientesView();
                    }
                }else{
                    SuperPenguinAlertas.getInstance().mostrarAlertasInformacion(33);
                    if(tfNombre.getText().equals("")){
                        tfNombre.requestFocus();
                    }else if(tfApellido.getText().equals("")){
                        tfApellido.requestFocus();
                    }else{
                        tfDireccion.requestFocus();
                    }
                }
                
            }
        }
    
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(ClienteDTO.getClienteDTO().getCliente() != null){
            cargarDatos(ClienteDTO.getClienteDTO().getCliente());
        }
    }

    public void cargarDatos(Cliente cliente){
        tfClienteId.setText(Integer.toString(cliente.getClienteId()));
        tfNombre.setText(cliente.getNombre());
        tfApellido.setText(cliente.getApellido());
        tfTelefono.setText(cliente.getTelefono());
        tfDireccion.setText(cliente.getDireccion());
        tfNit.setText(cliente.getNit());

    }

    public void agregarCliente(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_agregarCliente(?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombre.getText());
            statement.setString(2, tfApellido.getText());
            statement.setString(3, tfTelefono.getText());
            statement.setString(4, tfDireccion.getText());
            statement.setString(5, tfNit.getText());
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
    
    
    public void editarCliente(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_EditarCliente(?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfClienteId.getText()));
            statement.setString(2, tfNombre.getText());
            statement.setString(3, tfApellido.getText());
            statement.setString(4, tfTelefono.getText());
            statement.setString(5, tfDireccion.getText());
            statement.setString(6, tfNit.getText());
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
