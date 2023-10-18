package com.rosatom.a_javaSE.b_collections_exceptions_reflection;

import java.util.*;

public class LimitedArrayList {
    public static void main(String[] args) {
        List<Integer> limitedList = new ArrayList<>(10) {
            @Override
            public boolean add(Integer integer) {
                if (this.size() == 10) {
                    throw new SizeArrayListException("Can't add 11th element");
                }
                return super.add(integer);
            }

            @Override
            public void add(int index, Integer element) {
                if (this.size() == 10) {
                    throw new SizeArrayListException("Can't add 11th element");
                }
                super.add(index, element);
            }

            @Override
            public boolean addAll(Collection<? extends Integer> c) {
                if (this.size() + c.size() > 10) {
                    throw new SizeArrayListException("Can't have more than 10 elements");
                }
                return super.addAll(c);
            }

            @Override
            public boolean addAll(int index, Collection<? extends Integer> c) {
                if (this.size() + c.size() > 10) {
                    throw new SizeArrayListException("Can't have more than 10 elements");
                }
                return super.addAll(index, c);
            }
        };

        for (int i = 0; i < 10; i++) {
            limitedList.add(i);
        }

    /* each of next 4 lines throws SizeArrayListException */
//        limitedList.add(11);
//        limitedList.add(2, 11);
//        limitedList.addAll(new LinkedHashSet<>(Set.of(11)));
//        limitedList.addAll(0, new LinkedList<>(List.of(11)));
    }
}

class SizeArrayListException extends RuntimeException {
    private String message;
    private Throwable cause;

    public SizeArrayListException() {}

    public SizeArrayListException(String message) {
        super(message);
        this.message = message;
    }

    public SizeArrayListException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }

    public SizeArrayListException(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getLocalizedMessage() {
        if ("ru".equals(Locale.getDefault().getLanguage())) {
            return "Невозможно иметь экземпляр ArrayList, содержащий более 10 элементов";
        }
        return super.getLocalizedMessage();
    }
}