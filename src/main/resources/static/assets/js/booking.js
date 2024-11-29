const calendarGrid = document.getElementById("calendarGrid");
const prevBtn = document.getElementById("prevBtn");
const nextBtn = document.getElementById("nextBtn");
const selectedDateText = document.getElementById('selectedDate');
const today = new Date();

let currentStartDate = new Date();
currentStartDate.setDate(today.getDate() + 1);

function formatDate(date) {
    const daysOfWeek = ["Chủ nhật", "Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy"];
    const day = daysOfWeek[date.getDay()];
    const dateNum = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();
    return `${day}  ${dateNum}/${month}/${year}`;
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
            document.getElementById('selected-time').textContent = selectedTime;
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

function choosePackage(packageID){
    console.log(packageID)
}