package Command;

import Controller.Controller;

public class ProductSearchCommand implements Command{
    Controller van;

    public ProductSearchCommand(Controller van){
        this.van = van;
    }

    public void execute(){
        van.Find();
    }
}
