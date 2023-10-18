package com.rosatom.a_javaSE.a_core;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        ///
        setAnotherInput();
        ///

        new ConsoleBattleship("Stepan1", "Stepan2").start();
    }

    private static void setAnotherInput() {
        System.setIn(new ByteArrayInputStream(
                ("0x0 0x2 0x4 0x6 " +                   // first player
                        "V 4x0 4x1 V 2x4 2x5 V 2x8 2x9 " +
                        "V 7x0 7x1 7x2 V 7x5 7x6 7x7 " +
                        "H 6x9 7x9 8x9 9x9 " +

                        "0x0 0x2 0x4 0x6 " +                // second player
                        "V 4x0 4x1 V 2x4 2x5 V 2x8 2x9 " +
                        "V 7x0 7x1 7x2 V 7x5 7x6 7x7 " +
                        "H 6x9 7x9 8x9 9x9 " +

                        "0x1 0x1 0x0 0x2 0x4 0x6 " +                // game process
                        "V 4x0 4x1 V 2x4 2x5 V 2x8 2x9 " +
                        "V 7x0 7x1 7x2 V 7x5 7x6 7x7 " +
                        "H 6x9 7x9 8x9 9x9 0x0   0x0 0x0"

                ).getBytes(StandardCharsets.UTF_8)
        ));
    }
}
