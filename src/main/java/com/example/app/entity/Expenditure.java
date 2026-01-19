package com.example.app.entity;
import lombok.Data;

@Data
public class Expenditure {
private Long id;
private String title;
private Integer amount;
private String category;
}
