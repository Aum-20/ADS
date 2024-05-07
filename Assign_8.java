package ADS;
import java.util.*;
import java.io.*;

class Student {
    private static final String fileName = "student.txt";

    int rollNumber;
    String name;
    int grNo;
    String className;

    public Student(int rollNumber, String name, int grNo, String className) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.grNo = grNo;
        this.className = className;
    }

    public void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(rollNumber + "," + name + "," + grNo + "," + className);
            writer.newLine();
            System.out.println("Student details added to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> readFromFile() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int rollNumber = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int grNo = Integer.parseInt(parts[2]);
                    String className = parts[3];
                    students.add(new Student(rollNumber, name, grNo, className));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Student> students = new ArrayList<>();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("\t***** Implementing Student Database Using Text files *****\t");
        System.out.println("------------------------------------------------------------------------");
        while (true) {
            System.out.println("Options Avaliable :");
            System.out.println("1. Add Student");
            System.out.println("2. Save Students to File");
            System.out.println("3. Get Students info from File");
            System.out.println("4. Exit");
            System.out.println("-----------------------------------------------------------------------");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll Number : ");
                    int rollNumber = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter Name : ");
                    String name = sc.nextLine();
                    System.out.print("Enter Gr. Number : ");
                    int grNo = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter Class Name : ");
                    String className = sc.nextLine();
                    students.add(new Student(rollNumber, name, grNo, className));
                    System.out.println("-----------------------------------------------------------------------");
                    break;

                case 2:
                    for (Student student : students) {
                        student.writeToFile();
                    }
                    System.out.println("Student info get saves to file.");
                    System.out.println("-----------------------------------------------------------------------");
                    break;

                case 3:
                    System.out.println("Avaliable Students to database : ");
                    List<Student> studentDB = Student.readFromFile();
                    for (Student student : studentDB) {
                        System.out.println(student);
                    }
                    System.out.println("-----------------------------------------------------------------------");
                    break;

                case 4:
                    System.out.println(" !! Thank you for responding !! \nHave a nice day ");
                    System.exit(0);
                    System.out.println("-----------------------------------------------------------------------");

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    System.out.println("-----------------------------------------------------------------------");
            }
        }
    }

    @Override
    public String toString() {
        return "Roll Number: " + rollNumber +
                "\n Name: " + name +
                "\n Grade Number: " + grNo +
                "\n Class Name: " + className;
    }
}

