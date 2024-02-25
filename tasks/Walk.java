package tutorial.tasks;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import tutorial.Task;
import tutorial.Walker;

public class Walk extends Task {
    Walker walker = new Walker(ctx);
    public static final Tile[] path = {new Tile(3222, 3308, 0), new Tile(3227, 3308, 0), new Tile(3232, 3308, 0), new Tile(3237, 3308, 0), new Tile(3244, 3308, 0)};
    public Walk(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(1519).count() == 19 && ctx.movement.destination().equals(Tile.NIL);
    }

    @Override
    public void execute() {
        if(!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL))
        {
            walker.walkPath(path);
        }
    }
}
