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
            "a.team_id,\n" +
            "            a.team_name,\n" +
            "           a.team_desc,\n" +
            "            a.`level`,\n" +
            "            a.team_technology,\n" +
            "            a.team_info,\n" +
            "            c.peopleCount,\n" +
            "\t\t\te.makeDeal,\n" +
            "            e.atype,\n" +
            "            e.makeMoney,\n" +
            "            makeDeal\n" +
            "            FROM\n" +
            "            tb_team a,\n" +
            "            (\n" +
            "            SELECT\n" +
            "            COUNT(user_id)AS peoplecount,\n" +
            "           team_id\n" +
            "       FROM\n" +
            "            tb_team_user\n" +
            "            WHERE\n" +
            "            team_id = #{id}\n" +
            "            )c,\n" +
            "            (\n" +
            "            SELECT\n" +
            "            count(d.id)makeDeal,\n" +
            "        d.mission_award_type atype,\n" +
            "            SUM(d.mission_award)makeMoney\n" +
            "            FROM\n" +
            "            tb_missions d\n" +
            "           WHERE\n" +
            "            d.id IN(\n" +
            "            SELECT\n" +
            "            mission_id\n" +
            "            FROM\n" +
            "            tb_mission_team\n" +
            "           WHERE\n" +
            "            team_id = #{id}\n" +
            "          AND `status` = \"done\"\n" +
            "            )\n" +
            "            GROUP BY\n" +
            "            d.mission_award_type\n" +
            "            )e\n" +
            "            WHERE\n" +
            "           a.`team_id` IN(\n" +
            "            SELECT\n" +
            "            b.team_id misid\n" +
            "            FROM\n" +
            "       tb_mission_team b\n" +
            "            WHERE\n" +
            "            b.`team_id` = #{id}\n" +
            "            AND `status` = \"done\"\n" +
            "          )\n" +
            "            AND a.team_id = c.team_id")
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
