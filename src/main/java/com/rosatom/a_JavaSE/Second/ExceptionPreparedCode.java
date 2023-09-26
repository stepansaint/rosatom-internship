package com.rosatom.a_JavaSE.Second;

public class ExceptionPreparedCode {
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            try {
                throw new MyException();
            } catch (Throwable e) {
                System.out.println("e = " + e);
                if (e.getCause() instanceof MyException ex) {
                    System.out.println("e.getCause() instanceof "
                            + MyException.class.getName() + ", s = " + ex.get());
                }
            }
            System.out.println();
        }
    }
}

class MyException extends RuntimeException {
    private String s;

    static {
        init();
    }

    public MyException() {
        s = "Hello";
    }

    private static void init() {
        throw new MyException();
    }

    public String get() {
        return s;
    }
}
