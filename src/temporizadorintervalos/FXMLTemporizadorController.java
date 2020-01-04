/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporizadorintervalos;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import modelo.Sesion;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import resumen.FXMLResumenController;
import resumen.FXMLResumenController.Estado;
import setEjercicio.FXMLSetEjecicioController;
import static temporizadorintervalos.IntervalTimerS.EstadoSesion.*;

/**
 *
 * @author jsoler
 */
public class FXMLTemporizadorController implements Initializable {

    BooleanProperty parado = new SimpleBooleanProperty(false);
    BooleanProperty iniciado = new SimpleBooleanProperty(false);

    private IntervalTimerS servicio;
    private Sesion sesion;

    @FXML
    private Text textoTiempo;
    @FXML
    private Button iniBut;
    @FXML
    private Button reaBut;
    @FXML
    private Button parBut;
    @FXML
    private Button sigBut;
    @FXML
    private Button setEjercicio;

    private Stage primaryStage;
    @FXML
    private Text tiempoTrabajoIndicator;
    @FXML
    private Text tiempoDescansoIndicator;
    @FXML
    private Text ejercicioCounter;
    @FXML
    private Text circuitoCounter;
    @FXML
    private ProgressBar progressBar;

    private boolean def = true;
    @FXML
    private Circle circulo;
    @FXML
    private Text estadoTexto;
    @FXML
    private ImageView reanudarIcon;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sesion = new Sesion();
        sesion.setN_circuitos(2);
        sesion.setDescanso_circuito(Duration.ofSeconds(5));
        sesion.setN_ejercicios(3);
        sesion.setT_ejercicio(Duration.ofSeconds(7));
        sesion.setDescanso_ejercicio(Duration.ofSeconds(10));

        initTimer(sesion);
    }

    public void initTimer(Sesion nuevo) {
        iniciado.set(false);
        parado.set(false);
        iniBut.disableProperty().bind(iniciado);
        parBut.disableProperty().bind(Bindings.or(Bindings.not(iniciado), parado));
        reaBut.disableProperty().bind(Bindings.or(Bindings.not(iniciado), Bindings.not(parado)));
        sigBut.disableProperty().bind(Bindings.or(Bindings.not(iniciado), Bindings.not(parado)));

        sesion = nuevo;
        tiempoTrabajoIndicator.setText(LocalTime.of(0, 0, 0).plus(sesion.getT_ejercicio()).toString().substring(3));
        tiempoDescansoIndicator.setText(LocalTime.of(0, 0, 0).plus(sesion.getDescanso_ejercicio()).toString().substring(3));

        servicio = new IntervalTimerS();

        servicio.setSesionTipo(sesion);
        textoTiempo.textProperty().bind(servicio.tiempoProperty());
        ejercicioCounter.setText(String.valueOf(servicio.getEjercicioActual()) + "/" + String.valueOf(sesion.getN_ejercicios()));
        circuitoCounter.setText(String.valueOf(servicio.getCircuitoActual()) + "/" + String.valueOf(sesion.getN_circuitos()));
        circulo.setStyle("-fx-fill:  deepskyblue;");
        estadoTexto.setText("Trabajo");
        progressBar.setProgress((double) (servicio.getEjercicioActual() - 1 + sesion.getN_ejercicios() * (servicio.getCircuitoActual() - 1))
                / (double) (sesion.getN_ejercicios() * sesion.getN_circuitos()));
        progressBar.setStyle("-fx-fill:  deepskyblue;");

        servicio.tiempoProperty().addListener((obs, oldSelection, newSelection)
                -> {
            ejercicioCounter.setText(String.valueOf(servicio.getEjercicioActual()) + "/" + String.valueOf(sesion.getN_ejercicios()));
            circuitoCounter.setText(String.valueOf(servicio.getCircuitoActual()) + "/" + String.valueOf(sesion.getN_circuitos()));
            progressBar.setProgress((double) (servicio.getEjercicioActual() - 1 + sesion.getN_ejercicios() * (servicio.getCircuitoActual() - 1))
                    / (double) (sesion.getN_ejercicios() * sesion.getN_circuitos()));

            if (servicio.estadoProperty().getValue() == DESCANSO_EJERCICIO) {
                circulo.setStyle("-fx-fill: lemonchiffon;");
                estadoTexto.setText("Descanso");
            } else if (servicio.estadoProperty().getValue() == DESCANSO_CIRCUITO) {
                circulo.setStyle("-fx-fill: lemonchiffon;");
                estadoTexto.setText("Descanso entre circuitos");
            } else {
                circulo.setStyle("-fx-fill:  deepskyblue;");
                estadoTexto.setText("Trabajo");
            }
        });

        servicio.setOnSucceeded(c -> {
            if (servicio.getValue()) {
                irAlResumen(sesion, servicio.getTiempoReal());
            }
        });

        // TODO
    }

    public void initStage(Stage stage) {
        primaryStage = stage;
    }

    @FXML
    private void iniciar(ActionEvent event) {
        servicio.start();
        iniciado.set(true);
    }

    @FXML
    private void reanudar(ActionEvent event) {
        servicio.restart();
        parado.set(false);
    }

    @FXML
    private void parar(ActionEvent event) {
        servicio.cancel();
        parado.set(true);
    }

    @FXML
    private void siguiente(ActionEvent event) {
        servicio.setCambiarEstado(true);
        servicio.restart();
    }

    private void reset(ActionEvent event) {
        servicio.restaurarInicio();
    }

    @FXML
    private void irASetEjercicio(ActionEvent event) {
        try {
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/setEjercicio/FXMLSetEjecicio.fxml"));
            Parent root = (Parent) miCargador.load();

            FXMLSetEjecicioController ventana1 = miCargador.<FXMLSetEjecicioController>getController();
            ventana1.currentSesionProperty().addListener((obs, anterior, nuevo) -> {
                initTimer(nuevo);
                servicio.restaurarInicio();
            });
            if (iniciado.getValue() == true) {
                servicio.cancel();
                parado.set(true);
            }

            ventana1.initStage(primaryStage, sesion);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void irAlResumen(Sesion sesion, long tiempoReal) {
        try {
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/resumen/FXMLResumen.fxml"));
            Parent root = (Parent) miCargador.load();

            FXMLResumenController ventana1 = miCargador.<FXMLResumenController>getController();
            ventana1.currentEstadoProperty().addListener((obs, anterior, nuevo) -> {
                initTimer(sesion);
                servicio.restaurarInicio();
                if (nuevo == Estado.NUEVO_EJERCICIO) {
                    irASetEjercicio(null);
                }

            });
            servicio.cancel();
            parado.set(true);
            ventana1.initStage(primaryStage, sesion, tiempoReal);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
