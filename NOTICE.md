# Notice

## Original work

Gipples Galore is made by Team Jelly / DigitalPear: Rowan, Monozari, sawlferton, Uraneptus, KingRolly and gaytwinkfemboy.

- Original mod (Fabric): https://modrinth.com/mod/gipples-galore
- Original source: https://github.com/ArshiaDPG/False-Futures-Fabric, commit `f24652e` (2023-11-11), the 1.20.1 version.
- The original code is licensed under the Apache License 2.0. Its license text is in `LICENSE-APACHE`, and it ships inside the jar as `LICENSE_gipples_galore-1.20.1`.
- The Modrinth project lists CC BY-NC-SA 4.0. To respect both, this port is released under CC BY-NC-SA 4.0 (`LICENSE`): free, with credit, and under the same license.

This port is unofficial. Team Jelly did not make it and does not support it. Please report bugs of the port here, not to them.

## What changed

The port is the 1.20.1 code of Gipples Galore, moved from Fabric to Forge 1.20.1. Gameplay, textures, models, sounds and data are the original ones.

- Mappings: the source was remapped from Yarn to Mojang names with Loom's `migrateMappings`.
- Registration: Forge freezes the vanilla registries, so `Registry.register` calls are recorded and registered during `RegisterEvent` (new file `GGRegistry.java`). The original `init()` methods run in the same order, inside the first `RegisterEvent`.
- Fabric APIs replaced by Forge events: entity attributes (`EntityAttributeCreationEvent`), biome placement (two biome modifier JSON files), loot (`LootTableLoadEvent`), creative tabs (`BuildCreativeModeTabContentsEvent`), renderers and layers (client events), game rules and mod checks.
- Constructors that need a `Supplier` on Forge: `RecordItem`, `MobBucketItem` and `FlowerPotBlock`. The empty flower pot registers the plants with `addPlant`.
- Models use GeckoLib for Forge 4.8.4 instead of the Fabric build.
- Fix: `models/block/gipplepad.json` pointed to a `falsefutures:` texture that does not exist, which also shows a missing texture on Fabric. It now points to `gipples_galore:block/gipplepad`.
- Not included: the data generation classes. Their output (recipes, loot tables, tags and models) comes from the original 1.20.1 jar.

47 of the 48 Java files were changed from the original and `GGRegistry.java` is new. The changed files keep the original package and class names.
