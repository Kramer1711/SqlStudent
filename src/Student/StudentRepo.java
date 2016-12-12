package Student;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Kramer on 2016/12/8.
 */
public class StudentRepo {
    Connection con;

    StudentRepo() {
        con = DBLink.link();
    }

    //插入数据
    public void insert(Student student) throws SQLException {
        String sql = "insert into "
                + student.Table + " values ("
                + student.num + ","
                + student.dorm + ","
                + "null" + ",'"
                + student.name + "','"
                + student.sex + "',null)";
        Statement st = null;
        try {
            con.setAutoCommit(false);
            st = con.createStatement();
            st.executeUpdate(sql);
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {//释放数据库的资源
            try {
                if (st != null)
                    st.close();
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //查询数据
    public Student getStudentByNum(String num) {
        Student student;
        String sql;
        if(num == null) return null;
        else sql = "select * from student where Sno = " + num;

        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                student = new Student(num, rs.getString(2),
                        rs.getString(4), rs.getString(5));
                return student;
            }
            JOptionPane.showMessageDialog(null,
                    "NON-EXISTENT!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)  rs.close();
                if (st != null)  st.close();
                if (con != null) con.close();
            }catch (SQLException e){ e.printStackTrace();}
        }
        return null;
    }

    //删除数据
    public boolean deleteStudentByNum(String num) {
        /*
            如果num为null，报错：sql语句语法错误
         */
        String sql = "delete from student where Sno = " + num;
        Statement st = null;
        try {
            st = con.createStatement();
            if (st.executeUpdate(sql) == 0) {
                JOptionPane.showMessageDialog(null, "NON-EXISTENT!",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "DELETE SUCCESS!",
                        "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
                if (con != null) con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //更新数据
    public boolean upData(Student student){
        String sql = "update student set " +
                "student.Sname2 = '"+student.name
                +"', student.dorm = "+student.dorm
                +", student.Ssex2 = '"+student.sex
                +"'  where student.Sno = " + student.num;
        Statement st = null;

        try {
            st = con.createStatement();
            if(st.executeUpdate(sql) == 1)
                JOptionPane.showMessageDialog(null,"UPDATA SUCCESS!",
                        "INFORMATION",JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null,"UPDATA FAITH!",
                        "INFORMATION",JOptionPane.ERROR_MESSAGE);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(st != null) st.close();
                if(con != null) con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }


}
