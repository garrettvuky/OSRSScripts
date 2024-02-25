package tutorial;

import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.PollingScript;
import tutorial.tasks.*;

import java.util.ArrayList;
import java.util.List;
@Script.Manifest(name="Telegrab", description="Fishes for lobsters and deposits lobsters.", properties="client=4; author=Garrett; topic=999;")
public class Telegrab extends PollingScript<ClientContext> {
    List<Task> taskList = new ArrayList<Task>();
    @Override
    public void start() {
        taskList.add(new BankTele(ctx));
        taskList.add(new WalkTele(ctx));
        taskList.add(new Grab(ctx));
    }
    @Override
    public void poll() {
        for(Task task: taskList){
            if(task.activate()){
                task.execute();
                break;
            }
        }
    }
}