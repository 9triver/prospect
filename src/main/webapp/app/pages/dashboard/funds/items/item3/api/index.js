export async function getDataSource() {

    let data = {
        "xAxis": {
            name: "科目类型",
            data: ['材料费', '专用费', '外协费', '燃料动力费', '事务费', '固定资产折旧费', '管理费', '工资及劳务费', '收益', '不可预见费']
        },
        "series1": {
            name: "科目预算",
            data: [10, 40, 13, 14, 25, 15, 25, 24, 32, 30]
        },
        "series2": {
            name: "实际支付",
            data: [9, 32, 12, 11, 12, 13, 14, 15, 16, 17]
        },
    }
    return data;
}