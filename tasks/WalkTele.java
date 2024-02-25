package tutorial.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;
import tutorial.Task;
import tutorial.Walker;

import java.util.concurrent.Callable;


public class WalkTele extends Task {
    Npc banker = ctx.npcs.select().id(1618).poll();
    public static final Tile[] path = {new Tile(2939, 3517, 0), new Tile(2942, 3512, 0), new Tile(2941, 3507, 0), new Tile(2941, 3502, 0), new Tile(2942, 3497, 0), new Tile(2942, 3492, 0), new Tile(2942, 3487, 0), new Tile(2944, 3482, 0), new Tile(2944, 3477, 0), new Tile(2944, 3472, 0), new Tile(2944, 3467, 0), new Tile(2944, 3462, 0), new Tile(2944, 3457, 0), new Tile(2944, 3452, 0), new Tile(2946, 3447, 0), new Tile(2948, 3442, 0), new Tile(2948, 3437, 0), new Tile(2949, 3432, 0), new Tile(2949, 3427, 0), new Tile(2953, 3423, 0), new Tile(2957, 3420, 0), new Tile(2960, 3416, 0), new Tile(2963, 3412, 0), new Tile(2965, 3407, 0), new Tile(2965, 3402, 0), new Tile(2965, 3397, 0), new Tile(2965, 3392, 0), new Tile(2964, 3387, 0), new Tile(2960, 3384, 0), new Tile(2956, 3381, 0), new Tile(2952, 3377, 0), new Tile(2947, 3375, 0), new Tile(2946, 3370, 0)};
    Walker walker = new Walker(ctx);
    public WalkTele(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {

                                return (ctx.inventory.select().count() > 27 && path[32].distanceTo(ctx.players.local().tile()) > 5) || (ctx.inventory.select().id(245).count() == 0 && path[0].distanceTo(ctx.players.local().tile()) > 5);
    }

    @Override
    public void execute() {
        System.out.println("Walking");
        if(ctx.movement.energyLevel() > 65)
        {
            ctx.movement.running(true);
        }
        GameObject LadderDown = ctx.objects.select().id(31579).poll();
        GameObject Ditch = ctx.objects.select().id(23271).nearest().poll();
        final int[] bounds2 = {-24, 32, -68, 0, -12, 32};
        final Tile TeleTile = new Tile(2939,3517,1);
        final Tile TeleTile2 = new Tile(2939,3517,0);
        final int[] bounds = {-204, 336, -36, 0, -4, 132};
        LadderDown.bounds(bounds2);
        Ditch.bounds(bounds);
        if(ctx.magic.casting(Magic.Spell.TELEKINETIC_GRAB))
        {
            Component TeleSpell = ((ClientContext)this.ctx).widgets.widget(218).component(24);
            TeleSpell.click();
        }
        if(ctx.players.local().tile().equals(TeleTile) && ctx.inventory.select().count() > 27)
        {
            LadderDown.interact("Climb", "Ladder");
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.players.local().tile().equals(TeleTile2);
                }
            },200,20);
        }
        if(!ctx.players.local().inMotion() && ctx.inventory.select().count() > 27 || ctx.movement.destination().equals(Tile.NIL) && ctx.inventory.select().count() > 27) {

            if (ctx.inventory.select().id(22586).count() > 0)
            {
                    if(Ditch.valid() && !Ditch.inViewport())
                    {
                        ctx.camera.turnTo(Ditch);
                    }
                    if(Ditch.valid()) {
                        if(!ctx.game.tab(Game.Tab.INVENTORY))
                        {
                            Component Inv = ((ClientContext)this.ctx).widgets.widget(218).component(54);
                            Inv.click();
                        }
                        Ditch.interact("Cross", "Wilderness Ditch");
                        Condition.wait(new Callable<Boolean>() {
                            @Override
                            public Boolean call() throws Exception {
                                return ctx.players.local().tile().y() > 3522;
                            }
                        },250,20);
                        Condition.sleep(Random.nextInt(400,600));
                        if(ctx.players.local().tile().y() > 3522) {
                            Tile stepTo = new Tile(ctx.players.local().tile().x(), ctx.players.local().tile().y() + Random.nextInt(3, 5), 0);
                            Tile stepTo2 = new Tile(Random.nextInt(2937,2939), 3517, 0);
                            ctx.movement.step(stepTo);
                            Condition.sleep(Random.nextInt(2000, 3000));
                            ctx.inventory.select().id(245).poll().interact("Use", "Wine of zamorak");
                            ctx.inventory.select().id(22586).poll().click();
                            Condition.sleep(Random.nextInt(400, 700));
                            ctx.input.send("3");
                            Condition.sleep(Random.nextInt(400, 700));
                            Ditch.interact("Cross", "Wilderness Ditch");
                            Condition.sleep(Random.nextInt(4500, 5000));
                            ctx.inventory.select().id(22586).poll().click();
                            Condition.sleep(Random.nextInt(400, 700));
                            ctx.movement.step(stepTo2);
                            Condition.sleep(Random.nextInt(2500,3500));
                        }

                    }


            }

            if(ctx.inventory.select().id(11941).count() > 0 && !ctx.players.local().inMotion() || (ctx.inventory.select().id(11941).count() > 0 &&ctx.movement.destination().equals(Tile.NIL)))
            {
                walker.walkPath(path);
            }
            Tile stepTo2 = new Tile(Random.nextInt(2937,2939), 3517, 0);
            if(path[0].distanceTo(ctx.players.local().tile()) < 15 && ctx.movement.destination().equals(Tile.NIL) && !ctx.players.local().inMotion())
            {
                ctx.movement.step(stepTo2);
            }
        }
        if(ctx.inventory.select().id(245).count() == 0 && path[0].distanceTo(ctx.players.local()) > 5 && (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL)))
        {
            System.out.println("Walking to Wines");
            ctx.inventory.select().id(11941).poll().click();
            walker.walkPathReverse(path);
        }
    }
}
