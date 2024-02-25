package tutorial.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Npc;
import tutorial.Task;

public class BoardShip extends Task {
    public BoardShip(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        Npc CustomsOfficer = ctx.npcs.select().id(3648).poll();
        Npc Tobias = ctx.npcs.select().id(3644).poll();
        return (CustomsOfficer.inViewport() && ctx.inventory.select().id(377).count() > 0) || (Tobias.inViewport() && ctx.inventory.select().count() < 28);
    }

    @Override
    public void execute() {
        if(ctx.inventory.select().count() > 27) {
            Npc CustomsOfficer = ctx.npcs.select().id(3648).poll();
            CustomsOfficer.interact("Pay-Fare", "Customs officer");
            Condition.sleep(Random.nextInt(2500, 5000));
            ctx.input.send(" ");
            Condition.sleep(Random.nextInt(600, 700));
            ctx.input.send("1");
            Condition.sleep(Random.nextInt(600, 700));
            ctx.input.send(" ");
            Condition.sleep(Random.nextInt(600, 700));
            ctx.input.send(" ");
            Condition.sleep(Random.nextInt(600, 700));
            ctx.input.send("2");
            Condition.sleep(Random.nextInt(600, 700));
            ctx.input.send(" ");
            Condition.sleep(Random.nextInt(600, 700));
            ctx.input.send(" ");
            Condition.sleep(Random.nextInt(600, 700));
            ctx.input.send("1");
            Condition.sleep(Random.nextInt(600, 700));
            ctx.input.send(" ");
            Condition.sleep(Random.nextInt(3000, 5000));
        }
        if(ctx.inventory.select().id(377).count() == 0){
            Npc Tobias = ctx.npcs.select().id(3644).poll();
            Tobias.interact("Pay-Fare", "Captain Tobias");
            Condition.sleep(Random.nextInt(2500, 3500));
            ctx.input.send(" ");
            Condition.sleep(Random.nextInt(400, 700));
            ctx.input.send("1");
            Condition.sleep(Random.nextInt(400, 700));
            ctx.input.send(" ");
            Condition.sleep(Random.nextInt(400, 700));
        }

    }
}
