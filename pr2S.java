import java.io.*;
import java.util.*;

class pr2S {
    public static void main(String args[]) throws Exception {
        String s;
        String s1[] = new String[70], s2[] = new String[70], s3[] = new String[70];

        BufferedWriter b1 = new BufferedWriter(new FileWriter("op2.txt"));
        BufferedReader b2 = new BufferedReader(new FileReader("intm2.txt"));
        BufferedReader b3 = new BufferedReader(new FileReader("symbol2.txt"));
        BufferedReader b4 = new BufferedReader(new FileReader("literal2.txt"));

        // Load all files into arrays s1, s2, and s3
        loadFileIntoArray(b2, s1);
        loadFileIntoArray(b3, s2);
        loadFileIntoArray(b4, s3);

        // Process and write output based on s1 array content
        for (int i = 0; i < s1.length && s1[i] != null; i++) {
            switch (s1[i]) {
                case "AD":
                    if ("01".equals(s1[i + 1])) b1.write(""); // No operation for AD 01
                    if ("02".equals(s1[i + 1])) b1.write(String.format("%s\t-\t-\t00%s\n", s1[i - 1], s1[i + 3]));
                    break;
                case "R1": case "R2": case "R3": case "R4":
                    b1.write("\t" + s1[i] + "\n ");
                    break;
                case "IS":
                    b1.write(s1[i - 1] + "\t" + s1[i + 1] + "\n");
                    break;
                case "L":
                    b1.write("\t" + findValueInArray(s1[i + 1], s3, 2) + "\n");
                    break;
                case "S":
                    b1.write("\t" + findValueInArray(s1[i + 1], s2, 2) + "\n");
                    break;
                case "DL":
                    if ("01".equals(s1[i + 1])) {
                        b1.write(String.format("%s\t-\t-\t00%s\n", s1[i - 1], s1[i + 3]));
                    }
                    break;
            }
        }
        
        b1.close();
        b2.close();
        b3.close();
        b4.close();
    }

    private static void loadFileIntoArray(BufferedReader br, String[] array) throws IOException {
        String line;
        int i = 0;
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) array[i++] = st.nextToken();
        }
    }

    private static String findValueInArray(String key, String[] array, int offset) {
        for (int j = 0; j < array.length; j++) {
            if (key.equals(array[j])) return array[j + offset];
        }
        return "";
    }
}
