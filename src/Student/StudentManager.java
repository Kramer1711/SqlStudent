package Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Kramer on 2016/12/9.
 */
public class StudentManager extends JFrame implements ActionListener{
    /**
     * 学生管理系统界面
     */

    private String num;
    private JTextField numText,nameText,dormText,numText1,sexText;
    private JButton deleteButton,upDataButton,addButton,selectButton;
    public static void main(String args[]){
        StudentManager frame = new StudentManager();
    }
    public StudentManager(){
        init();
    }


    private void init(){
        // JFrame
        // Setting the width and height of frame
        this.setTitle("学生管理系统");
        this.setSize(380, 270);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        JPanel panel = new JPanel();
        this.add(panel);
        placeComponents(panel);//面板配置
        this.setVisible(true);
    }

    /**
     * panel布局
     *
     * @param panel
     */
    protected void placeComponents(JPanel panel){
        //设置布局为null
        panel.setLayout(null);

        //学号Label
        JLabel numLabel = new JLabel("Enter the number:");
        numLabel.setBounds(30,20,110,25);
        panel.add(numLabel);

        //学号输入
        numText = new JTextField();
        numText.setBounds(145,20,100,25);
        numText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar=e.getKeyChar();
                if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {

                } else {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });//只运行输入数字
        panel.add(numText);


        //  姓名
        //  创建 JLabel
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20,80,50,25);
        panel.add(nameLabel);
        //  创建  TextField   供用户输入
        nameText = new JTextField(20);
        nameText.setBounds(80,80,90,25);
        panel.add(nameText);

        // 输入 学号    的文本域
        JLabel numLabel1 = new JLabel("Number:");
        numLabel1.setBounds(20,110,50,25);
        panel.add(numLabel1);

        numText1 = new JTextField(20);
        numText1.setBounds(80,110,90,25);
        //  不可编辑
        //numText1.setEditable(false);
        panel.add(numText1);

        //  输入  dorm    的文本域
        JLabel dormLabel = new JLabel("Dorm:");
        dormLabel.setBounds(190,80,50,25);
        panel.add(dormLabel);
        //  dorm TextField
        dormText = new JTextField(20);
        dormText.setBounds(250,80,90,25);
        panel.add(dormText);


        //  输入  sex 的文本域
        JLabel sexLabel = new JLabel("Sex:");
        sexLabel.setBounds(190,110,50,25);
        panel.add(sexLabel);
        //  sex TextField
        sexText = new JTextField(20);
        sexText.setBounds(250,110,90,25);
        panel.add(sexText);

        //  根据学号查询某学生的信息
        selectButton = new JButton("SELECT");
        selectButton.setBounds(260,20,80,25);
        selectButton.addActionListener(this);
        panel.add(selectButton);

        //  根据学号删除某学生信息
        deleteButton = new JButton("DELETE");
        deleteButton.setBounds(260,180,90,25);
        deleteButton.addActionListener(this);
        panel.add(deleteButton);

        //  更新某学生数据
        upDataButton = new JButton("UPDATA");
        upDataButton.setBounds(160,180,90,25);
        upDataButton.addActionListener(this);
        panel.add(upDataButton);

        //  添加学生
        addButton = new JButton("ADD");
        addButton.setBounds(60,180,90,25);
        addButton.addActionListener(this);
        panel.add(addButton);
    }

    /**
     * 事件处理
     * 1、删除学生
     * 2、更新学生信息
     * 3、查找学生信息
     * 4、进入添加学生界面
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == deleteButton){
            StudentRepo repo = new StudentRepo();
            num = numText.getText();
            repo.deleteStudentByNum(num);
        }else if(e.getSource() == upDataButton){
            Student student = new Student(num,dormText.getText(),
                    nameText.getText(),sexText.getText());
            StudentRepo repo = new StudentRepo();
            repo.upData(student);
        }else if(e.getSource() == selectButton){
            StudentRepo repo = new StudentRepo();
            num = numText.getText();
            Student student = repo.getStudentByNum(num);
            if(student != null) {
                dormText.setText(student.dorm);
                nameText.setText(student.name);
                sexText.setText(student.sex);
                numText1.setText(num);
                numText1.setEditable(false);
            }
        }else if(e.getSource() == addButton){
                new AddStudent();
                //StudentManager.this.dispose();
        }
    }
}
