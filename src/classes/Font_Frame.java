package classes;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


class Font_Frame extends Dialog implements ActionListener {
	JLabel la_font;
	JLabel la_style;
	JLabel la_size;
	JLabel la_pretext;
	JLabel la_preview;
	JPanel p_bt;
	JTextField t_font;
	JTextField t_style;
	JTextField t_size;
	List list_font;
	List list_style;
	List list_size;
	JButton b_ok;
	JButton b_cancel;
	
	Font f;
	String fontname;
	int fontstyle;
	int size;
	
	String[] Fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	String[] Sizes = { "8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36","48", "72" };
	String[] Styles = { "보통", "기울임꼴", "굵게", "굵은 기울임꼴" };

	Font_Frame(Frame parent) {
		super(parent, "글꼴", true);
		setLayout(null);
		
		la_font=new JLabel("글꼴");
		la_style=new JLabel("글꼴 스타일");
		la_size=new JLabel("크기");
		la_pretext=new JLabel("미리보기");
		la_preview=new JLabel();
		p_bt=new JPanel();
		t_font=new JTextField();
		t_style=new JTextField();
		t_size=new JTextField();
		list_font=new List();
		list_style=new List();
		list_size=new List();
		b_ok=new JButton("확인");
		b_cancel=new JButton("취소");
		
		for (int i = 0; i < Fonts.length; i++) {
			list_font.add(Fonts[i]);
		}
		for (int i = 0; i < Sizes.length; i++) {
			list_size.add(Sizes[i]);
		}
		for (int i = 0; i < Styles.length; i++) {
			list_style.add(Styles[i]);
		}
		
		la_font.setBounds(10, 24, 98, 26);
		la_style.setBounds(198, 26, 98, 26);
		la_size.setBounds(340, 24, 98, 26);
		la_pretext.setBounds(266, 187, 55, 25);
		p_bt.setBounds(205, 270, 320, 200);
		t_font.setBounds(10, 49, 181, 24);
		t_style.setBounds(201, 49, 129, 24);
		t_size.setBounds(340, 49, 91, 24);
		list_font.setBounds(10, 77, 181, 100);
		list_style.setBounds(201, 77, 129, 100);
		list_size.setBounds(340, 77, 91, 100);
		
		b_ok.setBackground(Color.LIGHT_GRAY);
		b_cancel.setBackground(Color.LIGHT_GRAY);
		
		list_font.addActionListener(this);
		list_style.addActionListener(this);
		list_size.addActionListener(this);
		b_ok.addActionListener(this);
		b_cancel.addActionListener(this);
		
		add(p_bt);
		p_bt.add(b_ok);
		p_bt.add(b_cancel);
		add(la_pretext);
		add(la_font);
		add(t_font);
		add(list_font);
		add(list_size);
		add(la_size);
		add(t_size);
		add(list_style);
		add(t_style);
		add(la_style);

		la_preview.setBounds(180, 210, 250, 60);
		la_preview.setText("가나다AaBbYyZz");
		la_preview.setOpaque(true);
		la_preview.setBackground(Color.WHITE);
		la_preview.setBorder(BorderFactory.createEtchedBorder());
		add(la_preview);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
			}
		});
	}

	public Font fontSet() {
		list_font.getSelectedItem();
		fontname = list_font.getSelectedItem();

		if (list_style.getSelectedItem().equals("보통"))
			fontstyle = Font.PLAIN;
		if (list_style.getSelectedItem().equals("기울임꼴"))
			fontstyle = Font.ITALIC;
		if (list_style.getSelectedItem().equals("굵게"))
			fontstyle = Font.BOLD;
		if (list_style.getSelectedItem().equals("굵은 기울임꼴"))
			fontstyle = Font.ITALIC + Font.BOLD;

		t_size.setText(list_size.getSelectedItem());
		size = Integer.parseInt(list_size.getSelectedItem());

		return f = new Font(fontname, fontstyle, size);
	}

	public void actionPerformed(ActionEvent e) {
		if ((e.getSource()).equals(list_font)) {
			t_font.setText(list_font.getSelectedItem());
			fontname = list_font.getSelectedItem();
			f = new Font(fontname, fontstyle, size);
			la_preview.setFont(f);
		} else if ((e.getSource()).equals(list_style)) {
			t_style.setText(list_style.getSelectedItem());

			if (list_style.getSelectedItem().equals("보통"))
				fontstyle = Font.PLAIN;
			if (list_style.getSelectedItem().equals("기울임꼴"))
				fontstyle = Font.ITALIC;
			if (list_style.getSelectedItem().equals("굵게"))
				fontstyle = Font.BOLD;
			if (list_style.getSelectedItem().equals("굵은 기울임꼴"))
				fontstyle = Font.ITALIC + Font.BOLD;

			f = new Font(fontname, fontstyle, size);
			la_preview.setFont(f);
		} else if ((e.getSource()).equals(list_size)) {
			t_size.setText(list_size.getSelectedItem());
			size = Integer.parseInt(list_size.getSelectedItem());
			f = new Font(fontname, fontstyle, size);
			la_preview.setFont(f);
		} else if ((e.getSource()).equals(b_ok)) {
			dispose();
		} else if ((e.getSource()).equals(b_cancel)) {
			dispose();
		}
	}
}