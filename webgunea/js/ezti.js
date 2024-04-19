document.querySelector("#show-login").addEventListener("click", function(){
    document.querySelector(".popup").classList.add("active");
});
document.querySelector(".popup .close-btn").addEventListener("click", function(){
    document.querySelector(".popup").classList.remove("active");
});




function load_page(page){
    window.location.href = page;
}


async function hasiSaio(){
    let erabiltzailea = document.querySelector("#erabiltzaile").value;
    let pasahitza = document.querySelector("#pasahitz").value;

    let response = await fetch ('json/accounts.json');
    let kontuak = await response.json();
    console.log(kontuak);
    for (i=0; i<kontuak.length; i++){

        if(kontuak[i].erabiltzailea == erabiltzailea && kontuak[i].pasahitza == pasahitza){
            load_page('erleak.html')
        }
    }
}

function check_enter(event){
    if(event.keyCode == 13){
        hasiSaio();
    }
}


async function print_eztiak(){

    let eztiak = await(await fetch("json/eztia.json")).json();

    let taula = "<tr><thead><td>Izena</td><td>Deskripzioa</td><td>Prezioa</td><td>Pisua</td><td>Irudia</td></thead></tr>";

    let irudia = document.getElementById("irudia").value;

    for(i=0; i<eztiak.length; i++){
        let produktuak = "<tr><td>"+eztiak[i].izena+"</td><td>"+eztiak[i].deskripzioa+"</td><td>"+eztiak[i].prezioa+"</td><td><img src='media/products/"+eztiak[i].irudia+"'></td></tr>";
        taula = taula + produktuak;
    }

    document.querySelector("#products").innerHTML = taula;
    localStorage.setItem("eztiak", JSON.stringify(eztiak));
}