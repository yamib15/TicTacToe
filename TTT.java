package Lecture24;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TTT extends JFrame implements ActionListener {

	public static final int BOARD_SIZE = 3;

	private static enum GameStatus {
		Xwins, Zwins, Incomplete, Tie
	}

	boolean crossTurn = true;
	private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];

	TTT() {
		super.setSize(800, 800);
		super.setTitle("Tic Tac Toe");
		GridLayout grid = new GridLayout(BOARD_SIZE, BOARD_SIZE);
		super.setLayout(grid);
		Font font = new Font("Comic Sans", 1, 150);
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				JButton button = new JButton("");
				buttons[i][j] = button;
				button.setFont(font);
				button.addActionListener(this);
				super.add(button);
			}
		}
		super.setResizable(false);
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton clickedButton = (JButton) e.getSource();
		makeMove(clickedButton);
		GameStatus gs = getgamestatus();
		if(gs==GameStatus.Incomplete){
			
		}else{
			declareWinner(gs);
			int choice = JOptionPane.showConfirmDialog(this, "Do you want to play again?");
			if (choice == JOptionPane.YES_OPTION) {
				for (int i = 0; i < BOARD_SIZE; i++) {
					for (int j = 0; j < BOARD_SIZE; j++) {
						buttons[i][j].setText("");
					}
				}
				crossTurn = true;
			} else {
				super.dispose();
			}
		}
		
			
		
	}

	public void makeMove(JButton clickedButton) {
		if (clickedButton.getText().length() > 0) {
			JOptionPane.showMessageDialog(this, "INVALID MOVE");
			return;
		}
		if (crossTurn) {
			clickedButton.setText("X");
		} else {
			clickedButton.setText("0");
		}
		crossTurn = !crossTurn;
		
	}

	private void declareWinner(GameStatus ob) {	
	
		if (ob == GameStatus.Xwins) {
			JOptionPane.showMessageDialog(this, "X Wins");
		} else if (ob == GameStatus.Zwins) {
			JOptionPane.showMessageDialog(this, "Z Wins");
		} else if (ob == GameStatus.Tie) {
			JOptionPane.showMessageDialog(this, "It's a tie");
		}
	}

	public GameStatus getgamestatus() {

		String text1 = "", text2 = "";
		int row = 0, col = 0;
		while (row < BOARD_SIZE) {
			col = 0;
			while (col < BOARD_SIZE - 1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row][col + 1].getText();
				if (text1 == "" || text2 == "" || !text1.equals(text2)) {
					break;
				}
				col++;
			}
			if (col == BOARD_SIZE - 1) {
				String winner = buttons[row][col].getText();
				if (winner.equals("X")) {
					return GameStatus.Xwins;
				} else {
					return GameStatus.Zwins;
				}
			}
			row++;
		}
		row = 0;
		col = 0;

		while (col < BOARD_SIZE) {
			row = 0;
			while (row < BOARD_SIZE - 1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row + 1][col].getText();
				if (text1 == "" || text2 == "" || !text1.equals(text2)) {
					break;
				}
				row++;
			}
			if (row == BOARD_SIZE - 1) {
				String winner = buttons[row][col].getText();
				if (winner.equals("X")) {
					return GameStatus.Xwins;
				} else {
					return GameStatus.Zwins;
				}
			}
			col++;
		}
		row=0;
		col=0;
		
		while (row < BOARD_SIZE - 1) {
			text1 = buttons[row][col].getText();
			text2 = buttons[row + 1][col + 1].getText();
			if (text1 == "" || text2 == "" || !text1.equals(text2)) {
				break;
			}
			row++;
			col++;

			if (row == BOARD_SIZE - 1) {
				String winner = buttons[row][col].getText();
				if (winner.equals("X")) {
					return GameStatus.Xwins;
				} else {
					return GameStatus.Zwins;
				}
			}

		}
		row = BOARD_SIZE - 1;
		col = 0;
		while (row > 0) {
			text1 = buttons[row][col].getText();
			text2 = buttons[row - 1][col + 1].getText();
			if (text1 == "" || text2 == "" || !text1.equals(text2)) {
				break;
			}
			row--;
			col++;
			if (row == 0) {
				String winner = buttons[row][col].getText();
				if (winner.equals("X")) {
					return GameStatus.Xwins;
				} else {
					return GameStatus.Zwins;
				}
			}
		}
		row=0;
		col=0;
		
		
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if(buttons[i][j].getText().equals("")){
					return GameStatus.Incomplete;
					
				}
			}
		}
		return GameStatus.Tie;
		
	}

}
