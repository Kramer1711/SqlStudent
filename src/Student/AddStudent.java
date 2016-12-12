package Student;

import com.sun.java.swing.plaf.windows.WindowsBorders;
import javafx.scene.control.ToolBar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Kramer on 2016/12/7.
 */
public class AddStudent extends JFrame implements ActionListener{

    /**
     *  添加学生
     *  Button事件存入数据库
     */
    private JButton saveButton = null;
    private JTextField nameText,numText,sexText,dormText;

    //单元测试使用入口
    public static void main(String[] args) {
        new AddStudent();
    }

    public AddStudent(){
        init();
    }
    private void init(){
        // 创建 JFrame 实例
        // Setting the width and height of frame
        this.setTitle("添加学生");
        this.setSize(360, 190);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        /* 创建面板
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        JPanel panel = new JPanel();
        // 添加面板
        this.add(panel);
        /*
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents(panel);
        // 设置界面可见
        this.setVisible(true);
    }

    /**
     * 设置panel的布局方式
     * @param panel
     */
    private void placeComponents(JPanel panel) {
        //这边设置布局为 null
        panel.setLayout(null);

        //  姓名
        //  创建 JLabel
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10,20,50,25);
        panel.add(nameLabel);
        //  创建  TextField   供用户输入
        nameText = new JTextField(20);
        nameText.setBounds(70,20,80,25);
        panel.add(nameText);

        // 输入 学号    的文本域
        JLabel numLabel = new JLabel("Number:");
        numLabel.setBounds(10,50,50,25);
        panel.add(numLabel);

        numText = new JTextField(20);
        numText.setBounds(70,50,80,25);
        //  限制输入为数字
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
        });
        panel.add(numText);

        //  输入  dorm    的文本域
        JLabel dormLabel = new JLabel("Dorm:");
        dormLabel.setBounds(180,20,50,25);
        panel.add(dormLabel);
        //  dorm TextField
        dormText = new JTextField(20);
        dormText.setBounds(240,20,80,25);
        panel.add(dormText);


        //  输入  sex 的文本域
        JLabel sexLabel = new JLabel("Sex:");
        sexLabel.setBounds(180,50,50,25);
        panel.add(sexLabel);
        //  sex TextField
        sexText = new JTextField(20);
        sexText.setBounds(240,50,80,25);
        panel.add(sexText);


        //  创建Save按钮
        saveButton = new JButton("Save");
        saveButton.setBounds(240, 100, 80, 25);
        saveButton.addActionListener(this);
        panel.add(saveButton);
    }


    /**
     * 存入学生信息
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveButton){
            Student student;
            StudentRepo repo = new StudentRepo();
            String num = numText.getText();
            String dorm = dormText.getText();
            String name = nameText.getText();
            String sex = sexText.getText();
            student = new Student(num,dorm,name,sex);
            try{
                repo.insert(student);
            }catch (Exception e1){
                JOptionPane.showMessageDialog(null,e1.getMessage());
            }
            AddStudent.this.dispose();
        }
    }
}
