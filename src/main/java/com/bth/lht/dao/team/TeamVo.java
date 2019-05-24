package com.bth.lht.dao.team;
import com.bth.lht.respose.team.TeamVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeamVo {


    //查找当前热门团队
    @Select("SELECT a.id,a.team_name,a.team_desc,a.team_info,c.peoplecount FROM tb_team a,\n" +
            "(\n" +
            "SELECT COUNT(user_id) AS peoplecount,team_id FROM tb_team_user GROUP BY team_id\n" +
            ") c \n" +
            "WHERE  a.id = c.team_id ORDER BY a.level DESC LIMIT 0,3\n")
    @Results({
            @Result(property = "id",column ="id" ),
            @Result(property = "teamName",column ="team_name" ),
            @Result(property = "teamDesc",column = "team_desc"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "teamInfo",column = "team_info")


    })
    List<TeamVO> findHotTeams();
    // 根据id查找团队
    @Select("SELECT a.id,a.team_name,a.team_desc,a.`level`,a.team_technology,a.team_info,c.peopleCount,e.atype,SUM(e.makeMoney)  makeMoney,COUNT(e.misid) makeDeal FROM tb_team a,\n" +
            "\n" +
            "(SELECT COUNT(user_id) AS peoplecount,team_id FROM tb_team_user WHERE team_id =#{id}) c ,\n" +
            "\n" +
            "(\n" +
            "SELECT d.id misid,d.mission_award_type atype ,d.mission_award makeMoney FROM tb_missions d WHERE d.id IN(\n" +
            "SELECT mission_id FROM tb_mission_team WHERE team_id =#{id} AND `status` =\"done\"\n" +
            " )\n" +
            " ) e \n" +
            "\n" +
            "WHERE a.`id` IN(\n" +
            "\tSELECT b.team_id misid FROM tb_mission_team b WHERE b.`team_id` = #{id} AND `status` =\"done\"\n" +
            "\n" +
            ") AND a.id = c.team_id \n")
    @Results({
            @Result(property = "id",column ="id" ),
            @Result(property = "teamName",column ="team_name" ),
            @Result(property = "teamTechnology",column = "team_technology"),
            @Result(property = "teamDesc",column = "team_desc"),
            @Result(property = "teamInfo",column = "team_info"),
            @Result(property = "level",column = "level"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "makeDeal",column = "makeDeal"),
            @Result(property = "makeMoney",column = "makeMoney")


    })
    TeamVO findByTeamId(String id);



}
