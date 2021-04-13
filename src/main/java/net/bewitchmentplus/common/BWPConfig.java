package net.bewitchmentplus.common;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.bewitchmentplus.BewitchmentPlus;
import net.minecraft.world.biome.Biome;

import java.util.Arrays;
import java.util.List;

@Config(name = BewitchmentPlus.MODID)
public class BWPConfig implements ConfigData {
	public final List<String> drudenBiomeCategories = Arrays.asList(Biome.Category.TAIGA.getName(), Biome.Category.FOREST.getName(), Biome.Category.SWAMP.getName());
	public final int drudenWeight = 4;
	public final int drudenMinGroupCount = 0;
	public final int drudenMaxGroupCount = 2;

	public final List<String> blackDogBiomeCategories = Arrays.asList(Biome.Category.PLAINS.getName());
	public final int blackDogWeight = 4;
	public final int blackDogMinGroupCount = 0;
	public final int blackDogMaxGroupCount = 2;
	public final boolean blackDogStructureSpawn = true;

	public final boolean cambionStructureSpawn = true;

	public final boolean bafometyrStructureSpawn = true;
}