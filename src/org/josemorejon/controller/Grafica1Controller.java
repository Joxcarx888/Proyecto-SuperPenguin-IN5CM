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
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.josemorejon.dao.Conexion;
import org.josemorejon.model.Empleado;
import org.josemorejon.system.Main;

public class Grafica1Controller implements Initializable {
    private Main stage;
    
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    
    @FXML
    BarChart<String, Number> grfSueldos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Empleado> empleados = listarEmpleados();

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        grfSueldos.setTitle("Sueldos de Empleados");
        xAxis.setLabel("Empleado");
        yAxis.setLabel("Sueldo");

        grfSueldos.getData().clear(); 

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        Random rand = new Random();
        for (Empleado empleado : empleados) {
            XYChart.Data<String, Number> data = new XYChart.Data<>(empleado.getNombreE(), empleado.getSueldo());
            // Asignar un color aleatorio a cada barra
            String color = String.format("#%06x", rand.nextInt(0xffffff + 1)); // Generar un color hexadecimal aleatorio
            data.nodeProperty().addListener((obs, oldNode, newNode) -> {
                if (newNode != null) {
                    newNode.setStyle("-fx-bar-fill: " + color + ";"); // Asignar el color al nodo de la barra
                }
            });
            series.getData().add(data);
        }
        grfSueldos.getData().add(series);
    }


    
    private ObservableList<Empleado> listarEmpleados() {
        ArrayList<Empleado> empleados = new ArrayList<>();
        
        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_ListarEmpleados()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int empleadoId = resultSet.getInt("empleadoId");
                String nombreEmpleado = resultSet.getString("nombreEmpleado");
                String apellidoEmpleado = resultSet.getString("apellidoEmpleado");
                double sueldo = resultSet.getDouble("sueldo");
                Time horaentrada = resultSet.getTime("horaentrada");
                Time horaSalida = resultSet.getTime("horaSalida");
                String cargoId = resultSet.getString("cargo");
                String encargadoId = resultSet.getString("nombreEncargado");
            
                empleados.add(new Empleado(empleadoId, nombreEmpleado, apellidoEmpleado, sueldo, horaentrada, horaSalida, cargoId, encargadoId));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        return FXCollections.observableList(empleados);
    }
    
    private Empleado encontrarEmpleadoConMasSueldo(ObservableList<Empleado> empleados) {
        Empleado empleadoConMasSueldo = null;
        double sueldoMasAlto = Double.MIN_VALUE;

        for (Empleado empleado : empleados) {
            if (empleado.getSueldo() > sueldoMasAlto) {
                sueldoMasAlto = empleado.getSueldo();
                empleadoConMasSueldo = empleado;
            }
        }

        return empleadoConMasSueldo;
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
}
