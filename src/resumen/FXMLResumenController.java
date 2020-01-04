/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resumen;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import modelo.Sesion;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class FXMLResumenController implements Initializable {

    @FXML
    private Button nuevoEjercicio;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;

    public enum Estado {
        MOSTRAR, REINICIAR, NUEVO_EJERCICIO
    };

    private Stage primaryStage;
    private Scene escenaAnterior;
    private String tituloAnterior;

    @FXML
    private PieChart trabajoAlDescanso;
    @FXML
    private BarChart<String, Number> duracionTeoricaAlReal;
    @FXML
    private Button reiniciar;
    private long tiempoRealDeSesion;
    private Sesion sesion;

    //private ReadOnlyObjectProperty<Estado> estadoProperty  = new ReadOnlyObjectWrapper<>();
    public ReadOnlyObjectWrapper<Estado> estadoProperty = new ReadOnlyObjectWrapper<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estadoProperty.set(Estado.MOSTRAR);

    }

    public void initStage(Stage stage, Sesion nuevo, long tiempoReal) {
        tiempoRealDeSesion = tiempoReal;
        sesion = nuevo;
        primaryStage = stage;
        escenaAnterior = stage.getScene();
        tituloAnterior = stage.getTitle();
        primaryStage.setTitle("Sesion Terminada");
        initGraficaDeBarras();
        initGraficaDeTarta();
    }

    private void initGraficaDeBarras() {
        xAxis.setLabel("");
        yAxis.setLabel("Tiempo");

        XYChart.Series<String, Number> series = new XYChart.Series();

        series.getData().add(new XYChart.Data<>("Duracion teórica " + (long) (calcularTiempoDeTrabajo() + calcularTiempoDeDescanso()) + "s",
                (long) (calcularTiempoDeTrabajo() + calcularTiempoDeDescanso())));
        series.getData().add(new XYChart.Data<>("Duracion real " + (long) tiempoRealDeSesion / 1000 + "s", (long) tiempoRealDeSesion / 1000));

        duracionTeoricaAlReal.setLegendVisible(false);
        duracionTeoricaAlReal.getData().add(series);
    }

    private void initGraficaDeTarta() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Trabajo " + calcularTiempoDeTrabajo() + "s", calcularTiempoDeTrabajo()),
                new PieChart.Data("Descanso " + calcularTiempoDeDescanso() + "s", calcularTiempoDeDescanso()));

        trabajoAlDescanso.setData(pieChartData);
        trabajoAlDescanso.setLegendVisible(false);
        trabajoAlDescanso.setTitle("Tiempo de trabajo a tiempo de descanso");
    }

    private long calcularTiempoDeTrabajo() {
        return sesion.getN_circuitos() * sesion.getN_ejercicios() * sesion.getT_ejercicio().getSeconds();
    }

    private long calcularTiempoDeDescanso() {
        return sesion.getN_circuitos() * (sesion.getN_ejercicios() - 1) * sesion.getDescanso_ejercicio().getSeconds()
                + (sesion.getN_circuitos() - 1) * sesion.getDescanso_circuito().getSeconds();
    }

    @FXML
    private void nuevoEjercicio(ActionEvent event) {
        primaryStage.setTitle(tituloAnterior);
        primaryStage.setScene(escenaAnterior);
        estadoProperty.set(Estado.NUEVO_EJERCICIO);
    }

    @FXML
    private void reiniciarTimer(ActionEvent event) {
        estadoProperty.set(Estado.REINICIAR);
        primaryStage.setTitle(tituloAnterior);
        primaryStage.setScene(escenaAnterior);
    }

    public ReadOnlyObjectProperty<Estado> currentEstadoProperty() {
        return estadoProperty.getReadOnlyProperty();
    }
}
