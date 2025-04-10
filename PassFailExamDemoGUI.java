import javax.swing.*;
import java.awt.*;

public class PassFailExamDemoGUI extends JFrame {

    private final JTextField questionsField = new JTextField(10);
    private final JTextField missedField = new JTextField(10);
    private final JTextField minPassingField = new JTextField(10);
    private final JTextArea resultArea = new JTextArea(5, 20);

    public PassFailExamDemoGUI() {
        setTitle("Pass/Fail Exam Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel("How many questions are on the exam?"));
        add(questionsField);
        add(new JLabel("How many questions did the student miss?"));
        add(missedField);
        add(new JLabel("What is the minimum passing score?"));
        add(minPassingField);

        JButton calculateButton = new JButton("Calculate Results");
        calculateButton.addActionListener(e -> calculateExam());
        add(calculateButton);

        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void calculateExam() {
        try {
            int questions = Integer.parseInt(questionsField.getText());
            int missed = Integer.parseInt(missedField.getText());
            double minPassing = Double.parseDouble(minPassingField.getText());

            PassFailExam exam = new PassFailExam(questions, missed, minPassing);

            String result = String.format("Each question counts %.2f points.\nThe exam score is %.2f\nThe exam grade is %s\n",
                                          exam.getPointsEach(), exam.getScore(), exam.getGrade());
            resultArea.setText(result);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PassFailExamDemoGUI::new);
    }
}

class PassFailExam {
    private final int numQuestions;
    private final int numMissed;
    private final double passingScore;

    public PassFailExam(int questions, int missed, double minPassing) {
        if (questions <= 0) {
            throw new IllegalArgumentException("Number of questions must be positive.");
        }
        if (missed < 0 || missed > questions) {
            throw new IllegalArgumentException("Number of missed questions is invalid.");
        }
        if (minPassing < 0 || minPassing > 100) {
            throw new IllegalArgumentException("Minimum passing score must be between 0 and 100.");
        }
        this.numQuestions = questions;
        this.numMissed = missed;
        this.passingScore = minPassing;
    }

    public double getPointsEach() {
        return 100.0 / numQuestions;
    }

    public double getScore() {
        return (double) (numQuestions - numMissed) / numQuestions * 100;
    }

    public String getGrade() {
        return getScore() >= passingScore ? "Pass" : "Fail";
    }
}
