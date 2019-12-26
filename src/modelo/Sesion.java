/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.Duration;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author jsoler
 */
public class Sesion {

    private final IntegerProperty n_circuitos = new SimpleIntegerProperty();

    public int getN_circuitos() {
        return n_circuitos.get();
    }

    public void setN_circuitos(int value) {
        n_circuitos.set(value);
    }

    public IntegerProperty n_circuitosProperty() {
        return n_circuitos;
    }
    private final IntegerProperty n_ejercicios = new SimpleIntegerProperty();

    public int getN_ejercicios() {
        return n_ejercicios.get();
    }

    public void setN_ejercicios(int value) {
        n_ejercicios.set(value);
    }

    public IntegerProperty n_ejerciciosProperty() {
        return n_ejercicios;
    }
    private final ObjectProperty<Duration> t_ejercicio = new SimpleObjectProperty<>();

    public Duration getT_ejercicio() {
        return t_ejercicio.get();
    }

    public void setT_ejercicio(Duration value) {
        t_ejercicio.set(value);
    }

    public ObjectProperty t_ejercicioProperty() {
        return t_ejercicio;
    }
    private final ObjectProperty<Duration> descanso_ejercicio = new SimpleObjectProperty<>();

    public Duration getDescanso_ejercicio() {
        return descanso_ejercicio.get();
    }

    public void setDescanso_ejercicio(Duration value) {
        descanso_ejercicio.set(value);
    }

    public ObjectProperty descanso_ejercicioProperty() {
        return descanso_ejercicio;
    }
    private final ObjectProperty<Duration> descanso_circuito = new SimpleObjectProperty<>();

    public Duration getDescanso_circuito() {
        return descanso_circuito.get();
    }

    public void setDescanso_circuito(Duration value) {
        descanso_circuito.set(value);
    }

    public ObjectProperty descanso_circuitoProperty() {
        return descanso_circuito;
    }
    
}
