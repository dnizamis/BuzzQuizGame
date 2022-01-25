
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** η κλάση AllQuestions περιλαμβάνει τις μεθόδους της μαζικής διαχείρισης των ερωτήσεων, δηλαδή οι μέθοδοι της καλούνται
 * για να φορτώσουν όλες τις ερωτήσεις σε ένα ArrayList και να επιστρέψουν
 * τις ερωτήσεις και τις απαντήσεις που πρέπει να εμφανιστούν κάθε φορά στο χρήστη. */

class AllQuestions {
    private Random random=new Random();
    private ArrayList<Question> allQuestions;

    /** Η παρακάτω μέθοδος δημιουργεί μία Arraylist τύπου Question με όλες τις ερωτήσεις του παιχνιδιού
     * τις πιθανές απαντήσεις και την σωστή απάντηση, φορτώνοντας τις απο ένα αρχείο */
    void createQuestionArray(){
        allQuestions=new ArrayList<>();
      try{
      File Questions=new File("allquestions.txt");
         Scanner scanner=new Scanner(Questions);
         while(scanner.hasNextLine()){
            String que=scanner.nextLine();
            String ansA=scanner.nextLine();
            String ansB=scanner.nextLine();
            String ansC=scanner.nextLine();
            String ansD=scanner.nextLine();
            int rig=Integer.parseInt(scanner.nextLine());
            String cat=scanner.nextLine();
            allQuestions.add(new Question(que,ansA,ansB,ansC,ansD,rig,cat));
         }
         scanner.close();
      }
      catch (FileNotFoundException e){
         System.out.print("An error occured.");
      }


    }

    /** επιστρέφει μια τυχαία ερώτηση απο την Arraylist και τις πιθανές απαντήσεις και
    * την αφαιρεί απο την Arraylist για να μην ξαναεμφανιστεί στο παιχνίδι */
    Question getNextQuestion(){

        int number=random.nextInt(allQuestions.size());
        Question question=allQuestions.get(number);
        allQuestions.remove(number);
        return question;
    }
}
