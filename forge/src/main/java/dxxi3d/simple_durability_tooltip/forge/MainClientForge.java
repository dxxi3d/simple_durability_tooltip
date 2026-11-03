package dxxi3d.simple_durability_tooltip.forge;

import dxxi3d.simple_durability_tooltip.ModLogger;
import dxxi3d.simple_durability_tooltip.SharedConstants;
import net.minecraftforge.fml.common.Mod;

@Mod(SharedConstants.MOD_ID)
public final class MainClientForge {
    public MainClientForge() {
        ModLogger.sendInitialized();
    }
}
