import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class DeleteFrame extends JFrame {

    Container c;
    JLabel rno;
    JTextField txtrno;
    JButton Deletebtn, Backbtn;

    DeleteFrame() {
        c = getContentPane();
        c.setBackground(Color.GRAY);
        c.setLayout(null);

        rno = new JLabel("Enter rno :");
        txtrno = new JTextField(20);
        Deletebtn = new JButton("Delete");
        Backbtn = new JButton("Back");

        Font f = new Font("Courier", Font.BOLD, 30);

        rno.setFont(f);
        txtrno.setFont(f);
        Deletebtn.setFont(f);
        Backbtn.setFont(f);

        rno.setBounds(249, 50, 300, 30);
        txtrno.setBounds(200, 90, 300, 30);
        Deletebtn.setBounds(240, 170, 165, 30);
        Backbtn.setBounds(240, 230, 165, 30);

        c.add(rno);
        c.add(txtrno);
        c.add(Deletebtn);
        c.add(Backbtn);

        ActionListener a1 = (ae) -> {
            MainFrame a = new MainFrame();
            dispose();
        };

        ActionListener a2 = (ae) -> {
            try {
                int r1 = Integer.parseInt(txtrno.getText());
                if (r1 < 0) {
                    txtrno.setText("");
                    txtrno.requestFocus();
                    throw new Exception("rollno must be greater than zero!");
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
                    String sql = "delete from student where rno=?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setInt(1, r1);
                    pst.executeUpdate();
                    con.close();
                    JOptionPane.showMessageDialog(c, "record deleted");
                    txtrno.setText("");
                } else {
                    throw new Exception(r1 + " does not exist");
                }
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(c, "issue = " + e);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(c, "roll no should be an integer");
                txtrno.setText("");
                txtrno.requestFocus();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(c, "issue = " + e.getMessage());
                txtrno.setText("");
                txtrno.requestFocus();
            }
        };

        Backbtn.addActionListener(a1);
        Deletebtn.addActionListener(a2);
        setTitle("Delete Frame");
        setSize(650, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
