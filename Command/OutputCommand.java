package Command;

import Controller.Controller;

public class OutputCommand implements Command{
    Controller van;

    public OutputCommand(Controller van){
        this.van = van;
    }

    public void execute(){
        van.outputStorage();
    }
}
