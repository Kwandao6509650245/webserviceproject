fetch('/api/available-slots')
    .then(res => res.json())
    .then(data => {
        const div = document.getElementById('slots');
        const select = document.getElementById('time');

        div.innerHTML = '<h2>Available Slots:</h2>' +
            '<ul>' +
            data.map(slot => {
                const date = new Date(slot);
                const formatted = date.toLocaleString('en-US', {
                    dateStyle: 'medium', timeStyle: 'short'
                });
                const option = document.createElement('option');
                option.value = slot;
                option.textContent = formatted;
                select.appendChild(option);
                return `<li>${formatted}</li>`;
            }).join('') +
            '</ul>';
    });


document.getElementById('bookingForm').addEventListener('submit', (e) => {
    e.preventDefault();
    const name = document.getElementById('name').value;
    const time = document.getElementById('time').value;

    fetch('/api/book-appointment', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, time })
    })
        .then(res => res.json())
        .then(data => {
            alert(`Booking confirmed for ${data.customerName} at ${new Date(data.time).toLocaleString()}`);
            location.reload();
            window.location.reload();
        })
        .catch(err => alert('Error: ' + err));


});
