const form = document.querySelector('form');

//Validações de formulários com preço
form.addEventListener('submit', function(event) {
  const precoCompra = document.querySelector('#precoCompra');
  const precoC = parseFloat(precoCompra.value);

  if (isNaN(precoC) || precoC < 0.01 || precoC > 9999999999.99) {
    event.preventDefault();
    alert('O valor da compra deve ser entre 0.01 e 9999999999.99');
  }
});

form.addEventListener('submit', function(event) {
  const precoVenda = document.querySelector('#precoVenda');
  const precoV = parseFloat(precoVenda.value);

  if (isNaN(precoV) || precoV < 0.01 || precoV > 9999999999.99) {
    event.preventDefault();
    alert('O valor da venda deve ser entre 0.01 e 9999999999.99');
  }
});

form.addEventListener('submit', function(event) {
  const valorVolume = document.querySelector('#volume');
  const valorV = parseFloat(valorVolume.value);

  if (isNaN(valorV) || valorV < 0.01 || valorV > 9999999999.99) {
    event.preventDefault();
    alert('O valor do volume deve ser entre 0.01 e 9999999999.99');
  }
});

form.addEventListener('submit', function(event) {
  const selectElement = document.getElementById("addSelect");

  if (selectElement.value === "") {
    event.preventDefault();
    alert("Por favor, selecione uma opção. Caso não tenha, adicione um cliente.");
  }

});

var selectElement = document.getElementById("editarSelect");
selectElement.style.display = "none";