package com.example.SpringBootJDBC.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.SpringBootJDBC.model.Alien;

@Repository 
public class AlienRepo {

    private JdbcTemplate template;
    
    public JdbcTemplate getTemplate() {
        return template;
    }
    @Autowired 
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public void save(Alien alien){
        String sql = "insert into alien (id,name,tech) values (?,?,?)";
        template.update(sql, alien.getId(),alien.getName(),alien.getTech());
    }

    public List<Alien> findAll() {
       String sql = "select id, name, tech from alien";
       
       return template.query(sql, (rs, rowNum) -> {
           Alien a = new Alien();
           a.setId(rs.getInt("id"));
           a.setName(rs.getString("name"));
           a.setTech(rs.getString("tech"));
           return a;
       });
   }
}
