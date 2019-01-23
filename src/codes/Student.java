package codes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Student extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Entry entry = new Entry();
	Read read = new Read();
	Write write = new Write();
	Exit exitc = new Exit();

	// Table
	JTable j;

	JLabel title, JLstudentID, JLfirstName, JLsurName, JLgender, JLemail;
	JTextField JTstudentID, JTfirstName, JTsurname, JTgender, JTemail;
	JButton insert, update, delete, Students;
	JToggleButton exit;

	String gender = null;

	static ArrayList<String> diziDelete = new ArrayList<String>();
	static ArrayList<String> diziExit = new ArrayList<String>();
	static ArrayList<String> diziShow = new ArrayList<String>();
	static ArrayList<String> diziJT = new ArrayList<String>();
	static ArrayList<String> diziUpdate = new ArrayList<String>();
	static ArrayList<String> diziInsert = new ArrayList<String>();

	static ArrayList<String> dizi1 = new ArrayList<String>(); // Entry
	static ArrayList<String> dizi2 = new ArrayList<String>(); // Insert
	static ArrayList<String> dizi3 = new ArrayList<String>(); // Update
	static ArrayList<String> dizi4 = new ArrayList<String>(); // Delete
	static ArrayList<String> dizi5 = new ArrayList<String>(); // Exit
	static ArrayList<String> dizi6 = new ArrayList<String>(); // Show
	static ArrayList<String> dizi7 = new ArrayList<String>(); // Write
	static ArrayList<String> dizi8 = new ArrayList<String>(); // Read
	// static ArrayList<String> buttonType = new ArrayList<String>(); // Read

	static ArrayList<String> diziLogComponents = new ArrayList<String>();

	private Scanner sc;

	public Student(){
		
		super("STUDENT CRUD SYSTEM");
		initUI();
		

	}

	public final void initUI() {

		title = new JLabel("Registration Form");
		JLstudentID = new JLabel("Id:");
		JLfirstName = new JLabel("Name:");
		JLsurName = new JLabel("Surname:");
		JLgender = new JLabel("Gender:");
		JLemail = new JLabel("Email:");
		title.setBounds(70, 7, 200, 30);
		JLstudentID.setBounds(30, 50, 60, 30);
		JLfirstName.setBounds(30, 85, 60, 30);
		JLsurName.setBounds(30, 120, 70, 30);
		JLgender.setBounds(30, 155, 60, 30);
		JLemail.setBounds(30, 190, 60, 30);

		JTstudentID = new JTextField(20);
		JTstudentID.setFont(new java.awt.Font("Tahoma", 1, 11));
		JTfirstName = new JTextField(20);
		JTfirstName.setFont(new java.awt.Font("Tahoma", 1, 11));

		JTsurname = new JTextField(20);
		JTsurname.setFont(new java.awt.Font("Tahoma", 1, 11));

		JTgender = new JTextField(20);
		JTgender.setFont(new java.awt.Font("Tahoma", 1, 11));

		JTemail = new JTextField(20);
		JTemail.setFont(new java.awt.Font("Tahoma", 1, 11));

		JTstudentID.setBounds(95, 50, 130, 30);
		JTfirstName.setBounds(95, 85, 130, 30);
		JTsurname.setBounds(95, 120, 130, 30);
		JTgender.setBounds(95, 155, 130, 30);
		JTemail.setBounds(95, 190, 130, 30);

		insert = new javax.swing.JButton("Insert");
		insert.setFont(new java.awt.Font("Tahoma", 1, 11));
		update = new javax.swing.JButton("Update");
		update.setFont(new java.awt.Font("Tahoma", 1, 11));
		delete = new javax.swing.JButton("Delete");
		delete.setFont(new java.awt.Font("Tahoma", 1, 11));
		exit = new javax.swing.JToggleButton("Exit");
		exit.setFont(new java.awt.Font("Tahoma", 1, 11));
		Students = new javax.swing.JButton("Students");
		Students.setFont(new java.awt.Font("Tahoma", 1, 11));
		insert.setBounds(130, 250, 100, 25);
		update.setBounds(130, 285, 100, 25);
		delete.setBounds(25, 285, 100, 25);
		exit.setBounds(25, 250, 100, 25);
		Students.setBounds(70, 320, 100, 25);

		add(title);
		add(JLstudentID);
		add(JLfirstName);
		add(JLsurName);
		add(JLgender);
		add(JLemail);
		add(JTstudentID);
		add(JTfirstName);
		add(JTsurname);
		add(JTgender);
		add(JTemail);
		add(insert);
		add(update);
		add(delete);
		add(exit);
		add(Students);

		JTstudentID.addMouseListener((MouseListener) this);
		JTfirstName.addMouseListener((MouseListener) this);
		JTsurname.addMouseListener((MouseListener) this);
		JTgender.addMouseListener((MouseListener) this);
		JTemail.addMouseListener((MouseListener) this);
		insert.addActionListener((ActionListener) this);
		update.addActionListener((ActionListener) this);
		delete.addActionListener((ActionListener) this);
		exit.addActionListener((ActionListener) this);
		Students.addActionListener((ActionListener) this);

		JScrollPane sp = new JScrollPane(j);
		add(sp);
		setSize(770, 420);
		setVisible(true);
		setLayout(new BorderLayout());
		add(sp, BorderLayout.EAST);

	}

	public void ReadFiles() {
		List<String> log = new ArrayList<String>();
		String[] details = null;
		try {
			File f = new File("/home/orcun/Desktop/log");
			sc = new Scanner(f);

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				details = line.split("\t");
				String component2 = details[1];
				log.add(component2);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == JTstudentID || e.getSource() == JTfirstName || e.getSource() == JTsurname
				|| e.getSource() == JTgender || e.getSource() == JTemail)

		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			ArrayList<String> dizi = new ArrayList<String>();
			dizi.add("JTextField");
			String[] x = e.getSource().getClass().toString().split("\\.");
			diziJT.add(x[2]);
			NoticeObservable noticeObservable = new NoticeObservable();
			entry.setObservable(noticeObservable);
			entry.setEntryCount(entry.getEntryCount() + 1);
			noticeObservable.addObserver(entry);
			noticeObservable.notifyObserver();

			for (int i = 0; i < diziJT.size(); i++) {
				for (int j = 0; j < dizi.size(); j++) {
					if (diziJT.get(i).equals(dizi.get(j))) {
						//System.out.println(diziJT.get(i) + "\tE");
						try {
							diziLogComponents.add("E");
							logFunction(diziJT.get(i) + "\tE" + "\t" + dateFormat.format(date));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
			diziJT.clear();
		}
	}

	public void actionPerformed(ActionEvent e) {
		SwingTableExample st = new SwingTableExample();
		if (e.getSource() == insert) {
			String insertType = "";
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			// System.out.println(dateFormat.format(date));
			ArrayList<String> dizi = new ArrayList<String>();

			dizi.add("JButton");

			String[] x = e.getSource().getClass().toString().split("\\.");
			diziInsert.add(x[2]);
			NoticeObservable noticeObservable = new NoticeObservable();
			entry.setObservable(noticeObservable);
			entry.setEntryCount(entry.getEntryCount() + 1);
			noticeObservable.addObserver(entry);
			noticeObservable.notifyObserver();

			for (int i = 0; i < diziInsert.size(); i++) {
				for (int j = 0; j < dizi.size(); j++) {
					if (diziInsert.get(i).equals(dizi.get(j))) {
						//System.out.println(diziInsert.get(i) + "\tE" + "\t" + dateFormat.format(date));
						try {
							insertType = diziInsert.get(i);
							diziLogComponents.add("E");

							logFunction(diziInsert.get(i) + "\tE" + "\t" + dateFormat.format(date));
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					}

				}

			}
			diziInsert.clear();

			try {
				theQuery(insertType,
						"insert into dbplugin (id,name,surname,gender,email) values('" + JTstudentID.getText() + "','"
								+ JTfirstName.getText() + "','" + JTsurname.getText() + "','" + JTgender.getText()
								+ "','" + JTemail.getText() + "')");

			} catch (Exception ex) {
			}
		} else if (e.getSource() == update) {
			String updateType = "";
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			ArrayList<String> dizi = new ArrayList<String>();

			dizi.add("JButton");

			String[] x = e.getSource().getClass().toString().split("\\.");
			diziUpdate.add(x[2]);
			NoticeObservable noticeObservable = new NoticeObservable();
			entry.setObservable(noticeObservable);
			entry.setEntryCount(entry.getEntryCount() + 1);
			noticeObservable.addObserver(entry);
			noticeObservable.notifyObserver();

			for (int i = 0; i < diziUpdate.size(); i++) {
				for (int j = 0; j < dizi.size(); j++) {
					if (diziUpdate.get(i).equals(dizi.get(j))) {
						//System.out.println(diziUpdate.get(i) + "\tE" + "\t" + dateFormat.format(date));
						try {
							updateType = diziUpdate.get(i);
							diziLogComponents.add("E");
							logFunction(diziUpdate.get(i) + "\tE" + "\t" + dateFormat.format(date));
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					}

				}

			}
			diziUpdate.clear();

			try {

				theQuery(updateType,
						"update dbplugin set name = '" + JTfirstName.getText() + "',surname='" + JTsurname.getText()
								+ "',gender='" + JTgender.getText() + "',email='" + JTemail.getText() + "'where id = "
								+ JTstudentID.getText());

			} catch (Exception ex) {
			}
		} else if (e.getSource() == delete) {
			String deleteType = "";
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			ArrayList<String> dizi = new ArrayList<String>();

			dizi.add("JButton");

			String[] x = e.getSource().getClass().toString().split("\\.");
			diziDelete.add(x[2]);
			NoticeObservable noticeObservable = new NoticeObservable();
			entry.setObservable(noticeObservable);
			entry.setEntryCount(entry.getEntryCount() + 1);
			noticeObservable.addObserver(entry);
			noticeObservable.notifyObserver();

			for (int i = 0; i < diziDelete.size(); i++) {
				for (int j = 0; j < dizi.size(); j++) {
					if (diziDelete.get(i).equals(dizi.get(j))) {
						//System.out.println(diziDelete.get(i) + "\tE" + "\t" + dateFormat.format(date));
						try {
							// dizi1.add("E");
							deleteType = diziDelete.get(i);
							diziLogComponents.add("E");
							logFunction(diziDelete.get(i) + "\tE" + "\t" + dateFormat.format(date));
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					}

				}

			}
			diziDelete.clear();

			try {

				theQuery(deleteType, "delete from dbplugin where id = " + JTstudentID.getText());
			} catch (Exception ex) {
			}
		} else if (e.getSource() == exit) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			ArrayList<String> dizi = new ArrayList<String>();
			dizi.add("JToggleButton");
			String[] x = e.getSource().getClass().toString().split("\\.");
			diziExit.add(x[2]);
			NoticeObservable noticeObservable = new NoticeObservable();
			exitc.setObservable(noticeObservable);
			exitc.setExitCount(exitc.getExitCount() + 1);
			noticeObservable.addObserver(exitc);
			noticeObservable.notifyObserver();

			for (int i = 0; i < diziLogComponents.size(); i++) {
				System.out.print(diziLogComponents.get(i));
			}
			System.out.println();
			for (int i = 0; i < diziExit.size(); i++) {
				for (int j = 0; j < dizi.size(); j++) {
					if (diziExit.get(i).equals(dizi.get(j))) {
						//System.out.println(diziExit.get(i) + "\tX" + "\t" + dateFormat.format(date));
						diziLogComponents.add("F");
						checkDiziComponent(diziLogComponents);
					}
				}
			}
			diziExit.clear();
			st.createAndShowGUI();
			
		} else if (e.getSource() == Students) {
			String selectType = "";

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			ArrayList<String> dizi = new ArrayList<String>();
			dizi.add("JButton");
			String[] x = e.getSource().getClass().toString().split("\\.");
			diziShow.add(x[2]);
			NoticeObservable noticeObservable = new NoticeObservable();
			read.setObservable(noticeObservable);
			read.setReadCount(read.getReadCount() + 1);
			noticeObservable.addObserver(read);
			noticeObservable.notifyObserver();

			for (int i = 0; i < diziShow.size(); i++) {
				for (int j = 0; j < dizi.size(); j++) {
					if (diziShow.get(i).equals(dizi.get(j))) {
						selectType = diziShow.get(i);
						//System.out.println(diziShow.get(i) + "\tX" + "\t" + dateFormat.format(date));
						try {
							diziLogComponents.add("X");
							logFunction(diziShow.get(i) + "\tX" + "\t" + dateFormat.format(date));
							showTableData(selectType);
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					}

				}

			}
			//System.out.println(dizi6.size() + "dizi6");
			diziShow.clear();

		}
	}

	static ArrayList<String> diziTemp = new ArrayList<String>();

	public static List<String> checkDiziComponent(ArrayList<String> x) {
		List<String> a = null;
		for (int s = 0; s < x.size(); s++) {
			if (x.get(s).equals("F")) {
				a = x.subList(0, s);
				for (int i = 0; i < a.size(); i++) {
					if (a.get(i).equals("E")) {
						int index = i;
						if (a.get(index + 1).equals("R")) {
							if (a.get(index + 2).equals("W")) {
								if (index + 4 <= a.size()) {
									if (a.get(index + 3).equals("X")) {
										List<String> y = a.subList(index, index + 4);
										String n = "";
										for (int j = 0; j < y.size(); j++) {
											n += y.get(j);
											System.out.print(y.get(j));
										}
										diziTemp.add(n);
										System.out.println();
									} else {
										List<String> y = a.subList(index, index + 3);
										String n = "";
										for (int j = 0; j < y.size(); j++) {
											n += y.get(j);
											System.out.print(y.get(j));
										}
										diziTemp.add(n);
										System.out.println();
									}
								} else {
									List<String> y = a.subList(index, index + 3);
									String n = "";
									for (int j = 0; j < y.size(); j++) {
										n += y.get(j);
										System.out.print(y.get(j));
									}
									diziTemp.add(n);
									System.out.println();
								}
							}
						}
					}
				}
			}

		}
		return a;
	}

	
	public static void logFunction(String x) throws IOException {
		
		
		File log = new File("/home/orcun/Desktop/log");
		
		try {
			if (log.exists() == false) {
				//System.out.println("We had to make a new file.");
				log.createNewFile();
			}
			PrintWriter out = new PrintWriter(new FileWriter(log, true));
			out.append(x);
			out.println();
			out.close();
		} catch (IOException e) {
			//System.out.println("COULD NOT LOG!!");
		}
	}

	public TableModel showTableData(String t) {
		Connection con = null;
		String driver = "com.mysql.jdbc.Driver";
		String databaseURL = "jdbc:mysql://localhost:3306/plugindb";
		String userName = "pmauser";
		String password = "33eminem";
		String[] columnNames = { "id", "name", "surname", "gender", "email" };

		DefaultTableModel model = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		model.setColumnIdentifiers(columnNames);
		j = new JTable();
		j.setModel(model);
		j.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		j.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane(j);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		String roll = "";
		String name = "";
		String cl = "";
		String ge = "";
		String sec = "";
		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(databaseURL, userName, password);
			// Statement st = con.createStatement();
			String sql = "select * from dbplugin";
			String a = sql.substring(0, sql.indexOf(" "));
			readOrWrite(t, a);
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int i = 0;
			for (int j = 0; j < 20; j++) {
				if (rs.next()) {
					roll = rs.getString("id");
					name = rs.getString("name");
					cl = rs.getString("surname");
					ge = rs.getString("gender");
					sec = rs.getString("email");
					model.addRow(new Object[] { roll, name, cl, ge, sec });
					i++;
				}

			}
			if (i < 1) {
				JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
			}
			if (i == 1) {
				//System.out.println(i + " Record Found");
			} else {
				//System.out.println(i + " Records Found");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		add(scroll);
		add(scroll, BorderLayout.EAST);
		setVisible(true);
		setSize(770, 420);
		/*
		for (int i = 0; i < diziLogComponents.size(); i++) {
			System.out.println(diziLogComponents.get(i));
		}
		*/
		return model;

	}

	static int flag;
	static String kelime = "";

	public void readOrWrite(String t, String x) throws IOException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		switch (x) {
		case "select":
			dizi6.add("X");
			//diziLogComponents.add("X");
			break;
		case "insert":
			kelime = "insert";
			dizi2.add("R");
			dizi2.add("W");
			diziLogComponents.add("R");
			diziLogComponents.add("W");
			logFunction(t + "\tR" + "\t" + dateFormat.format(date));
			logFunction(t + "\tW" + "\t" + dateFormat.format(date));
			break;
		case "update":
			kelime = "update";
			dizi3.add("R");
			dizi3.add("W");
			diziLogComponents.add("R");
			diziLogComponents.add("W");
			logFunction(t + "\tR" + "\t" + dateFormat.format(date));
			logFunction(t + "\tW" + "\t" + dateFormat.format(date));
			break;
		case "delete":
			kelime = "delete";
			dizi4.add("R");
			dizi4.add("W");
			diziLogComponents.add("R");
			diziLogComponents.add("W");
			logFunction(t + "\tR" + "\t" + dateFormat.format(date));
			logFunction(t + "\tW" + "\t" + dateFormat.format(date));
			break;

		default:
			break;
		}

	}

	// function to execute the insert update delete query
	public void theQuery(String t, String query) throws IOException {
		Connection con = null;
		String a = query.substring(0, query.indexOf(" "));
		readOrWrite(t, a);
		String driver = "com.mysql.jdbc.Driver";
		String databaseURL = "jdbc:mysql://localhost:3306/plugindb";
		String userName = "pmauser";
		String password = "33eminem";
		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(databaseURL, userName, password);
			Statement st = con.createStatement();
			st.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Query Executed");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}