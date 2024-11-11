import java.util.Scanner;

class FCFS {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = s.nextInt();

        int[] burstTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];

        System.out.println("Enter burst times for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process[" + (i + 1) + "]: ");
            burstTime[i] = s.nextInt();
        }

        waitingTime[0] = 0; // Waiting time for the first process is 0

        // Calculate waiting time for each process
        for (int i = 1; i < n; i++) {
            waitingTime[i] = waitingTime[i - 1] + burstTime[i - 1];
        }

        // Calculate turnaround time for each process
        for (int i = 0; i < n; i++) {
            turnaroundTime[i] = waitingTime[i] + burstTime[i];
        }

        System.out.println("\nProcess\t| Burst Time | Waiting Time | Turnaround Time");
        int totalWT = 0, totalTAT = 0;
        for (int i = 0; i < n; i++) {
            totalWT += waitingTime[i];
            totalTAT += turnaroundTime[i];
            System.out.println("P[" + (i + 1) + "]\t|\t" + burstTime[i] + "\t|\t" + waitingTime[i] + "\t|\t" + turnaroundTime[i]);
        }

        System.out.println("\nAverage Waiting Time: " + (float) totalWT / n);
        System.out.println("Average Turnaround Time: " + (float) totalTAT / n);
    }
}