import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Scanner;



/** Η κλάση Game αναπαριστά την αλληλεπίδραση με τον χρήστη, δηλαδή εκτυπώνει μηνύματα,
 * διαβάζει τις εισόδους του χρήστη και καλεί τις κατάλληλες μεθόδους απο την AllQuestions και την Question */
class GUI implements ActionListener {
    /**
     * H μέθοδος start αποτελεί την βασική μέθοδο του user interface και προσομοιώνει το παιχνίδι
     */

    private JFrame frame;
    private JFrame GameplayFrame;
    private JFrame BetgameFrame;
    private JFrame BetgameFrameFor1st;
    private JFrame BetgameFrameFor2nd;
    private JFrame TwoplayersFrame;
    private JFrame ScoreFrame;
    private JFrame ImageFrame;
    private JLabel SecondsLeft;
    private JTextArea BetMessage;
    private JTextArea BetMessageFor1st;
    private JTextArea BetMessageFor2nd;
    private JTextArea QuestionPart;
    private JTextArea CategoryPart;
    private JTextArea QuestionPartFor2;
    private JTextArea CategoryPartFor2;
    private JTextArea AnswerA;
    private JTextArea AnswerB;
    private JTextArea AnswerC;
    private JTextArea AnswerD;
    private JTextArea AnswerAFor1;
    private JTextArea AnswerBFor1;
    private JTextArea AnswerCFor1;
    private JTextArea AnswerDFor1;
    private JTextArea AnswerAFor2;
    private JTextArea AnswerBFor2;
    private JTextArea AnswerCFor2;
    private JTextArea AnswerDFor2;
    private JTextArea Message;
    private JTextArea Message2;
    private JTextArea Message3;
    private JTextArea MessageFor2;
    private JTextArea Message2For2;
    private JTextArea BestScore;
    private int playerAnswer;
    private int playerAnswer2;
    private Question next;
    private int score;
    private int score2;
    private int bestscore=0;
    private boolean answerIsRight;
    private boolean answerIsRight2;
    private int typeOfRound=0;
    private Round round;
    private int bet;
    private int betp1;
    private int betp2;
    private AllQuestions allQuestions;
    ///πόσοι γύροι σε ένα παιχνίδι
    private int numberOfRounds = 4;
    // πόσες ερωτήσεις ανα γύρο
    private int questionsPerRound = 5;
    private int currentQuestion=1;
    private int currentRound=1;
    private Random random;
    private String user1,user2;
    private int Seconds;
    private Timer timer;
    private int firstAnswer;
    private int winsinaRow1;
    private int winsinaRow2;
    private String roundType;




    // θα ξαναδω τα layouts και θα τα προσθεσω
    //Αν δε βγαζει την εικονα ή τα κουμπια, τερματισε το και ξανατρεξτο, δεν τα βγαζει παντα ειναι μαλλον λογω των layouts

    public void start() {

        /**
         * Παρακάτω υλοποιούμε τα 3 βασικα frames του παιχνιδιου και προσθετουμε τα χαρακτηριστικα τους
         * όπως χρώμα,γραμματοσειρά, επεκτασιμότητα κλπ
         * Το frame είναι για το αρχικο μενου
         * Το GamePlayframe είναι για το One player game
         * Το TwplayersFrame είναι για το Two players game
         */

        frame = new JFrame("Buzz! Quiz World Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(700, 600);
        frame.setResizable(false); // Ο χρήστης αν παει στις ακρες του Frame μπορει να το επεκτεινει
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);//Χρωματιζει το background.Το αλλαζουμε αν θελουμε
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon("Buzz-logo.png")),BorderLayout.WEST);
        frame.setVisible(true);

        GameplayFrame = new JFrame("Buzz! Quiz World Game");
        GameplayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GameplayFrame.setLayout(new BorderLayout());
        GameplayFrame.setSize(1000, 700);
        GameplayFrame.setResizable(false);
        GameplayFrame.getContentPane().setBackground(Color.white);
        GameplayFrame.getContentPane().setLayout(new BorderLayout());

        TwoplayersFrame = new JFrame();
        TwoplayersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TwoplayersFrame.setLayout(new BorderLayout());
        TwoplayersFrame.setSize(1000, 830);
        TwoplayersFrame.setResizable(false);


        JLabel welcomeMesage = new JLabel("Welcome to Buzz Quiz World Game!");
        welcomeMesage.setFont(new Font("MV Boli", Font.BOLD, 30)); //Ρυθμιζει την γραμματοσειρα και το μεγεθος του μηνυματος
        frame.getContentPane().add(welcomeMesage,BorderLayout.NORTH);


        /**
         * Δημιουργουνται τα buttons τα οποια γινονται implement μετα το τελος της κλασης
         * Καθώς και τα πανελς για να χωρισουμε τα frames και να τοποθετίσουμε σε διάφορα σημεία
         * Μηνυματα, κουμπια κλπ
         * Επίσης δημιουργουμε Jtext areas για κάθε διαφορετικό κείμενο που θα περιχει το πανελ
         * π.χ. category, question, message to player
         */



        JButton OnePlayer = new JButton("One Player");
        OnePlayer.setBounds(450, 150, 115, 50);
        OnePlayer.addActionListener(this);
        frame.getContentPane().add(OnePlayer,BorderLayout.EAST);

        JButton TwoPlayers = new JButton("Two Players");
        TwoPlayers.setBounds(450, 225, 115, 50);
        TwoPlayers.addActionListener(this);
        frame.getContentPane().add(TwoPlayers,BorderLayout.EAST);

        JButton ScoreButton = new JButton("Score");
        ScoreButton.setBounds(450, 300, 115, 50);
        ScoreButton.addActionListener(this);
        frame.getContentPane().add(ScoreButton,BorderLayout.EAST);

        JButton QuitButton = new JButton("Quit");
        QuitButton.setBounds(450, 375, 115, 50);
        QuitButton.addActionListener( this);
        frame.getContentPane().add(QuitButton,BorderLayout.EAST);


        JPanel Top = new JPanel();
        Top.setLayout(new BorderLayout());
        Top.setBackground(Color.WHITE);
        Top.setPreferredSize(new Dimension(1000,200));
        GameplayFrame.add(Top,BorderLayout.NORTH);

        JPanel Mid = new JPanel();
        Mid.setLayout(new GridLayout(4,2));
        Mid.setBackground(Color.WHITE);
        Mid.setPreferredSize(new Dimension(1000,300));
        GameplayFrame.add(Mid,BorderLayout.CENTER);


        JPanel Bottom = new JPanel();
        Bottom.setLayout(new BorderLayout());
        Bottom.setBackground(Color.WHITE);
        Bottom.setPreferredSize(new Dimension(1000,200));
        GameplayFrame.add(Bottom,BorderLayout.SOUTH);

        CategoryPart = new JTextArea("Category here...");
        CategoryPart.setFont(new Font("MV Boli", Font.BOLD, 25));
        CategoryPart.setLineWrap(true);
        CategoryPart.setEditable(false);
        Top.add(CategoryPart,BorderLayout.NORTH);

        QuestionPart = new JTextArea("Question here...");
        QuestionPart.setFont(new Font("MV Boli", Font.BOLD, 35));
        QuestionPart.setLineWrap(true);
        QuestionPart.setEditable(false);
        Top.add(QuestionPart,BorderLayout.CENTER);

        Message = new JTextArea("Game Starts! ");
        Message.setFont(new Font("MV Boli", Font.BOLD, 30));
        Message.setEditable(false);
        Bottom.add(Message,BorderLayout.NORTH);

        Message2 = new JTextArea("Second message here.. ");
        Message2.setFont(new Font("MV Boli", Font.BOLD, 30));
        Message2.setEditable(false);
        Bottom.add(Message2,BorderLayout.CENTER);

        Message3 = new JTextArea("Game Starts! ");
        Message3.setFont(new Font("MV Boli", Font.BOLD, 30));
        Message3.setEditable(false);
        Bottom.add(Message3,BorderLayout.SOUTH);

        /** Τα μεσαια panels γίναι grid layouts με 4 rows και  1 column
         *  όπου row left τα  button και row right  τα Jtextareas
         *  Γιαυτον τον λόγο υλοποιούνται ενναλάξ */

        JButton Answer1 = new JButton("A");
        Answer1.setPreferredSize(new Dimension(70,70));
        //Answer1.addActionListener(this);
        Action buttonAction1 = new AbstractAction("A") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerAnswer=1;
                checkAnswerOnePlayer();
            }
        };

        Answer1.setAction(buttonAction1);
        buttonAction1.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        Answer1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), "A");

        Answer1.getActionMap().put("A", buttonAction1);
        Mid.add(Answer1);


        AnswerA = new JTextArea("Answer A here ... ");
        AnswerA.setFont(new Font("MV Boli", Font.BOLD, 30));
        AnswerA.setLineWrap(true);
        AnswerA.setEditable(false);
        Mid.add(AnswerA);


        JButton Answer2 = new JButton("B");
        Answer2.setPreferredSize(new Dimension(70,70));
        //Answer2.addActionListener(this);
        Action buttonAction2 = new AbstractAction("B") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerAnswer=2;
                checkAnswerOnePlayer();
            }
        };

        Answer2.setAction(buttonAction2);
        buttonAction2.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        Answer2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), "B");

        Answer2.getActionMap().put("B", buttonAction2);
        Mid.add(Answer2);

        AnswerB = new JTextArea("Answer B here ... ");
        AnswerB.setFont(new Font("MV Boli", Font.BOLD, 30));
        AnswerB.setLineWrap(true);
        AnswerB.setEditable(false);
        Mid.add(AnswerB);

        JButton Answer3 = new JButton("C");
        Answer3.setPreferredSize(new Dimension(70,70));
        //Answer3.addActionListener(this);
        Action buttonAction3 = new AbstractAction("C") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerAnswer=3;
                checkAnswerOnePlayer();
            }
        };

        Answer3.setAction(buttonAction3);
        buttonAction3.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        Answer3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), "C");

        Answer3.getActionMap().put("C", buttonAction3);
        Mid.add(Answer3);

        AnswerC = new JTextArea("Answer C here ... ");
        AnswerC.setFont(new Font("MV Boli", Font.BOLD, 30));
        AnswerC.setLineWrap(true);
        AnswerC.setEditable(false);
        Mid.add(AnswerC);

        JButton Answer4 = new JButton("D");
        Answer4.setPreferredSize(new Dimension(70,70));
        //Answer4.addActionListener(this);
        Action buttonAction4 = new AbstractAction("D") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerAnswer=4;
                checkAnswerOnePlayer();
            }
        };

        Answer4.setAction(buttonAction4);
        buttonAction4.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        Answer4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_4, 0), "D");

        Answer4.getActionMap().put("D", buttonAction4);
        Mid.add(Answer4);

        AnswerD = new JTextArea("Answer D here ... ");
        AnswerD.setFont(new Font("MV Boli", Font.BOLD, 30));
        AnswerD.setLineWrap(true);
        AnswerD.setEditable(false);
        Mid.add(AnswerD);

        // Το frame για πονταρισμα για One Playe Game

        BetgameFrame = new JFrame("Choose your bet!");
        BetgameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BetgameFrame.setLayout(new BorderLayout());
        BetgameFrame.setSize(500, 400);
        BetgameFrame.setResizable(false);

        JPanel BetTop = new JPanel();
        BetTop.setLayout(new BorderLayout());
        BetgameFrame.add(BetTop,BorderLayout.NORTH);

        JPanel BetBottom = new JPanel();
        BetBottom.setLayout(new GridLayout(4,0));
        BetgameFrame.add(BetBottom);

        BetMessage = new JTextArea("Choose your bet for the next questionet for the next question!");
        BetMessage.setFont(new Font("MV Boli", Font.BOLD, 23));
        BetMessage.setLineWrap(true);
        BetMessage.setEditable(false);
        BetTop.add(BetMessage,BorderLayout.CENTER);

        JButton bet250 = new JButton("250");
        bet250.addActionListener(this);
        BetBottom.add(bet250);

        JButton bet500 = new JButton("500");
        bet500.addActionListener(this);
        BetBottom.add(bet500);

        JButton bet750 = new JButton("750");
        bet750.addActionListener(this);
        BetBottom.add(bet750);

        JButton bet1000 = new JButton("1000");
        bet1000.addActionListener(this);
        BetBottom.add(bet1000);

        // Το frame για πονταρισμα του πρωτου παιχτη οταν ειναι multiplayer

        BetgameFrameFor1st = new JFrame("Choose your bet "+user1+"!");
        BetgameFrameFor1st.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BetgameFrameFor1st.setLayout(new BorderLayout());
        BetgameFrameFor1st.setSize(700, 400);
        BetgameFrameFor1st.setResizable(false);

        JPanel BetTopFor1st = new JPanel();
        BetTopFor1st.setLayout(new BorderLayout());
        BetgameFrameFor1st.add(BetTopFor1st,BorderLayout.NORTH);

        JPanel BetBotFor1st = new JPanel();
        BetBotFor1st.setLayout(new GridLayout(4,0));
        BetgameFrameFor1st.add(BetBotFor1st);

        BetMessageFor1st = new JTextArea("Choose your bet for the next question for the next question!");
        BetMessageFor1st.setFont(new Font("MV Boli", Font.BOLD, 23));
        BetMessageFor1st.setLineWrap(true);
        BetMessageFor1st.setEditable(false);
        BetTopFor1st.add(BetMessageFor1st,BorderLayout.CENTER);

        JButton bet250For1st = new JButton("Bet 250");
        bet250For1st.addActionListener(this);
        BetBotFor1st.add(bet250For1st);

        JButton bet500For1st = new JButton("Bet 500");
        bet500For1st.addActionListener(this);
        BetBotFor1st.add(bet500For1st);

        JButton bet750For1st = new JButton("Bet 750");
        bet750For1st.addActionListener(this);
        BetBotFor1st.add(bet750For1st);

        JButton bet1000For1st = new JButton("Bet 1000");
        bet1000For1st.addActionListener(this);
        BetBotFor1st.add(bet1000For1st);

        // Το frame για πονταρισμα του δευτερου παιχτη οταν ειναι multiplayer

        BetgameFrameFor2nd = new JFrame("Choose your bet "+user2+"!");
        BetgameFrameFor2nd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BetgameFrameFor2nd.setLayout(new BorderLayout());
        BetgameFrameFor2nd.setSize(700, 400);
        BetgameFrameFor2nd.setResizable(false);

        JPanel BetTopFor2nd = new JPanel();
        BetTopFor2nd.setLayout(new BorderLayout());
        BetgameFrameFor2nd.add(BetTopFor2nd,BorderLayout.NORTH);

        JPanel BetBotFor2nd = new JPanel();
        BetBotFor2nd.setLayout(new GridLayout(4,0));
        BetgameFrameFor2nd.add(BetBotFor2nd);

        BetMessageFor2nd = new JTextArea("Choose your bet for the next question for the next question!");
        BetMessageFor2nd.setFont(new Font("MV Boli", Font.BOLD, 23));
        BetMessageFor2nd.setLineWrap(true);
        BetMessageFor2nd.setEditable(false);
        BetTopFor2nd.add(BetMessageFor2nd,BorderLayout.CENTER);

        JButton bet250For2nd = new JButton("Bet 250 Player 2");
        bet250For2nd.addActionListener(this);
        BetBotFor2nd.add(bet250For2nd);

        JButton bet500For2nd = new JButton("Bet 500 Player 2");
        bet500For2nd.addActionListener(this);
        BetBotFor2nd.add(bet500For2nd);

        JButton bet750For2nd = new JButton("Bet 750 Player 2");
        bet750For2nd.addActionListener(this);
        BetBotFor2nd.add(bet750For2nd);

        JButton bet1000For2nd = new JButton("Bet 1000 Player 2");
        bet1000For2nd.addActionListener(this);
        BetBotFor2nd.add(bet1000For2nd);


        JPanel TwoPlayersTop = new JPanel();
        TwoPlayersTop.setLayout(new BorderLayout());
        TwoPlayersTop.setBackground(Color.BLUE);
        TwoPlayersTop.setPreferredSize(new Dimension(1000,180));
        TwoplayersFrame.add(TwoPlayersTop,BorderLayout.NORTH);

        JPanel TwoPlayersMid = new JPanel();
        TwoPlayersMid.setLayout(new GridLayout(9,1));
        TwoPlayersMid.setBackground(Color.WHITE);
        TwoPlayersMid.setPreferredSize(new Dimension(1000,480));
        TwoplayersFrame.add(TwoPlayersMid,BorderLayout.CENTER);

        JPanel TwoPlayersBottom = new JPanel();
        TwoPlayersBottom.setLayout(new BorderLayout());
        TwoPlayersBottom.setBackground(Color.GREEN);
        TwoPlayersBottom.setPreferredSize(new Dimension(1000,170));
        TwoplayersFrame.add(TwoPlayersBottom,BorderLayout.SOUTH);

        CategoryPartFor2 = new JTextArea("Category here...");
        CategoryPartFor2.setFont(new Font("MV Boli", Font.BOLD, 25));
        CategoryPartFor2.setLineWrap(true);
        CategoryPartFor2.setEditable(false);
        TwoPlayersTop.add(CategoryPartFor2,BorderLayout.NORTH);

        QuestionPartFor2 = new JTextArea("Question here...");
        QuestionPartFor2.setFont(new Font("MV Boli", Font.BOLD, 35));
        QuestionPartFor2.setLineWrap(true);
        QuestionPartFor2.setEditable(false);
        TwoPlayersTop.add(QuestionPartFor2,BorderLayout.CENTER);

        MessageFor2 = new JTextArea("Game Starts! ");
        MessageFor2.setFont(new Font("MV Boli", Font.BOLD, 30));
        MessageFor2.setEditable(false);
        TwoPlayersBottom.add(MessageFor2,BorderLayout.NORTH);


        Message2For2 = new JTextArea("Second message here.. ");
        Message2For2.setFont(new Font("MV Boli", Font.BOLD, 30));
        Message2For2.setEditable(false);
        TwoPlayersBottom.add(Message2For2,BorderLayout.CENTER);

        JTextArea Message3For2 = new JTextArea("Third message here.. ");
        Message3For2.setFont(new Font("MV Boli", Font.BOLD, 30));
        Message3For2.setEditable(false);
        TwoPlayersBottom.add(Message3For2,BorderLayout.SOUTH);


        JButton Answer1For2p= new JButton("1");
        Answer1For2p.addActionListener(this);
        Action buttonActionA = new AbstractAction("1") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerAnswer=1;
                waitForAnswer();
            }
        };

        Answer1For2p.setAction(buttonActionA);
        buttonActionA.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        Answer1For2p.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), "A");

        Answer1For2p.getActionMap().put("A", buttonActionA);
        TwoPlayersMid.add(Answer1For2p);

        AnswerAFor1= new JTextArea("Answer A here ... ");
        AnswerAFor1.setFont(new Font("MV Boli", Font.BOLD, 30));
        AnswerAFor1.setLineWrap(true);
        AnswerAFor1.setEditable(false);
        TwoPlayersMid.add(AnswerAFor1);

        JButton Answer2For2p = new JButton("2");
        Answer2For2p.addActionListener(this);
        Action buttonActionB = new AbstractAction("2") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerAnswer=2;
                waitForAnswer();
            }
        };

        Answer2For2p.setAction(buttonActionB);
        buttonActionB.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        Answer2For2p.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), "B");

        Answer2For2p.getActionMap().put("B", buttonActionB);
        TwoPlayersMid.add(Answer2For2p);


        AnswerBFor1= new JTextArea("Answer B here ... ");
        AnswerBFor1.setFont(new Font("MV Boli", Font.BOLD, 30));
        AnswerBFor1.setLineWrap(true);
        AnswerBFor1.setEditable(false);
        TwoPlayersMid.add(AnswerBFor1);

        JButton Answer3For2p= new JButton("3");
        Answer3For2p.addActionListener(this);
        Action buttonActionC = new AbstractAction("3") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerAnswer=3;
                waitForAnswer();
            }
        };

        Answer3For2p.setAction(buttonActionC);
        buttonActionC.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        Answer3For2p.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), "C");

        Answer3For2p.getActionMap().put("C", buttonActionC);
        TwoPlayersMid.add(Answer3For2p);

        AnswerCFor1= new JTextArea("Answer C here ... ");
        AnswerCFor1.setFont(new Font("MV Boli", Font.BOLD, 30));
        AnswerCFor1.setLineWrap(true);
        AnswerCFor1.setEditable(false);
        TwoPlayersMid.add(AnswerCFor1);

        JButton Answer4For2p= new JButton("4");
        Answer4For2p.addActionListener(this);
        Action buttonActionD = new AbstractAction("4") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerAnswer=4;
                waitForAnswer();
            }
        };

        Answer4For2p.setAction(buttonActionD);
        buttonActionD.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        Answer4For2p.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_4, 0), "D");

        Answer4For2p.getActionMap().put("D", buttonActionD);
        TwoPlayersMid.add(Answer4For2p);

        AnswerDFor1= new JTextArea("Answer D here ... ");
        AnswerDFor1.setFont(new Font("MV Boli", Font.BOLD, 30));
        AnswerDFor1.setLineWrap(true);
        AnswerDFor1.setEditable(false);
        TwoPlayersMid.add(AnswerDFor1);

        JTextArea WhiteBar = new JTextArea();
        WhiteBar.setBackground(Color.WHITE);
        WhiteBar.setLineWrap(false);
        WhiteBar.setEditable(false);
        TwoPlayersMid.add(WhiteBar);

        JTextArea WhiteBar2 = new JTextArea();
        WhiteBar2.setBackground(Color.WHITE);
        WhiteBar2.setLineWrap(false);
        WhiteBar2.setEditable(false);
        TwoPlayersMid.add(WhiteBar2);


        JButton Answer5For2p= new JButton("5");
        Answer5For2p.addActionListener(this);
        Action buttonActionE = new AbstractAction("5") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerAnswer2=1;
                waitForAnswer();
            }
        };

        Answer5For2p.setAction(buttonActionE);
        buttonActionE.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        Answer5For2p.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_5, 0), "E");

        Answer5For2p.getActionMap().put("E", buttonActionE);
        TwoPlayersMid.add(Answer5For2p);

        AnswerAFor2= new JTextArea("Answer A here ... ");
        AnswerAFor2.setFont(new Font("MV Boli", Font.BOLD, 30));
        AnswerAFor2.setLineWrap(true);
        AnswerAFor2.setEditable(false);
        TwoPlayersMid.add(AnswerAFor2);

        JButton Answer6For2p = new JButton("6");
        Answer6For2p.addActionListener(this);
        Action buttonActionF = new AbstractAction("6") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerAnswer2=2;
                waitForAnswer();
            }
        };

        Answer6For2p.setAction(buttonActionF);
        buttonActionF.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        Answer6For2p.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_6, 0), "F");

        Answer6For2p.getActionMap().put("F", buttonActionF);
        TwoPlayersMid.add(Answer6For2p);


        AnswerBFor2= new JTextArea("Answer B here ... ");
        AnswerBFor2.setFont(new Font("MV Boli", Font.BOLD, 30));
        AnswerBFor2.setLineWrap(true);
        AnswerBFor2.setEditable(false);
        TwoPlayersMid.add(AnswerBFor2);

        JButton Answer7For2p= new JButton("7");
        Answer7For2p.addActionListener(this);
        Action buttonActionG = new AbstractAction("7") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerAnswer2=3;
                waitForAnswer();
            }
        };

        Answer7For2p.setAction(buttonActionG);
        buttonActionG.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        Answer7For2p.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_7, 0), "G");

        Answer7For2p.getActionMap().put("G", buttonActionG);
        TwoPlayersMid.add(Answer7For2p);

        AnswerCFor2= new JTextArea("Answer C here ... ");
        AnswerCFor2.setFont(new Font("MV Boli", Font.BOLD, 30));
        AnswerCFor2.setLineWrap(true);
        AnswerCFor2.setEditable(false);
        TwoPlayersMid.add(AnswerCFor2);

        JButton Answer8For2p= new JButton("8");
        Answer8For2p.addActionListener(this);
        Action buttonActionH = new AbstractAction("8") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerAnswer2=4;
                waitForAnswer();
            }
        };

        Answer8For2p.setAction(buttonActionH);
        buttonActionH.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        Answer8For2p.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_8, 0), "H");

        Answer8For2p.getActionMap().put("H", buttonActionH);
        TwoPlayersMid.add(Answer8For2p);

        AnswerDFor2= new JTextArea("Answer D here ... ");
        AnswerDFor2.setFont(new Font("MV Boli", Font.BOLD, 30));
        AnswerDFor2.setLineWrap(true);
        AnswerDFor2.setEditable(false);
        TwoPlayersMid.add(AnswerDFor2);

        //Implementation of timer in gui

        SecondsLeft = new JLabel();
        SecondsLeft.setBackground(Color.BLACK);
        SecondsLeft.setForeground(new Color(255,0,0));
        SecondsLeft.setFont(new Font("MV Boli", Font.BOLD, 80));
        SecondsLeft.setOpaque(true);
        SecondsLeft.setText("5");
        Bottom.add(SecondsLeft,BorderLayout.EAST);



        //Implementation of ScoreFrame

        ScoreFrame = new JFrame("Score History");
        ScoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ScoreFrame.setLayout(new BorderLayout());
        ScoreFrame.setSize(600, 500);
        ScoreFrame.setResizable(false);

        JPanel ScoreTop =new JPanel();
        ScoreTop.setLayout(new BorderLayout());
        ScoreTop.setBackground(Color.WHITE);
        ScoreTop.setPreferredSize(new Dimension(700,100));
        ScoreFrame.add(ScoreTop,BorderLayout.NORTH);

        JPanel ScoreBot =new JPanel();
        ScoreBot.setLayout(new GridLayout(4,0));
        ScoreBot.setBackground(Color.WHITE);
        ScoreBot.setPreferredSize(new Dimension(700,400));
        ScoreFrame.add(ScoreBot,BorderLayout.CENTER);

        JLabel ScoreMessage = new JLabel("The best Players and their points: ");
        ScoreMessage.setFont(new Font("MV Boli", Font.BOLD, 30));
        ScoreMessage.setForeground(new Color(77, 200,0));
        ScoreTop.add(ScoreMessage,BorderLayout.NORTH);

        BestScore = new JTextArea("Best Score in Single Player: " +bestscore);
        BestScore.setFont(new Font("MV Boli", Font.BOLD, 30));
        BestScore.setLineWrap(true);
        BestScore.setEditable(false);
        ScoreBot.add(BestScore);

        JTextArea ScoreP1 = new JTextArea("Best Score of " +user1+":...");
        ScoreP1.setFont(new Font("MV Boli", Font.BOLD, 30));
        ScoreP1.setLineWrap(true);
        ScoreP1.setEditable(false);
        ScoreBot.add(ScoreP1);

        JTextArea ScoreP2 = new JTextArea("Best Score of " +user2+":...");
        ScoreP2.setFont(new Font("MV Boli", Font.BOLD, 30));
        ScoreP2.setLineWrap(true);
        ScoreP2.setEditable(false);
        ScoreBot.add(ScoreP2);



        // Image Questions

        ImageFrame = new JFrame("Image Question");
        ImageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageFrame.setLayout(new BorderLayout());
        ImageFrame.setSize(700, 500);
        ImageFrame.setResizable(false);

        JPanel ImageTop =new JPanel();
        ImageTop.setLayout(new BorderLayout());
        ImageTop.setBackground(Color.RED);
        ImageTop.setPreferredSize(new Dimension(700,400));
        ImageFrame.add(ImageTop,BorderLayout.NORTH);

        JPanel ImageBot =new JPanel();
        ImageBot.setLayout(new BorderLayout());
        ImageBot.setBackground(Color.WHITE);
        ImageBot.setPreferredSize(new Dimension(700,100));
        ImageFrame.add(ImageBot,BorderLayout.SOUTH);

        JButton ImageButton = new JButton("Go to the Question");
        ImageButton.addActionListener(this);
        ImageBot.add(ImageButton);



        //Player admin = new Player("admin");
        //admin.initializePlayersArray();
        //String user1 = null;
        //String user2 = null;

        //admin.printAllPlayers();
        //System.out.println("Please answer each question with the number of the right answer: 1,2,3 or 4 ! \n");

        timer=new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Seconds--;
                SecondsLeft.setText(String.valueOf(Seconds));
                if(Seconds<=0) {
                    playerAnswer = 5;
                    checkAnswerOnePlayer();
                }
            }
        });
    }



/** Φορτώνει την επόμενη ερώτηση στο frame του παιχνιδιού ενός παίκτη */

    private void showQuestion(){
        Message.setText("Round: " +currentRound +" Question: "+currentQuestion );
        QuestionPart.setText(next.getQuestion());
        AnswerA.setText(next.getPossibleAnswerA());
        AnswerB.setText(next.getPossibleAnswerB());
        AnswerC.setText(next.getPossibleAnswerC());
        AnswerD.setText(next.getPossibleAnswerD());
        CategoryPart.setText(next.getCategory());
    }
    /** εκτυπώνει τον γύρο που βρισκόμαστε στο παιχνίδι ενός παίκτη */
    private void printTypeOfRound(){
        if(typeOfRound==0){
            Message3.setText("Type of Round: Right Answer!");
        }
        else if( typeOfRound==1){
            Message3.setText("Type of Round: Countdown");
        }
        else{
            Message3.setText("Type of round: Betting game!");
        }
    }
    /** Αυτή η μέθοδος είναι η βασική μέθοδος του παιχνιδιού ενός παίκτη. Φορτώνει τις ερωτήσεις και
     * κάνει λειτουργικό το παιχνίδι ανάλογα με τον γύρο που βρισκόμαστε */
    private void OnePlayerGame(){
        Seconds=5;
        random=new Random();
        next = allQuestions.getNextQuestion();


        if(currentQuestion<=questionsPerRound) {
            if(typeOfRound==2){
                BetMessage.setText("Category: "+next.getCategory());
                BetgameFrame.setVisible(true);
                GameplayFrame.setVisible(false);
            }
            printTypeOfRound();
            showQuestion();
            currentQuestion++;
            if(typeOfRound==1)
                timer.start();
            else
                timer.stop();
        }
        else if(currentRound<numberOfRounds){
            typeOfRound=random.nextInt(3);


            if(typeOfRound==2){
                BetMessage.setText("Category: "+next.getCategory());
                BetgameFrame.setVisible(true);
                GameplayFrame.setVisible(false);
            }

            currentRound++;
            currentQuestion=1;
            showQuestion();
            printTypeOfRound();
            currentQuestion++;
            if(typeOfRound==1)
                timer.start();
            else
                timer.stop();
        }
        else {
            GameplayFrame.setVisible(false);
            BetgameFrame.setVisible(false);
            GuiStart guiStart=new GuiStart();
            guiStart.endGame(score);
            UpdateScore(score);

        }
    }
    /** Εμφανίζει στο frame δυο παικτών  την επόμενη ερώτηση */
    private void showQuestionForTwo(){
        QuestionPart.setText(next.getQuestion());
        AnswerAFor1.setText(next.getPossibleAnswerA());
        AnswerBFor1.setText(next.getPossibleAnswerB());
        AnswerCFor1.setText(next.getPossibleAnswerC());
        AnswerDFor1.setText(next.getPossibleAnswerD());
        CategoryPartFor2.setText(next.getCategory());
        QuestionPartFor2.setText(next.getQuestion());
        AnswerAFor2.setText(next.getPossibleAnswerA());
        AnswerBFor2.setText(next.getPossibleAnswerB());
        AnswerCFor2.setText(next.getPossibleAnswerC());
        AnswerDFor2.setText(next.getPossibleAnswerD());
        printTypeOfRoundFor2();
    }

    /** εμφανίζει τον γύρο στον οποίο βρισκόμαστε στο παιχνίδι δύο παικτών*/
    private void printTypeOfRoundFor2(){
        if(typeOfRound==0){
            roundType="Type of Round: Right Answer! Each player wins 1000 points for each right answer! ";
            MessageFor2.setText("Type of Round: Right Answer! Question: "+currentQuestion);
        }

        else if( typeOfRound==1){
            roundType="Type of Round: Betting game! Each player wins or loses the points he bets! ";
            MessageFor2.setText("Type of Round: Betting game! Question: "+currentQuestion);
        }
        else if(typeOfRound==2){
            roundType="Type of Round: Fast Answer! First correct answer gets 1000 points, second correct answer gets 500!  ";
            MessageFor2.setText("Type of Round: Fast Answer!  Question: "+currentQuestion);
        }
        else {
            roundType="Type of Round: Thermometer! The first player with 5 correct answers gets 5000 points. ";
            MessageFor2.setText("Type of Round: Thermometer! Question: "+currentQuestion);
        }
    }
/** Αυτή η μέθοδος είναι η βασική μέθοδος του παιχνιδιού δύο παικτών. Φορτώνει τις ερωτήσεις και
 * κάνει λειτουργικό το παιχνίδι ανάλογα με τον γύρο που βρισκόμαστε */
    private void twoPlayersGame(){
        printTypeOfRoundFor2();
        random=new Random();
        next = allQuestions.getNextQuestion();
        //Chronometro chronometro1= new Chronometro();

        if (currentQuestion <= questionsPerRound||(typeOfRound==3&&(winsinaRow1<5 && winsinaRow2<5))) {
            if(typeOfRound==1){
                // BetMessageFor2nd.setText("Category: "+next.getCategory());
//                    TwoplayersFrame.setVisible(false);
//                    BetgameFrameFor1st.setVisible(true);
//                    BetMessageFor1st.setText("Category: "+next.getCategory());
                //BetMessageFor2nd.setVisible(true);
                BetMessageFor1st.setText("Category: "+next.getCategory());
                BetgameFrameFor1st.setVisible(true);
                TwoplayersFrame.setVisible(false);

            }

            showQuestionForTwo();
            currentQuestion++;
            playerAnswer=0;
            playerAnswer2=0;

        } else if (currentRound < numberOfRounds) {
            winsinaRow1=0;
            winsinaRow2=0;
            //εδω αλλαζεις το γυρο που θες να τεστάρεις, πχ typeOfRound=1 για bet
            typeOfRound=random.nextInt(4);
            printTypeOfRoundFor2();
            JOptionPane.showMessageDialog(TwoplayersFrame,roundType);
            if(typeOfRound==1){
                BetMessageFor1st.setText("Category: "+next.getCategory());
                BetgameFrameFor1st.setVisible(true);
                TwoplayersFrame.setVisible(false);
//                    BetMessageFor2nd.setText("Category: "+next.getCategory());
//                    BetMessageFor2nd.setVisible(true);
                //TwoplayersFrame.setVisible(false);
            }

            playerAnswer=0;
            playerAnswer2=0;
            currentRound++;
            currentQuestion = 1;
            showQuestionForTwo();
            currentQuestion++;
        } else {
            TwoplayersFrame.setVisible(false);
            GameplayFrame.setVisible(false);
            BetgameFrame.setVisible(false);
            GuiStart guiStart=new GuiStart();
            guiStart.endGameForTwo(score,score2,user1,user2);

        }

    }
/** περιμένει να απαντήσουν και οι δυο χρήστες για να ελέγξει τις απαντήσεις τους */
    private void waitForAnswer(){
        if((playerAnswer==1 || playerAnswer==2 || playerAnswer==3 || playerAnswer==4) && (playerAnswer2==1 || playerAnswer2==2 || playerAnswer2==3 || playerAnswer2==4)){
            checkAnswerTwoPlayers();
        }
        else if(playerAnswer==1 || playerAnswer==2 || playerAnswer==3 || playerAnswer==4)
            firstAnswer=1;
        else if(playerAnswer2==1 || playerAnswer2==2 || playerAnswer2==3 || playerAnswer2==4)
            firstAnswer=2;
    }
    private void waitForBet(){
        if (betp1==250 || betp1==500 || betp1==750 || betp1==1000){

            BetgameFrameFor1st.setVisible(false);
            BetMessageFor2nd.setText("Category: "+next.getCategory());
            BetgameFrameFor2nd.setVisible(true);

            if (betp2==250 || betp2==500 || betp2==750 || betp2==1000){
                BetgameFrameFor2nd.setVisible(false);
                TwoplayersFrame.setVisible(true);
            }

        }
    }




/** Σε αυτή τη μέθοδο δημιουργούνται τα πεδία για να γράψουν οι χρήστες τα ονόματα τους
 * και να ξεκινήσει το παιχνίδι δυο παικτών */
    private void readUsernames(){

        JTextField username1=new JTextField("Player 1");
        username1.setBounds(300, 150, 115, 50);
        JTextField username2=new JTextField("Player 2");
        username2.setBounds(300, 225,115, 50 );
        frame.add(username1);
        frame.add(username2);
        JButton jButton=new JButton("Start");
        jButton.setBounds(300,300,115,50);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                user1=username1.getText();
                user2=username2.getText();
                JOptionPane.showMessageDialog(TwoplayersFrame,""+user1+" uses keys 1,2,3,4 to answer!\n"+user2+" uses keys 5,6,7,8 to answer!");
                // Το ενημερωτικό μηνυμα που υπενθυμίζει στους χρήστες ποια κουμπιά να χρησιμοποιουν
                // Εμφανίζεται μόλις πατηθεί το κουμπί πριν υλοποιηθει το παιχνίδι
                typeOfRound=0;
                twoPlayersGame();
                TwoplayersFrame.setVisible(true);
                frame.setVisible(false);
            }
        });

        frame.getContentPane().add(jButton,BorderLayout.AFTER_LAST_LINE);
    }

    /**
     * Η παρακάτω μέθοδος καλείται όταν τελειώνει το παιχνίδι για να ρωτήσει τον χρήστη αν θέλει να ξαναπαίξει,
     * και αν θέλει καλεί ξανά την συνάρτηση start
     */
    private void endGame() {
        System.out.print("Do you want to play again? Press y for yes or n for no \n");
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        if (str.equals("y") || str.equals("Y"))
            start();
        else if (str.equals("n") || str.equals("N"))
            System.out.print("Thanks for playing! \n ");
        else {
            endGame();
        }
    }

    private void checkAnswerOnePlayer(){
        round=new Round();
        /* AnswerA.setBackground(Color.GREEN);
            tryToSleep();
            AnswerA.setBackground(Color.WHITE);
*/
        /*  AnswerA.setBackground(Color.GREEN);
            tryToSleep();
            AnswerA.setBackground(Color.WHITE);*/
        answerIsRight= next.getRightAnswer() == playerAnswer;

        switch(typeOfRound){
            case(0):
                score+=round.rightAnswerForOne(answerIsRight);
                break;
            case(1):
                score+=round.CountdownForOne(Seconds,answerIsRight);
                break;
            case(2):
                score+=round.bettingGameforOne(bet,answerIsRight);
                break;

        }
        Message2.setText("Your score is: " +score);
        OnePlayerGame();

    }
    /** Αυτή η μέθοδος ελέγχει την ορθότητα των απαντήσεων στο παιχνίδι δυο παικτών */
    private void checkAnswerTwoPlayers(){
        round=new Round();
        answerIsRight= next.getRightAnswer() == playerAnswer;

        answerIsRight2= next.getRightAnswer() == playerAnswer2;
        switch(typeOfRound){
            case(0):
                score+=round.rightAnswerForOne(answerIsRight);
                score2+=round.rightAnswerForOne(answerIsRight2);
                break;
            case(1):
                score+=round.bettingGameforOne(betp1,answerIsRight);
                score2+=round.bettingGameforOne(betp2,answerIsRight2);

                break;
            case(2):
                if((firstAnswer==1&& answerIsRight)||(firstAnswer==2&&answerIsRight&& !answerIsRight2))
                    score+=1000;
                else if(firstAnswer==2 && answerIsRight && answerIsRight2)
                    score+=500;
                if((firstAnswer==2&& answerIsRight2)||(firstAnswer==1&&answerIsRight2&& !answerIsRight))
                    score2+=1000;
                else if(firstAnswer==1 && answerIsRight && answerIsRight2)
                    score2+=500;
                break;
            case(3):
                if(answerIsRight)
                    winsinaRow1++;
                if(answerIsRight2)
                    winsinaRow2++;
                if(winsinaRow1==5)
                    score+=5000;
                if(winsinaRow2==5)
                    score+=5000;
                break;

        }
        Message2For2.setText("Score of "+user1 +" is: "+score+ " Score of "+user2 +" is: "+score2);
        twoPlayersGame();

    }

     private void UpdateScore(int score) {

         if (score > bestscore){
             bestscore = score;
             BestScore.setText(String.valueOf(bestscore));
         }
     }




//    public void tryToSleep(){
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException d) {
//            d.printStackTrace();
//        }
//    }

    @Override

    public void actionPerformed(ActionEvent e) {
        random=new Random();
        switch(e.getActionCommand()){

            case "One Player":
                JOptionPane.showMessageDialog(GameplayFrame,"Answer the Questions with 1,2,3,4 \n For answers A,B,C,D");
                // Το ενημερωτικό μηνυμα που υπενθυμίζει στον χρήστη ποια κουμπιά να χρησιμοποιησει
                // Εμφανίζεται μόλις πατηθεί το κουμπί πριν υλοποιηθει το παιχνίδι
                typeOfRound=0;
                allQuestions=new AllQuestions();
                allQuestions.createQuestionArray();
                OnePlayerGame();
                GameplayFrame.setVisible(true);
                frame.setVisible(false);

                break;

            case "Two Players":
                allQuestions=new AllQuestions();
                allQuestions.createQuestionArray();
                readUsernames();
                break;

            case "Score":
                //Τα στατιστικα των παικτών.
                ScoreFrame.setVisible(true);
                break;
            case "Quit":
                System.exit(0);
                break;

            case "A":
                playerAnswer=1;
                checkAnswerOnePlayer();

                break;
            case "B":
                playerAnswer=2;
                checkAnswerOnePlayer();
               /* if(answerIsRight) {
                    AnswerB.setBackground(Color.GREEN);
                    tryToSleep();
                    AnswerA.setBackground(Color.WHITE);
                }
                    else {
                    AnswerB.setBackground(Color.RED);
                    tryToSleep();
                    AnswerA.setBackground(Color.WHITE);
                    }*/
                break;
            case "C":
                playerAnswer=3;
                checkAnswerOnePlayer();
                /*if(answerIsRight) {
                    AnswerC.setBackground(Color.GREEN);
                    tryToSleep();
                    AnswerA.setBackground(Color.WHITE);
                }
                else {
                    AnswerC.setBackground(Color.RED);
                    tryToSleep();
                    AnswerA.setBackground(Color.WHITE);
                }*/
                break;
            case "D":
                playerAnswer=4;
                checkAnswerOnePlayer();
                /*if(answerIsRight) {
                    AnswerD.setBackground(Color.GREEN);
                    tryToSleep();
                    AnswerA.setBackground(Color.WHITE);
                }
                else {
                    AnswerD.setBackground(Color.RED);
                    tryToSleep();
                    AnswerA.setBackground(Color.WHITE);
                }*/
                break;
            case "250":
                bet=250;
                BetgameFrame.setVisible(false);
                GameplayFrame.setVisible(true);
            case "500":
                bet=500;
                BetgameFrame.setVisible(false);
                GameplayFrame.setVisible(true);
                break;
            case "750":
                bet=750;
                BetgameFrame.setVisible(false);
                GameplayFrame.setVisible(true);
                break;
            case "1000":
                bet=1000;
                BetgameFrame.setVisible(false);
                GameplayFrame.setVisible(true);
                break;

            case "Go to the Question":
                ImageFrame.setVisible(false);

            case "1":
                playerAnswer=1;
                waitForAnswer();
                // checkAnswerTwoPlayers();
                break;

            case "2":
                playerAnswer=2;
                waitForAnswer();
                //  checkAnswerTwoPlayers();
                break;

            case "3":
                playerAnswer=3;
                waitForAnswer();
                //   checkAnswerTwoPlayers();
                break;

            case "4":
                playerAnswer=4;
                waitForAnswer();
                // checkAnswerTwoPlayers();
                break;

            case "5":
                playerAnswer2=1;
                waitForAnswer();
                // checkAnswerTwoPlayers();
                break;

            case "6":
                playerAnswer2=2;
                waitForAnswer();
                // checkAnswerTwoPlayers();
                break;

            case "7":
                playerAnswer2=3;
                waitForAnswer();
                //  checkAnswerTwoPlayers();
                break;

            case "8":
                playerAnswer2=4;
                waitForAnswer();
                // checkAnswerTwoPlayers();
                break;

            case "Bet 250":
                betp1=250;
                waitForBet();

            case "Bet 500":
                betp1=500;
                waitForBet();

            case "Bet 750":
                betp1=750;
                waitForBet();

            case "Bet 1000":
                betp1=1000;
                waitForBet();

            case "Bet 250 Player 2":
                betp2=250;
                waitForBet();

            case "Bet 500 Player 2":
                betp2=500;
                waitForBet();

            case "Bet 750 Player 2":
                betp2=750;
                waitForBet();

            case "Bet 1000 Player 2":
                betp2=1000;
                waitForBet();

        }



    }

}