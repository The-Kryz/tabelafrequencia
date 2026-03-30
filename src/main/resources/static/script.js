async function calcular(tipo) {
    const input = document.getElementById('dados').value;
    if (!input) { 
        alert("Por favor, insira os dados!"); 
        return; 
    }

    const lista = input.split(',')
                       .map(n => parseInt(n.trim()))
                       .filter(n => !isNaN(n));
    
    try {
        const response = await fetch(`/api/frequencia/${tipo}`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(lista)
        });
        
        if (!response.ok) throw new Error("Erro na API");

        const data = await response.json();
        renderizarTabela(data, tipo);
    } catch (err) {
        document.getElementById('resultado').innerHTML = `<p style="color:red">Erro ao processar: ${err.message}</p>`;
    }
}

function renderizarTabela(data, tipo) {
    let titulo = tipo === 'discreta' ? "Tabela de Frequência Discreta" : "Tabela de Frequência Contínua";
    let html = `<h3>${titulo}</h3>`;
    html += `<table>
                <tr>
                    <th>${tipo === 'discreta' ? 'Valor' : 'Classe'}</th>
                    <th>FA (Absoluta)</th>
                    <th>FR% (Relativa)</th>
                    <th>FAc (Acumulada)</th>
                    <th>Frc% (Relativa Acumulada)</th>
                </tr>`;
    
    data.forEach(row => {
        html += `<tr>
                    <td><b>${row.classe}</b></td>
                    <td>${row.fa}</td>
                    <td>${row.fr.toFixed(2)}%</td>
                    <td>${row.fac}</td>
                    <td>${row.frc.toFixed(2)}%</td>
                 </tr>`;
    });
    
    document.getElementById('resultado').innerHTML = html + '</table>';
}