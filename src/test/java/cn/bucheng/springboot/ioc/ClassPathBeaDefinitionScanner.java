package cn.bucheng.springboot.ioc;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;

/**
 * @author ：yinchong
 * @create ：2019/6/26 15:50
 * @description：类扫描器
 * @modified By：
 * @version:
 */
public class ClassPathBeaDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    //判断是否满足加入到set集合中
    @Override
    protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
        //这里表示接口才加入到扫描中
        if(metadataReader.getClassMetadata().isInterface()){
            return true;
        }
        return false;
    }

    //判断是否满足加入到set集合中
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        if(beanDefinition.getMetadata().isInterface()){
            return true;
        }
        return false;
    }
}
