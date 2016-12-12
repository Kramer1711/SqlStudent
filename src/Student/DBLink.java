package Student;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Kramer on 2016/12/8.
 */
public class DBLink {
    public static Connection link(){
        Connection con=null;
        String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Student;user=sa;password=55154156";//sa身份连接
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con= DriverManager.getConnection(url);
            //JOptionPane.showMessageDialog(null,"连接数据库成功!");
        }catch(ClassNotFoundException ce){
            JOptionPane.showMessageDialog(null,ce.getMessage());
        }catch(SQLException co){
            JOptionPane.showMessageDialog(null,co.getMessage());
        }
        return con;
    }
}
