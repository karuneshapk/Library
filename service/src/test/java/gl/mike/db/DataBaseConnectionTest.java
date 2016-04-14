package gl.mike.db;

import org.junit.Assert;
import org.junit.Test;

import java.sql.*;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by user on 12.04.2016.
 */
public class DataBaseConnectionTest {

    Connection connection = null;

    private static final String DRIVER_CLASS_NAME_TEST = "com.mysql.jdbc.Driver";
    private static final String DB_CONN_URL_TEST = "jdbc:mysql://localhost:3306/library";
    private static final String DB_CONN_NAME_TEST = "root";
    private static final String DB_CONN_PASS_TEST = "1234";

    @Test
    public void connectionTest() {

        //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений
        String url = DB_CONN_URL_TEST;
        //Имя пользователя БД
        String name = DB_CONN_NAME_TEST;
        //Пароль
        String password = DB_CONN_PASS_TEST;
        try {
            //Загружаем драйвер
            Class.forName(DRIVER_CLASS_NAME_TEST);
            System.out.println("Драйвер подключен");
            //Создаём соединение
            connection = DriverManager.getConnection(url, name, password);
            connection.setAutoCommit(false);
            System.out.println("Соединение установлено");
            //Для использования SQL запросов существуют 3 типа объектов:
            preparedStatement(connection);
            connection.commit();
            assertNotNull(connection);
        } catch (Exception ex) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void preparedStatement(Connection connection) throws SQLException {
        //2.PreparedStatement: предварительно компилирует запросы,
        //которые могут содержать входные параметры
        // ? - место вставки нашего значеня
        System.out.println("Creating preparedStatement");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM test_table where id > ? and id < ?");

        System.out.println("Устанавливаем в нужную позицию значения определённого типа");//
        preparedStatement.setInt(1, 1);
        preparedStatement.setInt(2, 4);
        System.out.println("выполняем запрос");
        ResultSet result = preparedStatement.executeQuery();

        System.out.println("Выводим PreparedStatement");
        while (result.next()) {
            System.out.println("Номер в выборке #" + result.getRow()
                    + "\t Номер в базе #" + result.getInt("user"));
        }
    }


    @Test
    public void closeAllConnections() throws Exception {
            if (connection != null) {
                try {
                    connection.close();
                    assertNull(connection);
                } catch (SQLException ex) {
                    Assert.assertEquals("Can't close connection: " , ex.getMessage());
                }
            }
    }
}