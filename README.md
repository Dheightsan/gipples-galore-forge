# Gipples Galore for Forge 1.20.1 (unofficial port)

A port of [Gipples Galore](https://modrinth.com/mod/gipples-galore) by Team Jelly from Fabric to Forge 1.20.1.

Gipples are small flying creatures that live in rare colonies inside caves. They feed on glow lichen and produce gelatin, and if you feed one too early it splits in two. The mod also adds gelatin blocks, jellies with different effects, the gelatite and amoebalite stone sets, and the aneuploidian, which hunts gipples.

All of that is Team Jelly's work. This repository only moves it to Forge. See [NOTICE.md](NOTICE.md) for the credits and the full list of changes.

## Requirements

- Minecraft 1.20.1
- Forge 47 or newer
- [GeckoLib](https://modrinth.com/mod/geckolib) for Forge 1.20.1, 4.2 or newer (tested with 4.8.4)

## Tested

- Dedicated server: starts with no errors, mobs spawn with their original health, natural colonies generate in new chunks, and gelatin shows up in ancient city loot.
- Client: GeckoLib models, all gelatin blocks, both stone sets and the colonies, checked in game with screenshots.

If something breaks, open an issue here with your log and your mod list.

## Building

```
./gradlew build
```

The jar ends up in `build/libs`. You need JDK 17.

## License

The port is released under [CC BY-NC-SA 4.0](LICENSE): free to use and share, with credit, not for commercial use, and under the same license. The original code by Team Jelly is under the [Apache License 2.0](LICENSE-APACHE).
