# KinoXP

ER Diagram

Et Chen-notation ER diagram der kortlægger KinoXP domænet. Diagrammet viser 8 entiteter — Cinema, Hall, Film, Screening, Seat, ReservationSeat, Reservation og Admin — med deres attributter og relationer. Dette viser den fulde dataarkitektur inklusive sædekategorier, reservationslogik og rollebaseret adgangsseparation mellem admin og kunde.

<img width="1024" height="768" alt="KinoXPER (2)" src="https://github.com/user-attachments/assets/87fea7b2-2a55-438a-9c65-c4a96dd26578" />


Wireframes

Fem wireframes der dækker hele brugerrejsen. Kundesiden: hjemmeside med forestillingsoversigt, sædevalgside med farvekodede cowboy- og sofapladser samt dynamisk prisberegning, og en bekræftelsesside efter booking. Adminsiden: sikker loginside, hoveddashboard med håndtering af forestillinger og reservationer.

<img width="1024" height="768" alt="KINOXPHOMEPAGE (1)" src="https://github.com/user-attachments/assets/6f3d9ae8-b1af-4916-8511-70979c44240a" />

<img width="1024" height="768" alt="KINOXPBOOKINGPAGE (1)" src="https://github.com/user-attachments/assets/699c558c-6e1f-4f7b-b483-86c87e78b9d9" />

<img width="1024" height="768" alt="KINOXPBOOKINGPAGE (2)" src="https://github.com/user-attachments/assets/d5bfba49-83bc-4d88-9e43-e32f80c6d31d" />

<img width="1024" height="768" alt="KINOXPBOOKINGPAGE (5)" src="https://github.com/user-attachments/assets/a544f02c-64b7-4b81-8fa6-ec71b9cc75b3" />

<img width="1024" height="768" alt="KINOXPBOOKINGPAGE (7)" src="https://github.com/user-attachments/assets/f137ca33-31a3-4d05-be14-8203fc925be1" />


KinoXP Product Backlog

Sprint 1 — March 3–7

| Story | Priority | Points | Status |
| --- | --- | --- | --- |
| As a customer I want to see a list of current screenings so I know what films are showing and when | MVP | 1 | DONE |
| As a customer I want to see available seats for a screening so I can choose where to sit | MVP | 2 | DONE |
| As a customer I want to reserve specific seats so I can secure my spot | MVP | 3 | DONE |
| As a customer I want to see the total price before confirming so I know what I'm paying | MVP | 2 | DONE |
| As a customer I want to receive confirmation of my reservation so I have proof of booking | MVP | 1 | DONE |
| As a customer I want group discount applied automatically so I don't have to request it manually | MVP | 2 | PENDING |
| As an admin I want to log in securely so that former employees cannot access the system | MVP | 2 | PENDING |

Sprint 2 — March 8–14

| Story | Priority | Points | Status |
| --- | --- | --- | --- |
| As an admin I want to create a new screening so I can add films to the schedule | MVP | 2 | PENDING |
| As an admin I want to edit an existing screening so I can correct mistakes or update times | MVP | 2 | PENDING |
| As an admin I want to delete a screening so I can remove cancelled shows | MVP | 1 | PENDING |
| As an admin I want to see all reservations for a screening so I know how many seats are sold | MVP | 2 | PENDING |

Nice To Have

| Story | Priority | Points | Status |
| --- | --- | --- | --- |
| As a customer I want to log in so I can see my existing reservations | Nice To Have |  | PENDING |
| As an admin I want to manage seat pricing per hall so I can update prices without changing code | Nice To Have |  | PENDING |
| As an admin I want to add a 3D surcharge to a screening so the price reflects the equipment used | Nice To Have |  | PENDING |
| As an admin I want to block broken seats from being reserved so customers cannot book unavailable seats | Nice To Have |  | PENDING |


[burndown.html](https://github.com/user-attachments/files/25818836/burndown.html)
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Sprint 1 Burndown</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/4.4.1/chart.umd.min.js"></script>
<link href="https://fonts.googleapis.com/css2?family=Cormorant+Garamond:wght@400;600&display=swap" rel="stylesheet">
<style>
  * { margin: 0; padding: 0; box-sizing: border-box; }
  body { font-family: Arial, sans-serif; background: #f5f0ec; display: flex; flex-direction: column; align-items: center; padding: 2rem; }
  h1 { font-family: 'Cormorant Garamond', serif; font-size: 2rem; color: #6B3A2A; margin-bottom: 0.3rem; }
  .subtitle { color: #888; font-size: 0.85rem; margin-bottom: 2rem; }
  .chart-box { background: white; border-radius: 6px; padding: 2rem; width: 100%; max-width: 800px; box-shadow: 0 2px 8px rgba(0,0,0,0.08); }
  .legend { display: flex; gap: 2rem; justify-content: center; margin-top: 1.5rem; }
  .legend-item { display: flex; align-items: center; gap: 0.5rem; font-size: 0.8rem; color: #555; }
  .legend-dot { width: 12px; height: 12px; border-radius: 50%; }
  .stats { display: flex; gap: 1rem; margin-top: 1.5rem; width: 100%; max-width: 800px; }
  .stat { background: white; border-radius: 6px; padding: 1rem 1.5rem; flex: 1; text-align: center; box-shadow: 0 2px 8px rgba(0,0,0,0.08); }
  .stat-num { font-family: 'Cormorant Garamond', serif; font-size: 2rem; color: #6B3A2A; }
  .stat-label { font-size: 0.75rem; color: #888; margin-top: 0.2rem; }
</style>
</head>
<body>

<h1>Sprint 1 Burndown</h1>
<p class="subtitle">KinoXP · 7 days · 13 story points</p>

<div class="chart-box">
  <canvas id="chart"></canvas>
  <div class="legend">
    <div class="legend-item"><div class="legend-dot" style="background:#b09080"></div> Ideal</div>
    <div class="legend-item"><div class="legend-dot" style="background:#6B3A2A"></div> Actual</div>
  </div>
</div>

<div class="stats">
  <div class="stat"><div class="stat-num">13</div><div class="stat-label">Total Points</div></div>
  <div class="stat"><div class="stat-num">9</div><div class="stat-label">Completed</div></div>
  <div class="stat"><div class="stat-num">4</div><div class="stat-label">Remaining</div></div>
  <div class="stat"><div class="stat-num">69%</div><div class="stat-label">Completion</div></div>
</div>

<script>
  // Total: 13 points. Done: 9 pts (stories 1+2+3+2+1). Pending: 4 pts.
  // Ideal: linear from 13 to 0 over 7 days
  // Actual: team worked steadily, finished 9 pts, 4 remain
  const ideal = [13, 11, 9, 7, 5, 3, 1, 0];
  const actual = [13, 12, 10, 8, 6, 5, 4, 4]; // day 7 still has 4 pending

  const ctx = document.getElementById('chart').getContext('2d');
  new Chart(ctx, {
    type: 'line',
    data: {
      labels: ['Start', 'Day 1', 'Day 2', 'Day 3', 'Day 4', 'Day 5', 'Day 6', 'Day 7'],
      datasets: [
        {
          label: 'Ideal',
          data: ideal,
          borderColor: '#b09080',
          borderDash: [6, 4],
          borderWidth: 2,
          pointRadius: 3,
          pointBackgroundColor: '#b09080',
          tension: 0,
          fill: false
        },
        {
          label: 'Actual',
          data: actual,
          borderColor: '#6B3A2A',
          borderWidth: 2.5,
          pointRadius: 5,
          pointBackgroundColor: '#6B3A2A',
          tension: 0.1,
          fill: {
            target: 0,
            above: 'rgba(107,58,42,0.05)'
          }
        }
      ]
    },
    options: {
      responsive: true,
      plugins: {
        legend: { display: false },
        tooltip: {
          callbacks: {
            label: ctx => ' ' + ctx.dataset.label + ': ' + ctx.parsed.y + ' pts remaining'
          }
        }
      },
      scales: {
        x: {
          grid: { color: '#f0e8e3' },
          ticks: { font: { size: 12 }, color: '#888' }
        },
        y: {
          min: 0,
          max: 14,
          grid: { color: '#f0e8e3' },
          ticks: {
            stepSize: 1,
            font: { size: 12 },
            color: '#888',
            callback: val => val + ' pts'
          },
          title: {
            display: true,
            text: 'Story Points Remaining',
            color: '#888',
            font: { size: 12 }
          }
        }
      }
    }
  });
</script>
</body>
</html>
