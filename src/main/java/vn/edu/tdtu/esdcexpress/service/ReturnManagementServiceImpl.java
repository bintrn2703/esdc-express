package vn.edu.tdtu.esdcexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.esdcexpress.model.Order;
import vn.edu.tdtu.esdcexpress.model.ReturnManagement;
import vn.edu.tdtu.esdcexpress.model.User;
import vn.edu.tdtu.esdcexpress.repository.ReturnManagementRepository;

import java.util.ArrayList;
import java.util.List;

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
    public Iterable<ReturnManagement> getReturnManagementByOrder(Iterable<Order> orders) {
        List<ReturnManagement> returnManagements = new ArrayList<>();
        for(Order order: orders){
            Iterable<ReturnManagement> foundReturnManagements = returnManagementRepository.findByOrderId(order.getId());
            for(ReturnManagement returnManagement: foundReturnManagements) {
                returnManagements.add(returnManagement);
            }
        }
        return returnManagements;
    }


}
