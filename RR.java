import java.util.Scanner;

public class RR {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = s.nextInt();

        int[] processIds = new int[n];
        int[] burstTimes = new int[n];
        int[] remainingTimes = new int[n];
        int[] waitingTimes = new int[n];
        int[] turnaroundTimes = new int[n];

        System.out.print("Enter the time quantum: ");
        int quantum = s.nextInt();

        System.out.println("Enter burst times for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process[" + (i + 1) + "] Burst Time: ");
            processIds[i] = i + 1;
            burstTimes[i] = s.nextInt();
            remainingTimes[i] = burstTimes[i];
        }

        int currentTime = 0;
        boolean done;

        do {
            done = true;
            for (int i = 0; i < n; i++) {
                if (remainingTimes[i] > 0) {
                    done = false;
                    if (remainingTimes[i] > quantum) {
                        currentTime += quantum;
                        remainingTimes[i] -= quantum;
                    } else {
                        currentTime += remainingTimes[i];
                        waitingTimes[i] = currentTime - burstTimes[i];
                        remainingTimes[i] = 0;
                    }
                }
            }
        } while (!done);

        int totalWT = 0, totalTAT = 0;

        System.out.println("\nProcess | Burst Time | Waiting Time | Turnaround Time");
        for (int i = 0; i < n; i++) {
            turnaroundTimes[i] = burstTimes[i] + waitingTimes[i];
            totalWT += waitingTimes[i];
            totalTAT += turnaroundTimes[i];
            System.out.println("P[" + processIds[i] + "]\t|\t" + burstTimes[i] + "\t|\t" + waitingTimes[i] + "\t|\t" + turnaroundTimes[i]);
        }

        System.out.println("\nAverage Waiting Time: " + (float) totalWT / n);
        System.out.println("Average Turnaround Time: " + (float) totalTAT / n);

        s.close();
    }
}