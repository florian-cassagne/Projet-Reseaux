package clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class publisher {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);
            String line = null;
            System.out.print("Entrez un pseudo : ");

            String user = scanner.nextLine();

            System.out.println("Maintenant envoyez des messages, écrire STOP fermera le client");
            while (!"stop".equalsIgnoreCase(line)) {
                line = scanner.nextLine();
                out.println("PUBLISH author:@" + user + "\\r\\n" + line);
                out.flush();
                System.out.println(in.readLine());
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


