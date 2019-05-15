package com.sxops.www.linfen.dao.model.journey;

import lombok.ToString;

import java.util.Date;
import javax.persistence.*;

@Table(name = "lf_car_info")
@ToString
public class LfCarInfo {
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

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取车辆品牌
     *
     * @return car_brand - 车辆品牌
     */
    public String getCarBrand() {
        return carBrand;
    }

    /**
     * 设置车辆品牌
     *
     * @param carBrand 车辆品牌
     */
    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    /**
     * 获取车辆名称
     *
     * @return car_name - 车辆名称
     */
    public String getCarName() {
        return carName;
    }

    /**
     * 设置车辆名称
     *
     * @param carName 车辆名称
     */
    public void setCarName(String carName) {
        this.carName = carName;
    }

    /**
     * 获取车辆所属地区编码
     *
     * @return car_area_coding - 车辆所属地区编码
     */
    public String getCarAreaCoding() {
        return carAreaCoding;
    }

    /**
     * 设置车辆所属地区编码
     *
     * @param carAreaCoding 车辆所属地区编码
     */
    public void setCarAreaCoding(String carAreaCoding) {
        this.carAreaCoding = carAreaCoding;
    }

    /**
     * 获取车辆颜色
     *
     * @return car_colour - 车辆颜色
     */
    public String getCarColour() {
        return carColour;
    }

    /**
     * 设置车辆颜色
     *
     * @param carColour 车辆颜色
     */
    public void setCarColour(String carColour) {
        this.carColour = carColour;
    }

    /**
     * 获取年份
     *
     * @return car_year - 年份
     */
    public Date getCarYear() {
        return carYear;
    }

    /**
     * 设置年份
     *
     * @param carYear 年份
     */
    public void setCarYear(Date carYear) {
        this.carYear = carYear;
    }

    /**
     * 获取车牌
     *
     * @return car_license_plate - 车牌
     */
    public String getCarLicensePlate() {
        return carLicensePlate;
    }

    /**
     * 设置车牌
     *
     * @param carLicensePlate 车牌
     */
    public void setCarLicensePlate(String carLicensePlate) {
        this.carLicensePlate = carLicensePlate;
    }

    /**
     * 获取核载人数
     *
     * @return number_of_passengers - 核载人数
     */
    public Integer getNumberOfPassengers() {
        return numberOfPassengers;
    }

    /**
     * 设置核载人数
     *
     * @param numberOfPassengers 核载人数
     */
    public void setNumberOfPassengers(Integer numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    /**
     * 获取驾驶证照片
     *
     * @return driver_license_photo_url - 驾驶证照片
     */
    public String getDriverLicensePhotoUrl() {
        return driverLicensePhotoUrl;
    }

    /**
     * 设置驾驶证照片
     *
     * @param driverLicensePhotoUrl 驾驶证照片
     */
    public void setDriverLicensePhotoUrl(String driverLicensePhotoUrl) {
        this.driverLicensePhotoUrl = driverLicensePhotoUrl;
    }

    /**
     * 获取行驶证照片
     *
     * @return driving_license_photo_url - 行驶证照片
     */
    public String getDrivingLicensePhotoUrl() {
        return drivingLicensePhotoUrl;
    }

    /**
     * 设置行驶证照片
     *
     * @param drivingLicensePhotoUrl 行驶证照片
     */
    public void setDrivingLicensePhotoUrl(String drivingLicensePhotoUrl) {
        this.drivingLicensePhotoUrl = drivingLicensePhotoUrl;
    }

    /**
     * 获取车辆外观照片
     *
     * @return car_appearance_photo_url - 车辆外观照片
     */
    public String getCarAppearancePhotoUrl() {
        return carAppearancePhotoUrl;
    }

    /**
     * 设置车辆外观照片
     *
     * @param carAppearancePhotoUrl 车辆外观照片
     */
    public void setCarAppearancePhotoUrl(String carAppearancePhotoUrl) {
        this.carAppearancePhotoUrl = carAppearancePhotoUrl;
    }

    /**
     * 获取是否启用
     *
     * @return enable - 是否启用
     */
    public Integer getEnable() {
        return enable;
    }

    /**
     * 设置是否启用
     *
     * @param enable 是否启用
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    /**
     * 获取车辆所属用户
     *
     * @return owned_user_uuid - 车辆所属用户
     */
    public String getOwnedUserUuid() {
        return ownedUserUuid;
    }

    /**
     * 设置车辆所属用户
     *
     * @param ownedUserUuid 车辆所属用户
     */
    public void setOwnedUserUuid(String ownedUserUuid) {
        this.ownedUserUuid = ownedUserUuid;
    }

    /**
     * 获取修改时间
     *
     * @return create_time - 修改时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置修改时间
     *
     * @param createTime 修改时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}