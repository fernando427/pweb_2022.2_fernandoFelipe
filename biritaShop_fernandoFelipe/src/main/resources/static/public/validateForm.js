const form = document.querySelector('form');

//Validações de formulários com preço
form.addEventListener('submit', function(event) {
  const priceField = document.querySelector('#precoCompra');
  const price = parseFloat(priceField.value);

  if (isNaN(price) || price < 0.01 || price > 9999999999.99) {
    event.preventDefault();
    alert('O valor deve ser entre 0.01 e 9999999999.99');
  }
});

form.addEventListener('submit', function(event) {
  const priceField = document.querySelector('#precoVenda');
  const price = parseFloat(priceField.value);

  if (isNaN(price) || price < 0.01 || price > 9999999999.99) {
    event.preventDefault();
    alert('O valor deve ser entre 0.01 e 9999999999.99');
  }
});

form.addEventListener('submit', function(event) {
  const priceField = document.querySelector('#volume');
  const price = parseFloat(priceField.value);

  if (isNaN(price) || price < 0.01 || price > 9999999999.99) {
    event.preventDefault();
    alert('O valor deve ser entre 0.01 e 9999999999.99');
  }
});