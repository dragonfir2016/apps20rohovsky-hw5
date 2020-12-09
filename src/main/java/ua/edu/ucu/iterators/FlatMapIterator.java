package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.AsIntStream;

import java.util.Iterator;

public class FlatMapIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private IntToIntStreamFunction function;
    private StreamIterator currIterator = new StreamIterator();

    public FlatMapIterator(Iterator<Integer> iter, IntToIntStreamFunction oper) {
        this.iterator = iter;
        this.function = oper;
    }

    @Override
    public boolean hasNext() {
        if (this.currIterator.hasNext()) {
            return true;
        }
        if (this.iterator.hasNext()) {
            AsIntStream tempStream = (AsIntStream) this.function.applyAsIntStream(iterator.next());
            this.currIterator = new StreamIterator(tempStream.toArray());
            return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        return this.currIterator.next();
    }
}
