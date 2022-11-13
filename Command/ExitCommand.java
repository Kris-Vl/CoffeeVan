package Command;

public class ExitCommand implements Command{
    public void execute(){
        System.out.println("Завершення програми!");
        System.exit(0);
    }
}
