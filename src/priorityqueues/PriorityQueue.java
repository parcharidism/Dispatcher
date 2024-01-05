package priorityqueues;

import java.util.ArrayList;

/**
 *
 * @author Miltiadis Parcharidis AM 01183
 */
public class PriorityQueue extends ArrayList {

    int priority;

    public PriorityQueue(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}
