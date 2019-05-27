import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Thread> threads = new ArrayList<>(10);

    public static void main(String[] args) {
        String userInput;
        String userInputChopped[];
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 10; i++){
            Thread thread = new Thread(new MyRunnable(), "" + (i+1));
            if (i == 9)
                thread.setName("0");
            threads.add(thread);
        }

        for (Thread thread : threads){
            thread.start();
            thread.suspend();
        }

        while (true){
            userInput = scan.nextLine();
            userInputChopped = userInput.split(" ");
            switch (userInputChopped[0]){
                case "start":
                    resumeThreads(userInputChopped);
                    break;
                case "stop":
                    suspendThreads(userInputChopped);
                    break;
                default:
                    break;
            }
        }
    }

    static void resumeThreads(String userInputChopped[]){
        for (Thread thread : threads){
            for (int i = 1; i < userInputChopped.length; i++){
                if (thread.getName().equalsIgnoreCase(userInputChopped[i]))
                        thread.resume();
            }
        }
    }

    static void suspendThreads(String userInputChopped[]){
        for (Thread thread : threads){
            for (int i = 1; i < userInputChopped.length; i++){
                if (thread.getName().equalsIgnoreCase(userInputChopped[i]))
                    thread.suspend();
            }
        }
    }

    static class MyRunnable implements Runnable {

        char ch = 'A';

        public void run(){
            while (true) {
                Thread thread = Thread.currentThread();
                System.out.println(ch++ + thread.getName());
                try {
                    thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ch > 'Z')
                    ch = 'A';
            }
        }



    }
}
