/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author benjamin
 */
public class Neuron {

    private List<Connector> connections = new ArrayList<>();
    private double output = 0.0;
    private double input = 0.0;
    private double inputError = 0.0;
    private double outputError = 0.0;

    public Neuron(){
    }
    
    public void addConnector(Connector con){
        connections.add(con);
    }
    
    public void calc(){
        calcInput();
        calcOutput();
    }
    
    public void calcInput(){
        input = 0.0;
        for (int i = 0; i < connections.size(); i++) {
            Connector theConnector = connections.get(i);
            input += theConnector.getSignal();
        }
    }
    
    public void update(double learnRate){
        for (int i = 0; i < connections.size(); i++) {
            Connector c = connections.get(i);
            c.update(learnRate, inputError);
        }
    }

    public void setInput(double input) {
        this.input = input;
    }
    
    public void calcOutput() {
        output = activationFunction(input);
    }

    public double activationFunction(double x) {
        return x;
    }

    public double getOutput() {
        return output;
    }

    public List<Connector> getConnections() {
        return connections;
    }

    public void setConnections(List<Connector> connections) {
        this.connections = connections;
    }

    public double getInputError() {
        return inputError;
    }

    public void setInputError(double inputError) {
        this.inputError = inputError;
    }
    
    public void setOutputError(double error){
        outputError = error;
    }
    
    public void calcInputError(){
        inputError = outputError;
    }

    public double getOutputError() {
        return outputError;
    }
    
}
