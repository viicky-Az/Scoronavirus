package edu.cecar.controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 *Clase que controla las consultas de base de datos
 * 
 */
public class BaseDatos {
    
    public void guardarCorreo(String correo) {
        try {
            ConectarMySQL.getConectarMySQL("127.0.0.1", "covid", "root", "");

            PreparedStatement ps = ConectarMySQL.getConexion().prepareStatement("INSERT INTO correos values(null,?)");

            ps.setString(1, correo);
            ps.execute();
            ps.close();

            JOptionPane.showMessageDialog(null, "E-mail guardado de forma exitosa");

        }catch (SQLIntegrityConstraintViolationException e){
            JOptionPane.showMessageDialog(null, "E-mail ya se encuentra registrado");
        }catch (Exception ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminarCorreo(String correo) {
        try {
            ConectarMySQL.getConectarMySQL("127.0.0.1", "covid", "root", "");

            PreparedStatement ps = ConectarMySQL.getConexion().prepareStatement("DELETE FROM correos WHERE correo ='"+correo+"'");
            
            ps.executeUpdate();
            ps.close();
         
            JOptionPane.showMessageDialog(null, "E-mail eliminado de forma exitosa");

        } catch (Exception ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<String> consultarCorreo() {
        try {
            
            ConectarMySQL.getConectarMySQL("127.0.0.1", "covid", "root", "");

            PreparedStatement ps = ConectarMySQL.getConexion().prepareStatement("SELECT * FROM correos");
            ResultSet rs = ps.executeQuery();
            
            List<String> correos = new ArrayList();

            while (rs.next()) {
                String correo = rs.getString("correo");
                correos.add(correo);
            }
                       
            ps.close();
            
            return correos;
        } catch (Exception ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
