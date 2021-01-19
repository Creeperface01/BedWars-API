import java.net.URI

rootProject.name = "BedWars-api"

sourceControl {
    gitRepository(URI.create("https://github.com/Creeperface01/PlaceholderAPI-nukkit.git")) {
        producesModule("com.creeperface.nukkit.placeholderapi:PlaceholderAPI")
    }
}