function updateCurrentTime() {
    var currentTime = new Date();
    var hours = currentTime.getHours();
    var minutes = currentTime.getMinutes();
    var seconds = currentTime.getSeconds();
    var formattedTime = ("0" + hours).slice(-2) + ":" + ("0" + minutes).slice(-2) + ":" + ("0" + seconds).slice(-2);
    document.getElementById("current-time").innerText = formattedTime;

    // Gọi hàm futureTime và truyền giá trị currentTime
    futureTime(currentTime);
}

function futureTime(currentTime) {
    var futureDate = new Date(currentTime);
    var currentDayOfWeek = futureDate.getDay(); // Lấy số thứ tự của ngày trong tuần
    var daysToAdd = 1 - currentDayOfWeek; // Số ngày cần trừ để đưa về ngày đầu tuần (thứ hai)

    futureDate.setDate(futureDate.getDate() + daysToAdd + 7); // Cộng thêm 7 ngày để lấy ngày đầu tuần tiếp theo

    var day = futureDate.getDate();
    var month = futureDate.getMonth() + 1; // Tháng trong JavaScript được đếm từ 0 đến 11, nên cần cộng 1
    var year = futureDate.getFullYear();

    var formattedDate = day + '/' + month + '/' + year;
    document.getElementById("future-date").innerText = formattedDate;
}

setInterval(updateCurrentTime, 1000);
// Gọi hàm updateCurrentTime() khi trang được tải
window.onload = function () {
    updateCurrentTime();
    futureTime(new Date());
};