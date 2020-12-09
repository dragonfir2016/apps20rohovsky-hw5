package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntUnaryOperator;

import java.util.Iterator;

public class MapIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private IntUnaryOperator operator;

    public MapIterator(Iterator<Integer> iter, IntUnaryOperator oper) {
        this.iterator = iter;
        this.operator = oper;
    }

    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    public Integer next() {
        return this.operator.apply(this.iterator.next());
    }
}
