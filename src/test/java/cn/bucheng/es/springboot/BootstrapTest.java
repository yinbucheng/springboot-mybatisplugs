package cn.bucheng.es.springboot;

import cn.bucheng.search.dao.EmployeeRepository;
import cn.bucheng.search.po.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author ：yinchong
 * @create ：2019/6/25 18:29
 * @description：测试
 * @modified By：
 * @version:
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class BootstrapTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Before
    public void before(){
        System.setProperty("es.set.netty.runtime.available.processors","false");
    }


    @Test
    public void testBatchAdd(){
        List<Employee> employeeList = new LinkedList<>();
        for(int i=0;i<20;i++){
            Employee employee = new Employee();
            employee.setId(i+"");
            employee.setFirstName("尹"+i);
            employee.setLastName("步程"+i);
            employee.setAbout("优点 3：assertThat 不再像 assertEquals 那样，使用比较难懂的“谓宾主”语法模式（如：assertEquals(3, x);），相反，assertThat 使用了类似于“主谓宾”的易读语法模式（如：assertThat(x,is(3));），使得代码更加直观、易读。"+i);
            employee.setRemark("优点 4：可以将这些 Matcher 匹配符联合起来灵活使用，达到更多目的。如清单 3 所示:清单 3 Matcher 匹配符联合使用"+i);
            employee.setAge(i);
            employeeList.add(employee);
        }
        employeeRepository.saveAll(employeeList);
    }


    @Test
    public void testListAll(){
        Iterable<Employee> all = employeeRepository.findAll();
        for(Employee employee:all){
            System.out.println(employee);
        }
    }


    @Test
    public void testUpdateEmployee(){
        Optional<Employee> data = employeeRepository.findById("1");
        Employee employee = data.get();
        employee.setRemark("JUnit 4.4 结合 Hamcrest 提供了一个全新的断言语法——assertThat。程序员可以只使用 assertThat 一个断言语句，结合 Hamcrest 提供的匹配符，就可以表达全部的测试思想，我们引入的版本是Junit4.12所以支持assertThat。");
        employeeRepository.save(employee);
    }

    @Test
    public void testFindById(){
        Optional<Employee> data = employeeRepository.findById("1");
        Employee employee = data.get();
        System.out.println(employee);
    }


}
