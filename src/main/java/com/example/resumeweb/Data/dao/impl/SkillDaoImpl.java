package com.example.resumeweb.Data.dao.impl;

import com.example.resumeweb.Data.bean.Skill;
import com.example.resumeweb.Data.dao.inter.SkillDaoInter;
import com.example.resumeweb.Data.dao.inter.AbstractDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillDaoImpl extends AbstractDao implements SkillDaoInter {
    public Skill getSkill(ResultSet rs) throws Exception{
        int id=rs.getInt("id");
        String name=rs.getString("name");
        return new Skill(id,name);
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> skills=new ArrayList<>();
        String sql="SELECT * FROM skill";
        try(Connection c = connection()){
            Statement stmt = c.createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()){
                skills.add(getSkill(rs));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return skills;
    }

    @Override
    public Skill getById(int skillId) {
        String sql = "SELECT * FROM skill WHERE id=?";
        Skill skill = null;
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, skillId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                skill = getSkill(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return skill;
    }

    @Override
    public boolean addSkill(Skill skill) {
        String sql = "INSERT INTO skill ( name )\n" +
                "VALUES\n" +
                "\t(?)";
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, skill.getName());
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteSkill(int SkillId) {
        String sql = "DELETE FROM skill WHERE id=?";
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, SkillId);
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Skill skill) {
        String sql = "UPDATE skill SET name=? WHERE id=?";
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, skill.getName());
            stmt.setInt(2, skill.getId());
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
