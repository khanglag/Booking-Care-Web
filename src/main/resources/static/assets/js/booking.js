const calendarGrid = document.getElementById("calendarGrid");
const prevBtn = document.getElementById("prevBtn");
const nextBtn = document.getElementById("nextBtn");
const selectedDateText = document.getElementById('selectedDate');
const selectedTimeText =document.getElementById('selected-time');
const today = new Date();
let timex
let datex
let currentStartDate = new Date();
currentStartDate.setDate(today.getDate() + 1);

function formatDate(date) {
    const daysOfWeek = ["Chủ nhật", "Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy"];
    const day = daysOfWeek[date.getDay()];
    const dateNum = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();
    return `${day} ${dateNum}/${month}/${year}`;
}
function convertDateFormat(dateString) {
    const datePart = dateString.split(' ').slice(2).join(' ');  // Loại bỏ phần tên ngày (Thứ bảy, Chủ nhật...)

    if (!datePart) {
        console.error("Ngày không hợp lệ:", dateString);
        return null;
    }

    const [day, month, year] = datePart.split('/');

    if (day && month && year) {
        // Đảm bảo rằng tháng và ngày có 2 chữ số, sau đó tạo lại chuỗi theo định dạng yyyy-mm-dd
        const formattedDate = `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`;
        return formattedDate;
    } else {
        console.error("Ngày không hợp lệ sau khi tách:", datePart);
        return null;  // Nếu không hợp lệ, trả về null
    }
}
function timeToSeconds(timeStr) {
    const [hours, minutes, seconds] = timeStr.split(':').map(Number);
    return (hours * 3600) + (minutes * 60) + seconds;
}
function renderCalendar(startDate) {
    let selectedDay = null;
    calendarGrid.innerHTML = "";
    for (let i = 0; i < 7; i++) {
        const date = new Date(startDate);
        date.setDate(date.getDate() + i);
        const dayDiv = document.createElement("div");
        dayDiv.className = "calendar-day";
        dayDiv.textContent = formatDate(date);
        dayDiv.addEventListener('click', () => {
            if (selectedDay) {
                selectedDay.classList.remove('selected');
            }
            dayDiv.classList.add('selected');
            selectedDay = dayDiv;
            const selectedDate = formatDate(date);
            selectedDateText.innerText = `Ngày chọn: ${selectedDate}`;

            datex = convertDateFormat(selectedDate) ;
            const examination_day = document.querySelector('.examination_day');
            if(datex && timex){
                examination_day.textContent = `${datex} ${timex}`
            }
        });
        calendarGrid.appendChild(dayDiv);
    }
    const tomorrow = new Date(today);
    tomorrow.setDate(today.getDate() + 1);
    if ( startDate.getDate() === tomorrow.getDate() &&
        startDate.getMonth() === tomorrow.getMonth() &&
        startDate.getFullYear() === tomorrow.getFullYear()) {

        prevBtn.disabled = true;
    } else {

        prevBtn.disabled = false;
    }
}

prevBtn.addEventListener("click", () => {
    currentStartDate.setDate(currentStartDate.getDate() - 7);
    renderCalendar(currentStartDate);
});

nextBtn.addEventListener("click", () => {
    currentStartDate.setDate(currentStartDate.getDate() + 7);
    renderCalendar(currentStartDate);
});

renderCalendar(currentStartDate);

function generateTimeSlots(startTime, endTime, containerId) {
    const container = document.getElementById(containerId);
    let currentTime = new Date();
    const [startHour, startMinute] = startTime.split(':');
    const [endHour, endMinute] = endTime.split(':');
    currentTime.setHours(startHour, startMinute, 0, 0);
    const endTimeObj = new Date(currentTime);
    endTimeObj.setHours(endHour, endMinute, 0, 0);
    while (currentTime < endTimeObj) {
        const timeSlotDiv = document.createElement('div');
        timeSlotDiv.className = 'time-slot';
        timeSlotDiv.textContent = formatTime(currentTime);
        timeSlotDiv.addEventListener('click', () => {
            const allSlots = document.querySelectorAll('.time-slot');
            allSlots.forEach(slot => slot.classList.remove('selected'));
            timeSlotDiv.classList.add('selected');
            const selectedTime = timeSlotDiv.textContent;
            selectedTimeText.textContent = selectedTime;
            timex = selectedTime;
            const examination_day = document.querySelector('.examination_day');
            const time_id = document.querySelector('.time_id');
            if(datex && timex){
                examination_day.textContent = `${datex} ${timex}`
            }
            const time2 = "11:00:00";
            const time1InSeconds = timeToSeconds(timex);
            const time2InSeconds = timeToSeconds(time2);

            if (time1InSeconds < time2InSeconds) {
                time_id.textContent = '0000001';
            } else {
                time_id.textContent = '0000002';
            }
        });

        container.appendChild(timeSlotDiv);
        currentTime.setMinutes(currentTime.getMinutes() + 30);
    }
}

function formatTime(date) {
    const hours = date.getHours();
    const minutes = date.getMinutes();
    const seconds = date.getSeconds();
    return `${padZero(hours)}:${padZero(minutes)}:${padZero(seconds)}`;
}

function padZero(value) {
    return value < 10 ? '0' + value : value;
}

generateTimeSlots('08:00', '11:30', 'morning-schedule');
generateTimeSlots('13:30', '18:00', 'afternoon-schedule');

function choosePackage(buttonElement) {
    const packageId = buttonElement.getAttribute("data-package-id");
    document.querySelector('.package_id').textContent = packageId;
}

function booking(){
    const available_datetime = document.querySelector('.available_datetime').textContent;
    const doctor_id = document.querySelector('.doctor_id').textContent;
    const package_id = document.querySelector('.package_id').textContent;
    const patient_id = document.querySelector('.patient_id').textContent;
    const time_id = document.querySelector('.time_id').textContent;
    const examination_day = document.querySelector('.examination_day').textContent;
    const noteTextarea = document.querySelector('.d_note').textContent;
    const status = "CHƯA DUYỆT"
    const jsonData = JSON.stringify({
        availableDatetime: available_datetime,
        doctor:doctor_id,
        packageField:package_id,
        patient:patient_id,
        time:time_id,
        examinationDay:examination_day,
        note:noteTextarea,
        status:status,
    })
    fetch('/booking', {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: jsonData
    })
        .then(response => {
            if (response.ok) {
                const bookingModal = new bootstrap.Modal(document.getElementById('bookingSuccessModal'));
                bookingModal.show();

            }else {
                console.error('Booking unsuccess'+response.status);
            }
        })
        .catch(error => {
            console.error('Đã xảy ra lỗi:', error);
        });

}

//===============================================================================
const formattedDate = today.toISOString().split('T')[0];

const noteTextarea = document.querySelector('.note');
const note = document.querySelector('.d_note');


document.querySelector('.available_datetime').textContent = formattedDate;
noteTextarea.addEventListener('input', () => {
    note.textContent = noteTextarea.value;
});

function redirectIndex(){
     window.location.href = '/index';
//const nameDoctor = document.querySelector('nameOfDoctor').value;
    // const phoneDoctor = document.querySelector('phoneOfDoctor').textContent;
    // const emailUser = document.querySelector('emailOfUser').textContent;
    // const nameUser = document.querySelector('nameOfUser').textContent;
    // const examination_day = document.querySelector('.examination_day').textContent;
    // console.log(nameDoctor, phoneDoctor,emailUser,nameUser,examination_day)
    //console.log(nameDoctor)
}