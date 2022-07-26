package Sudoku;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp implements ActionListener {

    JButton submit = new JButton("Solve"), refresh = new JButton("Refresh");
    JTextField[][] textFields;
    JLabel status;
    JLabel result;

    public MainApp() {
        JFrame frame = new JFrame("Sudoku Solver");
        frame.setSize(375, 535);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
        submit.addActionListener(this);
        refresh.addActionListener(this);
        textFields = new JTextField[9][9];
        JLabel label = new JLabel("Sudoku");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.BLUE);
        label.setBounds(135, 7, 100, 20);
        frame.getContentPane().add(label);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                textFields[i][j] = new JTextField();
                textFields[i][j].setBounds(i * 40, 40+(j * 40), 40, 40);
                frame.getContentPane().add(textFields[i][j]);
            }
        }
        submit.setBounds(60, 440, 100, 50);
        frame.getContentPane().add(submit);
        refresh.setBounds(180, 440, 100, 50);
        frame.getContentPane().add(refresh);
        status = new JLabel("Status: ");
        status.setBounds(120, 405, 50, 20);
        frame.getContentPane().add(status);
        result = new JLabel("");
        result.setBounds(165, 405, 120, 20);
        result.setForeground(Color.RED);
        frame.getContentPane().add(result);
    }

    public void actionPerformed(ActionEvent e){
		if (e.getSource() == submit) {
			SudokuSolver sol = new SudokuSolver();
            char[][] board = new char[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    char c;
                    if(textFields[i][j].getText().equals("")) {
                        c = '.';
                    }
                    else {
                        c = textFields[i][j].getText().charAt(0);
                        textFields[i][j].setFont(new Font("Arial", Font.BOLD, 14));
                    }
                    board[i][j] = c;
                }
            }
            ValidityCheck verifier = new ValidityCheck();

            if(!verifier.isValidSudoku(board)) {
                result.setText("Invalid Input!");
                return;
            }

            result.setText("Solving...");

            char[][] solved = sol.solveSudoku(board);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    textFields[i][j].setText(Character.toString(solved[i][j]));
                }
            }
            result.setText("Solved");
        }
        if(e.getSource() == refresh) {
            for(int i=0; i<9; i++) {
                for(int j=0; j<9; j++) {
                    textFields[i][j].setText("");
                    textFields[i][j].setFont(null);
                }
            }
            result.setText("");
        }
	}

    public static void main(String[] args) {
        new MainApp();
    }
}