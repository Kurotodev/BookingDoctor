package org.example.medico;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MedicoTableModel extends AbstractTableModel {
    private List<Medico> medicos;
    private String[] columnNames = {"Cédula", "Nombre", "Apellidos", "Especialidad", "Teléfono", "Email", "Dirección", "Fecha Nacimiento"};

    public MedicoTableModel(List<Medico> medicos) {
        this.medicos = medicos;
    }

    @Override
    public int getRowCount() {
        return medicos.size();
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
        Medico medico = medicos.get(rowIndex);
        switch (columnIndex) {
            case 0: return medico.getCedula();
            case 1: return medico.getNombre();
            case 2: return medico.getApellidos();
            case 3: return medico.getEspecialidad();
            case 4: return medico.getTelefono();
            case 5: return medico.getEmail();
            case 6: return medico.getDireccion();
            case 7: return medico.getFechaNacimiento();
            default: return null;
        }
    }
}
