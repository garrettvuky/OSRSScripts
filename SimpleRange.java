package tutorial;


import org.powerbot.script.*;
import org.powerbot.script.rt4.*;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Script.Manifest(name="tutorial.Task", description="Tutorial", properties="client=4; author=Garrett; topic=999;")

public class SimpleRange extends PollingScript<ClientContext>
{
    public Npc cow1 = ctx.npcs.select().id(COW_ID).poll();
    public Npc cow2 = ctx.npcs.select().id(COW2_ID).poll();
    public Npc cow3 = ctx.npcs.select().id(COW3_ID).poll();
    public Npc cow4 = ctx.npcs.select().id(COW4_ID).poll();
    public final Player local = ctx.players.local();
    public static final int[] COW_ID = {5087, 5086, 5089};
    //public static final int[] COW_ID = {2791, 2731, 2790, 2793};
    public static final int COW1_ID =2791;
    public static final int COW2_ID =2731;
    public static final int COW3_ID =2790;
    public static final int COW4_ID =2793;
    Random rand = new Random();
    public void start() {

    }

    @Override
    public void poll() {
        System.out.println("poll");
        if(needsHeal())
        {
            System.out.println("needs heal");
            heal();
        }
        else if(shouldAttack())
        {
            Condition.sleep(1200);
            Attack();
        }
    }
    public boolean shouldAttack() {

        boolean shouldAttackBoolean = false;
        Actor i = ctx.players.local().interacting();

        return ctx.players.local().animation() != 426 &&
                ctx.players.local().animation() != 424 &&
                !local.inMotion() &&
                !local.interacting().valid() &&
                !i.valid() && ctx.combat.health() > 37;
    }
    public boolean needsHeal()
    {
        return ctx.combat.health() < 14;
    }
    public void heal(){
        Item food = (Item)((ItemQuery)((ClientContext)this.ctx).inventory.select()).id(361).poll();
        if(ctx.combat.health() < 37)
                            food.interact("Eat");
                            Condition.sleep(Random.nextInt(1800, 2400));
        }


    public void Attack() {
        Locatable mage = ctx.npcs.select().id(COW_ID).nearest().poll();
        if(Random.nextInt(0,5) == 1) {
            ctx.camera.turnTo(mage);
        }
        Condition.sleep(2500);
        System.out.println("Attack");
        ctx.npcs.select().id(COW_ID).nearest().poll().click();
        Condition.sleep(1200);
    }
}