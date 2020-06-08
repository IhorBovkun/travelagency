package sandbox2.models;

import sandbox2.utils.Utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public abstract class Container implements Iterable, Serializable {

    //  Google App Engine session data must be Serializable
    private static final long serialVersionUID = 1L;
    
    private Container getInstance() {
        return this;
    }

    /**
     * Iterator
     */
    private class ContainerIterator implements Iterator {

        private Container container;
        private Field[] fields;
        private int end;
        private int index = 0;

        {
            container = getInstance();
            fields = container.getClass().getDeclaredFields();
            end = fields != null ? fields.length : 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void forEachRemaining(Consumer action) {

        }

        @Override
        public boolean hasNext() {
            return index < end;
        }

        @Override
        public Object next() {
            try {
                String name = fields[index].getName();
                name = "get" + Utils.capitalize(name);

                index++;
                return container.getClass().getMethod(name, null)
                        .invoke(container, null);

            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }

        public int length() {
            return end;
        }

        public String nextField() {
            return fields[index++].getName();
        }
    }

    /**
     * Iterable
     */
    @Override
    public void forEach(Consumer action) {

    }

    @Override
    public Spliterator<TourData> spliterator() {
        return null;
    }

    @Override
    public Iterator iterator() {
        return new ContainerIterator();
    }
}
