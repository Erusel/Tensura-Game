package fr.erusel.tensura.chunkgenerators;

import org.bukkit.generator.ChunkGenerator;

public class EmptyChunkGenerator extends ChunkGenerator {

    @Override
    public ChunkData generateChunkData(org.bukkit.World world, java.util.Random random, int x, int z, org.bukkit.generator.ChunkGenerator.BiomeGrid biome) {
        return createChunkData(world);
    }
}
