/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setEjercicio;

import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modelo.Sesion;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class FXMLSetEjecicioController implements Initializable {
private Stage primaryStage;
private Scene escenaAnterior;
private String tituloAnterior;
    @FXML
    private Button cerrar;
@FXML
    private Spinner<Integer> numeroCircuitos;
    @FXML
    private Spinner<Integer> trabajo10Min;
    @FXML
    private Spinner<Integer> trabajoMin;
    @FXML
    private Spinner<Integer> trabajo10Seg;
    @FXML
    private Spinner<Integer> trabajoSeg;
    @FXML
    private Spinner<Integer> descanso10Min;
    @FXML
    private Spinner<Integer> descansoMin;
    @FXML
    private Spinner<Integer> descanso10Seg;
    @FXML
    private Spinner<Integer> descansoSeg;
    @FXML
    private Spinner<Integer> descansoCircuitos10Min;
    @FXML
    private Spinner<Integer> descansoCircuitosMin;
    @FXML
    private Spinner<Integer> descansoCircuitos10Seg;
    @FXML
    private Spinner<Integer> descansoCircuitosSeg;
    @FXML
    private Button empezar;
    @FXML
    private Spinner<Integer> numeroDescansos;
    
    private LocalTime tiempoTrabajo = LocalTime.of(0, 0, 0);
    private LocalTime tiempoDescanso = LocalTime.of(0, 0, 0);
    private LocalTime tiempoCircuitos = LocalTime.of(0, 0, 0);
    @FXML
    private Text textTrabajo;
    @FXML
    private Text textDescanso;
    @FXML
    private Text textCircuitos;
    Sesion sesion;
    private ReadOnlyObjectWrapper<Sesion> sesionPropoerty = new ReadOnlyObjectWrapper<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        prepararSpinners();
        empezar.setDisable(true);
        textTrabajo.setText(LocalTime.of(0, 0, 0).toString());
        textDescanso.setText(LocalTime.of(0, 0, 0).toString());
        textCircuitos.setText(LocalTime.of(0, 0, 0).toString());
    }    
    
    public void initStage(Stage stage, Sesion nuevo) {
        primaryStage = stage;
        escenaAnterior = stage.getScene();
        tituloAnterior = stage.getTitle();
        primaryStage.setTitle("Ajustar Ejercicios");
        sesion = nuevo;
        defaultValuesSpinners();
    }

    @FXML
    private void cerrar(ActionEvent event) {
        primaryStage.setTitle(tituloAnterior);
        primaryStage.setScene(escenaAnterior);
    }

    @FXML
    private void empezar(ActionEvent event) {
        sesion = new Sesion();
        sesion.setN_circuitos(numeroCircuitos.getValue());
        sesion.setDescanso_circuito(Duration.ofSeconds(tiempoCircuitos.getMinute()*60 + tiempoCircuitos.getSecond()));
        sesion.setN_ejercicios(numeroDescansos.getValue());
        sesion.setT_ejercicio(Duration.ofSeconds(tiempoTrabajo.getMinute()*60 + tiempoTrabajo.getSecond()));
        sesion.setDescanso_ejercicio(Duration.ofSeconds(tiempoDescanso.getMinute()*60 + tiempoDescanso.getSecond()));
        sesionPropoerty.set(sesion);

        primaryStage.setTitle(tituloAnterior);
        primaryStage.setScene(escenaAnterior);
    }

    private void prepararSpinners() {
        SpinnerValueFactory<Integer> numeroSpinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1);
        SpinnerValueFactory<Integer> numeroSpinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1);
        SpinnerValueFactory<Integer> tiempoSpinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0, 1);
        SpinnerValueFactory<Integer> tiempoSpinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9, 0, 1);
        SpinnerValueFactory<Integer> tiempoSpinner3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0, 1);
        SpinnerValueFactory<Integer> tiempoSpinner4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9, 0, 1);
        SpinnerValueFactory<Integer> tiempoSpinner5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0, 1);
        SpinnerValueFactory<Integer> tiempoSpinner6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9, 0, 1);
        SpinnerValueFactory<Integer> tiempoSpinner7 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0, 1);
        SpinnerValueFactory<Integer> tiempoSpinner8 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9, 0, 1);
        SpinnerValueFactory<Integer> tiempoSpinner9 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0, 1);
        SpinnerValueFactory<Integer> tiempoSpinner10 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9, 0, 1);
        SpinnerValueFactory<Integer> tiempoSpinner11 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0, 1);
        SpinnerValueFactory<Integer> tiempoSpinner12 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9, 0, 1);
        
        
        numeroDescansos.setValueFactory(numeroSpinner1);
        numeroDescansos.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        numeroDescansos.setStyle("-fx-alignment: CENTER;");
        numeroDescansos.setStyle("-fx-text-alignment: CENTER;");

        numeroCircuitos.setValueFactory(numeroSpinner2);
        numeroCircuitos.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        
        trabajo10Min.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
        trabajoMin.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
        trabajo10Seg.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
        trabajoSeg.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
        trabajo10Min.setValueFactory(tiempoSpinner1);
        trabajoMin.setValueFactory(tiempoSpinner2);
        trabajo10Seg.setValueFactory(tiempoSpinner3);
        trabajoSeg.setValueFactory(tiempoSpinner4);
        
        descanso10Min.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
        descansoMin.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
        descanso10Seg.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
        descansoSeg.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
        descanso10Min.setValueFactory(tiempoSpinner5);
        descansoMin.setValueFactory(tiempoSpinner6);
        descanso10Seg.setValueFactory(tiempoSpinner7);
        descansoSeg.setValueFactory(tiempoSpinner8);
        
        descansoCircuitos10Min.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
        descansoCircuitosMin.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
        descansoCircuitos10Seg.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
        descansoCircuitosSeg.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
        descansoCircuitos10Min.setValueFactory(tiempoSpinner9);
        descansoCircuitosMin.setValueFactory(tiempoSpinner10);
        descansoCircuitos10Seg.setValueFactory(tiempoSpinner11);
        descansoCircuitosSeg.setValueFactory(tiempoSpinner12);
        
        trabajo10Min.valueProperty().addListener((obs, oldSelection, newSelection) -> {setTiempoTrabajo(); });
        trabajoMin.valueProperty().addListener((obs, oldSelection, newSelection) -> {setTiempoTrabajo(); });
        trabajo10Seg.valueProperty().addListener((obs, oldSelection, newSelection) -> {setTiempoTrabajo(); });
        trabajoSeg.valueProperty().addListener((obs, oldSelection, newSelection) -> {setTiempoTrabajo(); });
        
        descanso10Min.valueProperty().addListener((obs, oldSelection, newSelection) -> {setTiempoDescanso(); });
        descansoMin.valueProperty().addListener((obs, oldSelection, newSelection) -> {setTiempoDescanso(); });
        descanso10Seg.valueProperty().addListener((obs, oldSelection, newSelection) -> {setTiempoDescanso(); });
        descansoSeg.valueProperty().addListener((obs, oldSelection, newSelection) -> {setTiempoDescanso(); });
        
        descansoCircuitos10Min.valueProperty().addListener((obs, oldSelection, newSelection) -> {setTiempoCircuitos(); });
        descansoCircuitosMin.valueProperty().addListener((obs, oldSelection, newSelection) -> {setTiempoCircuitos(); });
        descansoCircuitos10Seg.valueProperty().addListener((obs, oldSelection, newSelection) -> {setTiempoCircuitos(); });
        descansoCircuitosSeg.valueProperty().addListener((obs, oldSelection, newSelection) -> {setTiempoCircuitos(); });
    }

    private void setTiempoTrabajo() {
        tiempoTrabajo = LocalTime.of(0, trabajo10Min.getValue()*10 + trabajoMin.getValue(), trabajo10Seg.getValue()*10 + trabajoSeg.getValue());
        if(tiempoTrabajo.getSecond() < 10 && tiempoTrabajo.getMinute() < 10)
            textTrabajo.setText("0" + tiempoTrabajo.getMinute() + ":0" + tiempoTrabajo.getSecond());
        else if(tiempoTrabajo.getSecond() < 10 && tiempoTrabajo.getMinute() >= 10)
            textTrabajo.setText(tiempoTrabajo.getMinute() + ":0" + tiempoTrabajo.getSecond());
        else if(tiempoTrabajo.getSecond() >= 10 && tiempoTrabajo.getMinute() < 10)
            textTrabajo.setText("0" + tiempoTrabajo.getMinute() + ":" + tiempoTrabajo.getSecond());
        else
            textTrabajo.setText(tiempoTrabajo.getMinute() + ":" + tiempoTrabajo.getSecond());
        if(tiempoTrabajo.compareTo(LocalTime.of(0, 0, 0)) == 0){
            empezar.setDisable(true);
        } else{
            empezar.setDisable(false);
        }
    }

    private void setTiempoDescanso() {
        tiempoDescanso = LocalTime.of(0, descanso10Min.getValue()*10 + descansoMin.getValue(), descanso10Seg.getValue()*10 + descansoSeg.getValue());
        
        if(tiempoDescanso.getSecond() < 10 && tiempoDescanso.getMinute() < 10)
            textDescanso.setText("0" + tiempoDescanso.getMinute() + ":0" + tiempoDescanso.getSecond());
        else if(tiempoDescanso.getSecond() < 10 && tiempoDescanso.getMinute() >= 10)
            textDescanso.setText(tiempoDescanso.getMinute() + ":0" + tiempoDescanso.getSecond());
        else if(tiempoDescanso.getSecond() >= 10 && tiempoDescanso.getMinute() < 10)
            textDescanso.setText("0" + tiempoDescanso.getMinute() + ":" + tiempoDescanso.getSecond());
        else
            textDescanso.setText(tiempoDescanso.getMinute() + ":" + tiempoDescanso.getSecond());
    }

    private void setTiempoCircuitos() {
        tiempoCircuitos = LocalTime.of(0, descansoCircuitos10Min.getValue()*10 + descansoCircuitosMin.getValue(),
                descansoCircuitos10Seg.getValue()*10 + descansoCircuitosSeg.getValue());
        
        if(tiempoCircuitos.getSecond() < 10 && tiempoCircuitos.getMinute() < 10)
            textCircuitos.setText("0" + tiempoCircuitos.getMinute() + ":0" + tiempoCircuitos.getSecond());
        else if(tiempoCircuitos.getSecond() < 10 && tiempoCircuitos.getMinute() >= 10)
            textCircuitos.setText(tiempoCircuitos.getMinute() + ":0" + tiempoCircuitos.getSecond());
        else if(tiempoCircuitos.getSecond() >= 10 && tiempoCircuitos.getMinute() < 10)
            textCircuitos.setText("0" + tiempoCircuitos.getMinute() + ":" + tiempoCircuitos.getSecond());
        else
            textCircuitos.setText(tiempoCircuitos.getMinute() + ":" + tiempoCircuitos.getSecond());
    }

    public ReadOnlyObjectProperty<Sesion> currentSesionProperty() {
        return sesionPropoerty.getReadOnlyProperty() ;
    }

    private void defaultValuesSpinners() {
        numeroCircuitos.getValueFactory().setValue(sesion.getN_circuitos());
        numeroDescansos.getValueFactory().setValue(sesion.getN_ejercicios());
        
        Long minutos = sesion.getT_ejercicio().toMinutes();
        Long segundos = sesion.getT_ejercicio().minusMinutes(minutos).getSeconds();
        trabajo10Min.getValueFactory().setValue(Math.toIntExact(minutos/10));
        trabajoMin.getValueFactory().setValue(Math.toIntExact(minutos%10));
        trabajo10Seg.getValueFactory().setValue(Math.toIntExact(segundos/10));
        trabajoSeg.getValueFactory().setValue(Math.toIntExact(segundos%10));
        
        minutos = sesion.getDescanso_ejercicio().toMinutes();
        segundos = sesion.getDescanso_ejercicio().minusMinutes(minutos).getSeconds();
        descanso10Min.getValueFactory().setValue(Math.toIntExact(minutos/10));
        descansoMin.getValueFactory().setValue(Math.toIntExact(minutos%10));
        descanso10Seg.getValueFactory().setValue(Math.toIntExact(segundos/10));
        descansoSeg.getValueFactory().setValue(Math.toIntExact(segundos%10));
        
        minutos = sesion.getDescanso_circuito().toMinutes();
        segundos = sesion.getDescanso_circuito().minusMinutes(minutos).getSeconds();
        descansoCircuitos10Min.getValueFactory().setValue(Math.toIntExact(minutos/10));
        descansoCircuitosMin.getValueFactory().setValue(Math.toIntExact(minutos%10));
        descansoCircuitos10Seg.getValueFactory().setValue(Math.toIntExact(segundos/10));
        descansoCircuitosSeg.getValueFactory().setValue(Math.toIntExact(segundos%10));
    }
}
