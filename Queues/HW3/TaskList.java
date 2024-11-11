import java.util.Iterator;

//Marc Sulsenti
//I pledge my honor that I have abided by the Stevens Honor System.
public class TaskList<E> {
    private ListQueue<E> all;
    private ListQueue<E> completed;
    private ListQueue<E> active;
    private final int LOW_PRIORITY = Integer.MAX_VALUE;
    private final int HIGH_PRIORITY = 1;



    public TaskList() {
        all = new ListQueue();
        completed = new ListQueue();
        active = new ListQueue();
    }

    public boolean createTask(E item) {
        //create a task with the given data, sets priority to low
        //places task in both the active and the all
        if(item==null){
            return false;
        }
        else{
            all.offer(item,LOW_PRIORITY);
            active.offer(item,LOW_PRIORITY);
            return true;
        }
    }


    public boolean createTask(E item, int priority){
        //creates a task with the given data and given priority
        //places task in both the active and the all.
        if(item==null){
            return false;
        }
        else{
            all.offer(item,priority);
            active.offer(item,priority);
            return true;
        }
    }

    public ListQueue<E> getAll(){
        return all;
    }
    public ListQueue<E> getCompleted(){
        return completed;
    }
    public ListQueue<E> getActive(){
        return active;
    }

    public void printTopThreeTasks(){
        //Print the top three highest priority Tasks
        System.out.println("Top 3 highest priority tasks:");
        System.out.println("-------------------");
        System.out.println("Printing Top three Tasks...");

        if(active.getSize()<=0){
            //If size of active is zero, then there are no active tasks
            System.out.println("There are no tasks.");
        }
        if(active.getSize()==1) {
            //If the size of active is one, then there is only one active task
            System.out.println("1.  "+active.front.getData());
        }
        if(active.getSize()==2){
            //If the size of active is two, then there are only two active tasks.
            System.out.println("1.  "+active.front.getData());
            System.out.println("2.  "+active.front.getNext().getData());
        }
        if(active.getSize()>2){
            //If the size of active is three, then print out the top three tasks.
            System.out.println("1.  "+active.front.getData());
            System.out.println("2.  "+active.front.getNext().getData());
            System.out.println("3.  "+active.front.getNext().getNext().getData());
        }

    }

    public void printTasks(ListQueue<E> queue) {
        //Prints all tasks in the provided queue
        Iterator<E> iterator = queue.iterator();
        int taskNumber = 1;
        while (taskNumber <= queue.getSize() ) {
            System.out.println(taskNumber + ".  " + iterator.next().toString());
            taskNumber++;
        }
    }






    public boolean crossOffMostUrgent(){
        //Removes the most urgent task from the active queue, and places it in the completed queue.
        if(active.size==0){
           //error here
            System.out.println("There are no tasks");
           return false;
       }
       else{
            ListQueue<E>.Node<E> current = active.front;
            System.out.println("Task is completed and removed from the list: " + current.getData());
            System.out.println("Successfully removed the most urgent task/top of the list task!");
            completed.offer(current.getData(), Integer.MAX_VALUE);
            active.remove(current);
            return true;
        }

    }


    public boolean crossOffTask(int TaskNumber){
        //Removes the specified task from the active queue and places it in the completed queue.
        if(TaskNumber < 1 || TaskNumber > active.size){
            throw new IndexOutOfBoundsException("Invalid Task Number");
        }
        ListQueue<E>.Node<E> current = active.front;
        if(TaskNumber == 1){
            completed.offer(current.getData(), Integer.MAX_VALUE);
            active.remove(current);
            return true;
        }
        for (int i = 1; i != TaskNumber ; i++) {
            current=current.getNext();
        }
        System.out.println("Task number "+TaskNumber+" is removed: "+current.getData());
        System.out.println("Successfully removed the task number: "+TaskNumber);
        completed.offer(current.getData(), Integer.MAX_VALUE);
        active.remove(current);
        return true;

        }

        public void showActiveTasks(){
        //prints all tasks in the active queue
        printTasks(active);
        }

        public void showCompletedTasks(){
        //prints all tasks in the completed queue
        printTasks(completed);
        }

        public void showAllTasks(){
        //prints all tasks in the all queue
        printTasks(all);
        }




}
