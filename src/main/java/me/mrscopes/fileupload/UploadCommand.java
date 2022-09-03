package me.mrscopes.fileupload;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class UploadCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage("/upload <file>");
            return true;
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            sender.sendMessage("Could not find that file.", "Example: '/upload plugins/EssentialsX.jar'.");
            return true;
        }

        if (file.isDirectory()) {
            if (System.getProperty("os.name").contains("Windows")) sender.sendMessage("Note: On windows, some files in use may be locked and it will result in them not being transferred. This is usually a world folder for example.");

            try {
                sender.sendMessage(String.format("Zipping '%s' folder.", file.getName()));
                File targetFile = new File(String.format("%s/%s.zip", FileUpload.getInstance().getDataFolder().getPath(), file.getName()));
                new Utilities().zip(new String[] { file.getPath() }, targetFile.getPath());

                sender.sendMessage(String.format("Uploading '%s' folder.", file.getName()));

                sender.sendMessage(Utilities.uploadFile(targetFile));
                targetFile.delete();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        else {
            sender.sendMessage(String.format("Uploading file '%s'.", file.getName()));
            try {
                sender.sendMessage(Utilities.uploadFile(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
