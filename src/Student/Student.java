package Student;

/**
 * Created by Kramer on 2016/12/8.
 */
public class Student {
    //表名
    final static String Table = "student";
    //属性链接
    final static String KEY_name = "Sname2";
    final static String KEY_num = "Sno";
    final static String KEY_sex = "Ssex2";
    final static String KEY_dorm = "dorm";
    //属性
    public String num;
    public String name;
    public String sex;
    public String dorm;

    Student(){}
    Student(String num,String name,String sex){
        this.num = num;
        this.name = name;
        this.sex = sex;
    }
    Student(String num,String dorm,String name,String sex){
        this(num,name,sex);
        this.dorm = dorm;
    }
}
