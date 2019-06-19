package cn.bucheng.fastdfs;

import com.github.tobato.fastdfs.service.DefaultFastFileStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ：yinchong
 * @create ：2019/6/18 19:16
 * @description：fastdfs测试代码
 * @modified By：
 * @version:
 */
public class FastDFSTest {

    private FastFileStorageClient fileStorageClient;

    @Before
    public void init(){
        fileStorageClient = new DefaultFastFileStorageClient();
    }
}
