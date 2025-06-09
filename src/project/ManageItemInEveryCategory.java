package project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public interface ManageItemInEveryCategory {
    static void deleteLine(String fileName, String deletedLine) throws IOException {
        RandomAccessFile file = new RandomAccessFile(fileName, "rw");
        String delete;
        StringBuilder task = new StringBuilder();
        byte[] tasking;
        while ((delete = file.readLine()) != null) {
            if (delete.startsWith(deletedLine)) {
                continue;
            }
            task.append(delete).append("\n");
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(task.toString());
        file.close();
        writer.close();
    }

    void viewItems();

    void addItemByAdmin(String itemName, int price);

    void removeItemByAdmin(String itemName) throws IOException;

    void changeStateOfItemByAdmin(String itemName, ItemState state) throws IOException;

    void changePriceOfItemByAdmin(String itemName, int newPrice) throws IOException;

    Item getItemByName(String itemName);
}
