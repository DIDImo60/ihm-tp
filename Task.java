public class Task {

    String title;
    boolean done = false;

    public Task (String title){
        this.title = title;
    }

    public Task (String title , boolean done ){
        this.title = title;
        this.done = done;
    }

    //gets
    public String getTitel(){
        return this.title;
    }

    public boolean getStatu(){
        return this.done;
    }

    //sets
    public void setTitel(String title) {
        this.title = title;
    }

    public void setStatu(boolean statu){
        this.done = statu;
    }
    // تحويل الى نص
    public String toFileString(){
        String statu = this.done? "done" : "not done";
        return this.title+"|"+statu  ;
    }
}