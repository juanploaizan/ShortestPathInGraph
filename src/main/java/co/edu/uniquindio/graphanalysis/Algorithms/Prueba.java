package co.edu.uniquindio.graphanalysis.Algorithms;

import co.edu.uniquindio.graphanalysis.TestCasesGenerator;

public class Prueba {
        public static void main(String[] args)
        {
            int cont = 0;
            int[][] g = TestCasesGenerator.loadGraphFromFile("graph250.txt", 250);
            for (int i = 0; i < g.length; i++){
                for (int j = 0; j < g.length; j++){
                    if(cont % 3 == 0 && cont != 0){
                        System.out.println("xxx");
                    }
                    System.out.print(g[i][j] + " ");
                    cont++;
                }
            }
        }
}
