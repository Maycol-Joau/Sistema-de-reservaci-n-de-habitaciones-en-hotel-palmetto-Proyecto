

package hotel;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class conexion {
    Connection cn;
    PreparedStatement ps;
    ResultSet sr;
    
    // Constructor para establecer la conexión con la base de datos
    public conexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelp", "root", "");
            System.out.println("Se conecto a la base de datos.");
        } catch (Exception e) {
            System.out.println("Error al conectar la base de datos"+e.getMessage());
        }
    }
    // Método para registrar una reserva en la base de datos
    public int conexionHotel(String sucursal, String huesped, String habitacion, String entrada, String salida) {
        int res = 0;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("YY-MM-DD");
            Date entradaDate = dateFormat.parse(entrada);
            Date salidaDate = dateFormat.parse(salida);
            //Preparamos la consulta SQL para insertar la reserva
            ps = cn.prepareStatement("INSERT INTO reserva (sucursal, huesped, habitacion, entrada, salida) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, sucursal);
            ps.setString(2, huesped);
            ps.setString(3, habitacion);
            // Establecemos las fechas de entrada y salida como parámetros en la consulta SQL
            ps.setTimestamp(4, new Timestamp(entradaDate.getTime()));
            ps.setTimestamp(5, new Timestamp(salidaDate.getTime()));
            //Ejecutamos la consulta SQL
            res = ps.executeUpdate();
            System.out.println("La reserva fue registrada correctamente");
        } catch (Exception e) {
            System.out.println("Hubo un error en el registro: " + e.getMessage());
        }
        return res;
    }
}