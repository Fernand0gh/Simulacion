var radio=300, n=0, aciertos=0, pi=0, lanzamientos=0;
function setup() {
    createCanvas(radio*2, radio*2);
    dibujando_diana();
}

function dibujando_diana() {
    fill(color(255,255,255));
    rect(0,0,radio*2,radio*2);
    fill(color(255,0,0));
    circle(300,300,radio*2);
    fill(color(255,255,255));
    circle(300,300,1.75*radio);
    fill(color(255,0,0));
    circle(300,300,radio*1.5);
    fill(color(255,255,255));
    circle(300,300,1.25*radio);
    fill(color(255,0,0));
    circle(300,300,radio);
    fill(color(255,255,255));
    circle(300,300,0.75*radio);
    fill(color(255,0,0));
    circle(300,300,0.5*radio);
    fill(color(255,255,255));
    circle(300,300,0.25*radio);
}

function obtener_numero_pseudoaleatorio_en_rango(min, max){
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1) + min) + Math.random();
}


function lanzar_dardos(){
    event.preventDefault();
    lanzamientos= parseInt(document.getElementById('dardos').value);
    n+=lanzamientos;
    strokeWeight(5);

    for(let i=0; i<lanzamientos; i++){
        let x = obtener_numero_pseudoaleatorio_en_rango(0,600);
        let y = obtener_numero_pseudoaleatorio_en_rango(0,600);
        stroke(color(parseInt(obtener_numero_pseudoaleatorio_en_rango(0,255)),parseInt(obtener_numero_pseudoaleatorio_en_rango(0,255)),parseInt(obtener_numero_pseudoaleatorio_en_rango(0,255))));

        point(x,y);     
        x-=300;
        y-=300;
        x=Math.abs(x);
        y=Math.abs(y);
        let h = Math.pow(x,2) + Math.pow(y,2);
        let comprobacion = Math.sqrt(h);
        if (comprobacion <=300) {
           aciertos++;
        }
    }
    pi= (aciertos/n)*4;
    document.getElementById("valor_aproximado").innerHTML="<p class='text-light bg-dark'>Dardos lanzados: " + n + "<br>Dardos dentro de la diana: " + aciertos + "<br>Valor aproximado de PI: " + pi + "</p>";
}
function reiniciar() {
    event.preventDefault();
    dibujando_diana()
    aciertos=0;
    n=0;
    let tomar_valor = document.getElementById("reiniciar");
    if(tomar_valor.value != "") {
        tomar_valor.value = ""; 
    } 
}