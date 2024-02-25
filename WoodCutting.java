package tutorial;


import org.powerbot.script.*;
import org.powerbot.script.rt4.*;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.Widget;
@Script.Manifest(name="Woodcutting", description="Tutorial", properties="client=4; author=Garrett; topic=999;")
public class WoodCutting extends PollingScript<ClientContext> {
    @Override
    public void poll() {
        if(shouldCut())
        {
            Cut();
        }
        else if(ctx.inventory.select().count() > 27)
        {
            System.out.println("Dropping Wood");
            Drop();
        }
    }
    public boolean shouldCut()
    {
        return ctx.players.local().animation() == -1 && ctx.inventory.select().count() <= 27 && !ctx.players.local().inMotion();
    }
    public void Cut()
    {
        GameObject Tree = ctx.objects.select().id(10829,10833,10831).nearest().poll();
        if(Tree.valid())
        {
            System.out.println("Cutting Wood");
            Tree.interact("Chop down");
            Condition.sleep(3000);
        }
    }
    public void Drop() {
        for(int i = ctx.inventory.select().id(1519).count(); i > 0; i--)
        {
            Item itemToDrop = ctx.inventory.select().id(1519).poll();
            itemToDrop.interact("Drop","Willow logs");
            Condition.sleep(Random.nextInt(500,700));
        }
    }
}
