package training.tasks.unit7.task3;

import java.util.Random;

class IntegerSetterGetter extends Thread {
    private SharedResource resource;
    private volatile boolean run;

    private Random rand = new Random();

    public IntegerSetterGetter(String name, SharedResource resource) {
        super(name);
        this.resource = resource;
        run = true;
    }

    public void stopThread() {
        run = false;
    }

    public void run() {
        int action;

        while (run) {
            action = rand.nextInt(1000);

            if (action % 2 == 0) {
                getIntegersFromResource();
            } else {
                setIntegersIntoResource();
            }
        }
        System.out.println("Поток " + getName() + " завершил работу.");
    }

    private void getIntegersFromResource() {
        Integer number;

        while ((number = resource.getElement()) != null) {
            System.out.println("Поток " + getName() + " извлек число " + number);
        }
    }

    private void setIntegersIntoResource() {
        Integer number = rand.nextInt(500);
        resource.setElement(number);
        System.out.println("Поток " + getName() + " записал число " + number);
    }
}
