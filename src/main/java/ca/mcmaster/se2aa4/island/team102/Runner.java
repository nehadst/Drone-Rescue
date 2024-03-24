package ca.mcmaster.se2aa4.island.team102;

import static eu.ace_design.island.runner.Runner.run;

import java.io.File;

public class Runner {
//This class is used to run the simulation.
    private Runner() {
        throw new IllegalStateException("Utility class");
    }    

    public static void main(String[] args) {
        //(String[]) -> void
        //Main method to start the simulation with predefined settings. It loads the exploration map from a file,
        //sets up the simulation parameters, and starts the exploration. Exits the program if any exception occurs.
        String filename = args[0];
        try {
            run(Explorer.class)
                    .exploring(new File(filename))
                    .withSeed(42L)
                    .startingAt(1, 1, "EAST")
                    .backBefore(7000)
                    .withCrew(5)
                    .collecting(1000, "WOOD")
                    .storingInto("./outputs")
                    .withName("Island")
                    .fire();
        } catch(Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

}
