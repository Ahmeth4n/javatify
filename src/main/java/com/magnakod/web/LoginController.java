package com.magnakod.web;

import com.magnakod.dto.aggregation.TasksWeeklyTotal;
import com.magnakod.emulator.Spotify;
import com.magnakod.emulator.utils.SpotifyUtils;
import com.magnakod.entity.Tasks;
import com.magnakod.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static com.magnakod.SpotifyPlatformApplication.getSpotifyBean;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Controller
public class LoginController{
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private TasksRepository tasksRepository;
    @Autowired
    private Spotify spotify;
    @GetMapping(value = "/")
    public String dashboard(Model model) {
        Aggregation agg = newAggregation(
                project("taskCount", "taskAddedDate")
                        .andExpression("dateToString('%Y-%m-%d', taskAddedDate)").as("taskAddedDateString"),
                group("taskAddedDateString", "taskAddedDate")
                        .sum("taskCount").as("totalTaskCount"),
                project("totalTaskCount", "taskAddedDate"),
                sort(Sort.by(Sort.Direction.ASC, "taskAddedDate")),
                limit(10)
        );

        AggregationResults<TasksWeeklyTotal> results = mongoTemplate.aggregate(agg, Tasks.class, TasksWeeklyTotal.class);
        List<TasksWeeklyTotal> weeklyTotals = results.getMappedResults();

        List<List<Object>> chartData = new ArrayList<>();
        for (TasksWeeklyTotal tasksWeeklyTotal : weeklyTotals){
            chartData.add(List.of(SpotifyUtils.dateFormat(tasksWeeklyTotal.getTaskAddedDate()),tasksWeeklyTotal.getTotalTaskCount()));
        }

        model.addAttribute("loaded", spotify.getSessionCount());
        model.addAttribute("chartData",chartData);
        return "dashboard";
    }


}
