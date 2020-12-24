package com.gp.vo;

public class GoodsCar {
    private Integer id;
    private WarehouseVo warehouseVo;
    private Integer uid;
    private Integer shu;

    public GoodsCar(Integer id, WarehouseVo warehouseVo, Integer uid, Integer shu) {
        this.id = id;
        this.warehouseVo = warehouseVo;
        this.uid = uid;
        this.shu = shu;
    }

    public GoodsCar() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WarehouseVo getGoodsVo() {
        return warehouseVo;
    }

    public void setGoodsVo(WarehouseVo warehouseVo) {
        this.warehouseVo = warehouseVo;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getShu() {
        return shu;
    }

    public void setShu(Integer shu) {
        this.shu = shu;
    }

    @Override
    public String toString() {
        return "GoodsCar{" +
                "id=" + id +
                ", warehouseVo=" + warehouseVo +
                ", uid=" + uid +
                ", shu=" + shu +
                '}';
    }
}
