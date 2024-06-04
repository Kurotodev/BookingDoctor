package org.example.medico;

import javax.swing.*;
import java.awt.*;

public class MedicoForm extends JDialog {
    private JTextField tfCedula;
    private JTextField tfNombre;
    private JTextField tfApellidos;
    private JTextField tfEspecialidad;
    private JTextField tfTelefono;
    private JTextField tfEmail;
    private JTextField tfDireccion;
    private JTextField tfFechaNacimiento;
    private boolean succeeded;
    private Medico medico;

    public MedicoForm(Frame parent, Medico medico) {
        super(parent, "Formulario de Médico", true);
        this.medico = medico;

        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10));
        panel.add(new JLabel("Cédula:"));
        tfCedula = new JTextField();
        panel.add(tfCedula);

        panel.add(new JLabel("Nombre:"));
        tfNombre = new JTextField();
        panel.add(tfNombre);

        panel.add(new JLabel("Apellidos:"));
        tfApellidos = new JTextField();
        panel.add(tfApellidos);

        panel.add(new JLabel("Especialidad:"));
        tfEspecialidad = new JTextField();
        panel.add(tfEspecialidad);

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

        if (medico != null) {
            tfCedula.setText(medico.getCedula());
            tfNombre.setText(medico.getNombre());
            tfApellidos.setText(medico.getApellidos());
            tfEspecialidad.setText(medico.getEspecialidad());
            tfTelefono.setText(medico.getTelefono());
            tfEmail.setText(medico.getEmail());
            tfDireccion.setText(medico.getDireccion());
            tfFechaNacimiento.setText(medico.getFechaNacimiento());
            tfCedula.setEnabled(false);  // No permitir modificar la cédula
        }

        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");

        btnOk.addActionListener(e -> {
            if (MedicoForm.this.medico == null) {
                MedicoForm.this.medico = new Medico(
                        tfCedula.getText(),
                        tfNombre.getText(),
                        tfApellidos.getText(),
                        tfEspecialidad.getText(),
                        tfTelefono.getText(),
                        tfEmail.getText(),
                        tfDireccion.getText(),
                        tfFechaNacimiento.getText()
                );
            } else {
                MedicoForm.this.medico.setNombre(tfNombre.getText());
                MedicoForm.this.medico.setApellidos(tfApellidos.getText());
                MedicoForm.this.medico.setEspecialidad(tfEspecialidad.getText());
                MedicoForm.this.medico.setTelefono(tfTelefono.getText());
                MedicoForm.this.medico.setEmail(tfEmail.getText());
                MedicoForm.this.medico.setDireccion(tfDireccion.getText());
                MedicoForm.this.medico.setFechaNacimiento(tfFechaNacimiento.getText());
            }
            succeeded = true;
            dispose();
        });

        btnCancel.addActionListener(e -> {
            succeeded = false;
            dispose();
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

    public Medico getMedico() {
        return medico;
    }
}
