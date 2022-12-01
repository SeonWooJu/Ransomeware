package com.joke.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.joke.logic.ButtonLogic;
import com.joke.object.PathAndResult;

public class NewView extends JDialog{

    public void Loading(String str) {
        setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);
        setTitle(str);
        setSize(300, 150);
		
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        
        JLabel la = new JLabel(str);
        la.setLocation(115, 50);
        la.setSize(200, 20);

        this.add(la);
    }

    public void Report(List<PathAndResult> pathAndResultsList, String str) {
        setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);
        setTitle(str);
        setSize(700, 500);

        JTextArea jTextArea = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(jTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setBounds(WIDTH + 10, 10, 663, 443);

        for (PathAndResult pathAndResult : pathAndResultsList) {
            jTextArea.append(pathAndResult.getPath() + "\n" + pathAndResult.getResult() + "\n=======================\n");
        }
        
        this.add(jScrollPane);
    }

    public void PwInput(String key, String iv) {
        setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);
        setTitle("pw");
        setSize(200, 150);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(30, 20));

        JTextField jTextField = new JTextField();

        JLabel jLabel = new JLabel("암호 입력");
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setVerticalAlignment(SwingConstants.CENTER);

        JButton jButton = new JButton("Enter");
        jButton.addActionListener(event -> {
            if ("I_love_university".equals(jTextField.getText())) {
                jLabel.setText("암호가 맞았습니다.");
                new ButtonLogic().Decode(key, iv, jTextField.getText());
            } else {
                jLabel.setText("암호가 틀렸습니다.");
            }
        });

        contentPane.add(jTextField, BorderLayout.CENTER);
        contentPane.add(jLabel, BorderLayout.NORTH);
        contentPane.add(jButton, BorderLayout.SOUTH);
    }

    public void Close() {
        dispose();
    }

    public void Exit() {
        System.exit(0);
    }
}
