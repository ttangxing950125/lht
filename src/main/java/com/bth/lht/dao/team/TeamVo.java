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
            "WHERE  a.team_id = c.team_id ORDER BY a.level DESC LIMIT 0,3\n")
    @Results({
            @Result(property = "id",column ="id" ),
            @Result(property = "teamName",column ="team_name" ),
            @Result(property = "teamDesc",column = "team_desc"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "teamInfo",column = "team_info")


    })
    List<TeamVO> findHotTeams();



    // 根据id查找团队
    @Select("SELECT\n" +
            "\ta.team_id,\n" +
            "\ta.team_name,\n" +
            "\ta.team_desc,\n" +
            "\ta.`level`,\n" +
            "\ta.team_technology,\n" +
            "\ta.team_info,\n" +
            "\tc.peopleCount,\n" +
            "\te.makeDeal,\n" +
            "\te.atype,\n" +
            "\te.makeMoney,\n" +
            "\tmakeDeal\n" +
            "FROM\n" +
            "\ttb_team a,\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tCOUNT(user_id)AS peoplecount,\n" +
            "\t\t\tteam_id\n" +
            "\t\tFROM\n" +
            "\t\t\ttb_team_user\n" +
            "\t\tWHERE\n" +
            "\t\t\tteam_id = #{id}\n" +
            "\t)c,\n" +
            "\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tcount(d.id)makeDeal,\n" +
            "\t\t\td.mission_award_type atype,\n" +
            "\t\t\tSUM(d.mission_award)makeMoney\n" +
            "\t\tFROM\n" +
            "\t\t\ttb_missions d\n" +
            "\t\tWHERE\n" +
            "\t\t\td.id IN(\n" +
            "\t\t\t\tSELECT\n" +
            "\t\t\t\t\tmission_id\n" +
            "\t\t\t\tFROM\n" +
            "\t\t\t\t\ttb_mission_team\n" +
            "\t\t\t\tWHERE\n" +
            "\t\t\t\t\tteam_id = #{id}\n" +
            "\t\t\t\tAND `status` = \"done\"\n" +
            "\t\t\t)\n" +
            "\t\tGROUP BY\n" +
            "\t\t\td.mission_award_type\n" +
            "\t)e\n" +
            "WHERE\n" +
            "\ta.`team_id` IN(\n" +
            "\t\tSELECT\n" +
            "\t\t\tb.team_id misid\n" +
            "\t\tFROM\n" +
            "\t\t\ttb_mission_team b\n" +
            "\t\tWHERE\n" +
            "\t\t\tb.`team_id` = #{id}\n" +
            "\t\tAND `status` = \"done\"\n" +
            "\t)\n" +
            "AND a.team_id = c.team_id")
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
