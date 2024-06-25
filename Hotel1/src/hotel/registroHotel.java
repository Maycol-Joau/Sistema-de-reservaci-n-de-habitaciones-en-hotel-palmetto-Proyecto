
package hotel;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class registroHotel {
    Connection cn;
    PreparedStatement ps;
    ResultSet sr;
    // Constructor para establecer la conexión con la base de datos
    public registroHotel(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelp", "root", "");
            System.out.println("Se conecto a la base de datos.");
        } catch (Exception e) {
            System.out.println("Error al conectar la base de datos"+e.getMessage());
        }
    }
    // Método para registrar un cliente en la base de datos
    public int RCliente(String nombre,String apellido,String telefono,String correo,String clave){
        int res = 0;
        try {
            //Preparamos la consulta SQL para insertar los datos del cliente
            ps = cn.prepareStatement("INSERT INTO cliente(nombre,apellido,telefono,correo,clave) VALUES (?,?,?,?,?)");
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, telefono);
            ps.setString(4, correo);
            ps.setString(5, clave);
            res = ps.executeUpdate();
                System.out.println("El usuario fue registrado correctamente");
        } catch (Exception e) {
            System.out.println("Hubo un error en el registro: " + e.getMessage());
        }
        return res;
    }
    // Método para registrar una tarjeta en la base de datos
    public int Rtarjeta(String tarjeta,String vencimiento,String codSeguridad){
        int res = 0;
        try {
           // Creamos un SimpleDateFormat para el formato de fecha de entrada "dd/MM/yy"
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            // Convertimos la fecha de vencimiento ingresada en formato String a un objeto Date
            Date vencimientoDate = dateFormat.parse(vencimiento);
            // Creamos un SimpleDateFormat para el formato de fecha de salida "yyyy-MM-dd"
            SimpleDateFormat dateFormatBd = new SimpleDateFormat("yyyy-MM-dd");
            // Formateamos la fecha de vencimiento al formato deseado "yyyy-MM-dd"
            String vencimientoBd = dateFormatBd.format(vencimientoDate);
            //Preparamos la consulta SQL para insertar los datos de la tarjeta a la base de datos
            ps = cn.prepareStatement("INSERT INTO tarjeta(tarjeta,vencimiento,codSeguridad) VALUES (?,?,?)");
            ps.setString(1, tarjeta);
            ps.setString(2, vencimientoBd);
            ps.setString(3, codSeguridad);
            res = ps.executeUpdate();
                System.out.println("La terjeta fue registrado correctamente");
        }catch (Exception e) {
            System.out.println("Hubo un error en el registro de la tarjeta: " + e.getMessage());
        }
        return res;
    }    
}
