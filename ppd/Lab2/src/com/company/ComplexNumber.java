package com.company;

public class ComplexNumber {
    float a;
    float b;

    public float getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public ComplexNumber(float a, float b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return a + " " + b + "i";
    }

    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(a + other.a, b + other.b);
    }

    public ComplexNumber multiply(ComplexNumber other) {
        float newA = (a * other.a) - (b * other.b);
        float newB = (a * other.b) + (b * other.a);
        return new ComplexNumber(newA, newB);
    }

    public ComplexNumber division(ComplexNumber other) {
        float numi = (other.a * other.a) + (other.b * other.b);
        float newA = ((a * other.a) + (b * other.b)) / numi;
        float newB = ((b * other.a) + (a * other.b * (-1))) / numi;
        return new ComplexNumber(newA, newB);
    }
}

