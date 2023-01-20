import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public abstract class FileReadWrite {
    private static String FILEPATH = Paths.get(Paths.get(System.getProperty("user.dir")).toString(),
            "data/duke.txt").toString();


    public static void readFile(ArrayList<Task> taskList) throws IOException {
        File file = new File(FILEPATH);
        if (! file.exists()) {
            file.mkdir();
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            String[] arr = nextLine.split("___", 5);
            String taskType = arr[0];
            String done = arr[1];
            String description = arr[2];
            Task nextTask;
            if (taskType.equals("T")) {
                boolean d = done.equals("✓") ? true : false;
                nextTask = new Todo(description, d);
            } else if (taskType.equals("D")) {
                boolean d = done.equals("✓") ? true : false;
                String date = arr[3];
                nextTask = new Deadline(description, d, date);
            } else {
                String date1 = arr[3];
                String date2 = arr[4];
                boolean d = done.equals("✓") ? true : false;
                nextTask = new Event(description, d, date1, date2);
            }
            taskList.add(nextTask);
        }
    }

    public static void writeTask(ArrayList<Task> list) throws IOException {
        FileWriter fileWriter = new FileWriter(FILEPATH);
        for (Task t : list) {
            fileWriter.write(t.summary() +"\n");
        }
        fileWriter.close();
    }

    public static void writeMark(int i, Task t) throws IOException {
        File file = new File(FILEPATH);
        Scanner scanner = new Scanner(file);
        ArrayList<String> lines = new ArrayList<>();
        while (scanner.hasNext()){
            lines.add(scanner.nextLine());
        }
        lines.remove(i - 1);
        t.mark();
        lines.add(i - 1, t.summary());
        FileWriter fileWriter = new FileWriter(FILEPATH);
        for (String s : lines){
            fileWriter.write(s + "\n");
        }
        fileWriter.close();
    }

    public static void writeUnmark(int i, Task t) throws IOException {
        File file = new File(FILEPATH);
        Scanner scanner = new Scanner(file);
        ArrayList<String> lines = new ArrayList<>();
        while (scanner.hasNext()){
            lines.add(scanner.nextLine());
        }
        lines.remove(i - 1);
        t.unmark();
        lines.add(i - 1, t.summary());
        FileWriter fileWriter = new FileWriter(FILEPATH);
        for (String s : lines){
            fileWriter.write(s + "\n");
        }
        fileWriter.close();
    }

    public static void writeDelete(int i) throws IOException {
        File file = new File(FILEPATH);
        Scanner scanner = new Scanner(file);
        ArrayList<String> lines = new ArrayList<>();
        while (scanner.hasNext()){
            lines.add(scanner.nextLine());
        }
        lines.remove(i - 1);

        FileWriter fileWriter = new FileWriter(FILEPATH);
        for (String s : lines){
            fileWriter.write(s + "\n");
        }
        fileWriter.close();
    }
}
