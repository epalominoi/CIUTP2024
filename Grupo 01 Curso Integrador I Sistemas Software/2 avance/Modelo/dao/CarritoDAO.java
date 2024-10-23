/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.dao;

/**
 *
 * @author Joaquin
 */



import Servicio.DBConexion;
import Modelo.dto.Carrito;
import java.sql.*;

public class CarritoDAO {
    private Connection cnx;

    public CarritoDAO() {
        cnx = new DBConexion().getConnection();
    }

    public Carrito get(int id) {
        Carrito c = null;
        PreparedStatement ps;
        ResultSet rs;
        String cadSQL = "SELECT * FROM carrito WHERE idCarrito = ?";
        try {
            ps = cnx.prepareStatement(cadSQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                c = new Carrito(
                    rs.getInt("idCarrito"),
                    rs.getInt("user_id"),
                    rs.getInt("flor_id"),
                    rs.getInt("cantidad")
                );
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }
    
    public void agregarAlCarrito(int userId, int florId, int cantidad) {
    String cadSQL = "INSERT INTO carrito (user_id, flor_id, cantidad) VALUES (?, ?, ?)";
    try (PreparedStatement ps = cnx.prepareStatement(cadSQL)) {
        ps.setInt(1, userId);
        ps.setInt(2, florId);
        ps.setInt(3, cantidad);
        ps.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

}

