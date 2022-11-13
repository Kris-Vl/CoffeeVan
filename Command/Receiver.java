package Command;

import Controller.Controller;

import java.util.ArrayList;
import java.util.List;

public class Receiver {
    private final List<Command> commandList = new ArrayList<>();
    private final Controller van;
    public Receiver(Controller van){
        this.van = van;
        addCommands();
    }
    public void run(int index){
        commandList.get(index).execute();
    }

    public void addCommands(){
        commandList.add(new OrderFormationCommand(van));
        commandList.add(new OrderSortCommand(van));
        commandList.add(new ProductSearchCommand(van));
        commandList.add(new IncreaseOrderVolumeCommand(van));
        commandList.add(new OutputCommand(van));
        commandList.add(new ExitCommand());
    }
}
