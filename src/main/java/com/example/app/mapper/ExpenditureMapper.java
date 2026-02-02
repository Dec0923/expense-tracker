package com.example.app.mapper;

import java.util.List;
import java.util.Map;

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
    
    @Insert("INSERT INTO total_history(recorded_total) VALUES(#{total})")
    void saveTotal(int total);
    
    @Select("SELECT * FROM total_history ORDER BY recorded_at DESC")
    List<java.util.Map<String, Object>> findTotalHistory();
    
    @Delete("DELETE FROM total_history")
    void deleteAllHistory();
    
    @Select("SELECT DATE(created_at) as date, SUM(amount) as total FROM expenditures GROUP BY DATE(created_at)")
    List<Map<String, Object>> findDailyTotals();
}