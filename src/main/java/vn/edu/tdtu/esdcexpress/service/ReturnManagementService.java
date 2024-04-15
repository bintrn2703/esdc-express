package vn.edu.tdtu.esdcexpress.service;

import vn.edu.tdtu.esdcexpress.model.ReturnManagement;

public interface ReturnManagementService {
    public ReturnManagement save(ReturnManagement returnManagement);
    public ReturnManagement findById(Long id);
}
