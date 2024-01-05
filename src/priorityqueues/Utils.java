package priorityqueues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author Miltiadis Parcharidis AM 01183
 */
public class Utils {

    private static PriorityQueue pq1;
    private static PriorityQueue pq2;
    private static PriorityQueue pq3;
    private static PriorityQueue pq4;
    private static PriorityQueue pq5;
    private static PriorityQueue pq6;
    private static PriorityQueue pq7;
    private static ArrayList<PriorityQueue> queueList;
    private static int pid = 0;

    public static int rndGen(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);

    }

    public static void generateNewProcess(int timeNow) {

        int burst = rndGen(1, 5); // τυχαίο burst
        int priority = rndGen(1, 7);

        int arrival = rndGen(timeNow + 1, timeNow + 5);
        Process p = new Process(arrival, burst, priority, getnewpid());
        putInQueue(p);

    }

    static ArrayList init() {
        pq1 = new PriorityQueue(1);
        pq2 = new PriorityQueue(2);
        pq3 = new PriorityQueue(3);
        pq4 = new PriorityQueue(4);
        pq5 = new PriorityQueue(5);
        pq6 = new PriorityQueue(6);
        pq7 = new PriorityQueue(7);

        queueList = new ArrayList();
        queueList.add(pq1);
        queueList.add(pq2);
        queueList.add(pq3);
        queueList.add(pq4);
        queueList.add(pq5);
        queueList.add(pq6);
        queueList.add(pq7);

        return queueList;
    }

    static void putInQueue(Process p) {

        switch (p.getPriority()) {
            case 1:
                pq1.add(p);
                break;
            case 2:
                pq2.add(p);
                break;
            case 3:
                pq3.add(p);
                break;
            case 4:
                pq4.add(p);
                break;
            case 5:
                pq5.add(p);
                break;
            case 6:
                pq6.add(p);
                break;
            case 7:
                pq7.add(p);
                break;
        }
    }

    static void printStats(ArrayList<Process> deadQueue) {
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("Stats of the last  " + deadQueue.size() + " processes");
        double avgResponse = 0;
        double avgTurnAround = 0;
        for (Process p : deadQueue) {
            avgResponse = avgResponse + p.getResponseTime();
            avgTurnAround = avgTurnAround + p.getTurnAroundTime();
        }
        System.out.println();
        System.out.println("Avg. Response Time: " + String.format("%.2f", avgResponse / deadQueue.size()));
        System.out.println("Avg. Turn Around Time: " + String.format("%.2f", avgTurnAround / deadQueue.size()));
        System.out.println("----------------------------------------------");
        System.out.println();
        promptToContinue();
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("Clearing dead queue; continuing the Dispatcher");
        System.out.println("----------------------------------------------");
    }

    static int getnewpid() {
        return ++pid;
    }

    private static void promptToContinue() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press c to continue or q to quit");
        String input = "";

        do {
            input = scanner.nextLine();
        } while (input.equalsIgnoreCase("c")
                ^ !input.equalsIgnoreCase("q"));

        if (input.equalsIgnoreCase("q")) {
            System.exit(0);
        }
    }

    static void sortByArrival(ArrayList<Process> processes) {

        Collections.sort(processes, new Comparator() {

            public int compare(Object o1, Object o2) {
                Integer x3 = ((Process) o1).getArrivalTime();
                Integer x4 = ((Process) o2).getArrivalTime();
                return x3.compareTo(x4);
            }
        });
    }

}
