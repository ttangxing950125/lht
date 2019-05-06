package com.bth.lht.dao.user;

import com.bth.lht.respose.wxUser.UserInfoVO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserInfoDao {

    @Select("SELECT m.id,m.domain,m.level,m.my_desc myDesc,m.my_technology myTechnology,m.info_username cName,n.reason,p.makeDeal,p.makeMoney FROM\n" +
            "(SELECT reason,usereo_id FROM tb_wx_user_teameos WHERE teameos_id  =#{teamId}) n,\n" +
            "(SELECT o.user_id,o.`status`,COUNT(*) makeDeal,SUM(mission_award) makeMoney  FROM tb_missions h\n" +
            " JOIN tb_mission_user o ON h.id=o.mission_id  GROUP BY o.user_id) p,\n" +
            "(SELECT c.*,b.wx_nick_name, b.`id` AS userid FROM\n" +
            "tb_user_info c JOIN tb_wx_user AS b ON c.`id` = b.`user_info_id`) m  WHERE m.userid=n.usereo_id AND m.userid=p.user_id \n" +
            "AND p.status = \"done\" and m.userid =#{userId}")

            @Results({
                    @Result(property = "id",column = "id"),
                    @Result(property = "level",column = "level"),
                    @Result(property = "myDesc",column = "myDesc"),
                    @Result(property = "myTechnology",column = "myTechnology"),
                    @Result(property = "cName",column = "cName"),
                    @Result(property = "makeDeal",column = "makeDeal"),
                    @Result(property = "makeMoney",column = "makeMoney"),
                    @Result(property = "reason",column = "reason"),
                    @Result(property = "domain",column = "domain")
            })

    ///*当前的用户自己的团队的申请列表里面个人的 详细情况
    UserInfoVO findByTeamAndUserId(@Param("userId")String userId,@Param("teamId")String teamId);


}
