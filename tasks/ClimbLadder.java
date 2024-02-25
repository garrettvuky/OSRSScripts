package tutorial.tasks;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.GroundItem;
import tutorial.Task;

public class ClimbLadder extends Task {
    GroundItem Wine = ctx.groundItems.select().id(245).poll();
    Tile TelegrabTile = new Tile(2939, 3517, 1);
    Tile TelegrabTile2 = new Tile(2939, 3517, 0);

    public ClimbLadder(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return (ctx.players.local().tile().equals(TelegrabTile) && !Wine.inViewport() && ctx.inventory.select().count() < 28) || (ctx.players.local().tile().equals(TelegrabTile2) && !Wine.inViewport() && ctx.inventory.select().count() < 28);
    }

    @Override
    public void execute() {
        GameObject LadderUp = ctx.objects.select().id(31580).poll();
        GameObject LadderDown = ctx.objects.select().id(31579).poll();
        Tile TelegrabTile2 = new Tile(2939, 3517, 0);


        if(ctx.players.local().tile().equals(TelegrabTile2))
        {
            LadderUp.interact("Climb", "Ladder");
        }
        else{
            LadderDown.interact("Climb", "Ladder");
        }

    }
}
