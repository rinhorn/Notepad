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
	// 파일부분
	JMenu menu1;
	JMenuItem new1, open, save, saveAs, exit;
	// 편집부분
	JMenu menu2;
	JMenuItem cut, copy, stick, del, find, replace;
	//서식부분
	JMenu menu3;
	JMenuItem font;
	//계산기
	JMenu menu4;
	JMenuItem calculator;
	
	JMenuBar menuBar;
	JTextArea area;
	JScrollPane bar;
	JFileChooser chooser;

	private String fileName;

	public Memo() {
		// 파일부분
		menu1 = new JMenu("파일");
		new1 = new JMenuItem("새 파일");
		open = new JMenuItem("열기");
		save = new JMenuItem("저장");
		saveAs = new JMenuItem("다른 이름으로 저장");
		exit = new JMenuItem("종료");
		// 편집부분
		menu2 = new JMenu("편집");
		cut = new JMenuItem("잘라내기");
		copy = new JMenuItem("복사");
		stick = new JMenuItem("붙여넣기");
		del = new JMenuItem("삭제");
		find = new JMenuItem("찾기");
		replace = new JMenuItem("바꾸기");
		//서식 부분
		menu3=new JMenu("서식");
		font=new JMenuItem("글꼴");
		//계산기
		menu4=new JMenu("부가기능");
		calculator=new JMenuItem("계산기");
		
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

		setTitle("새 파일");
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "새 파일") {
			area.setText("");
			setTitle("새 파일");
		} else if (e.getActionCommand() == "열기") {
			if (chooser.showOpenDialog(Memo.this) == JFileChooser.CANCEL_OPTION)
				return;
			File file = chooser.getSelectedFile();
			String fileName = file.getPath();
			setTitle(file.getName());// 타이틀을 파일의 이름만 가져옴
			FileRead(fileName);

		} else if (e.getActionCommand() == "저장") {
			if (getTitle().equals("새 파일")) {
				JFileChooser fc = new JFileChooser();
				if (fc.showSaveDialog(Memo.this) == JFileChooser.CANCEL_OPTION)
					return;
				File f = fc.getSelectedFile();
				fileName = f.getPath();
				setTitle(f.getName());
				FileSave(fileName);
				
			} else {
				FileSave(fileName);// 타이틀이 존재할경우에는 그냥 세이브로 진행
			}

		} else if (e.getActionCommand() == "다른 이름으로 저장") {
			if (chooser.showSaveDialog(Memo.this) == JFileChooser.CANCEL_OPTION)
				return;
			File file = chooser.getSelectedFile();
			String fileName = file.getPath();
			FileSave(fileName);
		} else if (e.getActionCommand() == "종료") {
			System.exit(0);
		} else if (e.getActionCommand() == "잘라내기") {
			area.cut();
		} else if (e.getActionCommand() == "복사") {
			area.copy();
		} else if (e.getActionCommand() == "붙여넣기") {
			area.paste();
		} else if (e.getActionCommand() == "삭제") {
			area.replaceSelection("");
		} else if (e.getActionCommand() == "찾기") {
			Search_Frame search_frame = new Search_Frame(this);
			search_frame.setSize(410, 110);
			search_frame.setTitle("찾기");
			search_frame.setVisible(true);
		}else if (e.getActionCommand() == "글꼴") {
			Font_Frame font_frame=new Font_Frame(this);
			font_frame.setSize(450,330);
			font_frame.show();
			area.setFont(font_frame.fontSet());
		}else if (e.getActionCommand() == "계산기") {
			Calcurator cal=new Calcurator();
			cal.setTitle("계산기");
			cal.setSize(400,350);
		}

	}

	// 파일 열기
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

	// 파일 저장
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
