import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GUI implements ActionListener {
    JFrame frame = new JFrame("Tic Tac Toe");
    JButton[] buttons = new JButton[9];
    JPanel btnGridPanel = new JPanel();
    JPanel btnPanel = new JPanel();
    JButton singlePlayer, restart;
    Dialog dialogBox;

    Game game = new Game(this);

    public GUI() {
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        //Initialize Buttons
        btnGridPanel.setLayout(new GridLayout(3, 3));
        for(int i = 0; i < 9; i++){
            buttons[i] = new JButton();
            buttons[i].setText("");
            buttons[i].setFont(new Font("Ink Free", Font.BOLD, 120));
            buttons[i].addActionListener(this);
            btnGridPanel.add(buttons[i]);
        }

        //Bottom button panel
        singlePlayer = new JButton(new AbstractAction("Single Player") {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setSinglePlayer(true);
                singlePlayer.setBackground(Color.GREEN);
                setEnabled(false);
            }
        });

        restart = new JButton(new AbstractAction("Restart Game") {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JButton btn: buttons){
                    btn.setEnabled(true);
                    btn.setText("");
                }
                singlePlayer.setEnabled(true);
                singlePlayer.setBackground(null);
                game.resetVariables();
            }
        });

        btnPanel.add(singlePlayer);
        btnPanel.add(restart);

        frame.add(btnGridPanel);
        frame.add(btnPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!game.isSinglePlayer()){
            singlePlayer.setEnabled(false);
            singlePlayer.setBackground(Color.RED);
        }

        for(int i = 0; i < buttons.length; i++){
            JButton btn = buttons[i];
            if(e.getSource() == btn){
                if(btn.getText().equals("")){
                    btn.setText(game.getCurrentPlayer());
                    btn.setEnabled(false);
                    game.playGame(i);
                    gameOverScreen();
                }
            }
        }
    }

    public void gameOverScreen(){
        if(game.getGameOver()){
            //Disable all buttons
            for(JButton btn: buttons){
                btn.setEnabled(false);
            }

            dialogBox = new JDialog(frame, "Game Over", true);
            dialogBox.setSize(300, 200);
            if(game.getWinner().equals("X") || game.getWinner().equals("O")){
                Label label = new Label(game.getWinner() + " is the Winner!", Label.CENTER);
                dialogBox.add(label);
                dialogBox.setVisible(true);
            }else if(game.getWinner().equals("Game Draw")){
                Label label = new Label(game.getWinner(), Label.CENTER);
                dialogBox.add(label);
                dialogBox.setVisible(true);
            }
        }
    }
}
