function getUserInfo() {
    const id = document.querySelector('.form_idOfUser')?.textContent || '';
    const name = document.querySelector('.form_nameOfUser')?.textContent || '';
    const gender = document.querySelector('.form_sexOfUser')?.textContent || 'Nam';
    const identificationCard = document.querySelector('.form_identificationOfUser')?.textContent || '';
    const phoneNumber = document.querySelector('.form_phoneOfUser')?.textContent || '';
    const email = document.querySelector('.form_emailOfUser')?.textContent || '';
    const address = document.querySelector('.form_addressOfUser')?.textContent || '';
    return {
        id,
        name,
        gender,
        identificationCard,
        phoneNumber,
        email,
        address
    };
}

function fillUserInfo() {
    const userInfo = getUserInfo();
    document.querySelector('.input_idOfUser').value = userInfo.id;
    document.querySelector('.input_nameOfUser').value = userInfo.name;
    document.querySelector('.input_sexOfUser').value = userInfo.gender;
    document.querySelector('.input_identificationOfUser').value = userInfo.identificationCard;
    document.querySelector('.input_phoneOfUser').value = userInfo.phoneNumber;
    document.querySelector('.input_emailOfUser').value = userInfo.email;
    document.querySelector('.input_addressOfUser').value = userInfo.address;
}
const btnSubmitInfoUser = document.getElementById("btn_saveInfoUser");
btnSubmitInfoUser.addEventListener('click',()=>{
    const userInfo = getUserInfo();
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
        fetch('/individual', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: jsonData
        })
            .then(response => {
                if (response.ok) {
                    location.reload();
                }else {
                    console.error('Cập nhật không thành công');
                }
            })
            .catch(error => {
                console.error('Đã xảy ra lỗi:', error);
            });

})