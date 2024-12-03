let userID= '';
function displaySelection(radio) {
    const resultDiv = document.getElementById('result');
    resultDiv.innerText = `${radio.value}`;
}
const btnSubmit = document.getElementById("btnSubmitAdmin");
btnSubmit.addEventListener('click',()=>{
    event.preventDefault();
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const username = document.getElementById('username').value;
    const pass = document.getElementById('password').value;
    const rePass =  document.getElementById('repass').value;
    const role = document.getElementById('result').textContent;
    if (name === '' || email === '' || username === '' || pass === '' || rePass === '' || role === '') {
        alert('Vui lòng nhập đầy đủ thông tin !!!!')
        return
    }
        const jsonData = JSON.stringify({
        email: email,
        name: name,
        account: {
            username: username,
            password: pass,
            role_id:role
        }
    });
    console.log(jsonData)
    if (pass === rePass) {
        fetch('/userManagement', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: jsonData
        })
            .then(response => {
                if (response.ok) {
                    alert("Tạo tài khoản thành công")
                    location.reload()
                } else {
                    alert('Đăng kí thất bại! Vui lòng thử lại.');
                }
            })
            .catch(error => {
                console.error('Đã xảy ra lỗi:', error);
                alert('Đã xảy ra lỗi! Vui lòng thử lại.');
            });
    } else {
        alert('Mật khẩu không khớp! Vui lòng thử lại.');
    }
})
function filterTable() {
    const keyword = document.getElementById('searchInput').value.toLowerCase();
    const rows = document.querySelectorAll('#userTable tbody tr');
    rows.forEach(row => {
        const rowText = row.textContent.toLowerCase();
        if (rowText.includes(keyword)) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
}
function editUser(button){
    const row = button.parentElement.parentElement;
    const cells = row.querySelectorAll('td');
    const userInfo = {
        stt: cells[0].innerText,
        id: cells[1].innerText,
        name: cells[2].innerText,
        phone: cells[3].innerText,
        identificationCard: cells[4].innerText,
        address: cells[5].innerText,
        email: cells[6].innerText,
        gender: cells[7].innerText
    };

    document.querySelector('.input_idOfUser').value = userInfo.id;
    document.querySelector('.input_nameOfUser').value = userInfo.name;
    document.querySelector('.input_sexOfUser').value = userInfo.gender;
    document.querySelector('.input_identificationOfUser').value = userInfo.identificationCard;
    document.querySelector('.input_phoneOfUser').value = userInfo.phone;
    document.querySelector('.input_emailOfUser').value = userInfo.email;
    document.querySelector('.input_addressOfUser').value = userInfo.address;

}
const btnSubmitInfoUser = document.getElementById("btn_saveInfoUser");
btnSubmitInfoUser.addEventListener('click',()=>{
    const id = document.querySelector('.input_idOfUser').value;
    const name = document.querySelector('.input_nameOfUser').value;
    const gender = document.querySelector('.input_sexOfUser').value;
    const identification =  document.querySelector('.input_identificationOfUser').value;
    const email =  document.querySelector('.input_emailOfUser').value;
    const address =  document.querySelector('.input_addressOfUser').value;
    const phone =  document.querySelector('.input_phoneOfUser').value;
    const description = "";
    const jsonData = JSON.stringify({
        userId: id,
        name:name,
        phoneNumber:phone,
        gender:gender,
        identificationCard:identification,
        email:email,
        address:address,
        description: description,
    });
    fetch('/editUser', {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: jsonData
    })
        .then(response => {
            if (response.ok) {
                alert("Cập nhật user thành công")
                location.reload();
            }else {
                console.error('Cập nhật không thành công');
            }
        })
        .catch(error => {
            console.error('Đã xảy ra lỗi:', error);
        });
})


