package org.katheer.test;

import org.katheer.dao.StudentDao;
import org.katheer.dto.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Test {

    private static ApplicationContext context;
    private static StudentDao studentDao;
    private static BufferedReader reader;

    static {
        context = new ClassPathXmlApplicationContext("org" +
                "/katheer/resource/applicationContext.xml");
        studentDao = (StudentDao) context.getBean("studentDao");

        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    //addStudent menu
    private static void addStudent() {
        Student student = new Student();
        System.out.println("===ADD STUDENT===");
        System.out.println("Enter student details:");
        try {
            System.out.print("Name  : ");
            student.setName(reader.readLine().trim());

            System.out.print("ID    : ");
            student.setId(Integer.parseInt(reader.readLine().trim()));

            System.out.print("Dept  : ");
            student.setDept(reader.readLine().trim());

            System.out.print("CGPA  : ");
            student.setCgpa(Float.parseFloat(reader.readLine().trim()));

            System.out.print("Email : ");
            student.setEmail(reader.readLine().trim());

            System.out.print("Mobile: ");
            student.setMobile(reader.readLine().trim());
        } catch (Exception e) {
            System.out.println("Data Incorrect!");
            return;
        }

        System.out.println(studentDao.add(student));
    }

    //updateStudent menu
    private static void updateStudent() {
        System.out.println("===UPDATE STUDENT===");
        System.out.print("Enter Student ID : ");
        int id = 0;
        try {
            id = Integer.parseInt(reader.readLine().trim());
        } catch (Exception e) {
            System.out.println("ID should be a number!");
            e.printStackTrace();
            return;
        }

        Student student = studentDao.search(id);

        if (student == null) {
            System.out.println("Student with ID " + id + " not exists!");
        } else {
            Student student1 = null;
            try {
                student1 = new Student();
                student1.setId(id);
                System.out.println("Enter new details : ");
                System.out.println("Name    [" + student.getName() + "] : ");
                student1.setName(reader.readLine().trim());

                System.out.println("Dept    [" + student.getDept() + "] : ");
                student1.setDept(reader.readLine().trim());

                System.out.println("CGPA    [" + student.getCgpa() + "] : ");
                student1.setCgpa(Float.parseFloat(reader.readLine().trim()));

                System.out.println("Email   [" + student.getEmail() + "] : ");
                student1.setEmail(reader.readLine().trim());

                System.out.println("Mobile  [" + student.getMobile() + "] : ");
                student1.setMobile(reader.readLine().trim());
            } catch (Exception e) {
                System.out.println("Data Incorrect!");
                e.printStackTrace();
                return;
            }

            System.out.println(studentDao.update(student1));
        }

    }

    //searchStudent menu
    private static void searchStudent() {
        System.out.println("===SEARCH STUDENT===");
        System.out.print("Enter Student ID : ");
        int id = 0;
        try {
            id = Integer.parseInt(reader.readLine().trim());
        } catch (Exception e) {
            System.out.println("Student ID should be a number!");
            e.printStackTrace();
            return;
        }

        Student student = studentDao.search(id);

        if (student == null) {
            System.out.println("Student with ID " + id + " not exists!");
        } else {
            System.out.println("Student Detais :");
            System.out.println("Name    : " + student.getName());
            System.out.println("Dept    : " + student.getDept());
            System.out.println("CGPA    : " + student.getCgpa());
            System.out.println("Email   : " + student.getEmail());
            System.out.println("Mobile  : " + student.getMobile());
        }
    }

    //deleteStudent menu
    private static void deleteStudent() {
        System.out.println("===DELETE STUDENT===");
        System.out.print("Enter Student ID : ");
        int id = 0;
        try {
            id = Integer.parseInt(reader.readLine().trim());
        } catch (Exception e) {
            System.out.println("ID should be a number");
            return;
        }

        System.out.println(studentDao.delete(id));
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println();
            System.out.println("***WELCOME TO STUDENT DB***");
            System.out.println("SERVICES:");
            System.out.println("---------");
            System.out.println("1.Add Student");
            System.out.println("2.Update Student");
            System.out.println("3.Search Student");
            System.out.println("4.Delete Student");
            System.out.println("5.Exit");

            int choice = 0;
            try {
                System.out.print("Enter your choice : ");
                choice = Integer.parseInt(reader.readLine().trim());
                System.out.println();
            } catch (Exception e) {
                System.out.println("Choice should be a number!");
                e.printStackTrace();
            }

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Wrong Choice! Enter choice from (1,2," +
                            "3,4,5)!");
            }

        }
    }
}
