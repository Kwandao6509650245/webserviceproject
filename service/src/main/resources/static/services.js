const baseUrl = 'http://localhost:8081/api';

// โหลดรายการบริการทั้งหมด
async function loadServices() {
    const res = await fetch(`${baseUrl}/services`);
    const services = await res.json();

    const container = document.getElementById('services');
    container.innerHTML = '';

    services.forEach(service => {
        const div = document.createElement('div');
        div.className = 'service';
        div.innerHTML = `
      <strong>${service.name}</strong><br>
      ${service.description}<br>
      <div class="rating">
        <label>Rate this service: </label>
        <select id="rating-${service.id}">
          <option value="">--</option>
          <option value="1">1 ⭐</option>
          <option value="2">2 ⭐</option>
          <option value="3">3 ⭐</option>
          <option value="4">4 ⭐</option>
          <option value="5">5 ⭐</option>
        </select>
        <input type="text" id="comment-${service.id}" placeholder="Optional comment" />
        <button onclick="submitRating(${service.id})">Submit</button>
      </div>
    `;
        container.appendChild(div);
    });
}

// ส่งเรตติ้ง
async function submitRating(serviceId) {
    const rating = document.getElementById(`rating-${serviceId}`).value;
    const comment = document.getElementById(`comment-${serviceId}`).value;

    if (!rating) {
        alert('Please select a rating.');
        return;
    }

    const body = {
        serviceId: serviceId,
        rating: parseInt(rating),
        comment: comment
    };

    const res = await fetch(`${baseUrl}/rate-service`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(body)
    });

    if (res.ok) {
        alert('Thank you for your feedback!');
        document.getElementById(`rating-${serviceId}`).value = '';
        document.getElementById(`comment-${serviceId}`).value = '';
    } else {
        alert('Error submitting rating.');
    }
}

loadServices();