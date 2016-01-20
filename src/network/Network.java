/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

/**
 *
 * @author benjamin
 */
public class Network {

    private Neuron[][] nn;
    private double learnRate = 0.1;

    public Network(int[] size) {
        nn = new Neuron[size.length][];
        for (int i = 0; i < size.length; i++) {
            nn[i] = new Neuron[size[i]];
        }
        for (int i = 0; i < nn.length; i++) {
            for (int j = 0; j < nn[i].length; j++) {
                nn[i][j] = new Neuron();
                //if we are not in the input layer, we add connectors
                if (i > 0) {
                    Neuron toNeuron = nn[i][j];
                    for (int k = 0; k < nn[i - 1].length; k++) {
                        Neuron fromNeuron = nn[i - 1][k];
                        Connector c = new Connector(fromNeuron, toNeuron);
                        toNeuron.addConnector(c);
                    }
                }
            }
        }
    }

    public void setInput(double[] data) {
        if (data.length != nn[0].length) {
            throw new RuntimeException("The length of the dataset must be: " + nn[0].length);
        }

        for (int i = 0; i < data.length; i++) {
            double e = data[i];
            nn[0][i].setInput(e);
        }
    }

    public void calc() {
        for (int i = 0; i < nn.length; i++) {
            for (int j = 0; j < nn[i].length; j++) {
                Neuron n = nn[i][j];
                // only calculate input after input layer
                if (i > 0) {
                    n.calcInput();
                }
                n.calcOutput();
            }
        }
    }

    public int getOutputLayer() {
        return nn.length - 1;
    }

    public double[] getOutput() {
        int outputNeurons = nn[getOutputLayer()].length;
        double[] result = new double[outputNeurons];
        for (int i = 0; i < outputNeurons; i++) {
            result[i] = nn[getOutputLayer()][i].getOutput();
        }
        return result;
    }
    
    public void learn(double[][] data){
        setInput(data[0]);
        calc();
        double[] output = getOutput();
        double[] expectedOutput = data[1];
        for (int i = 0; i < expectedOutput.length; i++) {
            double error = expectedOutput[i]-output[i];
            nn[getOutputLayer()][i].setOutputError(error);
            nn[getOutputLayer()][i].calcInputError();
            nn[getOutputLayer()][i].update(learnRate);
        }
    }

    public double getLearnRate() {
        return learnRate;
    }

    public void setLearnRate(double learnRate) {
        this.learnRate = learnRate;
    }
}
