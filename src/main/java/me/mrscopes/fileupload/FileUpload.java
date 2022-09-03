package me.mrscopes.fileupload;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class FileUpload extends JavaPlugin {
    private static FileUpload instance;
    public static FileUpload getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        getLogger().info("Enabled.");
        getCommand("upload").setExecutor(new UploadCommand());
    }
}
