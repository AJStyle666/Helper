import java.util.*;


public class SJF {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number of processes:");
        int n = s.nextInt();

        int[] burstT = new int[n];
        int[] waitT = new int[n];
        int[] taT = new int[n];
        int[] processID = new int[n]; // To store process IDs

        int totalTAT = 0;
        int totalWT = 0;

        System.out.println("Enter burst times:");
        for (int i = 0; i < n; i++) {
            System.out.println("P[" + (i + 1) + "]:");
            burstT[i] = s.nextInt();
            processID[i] = i + 1; // Assign process IDs sequentially
        }

        // Sorting processes by burst time (SJF)
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (burstT[i] > burstT[j]) {
                    // Swap burst times
                    int temp = burstT[i];
                    burstT[i] = burstT[j];
                    burstT[j] = temp;

                    // Swap process IDs to keep track
                    temp = processID[i];
                    processID[i] = processID[j];
                    processID[j] = temp;
                }
            }
        }

        // Calculate waiting time
        waitT[0] = 0;
        for (int i = 1; i < n; i++) {
            waitT[i] = waitT[i - 1] + burstT[i - 1];
            totalWT += waitT[i];
        }

        // Calculate turnaround time
        for (int i = 0; i < n; i++) {
            taT[i] = waitT[i] + burstT[i];
            totalTAT += taT[i];
        }

        System.out.println("Process | Burst Time | Waiting Time | Turnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println("P[" + processID[i] + "]\t\t" + burstT[i] + "\t\t" + waitT[i] + "\t\t" + taT[i]);
        }

        System.out.println("\nAverage Waiting Time: " + (float) totalWT / n);
        System.out.println("Average Turnaround Time: " + (float) totalTAT / n);
    }
}