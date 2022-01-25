import java.util.Scanner;

/** Κλάση που βοηθάει στην υλοποίηση του κάθε γύρου */

public class Round {
    private AllQuestions allQuestions;

    Round() {
        allQuestions = new AllQuestions();
        allQuestions.createQuestionArray();
    }
/** Σωστή απάντηση για έναν παίκτη */
int rightAnswerForOne(boolean isCorrect){
        if(isCorrect)
            return 1000;
        else
            return 0;
    }

/** Παιχνίδι πονταρίσματος για έναν παίκτη */

int bettingGameforOne(int bet, boolean isCorrect){
    if(isCorrect)
        return bet;
    else {
        return (-1 * bet);
    }
    }




    double CountdownForOne(int seconds, boolean rightAnswer){
        if(rightAnswer)
            return seconds*200;
        else
            return 0;
    }




    public int bettingGameForTwoPlayers(int j) {
        Scanner sc = new Scanner(System.in);
        int winner = 0;
        Question currentQuestion = allQuestions.getNextQuestion();

        currentQuestion.printQuestion(j + 1);
        int rightAnswer = currentQuestion.getRightAnswer();
        int player1Answer = sc.nextInt(); // διαβασε την απάντηση του χρήστη
        int player2Answer = sc.nextInt();
        //έλεγχος εγκυρότητας της επιλογής του χρήστη
        while (player1Answer != 1 && player1Answer != 2 && player1Answer != 3 && player1Answer != 4) {
            System.out.print("There is no choice with this number Player 1. Choose one between 1,2,3 or 4. \n");
            player1Answer = sc.nextInt();
        }
        while (player2Answer != 1 && player2Answer != 2 && player2Answer != 3 && player2Answer != 4) {
            System.out.print("There is no choice with this number Player 2. Choose one between 1,2,3 or 4. \n");
            player2Answer = sc.nextInt();
        }
        if (player1Answer == rightAnswer && player2Answer == rightAnswer)
            winner = 0;

        if (player1Answer == rightAnswer && player2Answer != rightAnswer)
            winner = 1;

        if (player1Answer != rightAnswer && player2Answer == rightAnswer)
            winner = 2;

        if (player1Answer != rightAnswer && player2Answer != rightAnswer)
            winner = 3;

        return winner;


    }
}