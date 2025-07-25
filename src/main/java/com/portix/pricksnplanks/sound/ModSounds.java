package com.portix.pricksnplanks.sound;

import com.portix.pricksnplanks.PricksnPlanks;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent THORNS = registerSoundEvent("thorns");
    public static final RegistryKey<JukeboxSong> THORNS_KEY =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(PricksnPlanks.MOD_ID, "thorns"));


    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(PricksnPlanks.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        PricksnPlanks.LOGGER.info("Registering Mod Sounds for " + PricksnPlanks.MOD_ID);
    }
}
