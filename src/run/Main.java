/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import network.Network;

/**
 *
 * @author benjamin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] layers = new int[2];
        int INPUT_NEURONS = 8;
        int OUTPUT_NEURONS = 1;
        layers[0] = INPUT_NEURONS;
        layers[1] = OUTPUT_NEURONS;

        Network nn = new Network(layers);
        double[] input = new double[INPUT_NEURONS];
        double[] expectedOutput = new double[OUTPUT_NEURONS];
        expectedOutput[0] = 3.0;
        System.out.println("input:");
        System.out.print("\t>> ");
        for (int i = 0; i < input.length; i++) {
            if (i >= 6) {
                input[i] = 1.0;
            } else {
                input[i] = 0.0;
            }
            System.out.print((int) input[i]);
        }
        System.out.println("");

        double[][] trainingData = new double[2][];
        trainingData[0] = input;
        trainingData[1] = expectedOutput;

        nn.setInput(input);
        nn.calc();
        double[] result = nn.getOutput();

        System.out.println("output:");
        for (int i = 0; i < result.length; i++) {
            System.out.println("\t>> " + result[i]);
        }

        for (int i = 0; i < 100; i++) {
            System.out.println("-----------------------");
            nn.learn(trainingData);
            nn.setInput(input);
            nn.calc();
            result = nn.getOutput();

            System.out.println("output after learning:");
            for (int j = 0; j < result.length; j++) {
                System.out.println("\t>> " + result[j]);
            }
        }

    }

}
