package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterators.FilterIterator;
import ua.edu.ucu.iterators.FlatMapIterator;
import ua.edu.ucu.iterators.MapIterator;
import ua.edu.ucu.iterators.StreamIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AsIntStream implements IntStream {
    private final Iterator<Integer> elements;

    private AsIntStream(Iterator<Integer> iter) {
        this.elements = iter;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(new StreamIterator(values));
    }

    private void checkIsEmpty() {
        if (!this.elements.hasNext()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Double average() {
        this.checkIsEmpty();
        return (double) this.sum() / this.count();
    }

    @Override
    public Integer max() {
        this.checkIsEmpty();
        return this.reduce(this.elements.next(), Math::max);
    }

    @Override
    public Integer min() {
        this.checkIsEmpty();
        return this.reduce(this.elements.next(), Math::min);
    }

    @Override
    public long count() {
        long count = 0;
        while (this.elements.hasNext()) {
            this.elements.next();
            count++;
        }
        return count;
    }

    @Override
    public Integer sum() {
        this.checkIsEmpty();
        return this.reduce(0, (sum, x) -> sum += x);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIterator(this.elements, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        while (this.elements.hasNext()) {
            action.accept(this.elements.next());
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIterator(this.elements, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIterator(this.elements, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int result = identity;
        while (this.elements.hasNext()) {
            int numb = this.elements.next();
            result = op.apply(result, numb);
        }
        return result;
    }

    @Override
    public int[] toArray() {
        List<Integer> temp = new ArrayList<>();
        while (this.elements.hasNext()) {
            temp.add(this.elements.next());
        }
        int[] result = new int[temp.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = temp.get(i);
        }
        return result;
    }
}