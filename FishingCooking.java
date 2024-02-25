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
@Script.Manifest(name="Fishing", description="Tutorial", properties="client=4; author=Garrett; topic=999;")
public class FishingCooking extends PollingScript<ClientContext> {
    @Override
    public void poll() {
        if(canFish() && ctx.inventory.select().count() <= 27){
            Fish();
        }
        else if(ctx.inventory.select().count() > 27)
        {
            Drop();
        }
    }
    public boolean canFish() {
        Npc FishingSpot = ctx.npcs.select().id(1530).nearest().poll();
        return FishingSpot.valid() && ctx.players.local().animation() == -1 && ctx.inventory.select().count() <= 27 && !ctx.players.local().inMotion();
    }
    public void Fish() {
        Npc FishingSpot = ctx.npcs.select().id(1530).nearest().poll();
        FishingSpot.interact("Bait");
        Condition.sleep(Random.nextInt(2500,3500));
    }
    public void Drop() {
        for(int i = ctx.inventory.select().id(327).count(); i > 0; i--)
        {
            Item itemToDrop = ctx.inventory.select().id(327).poll();
            itemToDrop.interact("Drop","Raw shrimps");
            Condition.sleep(Random.nextInt(500,700));
        }
    }
}
