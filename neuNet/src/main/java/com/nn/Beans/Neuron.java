package com.nn.Beans;

import java.util.ArrayList;
import java.util.List;

public class Neuron
{
    // Constant for the sigmoid function
    final double e = 2.71828;

    // List of inputs and weights
    List<Double> inputs = new ArrayList<>();
    List<Double> weights = new ArrayList<>();

    // Node-specific variables
    double bias = 0.3;
    double threshold = 0.5;
    double output = 0.0;

    // Getters/Setters
    public void setBias(double bias)
    {
        this.bias = bias;
    }

    public void setThreshold(double threshold)
    {
        this.threshold = threshold;
    }

    public void setOutput(double output)
    {
        this.output = output;
    }

    public double getInput(int i)
    {
        return this.inputs.get(i);
    }

    // Accessors
    public void addWeight(Double w)
    {
        weights.add(w);
    }

    public void addInput(Double i)
    {
        inputs.add(i);
    }

    // Processes
    private double sigmoid(double input, double bias)
    {
        return (1 / (1 + Math.pow(e, (-input / bias))));
    }

    private void processInputAlpha()
    {
        for (int i = 0; i < inputs.size(); i++)
        {
            output += inputs.get(i) * weights.get(i);
        }
        output -= bias;
        output = sigmoid(output, bias);
    }

    private void processInputBeta()
    {
        for (int i = 0; i < inputs.size(); i++)
        {
            output += inputs.get(i) * weights.get(i);
            output -= bias;
            output = sigmoid(output, bias);
        }
    }

    private void processInputGamma()
    {
        for(int i = 0; i < inputs.size(); i++)
            output += sigmoid(weights.get(i), inputs.get(i));
        output /= inputs.size();
    }

    public double renderOutput()
    {
        processInputBeta();
        return output >= threshold ? output : 0;
    }
}
