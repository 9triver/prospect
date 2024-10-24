export async function getDataSource() {
    let xAxisData = [];
    for (let i = 0; i < 10; i++) {
        xAxisData.push('承研合同' + (i + 1));
    }
    let data = {
        "xAxis": {
            name: "承研合同",
            data: xAxisData
        },
        "series1": {
            name: "整体金额",
            data: [100, 100, 34, 14, 25, 35, 25, 54, 32, 65]
        },
        "series2": {
            name: "支出金额",
            data: [9, 110, 12, 11, 12, 13, 14, 15, 28, 17]
        },
    }
    return data;
}