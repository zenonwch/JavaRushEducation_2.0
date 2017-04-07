package com.javarush.task.task34.task3408;

/* 
Кэширование
*/
public class Solution {
    public static void main(final String[] args) throws Exception {
        final SomeKey someKey = new SomeKey();
        someKey.name = "test";

        final SomeKey someKeyNew = new SomeKey();
        someKeyNew.name = "testNew";

        final SomeValue value = new SomeValue(someKey);

        final Cache<SomeKey, SomeValue> cache = new Cache<>();
        cache.put(value);

        final SomeValue valueFromCacheExisted = cache.getByKey(someKey, SomeValue.class);
        System.out.println(valueFromCacheExisted);

        final SomeValue valueFromCacheNew = cache.getByKey(someKeyNew, SomeValue.class);
        System.out.println(valueFromCacheNew);

        System.out.println(cache.size());
        /* expected output:
        SomeValue{myKey=SomeKey{name='test'}}
        SomeValue{myKey=SomeKey{name='testNew'}}
        2
         */
    }

    public static class SomeKey {
        String name;

        @Override
        public String toString() {
            return "SomeKey{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static class SomeValue {
        private SomeKey myKey;

        public SomeValue() {
        }

        public SomeValue(final SomeKey myKey) {        //use this constructor
            this.myKey = myKey;
        }

        private SomeKey getKey() {
            return myKey;
        }

        @Override
        public String toString() {
            return "SomeValue{" +
                    "myKey=" + myKey +
                    '}';
        }
    }
}
