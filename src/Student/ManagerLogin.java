package Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Kramer on 2016/12/14.
 */
public class ManagerLogin extends JFrame {

    private JTextField accountsText;
    private JPasswordField passwordText;
    private JButton loginButton;

    public static void main(String args[]){
        new ManagerLogin();
    }

    public ManagerLogin(){
        init();
    }
    private void init(){
        // JFrame
        // Setting the width and height of frame
        this.setTitle("学生管理系统");
        this.setSize(300, 180);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        JPanel panel = new JPanel();
        this.add(panel);
        placeComponents(panel);//面板配置
        this.setVisible(true);
    }

    protected void placeComponents(JPanel panel) {
        //设置布局为null
        panel.setLayout(null);

        //帐号Label
        JLabel accountsLabel = new JLabel("Accounts:");
        accountsLabel.setBounds(20, 20, 80, 25);
        panel.add(accountsLabel);

        //学号输入
        accountsText = new JTextField();
        accountsText.setBounds(110, 20, 150, 25);
        accountsText.setHorizontalAlignment(0);
        panel.add(accountsText);


        //  密码
        //  创建 JLabel
        JLabel passwordLabel = new JLabel("PassWord:");
        passwordLabel.setBounds(20, 60, 80, 25);
        panel.add(passwordLabel);
        //  创建  PasswordField
        passwordText = new JPasswordField(20);
        passwordText.setBounds(110, 60, 150, 25);
        passwordText.setHorizontalAlignment(0);
        panel.add(passwordText);


        //  根据学号查询某学生的信息
        loginButton = new JButton("LOGIN");
        loginButton.setBounds(getWidth()/2-40, 100, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = accountsText.getText();
                String passWord = new String(passwordText.getPassword());
                StudentRepo repo = new StudentRepo();
                Account account1 = repo.getAccount(account);

                if(passWord.compareTo(account1.password) == 0) {
                    new StudentManager();
                    ManagerLogin.this.dispose();
                }else {
                    JOptionPane.showMessageDialog(null,"帐号或者密码错误！",
                            "MESSAGE",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        panel.add(loginButton);
    }
}
