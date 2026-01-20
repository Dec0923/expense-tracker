package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.entity.Expenditure;
import com.example.app.mapper.ExpenditureMapper;

@Controller
public class TrackerController {

    @Autowired
    private ExpenditureMapper mapper;

    @GetMapping("/expenditures")
    public String index(Model model) {
        var list = mapper.findAll();
        int total = list.stream().mapToInt(Expenditure::getAmount).sum();
        model.addAttribute("list", list);
        model.addAttribute("total", total);
        return "index";
    }

    @PostMapping("/add")
    public String add(Expenditure expenditure) {
        mapper.insert(expenditure);
        return "redirect:/expenditures";
    }
}
