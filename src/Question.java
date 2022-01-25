import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/** Κάθε αντικείμενο της κλάσης Question αποτελείται απο όλες τις μεταβλητές που προσδιορίζουν μια ερώτηση:
 * την εκφώνηση, την κατηγορία της,  4 πιθανές απαντήσεις και την σωστή απάντηση. Περιλαμβάνει επίσης
 * μεθόδους εκτύπωσης ερωτήσεων και απαντήσεων σε τυχαία σειρά. */

public class Question {
    private Random random = new Random();
    private ArrayList<Question> allQuestions;
    private String question;
    private String possibleAnswerA, possibleAnswerB, possibleAnswerC, possibleAnswerD;
    private String category;
    private int rightAnswer;

    public Question(String question, String possibleAnswerA, String possibleAnswerB, String possibleAnswerC,
                    String possibleAnswerD, int rightAnswer, String category) {

        this.question = question;
        this.possibleAnswerA = possibleAnswerA;
        this.possibleAnswerB = possibleAnswerB;
        this.possibleAnswerC = possibleAnswerC;
        this.possibleAnswerD = possibleAnswerD;
        this.rightAnswer = rightAnswer;
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    String getPossibleAnswerA() {
        return possibleAnswerA;
    }

    String getPossibleAnswerB() {
        return possibleAnswerB;
    }

    String getPossibleAnswerC() {
        return possibleAnswerC;
    }

    String getPossibleAnswerD() {
        return possibleAnswerD;
    }

    int getRightAnswer() {
        return rightAnswer;
    }

    public String getCategory() {
        return category;
    }
/** Η παρακάτω μέθοδος εκτυπώνει την εκφώνηση της ερώτησης και καλεί
 * την printPossibleAnswers() για να εκτυπώσει τις πιθανές απαντήσεις */

void printQuestion(int number) {
        System.out.println("Question: \n");
        System.out.print(number + ")");
        System.out.println(getQuestion());
        System.out.print("Possible Answers: \n");
        printPossibleAnswers();
    }

    /** Η παρακάτω μέθοδος  εκτυπώνει με τυχαία σειρά τις απαντήσεις μιας ερώτησης. Δημιουργεί ένα hashset
     * που αποθηκεύει τις πιθανές απαντήσεις που έχουν ήδη εκτυπωθεί ώστε να μην εκτυπωθούν ξανά,
     * και τυχαία εκτυπώνει μια απο τις μη εκτυπωμένες */

    private void printPossibleAnswers() {
        boolean flag=false;
    String temp;
        HashSet<Integer> visited = new HashSet<>();
        while (visited.size() < 4) {
            int i = random.nextInt(4);
            //System.out.print(i);
            switch (i) {
                case 0:
                    if (!visited.contains(i)) {
                        System.out.print((visited.size()+1)+ ") ");
                        System.out.print(getPossibleAnswerA()+ "\n");
                        if(this.rightAnswer==1&& !flag) {
                            this.rightAnswer = visited.size()+1;
                            flag=true;
                        }
                        visited.add(0);
                    }
                case 1:
                    if (!visited.contains(i)) {
                        System.out.print((visited.size()+1)+ ") ");
                        System.out.print(getPossibleAnswerB()+"\n");
                        if(this.rightAnswer==2&& !flag) {
                            this.rightAnswer=visited.size()+1;
                            flag=true;
                        }
                        visited.add(1);
                    }
                case 2:
                    if (!visited.contains(i)) {
                        System.out.print((visited.size()+1)+ ") ");
                        System.out.print(getPossibleAnswerC()+"\n");
                        if(this.rightAnswer==3&& !flag) {
                            this.rightAnswer=visited.size()+1;
                            flag=true;
                        }
                        visited.add(2);
                    }
                case 3:
                    if (!visited.contains(i)) {
                        System.out.print((visited.size()+1)+ ") ");
                        System.out.print(getPossibleAnswerD()+"\n");
                        if(this.rightAnswer==4&& !flag) {
                            this.rightAnswer=visited.size()+1;
                            flag=true;
                        }
                        visited.add(3);
                    }


            }
        }

    }
}
