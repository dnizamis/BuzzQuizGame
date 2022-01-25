import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Αυτή η κλάση  περιέχει το frame που ρωτάει τους χρήστες αν θέλουν να παίξουν, εμφανίζεται
 * στην αρχή του προγράμματος αλλα και στο τέλος ενός παιχνιδιού */

public class GuiStart {
    private JFrame EndGameFrame;
    private JPanel EndTop;
    private JPanel EndBottom;
    private JTextArea EndMessage;
    private int highScore;
    private int wins1;
    private int wins2;
    private String user1;
    private String user2;

    GuiStart(){
        wins1=0;
        wins2=0;
        highScore=0;
        EndGameFrame= new JFrame("End of Game");
        EndGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EndGameFrame.setLayout(new BorderLayout());
        EndGameFrame.setSize(600, 200);
        EndGameFrame.setResizable(false);
        EndTop = new JPanel();
        EndTop.setLayout(new BorderLayout());
        EndGameFrame.add(EndTop, BorderLayout.NORTH);

        EndBottom = new JPanel();
        EndBottom.setLayout(new GridLayout(1, 0));
        EndGameFrame.add(EndBottom);
        JTextArea EndMessageScore = new JTextArea("");
        EndMessageScore.setFont(new Font("MV Boli", Font.BOLD, 25));
        EndMessageScore.setLineWrap(true);
        EndMessageScore.setEditable(false);
        EndTop.add(EndMessageScore, BorderLayout.NORTH);

        EndMessage = new JTextArea("Are you ready to play?");
        EndMessage.setFont(new Font("MV Boli", Font.BOLD, 25));
        EndMessage.setLineWrap(true);
        EndMessage.setEditable(false);
        EndTop.add(EndMessage, BorderLayout.CENTER);

        JButton GotoMenu = new JButton("Sure! ");
        GotoMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EndGameFrame.setVisible(false);
                GUI again=new GUI();
                again.start();


            }
        });
        EndBottom.add(GotoMenu);

        JButton QuitGame = new JButton("Not Really!");
        QuitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        EndBottom.add(QuitGame);
    }
    /**Καλείται στο τέλος ενός παιχνιδιού ενός παίκτη, εμφανίζει το σκορ
     * και τις επιλογές να παίξει ή να σταματήσει */
    void endGame(int score){
        EndMessage.setText("Congratulations! Your score is: " +score +"! Do you want to play again?");
        EndGameFrame.setVisible(true);
    }
    /**Καλείται στο τέλος ενός παιχνιδιού δυο παικτών, εμφανίζει το σκορ
     * και τις επιλογές να παίξουν ή να σταματήσουν */
    void endGameForTwo(int score, int score2, String user, String user2){
        user1=user;
        this.user2=user2;
    if(score>score2) {
        EndMessage.setText("End of Game! The winner is " + user + "with " + score +
                " points! " + user2 + " has " + score2 + " points. Play again?");
        wins1++;
    }
    else if(score<score2) {
        EndMessage.setText("End of Game! The winner is " + user2 + "with " + score2 +
                " points! " + user + " has " + score + " points. Play again?");
    }
    else {
        EndMessage.setText("End of Game! Draw! Both " + user + " and " + user2 + "have " + score + " points. Play again?");
    }
        EndGameFrame.setVisible(true);
    }
    /** μέθοδος να εμφανιστεί το frame  */
    public void start(){
        EndGameFrame.setVisible(true);
    }
    public void updateHighScore(int score){
     if(score>highScore)
         highScore=score;
    }

    void showScore(){
        EndMessage.setText("Highscore for one player is: "+ highScore+ ".");
        EndGameFrame.setVisible(true);
    }


    /**μέθοδος να μην φαίνεται το frame */
    public void hide(){
        EndGameFrame.setVisible(false);
    }
}
