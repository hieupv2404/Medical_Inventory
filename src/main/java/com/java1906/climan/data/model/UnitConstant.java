package com.java1906.climan.data.model;

public enum UnitConstant {
    CAN(1),
    LANG(2),
    DONGCAN(3),
    PHAN(4),
    GAM(5),
    LY(6);

    private Integer value;

    private UnitConstant(Integer value) {
        this.value = value;
    }
    

    public Integer getValue() {
        return this.value;
    }




    /*
    1 cân = 500 gam.
    1 lạng = 31,25 gam.
    1 đồng cân = 3,1 gam.
    1 phân = 0,31 gam.
    1 gam = 3 phân 2 ly.
    1 ly = 0,03 gam.
     */
}
