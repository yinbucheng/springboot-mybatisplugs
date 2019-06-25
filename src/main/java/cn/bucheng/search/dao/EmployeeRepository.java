package cn.bucheng.search.dao;

import cn.bucheng.search.po.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends ElasticsearchRepository<Employee,String> {
    Employee queryEmployeeById(String id);
}
