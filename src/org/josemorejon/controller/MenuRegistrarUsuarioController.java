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
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.josemorejon.dao.Conexion;
import org.josemorejon.model.Empleado;
import org.josemorejon.model.NivelAcceso;
import org.josemorejon.system.Main;
import org.josemorejon.utils.PasswordUtils;
import org.josemorejon.utils.SuperPenguinAlertas;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class MenuRegistrarUsuarioController implements Initializable {
    private Main stage;
    
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    
    @FXML
    TextField tfUsuario,tfContra;
    
    @FXML
    ComboBox cmbEmpleados,cmbNivelAcceso;
    
    @FXML
    Button btnRegistrar,btnRegresar,btnEmpleado;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnRegresar){
                stage.menuInicioSesionView();
        }else if(!tfUsuario.getText().equals("") && !tfContra.getText().equals("")){
            if(event.getSource() == btnRegistrar){
                agregarUsuario();
                SuperPenguinAlertas.getInstance().mostrarAlertasInformacion(400);
                stage.menuInicioSesionView();
            }
        }else if(event.getSource() == btnEmpleado){
                stage.formEmpleadosView(3);
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
        cmbEmpleados.setItems(listarEmpleados());
        cmbNivelAcceso.setItems(listarNivelesAcceso());
    }   
    
    public ObservableList<NivelAcceso> listarNivelesAcceso(){
        ArrayList<NivelAcceso> nivelesAcceso = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarNivelesAcceso()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int nivelAccesoId = resultSet.getInt("nivelAccesoId");
                String nivelAcceso = resultSet.getString("nivelAcceso");
                
                nivelesAcceso.add(new NivelAcceso(nivelAccesoId,nivelAcceso));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                
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
    
        return FXCollections.observableArrayList(nivelesAcceso);
    }
    
    public ObservableList<Empleado> listarEmpleados(){
        ArrayList<Empleado> empleados = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = " CALL sp_ListarEmpleados()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int empleadoId = resultSet.getInt("empleadoId");
                String nombreEmpleado = resultSet.getString("nombreEmpleado");
                String apellidoEmpleado = resultSet.getString("apellidoEmpleado");
                double sueldo = resultSet.getDouble("sueldo");
                Time horaentrada = resultSet.getTime("horaentrada");
                Time horaSalida = resultSet.getTime("horaSalida");
                String cargoId = resultSet.getString("cargo");
                String encargadoId = resultSet.getString("nombreEncargado");
            
                empleados.add(new Empleado(empleadoId, nombreEmpleado, apellidoEmpleado, sueldo, horaentrada, horaSalida,cargoId,encargadoId));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                
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
        
        
        return FXCollections.observableList(empleados);
    }
    
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    public void agregarUsuario(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarUsuario(?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfUsuario.getText());
            statement.setString(2,PasswordUtils.getIntance().encrytedPassword(tfContra.getText()));
            statement.setInt(3,((NivelAcceso)cmbNivelAcceso.getSelectionModel().getSelectedItem()).getNivelAccesoId());
            statement.setInt(4,((Empleado)cmbEmpleados.getSelectionModel().getSelectedItem()).getEmpleadoId());
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
    
}
