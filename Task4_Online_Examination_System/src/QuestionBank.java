import java.util.ArrayList;

public class QuestionBank {

    public static ArrayList<Question> getQuestions() {

        ArrayList<Question> list = new ArrayList<>();

        list.add(new Question(
                "What is Java?",
                "Programming Language",
                "Database",
                "OS",
                "Browser",
                "Programming Language"
        ));

        list.add(new Question(
                "Who developed Java?",
                "Microsoft",
                "Sun Microsystems",
                "Google",
                "IBM",
                "Sun Microsystems"
        ));

        list.add(new Question(
                "Which is OOP feature?",
                "Encapsulation",
                "Compilation",
                "Execution",
                "Storage",
                "Encapsulation"
        ));

        return list;
    }
}