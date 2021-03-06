package ua.edu.ucu.iterators;

import java.util.Iterator;

public class StreamIterator implements Iterator<Integer> {
    private final int[] numbers;
    private int idx = 0;

    public StreamIterator(int... values) {
        this.numbers = values.clone();
    }

    @Override
    public boolean hasNext() {
        if (this.idx < this.numbers.length) {
            return true;
        }
        this.idx = 0;
        return false;
    }

    @Override
    public Integer next() {
        int res = this.numbers[this.idx];
        this.idx++;
        return res;
    }
}
