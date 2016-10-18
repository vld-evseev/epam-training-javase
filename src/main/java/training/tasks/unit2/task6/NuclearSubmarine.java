package training.tasks.unit2.task6;

public class NuclearSubmarine {

    private NuclearReactor nuclearReactor;
    private boolean isStarted = false;

    public NuclearSubmarine() {
        nuclearReactor = new NuclearReactor();
    }

    public void start() {
        if (!isStarted) {
            isStarted = true;
            nuclearReactor.launch();
            System.out.println("Submarine started successfully");
        } else {
            System.out.println("Submarine is on the run already");
        }
    }

    private static class NuclearReactor {

        void launch() {
            System.out.println("Nuclear reactor started");
        }

    }

}
