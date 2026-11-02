import java.util.Scanner;

public class TodoApp {

    static Scanner scanner = new Scanner(System.in);
    static TaskManager tasks = new TaskManager();

    static void showMenu() {
        System.out.println("1 if you want to add a task");
        System.out.println("2 if you want to see your tasks");
        System.out.println("3 if you want to check a task");
        System.out.println("4 if you want to delete a task");
        System.out.println("5 if you want to switch cmd mode");
        System.out.println("6 if you want to exit the program");
    }
    static void cmdMenu(){
        System.out.println("'add' to add a task");
        System.out.println("'list' to see your tasks");
        System.out.println("'check' to check a task");
        System.out.println("'delete' to delete a task");
        System.out.println("'menu' to switch cmd mode");
        System.out.println("'exit' to exit the program");
    }

    public static void main(String[] args) {
        String filepath = "save.text";
        boolean isMenuMode = true;

        tasks.loadFromFile(filepath);

        while (true) {
            if(isMenuMode){
                showMenu();
                System.out.print("choose an option (1-5) : ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        tasks.addTask();
                        break;
                    case "2":
                        tasks.viewTasks();
                        break;
                    case "3":
                        tasks.markDone();
                        break;
                    case "4":
                        tasks.deleteTask();
                        break;
                    case "5":
                        isMenuMode = false;
                        break;
                    case "6":
                        System.out.println("goodbye!");
                        tasks.saveInFile(filepath);
                        return;
                    default:
                        System.out.println("invalid choice. try again.");
                }
            }
            else{
                cmdMenu();
                String choice = scanner.nextLine();

                switch (choice) {
                    case "add":
                        tasks.addTask();
                        break;
                    case "list":
                        tasks.viewTasks();
                        break;
                    case "check":
                        tasks.markDone();
                        break;
                    case "delete":
                        tasks.deleteTask();
                        break;
                    case "menu":
                        isMenuMode = true;
                        break;
                    case "exit":
                        System.out.println("goodbye!");
                        return;
                    default:
                        System.out.println("invalid choice. try again.");
                }

            }
        }
    }
}

