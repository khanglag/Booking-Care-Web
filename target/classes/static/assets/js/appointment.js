function payment(button){

    const name = button.getAttribute('data-name');
    const price = button.getAttribute('data-price');
    const form = document.createElement('form');
    form.method = 'POST';
    form.action = '/payment';
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

    document.body.appendChild(form);
    form.submit();
}