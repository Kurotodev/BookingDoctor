package org.example.cita;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CitaAdmin extends JInternalFrame {
    private List<Cita> citas;
    private JTable citaTable;
    private CitaTableModel tableModel;
    private static final String FILE_NAME = "citas.xml";

    public CitaAdmin() {
        setTitle("Administración de Citas");
        setSize(800, 600);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);

        citas = new ArrayList<>();
        loadCitas();

        tableModel = new CitaTableModel(citas);
        citaTable = new JTable(tableModel);

        add(new JScrollPane(citaTable), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton addButton = new JButton("Agregar");
        JButton modifyButton = new JButton("Modificar");
        JButton deleteButton = new JButton("Eliminar");

        addButton.addActionListener(e -> addCita());
        modifyButton.addActionListener(e -> modifyCita());
        deleteButton.addActionListener(e -> deleteCita());

        panel.add(addButton);
        panel.add(modifyButton);
        panel.add(deleteButton);

        add(panel, BorderLayout.SOUTH);
    }

    private void loadCitas() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                citas = (List<Cita>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveCitas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(citas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



     private void modifyCita() {
        int selectedRow = citaTable.getSelectedRow();
        if (selectedRow >= 0) {
            Cita cita = citas.get(selectedRow);
            Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
            CitaForm form = new CitaForm(parentFrame, cita);
            form.setVisible(true);
            if (form.isSucceeded()) {
                tableModel.fireTableDataChanged();
                saveCitas();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un paciente para modificar.");
        }
    }
    private void deleteCita() {
        int selectedRow = citaTable.getSelectedRow();
        if (selectedRow >= 0) {
            citas.remove(selectedRow);
            tableModel.fireTableDataChanged();
            saveCitas();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una cita para eliminar.");
        }
    }


    private void addCita() {
        Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
        CitaForm form = new CitaForm(parentFrame, null);
        form.setVisible(true);
        if (form.isSucceeded()) {
            citas.add(form.getCita());
            tableModel.fireTableDataChanged();
            saveCitas();
        }
    }


}
