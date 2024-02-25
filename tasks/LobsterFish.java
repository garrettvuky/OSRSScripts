package tutorial.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;
import tutorial.Task;

public class LobsterFish extends Task {
    public static final Tile[] path = {new Tile(2956, 3143, 1), new Tile(2956, 3146, 0), new Tile(2951, 3146, 0), new Tile(2946, 3146, 0), new Tile(2941, 3146, 0), new Tile(2936, 3148, 0), new Tile(2931, 3148, 0), new Tile(2926, 3148, 0), new Tile(2921, 3150, 0), new Tile(2916, 3153, 0), new Tile(2918, 3158, 0), new Tile(2920, 3163, 0), new Tile(2920, 3168, 0), new Tile(2923, 3173, 0), new Tile(2924, 3178, 0)};
    public LobsterFish(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        Npc FishingSpot = ctx.npcs.select().id(1522).nearest().poll();
        return FishingSpot.inViewport() && ctx.players.local().animation() == -1 && ctx.inventory.select().count() < 28;
    }

    @Override
    public void execute() {
        Npc FishingSpot = ctx.npcs.select().id(1522).nearest().poll();
        if(!FishingSpot.inViewport())
        {
            ctx.camera.turnTo(FishingSpot);
        }
        System.out.println("Fishing for Lobster");
        FishingSpot.interact("Cage");
        Condition.sleep(Random.nextInt(5000,7500));
    }
}
