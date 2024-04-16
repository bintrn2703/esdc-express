package vn.edu.tdtu.esdcexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.esdcexpress.model.Order;
import vn.edu.tdtu.esdcexpress.model.ReturnManagement;
import vn.edu.tdtu.esdcexpress.model.User;
import vn.edu.tdtu.esdcexpress.repository.ReturnManagementRepository;
@Service
public class ReturnManagementServiceImpl implements ReturnManagementService{
    @Autowired
    ReturnManagementRepository returnManagementRepository;
    @Override
    public ReturnManagement save(ReturnManagement returnManagement) {
        return returnManagementRepository.save(returnManagement);
    }

    @Override
    public ReturnManagement findById(Long id) {
        return returnManagementRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<ReturnManagement> getReturnManagementByOrder(Order order) {
        return returnManagementRepository.findByOrderId(order.getId());
    }
}
