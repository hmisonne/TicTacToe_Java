package com.example.mypackage;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {
    int size = 3;
    int totalButtons = size*size;
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[totalButtons];
    int[] rows = new int[size];
    int[] columns = new int[size];

    boolean player1_turn;

    TicTacToe(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25,25,25));
        textfield.setForeground(new Color(25,255,0));
        textfield.setFont(new Font("Ink Free",Font.BOLD,75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);

        button_panel.setLayout(new GridLayout(size,size));
        button_panel.setBackground(new Color(150,150,150));

        for(int i=0;i<totalButtons;i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i=0;i<totalButtons;i++) {
            if(e.getSource()==buttons[i]) {
                int rowIndex = i / size;
                int columnIndex = i % size;
                if(buttons[i].getText()=="") {
                    updateUI(i);
                    rows[rowIndex] = player1_turn
                            ? (rows[rowIndex] + 1)
                            : (rows[rowIndex] - 1);
                    columns[columnIndex] = player1_turn
                            ? (columns[columnIndex] + 1)
                            : (columns[columnIndex] - 1);
                    check(rowIndex, columnIndex);
                    player1_turn= !player1_turn;
                }


            }
        }
    }

    public void updateUI(int i){
        if(player1_turn){
            buttons[i].setForeground(new Color(255,0,0));
            buttons[i].setText("X");
            textfield.setText("O turn");
        } else {
            buttons[i].setForeground(new Color(0,0,255));
            buttons[i].setText("O");
            textfield.setText("X turn");
        }

    }

    public void firstTurn() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(random.nextInt(2)==0) {
            player1_turn=true;
            textfield.setText("X turn");
        }
        else {
            player1_turn=false;
            textfield.setText("O turn");
        }
    }


    public void check(int rowIndex, int columnIndex){
        if (Math.abs(rows[rowIndex]) == size || Math.abs(columns[columnIndex]) == size){
            System.out.println("X winner");
            if (Math.abs(rows[rowIndex])  == size){
                wins(player1_turn, -1, rowIndex);
            }
            else {
                wins(player1_turn, columnIndex, -1);
            }
            System.out.println("Rows: "+Arrays.toString(rows));
            System.out.println("Columns: "+Arrays.toString(columns));
        }
    }

    public void wins(boolean player1_turn, int columnIndex, int rowIndex){
        String player = player1_turn? "X" : "O";
        if(columnIndex != -1 ){
            for (int i = 0 ; i <size ; i ++){
                buttons[columnIndex + (size*i)].setBackground(Color.GREEN);
            }
        }
        else {
            for (int i =0 ; i <size ; i ++){
                buttons[(rowIndex*size)+i].setBackground(Color.GREEN);
            }
        }
        textfield.setText(player+" wins");
    }

}