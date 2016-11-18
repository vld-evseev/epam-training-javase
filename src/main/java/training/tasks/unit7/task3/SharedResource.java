package training.tasks.unit7.task3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SharedResource {

    private BlockingQueue<Integer> blockingList;

    public SharedResource() {
        blockingList = new ArrayBlockingQueue<Integer>(100, true);
    }

    public void setElement(Integer element) {
        blockingList.add(element);
    }

    public Integer getElement() {
        if (blockingList.size() > 0) {
            return blockingList.poll();
        }

        return null;
    }

    public BlockingQueue<Integer> getList() {
        return blockingList;
    }
}
