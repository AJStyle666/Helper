import java.util.Scanner;

public class PS {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = s.nextInt();

        int[] processIds = new int[n];
        int[] burstTimes = new int[n];
        int[] priorities = new int[n];
        int[] waitingTimes = new int[n];
        int[] turnaroundTimes = new int[n];

        System.out.println("Enter burst time and priority for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process[" + (i + 1) + "] (Burst Time Priority): ");
            processIds[i] = i + 1;
            burstTimes[i] = s.nextInt();
            priorities[i] = s.nextInt();
        }

        // Sort by priority (highest priority first)
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (priorities[i] > priorities[j]) {
                    // Swap priorities
                    int temp = priorities[i];
                    priorities[i] = priorities[j];
                    priorities[j] = temp;

                    // Swap burst times
                    temp = burstTimes[i];
                    burstTimes[i] = burstTimes[j];
                    burstTimes[j] = temp;

                    // Swap process IDs to maintain process order
                    temp = processIds[i];
                    processIds[i] = processIds[j];
                    processIds[j] = temp;
                }
            }
        }

        waitingTimes[0] = 0; // First process has a waiting time of 0
        int totalWT = 0, totalTAT = 0;

        // Calculate waiting time for each process
        for (int i = 1; i < n; i++) {
            waitingTimes[i] = waitingTimes[i - 1] + burstTimes[i - 1];
            totalWT += waitingTimes[i];
        }

        // Calculate turnaround time and display process table
        System.out.println("\nProcess | Burst Time | Priority | Waiting Time | Turnaround Time");
        for (int i = 0; i < n; i++) {
            turnaroundTimes[i] = waitingTimes[i] + burstTimes[i];
            totalTAT += turnaroundTimes[i];
            System.out.println("P[" + processIds[i] + "]\t|\t" + burstTimes[i] + "\t|\t" + priorities[i] + "\t|\t" + waitingTimes[i] + "\t|\t" + turnaroundTimes[i]);
        }

        // Calculate and display average waiting time and average turnaround time
        System.out.println("\nAverage Waiting Time: " + (float) totalWT / n);
        System.out.println("Average Turnaround Time: " + (float) totalTAT / n);
        
        s.close();
    }
}