package me.mrscopes.fileupload;

import org.bukkit.plugin.java.JavaPlugin;

public final class FileUpload extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Enabled.");
        getCommand("upload").setExecutor(new UploadCommand());
    }
}
