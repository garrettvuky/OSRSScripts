package tutorial.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;
import tutorial.Task;
import tutorial.Walker;

public class Fish extends Task {
    public static final Tile[] path = {new Tile(3244, 3153, 0), new Tile(3243, 3158, 0), new Tile(3243, 3163, 0), new Tile(3243, 3168, 0), new Tile(3243, 3173, 0), new Tile(3243, 3178, 0), new Tile(3243, 3183, 0), new Tile(3243, 3188, 0), new Tile(3244, 3193, 0), new Tile(3241, 3197, 0), new Tile(3238, 3201, 0), new Tile(3236, 3206, 0), new Tile(3236, 3211, 0), new Tile(3235, 3216, 0), new Tile(3230, 3218, 0), new Tile(3225, 3218, 0), new Tile(3220, 3218, 0), new Tile(3215, 3218, 0), new Tile(3215, 3213, 0), new Tile(3210, 3211, 0), new Tile(3205, 3209, 1), new Tile(3205, 3209, 2), new Tile(3205, 3214, 2), new Tile(3208, 3219, 2)};
    public Fish(ClientContext ctx) {
        super(ctx);
    }


    @Override
    public boolean activate() {
        Npc FishingSpot = ctx.npcs.select().id(1530).nearest().poll();
        return FishingSpot.valid() && ctx.players.local().animation() == -1 && ctx.inventory.select().count() < 28;
    }

    @Override
    public void execute() {

            Npc FishingSpot = ctx.npcs.select().id(1530).nearest().poll();
            if(!FishingSpot.inViewport() && !ctx.players.local().inMotion())
            {
                ctx.camera.turnTo(FishingSpot);
            }
            System.out.println("Fishing");
            FishingSpot.interact("Bait");
            Condition.sleep(Random.nextInt(5000,7500));
        }

}
