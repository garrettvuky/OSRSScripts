package tutorial.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;
import tutorial.Task;

import java.util.concurrent.Callable;

public class Grab extends Task {

    public Grab(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        GroundItem Wine = ctx.groundItems.select().id(245).poll();
        return ctx.inventory.select().count() < 28 && Wine.valid() && ctx.inventory.select().id(563).count() > 0 && Wine.tile().distanceTo(ctx.players.local().tile()) < 12;
    }

    @Override
    public void execute() {
        System.out.println("Grabbing");
        final int[] Lup =    {-12, 40, -140, 0, 28, 32};
        final int[] Ldown =    {-24, 32, -68, 0, -12, 32};
        final int[] WineBounds = {12, -16, -108, -80, 20, -12};
        final int[] Winebounds2 = {-36, -4, -108, -76, -12, 16};
        GroundItem Wine = ctx.groundItems.select().id(245).poll();
        Component TeleSpell = ((ClientContext)this.ctx).widgets.widget(218).component(24);
        Component InvTab = ((ClientContext)this.ctx).widgets.widget(548).component(54);
        GameObject LadderUp = ctx.objects.select().id(31580).poll();
        GameObject LadderDown = ctx.objects.select().id(31579).poll();
        LadderUp.bounds(Lup);
        LadderDown.bounds(Ldown);
        if(LadderDown.valid()) {            //Sets Wine bounds
            Wine.bounds(WineBounds);
        }
        else if(LadderUp.valid())
        {
            Wine.bounds(Winebounds2);
        }
        if(LadderUp.valid() && !Wine.valid())
        {
            if(ctx.inventory.select().count() < 28)
            {
                LadderUp.interact("Climb", "Ladder");
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return Wine.valid();
                    }
                });
            }
        }
        if(ctx.groundItems.select().id(245).poll().tile().distanceTo(ctx.players.local()) < 3 && Wine.inViewport())
        {
            System.out.println("Attempting Grab Upstairs");
             if(!ctx.game.tab(Game.Tab.MAGIC))
             {
                 System.out.println("Switching to Magic Tab");
                 Component MagicTab = ctx.widgets.widget(548).component(57);
                 MagicTab.interact("Magic");
                 Condition.sleep(Random.nextInt(400,700));
             }
             else {
                 if(!ctx.magic.casting(Magic.Spell.TELEKINETIC_GRAB))
                 {
                     TeleSpell.interact("Cast", "Telekinetic Grab");
                 }
                     System.out.println("Casting Telekinetic Grab");
                     Wine.interact("Cast", "Wine of zamorak");
                     Condition.wait(new Callable<Boolean>() {
                     @Override
                     public Boolean call() throws Exception {
                         int InvCount = ctx.inventory.select().count();
                         return ctx.inventory.select().count() > InvCount;
                     }
                 },200,20);
             }
            if(ctx.game.tab(Game.Tab.MAGIC) && !hasHealth())
            {
                if(!ctx.magic.casting(Magic.Spell.TELEKINETIC_GRAB))
                {
                    TeleSpell.interact("Cast", "Telekinetic Grab");
                    int rand = Random.nextInt(97, 106);
                    if(ctx.input.getLocation().distance(Wine.tile().matrix(ctx).point(rand)) > 10) {
                        ctx.input.move(Wine.tile().matrix(ctx).point(rand));
                    }
                }
                Wine.interact("Cast", "Wine of zamorak");
                System.out.println("Casting Telekinetic Grab");
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        int InvCount = ctx.inventory.select().count();
                        return ctx.inventory.select().count() > InvCount;
                    }
                },200,20);
                int rand2 = Random.nextInt(1,8);
                if(rand2 == 3)
                {
                    InvTab.click();
                }
            }
             if(ctx.inventory.select().count() < 27 && !hasHealth())
             {
                 if(!ctx.magic.casting(Magic.Spell.TELEKINETIC_GRAB))
                 {
                     TeleSpell.interact("Cast", "Telekinetic Grab");
                 }
             }
        }
        if(LadderDown.valid() && !Wine.valid() && hasHealth())
        {
            if(ctx.magic.casting(Magic.Spell.TELEKINETIC_GRAB))
            {
                TeleSpell.click();
            }
            LadderDown.interact("Climb","Ladder");
        }
        if(ctx.groundItems.select().id(245).poll().tile().distanceTo(ctx.players.local()) > 3 && Wine.inViewport() && hasHealth())
        {
            if(!ctx.game.tab(Game.Tab.MAGIC))
            {
                Component MagicTab = ctx.widgets.widget(548).component(57);
                MagicTab.interact("Magic");
                Condition.sleep(Random.nextInt(400,700));
            }
                else {
                if(!ctx.magic.casting(Magic.Spell.TELEKINETIC_GRAB))
                {
                    TeleSpell.interact("Cast", "Telekinetic Grab");
                }
                    System.out.println("Casting Telekinetic Grab");
                    Wine.interact("Cast", "Wine of zamorak");
                    Condition.wait(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            int InvCount = ctx.inventory.select().count();
                            return ctx.inventory.select().count() > InvCount;
                        }
                    },200,15);
            }

        }
        if(LadderUp.valid() && !Wine.valid())
        {
            if(ctx.inventory.select().count() < 28)
            {
                LadderUp.interact("Climb", "Ladder");
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return Wine.valid();
                    }
                });
            }
        }
    }
    public boolean hasHealth(){
        return ctx.combat.health() > Random.nextInt(20,22);
    }
}
