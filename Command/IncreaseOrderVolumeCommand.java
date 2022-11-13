package Command;

import Controller.Controller;

public class IncreaseOrderVolumeCommand implements Command{
    Controller van;

    public IncreaseOrderVolumeCommand(Controller van){
        this.van = van;
    }

    public void execute(){
        van.upVolumeOfStorage();
    }
}
