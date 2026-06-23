package Iteration;

import java.util.Iterator;

public class MyRangeIterator implements Iterator<Integer> {
    private int start;
    private int end;

    public MyRangeIterator(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean hasNext() {
        return start < end;
    }

    @Override
    public Integer next() {
        start+=2;
        return start;
    }
}