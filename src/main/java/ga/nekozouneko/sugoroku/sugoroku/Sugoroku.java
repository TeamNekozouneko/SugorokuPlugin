package ga.nekozouneko.sugoroku.sugoroku;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public final class Sugoroku extends JavaPlugin implements Listener {

    private List<String> l = Arrays.asList("副業に失敗! 5マス戻る","マイクラで遊んでいたらBANされた! 3マス戻る","警察に捕まった! 10マス戻る","某管理人に論破された! 2回休む","チャレンジ! さいころで3以下が出たら10マス進む","ゲームの大会に出た! 2以下なら10マス進む!","寄付したらいいことが帰ってきた! 5マス進む","某鯖主にBANされる...15マス戻る","チャレンジ! 次のすごろくで10が出れば5回すごろくをふる","就職に失敗した... 4マス戻る",
            "家の中を一周する(リアル)","Minecraftを買ったら運良く500000000個目だった! 15マスすすむ","何もなし","何もなし","何もなし","何もなし","初心者以外は鉄ツルハシを作る","なにか食べる(リアル)","料理作る","ねこぞうに電話",
            "ねこ家族に電話","JEの利点","BEの利点","CS版の利点","BEに愚痴","JEに愚痴","CSに愚痴","6x6以上の家を作る","誰かに愚痴を言う","誰かに愚痴を言われる");

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this,this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private ItemStack sugoroku;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        sugoroku = new ItemStack(Material.ENDER_CHEST,1);
        ItemMeta meta = sugoroku.getItemMeta();
        meta.setDisplayName("§6§lさいころ");
        List<String> b = new ArrayList<>();
        b.add("§r右クリックして回す");
        meta.setLore(b);
        sugoroku.setItemMeta(meta);
        e.getPlayer().getInventory().remove(sugoroku);
        e.getPlayer().getInventory().addItem(sugoroku);
    }

    @EventHandler
    public void onRightClicked(PlayerInteractEvent e) {
        if (e.getItem().equals(sugoroku)) {
            Random a = new Random();
            int b = a.nextInt(11);
            int lb = a.nextInt(30);
            e.getPlayer().chat("/susumu "+e.getPlayer().getName()+" "+b);
            for (Player p:getServer().getOnlinePlayers()) {
                 p.sendTitle("§e"+b,"マス進む ("+e.getPlayer().getName()+")",5,40,5);
                 p.sendMessage("[§6すごろくプラグイン§r] お題:\n"+ l.get(lb));

            }
        }

    }
}
