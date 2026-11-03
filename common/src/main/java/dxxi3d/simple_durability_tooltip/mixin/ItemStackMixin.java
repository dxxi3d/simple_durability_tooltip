package dxxi3d.simple_durability_tooltip.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow
    public abstract boolean isDamageableItem();

    @Shadow
    public abstract int getMaxDamage();

    @Shadow
    public abstract int getDamageValue();

    @WrapOperation(
        method = "addDetailsToTooltip",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isDamaged()Z"),
        require = 0
    )
    private boolean hideVanillaDurability(ItemStack instance, Operation<Boolean> original) {
        return false;
    }

    @Inject(
        method = "addDetailsToTooltip",
        at = {
            @At(value = "INVOKE", target = "Lnet/minecraft/world/item/TooltipFlag;isAdvanced()Z"),
            @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;addDetailsToTooltipTail(Lnet/minecraft/world/item/Item$TooltipContext;Lnet/minecraft/world/item/component/TooltipDisplay;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/TooltipFlag;Ljava/util/function/Consumer;)V")
        },
        require = 0
    )
    private void addDurabilityTooltip(Item.TooltipContext context, TooltipDisplay display, @Nullable Player player, TooltipFlag tooltipFlag, Consumer<Component> builder, CallbackInfo ci) {
        if (!this.isDamageableItem() || !display.shows(DataComponents.DAMAGE)) {
            return;
        }

        int maxDurability = this.getMaxDamage();
        int remainingDurability = maxDurability - this.getDamageValue();

        builder.accept(Component.empty());
        builder.accept(Component.translatable("item.durability", remainingDurability, maxDurability));
    }
}
