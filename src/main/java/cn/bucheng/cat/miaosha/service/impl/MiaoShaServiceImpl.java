package cn.bucheng.cat.miaosha.service.impl;

import cn.bucheng.cat.miaosha.service.MiaoShaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @author buchengyin
 * @create 2019/7/7 22:19
 * @describe
 */
@Service
public class MiaoShaServiceImpl implements MiaoShaService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional
    public boolean miaoSha(Long itemId, Long userId) {

        Object o = redisTemplate.opsForValue().get("miaoshao_" + itemId + "_" + userId);
        if (null != o) {
            System.out.println("------------>请稍后再重试 " + userId + "<----------------");
            return false;
        }
        redisTemplate.opsForValue().set("miaoshao_" + itemId + "_" + userId, "miaosha", 10, TimeUnit.SECONDS);

        Object temp = redisTemplate.opsForList().rightPop("miaosha_" + itemId);
        if (null == temp) {
            System.out.println("--------->秒杀商品已经被抢完了<------------");
            return false;
        }


        String miaoShaSql = "update test.t_miaosha_item set number = number -1 where number>0 and item_id = " + itemId;
        String saveSql = "insert into test.t_miaosha_user(user_id,item_id)values(" + userId + "," + itemId + ")";
        int rows = jdbcTemplate.update(miaoShaSql);
        if (rows <= 0)
            return false;
        rows = jdbcTemplate.update(saveSql);
        if (rows < 0) {
            throw new RuntimeException("保存失败");
        }
        return true;
    }
}
