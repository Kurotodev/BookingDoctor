package org.example;

import javax.swing.*;

import org.example.cita.CitaAdmin;
import org.example.paciente.*;
import org.example.medico.*;
import org.example.user.*;

public class MainFrame extends JFrame {
    private JDesktopPane desktopPane;

    public MainFrame() {
        setTitle("Sistema de Administración de Citas Médicas");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        desktopPane = new JDesktopPane();
        setContentPane(desktopPane);

        createMenuBar();

        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuPacientes = new JMenu("Pacientes");
        JMenuItem itemGestionPacientes = new JMenuItem("Gestionar Pacientes");
        itemGestionPacientes.addActionListener(e -> openPacienteModule());
        menuPacientes.add(itemGestionPacientes);

        JMenu menuMedicos = new JMenu("Médicos");
        JMenuItem itemGestionMedicos = new JMenuItem("Gestionar Médicos");
        itemGestionMedicos.addActionListener(e -> openMedicoModule());
        menuMedicos.add(itemGestionMedicos);

        JMenu menuCitas = new JMenu("Citas");
        JMenuItem itemGestionCitas = new JMenuItem("Gestionar Citas");
        itemGestionCitas.addActionListener(e -> openCitaModule());
        menuCitas.add(itemGestionCitas);

        JMenu menudeusuarios = new JMenu("Modulos");
        JMenuItem administracionUsuariosItem = new JMenuItem("Administración de Usuarios");
        administracionUsuariosItem.addActionListener(e -> abrirModuloUsuarios());
        menudeusuarios.add(administracionUsuariosItem);

        menuBar.add(menuPacientes);
        menuBar.add(menuMedicos);
        menuBar.add(menuCitas);
        menuBar.add(menudeusuarios);

        setJMenuBar(menuBar);
    }

    private void openPacienteModule() {
        PacienteAdmin pacienteAdmin = new PacienteAdmin();
        desktopPane.add(pacienteAdmin);
        pacienteAdmin.setVisible(true);
    }

    private void openMedicoModule() {
        MedicoAdmin medicoAdmin = new MedicoAdmin();
        desktopPane.add(medicoAdmin);
        medicoAdmin.setVisible(true);
    }

    private void openCitaModule() {
        CitaAdmin citaAdmin = new CitaAdmin();
        desktopPane.add(citaAdmin);
        citaAdmin.setVisible(true);
    }
    private void abrirModuloUsuarios() {
        UserAdmin userAdmin = new UserAdmin();
        desktopPane.add(userAdmin);
        userAdmin.setVisible(true);
    }

}
