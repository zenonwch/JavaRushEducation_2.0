package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E>
        implements Set<E>, Serializable, Cloneable {
    private static final long serialVersionUID = 1713329991004446844L;

    private transient HashMap<E, Object> map; //transient means not serializable

    private static final Object PRESENT = new Object();

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

    private void writeObject(final ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();

        final int size = size();
        oos.writeInt(size);

        final int capacity = HashMapReflectionHelper.callHiddenMethod(map, "capacity");
        oos.writeInt(capacity);

        final float loadFactor = HashMapReflectionHelper.callHiddenMethod(map, "loadFactor");
        oos.writeFloat(loadFactor);

        for (final E e : map.keySet()) {
            oos.writeObject(e);
        }
    }

    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();

        final int size = ois.readInt();

        final int capacity = ois.readInt();
        final float loadFactory = ois.readFloat();

        map = new HashMap<>(capacity, loadFactory);

        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked") final E e = (E) ois.readObject();
            map.put(e, PRESENT);
        }
    }
}
