function handleUpdate(button) {
    const recordContainer = button.closest(".record-container");
    if (!recordContainer) {
        console.error("Không tìm thấy record-container!");
        return;
    }
    const recordId = button.getAttribute("data-record-id");
    const patientID = button.getAttribute("data-patient-id");
    const doctorID = button.getAttribute("data-doctor-id");
    const createAt = button.getAttribute("data-createAt");
    const description = recordContainer.querySelector(".description").value;
    const diagnosis = recordContainer.querySelector(".diagnosis").value;
    const treatmentPlan = recordContainer.querySelector(".treatmentPlan").value;
   const jsonData = JSON.stringify({
       recordId,
       patientID,
       doctorID,
       createAt,
       description,
       diagnosis,
       treatmentPlan
   })
    fetch('/updateMedicalRecord', {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: jsonData
    })
        .then(response => {
            if (response.ok) {
              alert("Cập nhật thành công hồ sơ bệnh nhân")
                location.reload()
            }else {
                alert("Cập nhật không thành công hồ sơ bệnh nhân")
            }
        })
        .catch(error => {
            console.error('Đã xảy ra lỗi:', error);
        });
}
function filterMedicalRecords() {
    const filterValue = document.getElementById("filterInput").value.toLowerCase().trim();
    const records = document.querySelectorAll(".record-container");
    let hasMatch = false;
    records.forEach((record) => {
        const patientID = record.querySelector(".record-patient-id")?.innerText.toLowerCase();
        const patientName = record.querySelector(".record-patient-name")?.innerText.toLowerCase();
        const doctorName = record.querySelector(".record-doctor-name")?.innerText.toLowerCase();
        if (
            patientID.includes(filterValue) ||
            patientName.includes(filterValue) ||
            doctorName.includes(filterValue)
        ) {
            record.style.display = "";
            hasMatch = true;
        } else {
            record.style.display = "none";
        }
    });
    if (!hasMatch) {
        console.log("Không tìm thấy hồ sơ phù hợp.");
    }
}
