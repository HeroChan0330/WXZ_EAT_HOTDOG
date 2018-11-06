package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AlertWindow extends JFrame{
	public AlertWindow(String title,String headLine,Runnable onCallBack) {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(320, 120);
		this.setResizable(false);
		this.setTitle(title);
		JLabel jLabel=new JLabel(headLine);
		jLabel.setBounds(0,0,320,60);
		jLabel.setHorizontalAlignment(SwingConstants .CENTER);
		jLabel.setVerticalAlignment(SwingConstants .CENTER);
		JButton jButton=new JButton("OK");
		jButton.setBounds(240,60,60,30);
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				onCallBack.run();
				AlertWindow.this.hide();
			}
		});
		this.setLayout(null);
		this.add(jButton);
		this.add(jLabel);
	}
	
}
