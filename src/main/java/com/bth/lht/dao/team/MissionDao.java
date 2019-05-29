package com.bth.lht.dao.team;

import com.bth.lht.respose.mission.MissionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MissionDao {
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "missionTitle",column = "mission_title"),
            @Result(property = "missionInfo",column = "mission_info"),
            @Result(property = "missionStory",column = "mission_story"),
            @Result(property = "missionResponseStopTime",column = "mission_response_stop_time"),
            @Result(property = "missionStartTime",column = "mission_start_time"),
            @Result(property = "missionEndTime",column = "mission_end_time"),
            @Result(property = "missionAwardType",column = "mission_award_type"),
            @Result(property = "missionAward",column = "mission_award"),
            @Result(property = "missionType",column = "mission_type"),
            @Result(property = "missionDeposit",column = "mission_deposit"),
            @Result(property = "missionCheckType",column = "mission_check_type"),
            @Result(property = "missionDemand",column = "mission_demand"),
            @Result(property = "missionCheckStandard",column = "mission_check_standard")

    })


    @Select("\n" +
            "SELECT \n" +
            "  `id`,\n" +
            "  `create_date`,\n" +
            "  `modify_date`,\n" +
            "  `mission_award`,\n" +
            "  `mission_award_type`,\n" +
            "  `mission_check_standard`,\n" +
            "  `mission_check_type`,\n" +
            "  `mission_demand`,\n" +
            "  `mission_deposit`,\n" +
            "  `mission_end_time`,\n" +
            "  `mission_info`,\n" +
            "  `mission_response_stop_time`,\n" +
            "  `mission_start_time`,\n" +
            "  `mission_story`,\n" +
            "  `mission_title`,\n" +
            "  `mission_type`,\n" +
            "  `leader_id`,\n" +
            "  `mission_level`,\n" +
            "  `parent_id` \n" +
            "FROM\n" +
            "  `db_lht`.`tb_missions` WHERE id IN (\n" +
            "\n" +
            "SELECT mission_id FROM tb_mission_team WHERE team_id=#{id，jdbcType=VARCHAR}" +
            "\n" +
            ")")
    //团队任务
    public List<MissionVO> findAllByTeamId(String id);

}
