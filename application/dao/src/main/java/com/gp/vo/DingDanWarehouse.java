package com.gp.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "handler" })
public class DingDanWarehouse {
    private Integer count;
    private WarehouseVo warehouseVo;

    public DingDanWarehouse() {
    }

    public DingDanWarehouse(Integer count, WarehouseVo warehouseVo) {
        this.count = count;
        this.warehouseVo = warehouseVo;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public WarehouseVo getWarehouseVo() {
        return warehouseVo;
    }

    public void setWarehouseVo(WarehouseVo warehouseVo) {
        this.warehouseVo = warehouseVo;
    }

    @Override
    public String toString() {
        return "DingDanWarehouse{" +
                "count=" + count +
                ", warehouseVo=" + warehouseVo +
                '}';
    }
}
