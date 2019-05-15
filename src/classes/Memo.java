package classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


class Memo extends JFrame implements ActionListener {
	// ���Ϻκ�
	JMenu menu1;
	JMenuItem new1, open, save, saveAs, exit;
	// �����κ�
	JMenu menu2;
	JMenuItem cut, copy, stick, del, find, replace;
	//���ĺκ�
	JMenu menu3;
	JMenuItem font;
	//����
	JMenu menu4;
	JMenuItem calculator;
	
	JMenuBar menuBar;
	JTextArea area;
	JScrollPane bar;
	JFileChooser chooser;

	private String fileName;

	public Memo() {
		// ���Ϻκ�
		menu1 = new JMenu("����");
		new1 = new JMenuItem("�� ����");
		open = new JMenuItem("����");
		save = new JMenuItem("����");
		saveAs = new JMenuItem("�ٸ� �̸����� ����");
		exit = new JMenuItem("����");
		// �����κ�
		menu2 = new JMenu("����");
		cut = new JMenuItem("�߶󳻱�");
		copy = new JMenuItem("����");
		stick = new JMenuItem("�ٿ��ֱ�");
		del = new JMenuItem("����");
		find = new JMenuItem("ã��");
		replace = new JMenuItem("�ٲٱ�");
		//���� �κ�
		menu3=new JMenu("����");
		font=new JMenuItem("�۲�");
		//����
		menu4=new JMenu("�ΰ����");
		calculator=new JMenuItem("����");
		
		menuBar = new JMenuBar();
		chooser = new JFileChooser();
		area = new JTextArea();
		bar = new JScrollPane(area);

		menu1.add(new1);
		menu1.add(open);
		menu1.add(save);
		menu1.add(saveAs);
		menu1.addSeparator();
		menu1.add(exit);
		menuBar.add(menu1);

		menu2.add(cut);
		menu2.add(copy);
		menu2.add(stick);
		menu2.add(del);
		menu2.addSeparator();
		menu2.add(find);
		menu2.add(replace);
		menuBar.add(menu2);
		
		menu3.add(font);
		menuBar.add(menu3);
		
		menu4.add(calculator);
		menuBar.add(menu4);

		find.addActionListener(this);
		new1.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		saveAs.addActionListener(this);
		exit.addActionListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		stick.addActionListener(this);
		del.addActionListener(this);
		find.addActionListener(this);
		font.addActionListener(this);
		calculator.addActionListener(this);

		setJMenuBar(menuBar);

		add(bar);
		setSize(600, 400);
		setVisible(true);

		setTitle("�� ����");
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "�� ����") {
			area.setText("");
			setTitle("�� ����");
		} else if (e.getActionCommand() == "����") {
			if (chooser.showOpenDialog(Memo.this) == JFileChooser.CANCEL_OPTION)
				return;
			File file = chooser.getSelectedFile();
			String fileName = file.getPath();
			setTitle(file.getName());// Ÿ��Ʋ�� ������ �̸��� ������
			FileRead(fileName);

		} else if (e.getActionCommand() == "����") {
			if (getTitle().equals("�� ����")) {
				JFileChooser fc = new JFileChooser();
				if (fc.showSaveDialog(Memo.this) == JFileChooser.CANCEL_OPTION)
					return;
				File f = fc.getSelectedFile();
				fileName = f.getPath();
				setTitle(f.getName());
				FileSave(fileName);
				
			} else {
				FileSave(fileName);// Ÿ��Ʋ�� �����Ұ�쿡�� �׳� ���̺�� ����
			}

		} else if (e.getActionCommand() == "�ٸ� �̸����� ����") {
			if (chooser.showSaveDialog(Memo.this) == JFileChooser.CANCEL_OPTION)
				return;
			File file = chooser.getSelectedFile();
			String fileName = file.getPath();
			FileSave(fileName);
		} else if (e.getActionCommand() == "����") {
			System.exit(0);
		} else if (e.getActionCommand() == "�߶󳻱�") {
			area.cut();
		} else if (e.getActionCommand() == "����") {
			area.copy();
		} else if (e.getActionCommand() == "�ٿ��ֱ�") {
			area.paste();
		} else if (e.getActionCommand() == "����") {
			area.replaceSelection("");
		} else if (e.getActionCommand() == "ã��") {
			Search_Frame search_frame = new Search_Frame(this);
			search_frame.setSize(410, 110);
			search_frame.setTitle("ã��");
			search_frame.setVisible(true);
		}else if (e.getActionCommand() == "�۲�") {
			Font_Frame font_frame=new Font_Frame(this);
			font_frame.setSize(450,330);
			font_frame.show();
			area.setFont(font_frame.fontSet());
		}else if (e.getActionCommand() == "����") {
			Calcurator cal=new Calcurator();
			cal.setTitle("����");
			cal.setSize(400,350);
		}

	}

	// ���� ����
	public void FileRead(String fileName) {
		try {
			FileReader fr = new FileReader(fileName);
			StringWriter sw = new StringWriter();
			while (true) {
				int read = fr.read();
				if (read == -1)
					break;
				sw.write(read);
			}
			sw.close();
			area.setText(sw.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ���� ����
	public void FileSave(String fileName) {
		try {
			PrintStream ps = new PrintStream(fileName);
			ps.println(area.getText());
			ps.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Memo();
	}
}
