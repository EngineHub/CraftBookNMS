package com.sk89q.craftbook.togipskcuf.adapter;

import com.sk89q.craftbook.ChangedSign;
import com.sk89q.craftbook.LocalPlayer;
import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.ChatComponentText;
import net.minecraft.server.v1_12_R1.TileEntitySign;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;

public class NMSChangedSign extends ChangedSign {

    private CraftWorld world;
    private BlockPosition position;

    public NMSChangedSign(Block block, String[] lines, LocalPlayer player) {
        super(block, lines, player);

        setupNMSData();
    }

    public NMSChangedSign(Block block, String[] lines) {
        super(block, lines);

        setupNMSData();
    }

    private void setupNMSData() {
        this.world = (CraftWorld) getBlock().getWorld();
        this.position = new BlockPosition(getBlock().getX(), getBlock().getY(), getBlock().getZ());
    }

    @Override
    public void flushLines() {
        TileEntitySign tile = (TileEntitySign) this.world.getHandle().getTileEntity(position);

        String[] newLines = new String[4];
        for (int i = 0; i < newLines.length; i++) {
            newLines[i] = tile.lines[i].toPlainText();
        }

        this.setLines(newLines);
        this.setOldLines(newLines);
    }

    public boolean update(boolean force) {
        if(!hasChanged() && !force)
            return false;
        TileEntitySign tile = (TileEntitySign) this.world.getHandle().getTileEntity(position);
        for(int i = 0; i < 4; i++) {
            tile.lines[i] = new ChatComponentText(this.getLines()[i]);
        }
        String[] oldLines = new String[4];
        System.arraycopy(this.getLines(), 0, oldLines, 0, this.getLines().length);
        setOldLines(oldLines);
        return true;
    }
}
