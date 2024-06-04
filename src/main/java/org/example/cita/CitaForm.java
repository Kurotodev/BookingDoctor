package org.example.cita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

public class CitaForm extends JDialog {
    private static int contadorCitas = 1;
    private int numeroCita;
    private JTextField tfFecha;
    private JTextField tfHora;
    private JTextField tfCedulaPaciente;
    private JTextField tfNombreDoctor;

    private boolean succeeded;
    private Cita cita;

    public CitaForm(Frame parent, Cita cita) {
        super(parent, "Formulario de Cita", true);
        this.cita = cita;

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        if (cita != null) {
            this.numeroCita = cita.getNumeroCita();
        } else {
            this.numeroCita = contadorCitas++;
        }



        panel.add(new JLabel("Fecha de la cita:"));
        DatePicker datePicker = new DatePicker();
        if (cita != null) {
            datePicker.setDate(LocalDate.parse(cita.getFecha(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        panel.add(datePicker);

        panel.add(new JLabel("Hora de la cita:"));
        TimePicker timePicker = new TimePicker();
        if (cita != null) {
            timePicker.setTime(LocalTime.parse(cita.getHora(), DateTimeFormatter.ofPattern("HH:mm")));
        }
        panel.add(timePicker);

        panel.add(new JLabel("Cédula Paciente:"));
        tfCedulaPaciente = new JTextField();
        if (cita != null) tfCedulaPaciente.setText(cita.getCedulaPaciente());
        panel.add(tfCedulaPaciente);

        panel.add(new JLabel("Nombre del Médico:"));
        tfNombreDoctor = new JTextField();
        if (cita != null) tfNombreDoctor.setText(cita.getNombreDoctor());
        panel.add(tfNombreDoctor);

        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");

        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LocalDate selectedDate = datePicker.getDate();
                LocalTime selectedTime = timePicker.getTime();
                LocalDateTime selectedDateTime = LocalDateTime.of(selectedDate, selectedTime);

                if (selectedDateTime.isAfter(LocalDateTime.now())) {
                    if (CitaForm.this.cita == null) {
                        CitaForm.this.cita = new Cita(
                                numeroCita,
                                selectedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                selectedTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                                tfCedulaPaciente.getText(),
                                tfNombreDoctor.getText()
                        );
                    } else {
                        CitaForm.this.cita.setFecha(selectedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                        CitaForm.this.cita.setHora(selectedTime.format(DateTimeFormatter.ofPattern("HH:mm")));
                        CitaForm.this.cita.setcedulaPacienta(tfCedulaPaciente.getText());
                        CitaForm.this.cita.setnombreDoctor(tfNombreDoctor.getText());
                    }
                    succeeded = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(CitaForm.this, "La fecha y hora seleccionadas ya han pasado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                succeeded = false;
                dispose();
            }
        });

        JPanel bp = new JPanel();
        bp.add(btnOk);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public Cita getCita() {
        return cita;
    }
}
