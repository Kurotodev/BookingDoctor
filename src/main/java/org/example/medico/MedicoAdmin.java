package org.example.medico;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoAdmin extends JInternalFrame {
    private List<Medico> medicos;
    private JTable medicoTable;
    private MedicoTableModel tableModel;
    private static final String FILE_NAME = "medicos.xml";

    public MedicoAdmin() {
        setTitle("Administración de Médicos");
        setSize(800, 600);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);

        medicos = new ArrayList<>();
        loadMedicos();

        tableModel = new MedicoTableModel(medicos);
        medicoTable = new JTable(tableModel);

        add(new JScrollPane(medicoTable), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton addButton = new JButton("Agregar");
        JButton modifyButton = new JButton("Modificar");
        JButton deleteButton = new JButton("Eliminar");

        addButton.addActionListener(e -> addMedico());
        modifyButton.addActionListener(e -> modifyMedico());
        deleteButton.addActionListener(e -> deleteMedico());

        panel.add(addButton);
        panel.add(modifyButton);
        panel.add(deleteButton);

        add(panel, BorderLayout.SOUTH);
    }

    private void loadMedicos() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                medicos = (List<Medico>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveMedicos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(medicos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addMedico() {
        Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
        MedicoForm form = new MedicoForm(parentFrame, null);
        form.setVisible(true);
        if (form.isSucceeded()) {
            medicos.add(form.getMedico());
            tableModel.fireTableDataChanged();
            saveMedicos();
        }
    }

    private void modifyMedico() {
        int selectedRow = medicoTable.getSelectedRow();
        if (selectedRow >= 0) {
            Medico medico = medicos.get(selectedRow);
            Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
            MedicoForm form = new MedicoForm(parentFrame, medico);
            form.setVisible(true);
            if (form.isSucceeded()) {
                tableModel.fireTableDataChanged();
                saveMedicos();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un médico para modificar.");
        }
    }

    private void deleteMedico() {
        int selectedRow = medicoTable.getSelectedRow();
        if (selectedRow >= 0) {
            medicos.remove(selectedRow);
            tableModel.fireTableDataChanged();
            saveMedicos();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un médico para eliminar.");
        }
    }
}
