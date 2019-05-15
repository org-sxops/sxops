package com.sxops.www.linfen.dao.model.journey;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "lf_journey_client")
public class LfJourneyClient {
    /**
     * ID
     */
    @Id
    private Long id;

    /**
     * 任务UUID
     */
    @Column(name = "massage_uuid")
    private String massageUuid;

    /**
     * 用户UUID
     */
    @Column(name = "user_info_uuid")
    private String userInfoUuid;

    /**
     * 始发地
     */
    @Column(name = "originating_place")
    private String originatingPlace;

    /**
     * 目的地
     */
    @Column(name = "objective_place")
    private String objectivePlace;

    /**
     * 预计出发时间
     */
    @Column(name = "es_departure_time")
    private Date esDepartureTime;

    /**
     * 预计到达时间
     */
    @Column(name = "es_arrival_time")
    private Date esArrivalTime;

    /**
     * 简短描述
     */
    private String describe;

    /**
     * 总人数
     */
    @Column(name = "total_number")
    private Integer totalNumber;

    /**
     * 是否置顶
     */
    @Column(name = "is_top")
    private String isTop;

    /**
     * 置顶时长
     */
    @Column(name = "top_time")
    private Long topTime;

    /**
     * 置顶过期时间
     */
    @Column(name = "top_expiration_date")
    private Date topExpirationDate;

    /**
     * 是否启用
     */
    private String enable;

    /**
     * 最后刷新时间
     */
    @Column(name = "last_refresh_time")
    private Date lastRefreshTime;

    /**
     * 置顶权重
     */
    @Column(name = "top_weight")
    private BigDecimal topWeight;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取任务UUID
     *
     * @return massage_uuid - 任务UUID
     */
    public String getMassageUuid() {
        return massageUuid;
    }

    /**
     * 设置任务UUID
     *
     * @param massageUuid 任务UUID
     */
    public void setMassageUuid(String massageUuid) {
        this.massageUuid = massageUuid;
    }

    /**
     * 获取用户UUID
     *
     * @return user_info_uuid - 用户UUID
     */
    public String getUserInfoUuid() {
        return userInfoUuid;
    }

    /**
     * 设置用户UUID
     *
     * @param userInfoUuid 用户UUID
     */
    public void setUserInfoUuid(String userInfoUuid) {
        this.userInfoUuid = userInfoUuid;
    }

    /**
     * 获取始发地
     *
     * @return originating_place - 始发地
     */
    public String getOriginatingPlace() {
        return originatingPlace;
    }

    /**
     * 设置始发地
     *
     * @param originatingPlace 始发地
     */
    public void setOriginatingPlace(String originatingPlace) {
        this.originatingPlace = originatingPlace;
    }

    /**
     * 获取目的地
     *
     * @return objective_place - 目的地
     */
    public String getObjectivePlace() {
        return objectivePlace;
    }

    /**
     * 设置目的地
     *
     * @param objectivePlace 目的地
     */
    public void setObjectivePlace(String objectivePlace) {
        this.objectivePlace = objectivePlace;
    }

    /**
     * 获取预计出发时间
     *
     * @return es_departure_time - 预计出发时间
     */
    public Date getEsDepartureTime() {
        return esDepartureTime;
    }

    /**
     * 设置预计出发时间
     *
     * @param esDepartureTime 预计出发时间
     */
    public void setEsDepartureTime(Date esDepartureTime) {
        this.esDepartureTime = esDepartureTime;
    }

    /**
     * 获取预计到达时间
     *
     * @return es_arrival_time - 预计到达时间
     */
    public Date getEsArrivalTime() {
        return esArrivalTime;
    }

    /**
     * 设置预计到达时间
     *
     * @param esArrivalTime 预计到达时间
     */
    public void setEsArrivalTime(Date esArrivalTime) {
        this.esArrivalTime = esArrivalTime;
    }

    /**
     * 获取简短描述
     *
     * @return describe - 简短描述
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 设置简短描述
     *
     * @param describe 简短描述
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * 获取总人数
     *
     * @return total_number - 总人数
     */
    public Integer getTotalNumber() {
        return totalNumber;
    }

    /**
     * 设置总人数
     *
     * @param totalNumber 总人数
     */
    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    /**
     * 获取是否置顶
     *
     * @return is_top - 是否置顶
     */
    public String getIsTop() {
        return isTop;
    }

    /**
     * 设置是否置顶
     *
     * @param isTop 是否置顶
     */
    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    /**
     * 获取置顶时长
     *
     * @return top_time - 置顶时长
     */
    public Long getTopTime() {
        return topTime;
    }

    /**
     * 设置置顶时长
     *
     * @param topTime 置顶时长
     */
    public void setTopTime(Long topTime) {
        this.topTime = topTime;
    }

    /**
     * 获取置顶过期时间
     *
     * @return top_expiration_date - 置顶过期时间
     */
    public Date getTopExpirationDate() {
        return topExpirationDate;
    }

    /**
     * 设置置顶过期时间
     *
     * @param topExpirationDate 置顶过期时间
     */
    public void setTopExpirationDate(Date topExpirationDate) {
        this.topExpirationDate = topExpirationDate;
    }

    /**
     * 获取是否启用
     *
     * @return enable - 是否启用
     */
    public String getEnable() {
        return enable;
    }

    /**
     * 设置是否启用
     *
     * @param enable 是否启用
     */
    public void setEnable(String enable) {
        this.enable = enable;
    }

    /**
     * 获取最后刷新时间
     *
     * @return last_refresh_time - 最后刷新时间
     */
    public Date getLastRefreshTime() {
        return lastRefreshTime;
    }

    /**
     * 设置最后刷新时间
     *
     * @param lastRefreshTime 最后刷新时间
     */
    public void setLastRefreshTime(Date lastRefreshTime) {
        this.lastRefreshTime = lastRefreshTime;
    }

    /**
     * 获取置顶权重
     *
     * @return top_weight - 置顶权重
     */
    public BigDecimal getTopWeight() {
        return topWeight;
    }

    /**
     * 设置置顶权重
     *
     * @param topWeight 置顶权重
     */
    public void setTopWeight(BigDecimal topWeight) {
        this.topWeight = topWeight;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}