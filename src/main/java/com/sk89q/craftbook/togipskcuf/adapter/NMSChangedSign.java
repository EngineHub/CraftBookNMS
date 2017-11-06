package com.sk89q.craftbook.togipskcuf.adapter;

import com.sk89q.craftbook.ChangedSign;
import com.sk89q.craftbook.LocalPlayer;
import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.IBlockData;
import net.minecraft.server.v1_12_R1.TileEntitySign;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.block.CraftSign;

public class NMSChangedSign extends ChangedSign {

    private CraftWorld world;
    private BlockPosition position;
    private TileEntitySign sign;

    private boolean setup = false;

    public NMSChangedSign(Block block, String[] lines, LocalPlayer player) {
        super(block, lines, player);

        setupNMSData();
    }

    public NMSChangedSign(Block block, String[] lines) {
        super(block, lines);

        setupNMSData();
    }

    private void setupNMSData() {
        if (setup) return;
        this.setup = true;
        this.world = (CraftWorld) getBlock().getWorld();
        this.position = new BlockPosition(getBlock().getX(), getBlock().getY(), getBlock().getZ());
        this.sign = (TileEntitySign) this.world.getHandle().getTileEntity(position);
    }

    @Override
    public void flushLines() {
        if (!setup) {
            setupNMSData();
        }

        if (sign == null) {
            System.out.println("Failed to update sign. Tile entity missing at " + position.toString());
            return;
        }

        String[] newLines = CraftSign.revertComponents(sign.lines);
        String[] oldLines = new String[newLines.length];
        System.arraycopy(newLines, 0, oldLines, 0, 4);

        this.setLines(newLines);
        this.setOldLines(oldLines);
    }

    @Override
    public boolean update(boolean force) {
        if(!hasChanged() && !force)
            return false;

        if (sign == null) {
            System.out.println("Failed to update sign. Tile entity missing at " + position.toString());
            return false;
        }

        System.arraycopy(CraftSign.sanitizeLines(this.getLines()), 0, sign.lines, 0, 4);

        sign.update();
        IBlockData blockData = world.getHandle().getType(position);
        world.getHandle().notify(position, blockData, blockData, 3);
        String[] oldLines = new String[4];
        System.arraycopy(this.getLines(), 0, oldLines, 0, this.getLines().length);
        setOldLines(oldLines);
        return true;
    }
}
