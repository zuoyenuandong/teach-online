package com.kuang.service.ucenter.service;

import com.kuang.service.base.dto.MemberDto;
import com.kuang.service.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kuang.service.ucenter.entity.vo.LoginVo;
import com.kuang.service.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Kuang
 * @since 2022-08-10
 */
public interface MemberService extends IService<Member> {

    void register(RegisterVo registerVo);

    String login(LoginVo loginVo);

    Member getByOpenid(String openid);

    MemberDto getMemberDtoByMemberId(String memberId);
}
