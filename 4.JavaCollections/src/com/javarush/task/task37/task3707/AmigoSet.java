package com.javarush.task.task37.task3707;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class AmigoSet<E> extends AbstractSet<E> implements Set<E>, Serializable, Cloneable {
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(final Collection<? extends E> collection) {
        map = new HashMap<>(Math.max(16, (int) Math.ceil(collection.size() / .75f)));
        addAll(collection);
    }

    @Override
    public boolean add(final E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(final Object o) {
        return map.keySet().contains(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(final Object o) {
        return map.remove(o) == null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AmigoSet<E> clone() {
        try {
            final AmigoSet<E> newAmigoSet = (AmigoSet<E>) super.clone();
            newAmigoSet.map = (HashMap<E, Object>) map.clone();
            return newAmigoSet;
        } catch (final Exception e) {
            throw new InternalError(e);
        }
    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }

    @Override
    public boolean removeIf(final Predicate<? super E> filter) {
        return false;
    }

    @Override
    public Stream<E> stream() {
        return null;
    }

    @Override
    public Stream<E> parallelStream() {
        return null;
    }

    @Override
    public void forEach(final Consumer<? super E> action) {

    }
}
