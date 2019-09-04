package com.gnerv.boot.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 实体扩展类
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/9/4
 */
public class BaseExtend extends Model<BaseExtend> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 扩展字段
     */
    @TableField(exist = false)
    private Object extend;

    public Object getExtend() {
        return extend;
    }

    public void setExtend(Object extend) {
        this.extend = extend;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
