package org.acme.getting.started;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

/**
 * @author tam
 */
@QuarkusMain
public class QuarksApplication {
    public static void main(String ... args) {
        System.out.println("Running main method");
        Quarkus.run(args);
    }
}
