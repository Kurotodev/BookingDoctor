package org.example.paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacienteForm extends JDialog {
    private JTextField tfCedula;
    private JTextField tfNombre;
    private JTextField tfApellidos;
    private JTextField tfTelefono;
    private JTextField tfEmail;
    private JTextField tfDireccion;
    private JTextField tfFechaNacimiento;
    private JTextField tfContactoEmergencia;
    private JTextField tfTelefonoEmergencia;
    private boolean succeeded;
    private Paciente paciente;

    public PacienteForm(Frame parent, Paciente paciente) {
        super(parent, "Formulario de Paciente", true);
        this.paciente = paciente;

        JPanel panel = new JPanel(new GridLayout(10, 2, 10, 10));
        panel.add(new JLabel("Cédula:"));
        tfCedula = new JTextField();
        panel.add(tfCedula);

        panel.add(new JLabel("Nombre:"));
        tfNombre = new JTextField();
        panel.add(tfNombre);

        panel.add(new JLabel("Apellidos:"));
        tfApellidos = new JTextField();
        panel.add(tfApellidos);

        panel.add(new JLabel("Teléfono:"));
        tfTelefono = new JTextField();
        panel.add(tfTelefono);

        panel.add(new JLabel("Email:"));
        tfEmail = new JTextField();
        panel.add(tfEmail);

        panel.add(new JLabel("Dirección:"));
        tfDireccion = new JTextField();
        panel.add(tfDireccion);

        panel.add(new JLabel("Fecha de Nacimiento:"));
        tfFechaNacimiento = new JTextField();
        panel.add(tfFechaNacimiento);

        panel.add(new JLabel("Contacto de Emergencia:"));
        tfContactoEmergencia = new JTextField();
        panel.add(tfContactoEmergencia);

        panel.add(new JLabel("Teléfono de Emergencia:"));
        tfTelefonoEmergencia = new JTextField();
        panel.add(tfTelefonoEmergencia);

        if (paciente != null) {
            tfCedula.setText(paciente.getCedula());
            tfNombre.setText(paciente.getNombre());
            tfApellidos.setText(paciente.getApellidos());
            tfTelefono.setText(paciente.getTelefono());
            tfEmail.setText(paciente.getEmail());
            tfDireccion.setText(paciente.getDireccion());
            tfFechaNacimiento.setText(paciente.getFechaNacimiento());
            tfContactoEmergencia.setText(paciente.getContactoEmergencia());
            tfTelefonoEmergencia.setText(paciente.getTelefonoEmergencia());
            tfCedula.setEnabled(false);  // No permitir modificar la cédula
        }

        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");

        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (PacienteForm.this.paciente == null) {
                    PacienteForm.this.paciente = new Paciente(
                            tfCedula.getText(),
                            tfNombre.getText(),
                            tfApellidos.getText(),
                            tfTelefono.getText(),
                            tfEmail.getText(),
                            tfDireccion.getText(),
                            tfFechaNacimiento.getText(),
                            tfContactoEmergencia.getText(),
                            tfTelefonoEmergencia.getText()
                    );
                } else {
                    PacienteForm.this.paciente.setNombre(tfNombre.getText());
                    PacienteForm.this.paciente.setApellidos(tfApellidos.getText());
                    PacienteForm.this.paciente.setTelefono(tfTelefono.getText());
                    PacienteForm.this.paciente.setEmail(tfEmail.getText());
                    PacienteForm.this.paciente.setDireccion(tfDireccion.getText());
                    PacienteForm.this.paciente.setFechaNacimiento(tfFechaNacimiento.getText());
                    PacienteForm.this.paciente.setContactoEmergencia(tfContactoEmergencia.getText());
                    PacienteForm.this.paciente.setTelefonoEmergencia(tfTelefonoEmergencia.getText());
                }
                succeeded = true;
                dispose();
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

    public Paciente getPaciente() {
        return paciente;
    }
}

