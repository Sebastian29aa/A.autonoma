/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueaderosistema;

/**
 *
 * @author Emerson
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ParqueaderoApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Parqueadero");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        BaseDatos db = new BaseDatos();

        // Panel de formulario
        JPanel panelForm = new JPanel(new GridLayout(6, 2));
        JTextField txtPlaca = new JTextField();
        JTextField txtMarca = new JTextField();
        JTextField txtModelo = new JTextField();
        JTextField txtColor = new JTextField();
        JTextField txtHoraIngreso = new JTextField();
        JLabel lblId = new JLabel("ID (para editar):");
        JTextField txtId = new JTextField();
        
        panelForm.add(lblId);
        panelForm.add(txtId);
        panelForm.add(new JLabel("Placa:"));
        panelForm.add(txtPlaca);
        panelForm.add(new JLabel("Marca:"));
        panelForm.add(txtMarca);
        panelForm.add(new JLabel("Modelo:"));
        panelForm.add(txtModelo);
        panelForm.add(new JLabel("Color:"));
        panelForm.add(txtColor);
        panelForm.add(new JLabel("Hora Ingreso:"));
        panelForm.add(txtHoraIngreso);

        // Panel de botones
        JPanel panelButtons = new JPanel();
        JButton btnRegistrar = new JButton("Registrar Vehículo");
        JButton btnConsultar = new JButton("Consultar Vehículos");
        JButton btnSalir = new JButton("Salir");
        JButton btnEditar = new JButton("Actualizar Vehículo");
        
        panelButtons.add(btnEditar);

        panelButtons.add(btnRegistrar);
        panelButtons.add(btnConsultar);
        panelButtons.add(btnSalir);

        // Área de resultados
        JTextArea txtResultados = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(txtResultados);

        // Agregar acciones a los botones
        btnRegistrar.addActionListener(e -> {
            Vehiculo vehiculo = new Vehiculo(0, txtPlaca.getText(), txtMarca.getText(), txtModelo.getText(), txtColor.getText(), txtHoraIngreso.getText());
            if (db.registrarVehiculo(vehiculo)) {
                txtResultados.setText("Vehículo registrado con éxito.");
            } else {
                txtResultados.setText("Error al registrar vehículo.");
            }
        });

        btnConsultar.addActionListener(e -> {
            List<Vehiculo> vehiculos = db.consultarVehiculos();
            txtResultados.setText("");
            for (Vehiculo v : vehiculos) {
                txtResultados.append(v.id + " - " + v.placa + " - " + v.marca + " - " + v.modelo + " - " + v.color + " - " + v.horaIngreso + "\n");
            }
        });

        
        
        btnEditar.addActionListener(e -> {
    try {
        int id = Integer.parseInt(txtId.getText());
        String placa = txtPlaca.getText();
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        String color = txtColor.getText();
        String horaIngreso = txtHoraIngreso.getText();

        Vehiculo vehiculo = new Vehiculo(id, placa, marca, modelo, color, horaIngreso);
        if (db.editarVehiculo(vehiculo)) {
            txtResultados.setText("Vehículo con ID " + id + " actualizado correctamente.");
        } else {
            txtResultados.setText("No se pudo actualizar el vehículo con ID " + id + ".");
        }
    } catch (NumberFormatException ex) {
        txtResultados.setText("ID inválido. Debe ser un número entero.");
    }
});

        
        
        
        btnSalir.addActionListener(e -> System.exit(0));

        // Agregar componentes al frame
        frame.add(panelForm, BorderLayout.NORTH);
        frame.add(panelButtons, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}