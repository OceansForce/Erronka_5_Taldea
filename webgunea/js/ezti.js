function load_page(page){
    window.location.href = page;
}

async function print_eztiak(){
    let eztiak = await(await fetch("json/eztia.json")).json();

    let taula = "<tr><thead><td>Izena</td><td>Deskripzioa</td><td>Prezioa</td></thead></tr>";
}