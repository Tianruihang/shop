package cn.lili.modules.member.mapper;


import cn.lili.modules.member.entity.dos.Member;
import cn.lili.modules.member.entity.vo.MemberAuthVO;
import cn.lili.modules.member.entity.vo.MemberVO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 会员数据处理层
 *
 * @author Bulbasaur
 * @since 2020-02-25 14:10:16
 */
public interface MemberMapper extends BaseMapper<Member> {

    /**
     * 获取所有的会员手机号
     * @return 会员手机号
     */
    @Select("select m.mobile from li_member m")
    List<String> getAllMemberMobile();

    @Select("select * from li_member ${ew.customSqlSegment}")
    IPage<MemberVO> pageByMemberVO(IPage<MemberVO> page, @Param(Constants.WRAPPER) Wrapper<Member> queryWrapper);

    //li_mebmer表中id与atm_user_points表中user_id关联查询
    @Select("select m.*,a.id as pointId,a.user_id,a.true_name,a.id_card_num,a.on_img,a.back_img,a.auth_img,a.zfb_img,a.we_chat_img,a.status" +
            " from li_member m left join atm_user_points a on m.id=a.user_id ${ew.customSqlSegment}")
    IPage<MemberAuthVO> pageByMemberAuthVO(IPage<MemberAuthVO> page, @Param(Constants.WRAPPER) Wrapper<Member> queryWrapper);

    @Select("select m.*,a.id as pointId,a.user_id,a.true_name,a.id_card_num,a.on_img,a.back_img,a.auth_img,a.zfb_img,a.we_chat_img,a.status" +
            " from li_member m left join atm_user_points a on m.id=a.user_id ${ew.customSqlSegment}")
    List<MemberAuthVO> listByMemberAuthVO(@Param(Constants.WRAPPER) Wrapper<Member> queryWrapper);
    //查询实名制会员
    @Select("select m.*,a.status" +
            " from li_member m left join atm_user_points a on m.id=a.user_id where a.status=1")
    List<MemberAuthVO> listByMemberAuthVO2();
}