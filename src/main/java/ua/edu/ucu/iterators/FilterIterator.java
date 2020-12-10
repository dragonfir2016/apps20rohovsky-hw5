package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;

public class FilterIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private IntPredicate predicate;
    private int currValue;

    public FilterIterator(Iterator<Integer> iter, IntPredicate pred) {
        this.iterator = iter;
        this.predicate = pred;
    }


    @Override
    public boolean hasNext() {
        while (this.iterator.hasNext()) {
            this.currValue = this.iterator.next();
            if (this.predicate.test(this.currValue)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return this.currValue;
    }
}
