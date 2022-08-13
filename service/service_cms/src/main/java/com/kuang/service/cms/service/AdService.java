package com.kuang.service.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kuang.service.cms.entity.Ad;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kuang.service.cms.entity.vo.AdVo;

import java.util.List;

/**
 * <p>
 * 广告推荐 服务类
 * </p>
 *
 * @author Kuang
 * @since 2022-08-10
 */
public interface AdService extends IService<Ad> {

    IPage<AdVo> selectPage(Long page, Long limit);

    boolean removeAdImageById(String id);

    List<Ad> selectByAdTypeId(String adTypeId);
}
