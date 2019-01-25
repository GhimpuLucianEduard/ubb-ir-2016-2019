package com.company.Interfaces;

import com.company.GrainSync.Node;

public interface Iterator {
    double getNext();
    boolean isValid();
    double getElement(int index);
}
