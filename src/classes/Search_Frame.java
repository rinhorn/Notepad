package classes;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
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

class Search_Frame extends JFrame implements ActionListener {
	JPanel pa_search;
	JPanel pa_cancel;
	JPanel pa_check;
	Checkbox checkbox;
	JLabel la_content;
	JLabel la_check;
	JTextField t_content;
	JButton bt_next;
	JButton bt_cancel;
	Memo memo;
	int num = 0;
	String search_input;
	int search_index;
	String str;

	public Search_Frame(Memo memo) {
		this.memo = memo;
		pa_search = new JPanel();
		pa_cancel = new JPanel();
		pa_check = new JPanel();
		checkbox = new Checkbox(null, true, null);
		la_content = new JLabel("찾을 내용 :  ");
		la_check = new JLabel("대/소문자 구분");
		t_content = new JTextField(20);
		bt_next = new JButton("다음 찾기");
		bt_cancel = new JButton("닫    기");

		pa_search.add(la_content);
		pa_search.add(t_content);
		pa_cancel.add(bt_next);
		pa_cancel.add(bt_cancel);
		pa_check.add(checkbox);
		pa_check.add(la_check);
		add(pa_search, "North");
		add(pa_cancel, "East");
		add(pa_check, FlowLayout.LEFT);

		bt_next.setBackground(Color.LIGHT_GRAY);
		bt_cancel.setBackground(Color.LIGHT_GRAY);

		bt_next.addActionListener(this);
		bt_cancel.addActionListener(this);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "다음 찾기") {
			search_input = t_content.getText();
			search_index = memo.area.getText().indexOf(search_input, num);
			if (checkbox.getState() == false) {
				search_input = t_content.getText().toLowerCase();
				search_index = memo.area.getText().toLowerCase().indexOf(search_input, num);
			}
			
			str = memo.area.getText().replaceAll("\\r", "");
			memo.area.setText(str);
			memo.area.requestFocus();
			for (int i = 0; i < str.length(); i++) {
				if (search_index == i) {
					memo.area.select(search_index, search_index + search_input.length());
					num = (search_index + search_input.length());
				}
			}

			if (num != search_index + search_input.length()) {
				End end = new End("경고", search_input);
				// end.setVisible(true);
				// end.setBounds(200, 200, 200, 100);
			}
		} else {
			this.setVisible(false); // 찾기창닫기
		}
	}
}

class End extends Frame {
	End(String Title, String input) {
		super(Title);
		JOptionPane.showMessageDialog(this, "\"" + input + "\"" + "를 찾을 수 없습니다.");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}

}
