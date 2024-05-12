package org.josemorejon.system;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.josemorejon.controller.FormAsignarEController;
import org.josemorejon.controller.FormCargoController;
import org.josemorejon.controller.FormCategoriaPController;
import org.josemorejon.controller.FormClienteController;
import org.josemorejon.controller.FormComprasController;
import org.josemorejon.controller.FormDetalleCompraController;
import org.josemorejon.controller.FormDetalleFacturaController;
import org.josemorejon.controller.FormDistribuidoresController;
import org.josemorejon.controller.FormEmpleadosController;
import org.josemorejon.controller.FormFacturasController;
import org.josemorejon.controller.FormProductosController;
import org.josemorejon.controller.FormPromocionesController;
import org.josemorejon.controller.MenuCargosController;
import org.josemorejon.controller.MenuCategoriaPController;
import org.josemorejon.controller.MenuClientesController;
import org.josemorejon.controller.MenuComprasController;
import org.josemorejon.controller.MenuDistribuidoresController;
import org.josemorejon.controller.MenuEmpleadosController;
import org.josemorejon.controller.MenuFacturasController;
import org.josemorejon.controller.MenuPrincipalController;
import org.josemorejon.controller.MenuProductosController;
import org.josemorejon.controller.MenuPromocionesController;
import org.josemorejon.controller.MenuTicketSoporteController;
 
public class Main extends Application {
    private final String URLVIEW = "/org/josemorejon/view/";
    private Stage stage;
    private Scene scene;
    private int op;
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Super Penguin S.A App");
        menuPrincipalView();
        stage.show();
    }
    public Initializable switchScene(String fxmlName, int width, int height) throws Exception{
        Initializable resultado = null;
        FXMLLoader loader = new FXMLLoader();
        InputStream file = Main.class.getResourceAsStream(URLVIEW + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(URLVIEW + fxmlName));
        scene = new Scene((AnchorPane)loader.load(file), width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        resultado = (Initializable)loader.getController();
        return resultado;
    }
    public void menuPrincipalView(){
        try{
            MenuPrincipalController menuPrincipalView = (MenuPrincipalController)switchScene("MenuPrincipalView.fxml", 950, 675);
            menuPrincipalView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void menuClientesView(){
        try{
            MenuClientesController menuClientesView = (MenuClientesController)switchScene("MenuClientesView.fxml", 1200, 750);
            menuClientesView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formClientesView(int op){
        try{
            FormClienteController formClientesView = (FormClienteController)switchScene("FormClienteView.fxml", 500, 750);
            formClientesView.setOp(op);
            formClientesView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuTicketSoporteView(){
        try{
            MenuTicketSoporteController menuTicketSoporteView = (MenuTicketSoporteController)switchScene("MenuTicketSoporteView.fxml", 1200, 750);
            menuTicketSoporteView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuCargosView(){
        try{
            MenuCargosController menuCargosView = (MenuCargosController)switchScene("MenuCargosView.fxml", 1200, 750);
            menuCargosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formCargosView(int op){
        try{
            FormCargoController formCargoView = (FormCargoController)switchScene("FormCargoView.fxml", 500, 750);
            formCargoView.setOp(op);
            formCargoView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuDistribuidoresView(){
        try{
            MenuDistribuidoresController menuCargosView = (MenuDistribuidoresController)switchScene("MenuDistribuidoresView.fxml", 1200, 750);
            menuCargosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formDistribuidoresView(int op){
        try{
            FormDistribuidoresController formDistribuidorView = (FormDistribuidoresController)switchScene("FormDistribuidoresView.fxml", 500, 750);
            formDistribuidorView.setOp(op);
            formDistribuidorView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuCategoriaPView(){
        try{
            MenuCategoriaPController menuCategoriaP = (MenuCategoriaPController)switchScene("MenuCategoriaPView.fxml", 1200, 750);
            menuCategoriaP.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formCategoriaPView(int op){
        try{
            FormCategoriaPController formCategoriaP = (FormCategoriaPController)switchScene("FormCategoriaPView.fxml", 500, 750);
            formCategoriaP.setOp(op);
            formCategoriaP.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuEmpleadosView(){
        try{
            MenuEmpleadosController menuEmpleado = (MenuEmpleadosController)switchScene("MenuEmpleadosView.fxml", 1200, 750);
            menuEmpleado.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formEmpleadosView(int op){
        try{
            FormEmpleadosController formEmpleado = (FormEmpleadosController)switchScene("FormEmpleadosView.fxml", 500, 1000);
            formEmpleado.setOp(op);
            formEmpleado.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formAsignarEView(){
        try{
            FormAsignarEController formAsignarE = (FormAsignarEController)switchScene("FormAsignarEView.fxml", 500, 750);
            formAsignarE.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuProductosView(){
        try{
            MenuProductosController menuProductos = (MenuProductosController)switchScene("MenuProductosView.fxml", 1200, 750);
            menuProductos.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formProductosView(int op){
        try{
            FormProductosController formProductos = (FormProductosController)switchScene("FormProductosView.fxml", 1200, 750);
            formProductos.setOp(op);
            formProductos.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuPromocionesView(){
        try{
            MenuPromocionesController menuPromociones = (MenuPromocionesController)switchScene("MenuPromocionesView.fxml", 1200, 750);
            menuPromociones.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formPromocionesView(int op){
        try{
            FormPromocionesController formPromociones = (FormPromocionesController)switchScene("FormPromocionesView.fxml", 500, 750);
            formPromociones.setOp(op);
            formPromociones.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuFacturasView(){
        try{
            MenuFacturasController menuFacturas = (MenuFacturasController)switchScene("MenuFacturasView.fxml", 1200, 750);
            menuFacturas.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formFacturasView(int op){
        try{
            FormFacturasController formFacturas = (FormFacturasController)switchScene("FormFacturasView.fxml", 500, 750);
            formFacturas.setOp(op);
            formFacturas.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formDetalleFacturaView(int op){
        try{
            FormDetalleFacturaController formDetalleFactura = (FormDetalleFacturaController)switchScene("FormDetalleFacturaView.fxml", 500, 750);
            formDetalleFactura.setOp(op);
            formDetalleFactura.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuComprasView(){
        try{
            MenuComprasController menuCompras = (MenuComprasController)switchScene("MenuComprasView.fxml", 1200, 750);
            menuCompras.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formComprasView(int op){
        try{
            FormComprasController formCompras = (FormComprasController)switchScene("FormComprasView.fxml", 500, 750);
            formCompras.setOp(op);
            formCompras.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formDetalleCompraView(int op){
        try{
            FormDetalleCompraController formDetalleFactura = (FormDetalleCompraController)switchScene("FormDetalleCompraView.fxml", 500, 750);
            formDetalleFactura.setOp(op);
            formDetalleFactura.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
