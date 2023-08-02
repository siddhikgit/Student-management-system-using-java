import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;



class MainFrame extends JFrame
	{

	Container c;
	JButton Addbtn,Viewbtn,Updatebtn,Deletebtn;


	MainFrame()
		{

		c=getContentPane();
		c.setBackground(Color.DARK_GRAY); 
		c.setLayout(null);
	
		
		Addbtn=new JButton("Add");
		Viewbtn=new JButton("View");
		Updatebtn=new JButton("Update");
		Deletebtn=new JButton("Delete");

		Font f=new Font("Courier",Font.BOLD,30);
	
		Addbtn.setFont(f);
		Viewbtn.setFont(f);
		Updatebtn.setFont(f);
		Deletebtn.setFont(f);


		Addbtn.setBounds(200,50,165,30);
		Viewbtn.setBounds(200,120,165,30);
		Updatebtn.setBounds(200,190,165,30);
		Deletebtn.setBounds(200,260,165,30);

		ActionListener a1=(ae) ->
		{


		AddFrame a=new AddFrame();
		dispose();



		};


			ActionListener a2=(ae) ->
		{


		UpdateFrame a=new UpdateFrame();
		dispose();



		};


			ActionListener a3=(ae) ->
		{


		DeleteFrame a=new DeleteFrame();
		dispose();



		};


				ActionListener a4=(ae) ->
		{


		ViewFrame a=new ViewFrame();
		dispose();



		};

		c.add(Addbtn);
		c.add(Viewbtn);
		c.add(Updatebtn);
		c.add(Deletebtn);

		Addbtn.addActionListener(a1);
		Updatebtn.addActionListener(a2);
		Deletebtn.addActionListener(a3);
		Viewbtn.addActionListener(a4);
		setTitle("S.M.S Application");
		setSize(600,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		setVisible(true);

		}


	}