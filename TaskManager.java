import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public void addTask() {
        System.out.print("enter task : ");
        String title = scanner.nextLine();
        this.tasks.add(new Task(title));
        System.out.println("task '" + title + "' added!");
    }

    public void viewTasks() {
        if (this.tasks.isEmpty()) {
            System.out.println("no tasks yet!");
            return;
        }
        System.out.println("\nyour tasks :");
        for (int i = 0; i < this.tasks.size(); i++) {
            Task t = this.tasks.get(i);
            String status = t.getStatu() ? "done" : "not done";
            System.out.println((i + 1) + "." + t.title + "[" + status + "]");
        }
    }

    public void markDone() {
        this.viewTasks();
        if (this.tasks.isEmpty()) {
            return;
        }
        try {
            
            System.out.print("enter task number to mark done: \n");
            System.out.print("enter 0 to exit \n");
            do{
                int index = Integer.parseInt(scanner.nextLine()) - 1;
                if(index < 0) return ;
                if (index >= 0 && index < this.tasks.size()) {
                    this.tasks.get(index).setStatu(true) ;
                    System.out.println("marked as done!");
                    return ;
                } else {
                    System.out.println("invalid number!");
                }
            }while(true);
        } catch (NumberFormatException e) {
            System.out.println("please enter a valid number.\n");
        }
    }

    public void deleteTask() {
        viewTasks();
        if (this.tasks.isEmpty()) {
            return;
        }
        try {
            System.out.print("enter task number to delete : \n");
            System.out.print("enter 0 to exit \n");
            do{
                int index = Integer.parseInt(scanner.nextLine()) - 1;
                if(index < 0) return ;

                if (index >= 0 && index < this.tasks.size()) {
                    Task removed = this.tasks.remove(index);
                    System.out.println("deleted task: " + removed.title);
                    return ;
                } else {
                    System.out.println("invalid number!\n");
                }
            }while(true);
        } catch (NumberFormatException e) {
            System.out.println("please enter a valid number\n");
        }
    }

    public void saveInFile(String filepath ) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))){
            for(Task task : this.tasks){
                writer.write(task.toFileString());
                writer.newLine();
            }
            System.out.println("save complet successfull");
        }catch (IOException e) {
            System.out.println("save error" + e.getMessage());
        }
    }

    public void loadFromFile(String filepath){
        File file = new File(filepath);
        if(!file.exists()){
            return;
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line;
            while((line = reader.readLine())!=null){
                String[] parts = line.split("\\|");
                if(parts.length == 2){
                    String titel = parts[0];
                    String statu = parts[1];

                    boolean done = statu.trim().equals("done");

                    this.tasks.add(new Task(titel,done));
                }
            }
            System.out.println("loading successfull");
        }catch(IOException e){
            System.out.println("loading not successfull :" + e.getMessage());
        }
    }
}
