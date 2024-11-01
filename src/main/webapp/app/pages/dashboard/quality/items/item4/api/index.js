export async function getDataSource() {
    let dataSource = [
        {
            "name": "任务包1",
            "issue": 100, //质量问题
            "toZero": 80,     //质量归零
            "key1": 40,  //实施方案评审
            "key2": 20,//工艺评审
            "key3": 20,//首件鉴定评审
            "key4": 20,//出厂评审
        },
        {
            "name": "任务包2",
            "issue": 100,
            "toZero": 100,
            "key1": 25,
            "key2": 25,
            "key3": 25,
            "key4": 25,
        },
        {
            "name": "任务包3",
            "issue": 34,
            "toZero": 12,
            "key1": 8,
            "key2": 8,
            "key3": 9,
            "key4": 9,
        },
        {
            "name": "任务包4",
            "issue": 14,
            "toZero": 12,
            "key1": 3,
            "key2": 4,
            "key3": 3,
            "key4": 4,
        },
        {
            "name": "任务包5",
            "issue": 25,
            "toZero": 12,
            "key1": 6,
            "key2": 7,
            "key3": 6,
            "key4": 6,
        },
        {
            "name": "任务包6",
            "issue": 35,
            "toZero": 13,
            "key1": 8,
            "key2": 9,
            "key3": 9,
            "key4": 9,
        },
        {
            "name": "任务包7",
            "issue": 25,
            "toZero": 14,
            "key1": 5,
            "key2": 6,
            "key3": 7,
            "key4": 7,
        },
        {
            "name": "任务包8",
            "issue": 54,
            "toZero": 15,
            "key1": 13,
            "key2": 13,
            "key3": 14,
            "key4": 14,
        },
        {
            "name": "任务包9",
            "issue": 32,
            "toZero": 28,
            "key1": 8,
            "key2": 8,
            "key3": 8,
            "key4": 8,
        },
        {
            "name": "任务包10",
            "issue": 65,
            "toZero": 17,
            "key1": 15,
            "key2": 15,
            "key3": 17,
            "key4": 18,
        }
    ];
    
    return dataSource;
}