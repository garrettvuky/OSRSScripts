package tutorial;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import tutorial.tasks.*;

import java.util.ArrayList;
import java.util.List;
@Script.Manifest(name="Willow Maker", description="Tutorial", properties="client=4; author=Garrett; topic=999;")
public class WillowMaker extends PollingScript<ClientContext> {
    List<Task> taskList = new ArrayList<Task>();
    @Override
    public void start() {
        taskList.add(new Firemake(ctx));
        taskList.add(new Walk(ctx));
        taskList.add(new Cut(ctx));
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
