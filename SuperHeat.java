package tutorial;


import org.powerbot.script.*;
import org.powerbot.script.rt4.*;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.Widget;
@Script.Manifest(name="Superheat", description="Tutorial", properties="client=4; author=Garrett; topic=999;")
public class SuperHeat extends PollingScript<ClientContext> {
    public void start() {

    }
    @Override
    public void poll()
    {
        if(canCast() && ctx.game.tab(Game.Tab.MAGIC) && ctx.inventory.select().id(442).count() > 0)
        {
            System.out.println("Casting");
            Cast();
        }
        else{
            Bank();
        }
        if(ctx.inventory.select().id(442).count() > 0) {
            CastSuperHeat();
            Condition.sleep(Random.nextInt(600, 750));
        }
        if(ctx.inventory.select().id(2355).count() == 26)
        {
vv            Bank();
        }dw
    }
    public boolean canCast()
    {
            return ctx.inventory.select().id(561).count()  > 0;
    }
    public void Spells()
    {
        Component MagicTab = ((ClientContext)this.ctx).widgets.widget(548).component(57);
        MagicTab.interact("Magic");
    }
    public void Cast()
    {
        Component SuperHeatSpell = ((ClientContext)this.ctx).widgets.widget(218).component(30);
        SuperHeatSpell.interact("Cast");
    }
    public void Bank() {
        Npc banker = ctx.npcs.select().id(1633).poll();
        Item IronBars = ctx.inventory.select().id(2355).poll();
        if (!ctx.bank.open()) {
            banker.interact("Bank", "Banker");
            Condition.sleep(Random.nextInt(200, 500));
        }
        if (ctx.bank.open()) {
            IronBars.interact("Deposit-All", "Silver bar");
            System.out.println("Banking");
            Condition.sleep(Random.nextInt(400,550));
        }
        ctx.bank.withdraw(442, 26);
        Condition.sleep(Random.nextInt(200, 300));
        ctx.bank.close();
    }
    public void CastSuperHeat(){
        Item SuperHeatItem = ctx.inventory.itemAt(6, 2);
        Condition.sleep(Random.nextInt(400, 500));
        SuperHeatItem.interact("Cast", "Superheat Item");

    }
}
