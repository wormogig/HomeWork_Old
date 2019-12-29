package Lesson_03.Task_04;


import java.io.*;
import java.net.Socket;

public class Client {


    public static void main(String[] args) {
        Socket socket = null;
        ObjectOutputStream out = null;
        Student student = new Student(1,"Elon Musk");
        student.info();
        try {
            socket = new Socket("localhost", 8189);
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(student);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Студент отправлен");

    }
}
