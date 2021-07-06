package rmc.mixins.eidolon_worktable_dupe.inject;

import elucent.eidolon.gui.WorktableResultSlot;
import elucent.eidolon.recipe.WorktableRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

/**
 * Developed by RMC Team, 2021
 * @author KR33PY
 */
@Mixin(value = WorktableResultSlot.class)
public abstract class WorktableResultSlotMixin {

    @Inject(method = "Lelucent/eidolon/gui/WorktableResultSlot;onTake(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;",
            locals = LocalCapture.CAPTURE_FAILHARD,
            at = @At(value = "INVOKE",
                     ordinal = 1,
                     target = "Lnet/minecraftforge/common/ForgeHooks;setCraftingPlayer(Lnet/minecraft/entity/player/PlayerEntity;)V"))
    private void preventExtraDupe(PlayerEntity thePlayer, ItemStack stack, CallbackInfoReturnable<?> mixin, WorktableRecipe recipe, NonNullList<?> items) {
        if (recipe == null && items.size() == 13) {
            items.subList(9, 13).clear();
        }
    }

}
