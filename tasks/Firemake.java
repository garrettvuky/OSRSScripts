package tutorial.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;
import tutorial.Task;

import java.util.concurrent.Callable;

public class Firemake extends Task {
    Tile fireTile = new Tile(3244, 3308, 0);
    public Firemake(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.players.local().tile().equals(fireTile);
    }

    @Override
    public void execute() {
        for(int i = ctx.inventory.select().id(1519).count(); i > 0; i--)
        {
            i = ctx.inventory.select().id(1519).count();
            Item Tinderbox = ctx.inventory.select().id(590).poll();
            Item Logs = ctx.inventory.select().id(1519).poll();
            if(ctx.players.local().animation() == -1)
            {
                Logs.interact("Use", "Willow logs");
                Condition.sleep(Random.nextInt(200,450));
                Tinderbox.click();
                Condition.sleep(Random.nextInt(1000,2000));
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.players.local().animation() == -1;
                    }
                },100,100);
            }
            else{
                i = i + 1;
            }
        }
    }
}
