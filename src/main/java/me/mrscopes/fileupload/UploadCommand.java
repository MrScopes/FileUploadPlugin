package me.mrscopes.fileupload;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

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
            sender.sendMessage("only works with single files currently.");
            return true;
            /* not yet working
            int length = file.listFiles().length;

            sender.sendMessage(String.format("Zipping '%s' folder.", file.getName(), length));

            File targetFile = new File(String.format("plugins/FileUpload/%s.zip", file.getName() ));
            String[] files = { String.valueOf(targetFile) };

            try {
                sender.sendMessage(String.format("Copying '%s' folder.", file.getName(), length));
                File copiedFile = new File(String.format("plugins/FileUpload/copy-%s", file.getName()));
                Utilities.copyDirectory(file.getAbsolutePath(), copiedFile.getAbsolutePath());
                sender.sendMessage("Copied.");

                sender.sendMessage(String.format("Zipping '%s' folder.", file.getName(), length));
                new Utilities().zip(new String[] { copiedFile.getPath() }, targetFile.getPath());
                //copiedFile.delete();

                sender.sendMessage("Zipped.");
                sender.sendMessage(String.format("Uploading '%s' folder.", file.getName(), length));
                // upload targetFile
                sender.sendMessage("Uploaded.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            */

        }

        else {
            sender.sendMessage(String.format("Uploading file '%s'.", file.getName()));
            File target = new File("plugins/FileUpload/" + file.getName());
            try {
                Files.copy(file.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
                sender.sendMessage(Utilities.uploadFile(target));
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
