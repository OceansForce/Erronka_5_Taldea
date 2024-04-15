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


async function kargatuTaula(){
    let datuak = await (await fetch('json/eztia.json')).json();
    let taula = "<tr><thead><td>Irudia</td><td>Izena</td><td>Pisua</td><td>Prezioa</td></thead></tr>";

    let mota = document.getElementById("mota").value;
    let pisua = document.getElementById("pisua").value;

    for(i=0; i<datuak.length; i++){
        let newLine = "<tr><td><img src='images/products/"+datuak[i].irudia+"' width=200px height=200px></td><td>"+datuak[i].mota+"</td><td>"+datuak[i].pisua+"</td><td>"+datuak[i].prezioa+"</td></tr>";
        taula = taula + newLine;
    }

    document.querySelector("#katalogoa").innerHTML = taula;
    localStorage.setItem("datuak", JSON.stringify(datuak));
}


async function mota(){
    let datuak = await (await fetch('json/eztia.json')).json();
    let taula = "<tr><thead><td>Irudia</td><td>Izena</td><td>Pisua</td><td>Prezioa</td></thead></tr>";
    let mota = document.getElementById("mota").value;
    for(i=0; i<datuak.mota; i++){
        if(mota == datuak[i].mota){
            let newLine = "<tr><td><img src='images/products/"+datuak[i].irudia+"' width=200px height=200px></td><td>"+datuak[i].mota+"</td><td>"+datuak[i].pisua+"</td><td>"+datuak[i].prezioa+"</td></tr>";
            taula = taula + newLine;
        }
    }
    document.querySelector("#katalogoa").innerHTML = taula;
    localStorage.setItem("datuak", JSON.stringify(datuak));
}