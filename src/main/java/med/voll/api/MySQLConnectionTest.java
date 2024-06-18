package med.voll.api;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLConnectionTest {

    public static void main(String[] args) {
        // Datos de conexión
        String jdbcUrl = "jdbc:mysql://localhost:3306/vollmed_api"; // Cambia "tu_basededatos" por el nombre de tu base de datos
        String username = "root"; // Cambia si tu usuario es diferente
        String password = "MSQRMiguel-2386"; // Cambia por tu contraseña

        try {
            // Establecer la conexión
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Verificar si la conexión es exitosa
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos MySQL!");

                // Crear una declaración
                Statement statement = connection.createStatement();

                // Ejecutar una consulta simple
                String query = "SELECT VERSION()";
                ResultSet resultSet = statement.executeQuery(query);

                // Imprimir el resultado de la consulta
                if (resultSet.next()) {
                    System.out.println("Versión de MySQL: " + resultSet.getString(1));
                }

                // Cerrar la conexión
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
