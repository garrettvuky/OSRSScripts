package tutorial.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.Npc;
import tutorial.Task;
import tutorial.Walker;

public class Bank extends Task {
    public static final Tile[] path = {new Tile(3243, 3158, 0), new Tile(3243, 3163, 0), new Tile(3243, 3168, 0), new Tile(3243, 3173, 0), new Tile(3243, 3178, 0), new Tile(3243, 3183, 0), new Tile(3243, 3188, 0), new Tile(3244, 3193, 0), new Tile(3241, 3197, 0), new Tile(3238, 3201, 0), new Tile(3236, 3206, 0), new Tile(3236, 3211, 0), new Tile(3235, 3216, 0), new Tile(3230, 3218, 0), new Tile(3225, 3218, 0), new Tile(3220, 3218, 0), new Tile(3215, 3218, 0), new Tile(3215, 3213, 0), new Tile(3210, 3211, 0), new Tile(3205, 3209, 1), new Tile(3205, 3209, 2), new Tile(3205, 3214, 2), new Tile(3208, 3219, 2)};
    public static final Tile[] path2 = {new Tile( 3208, 3219, 2), new Tile(3206, 3214, 2), new Tile(3206, 3208, 1), new Tile(3206, 3208, 0), new Tile(3211, 3209, 0), new Tile(3215, 3212, 0), new Tile(3215, 3217, 0), new Tile(3220, 3218, 0), new Tile(3225, 3218, 0), new Tile(3230, 3218, 0), new Tile(3235, 3218, 0), new Tile(3236, 3213, 0), new Tile(3236, 3208, 0), new Tile(3236, 3203, 0), new Tile(3241, 3201, 0), new Tile(3244, 3197, 0), new Tile(3244, 3192, 0), new Tile(3244, 3187, 0), new Tile(3243, 3182, 0), new Tile(3243, 3177, 0), new Tile(3241, 3172, 0), new Tile(3241, 3167, 0), new Tile(3241, 3162, 0), new Tile(3241, 3157, 0)};
    Walker walker = new Walker(ctx);
    public Bank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count()>27 || (ctx.inventory.select().count()<28 && path[0].distanceTo(ctx.players.local()) > 12);
    }

    @Override
    public void execute() {
        Npc banker = ctx.npcs.select().id(2897).poll();
        Npc FishingSpot = ctx.npcs.select().id(1530).nearest().poll();
        if(ctx.movement.energyLevel() > 65)
        {
            ctx.movement.running(true);
        }

        if(!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || path[0].distanceTo(ctx.players.local()) > 6) {
            if(ctx.inventory.select().count() > 27 && ctx.movement.destination().equals(Tile.NIL)) {
                walker.walkPath(path);
                System.out.println("Walking path to bank");
            }
            else if(!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL)){
                walker.walkPathReverse(path);
                System.out.println("Walking path from bank");
            }
        }
        if(ctx.inventory.select().id(327,345).count() > 0) {
            if(banker.inViewport())
            {

            }
            else{
                ctx.camera.turnTo(banker);
            }
            Bank();
        }
    }
    public void Bank() {
        Npc banker = ctx.npcs.select().id(2897).poll();
        Item RawSardine = ctx.inventory.select().id(327).poll();
        Item RawHerring = ctx.inventory.select().id(345).poll();
        if (!ctx.bank.open()) {
            banker.interact("Bank", "Banker");
            Condition.sleep(Random.nextInt(200, 500));
        }
        if (ctx.bank.open()) {
            Condition.sleep(Random.nextInt(200, 500));
            RawHerring.interact("Deposit-All", "Raw herring");
            Condition.sleep(Random.nextInt(200, 500));
            RawSardine.interact("Deposit-All", "Raw sardine");
            System.out.println("Banking");
            Condition.sleep(Random.nextInt(1000, 2000));
        }

    }
}
