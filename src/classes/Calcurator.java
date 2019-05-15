package classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calcurator extends JFrame implements ActionListener {
	//변수 선언
	JButton bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0;
	JButton equal, plus, minus, divide, multiple, clear, clearAll, back;
	JTextField text;
	JLabel la, la2;
	boolean flag, Flag2;
	JPanel p1, p2;
	String num1 = "", num2 = "", operator = "";
	int i, a, result;
	Dialog dial;

	public Calcurator() {
		
		setLayout(new BorderLayout());

		la = new JLabel();
		la.setBackground(Color.lightGray);
		la2=new JLabel();
		la2.setBackground(Color.lightGray);

		bt1 = new JButton("1");
		bt2 = new JButton("2");
		bt3 = new JButton("3");
		bt4 = new JButton("4");
		bt5 = new JButton("5");
		bt6 = new JButton("6");
		bt7 = new JButton("7");
		bt8 = new JButton("8");
		bt9 = new JButton("9");
		bt0 = new JButton("0");

		equal = new JButton("=");
		plus = new JButton("+");
		minus = new JButton("-");
		divide = new JButton("/");
		multiple = new JButton("*");
		

		clear = new JButton("C");
		clearAll = new JButton("CE");
		back = new JButton("←");

		text = new JTextField(30);
		text.setPreferredSize(new Dimension(50,30));
		text.setFont(new Font("굴림", Font.BOLD, 15));
		text.setEditable(false); // textfield 의 값을 수정 못하게 

		p1 = new JPanel(new FlowLayout());
		p2 = new JPanel(new GridLayout(5, 4, 1, 1));

		bt1.setBackground(Color.LIGHT_GRAY);
		bt2.setBackground(Color.LIGHT_GRAY);
		bt3.setBackground(Color.LIGHT_GRAY);
		bt4.setBackground(Color.LIGHT_GRAY);
		bt5.setBackground(Color.LIGHT_GRAY);
		bt6.setBackground(Color.LIGHT_GRAY);
		bt7.setBackground(Color.LIGHT_GRAY);
		bt8.setBackground(Color.LIGHT_GRAY);
		bt9.setBackground(Color.LIGHT_GRAY);
		bt0.setBackground(Color.LIGHT_GRAY);
		equal.setBackground(Color.LIGHT_GRAY);
		plus.setBackground(Color.LIGHT_GRAY);
		minus.setBackground(Color.LIGHT_GRAY);
		divide.setBackground(Color.LIGHT_GRAY);
		multiple.setBackground(Color.LIGHT_GRAY);
		clear.setBackground(Color.LIGHT_GRAY);
		clearAll.setBackground(Color.LIGHT_GRAY);
		back.setBackground(Color.LIGHT_GRAY);
				
		bt1.setFont(new Font("굴림", Font.BOLD, 35));
		bt2.setFont(new Font("굴림", Font.BOLD, 35));
		bt3.setFont(new Font("굴림", Font.BOLD, 35));
		bt4.setFont(new Font("굴림", Font.BOLD, 35));
		bt5.setFont(new Font("굴림", Font.BOLD, 35));
		bt6.setFont(new Font("굴림", Font.BOLD, 35));
		bt7.setFont(new Font("굴림", Font.BOLD, 35));
		bt8.setFont(new Font("굴림", Font.BOLD, 35));
		bt9.setFont(new Font("굴림", Font.BOLD, 35));
		bt0.setFont(new Font("굴림", Font.BOLD, 35));
		equal.setFont(new Font("굴림", Font.BOLD, 35));
		plus.setFont(new Font("굴림", Font.BOLD, 35));
		minus.setFont(new Font("굴림", Font.BOLD, 35));
		divide.setFont(new Font("굴림", Font.BOLD, 35));
		multiple.setFont(new Font("굴림", Font.BOLD, 35));
		clear.setFont(new Font("굴림", Font.BOLD, 35));
		clearAll.setFont(new Font("굴림", Font.BOLD, 35));
		back.setFont(new Font("굴림", Font.BOLD, 35));
		
		p1.add(text);

		
		p2.add(clearAll);
		p2.add(clear);
		p2.add(back);
		p2.add(la);

		p2.add(bt7);
		p2.add(bt8);
		p2.add(bt9);
		p2.add(plus);
		p2.add(bt4);
		p2.add(bt5);
		p2.add(bt6);
		p2.add(minus);
		p2.add(bt1);
		p2.add(bt2);
		p2.add(bt3);
		p2.add(multiple);
		p2.add(la2);
		p2.add(bt0);
		p2.add(equal);
		p2.add(divide);

		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		bt4.addActionListener(this);
		bt5.addActionListener(this);
		bt6.addActionListener(this);
		bt7.addActionListener(this);
		bt8.addActionListener(this);
		bt9.addActionListener(this);
		bt0.addActionListener(this);
		plus.addActionListener(this);
		minus.addActionListener(this);
		multiple.addActionListener(this);
		divide.addActionListener(this);
		equal.addActionListener(this);

		clear.addActionListener(this);
		clearAll.addActionListener(this);
		back.addActionListener(this);

		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
			}
		});

		setResizable(false); // 사이즈 조정을 못하게
		setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		String save = e.getActionCommand();
		if (save == "1" || save == "2" || save == "3" || save == "4" || save == "5" || save == "6" || save == "7" || save == "8" || save == "9" || save == "0") {
			if (flag == false) { //false 라면 연산자가 아직 안들어왔다
				num1 = num1 + save;
				text.setText(num1);
			} else { //  true 라면 연산자가 이미 들어왔다. 새로운 변수에 담아야 한다.
				num2 = num2 + save;
				text.setText(num2);
			}
		}

		// 연산자가 들어왔을때 그 연산자를 변수에 담아놓는다.
		if (save == "+" || save == "-" || save == "*" || save == "/") {
			flag = true;
			Flag2 = false;
			operator = save;
			text.setText("");
		}
		
		// equal 의 액션이 발생했을 경우 String 변수에 담긴 값을 숫자형으로 형변환
		if (save == "=") {
			if (flag == false) {
				JOptionPane.showMessageDialog(this, "제대로 된 계산을 수행하시오");
			} else {
				i = Integer.parseInt(num1); // 실수 연산을 위해 int형으로 받아온다.
				a = Integer.parseInt(num2); 

				if (operator == "+") { 
					result = i + a;
					text.setText("" + result);
				}
				if (operator == "-") { 
					result = i - a;
					text.setText("" + result);
				}
				if (operator == "*") { 
					result = i * a;
					text.setText("" + result);
				}
				if (operator == "/") { 
					if (i == 0 || a == 0) {
						text.setText("0으로 나눠지지않음");
					} else {
						result = i / a;
						text.setText("" + result);
					}
				}
			}
		}

		if (save == "CE") { 
			num1 = "";
			num2 = "";
			i = 0;
			a = 0;
			result = 0;
			flag = false;
			Flag2 = false;
			text.setText("");
		}
		if (save == "C") { 
			if (flag == false) {
				num1 = "";
				text.setText("");
			} else {
				num2 = "";
				text.setText("");
			}
		}
		if (save == "←") { 
			backSpace();
		}
	} 

	public void backSpace() {
		if (flag == false) {
			num1 = num1.substring(0, num1.length() - 1);
			text.setText(num1);
		} else {
			num2 = num2.substring(0, num2.length() - 1);
			text.setText(num2);
		}
	}
}
