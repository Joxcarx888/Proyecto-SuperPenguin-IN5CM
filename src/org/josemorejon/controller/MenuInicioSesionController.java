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
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.josemorejon.dao.Conexion;
import org.josemorejon.model.Usuario;
import org.josemorejon.system.Main;
import org.josemorejon.utils.PasswordUtils;
import org.josemorejon.utils.SuperPenguinAlertas;


public class MenuInicioSesionController implements Initializable {
    private Main stage;
    private int op = 0;
    
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    
    @FXML
    PasswordField tfContra;
    
    @FXML
    TextField tfUsuario;
    
    @FXML
    Button btnIniciar, btnRegistrar;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        
        if(event.getSource() == btnRegistrar){
                stage.menuRegistrarUsuarioView();
        }else if(!tfUsuario.getText().equals("") && !tfContra.getText().equals("")){
            if(event.getSource() == btnIniciar){
                Usuario usuario = buscarUsuario();
                if(op == 0){
                    if(usuario != null){
                        if(PasswordUtils.getIntance().checkPassword(tfContra.getText(), usuario.getContrasenia())){
                            if(usuario.getNivelAccesoId() == 1){
                                btnRegistrar.setDisable(false);
                                btnIniciar.setText("IR AL MENU");
                                op = 33;
                            }else if(usuario.getNivelAccesoId() == 2){                 
                                 stage.menuPrincipalView(); 
                                 SuperPenguinAlertas.getInstance().alertaSaludo(usuario.getUsuario());
                            }
                        }else{
                            SuperPenguinAlertas.getInstance().mostrarAlertasInformacion(888);
                        }
                        
                    }else{
                        SuperPenguinAlertas.getInstance().mostrarAlertasInformacion(5);
                    }
                }else{
                    stage.menuPrincipalView();
                    SuperPenguinAlertas.getInstance().alertaSaludo(usuario.getUsuario());
                }

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
    
    public Usuario buscarUsuario(){
        Usuario usuario = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_buscarUsuarios(?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfUsuario.getText());
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                int usuarioId = resultSet.getInt("usarioId");
                String user = resultSet.getString("usuario");
                String contrasenia = resultSet.getString("contrasenia");
                int nivelAccesoId = resultSet.getInt("nivelAccesoId");
                int empleadoId = resultSet.getInt("empleadoId");
                
                usuario = new Usuario(usuarioId,user,contrasenia,nivelAccesoId,empleadoId);
            
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
        
        return usuario;
    
    }
    
    
}
