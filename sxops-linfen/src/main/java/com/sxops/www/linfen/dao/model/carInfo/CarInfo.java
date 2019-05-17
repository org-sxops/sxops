package com.sxops.www.linfen.dao.model.carInfo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import javax.persistence.*;

@Table(name = "lf_car_info")
@ToString
@Data
public class CarInfo {
    @Id
    private Long id;

    private String uuid;

    /**
     * 车辆品牌
     */
    @Column(name = "car_brand")
    private String carBrand;

    /**
     * 车辆名称
     */
    @Column(name = "car_name")
    private String carName;

    /**
     * 车辆所属地区编码
     */
    @Column(name = "car_area_coding")
    private String carAreaCoding;

    /**
     * 车辆颜色
     */
    @Column(name = "car_colour")
    private String carColour;

    /**
     * 年份
     */
    @Column(name = "car_year")
    private Date carYear;

    /**
     * 车牌
     */
    @Column(name = "car_license_plate")
    private String carLicensePlate;

    /**
     * 核载人数
     */
    @Column(name = "number_of_passengers")
    private Integer numberOfPassengers;

    /**
     * 驾驶证照片
     */
    @Column(name = "driver_license_photo_url")
    private String driverLicensePhotoUrl;

    /**
     * 行驶证照片
     */
    @Column(name = "driving_license_photo_url")
    private String drivingLicensePhotoUrl;

    /**
     * 车辆外观照片
     */
    @Column(name = "car_appearance_photo_url")
    private String carAppearancePhotoUrl;

    /**
     * 是否启用
     */
    private Integer enable;

    /**
     * 车辆所属用户
     */
    @Column(name = "owned_user_uuid")
    private String ownedUserUuid;

    /**
     * 修改时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    }