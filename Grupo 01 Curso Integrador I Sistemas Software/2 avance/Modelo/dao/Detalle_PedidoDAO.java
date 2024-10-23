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
import Modelo.dto.Detalle_Pedido;
import java.sql.*;

public class Detalle_PedidoDAO {
    private Connection cnx;

    public Detalle_PedidoDAO() {
        cnx = new DBConexion().getConnection();
    }

    public Detalle_Pedido get(int id) {
        Detalle_Pedido detallePedido = null;
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM detalle_pedido WHERE detalle_id = ?";
        try {
            ps = cnx.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                detallePedido = new Detalle_Pedido(
                    rs.getInt("detalle_id"),
                    rs.getInt("pedido_id"),
                    rs.getInt("flor_id"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio")
                );
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return detallePedido;
    }
}

