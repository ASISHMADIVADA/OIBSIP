import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class QuizFrame extends JFrame implements ActionListener {

    JLabel questionLabel, timerLabel;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup group;
    JButton nextButton;

    ArrayList<Question> questions;
    int current = 0;
    int score = 0;

    TimerManager timerManager;

    public QuizFrame() {

        setTitle("Online Examination System");
        setSize(600, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // CLOSE CONFIRMATION
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {

                int choice = JOptionPane.showConfirmDialog(
                        QuizFrame.this,
                        "Are you sure you want to quit?",
                        "Exit",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {
                    timerManager.stop();
                    dispose();
                }
            }
        });

        questions = QuestionBank.getQuestions();

        questionLabel = new JLabel();
        questionLabel.setBounds(50, 50, 500, 30);
        add(questionLabel);

        opt1 = new JRadioButton();
        opt2 = new JRadioButton();
        opt3 = new JRadioButton();
        opt4 = new JRadioButton();

        opt1.setBounds(50, 100, 300, 30);
        opt2.setBounds(50, 130, 300, 30);
        opt3.setBounds(50, 160, 300, 30);
        opt4.setBounds(50, 190, 300, 30);

        group = new ButtonGroup();
        group.add(opt1);
        group.add(opt2);
        group.add(opt3);
        group.add(opt4);

        add(opt1);
        add(opt2);
        add(opt3);
        add(opt4);

        nextButton = new JButton("Next");
        nextButton.setBounds(250, 260, 100, 30);
        nextButton.addActionListener(this);
        add(nextButton);

        timerLabel = new JLabel("Time Left:");
        timerLabel.setBounds(450, 20, 120, 30);
        add(timerLabel);

        loadQuestion();

        timerManager = new TimerManager(60, this::submitExam);
        timerManager.start(timerLabel);

        setVisible(true);
    }

    void loadQuestion() {

        if (current >= questions.size()) {
            submitExam();
            return;
        }

        Question q = questions.get(current);

        questionLabel.setText(q.question);
        opt1.setText(q.opt1);
        opt2.setText(q.opt2);
        opt3.setText(q.opt3);
        opt4.setText(q.opt4);

        group.clearSelection();
    }

    public void actionPerformed(ActionEvent e) {

        checkAnswer();
        current++;
        loadQuestion();
    }

    void checkAnswer() {

        Question q = questions.get(current);

        if (opt1.isSelected() && opt1.getText().equals(q.answer)) score++;
        if (opt2.isSelected() && opt2.getText().equals(q.answer)) score++;
        if (opt3.isSelected() && opt3.getText().equals(q.answer)) score++;
        if (opt4.isSelected() && opt4.getText().equals(q.answer)) score++;
    }

    void submitExam() {

        timerManager.stop();

        JFrame result = new JFrame("Result");
        result.setSize(300, 200);
        result.setLayout(null);
        result.setLocationRelativeTo(null);

        JLabel scoreLabel = new JLabel("Score: " + score + "/" + questions.size());
        scoreLabel.setBounds(80, 30, 200, 30);
        result.add(scoreLabel);

        JButton logout = new JButton("Logout");
        logout.setBounds(80, 80, 100, 30);
        result.add(logout);

        logout.addActionListener(e -> {
            result.dispose();
            this.dispose();
            new LoginFrame();
        });

        result.setVisible(true);
        this.setVisible(false);
    }
}