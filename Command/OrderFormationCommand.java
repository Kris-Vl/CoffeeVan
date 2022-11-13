package Command;

import Controller.Controller;

public class OrderFormationCommand implements Command{
    Controller van;

    public OrderFormationCommand(Controller van){
        this.van = van;
    }

    public void execute(){
        van.fillStorage();
    }
}
