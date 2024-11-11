
import java.util.InputMismatchException;
import java.util.Scanner;

//Marc Sulsenti
//I pledge my honor that I have abided by the Stevens Honor System.

public class TestTaskList <E> {
    private TaskList  toDoList;
    private Scanner scan;

    public TestTaskList() {
        //Creates toDoList and Scanner
        toDoList = new TaskList<>();
        scan = new Scanner(System.in);
    }
    public static void main(String[] args){
        //Main method to run printMenu()
        System.out.println("~~~ TO-DO List Program, created by truly yours ~~~");
        TestTaskList  main = new TestTaskList<>();
        main.printMenu();

    }

     public void printMenu(){
        //printMenu generates the main menu for the Homework assignment. Taking in user input
         // repeatedly presenting the user with 8 options.
        boolean start = true;
        while(true) {
            if(start) {
                System.out.println("==> Currently there are NO items in the To-Do List");
            }
            if(start == false){
                System.out.println("Current TO-DO List:");
                System.out.println("-------------------");
                toDoList.showActiveTasks();
            }
            System.out.println("To add a new task without priority information, press 1.");
            System.out.println("To add a new task with a priority information, press 2.");
            System.out.println("To cross off the task at the top of the list, press 3.");
            System.out.println("To cross off a certain task in the list, press 4.");
            System.out.println("To see the top 3 highest priority tasks, press 5.");
            System.out.println("To see the completed tasks, press 6.");
            System.out.println("To see the all tasks that has been completed or still active, press 7.");
            System.out.println("To quit the program, press 8.");
             String choice = scan.nextLine();
            if (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4") || choice.equals("5") || choice.equals("6") || choice.equals("7") || choice.equals("8")) {
                Integer menuitem = Integer.valueOf(choice);
                processMenuItem(menuitem);
                start = false;

            }
            else{
                System.out.println("ERROR! Please enter a number between 1 and 8 (included).");
            }
        }

    }


    private  boolean processMenuItem(int menuItem){
        //processMenuItem takes into the user input from printMenu and calls the proper method from TaskList
        if(menuItem == 1){
            //Allows for the creation of a task where the user chooses only the task. Priority it auto set to LOW
            System.out.println("Please enter the task description:");
            String task = scan.nextLine();
            toDoList.createTask(task);
            System.out.println("Successfully entered the task to the to-do list!");
        }
        if(menuItem == 2){
            //Allows for the creation of a task where the user chooses the task and the priority.
            System.out.println("Please enter the task description:");
            String task = scan.nextLine();
            try {
                System.out.println("Please enter a priority number (1 indicates highest priority, increasing\n" +
                        "numbers show lower priority) :");
                Integer priority = scan.nextInt();
                toDoList.createTask(task, priority);
                System.out.println("Successfully entered the task to the to-do list!");
            }
            catch(InputMismatchException e){
                System.out.println("ERROR! Enter an integer");
            }
        }
        if(menuItem == 3){
            //Crosses off the most urgent task (priority 1)
            toDoList.crossOffMostUrgent();
        }
        if (menuItem == 4) {
            //Crosses off the specific active task.
            System.out.println("Please enter the task number you would like to cross off the list :");
            try {
                Integer task_num = scan.nextInt();

                if (task_num > toDoList.getActive().size || task_num < 1) {
                    System.out.println(toDoList.getActive().size);
                    System.out.println("Unsuccessful operation! Please try again!");
                } else {
                    toDoList.crossOffTask(task_num);
                }
            }
            catch(InputMismatchException e){
                System.out.println("ERROR! Enter an integer");
            }
        }
        if(menuItem == 5){
            //Prints top three tasks
            toDoList.printTopThreeTasks();
        }
        if(menuItem == 6)
            //Show completed Tasks
            toDoList.showCompletedTasks();
        if(menuItem == 7){
            //Show all Tasks
           toDoList.showAllTasks();
        }
        if(menuItem == 8){
            //quit the program
            System.exit(0);
        }
        return true;
    }





}
