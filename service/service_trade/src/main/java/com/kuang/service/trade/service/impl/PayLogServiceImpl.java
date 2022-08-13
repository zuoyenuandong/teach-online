package com.kuang.service.trade.service.impl;

import com.kuang.service.trade.entity.PayLog;
import com.kuang.service.trade.mapper.PayLogMapper;
import com.kuang.service.trade.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author Kuang
 * @since 2022-08-12
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}
