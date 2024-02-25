package tutorial.tasks;

import org.powerbot.bot.rt4.TLoginState;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import tutorial.Task;

public class Cut extends Task {
    public static final int LOG_ID = 1519;
    public static final int[] TREE_ID = {10829,10833,10831};
    public Cut(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.players.local().animation() == -1 && ctx.inventory.select().id(LOG_ID).count() < 19;
    }

    @Override
    public void execute() {
        GameObject Tree = ctx.objects.select().id(TREE_ID).nearest().poll();
        if(Tree.valid())
        {
            System.out.println("Cutting Wood");
            Tree.interact("Chop down", "Willow");
            Condition.sleep(3000);
        }
    }
}
