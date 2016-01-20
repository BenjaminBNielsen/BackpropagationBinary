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
public class Connector {

    private double weight;
    private Neuron from;
    private Neuron to;

    public Connector(Neuron from, Neuron to) {
        this.from = from;
        this.to = to;
        weight = randomWeight();
    }
    
    public double randomWeight(){
        return Math.random()-0.5;
    }

    public void update(double learnRate, double inputError) {
        double delta = learnRate * inputError * from.getOutput();
        weight += delta;
    }

    public double getSignal() {
        return from.getOutput() * weight;
    }

}
