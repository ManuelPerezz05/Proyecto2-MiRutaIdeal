
const map = L.map('mapa').setView([43.361, -5.849], 8);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; OpenStreetMap contributors'
}).addTo(map);

const iconos = {
    'albergue': 'img/icons/albergue.png',
    'restaurante': 'img/icons/restaurante.png',
    'farmacia': 'img/icons/farmacia.png',
    'taxi': 'img/icons/taxi.png',
    'punto_medico': 'img/icons/medico.png',
    'hotel': 'img/icons/hotel.png'
};

let marcadoresPorTipo = {};

function cargarPuntos() {
    fetch('http://localhost:3000/puntos_interes')
        .then(res => res.json())
        .then(puntos => {
            puntos.forEach(p => {
                const tipo = p.tipo ? p.tipo.toLowerCase() : 'otro';
                const iconoUrl = iconos[tipo] || iconos['albergue'];
                const icono = L.icon({
                    iconUrl: iconoUrl,
                    iconSize: [32, 32],
                    iconAnchor: [16, 32],
                    popupAnchor: [0, -32]
                });

                const marcador = L.marker([p.latitud, p.longitud], { icon: icono }).bindPopup(
                    `<strong>${p.nombre}</strong><br>${p.direccion || ''}<br>${p.precio || ''}<br>📞 ${p.telefono || ''}`
                );

                marcador.addTo(map);

                if (!marcadoresPorTipo[tipo]) {
                    marcadoresPorTipo[tipo] = [];
                }
                marcadoresPorTipo[tipo].push(marcador);
            });
        });
}

function toggleTipo(tipo, visible) {
    const t = tipo.toLowerCase();
    if (marcadoresPorTipo[t]) {
        marcadoresPorTipo[t].forEach(m =>
            visible ? m.addTo(map) : map.removeLayer(m)
        );
    }
}

document.querySelectorAll('input[type=checkbox]').forEach(input => {
    input.addEventListener('change', e => {
        toggleTipo(e.target.value, e.target.checked);
    });
});

document.getElementById('limpiar-mapa')?.addEventListener('click', () => {
    Object.values(marcadoresPorTipo).forEach(lista =>
        lista.forEach(m => map.removeLayer(m))
    );
});

fetch('http://localhost:3000/etapas/etapas/con_trazado')
  .then(res => res.json())
  .then(etapas => {
    let bounds = [];

    etapas.forEach(etapa => {
      if (!etapa.trazado) return;

      let trazado;
      try {
        trazado = typeof etapa.trazado === "string" ? JSON.parse(etapa.trazado) : etapa.trazado;
      } catch (e) {
        console.warn(`⚠️ Error al parsear trazado de etapa ${etapa.id}`, e);
        return;
      }

      if (trazado.type === "LineString") {
        const coords = trazado.coordinates.map(([lon, lat]) => [lat, lon]);
        bounds.push(...coords);

        L.polyline(coords, {
          color: 'green',
          weight: 4,
          opacity: 0.8
        }).addTo(map);

        L.marker(coords[0])
          .bindPopup(`<strong>${etapa.nombre}</strong>`)
          .addTo(map);
      }
    });

    if (bounds.length > 0) {
      map.fitBounds(bounds, { padding: [50, 50] });
    }
  });

function centrarEnEtapa(etapaId) {
    fetch(`http://localhost:3000/etapas/${etapaId}`)
        .then(res => res.json())
        .then(etapa => {
            if (!etapa.trazado) return;
            let trazado;
            try {
                trazado = typeof etapa.trazado === "string" ? JSON.parse(etapa.trazado) : etapa.trazado;
            } catch (e) {
                console.warn(`Error al parsear trazado de etapa ${etapaId}`, e);
                return;
            }

            if (trazado.type === "LineString") {
                const coords = trazado.coordinates.map(([lon, lat]) => [lat, lon]);
                map.fitBounds(coords, { padding: [40, 40] });
            }
        })
        .catch(err => console.error("Error al centrar en etapa:", err));
}

document.addEventListener("DOMContentLoaded", function () {
    cargarPuntos();

    document.querySelectorAll("tr[data-etapa-id]").forEach(row => {
        row.addEventListener("click", () => {
            const etapaId = row.dataset.etapaId;
            centrarEnEtapa(etapaId);
        });
    });
});

  document.addEventListener('DOMContentLoaded', () => {
    const botones = document.querySelectorAll('.toggle-etapa');
    botones.forEach(boton => {
      boton.addEventListener('click', () => {
        const id = boton.getAttribute('data-target');
        const fila = document.getElementById(id);
        if (fila) fila.classList.toggle('hidden');
      });
    });
  });

  document.addEventListener("DOMContentLoaded", function () {
    const galeria = document.querySelector('.galerias-ficha');

    if (!galeria) {
      console.warn('No se encontró .galerias-ficha');
      return;
    }

    // Espera a que todas las imágenes estén cargadas
    const images = galeria.querySelectorAll('img');
    let loadedCount = 0;
    let totalImages = images.length;

    if (totalImages === 0) {
      inicializarSlick();
      return;
    }

    images.forEach(img => {
      if (img.complete && img.naturalHeight !== 0) {
        imagenCargada();
      } else {
        img.addEventListener('load', imagenCargada);
        img.addEventListener('error', imagenCargada);
      }
    });

    function imagenCargada() {
      loadedCount++;
      if (loadedCount === totalImages) {
        inicializarSlick();
      }
    }

    function inicializarSlick() {
      $('.galerias-ficha').slick({
        infinite: true,
        slidesToShow: 3,
        slidesToScroll: 1,
        dots: true,
        arrows: true,
        responsive: [
          {
            breakpoint: 768,
            settings: {
              slidesToShow: 2
            }
          },
          {
            breakpoint: 480,
            settings: {
              slidesToShow: 1
            }
          }
        ]
      });

      // 🔄 Forzar refresco visual si hay glitches
      setTimeout(() => {
        $('.galerias-ficha').slick('setPosition');
      }, 300);
    }
  });