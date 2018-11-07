package cn.bucheng.springmybatis.scanner;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class ClassScanner extends ClassPathScanningCandidateComponentProvider implements ResourceLoaderAware {

    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        boolean isCandidate = false;
        if (beanDefinition.getMetadata().hasAnnotation(Mapper.class.getCanonicalName())&&beanDefinition.getMetadata().isInterface()) {
            if (!beanDefinition.getMetadata().isAnnotation()) {
                isCandidate = true;
            }
        }
        return isCandidate;
    }

    protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
        return true;
    }

    public Set<BeanDefinition> scann(String ...packageNames){
        Set<BeanDefinition> result = new HashSet<>();
        for(String packageName:packageNames){
            Set<BeanDefinition> candidateComponents = findCandidateComponents(packageName);
            if(candidateComponents!=null&&candidateComponents.size()>0)
                result.addAll(candidateComponents);
        }
        return result;
    }
}
