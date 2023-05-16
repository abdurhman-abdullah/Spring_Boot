package com.example.homework3.Controller;

import com.example.homework3.Model.TrackerSystem;
import com.example.homework3.Response.MessageResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/TrackerSystem")
public class TrackerSystemController {
    ArrayList<TrackerSystem> trackerSystems = new ArrayList<>();

    @GetMapping("/getAll")
    public Object getAll(){
        return trackerSystems;
    }

    @PostMapping("/add")
    public MessageResponse add(@RequestBody TrackerSystem trackerSystem){
        int count = 1;
        for (int i = 0; i < trackerSystems.size(); i++){
            count = trackerSystems.get(i).getId() + 1;
        }
            trackerSystem.setId(count);
        trackerSystems.add(trackerSystem);
        return new MessageResponse("Success");
    }

    @PutMapping("/update/{index}")
    public MessageResponse put(@PathVariable int index, @RequestBody TrackerSystem trackerSystem){
        if (index != trackerSystems.size()-1 && index > trackerSystems.size()-1){
            return new MessageResponse("this number is not found");
        }
        int id = trackerSystems.get(index).getId();
        trackerSystem.setId(id);
        trackerSystems.set(index, trackerSystem);
        return new MessageResponse("Success");
    }

    @DeleteMapping("/delete/{index}")
    public MessageResponse delete(@PathVariable int index){
        if (index != trackerSystems.size()-1 && index > trackerSystems.size()-1){
            return new MessageResponse("this number is not found");
        }
        trackerSystems.remove(index);
        return new MessageResponse("Success");
    }

    @PutMapping("/changeStatus")
    public MessageResponse changeStatus(){

        for(int i = 0; i < trackerSystems.size(); i++) {
            String check = trackerSystems.get(i).getStatus().toLowerCase();
            if(check.equals("not done")){
                trackerSystems.get(i).setStatus("done");
                trackerSystems.set(i, trackerSystems.get(i));
            }else {
                trackerSystems.get(i).setStatus("not done");
                trackerSystems.set(i, trackerSystems.get(i));
            }

        }

        return new MessageResponse("Success");
    }


    @GetMapping("/getTitle/{title}")
    public Object getByTitle(@PathVariable String title){
        ArrayList<TrackerSystem> search = new ArrayList<>();

        for (int i = 0; i < trackerSystems.size(); i++){
            String searchTitle = trackerSystems.get(i).getTitle().toLowerCase();
            if(searchTitle.equals(title.toLowerCase())) {
                    search.add(trackerSystems.get(i));
                }
            }
            return search;
    }
}
