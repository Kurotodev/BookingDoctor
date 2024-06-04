package org.example.paciente;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PacienteTableModel extends AbstractTableModel {
    private List<Paciente> pacientes;
    private String[] columnNames = {"Cédula", "Nombre", "Apellidos", "Teléfono", "Email", "Dirección", "Fecha Nacimiento", "Contacto Emergencia", "Teléfono Emergencia"};

    public PacienteTableModel(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    @Override
    public int getRowCount() {
        return pacientes.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Paciente paciente = pacientes.get(rowIndex);
        switch (columnIndex) {
            case 0: return paciente.getCedula();
            case 1: return paciente.getNombre();
            case 2: return paciente.getApellidos();
            case 3: return paciente.getTelefono();
            case 4: return paciente.getEmail();
            case 5: return paciente.getDireccion();
            case 6: return paciente.getFechaNacimiento();
            case 7: return paciente.getContactoEmergencia();
            case 8: return paciente.getTelefonoEmergencia();
            default: return null;
        }
    }
}
