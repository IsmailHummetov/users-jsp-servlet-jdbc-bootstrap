package com.example.resumeweb.main;

import com.example.resumeweb.bean.*;
import com.example.resumeweb.dao.impl.*;
import com.example.resumeweb.dao.inter.*;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserDaoInter userDao = new UserDaoImpl();
        //Getting all user from database
        List<User> user = userDao.getAll();
        for (User u: user)
            System.out.println(u);
/*
        //System.out.println(userDao.getUserbyId(5));*/

 /*//Insert user to database
        User u = new User(1,"Ismail","Hummetov"
                ,"hummetov@gmail.com","+994707070707"
                ,"Java BackEnd Developer",2003/08/20
                ,2,1,"Sumgait");
        userDao.addUser(u);*/

 /*//Update User dataes
        User u = userDao.getUserbyId(6);
        u.setFirstname("Akif");
        u.setLastname("Agayev");
        u.setProfileDescription("Java");
        userDao.updateUser(u);*/

 /*//Removing user with ID
        userDao.deleteUser(7);*/

 /*CountryDaoInter countryDao = new CountryDaoImpl();
//        List<Country>countries = countryDao.getAll();
//        for (Country c : countries)
//            System.out.println(c);
//        Country c = new Country(4,"USA","American");
//        countryDao.addCountry(c);
//        Country c = countryDao.getbyId(1);
//        c.setNationality("Azerbaijani");
//        countryDao.updateCountry(c);
        countryDao.deleteCountry(9);*/

 /*SkillDaoInter skillDao = new SkillDaoImpl();
        List<Skill>skills= skillDao.getAll();
        for(Skill s:skills)
            System.out.println(s);
        //System.out.println(skillDao.getById(5));
        //skillDao.addSkill(new Skill(1,"WordPress"));
//        Skill s=skillDao.getById(10);
//        s.setName("Pascal");
//        skillDao.update(s);
        skillDao.deleteSkill(10);*/

 /*UserDaoInter userDao= new UserDaoImpl();
        User u = userDao.getbyId(1);
        SkillDaoInter skillDao = new SkillDaoImpl();
        Skill skill = skillDao.getById(8);
        UserSkill us = new UserSkill(14,u,skill,9);

        //userSkillDao.addUserSkill(us);
        userSkillDao.updateUserSkill(us);*/
 /*UserSkillDaoInter userSkillDao = new UserSkillDaoImpl();
        userSkillDao.deleteUserSkill(11);
        List<UserSkill>userSkills=userSkillDao.getAllSkillById(5);
        for(UserSkill userskill:userSkills)
            System.out.println(userskill);*/

 /*EmpHistoryDaoInter empHistoryDao = new EmpHistoryDaoImpl();
        List<EmpHistory> list = empHistoryDao.getAll();
        for(EmpHistory l : list)
            System.out.println(l);
        System.out.println(empHistoryDao.getById(1));*/
//        UserEmpHistoryDaoInter userEmpHistoryDao = Context.instanceUserEmpHistoryDao();
////        List <EmpHistory> empHistoryList = userEmpHistoryDao.getAll();
////        for (EmpHistory eh:empHistoryList)
////            System.out.println(eh);
//        System.out.println(userEmpHistoryDao.getEmpHistoryByUserId(1));
        /*UserDaoInter userDao = Context.instanceUserDao();
        User u = userDao.getbyId(1);
        System.out.println(u.getFirstname());*/
    }
}
