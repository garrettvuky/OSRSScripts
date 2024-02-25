package tutorial.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;
import tutorial.Task;

public class BankLobster extends Task {
    public BankLobster(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        GameObject BankDeposit = ctx.objects.select().id(26254).poll();
        return ctx.inventory.select().count() > 27 && BankDeposit.inViewport();
    }

    @Override
    public void execute() {
        Item Lobster = ctx.inventory.select().id(377).shuffle().poll();
        GameObject BankDeposit = ctx.objects.select().id(26254).poll();
        BankDeposit.interact("Deposit");
        Condition.sleep(Random.nextInt(3000, 5000));
        if(ctx.bank.open())
        {
            Lobster.interact("Deposit-All", "Raw lobster");
        }
        ctx.bank.close();
    }
}
