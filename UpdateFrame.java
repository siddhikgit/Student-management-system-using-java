import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class UpdateFrame extends JFrame {
    Container c;
    JLabel rno, name, m1, m2, m3;
    JTextField txtrno, txtname, txtm1, txtm2, txtm3;
    JButton Savebtn, Backbtn;

    UpdateFrame() {
        c = getContentPane();
        c.setBackground(Color.GRAY);
        c.setLayout(null);

        rno = new JLabel("Enter rno :");
        txtrno = new JTextField(20);
        name = new JLabel("Enter name :");
        txtname = new JTextField(20);
        m1 = new JLabel("Enter sub marks 1 :");
        txtm1 = new JTextField(20);
        m2 = new JLabel("Enter sub marks 2 :");
        txtm2 = new JTextField(20);
        m3 = new JLabel("Enter sub marks 3 :");
        txtm3 = new JTextField(20);
        Savebtn = new JButton("Save");
        Backbtn = new JButton("Back");

        Font f = new Font("Courier", Font.BOLD, 30);

        rno.setFont(f);
        txtrno.setFont(f);
        name.setFont(f);
        txtname.setFont(f);
        m1.setFont(f);
        txtm1.setFont(f);
        m2.setFont(f);
        txtm2.setFont(f);
        m3.setFont(f);
        txtm3.setFont(f);
        Savebtn.setFont(f);
        Backbtn.setFont(f);

        rno.setBounds(249, 50, 300, 30);
        txtrno.setBounds(200, 90, 300, 30);
        name.setBounds(242, 140, 300, 30);
        txtname.setBounds(200, 180, 300, 40);
        m1.setBounds(210, 230, 300, 30);
        txtm1.setBounds(200, 270, 300, 30);
        m2.setBounds(210, 320, 300, 30);
        txtm2.setBounds(200, 360, 300, 30);
        m3.setBounds(210, 410, 300, 30);
        txtm3.setBounds(200, 450, 300, 30);
        Savebtn.setBounds(240, 520, 165, 30);
        Backbtn.setBounds(240, 600, 165, 30);

        c.add(rno);
        c.add(txtrno);
        c.add(name);
        c.add(txtname);
        c.add(m1);
        c.add(txtm1);
        c.add(m2);
        c.add(txtm2);
        c.add(m3);
        c.add(txtm3);
        c.add(Savebtn);
        c.add(Backbtn);

        ActionListener a1 = (ae) -> {
            MainFrame a = new MainFrame();
            dispose();
        };

        ActionListener a2 = (ae) -> {
            try {
                if (txtrno.getText().equals("")) {
                    throw new Exception("roll no should not be empty");
                }
                int r1 = Integer.parseInt(txtrno.getText());
                if (r1 <= 0) {
                    txtrno.setText("");
                    txtrno.requestFocus();
                    throw new Exception("roll no must be greater than zero!");
                }

                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                String url = "jdbc:mysql://localhost:3306/javaproj";
                String un = "root";
                String pw = "abc123";

                Connection con = DriverManager.getConnection(url, un, pw);
                String sq = "select * from student where rno=?";
                PreparedStatement ps = con.prepareStatement(sq);
                ps.setInt(1, r1);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String name = txtname.getText().trim();
                    if (name.isEmpty()) {
                        txtname.setText("");
                        txtname.requestFocus();
                        throw new Exception("name field cannot be blank");
                    }
                    if (!name.matches("[a-zA-Z ]+")) {
                        txtname.setText("");
                        txtname.requestFocus();
                        throw new Exception("name should contain letters only !");
                    }
                    if (name.length() < 2) {
                        throw new Exception("name should not be one letter");
                    }
                    if (txtm1.getText().equals("") || txtm2.getText().equals("") || txtm3.getText().equals("")) {
                        throw new Exception("marks field should not be empty");
                    }
                    int marks1 = Integer.parseInt(txtm1.getText());
                    if (marks1 < 0 || marks1 > 100) {
                        txtm1.setText("");
                        txtm1.requestFocus();
                        throw new Exception("marks of subject 1 should be in the range of 0-100");
                    }
                    int marks2 = Integer.parseInt(txtm2.getText());
                    if (marks2 < 0 || marks2 > 100) {
                        txtm2.setText("");
                        txtm2.requestFocus();
                        throw new Exception("marks of subject 2 should be in the range of 0-100");
                    }
                    int marks3 = Integer.parseInt(txtm3.getText());
                    if (marks3 < 0 || marks3 > 100) {
                        txtm3.setText("");
                        txtm3.requestFocus();
                        throw new Exception("marks of subject 3 should be in the range of 0-100");
                    }

                    String sql = "update student set name=?,m1=?,m2=?,m3=? where rno=?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, name);
                    pst.setDouble(2, marks1);
                    pst.setDouble(3, marks2);
                    pst.setDouble(4, marks3);
                    pst.setInt(5, r1);
                    pst.executeUpdate();
                    con.close();
                    JOptionPane.showMessageDialog(c, "record updated");
                    txtrno.setText("");
                    txtname.setText("");
                    txtm1.setText("");
                    txtm2.setText("");
                    txtm3.setText("");
                } else {
                    throw new Exception(r1 + " doesn't exist");
                }
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(c, "issue = " + e);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(c, "roll no and marks should be integer");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(c, "issue = " + e.getMessage());
            }
        };

        Savebtn.addActionListener(a2);
        Backbtn.addActionListener(a1);
        setTitle("Update Frame");
        setSize(650, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}