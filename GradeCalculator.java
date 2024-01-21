import java.util.ArrayList;
import java.util.Arrays;
import java.text.DecimalFormat;

public class GradeCalculator {
    static class Student {
        String name;
        ArrayList<Grade> grades;
        public Student(String name, ArrayList<Grade> grades) {
            this.name = name;
            this.grades = grades;
        }
    }
    static class Grade {
        String subject;
        int grade; 
        public Grade(String subject, int grade) {
            this.subject = subject;
            this.grade = grade;
        }
    }

    public static double[] averageGrades(Student[] students) {
        double[] averages = new double[students.length];
        for (int i = 0; i < students.length; i++) {
            ArrayList<Grade> grades = students[i].grades;
            int sum = 0;
            for (Grade grade : grades) {
                sum += grade.grade;
            }
            double average = (double) sum / grades.size();
            averages[i] = average;
        }
        return averages;
    }

    public static double[] averageSubjects(Student[] students) {
        double[] averages = new double[students[0].grades.size()];
        for (int i = 0; i < students[0].grades.size(); i++) {
            String subject = students[0].grades.get(i).subject; 
            int sum = 0;
            int count = 0;
            for (Student student : students) {
                for (Grade grade : student.grades) {
                    if (grade.subject.equals(subject)) {
                        sum += grade.grade;
                        count++;
                    }
                }
            }
            double average = (double) sum / count; 
            averages[i] = average;
        }
        return averages;
    } 
    public static double overallAverage(Student[] students) {
        double sum = 0;
        for (double average : averageGrades(students)) {
            sum += average;
        } 
        double overall = sum / students.length;
        return overall;
    }
    public static double stdDeviation(Student[] students) {
        double mean = overallAverage(students);
        double sum = 0;
        int count = 0;
        for (Student student : students) {
            for (Grade grade : student.grades) {
                sum += Math.pow(grade.grade - mean, 2);
                count++;
            }
        }
        double variance = sum / count;
        double stdDev = Math.sqrt(variance);
        return stdDev;
    }
    public static String format(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value);
    }
    public static void printOutput(Student[] students) {
        ArrayList<String> output = new ArrayList<>();
        output.add("\"average_grades\": [");
        for (double average : averageGrades(students)) {
            output.add(format(average) + ", ");
        }
        output.set(output.size() - 1, output.get(output.size() - 1).replace(", ", ""));
        output.add("],");

    
        output.add("\"average_subjects\": [");
        for (double average : averageSubjects(students)) {
            output.add(format(average) + ", ");
        }
        output.set(output.size() - 1, output.get(output.size() - 1).replace(", ", ""));
        output.add("],");
        output.add("\"overall_average\": " + format(overallAverage(students)) + ",");
        output.add("\"std_deviation\": " + format(stdDeviation(students)));
        System.out.println("{");
        for (String line : output) {
            System.out.println("  " + line);
        }
        System.out.println("}");
    }

    public static void main(String[] args) {
        Student[] students = new Student[3];
        students[0] = new Student("John Doe", new ArrayList<>(Arrays.asList(
                new Grade("Math", 90),
                new Grade("English", 85),
                new Grade("Science", 92),
                new Grade("History", 88),
                new Grade("Art", 95)
        )));
        students[1] = new Student("Jane Smith", new ArrayList<>(Arrays.asList(
                new Grade("Math", 88),
                new Grade("English", 92),
                new Grade("Science", 87),
                new Grade("History", 90),
                new Grade("Art", 93)
        )));
        students[2] = new Student("Bob Johnson", new ArrayList<>(Arrays.asList(
                new Grade("Math", 78),
                new Grade("English", 85),
                new Grade("Science", 80),
                new Grade("History", 88),
                new Grade("Art", 82)
        )));
        printOutput(students);
    }
}
