package org.example.cita;

import java.io.Serializable;

public class Cita implements Serializable {
    private static int contadorCitas = 1;
    private int numeroCita;
    private String fecha;
    private String hora;
    private String cedulaPaciente;
    private String nombreDoctor;

    public Cita(int tfnumeroCita, String fecha, String hora, String cedulaPaciente, String nombreDoctor) {
        this.numeroCita = contadorCitas++;
        this.fecha = fecha;
        this.hora = hora;
        this.cedulaPaciente = cedulaPaciente;
        this.nombreDoctor = nombreDoctor;
    }

    public int getNumeroCita() {
        return numeroCita;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public void setHora(String Hora) {
        this.hora = Hora;
    }
    public void setcedulaPacienta(String cedula) {
        this.cedulaPaciente = cedula;
    }
    public void setnombreDoctor(String doctor) {
        this.nombreDoctor = doctor;
    }
    public String getFecha() {
        return fecha;
    }
    

    public String getHora() {
        return hora;
    }

    public String getCedulaPaciente() {
        return cedulaPaciente;
    }

    public String getNombreDoctor() {
        return nombreDoctor;
    }

}
