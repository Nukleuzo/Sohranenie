package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {

        File dir = new File("Z://Games//savegame");
        if (dir.mkdir()) System.out.println();

        List<GameProgress> spisokIgrokov = new ArrayList<>();

        spisokIgrokov.add(new GameProgress("Save1.dat", 2, 2, 5, 134));
        spisokIgrokov.add(new GameProgress("Save2.dat", 6, 7, 4, 34));
        spisokIgrokov.add(new GameProgress("Save3.dat", 5, 2, 6, 342));


        for (GameProgress player : spisokIgrokov) {
            File fileName = new File(dir, player.getName());
            try {
                if (fileName.createNewFile()) System.out.println("Создан файл " + player.getName());
                saveGame(fileName.getAbsolutePath(), player);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        File archive = new File(dir, "archive.zip");  // создаем архивный файл, куда будет происходить архивация
        System.out.println("Создан архив " + archive.getName());
        for (File file : dir.listFiles()) {
            if (file.getName().contains(".dat")) {
                try {
                    zipFiles(String.valueOf(archive), Arrays.asList(dir.list()));
                    file.delete();
                    System.out.println("Удален файл " + file.getName());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }


    private static void saveGame(String way, GameProgress gameProgress) {

        try (FileOutputStream fos = new FileOutputStream(way);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String archive, List<String> files) throws IOException {
        try {
            ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(archive)); //создаем объект архива
            for (String s : files) {
                try {
                    FileInputStream fis = new FileInputStream(s);
                    zip.putNextEntry(new ZipEntry(new File(s).getName()));
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zip.write(buffer);
                    zip.closeEntry();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
