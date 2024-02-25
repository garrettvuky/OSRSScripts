package tutorial.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;
import tutorial.Task;
import tutorial.Walker;

public class WalkLobster extends Task {
    public static final Tile[] path = {new Tile(2956, 3146, 0), new Tile(2951, 3146, 0), new Tile(2946, 3146, 0), new Tile(2941, 3146, 0), new Tile(2936, 3147, 0), new Tile(2931, 3148, 0), new Tile(2926, 3148, 0), new Tile(2922, 3151, 0), new Tile(2917, 3152, 0), new Tile(2917, 3157, 0), new Tile(2920, 3161, 0), new Tile(2920, 3166, 0), new Tile(2920, 3171, 0), new Tile(2924, 3174, 0), new Tile(2924, 3179, 0)};
    public static final Tile[] pathToBank = {new Tile(3029, 3217, 0), new Tile(3027, 3222, 0), new Tile(3027, 3227, 0), new Tile(3027, 3232, 0), new Tile(3030, 3236, 0), new Tile(3035, 3236, 0), new Tile(3040, 3236, 0), new Tile(3045, 3235, 0)};
    Walker walker = new Walker(ctx);
    public WalkLobster(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ((ctx.inventory.select().count() > 27 || (ctx.inventory.select().count() < 27)) && ctx.movement.destination().equals(Tile.NIL));
    }

    @Override
    public void execute() {
        if(ctx.movement.energyLevel() > 65)
        {
            ctx.movement.running(true);
        }
        if(!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL)) {
            if ((ctx.inventory.select().count() > 27)) {
                walker.walkPathReverse(path);
            }
            if ((ctx.inventory.select().count() < 28) && path[14].distanceTo(ctx.players.local()) > 5 && ctx.inventory.select().id(377).count() == 0) {
                walker.walkPath(path);
            }
            if (ctx.inventory.select().count() > 27 && ((pathToBank[0].distanceTo(ctx.players.local()) < 3) || (pathToBank[1].distanceTo(ctx.players.local()) < 3) || (pathToBank[2].distanceTo(ctx.players.local()) < 3) || (pathToBank[3].distanceTo(ctx.players.local()) < 3) || (pathToBank[4].distanceTo(ctx.players.local()) < 3) || (pathToBank[5].distanceTo(ctx.players.local()) < 3) || (pathToBank[6].distanceTo(ctx.players.local()) < 3) || (pathToBank[7].distanceTo(ctx.players.local()) < 3))) {
                walker.walkPath(pathToBank);
            }
            if (ctx.inventory.select().count() < 27 && ((pathToBank[0].distanceTo(ctx.players.local()) < 3) || (pathToBank[1].distanceTo(ctx.players.local()) < 3) || (pathToBank[2].distanceTo(ctx.players.local()) < 3) || (pathToBank[3].distanceTo(ctx.players.local()) < 3) || (pathToBank[4].distanceTo(ctx.players.local()) < 3) || (pathToBank[5].distanceTo(ctx.players.local()) < 3) || (pathToBank[6].distanceTo(ctx.players.local()) < 3) || (pathToBank[7].distanceTo(ctx.players.local()) < 3))) {
                walker.walkPathReverse(pathToBank);
            }
        }
    }
}
