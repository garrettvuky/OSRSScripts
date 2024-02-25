package tutorial.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import tutorial.Task;

public class CrossPlank extends Task {
    public CrossPlank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        Tile plankTileSarim = new Tile(3032,3217,1);
        Tile plankTileKaramja = new Tile(2956,3143,1);
        return ctx.players.local().tile().equals(plankTileSarim) || ctx.players.local().tile().equals(plankTileKaramja);
    }

    @Override
    public void execute() {
        Tile plankTileSarim = new Tile(3032,3217,1);
        Tile plankTileKaramja = new Tile(2956,3143,1);
        if(ctx.players.local().tile().equals(plankTileSarim)) {
            GameObject Gangplank1 = ctx.objects.select().id(2084).poll();
            Gangplank1.interact("Cross");
            Condition.sleep(Random.nextInt(2000, 3000));
        }
        if(ctx.players.local().tile().equals(plankTileKaramja)) {
            GameObject Gangplank2 = ctx.objects.select().id(2082).poll();
            Gangplank2.interact("Cross");
            Condition.sleep(Random.nextInt(2000, 3000));
        }
    }
}
