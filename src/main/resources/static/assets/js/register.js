const btnSubmit = document.getElementById("btnSubmit");
btnSubmit.addEventListener('click',()=>{
    event.preventDefault();
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const username = document.getElementById('username').value;
    const pass = document.getElementById('password').value;
    const rePass =  document.getElementById('repass').value;
    const jsonData = JSON.stringify({
        email: email,
        name: name,
        account: {
            username: username,
            password: pass
        }
    });
    if (pass === rePass) {
        console.log(pass,rePass)
        fetch('/register', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: jsonData
        })
            .then(response => {
                if (response.ok) {
                    window.location.href = '/signin';
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