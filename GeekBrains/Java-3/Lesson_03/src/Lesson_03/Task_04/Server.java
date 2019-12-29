package Lesson_03.Task_04;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;
        try {
            System.out.println("Жду студента...");
            server = new ServerSocket(8189);
            socket = server.accept();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Student student = (Student)in.readObject();
            student.info();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
