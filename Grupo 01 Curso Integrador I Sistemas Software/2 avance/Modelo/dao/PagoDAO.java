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
import Modelo.dto.Pago;
import java.sql.*;

public class PagoDAO {
    private Connection cnx;

    public PagoDAO() {
        cnx = new DBConexion().getConnection();
    }

    public Pago get(int id) {
        Pago pago = null;
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM pago WHERE pago_id = ?";
        try {
            ps = cnx.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                pago = new Pago(
                    rs.getInt("pago_id"),
                    rs.getInt("user_id"),
                    rs.getString("nombre_completo"),
                    rs.getString("correo"),
                    rs.getString("telefono"),
                    rs.getString("direccion_envio"),
                    rs.getString("ciudad"),
                    rs.getString("estado"),
                    rs.getString("codigo_postal"),
                    rs.getString("numero_tarjeta"),
                    rs.getString("fecha_expiracion"),
                    rs.getString("cvv"),
                    rs.getDouble("monto_total")
                );
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pago;
    }
}
