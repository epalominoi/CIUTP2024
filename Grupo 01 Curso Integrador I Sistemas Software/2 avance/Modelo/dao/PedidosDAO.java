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
import Modelo.dto.Pedidos;
import java.sql.*;

public class PedidosDAO {
    private Connection cnx;

    public PedidosDAO() {
        cnx = new DBConexion().getConnection();
    }

    public Pedidos get(int id) {
        Pedidos pedido = null;
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM pedidos WHERE pedido_id = ?";
        try {
            ps = cnx.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                pedido = new Pedidos(
                    rs.getInt("pedido_id"),
                    rs.getInt("user_id"),
                    rs.getDouble("total"),
                    rs.getString("estado")
                );
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pedido;
    }
}

