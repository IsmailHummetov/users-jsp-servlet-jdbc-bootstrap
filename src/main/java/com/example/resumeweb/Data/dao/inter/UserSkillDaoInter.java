package com.example.resumeweb.Data.dao.inter;

import com.example.resumeweb.Data.bean.UserSkill;

import java.util.List;

public interface UserSkillDaoInter {
    List<UserSkill> getAllSkillById(int userId);

    boolean addUserSkill(UserSkill us);

    boolean updateUserSkill(UserSkill us);

    boolean deleteUserSkill(int id);
}
