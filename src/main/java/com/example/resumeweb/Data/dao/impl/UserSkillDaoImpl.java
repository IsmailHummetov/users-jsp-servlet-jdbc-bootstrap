package com.example.resumeweb.Data.dao.impl;

import com.example.resumeweb.Data.bean.Skill;
import com.example.resumeweb.Data.bean.User;
import com.example.resumeweb.Data.bean.UserSkill;
import com.example.resumeweb.Data.dao.inter.UserSkillDaoInter;
import com.example.resumeweb.Data.dao.inter.AbstractDao;
import com.example.resumeweb.Data.dao.inter.UserDaoInter;
import com.example.resumeweb.Data.main.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDaoImpl extends AbstractDao implements UserSkillDaoInter {

    public UserSkill getUserSkill(ResultSet rs) throws Exception {
        int id = rs.getInt("user_skill_id");
        UserDaoInter userDao = Context.instanceUserDao();
        User user = userDao.getbyId(rs.getInt("user_id"));
        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("name");
        Skill skill = new Skill(skillId, skillName);
        int power = rs.getInt("power");
        return new UserSkill(id, user, skill, power);
    }

    @Override
    public List<UserSkill> getAllSkillById(int userId) {
        List<UserSkill> userSkills = new ArrayList<>();
        String sql = "SELECT\n" +
                "\t`user`.*,\n" +
                "\tskill.name,\n" +
                "\tuser_skill.id as user_skill_id,\n" +
                "\tuser_skill.user_id,\n" +
                "\tuser_skill.skill_id,\n" +
                "\tuser_skill.power\n" +
                "FROM\n" +
                "\tuser_skill\n" +
                "\tLEFT JOIN skill ON user_skill.skill_id = skill.id\n" +
                "\tLEFT JOIN `user` ON user_skill.user_id = `user`.id \n" +
                "WHERE\n" +
                "\t`user`.id =?";
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                userSkills.add(getUserSkill(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userSkills;
    }

    @Override
    public boolean addUserSkill(UserSkill us) {
        String sql = "INSERT INTO user_skill ( user_id, skill_id, power )\n" +
                "VALUES\n" +
                "\t(?,?,?)";
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, us.getUser().getId());
            stmt.setInt(2, us.getSkill().getId());
            stmt.setInt(3, us.getPower());
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUserSkill(UserSkill us) {
        String sql = "UPDATE user_skill SET user_id=?,skill_id=?,power=? WHERE id=?";
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, us.getUser().getId());
            stmt.setInt(2, us.getSkill().getId());
            stmt.setInt(3, us.getPower());
            stmt.setInt(4, us.getId());
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteUserSkill(int id) {
        String sql = "DELETE FROM user_skill WHERE id=?";
        try (Connection c = connection()) {
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
