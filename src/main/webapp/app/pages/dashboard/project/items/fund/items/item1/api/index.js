export async function getDataSource() {
    let dataSource = [
        {
            "name":"承研合同1",
            "total":100,    //整体金额
            "pay":80,        //支出金额
            "cost":10,       //成本
            "apply":3,      //实施
            //材料、专用、外协费
            "fee1Total": 1200, //课题预算情况
            "fee1Contract": 1150,//课题合同签订情况
            "fee1Cost": 1100,//课题财务成本情况
            // 其他费用
            "fee2Total": 900,
            "fee2Contract": 850,
            "fee2Cost": 800
        },
        {
            "name":"承研合同2",
            "total":100,
            "pay":110,
            "cost":4,
            "apply":5,
            "fee1Total": 1500,
            "fee1Contract": 1450,
            "fee1Cost": 1400,
            "fee2Total": 1100,
            "fee2Contract": 1050,
            "fee2Cost": 1000
        },
        {
            "name":"承研合同3",
            "total":34,
            "pay":12,
            "cost":6,
            "apply":7,
            "fee1Total": 1300,
            "fee1Contract": 1250,
            "fee1Cost": 1200,
            "fee2Total": 1000,
            "fee2Contract": 950,
            "fee2Cost": 900
        },
        {
            "name":"承研合同4",
            "total":14,
            "pay":12,
            "cost":8,
            "apply":9,
            "fee1Total": 1600,
            "fee1Contract": 1550,
            "fee1Cost": 1500,
            "fee2Total": 1200,
            "fee2Contract": 1150,
            "fee2Cost": 1100
        },
        {
            "name":"承研合同5",
            "total":25,
            "pay":12,
            "cost":10,
            "apply":11,
            "fee1Total": 1400,
            "fee1Contract": 1350,
            "fee1Cost": 1300,
            "fee2Total": 1100,
            "fee2Contract": 1050,
            "fee2Cost": 1000
        },
        {
            "name":"承研合同6",
            "total":35,
            "pay":13,
            "cost":12,
            "apply":13,
            "fee1Total": 1800,
            "fee1Contract": 1750,
            "fee1Cost": 1700,
            "fee2Total": 1400,
            "fee2Contract": 1350,
            "fee2Cost": 1300
        },
        {
            "name":"承研合同7",
            "total":25,
            "pay":14,
            "cost":14,
            "apply":15,
            "fee1Total": 1700,
            "fee1Contract": 1650,
            "fee1Cost": 1600,
            "fee2Total": 1300,
            "fee2Contract": 1250,
            "fee2Cost": 1200
        },
        {
            "name":"承研合同8",
            "total":54,
            "pay":15,
            "cost":16,
            "apply":17,
            "fee1Total": 2000,
            "fee1Contract": 1950,
            "fee1Cost": 1900,
            "fee2Total": 1600,
            "fee2Contract": 1550,
            "fee2Cost": 1500
        },
        {
            "name":"承研合同9",
            "total":32,
            "pay":28,
            "cost":18,
            "apply":19,
            "fee1Total": 1900,
            "fee1Contract": 1850,
            "fee1Cost": 1800,
            "fee2Total": 1500,
            "fee2Contract": 1450,
            "fee2Cost": 1400
        },
        {
            "name":"承研合同10",
            "total":65,
            "pay":17,
            "cost":20,
            "apply":21,
            "fee1Total": 2200,
            "fee1Contract": 2150,
            "fee1Cost": 2100,
            "fee2Total": 1800,
            "fee2Contract": 1750,
            "fee2Cost": 1700
        }
    ]
    return dataSource;
}