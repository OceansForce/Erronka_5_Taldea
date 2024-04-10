function load_page(page){
    window.location.href = page;
}

async function print_eztiak(){
    let eztiak = await(await fetch("../json/eztia.json")).json();

    let taula = "<tr><thead><td>Izena</td><td>Deskripzioa</td><td>Prezioa</td><td>Pisua</td><td>Irudia</td></thead></tr>";

    for(i=0; i<eztiak.length; i++){

        let produktuak = "<tr><td>"+eztiak[i].izena+"</td><td>"+eztiak[i].deskripzioa+"</td><td>"+eztiak[i].prezioa+"</td><td><img src="+eztiak[i].irudi+"></td></tr>";
        taula = taula + produktuak;
    }

    document.querySelector("#products").innerHTML = taula;

    localStorage.setItem("eztiak", JSON.stringify(eztiak));
}