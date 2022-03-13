package dev.mrsterner.bewitchmentplus.common.registry;

import dev.mrsterner.bewitchmentplus.BewitchmentPlus;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class BWPStatusEffects {
    private static final Map<StatusEffect, Identifier> STATUS_EFFECTS = new LinkedHashMap<>();

    public static final StatusEffect HOMESTEAD = register("homestead", new BWPStatusEffect(StatusEffectCategory.BENEFICIAL, 0x9a9ebf));
    public static final StatusEffect HALF_LIFE = register("half_life", new BWPStatusEffect(StatusEffectCategory.BENEFICIAL, 0x9a9ebf));

    private static <T extends StatusEffect> T register(String name, T effect) {
        STATUS_EFFECTS.put(effect, new Identifier(BewitchmentPlus.MODID, name));
        return effect;
    }

    public static void init() {
        STATUS_EFFECTS.keySet().forEach(effect -> Registry.register(Registry.STATUS_EFFECT, STATUS_EFFECTS.get(effect), effect));
    }

    public static class BWPStatusEffect extends StatusEffect {
        public BWPStatusEffect(StatusEffectCategory category, int color) {
            super(category, color);
        }
    }
}
