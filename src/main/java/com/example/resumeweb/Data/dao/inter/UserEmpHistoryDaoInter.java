package com.example.resumeweb.Data.dao.inter;

import com.example.resumeweb.Data.bean.EmpHistory;

import java.util.List;

public interface UserEmpHistoryDaoInter {
    public List<EmpHistory> getAll();

    public EmpHistory getEmpHistoryByUserId(int userId);
}
