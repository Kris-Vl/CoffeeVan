package Command;

import Controller.Controller;

public class OrderSortCommand implements Command{
    Controller van;

    public OrderSortCommand(Controller van){
        this.van = van;
    }

    public void execute(){
        van.sort();
    }
}
