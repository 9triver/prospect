const calcTime = (ms:string)=>{
    // 定义一些常量
    const milliPerMinute = 60000;
    const milliPerHour = milliPerMinute * 60;
    const milliPerDay = milliPerHour * 24;
    const milliPerYear = milliPerDay * 365; // 假设每年365天

    // 计算总分钟数、小时数、天数和年数
    const totalMinutes = Math.floor(Number(ms) / milliPerMinute);
    const hours = Math.floor(totalMinutes / 60);
    const days = Math.floor(hours / 24);
    const years = Math.floor(days / 365);

    // 计算剩余的时间单位
    const remainingDays = days % 365;
    const remainingHours = hours % 24;
    const remainingMinutes = totalMinutes % 60;

    // 如果总分钟数小于1，则直接返回 "小于1min"
    if (totalMinutes < 1) {
        return "小于1min";
    }

    // 如果有年份，则以年、天、小时和分钟的形式输出
    if (years > 0) {
        return `${years}y${remainingDays > 0 ? ` ${remainingDays}d` : ''}${remainingHours > 0 ? ` ${remainingHours}h` : ''}${remainingMinutes > 0 ? ` ${remainingMinutes}min` : ''}`;
    }

    // 如果有天数，则以天、小时和分钟的形式输出
    if (days > 0) {
        return `${days}d${remainingHours > 0 ? ` ${remainingHours}h` : ''}${remainingMinutes > 0 ? ` ${remainingMinutes}min` : ''}`;
    }

    // 如果有小时数，则以小时和分钟的形式输出
    if (hours > 0) {
        return `${hours}h${remainingMinutes > 0 ? ` ${remainingMinutes}min` : ''}`;
    }

    // 如果只有分钟数，则以分钟形式输出
    return `${remainingMinutes}min`;
}

export {
    calcTime
}