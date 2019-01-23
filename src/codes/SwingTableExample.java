package codes;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class SwingTableExample extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void createAndShowGUI() {
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);

		model.addColumn("Functional Process");
		model.addColumn("Entries");
		model.addColumn("Exits");
		model.addColumn("Reads");
		model.addColumn("Writes");
		model.addColumn("CFP");
       
	
		for (String s : Student.diziTemp) {
			char[] a=s.toCharArray();
			for(int c=0;c<a.length;c++ ){
				if(a[c]=='E') {
					Student.dizi1.add("1");
					if(a[c+1]=='R') {
						Student.dizi8.add("1");
							if(a[c+2]=='W') {
								Student.dizi7.add("1");
								if(c+4<=a.length) {
									if(a[c+3]=='X') {
										Student.dizi5.add("1");
									}
									else {
										Student.dizi5.add("0");
									}
								}
								else{
									Student.dizi5.add("0");
								}
							}
							else {
								Student.dizi7.add("0");
							}

					}else if(a[c+1]=='W') {
						Student.dizi7.add("1");
						Student.dizi8.add("0");
						if(c+3<=a.length) {
							if(a[c+2] == 'X') {
								Student.dizi5.add("1");
							}else {
								Student.dizi5.add("0");
							}
						}else {
							Student.dizi5.add("0");
						}
					}else if(a[c+1]=='X') {
						Student.dizi5.add("1");
						Student.dizi8.add("0");
						Student.dizi7.add("0");
					}
				}
			}
			System.out.println();
		}
		ArrayList<Integer> sum = new ArrayList<Integer>();
		for (int i = 0; i < Student.dizi1.size(); i++) {
			sum.add((Integer.parseInt(Student.dizi1.get(i))+Integer.parseInt(Student.dizi5.get(i))+Integer.parseInt(Student.dizi8.get(i))+Integer.parseInt(Student.dizi7.get(i))));
		}
		
		for (int i = 0; i < Student.diziTemp.size(); i++) {
			model.insertRow(i, new Object[] {Student.diziTemp.get(i), Student.dizi1.get(i), Student.dizi5.get(i), Student.dizi8.get(i),
					Student.dizi7.get(i), sum.get(i) });
		}

		JFrame f = new JFrame();
		f.setSize(500, 500);
		f.add(new JScrollPane(table));
		f.setVisible(true);
	}

}