package com.sxops.www.linfen.dao.model.journey;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "lf_journey_server")
@ToString
@Data
public class JourneyServer {
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
     * 可乘人数
     */
    @Column(name = "surplus_number")
    private Integer surplusNumber;

    /**
     * 总载人数
     */
    @Column(name = "number_of_passengers")
    private Integer numberOfPassengers;

    /**
     * 简短描述
     */
    private String describe;

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
     * 预计行驶时间
     */
    @Column(name = "driving_time")
    private String drivingTime;

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
    private Integer enable;

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

  }