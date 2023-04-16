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
    alert("Por favor, selecione uma opção de cliente. Caso não tenha, adicione um cliente.");
  }

});

form.addEventListener('submit', function(event) {
  const selectElement = document.getElementById("addSelectProduto");

  if (selectElement.value === "") {
    event.preventDefault();
    alert("Por favor, selecione uma opção de produto.Caso não tenha, adicione um produto.");
  }

});

form.addEventListener('submit', function(event) {

  const regexTelefone = /^\(\d{2}\) \d{5}-\d{4}$/;
  const regexCep = /^[0-9]{5}-[0-9]{3}$/;
  const testTelefone = document.getElementById("telefone").value;
  const testCep = document.getElementById("cep").value;

  if (!regexTelefone.test(testTelefone)) {
    event.preventDefault();
    alert("Por favor, insira apenas números válidos no telefone.");
  }

  if (!regexCep.test(testCep)) {
    event.preventDefault();
    alert("Por favor, insira apenas números válidos no CEP.");
  }

});


function formatPhoneNumber(value) {
  if (!value) return value;
  const phoneNumber = value.replace(/[^\d]/g, "");
  const phoneNumberLength = phoneNumber.length;
  if (phoneNumberLength < 3) return phoneNumber;
  if (phoneNumberLength < 7) {
    return `(${phoneNumber.slice(0, 2)}) ${phoneNumber.slice(2)}`;
  }
  return `(${phoneNumber.slice(0, 2)}) ${phoneNumber.slice(
    2,
    7
  )}-${phoneNumber.slice(7, 10)}`;
}

function phoneNumberFormatter() {
  const inputField = document.getElementById("telefone");
  const formattedInputValue = formatPhoneNumber(inputField.value);
  inputField.value = formattedInputValue;
}

function formatCepNumber(value) {
  if (!value) return value;
  const cepNumber = value.replace(/[^\d]/g, "");
  return `${cepNumber.slice(0,5)}-${cepNumber.slice(5, 7)}`;
}

function cepNumberFormatter() {
  const inputField = document.getElementById("cep");
  const formattedInputValue = formatCepNumber(inputField.value);
  inputField.value = formattedInputValue;
}

var selectElement = document.getElementById("editarSelect");
selectElement.style.display = "none";

