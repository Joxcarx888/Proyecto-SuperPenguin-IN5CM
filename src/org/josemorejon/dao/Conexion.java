/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorejon.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    private static Conexion instance;
    private String jdburl = "jdbc:mysql://localhost:3306/SuperPenguin?serverTimezone=GMT-6&useSSL=false";
    private String user = "JoseMorejon";
    private String password = "Godzilla2";
    private Conexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public static Conexion getInstance(){
        if(instance == null){
            instance = new Conexion();
        }
        
        return instance;
    }
    
    public Connection obtenerConexion() throws SQLException{
        return DriverManager.getConnection(jdburl, user,password);
    }
}
