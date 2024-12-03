// function updateActionAndSubmit(year) {
//     const form = document.getElementById('yearForm');
//     form.action = `/statisticManagement/${year}`;
//     form.submit();
// }
function updateActionAndSubmit(year) {
    if (year) {
        // Tự động submit form sau khi chọn năm
        document.getElementById('yearForm').submit();
    } else {
        alert('Vui lòng chọn năm trước khi tiếp tục!');
    }
}
