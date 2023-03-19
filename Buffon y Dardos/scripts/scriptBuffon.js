var n = 0, lanzamientos = 0, data1 = [];
function obtener_numero_pseudoaleatorio_en_rango(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min) + Math.random();
}

// Gianluigi Buffon mi tio
const X_INICIAL = 0, Y_SUPERIOR = 0, ANCHO_RECTANGULO = 900, ALTURA_RECTANGULO = 300;
function setup() {
    createCanvas(ANCHO_RECTANGULO, ALTURA_RECTANGULO);
}

//se lee la cantidad de agujas a lanzar
function leer_n() {
    lanzamientos = parseInt(document.getElementById('teclado').value);
}

function grados_a_radianes(grados) {
    return grados * (Math.PI / 180);
}

const NUMERO_LINEAS = 6, DISTANCIA_ENTRE_SEPARACIONES = ANCHO_RECTANGULO / (NUMERO_LINEAS + 1);
function dibujar_rectangulo() {
    fill(color(255, 204, 0));
    rect(0, 0, 900, 300);

    //COLOR NEGRO
    fill(color(0, 0, 0))
    //PINTAR LAS LINEAS
    for (let i = 1; i <= NUMERO_LINEAS; i++) {
        fill(color(0, 0, 0))
        line(X_INICIAL + DISTANCIA_ENTRE_SEPARACIONES * i, Y_SUPERIOR, X_INICIAL + DISTANCIA_ENTRE_SEPARACIONES * i, Y_SUPERIOR + ALTURA_RECTANGULO);
    }

    //PINTAR LOS RECTANGULOS
    for (let i = 0; i <= NUMERO_LINEAS; i++) {
        if (i & 1) {
            fill(189, 280, 274)
        }
        else {
            fill(130, 235, 217)
        }
        rect(X_INICIAL + DISTANCIA_ENTRE_SEPARACIONES * i, Y_SUPERIOR, DISTANCIA_ENTRE_SEPARACIONES, ALTURA_RECTANGULO);
    }
}

var aprox, aciertos = 0, i = 1;
function dibujar_agujas() {
    event.preventDefault();

    n += lanzamientos;

    for (; i <= n; i++) {
        //calcular x y angulo
        let x1 = obtener_numero_pseudoaleatorio_en_rango(DISTANCIA_ENTRE_SEPARACIONES, ANCHO_RECTANGULO - DISTANCIA_ENTRE_SEPARACIONES - 1);
        let y1 = obtener_numero_pseudoaleatorio_en_rango(0, ALTURA_RECTANGULO - 1);
        let angulo = grados_a_radianes(obtener_numero_pseudoaleatorio_en_rango(0, 359));
        let x2 = x1 + DISTANCIA_ENTRE_SEPARACIONES * Math.cos(angulo);
        let y2 = y1 + DISTANCIA_ENTRE_SEPARACIONES * Math.sin(angulo);

        let r = int(obtener_numero_pseudoaleatorio_en_rango(1, 256));
        let g = int(obtener_numero_pseudoaleatorio_en_rango(1, 256));
        let b = int(obtener_numero_pseudoaleatorio_en_rango(1, 256));
        stroke(r, g, b);

        line(x1, y1, x2, y2);

        if (x1 > x2)
            [x1, x2] = [x2, x1];

        for (let j = 1; j <= NUMERO_LINEAS; j++) {
            const x_linea = DISTANCIA_ENTRE_SEPARACIONES * j;
            if (x1 <= x_linea && x2 >= x_linea) {
                aciertos++;
                break;
            }
        }
        

        if (aciertos > 0)
            aprox = (2 * i) / aciertos;
        else
            aprox = 0;
        data1.push({x: i, y: aprox }); //aproximacion a pi
    }
    document.getElementById("valor_aproximado").innerHTML = "<p class='text-light bg-dark'>Agujas lanzadas: " + n + "<br>Agujas que chocaron: " + aciertos + "<br>Valor aproximado de PI: " + aprox + "</p>";
    insertar_grafica();
}

//limpia la pagina 
function reiniciar() {
    dibujar_rectangulo();
    n = 0;
    aciertos = 0;
}

//crea la grafica
function grafica() {
    const ctx = document.getElementById('myChart');

    new Chart(ctx, {
        type: 'scatter',
        data: {
            datasets: [{
                label: 'Aproximación',
                borderColor: 'rgb(255,0,0)',
                data: data1,
            }]
        },
        options: {
            scales: {
                y:{
                    min:0,
                    max:5
                }
            },
            plugins:{
                zoom: {
                    zoom:{
                        wheel:{
                            enabled: true
                        }
                    }
                }
            }
        }
    });
}

//pinta la grafica en el html
function insertar_grafica() {
    document.getElementById("grafiquin").innerHTML = '<div class="d-inline-flex bg-light"><div><h1 class="hacheuno">Aproximaciones a PI</h1><canvas id="myChart" width="500" height="400"></canvas></div>';
}