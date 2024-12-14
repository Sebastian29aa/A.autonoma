/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueaderosistema;

/**
 *
 * @author Emerson
 */
public class Vehiculo {
    public int id;
    public String placa;
    public String marca;
    public String modelo;
    public String color;
    public String horaIngreso;

    public Vehiculo(int id, String placa, String marca, String modelo, String color, String horaIngreso) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.horaIngreso = horaIngreso;
    }
}