package com.sk89q.craftbook.togipskcuf.adapter;

import com.sk89q.craftbook.ChangedSign;
import com.sk89q.craftbook.LocalPlayer;
import com.sk89q.craftbook.util.SignUtil;
import com.sk89q.craftbook.util.compat.nms.NMSAdapter;
import org.bukkit.block.Block;

public class RealNMSAdapter extends NMSAdapter {

    public boolean hasNMSExtension() {
        return true;
    }

    @Override
    public ChangedSign getChangedSign(Block block, String[] lines, LocalPlayer player) {
        return !SignUtil.isSign(block) ? null : new NMSChangedSign(block, lines, player);
    }
}
