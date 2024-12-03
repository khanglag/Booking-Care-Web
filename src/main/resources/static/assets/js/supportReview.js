
function handleApproveClick(button) {
    const appointmentId = button.getAttribute('data-id');
    const patient = button.getAttribute('data-patient');
    const doctor = button.getAttribute('data-doctor');
    const availableDatetime = button.getAttribute('data-available-datetime');
    const packageField = button.getAttribute('data-package');
    const examinationDay = button.getAttribute('data-examination-day');
    const note = button.getAttribute('data-note');
    const status = button.getAttribute('data-status');

    // Tạo đối tượng JSON
    const jsonData = JSON.stringify({
        id: appointmentId,
        patient: patient,
        doctor: doctor,
        availableDatetime: availableDatetime,
        packageField: packageField,
        examinationDay: examinationDay,
        note: note,
        status: status
    });

    // Log dữ liệu ra console
    console.log("Thông tin JSON:");
    console.log(jsonData);
    fetch('/supportReview', {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: jsonData
    })
        .then(response => {
            if (response.ok) {
                console.error('Duyệt thành công');
                location.reload();
            }else {
                console.error('Duyệt không thành công');
            }
        })
        .catch(error => {
            console.error('Đã xảy ra lỗi:', error);
        });
}