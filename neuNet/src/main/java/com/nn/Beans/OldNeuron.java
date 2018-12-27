package com.nn.Beans;

import java.util.ArrayList;
import java.util.List;

public class OldNeuron
{
    final double e = 2.71828;
    List<Double> inputs;
    List<Double> weights;
    double bias = 1;
    double threshold = 0.5;
    double output = 0;

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    double sigmoid(double input, double bias) {
        //return (1 / (1 + Math.pow(e, -(weight * input + bias))));
        return (1 / (1 + Math.pow(e, (-input / bias))));
    }

    public void processInput() {
        for (int i = 0; i < inputs.size(); i++)
//            output += sigmoid(weights.get(i), inputs.get(i), bias);
//        output /= inputs.size();

            output += inputs.get(i) * weights.get(i);
        output -= bias;
        output = sigmoid(output, bias);

    }

    public double renderOutput() {
        return output >= threshold ? output : 0;
    }

    public void addInput(double i) {
        if (inputs == null)
            inputs = new ArrayList<Double>();
        inputs.add(i);
    }

    public void addWeight(double i) {
        if (weights == null)
            weights = new ArrayList<Double>();
        weights.add(i);
    }
}
