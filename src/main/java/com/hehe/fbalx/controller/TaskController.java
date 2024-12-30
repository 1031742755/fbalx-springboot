package com.hehe.fbalx.controller;

import com.hehe.fbalx.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TaskController {

    @Autowired
    private TaskStatusService taskStatusService;

    // 登录页面
    @GetMapping("/index")
    public String indexPage() {
        return "login"; // 返回的页面模板名，应该有 login.html
    }

    // 定时任务开关状态
    @PostMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("scheduledTaskActive", taskStatusService.isScheduledTaskActive());
        return "taskStatus";
    }

    // 更新定时任务开关状态
    @GetMapping("/toggleTask")
    public String getTaskStatus(Model model) {
        model.addAttribute("scheduledTaskActive", taskStatusService.isScheduledTaskActive());
        return "taskStatus";
    }

    @GetMapping("/updateTaskStatus")
    public String updateTaskStatus(Model model, @RequestParam("flag") String flag){
        if(flag != null && flag.equals("1")) {
            taskStatusService.setScheduledTaskActive(true);
        }else if(flag != null && flag.equals("0")){
            taskStatusService.setScheduledTaskActive(false);
        }
        model.addAttribute("scheduledTaskActive", taskStatusService.isScheduledTaskActive());
//        System.out.println(taskStatusService.isScheduledTaskActive());
        return "taskStatus";
    }
}

