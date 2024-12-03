let packageID = '';
function fillPackageModal(button) {
    // Lấy dữ liệu từ các thuộc tính data-
    let id = button.getAttribute("data-package-id");
    const packageName = button.getAttribute("data-package-name");
    const packageDescription = button.getAttribute("data-package-description");
    const packageAmount = button.getAttribute("data-package-amount");

    // Điền dữ liệu vào các input trong modal
    document.getElementById("edit_packageName").value = packageName;
    document.getElementById("edit_description").value = packageDescription;
    document.getElementById("edit_amount").value = packageAmount;
    packageID = id;
}
function btnEdit(){
    const packageName=    document.getElementById("edit_packageName").value;
    const packageDescription=   document.getElementById("edit_description").value ;
    const  packageAmount=  document.getElementById("edit_amount").value ;
     // if(packageID === '' || packageName === '' || packageDescription === '' || packageAmount === ''){
     //     alert("Bạn chưa điền đầy đủ thông tin")
     //     return
     // }
     const jsonData =JSON.stringify( {
         package_id: packageID,
         name: packageName,
         description: packageDescription,
         amount: packageAmount
     })
    fetch('/editCheckupPackage', {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: jsonData
    })
        .then(response => {
            if (response.ok) {
                alert("Cập nhật gói khám thành công")
                location.reload();
            }else {
                console.error('Cập nhật không thành công');
            }
        })
        .catch(error => {
            console.error('Đã xảy ra lỗi:', error);
        });
}

function filterPackages() {
    const searchValue = document.getElementById('searchInput').value.toLowerCase();
    const packages = document.querySelectorAll('.packages-container .package-item');
    packages.forEach(packageItem => {
        const title = packageItem.querySelector('.package-title').innerText.toLowerCase();
        const description = packageItem.querySelector('.package-description').innerText.toLowerCase();
        const price = packageItem.querySelector('.package-price').innerText.toLowerCase();

        if (title.includes(searchValue) || description.includes(searchValue) || price.includes(searchValue)) {
            packageItem.style.display = '';
        } else {
            packageItem.style.display = 'none';
        }
    });
}
