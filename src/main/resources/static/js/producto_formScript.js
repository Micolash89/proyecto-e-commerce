(function () {

    const inputFile = document.getElementById("input-file");
    const inputButton = document.getElementById("input-button");
    inputFile.addEventListener("change", unploadImage);
    inputFile.addEventListener("click", unploadImage);
    const imageView = document.getElementById("img-view");

    const dropArea = document.getElementById("drop-area");
    const categoria = document.getElementById("categoria");
    const fabricante = document.getElementById("fabricante");

    const categoriaRadio = document.getElementById("categoria-radio");
    const fabricanteRadio = document.getElementById("fabricante-radio");
    console.info(inputFile);
    function unploadImage() {


        if (inputFile.files != null) {
            let imgLink = URL.createObjectURL(inputFile.files[0]);
            imageView.style.backgroundImage = `url(${imgLink})`;
        }
        imageView.textContent = "";
        imageView.style.border = 0;
        imageView.style.width = "100%";
        imageView.style.height = "300px";
        inputButton.hidden = false;
    }

    dropArea.addEventListener("dragover", function (e) {
        e.preventDefault();
    });
    dropArea.addEventListener("drop", function (e) {
        e.preventDefault();
        inputFile.files = e.dataTransfer.files;
        unploadImage();
    });

    inputButton.addEventListener("click", boton)

    function boton(e) {
        e.preventDefault();
        console.log("hola")
        inputFile.value = "";
        imageView.style.backgroundImage = `url("")`;
        inputButton.hidden = true;
        imageView.innerHTML = `<i class="bi bi-cloud-arrow-up-fill"></i>
        <p>click o arrastre y suelte <br>para agregar el archivo</p>
        <span>subir una imagen</span>`;
        imageView.style.border = "2px";
        inputButton.hidden = true;
        imageView.style.height = "100%";
        imageView.style.borderStyle = "dashed"
        imageView.style.borderColor = "#bbb5ff";
    }

    categoriaRadio.addEventListener("click", categoriaFunction);

    function categoriaFunction() {
        console.log("hola");
        console.log(categoriaRadio.checked);
        if (categoriaRadio.checked) {
            categoria.innerHTML = '<span class="input-group-text" id="inputGroup-sizing-default">Categoria</span> <input type="text" name="idCategoria" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">';
            categoria.classList.add("input-group");
        } else {
            categoria.classList.remove("input-group");
            categoria.innerHTML = '<select name="idCategoria" class="form-select" aria-label="Default select example"> <option selected>Seleccionar categoria</option><option th:each="categoria:${categorias}" th:value="${categoria.id}" th:text="${categoria.nombre}" /> </select>'
        }
    }

    fabricanteRadio.addEventListener("click", fabricanteFunction);

    function fabricanteFunction() {

        if (fabricanteRadio.checked) {
            fabricante.innerHTML = '<span class="input-group-text" id="inputGroup-sizing-default">Fabricante</span> <input type="text" name="idFabricante" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">';
            fabricante.classList.add("input-group");
        } else {
            fabricante.classList.remove("input-group");
            fabricante.innerHTML = '<select name="idFabricante" class="form-select" aria-label="Default select example"> <option selected>Seleccionar fabricante</option><option th:each="fabricante:${fabricantes}" th:value="${fabricante.id}" th:text="${fabricante.nombre}" /> </select>'
        }

    }

})();
