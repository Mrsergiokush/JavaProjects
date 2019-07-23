package by.exadel.application.store;

import by.exadel.application.model.Task;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;

@Component
public class TaskStore implements IStore<Task>{

    private final String TASKS = "Tasks.txt";

    @Override
    public void setAll(ArrayList<Task> tasks) throws IOException {

        Writer writer = new FileWriter(TASKS);

        for (int i = 0; i < tasks.size(); i++) {
            writer.write(" " + tasks.get(i).getTaskId() + ",");
            writer.write(tasks.get(i).getTaskName() + ",");
            writer.write(tasks.get(i).getDeadline() + ",");
            writer.write(tasks.get(i).getUserId() + "\n");
        }
        writer.close();
    }

    @Override
    public ArrayList<Task> getAll() throws IOException{

        ArrayList<Task> tasks = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(TASKS));

        while((reader.read()) != -1){
            String[] string = reader.readLine().split(",");

            Task task = new Task();
            task.setTaskId(Integer.parseInt(string[0]));
            task.setTaskName(string[1]);
            task.setDeadline(string[2]);
            task.setUserId(Integer.parseInt(string[3]));

            tasks.add(task);
        }
        return tasks;
    }

    @Override
    public Integer scanID() throws IOException{

        ArrayList<Task> tasks = new ArrayList<>(getAll());

        Integer id = -1;

        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getTaskId() > id)
                id = tasks.get(i).getTaskId();
        }
        id++;
        return id;
    }
}
