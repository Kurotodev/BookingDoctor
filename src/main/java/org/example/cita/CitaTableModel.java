package org.example.cita;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CitaTableModel extends AbstractTableModel {
    private List<Cita> citas;
    private String[] columnNames = {"Fecha", "Hora", "Cédula Paciente", "Nombre Doctor"};

    public CitaTableModel(List<Cita> citas) {
        this.citas = citas;
    }

    @Override
    public int getRowCount() {
        return citas.size();
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
        Cita cita = citas.get(rowIndex);
        switch (columnIndex) {
            case 0: return cita.getFecha();
            case 1: return cita.getHora();
            case 2: return cita.getCedulaPaciente();
            case 3: return cita.getNombreDoctor();
            default: return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
