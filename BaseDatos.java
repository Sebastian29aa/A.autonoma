/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueaderosistema;

/**
 *
 * @author Emerson
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDatos {
    public Connection conectar() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:parqueadero.db");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }

    public boolean registrarVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO Vehiculos (placa, marca, modelo, color, horaIngreso) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vehiculo.placa);
            pstmt.setString(2, vehiculo.marca);
            pstmt.setString(3, vehiculo.modelo);
            pstmt.setString(4, vehiculo.color);
            pstmt.setString(5, vehiculo.horaIngreso);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar: " + e.getMessage());
            return false;
        }
    }

public List<Vehiculo> consultarVehiculos() {
    List<Vehiculo> vehiculos = new ArrayList<>();
    String sql = "SELECT * FROM Vehiculos";

    try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            Vehiculo vehiculo = new Vehiculo(
                rs.getInt("id"),
                rs.getString("placa"),
                rs.getString("marca"),
                rs.getString("modelo"),
                rs.getString("color"),
                rs.getString("horaIngreso")
            );
            vehiculos.add(vehiculo);
            System.out.println("Vehículo encontrado: " + vehiculo.placa);
        }
    } catch (SQLException e) {
        System.out.println("Error al consultar: " + e.getMessage());
    }

    System.out.println("Total de vehículos consultados: " + vehiculos.size());
    return vehiculos;
}
public boolean editarVehiculo(Vehiculo vehiculo) {
    String sql = "UPDATE Vehiculos SET placa = ?, marca = ?, modelo = ?, color = ?, horaIngreso = ? WHERE id = ?";
    try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, vehiculo.placa);
        pstmt.setString(2, vehiculo.marca);
        pstmt.setString(3, vehiculo.modelo);
        pstmt.setString(4, vehiculo.color);
        pstmt.setString(5, vehiculo.horaIngreso);
        pstmt.setInt(6, vehiculo.id);
        int filasActualizadas = pstmt.executeUpdate();
        return filasActualizadas > 0;
    } catch (SQLException e) {
        System.out.println("Error al editar vehículo: " + e.getMessage());
        return false;
    }
}
}