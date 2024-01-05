package priorityqueues;

/**
 *
 * @author Miltiadis Parcharidis AM 01183
 */
public class Process {

    private int arrivalTime;
    private int burst;
    private int priority;
    private boolean hasArrived;
    private int responseTime;
    private int turnAroundTime;
    private int pid;

    public Process(int arrivalTime, int burst, int priority, int pid) {
        this.arrivalTime = arrivalTime;
        this.burst = burst;
        this.priority = priority;
        this.hasArrived = false;
        this.pid = pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getPid() {
        return pid;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public int reduceBurst(int reduction) {
        burst = burst - reduction;
        return burst;
    }

    public boolean hasArrived() {
        return hasArrived;
    }

    public void setHasArrived(boolean hasArrived) {
        this.hasArrived = hasArrived;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    /**
     * Get the value of priority
     *
     * @return the value of priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Set the value of priority
     *
     * @param priority new value of priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Get the value of burst
     *
     * @return the value of burst
     */
    public int getBurst() {
        return burst;
    }

    /**
     * Set the value of burst
     *
     * @param burst new value of burst
     */
    public void setBurst(int burst) {
        this.burst = burst;
    }

    /**
     * Get the value of arrivalTime
     *
     * @return the value of arrivalTime
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    void printStats() {
        System.out.println("Process ID: " + getPid() + " has finished executing. Response time: " + getResponseTime()
                + " TurnAround time: " + getTurnAroundTime());
    }

}
