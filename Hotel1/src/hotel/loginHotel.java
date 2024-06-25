
package hotel;

import java.sql.*;

public class loginHotel {
    Connection cn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;  
    // Constructor para establecer la conexión con la base de datos
    public loginHotel() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelp", "root", "");
        } catch (Exception e) {
            System.out.println("Error al conectar la base de datos" + e.getMessage());
        }
    }
    // Método para verificar si los datos ingresados del usuario en el inicio de sesión son válidos
    public boolean verificarDatos(String correo, String clave) {
        try {
            String query = "SELECT * FROM cliente WHERE correo=? AND clave=? ";
            ps = cn.prepareStatement(query);
            ps.setString(1, correo);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            return rs.next(); 
        } catch (SQLException  e) {
            System.out.println("Error al verificar los datos: " + e.getMessage());
            return false;
        } finally {
            cerrarConexiones(ps, rs);
        }
    }
    // Método para verificar si el correo del usuario está registrado en la base de datos
        public boolean verificarCorreo(String correo) {
        try {
            String query = "SELECT COUNT(*) FROM cliente WHERE correo=?";
            ps = cn.prepareStatement(query);
            ps.setString(1, correo);
            rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count > 0;
        } catch (SQLException  e) {
            System.out.println("Error al verificar el correo: " + e.getMessage());
            return false;
        } finally {
            cerrarConexiones(ps, rs);   
        }
    }
        // Método para restaurar la contraseña del usuario
        public boolean restaurarContraseña(String correo, String contraseña) {
            try {
                String query = "UPDATE cliente SET clave=? WHERE correo=?";
                ps = cn.prepareStatement(query);
                ps.setString(1, contraseña);
                ps.setString(2, correo);
                int filasActualizadas = ps.executeUpdate();
                return filasActualizadas > 0;
            } catch (SQLException e) {
                System.out.println("Error al restaurar la contraseña: " + e.getMessage());
                return false;
            } finally {
                try {
                    if (ps != null) ps.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el PreparedStatement: " + e.getMessage());
        }
    }
}
        // Método para verificar si el usuario (nombre y apellido) existe en la base de datos
        public boolean verificarUsuario(String nombre, String apellido) {
            try {
                String query = "SELECT * FROM cliente WHERE nombre=? AND apellido=? ";
                ps = cn.prepareStatement(query);
                ps.setString(1, nombre);
                ps.setString(2, apellido);
                rs = ps.executeQuery();
                return rs.next(); 
            } catch (SQLException  e) {
                System.out.println("Error al verificar los datos del usuario: " + e.getMessage());
                return false;
            } finally {
                cerrarConexiones(ps, rs);
            }
        }
        // Método para verificar si la tarjeta está registrada en la base de datos
        public boolean tarjeta(String tarjeta, String vencimiento, String codSeguridad) {
            try {
                String query = "SELECT * FROM tarjeta WHERE tarjeta=? AND vencimiento=? AND codSeguridad=?";
                ps = cn.prepareStatement(query);
                ps.setString(1, tarjeta);
                ps.setString(2, vencimiento);
                ps.setString(3, codSeguridad);
                rs = ps.executeQuery();
                return rs.next(); 
            } catch (SQLException  e) {
                System.out.println("Error al verificar los datos del usuario: " + e.getMessage());
                return false;
            } finally {
                cerrarConexiones(ps, rs);
            }
        }
        // Método para cerrar conexiones con la base de datos
        private void cerrarConexiones(PreparedStatement ps, ResultSet rs) {
            try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            } catch (SQLException e) {
            System.out.println("Error al cerrar conexiones: " + e.getMessage());
        }
    }
}