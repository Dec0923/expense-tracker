package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.app.entity.Expenditure;

@Mapper
public interface ExpenditureMapper {
    @Select("SELECT * FROM expenditures ORDER BY created_at DESC")
    List<Expenditure> findAll();

    @Insert("INSERT INTO expenditures(title, amount, category) VALUES(#{title}, #{amount}, #{category})")
    void insert(Expenditure expenditure);

    @Delete("DELETE FROM expenditures WHERE id = #{id}")
    void delete(Long id);
}