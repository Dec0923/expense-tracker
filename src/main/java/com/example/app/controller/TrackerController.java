package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        var history = mapper.findTotalHistory();
        model.addAttribute("list", list);
        model.addAttribute("total", total);
        model.addAttribute("history", history); 
        return "index";
    }

    @PostMapping("/add")
    public String add(Expenditure expenditure) {
        mapper.insert(expenditure);
        return "redirect:/expenditures";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        mapper.delete(id);
        return "redirect:/expenditures";
    }

    // ★追加：現在の合計を記録（保存）する
    @PostMapping("/save-total")
    public String saveTotal(@RequestParam int total) {
        mapper.saveTotal(total);
        return "redirect:/expenditures";
    }
}
