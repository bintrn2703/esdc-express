package vn.edu.tdtu.esdcexpress.service;

import vn.edu.tdtu.esdcexpress.model.Order;
import vn.edu.tdtu.esdcexpress.model.ReturnManagement;
import vn.edu.tdtu.esdcexpress.model.User;

public interface ReturnManagementService {
    public ReturnManagement save(ReturnManagement returnManagement);
    public ReturnManagement findById(Long id);
    public Iterable<ReturnManagement> getReturnManagementByOrder(Order order);
}
