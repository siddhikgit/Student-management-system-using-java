import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


class ViewFrame extends JFrame {

    Container c;
    TextArea taData;
    JButton btnBack;

    ViewFrame() {

        c = getContentPane();
        c.setLayout(null);

        taData = new TextArea(10, 60);
        btnBack = new JButton("Back");

        Font f = new Font("Courier", Font.BOLD, 24);
        taData.setFont(f);
        btnBack.setFont(f);

        c.add(taData);
        c.add(btnBack);

        taData.setBounds(60, 20, 800, 500);
        btnBack.setBounds(400, 540, 150, 40);

        try {
            StringBuffer data = new StringBuffer();

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url = "jdbc:mysql://localhost:3306/javaproj";
            String un = "root";
            String pw = "abc123";
            Connection con = DriverManager.getConnection(url, un, pw);
            String sql = "select * from student";
            Statement pst = con.createStatement();
            pst.executeQuery(sql);
            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                int rno = rs.getInt(1);
                String name = rs.getString(2);
                int marks1 = rs.getInt(3);
                int marks2 = rs.getInt(4);
                int marks3 = rs.getInt(5);
                data.append("rno = " + rno + ", " + "name = " + name + ", " + "s1 = " + marks1 + ", " + "s2 = " + marks2 + ", " + "s3 = " + marks3 + "\n");
            }

            taData.setText(data.toString());
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(c, "issue =" + e);
        }

        ActionListener a2 = (ae) -> {
            MainFrame a = new MainFrame();
            dispose();
        };

        btnBack.addActionListener(a2);
        setTitle("View student");
        setSize(900, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
