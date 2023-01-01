package com.kdao.light.entity;

import com.kdao.autocode.anno.AutoEntityField;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

/**
 * <p>Title: </p >
 * <p>Description: 角色所属权限</p >
 * <p>Company: www.hn1024.cn</p >
 * <p>create date: 2021/10/27 002719:47</p >
 *
 * @author : echosong
 * @version :1.0.0
 */
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class KdRolePermission extends  SysBase{
    @AutoEntityField("角色id")
    private Integer roleId;

    @AutoEntityField("权限id")
    private Integer permissionId;
}
