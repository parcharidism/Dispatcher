package priorityqueues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miltiadis Parcharidis 011873
 */
public class Dispatcher {

    public static void main(String[] args) {

        int numProcesses;
        int arrivalTime;
        int burst;
        int priority;
        int userQ = 1;
        // Η λίστα με όλα τα Priority queues
        ArrayList<PriorityQueue> queueList;
        queueList = Utils.init();

        // Το ReadyQueue (ή execution queue)
        ArrayList<Process> readyQueue = new ArrayList();
        // Η λίστα με τις διεργασίες που ολοκληρώνονται
        ArrayList<Process> deadQueue = new ArrayList();
        Process p = null;

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("Welcome to the CPU Dispatcher:");

// Εισαγωγή δεδομένων χρήστη
        try {
            System.out.print("Please enter no of proccesses for entry (at least 1 is required): ");
            numProcesses = Integer.parseInt(reader.readLine());
            int pid = -1;
            for (int i = 0; i < numProcesses; i++) {
                pid = Utils.getnewpid();
                System.out.println("======= Proccess " + pid + " =======");
                System.out.print("Enter arrival time: ");
                arrivalTime = Integer.parseInt(reader.readLine());
                System.out.print("Enter burst: ");
                burst = Integer.parseInt(reader.readLine());
                System.out.print("Enter priority (1-7): ");
                priority = Integer.parseInt(reader.readLine());

                // Δημιουργία και προσθήκη διεργασίας στην ουρά προτεραιότητάς της
                p = new Process(arrivalTime, burst, priority, pid);
                Utils.putInQueue(p);
                System.out.println("New process with id: " + pid + " added in priority queue " + priority);
                System.out.println();

            }
            System.out.println("Enter quantum time: ");
            userQ = Integer.parseInt(reader.readLine());
        } catch (IOException ex) {
            Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        // Παράμετροι προγράμματος
        int quantumRem = userQ;
        int timeNow = 0;

        /**
         * Σορτάρουμε τις λίστες προτεραιοτήτων μετά την είσοδο δεδομένων του
         * χρήστη ώστε να έχουμε σωστή σειρά άφιξης διεργασιών
         */
        for (PriorityQueue pq : queueList) {
            Utils.sortByArrival(pq);
        }

        System.out.println("Starting the Dispatcher");
        while (true) {

            /**
             * Διάβασε από τη λίστα ουρών τα πρώτα τους Process και έλεγξε εάν
             * είναι η ώρα τους να μπουν στη λίστα εκτέλεσης.
             */
            for (PriorityQueue pq : queueList) {
                if (!pq.isEmpty()) {
                    p = (Process) pq.get(0);
                    if (p != null) {
                        if (p.getArrivalTime() <= timeNow) {
                            if (!p.hasArrived()) {
                                p.setResponseTime(timeNow - p.getArrivalTime());
                                p.setHasArrived(true);
                                System.out.println("Process ID: " + p.getPid() + " has just arrived. Time now is: " + timeNow);
                            }

                            readyQueue.add((Process) pq.remove(0));
                            break;
                        }
                    }
                }

            }

            if (!readyQueue.isEmpty()) {
                p = readyQueue.get(0);
                // Εκτέλεση διεργασίας στην ουρά και εναπόθεση στην τελευταία θέση εάν έχει ακόμη burst
                // Εαν η διεργασία έχει μικρότερο χρόνο από το quantum εκτέλεσέ την και φύγε            
                quantumRem = quantumRem <= p.getBurst() ? userQ : p.getBurst();
                while (quantumRem > 0) {
                    p.reduceBurst(1);
                    timeNow++; // Περνάει ο χρόνος
                    quantumRem--; // Μείωσε το κβάντο χρόνου
                }

                quantumRem = userQ; // επαναφορά τιμής quantum

                p = readyQueue.remove(0);
                if (p.getBurst() > 0) {
                    // Θέσε το arrival time της Process τελευταίο και τοποθέτησέ την στην αρχή της λίστας προτεραιότητάς της
                    Utils.putInQueue(p);

                } else { // Δεν έχει άλλο burst, σημείωσε το turnaroud time
                    p.setTurnAroundTime(timeNow - p.getArrivalTime());
                    p.printStats();
                    deadQueue.add(p);
                }
                p = null;
            } else {
                timeNow++; // Περνάει ο χρόνος - Περίπτωση χωρίς διεργασίες στην readyqueue
            }
            
            // Για debug λόγους
            System.out.println("Time before generating new process is: " + timeNow);
            // Δημιουργία νέας τυχαίας διεργασίας
            Utils.generateNewProcess(timeNow);

            if (deadQueue.size() > 9) {
                Utils.printStats(deadQueue);
                deadQueue.clear();
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
