package com.bth.lht.respose.wxUser;

import com.bth.lht.respose.mission.MissionVO;
import lombok.Data;

import java.util.List;

@Data
public class UserInfoVO {

    private  String id;

    private String level;//用户等级

    private String myDesc;//个人描述

    private String myTechnology;//我的技术

    private String cName;//昵称

    private int makeDeal;//成交量

    //成交总额
    private Double makeMoney;
//申请原因
    private  String reason;
    //领域
    private String domain;


    //参与的项目团队项目
    List<MissionVO> teamMissionVOS;
    //个人类任务
    List<MissionVO> personalMissionsVO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMyDesc() {
        return myDesc;
    }

    public void setMyDesc(String myDesc) {
        this.myDesc = myDesc;
    }

    public String getMyTechnology() {
        return myTechnology;
    }

    public void setMyTechnology(String myTechnology) {
        this.myTechnology = myTechnology;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getMakeDeal() {
        return makeDeal;
    }

    public void setMakeDeal(int makeDeal) {
        this.makeDeal = makeDeal;
    }

    public Double getMakeMoney() {
        return makeMoney;
    }

    public void setMakeMoney(Double makeMoney) {
        this.makeMoney = makeMoney;
    }



    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public UserInfoVO() {
    }


    public List<MissionVO> getTeamMissionVOS() {
        return teamMissionVOS;
    }

    public void setTeamMissionVOS(List<MissionVO> teamMissionVOS) {
        this.teamMissionVOS = teamMissionVOS;
    }

    public List<MissionVO> getPersonalMissionsVO() {
        return personalMissionsVO;
    }

    public void setPersonalMissionsVO(List<MissionVO> personalMissionsVO) {
        this.personalMissionsVO = personalMissionsVO;
    }


    public UserInfoVO(String id, String level, String myDesc, String myTechnology, String cName, int makeDeal, Double makeMoney, String reason, String domain, List<MissionVO> teamMissionVOS, List<MissionVO> personalMissionsVO) {
        this.id = id;
        this.level = level;
        this.myDesc = myDesc;
        this.myTechnology = myTechnology;
        this.cName = cName;
        this.makeDeal = makeDeal;
        this.makeMoney = makeMoney;
        this.reason = reason;
        this.domain = domain;
        this.teamMissionVOS = teamMissionVOS;
        this.personalMissionsVO = personalMissionsVO;
    }

    @Override
    public String toString() {
        return "UserInfoVO{" +
                "id='" + id + '\'' +
                ", level='" + level + '\'' +
                ", myDesc='" + myDesc + '\'' +
                ", myTechnology='" + myTechnology + '\'' +
                ", cName='" + cName + '\'' +
                ", makeDeal=" + makeDeal +
                ", makeMoney=" + makeMoney +
                ", reason='" + reason + '\'' +
                ", domain='" + domain + '\'' +
                ", teamMissionVOS=" + teamMissionVOS +
                ", personalMissionsVO=" + personalMissionsVO +
                '}';
    }
}
