package tutorial.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Locatable;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;
import org.powerbot.script.rt4.Component;
import tutorial.Task;

import java.awt.*;

public class BankTele extends Task {
    public static final Tile[] path = {new Tile(2944, 3517, 0), new Tile(2942, 3512, 0), new Tile(2941, 3507, 0), new Tile(2941, 3502, 0), new Tile(2942, 3497, 0), new Tile(2942, 3492, 0), new Tile(2942, 3487, 0), new Tile(2944, 3482, 0), new Tile(2944, 3477, 0), new Tile(2944, 3472, 0), new Tile(2944, 3467, 0), new Tile(2944, 3462, 0), new Tile(2944, 3457, 0), new Tile(2944, 3452, 0), new Tile(2946, 3447, 0), new Tile(2948, 3442, 0), new Tile(2948, 3437, 0), new Tile(2949, 3432, 0), new Tile(2949, 3427, 0), new Tile(2953, 3423, 0), new Tile(2957, 3420, 0), new Tile(2960, 3416, 0), new Tile(2963, 3412, 0), new Tile(2965, 3407, 0), new Tile(2965, 3402, 0), new Tile(2965, 3397, 0), new Tile(2965, 3392, 0), new Tile(2964, 3387, 0), new Tile(2960, 3384, 0), new Tile(2956, 3381, 0), new Tile(2952, 3377, 0), new Tile(2947, 3375, 0), new Tile(2946, 3370, 0)};
    public BankTele(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        Npc banker = ctx.npcs.select().id(1618).poll();
        return banker.valid() && (ctx.inventory.select().id(245).count() > 0);
    }

    @Override
    public void execute() {
        Npc banker = ctx.npcs.select().id(1618).poll();
        if(ctx.inventory.select().id(245).count() > 0) {
            if(!banker.inViewport())
            {
                ctx.camera.turnTo(banker);
            }

            Bank();
        }
    }
    public void Bank(){
        Npc banker = ctx.npcs.select().id(1618).poll();
        if (!ctx.bank.open()) {
            System.out.println("Banking");
            banker.interact("Bank", "Banker");
            Condition.sleep(Random.nextInt(200, 500));
        }
        if (ctx.bank.open()) {
            System.out.println("Banking");
            Widget DepositAll = ((ClientContext)this.ctx).widgets.widget(629);
            ctx.inventory.select().id(245).poll().interact("Deposit-All", "Wine of zamorak");
            Condition.sleep(Random.nextInt(300, 500));
            ctx.inventory.select().id(11941).poll().interact("View", "Looting bag");
            Condition.sleep(Random.nextInt(300, 500));
            ctx.inventory.select().poll().interact("Deposit loot");
            Condition.sleep(Random.nextInt(300, 500));
            ctx.bank.close();
        }
    }
}
