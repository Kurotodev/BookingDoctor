package org.example.paciente;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteAdmin extends JInternalFrame {
    private List<Paciente> pacientes;
    private JTable pacienteTable;
    private PacienteTableModel tableModel;
    private static final String FILE_NAME = "pacientes.xml";

    public PacienteAdmin() {
        setTitle("Administración de Pacientes");
        setSize(800, 600);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);

        pacientes = new ArrayList<>();
        loadPacientes();

        tableModel = new PacienteTableModel(pacientes);
        pacienteTable = new JTable(tableModel);

        add(new JScrollPane(pacienteTable), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton addButton = new JButton("Agregar");
        JButton modifyButton = new JButton("Modificar");
        JButton deleteButton = new JButton("Eliminar");

        addButton.addActionListener(e -> addPaciente());
        modifyButton.addActionListener(e -> modifyPaciente());
        deleteButton.addActionListener(e -> deletePaciente());

        panel.add(addButton);
        panel.add(modifyButton);
        panel.add(deleteButton);

        add(panel, BorderLayout.SOUTH);
    }

    private void loadPacientes() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                pacientes = (List<Paciente>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void savePacientes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(pacientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addPaciente() {
        Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
        PacienteForm form = new PacienteForm(parentFrame, null);
        form.setVisible(true);
        if (form.isSucceeded()) {
            pacientes.add(form.getPaciente());
            tableModel.fireTableDataChanged();
            savePacientes();
        }
    }

    private void modifyPaciente() {
        int selectedRow = pacienteTable.getSelectedRow();
        if (selectedRow >= 0) {
            Paciente paciente = pacientes.get(selectedRow);
            Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
            PacienteForm form = new PacienteForm(parentFrame, paciente);
            form.setVisible(true);
            if (form.isSucceeded()) {
                tableModel.fireTableDataChanged();
                savePacientes();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un paciente para modificar.");
        }
    }

    private void deletePaciente() {
        int selectedRow = pacienteTable.getSelectedRow();
        if (selectedRow >= 0) {
            pacientes.remove(selectedRow);
            tableModel.fireTableDataChanged();
            savePacientes();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un paciente para eliminar.");
        }
    }
}
