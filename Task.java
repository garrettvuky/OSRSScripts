package tutorial;

import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
@Script.Manifest(name="Fishing and Cooking", description="Tutorial", properties="client=4; author=Garrett; topic=999;")
public abstract class Task extends ClientAccessor {

    public Task(ClientContext ctx) {
        super(ctx);
    }

    public abstract boolean activate();
    public abstract void execute();
}
