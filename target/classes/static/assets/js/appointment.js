function payment(button){

    const name = button.getAttribute('data-name');
    const price = button.getAttribute('data-price');

    // Tạo form động
    const form = document.createElement('form');
    form.method = 'POST'; // Chuyển dữ liệu bằng POST
    form.action = '/payment';

    // Thêm các input ẩn để chứa dữ liệu
    const nameInput = document.createElement('input');
    nameInput.type = 'hidden';
    nameInput.name = 'name';
    nameInput.value = name;

    const priceInput = document.createElement('input');
    priceInput.type = 'hidden';
    priceInput.name = 'price';
    priceInput.value = price;

    form.appendChild(nameInput);
    form.appendChild(priceInput);

    // Gắn form vào body và submit
    document.body.appendChild(form);
    form.submit();
}