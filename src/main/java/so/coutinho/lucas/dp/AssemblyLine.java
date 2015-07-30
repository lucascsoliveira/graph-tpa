package so.coutinho.lucas.dp;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lucas.oliveira
 */
@Getter
@Setter
public class AssemblyLine {

    private Integer F;
    private Integer F1[];
    private Integer F2[];
    private Integer L;
    private Integer L1[];
    private Integer L2[];

    public void run(Integer a[][], Integer t[][], Integer e[], Integer c[], Integer n) {
        initializeAtributes(n);

        //  f1[1] <- e1+a1,1
        F1[0] = e[0] + a[0][0];
        //  f2[1] <- e2+a2,1
        F2[0] = e[1] + a[1][0];

        // Para j <- 2 até n faça
        for (int j = 1; j < n; j++) {
            // se (f1[j-1] + a1,j <= f2[j-1] + t2, j-1 + a1,j)
            if (F1[j - 1] + a[0][j] <= F2[j - 1] + t[1][j - 1] + a[0][j]) {
                // então
                //      f1[j] <- f1[j-1] + a1,j
                F1[j] = F1[j - 1] + a[0][j];
                //      l1[j] <- 1
                L1[j] = 1;
            } // senão
            else {
                //      f1[j] <- f2[j-1] + t2, j-1 + a1,j
                F1[j] = F2[j - 1] + t[1][j - 1] + a[0][j];
                //      l1[j] <- 2
                L1[j] = 2;
            }

            if (F2[j - 1] + a[1][j] <= F1[j - 1] + t[0][j - 1] + a[1][j]) {
                F2[j] = F2[j - 1] + a[1][j];
                L2[j] = 2;
            } else {
                F2[j] = F1[j - 1] + t[0][j - 1] + a[1][j];
                L2[j] = 1;
            }
        }

        // se f1[n]+x1 <= f2[n]+x2
        if (F1[n - 1] + c[0] <= F2[n - 1] + c[1]) {
            // então
            //      f*=f1[n]+x1; l*=1
            F = F1[n - 1] + c[0];
            L = 1;
        } // senão
        else {
            //      f*=f2[n]; l* = 2
            F = F2[n - 1];
            L = 2;
        }
    }

    private void initializeAtributes(Integer numberOfStations) {
        F = 0;
        F1 = new Integer[numberOfStations];
        F2 = new Integer[numberOfStations];
        L = 0;
        L1 = new Integer[numberOfStations];
        L2 = new Integer[numberOfStations];
    }

    public static void main(String[] args) {
        Integer timeStation[][] = {
            {7, 9, 3, 4, 8, 4},
            {8, 5, 6, 4, 5, 7}
        };

        Integer timeChangeLine[][] = {
            {2, 3, 1, 3, 4},
            {2, 1, 2, 2, 1}
        };

        Integer timeStart[] = {2, 4};
        Integer timeEnd[] = {3, 2};
        Integer numberOfStations = 6;

        AssemblyLine assemblyLine = new AssemblyLine();
        assemblyLine.run(timeStation, timeChangeLine, timeStart, timeEnd, numberOfStations);

        for (int i = 1; i <= numberOfStations; i++) {
            System.out.printf("\t%3d", i);
        }

        System.out.printf("\nF1[j]");
        for (int j = 0; j < numberOfStations; j++) {
            System.out.printf("\t%3d", assemblyLine.getF1()[j]);
        }

        System.out.printf("\nF2[j]");
        for (int j = 0; j < numberOfStations; j++) {
            System.out.printf("\t%3d", assemblyLine.getF2()[j]);
        }

        System.out.printf("\n");
        System.out.printf("F* = %d\n", assemblyLine.getF());

        for (int i = 1; i <= numberOfStations; i++) {
            System.out.printf("\t%3d", i);
        }

        System.out.printf("\nL1[j]");
        for (int j = 0; j < numberOfStations; j++) {
            System.out.printf("\t%3d", assemblyLine.getL1()[j]);
        }

        System.out.printf("\nL2[j]");
        for (int j = 0; j < numberOfStations; j++) {
            System.out.printf("\t%3d", assemblyLine.getL2()[j]);
        }

        System.out.printf("\n");
        System.out.printf("L* = %d\n", assemblyLine.getL());
    }

}
