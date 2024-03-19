package ca.mcmaster.se2aa4.island.team102;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TemplateTest {
    private Drone testDrone;

    @BeforeAll
    public void initDrone() {testDrone = new Drone();}
}
